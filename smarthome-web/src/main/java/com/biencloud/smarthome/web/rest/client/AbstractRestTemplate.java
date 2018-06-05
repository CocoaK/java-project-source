package com.biencloud.smarthome.web.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriUtils;

import com.biencloud.smarthome.web.rest.handler.RestError;

/**
 * 基于Rest请求的客户端操作抽象类。
 * 
 * @since 1.0 2013-9-20
 * @see InterceptingHttpAccessor
 */
public abstract class AbstractRestTemplate extends InterceptingHttpAccessor {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static final int SYS_ERROR_CODE = 1001;
	
	protected static final ClassLoader CLASS_LOADER = AbstractRestTemplate.class.getClassLoader();
	
	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	
	private ResponseErrorHandler errorHandler = new LogResponseErrorHandler();
	
	private final ResponseExtractor<HttpHeaders> headersExtractor = new HeadersExtractor();
	
	
	public AbstractRestTemplate(){
		this.messageConverters.add(new ByteArrayHttpMessageConverter());
		this.messageConverters.add(new StringHttpMessageConverter());
		this.messageConverters.add(new ResourceHttpMessageConverter());
		@SuppressWarnings({"rawtypes"})
		HttpMessageConverter hmc = new SourceHttpMessageConverter();
		this.messageConverters.add(hmc);
		this.messageConverters.add(new XmlAwareFormHttpMessageConverter());
	}
	
	public AbstractRestTemplate(ClientHttpRequestFactory requestFactory){
		this();
		setRequestFactory(requestFactory);
	}
	
	
	public List<HttpMessageConverter<?>> getMessageConverters() {
		return messageConverters;
	}
	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		this.messageConverters = messageConverters;
	}
	
	public ResponseErrorHandler getErrorHandler() {
		return errorHandler;
	}
	public void setErrorHandler(ResponseErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}
	
	
	/**
	 * 从Http响应中提取Http头信息。
	 */
	protected static class HeadersExtractor implements ResponseExtractor<HttpHeaders> {
		public HttpHeaders extractData(ClientHttpResponse response) throws IOException {
			return response.getHeaders();
		}
	}
	
	/**
	 * 对URL进行编码。
	 */
	@SuppressWarnings("serial")
	protected static class HttpUrlTemplate extends UriTemplate {

		public HttpUrlTemplate(String uriTemplate) {
			super(uriTemplate);
		}

		@Override
		protected URI encodeUri(String uri) {
			try {
				@SuppressWarnings("deprecation")
				String encoded = UriUtils.encodeHttpUrl(uri, "UTF-8");
				return new URI(encoded);
			} catch (UnsupportedEncodingException ex) {
				throw new IllegalStateException(ex);
			} catch (URISyntaxException ex) {
				throw new IllegalArgumentException("Could not create HTTP URL from [" + uri + "]: " + ex, ex);
			}
		}
	}
	
	/**
	 * 准备请求接受的请求头信息。
	 */
	protected class AcceptHeaderRequestCallback implements RequestCallback {
		private final Class<?> responseType;

		public AcceptHeaderRequestCallback(Class<?> responseType) {
			this.responseType = responseType;
		}

		public void doWithRequest(ClientHttpRequest request) throws IOException {
			if (responseType != null) {
				List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
				for (HttpMessageConverter<?> messageConverter : getMessageConverters()) {
					if (messageConverter.canRead(responseType, null)) {
						List<MediaType> supportedMediaTypes = messageConverter.getSupportedMediaTypes();
						for (MediaType supportedMediaType : supportedMediaTypes) {
							if (supportedMediaType.getCharSet() != null) {
								supportedMediaType = new MediaType(supportedMediaType.getType(), 
										supportedMediaType.getSubtype());
							}
							allSupportedMediaTypes.add(supportedMediaType);
						}
					}
				}				
				
				if (!allSupportedMediaTypes.isEmpty()) {
					MediaType.sortBySpecificity(allSupportedMediaTypes);
					if (logger.isDebugEnabled())
						logger.debug("Setting request Accept header to {}", allSupportedMediaTypes);					
					request.getHeaders().setAccept(allSupportedMediaTypes);
				}
			}
		}
	}
	
	/**
	 * 将请求的数据写到请求中。
	 */
	protected class HttpEntityRequestCallback extends AcceptHeaderRequestCallback {
		@SuppressWarnings("rawtypes")
		private final HttpEntity requestEntity;

		public HttpEntityRequestCallback(Object requestBody) {
			this(requestBody, null);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public HttpEntityRequestCallback(Object requestBody, Class<?> responseType) {
			super(responseType);
			if (requestBody instanceof HttpEntity) {
				this.requestEntity = (HttpEntity) requestBody;
			} else if (requestBody != null) {
				this.requestEntity = new HttpEntity(requestBody);
			} else {
				this.requestEntity = HttpEntity.EMPTY;
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public void doWithRequest(ClientHttpRequest httpRequest) throws IOException {
			super.doWithRequest(httpRequest);
			if (!requestEntity.hasBody()) {
				HttpHeaders httpHeaders = httpRequest.getHeaders();
				HttpHeaders requestHeaders = requestEntity.getHeaders();
				if (!requestHeaders.isEmpty()) {
					httpHeaders.putAll(requestHeaders);
				}
				if (httpHeaders.getContentLength() == -1) {
					httpHeaders.setContentLength(0L);
				}
			} else {
				Object requestBody = requestEntity.getBody();
				Class<?> requestType = requestBody.getClass();
				HttpHeaders requestHeaders = requestEntity.getHeaders();
				MediaType requestContentType = requestHeaders.getContentType();								
				for (@SuppressWarnings("rawtypes") HttpMessageConverter messageConverter : getMessageConverters()) {
					if (messageConverter.canWrite(requestType, requestContentType)) {
						if (!requestHeaders.isEmpty()) {
							httpRequest.getHeaders().putAll(requestHeaders);
						}
						if (logger.isDebugEnabled()) {
							if (requestContentType != null)
								logger.debug("Writing [{}] as \"{}\" using [{}]", 
										new Object[]{requestBody, requestContentType, messageConverter});
							else
								logger.debug("Writing [{}] using [{}]", new Object[]{requestBody, messageConverter});
						}
						messageConverter.write(requestBody, requestContentType, httpRequest);
						return;
					}
				}
				StringBuilder message = new StringBuilder(
						"Could not write request: no suitable HttpMessageConverter found for request type [");				
				message.append(requestType.getName());
				message.append("]");
				if (requestContentType != null){
					message.append(" and content type [");
					message.append(requestContentType);
					message.append("]");
				}				
				throw new RestClientException(message.toString());
			}
		}
	}
	
	
	/**
	 * 获取响应头信息提取器。
	 * @return
	 */
	protected ResponseExtractor<HttpHeaders> getHeadersExtractor(){
		return headersExtractor;
	}
	
	/**
	 * 根据异常信息创建错误信息。
	 * @param e 异常信息
	 * @return
	 */
	protected RestError createError(Exception e){
		RestError re = new RestError();
		re.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		re.setCode(SYS_ERROR_CODE);
		re.setMessage(e.getMessage());
		//re.setDeveloperMessage(e.getMessage());
		re.setThrowable(e);
		return re;
	}

	/**
	 * 编码指定的URL和变量为URI，并执行编码后的URI请求。
	 * @param url 请求URL
	 * @param method 请求方法
	 * @param requestCallback 请求回调接口
	 * @param entityTypeRef 返回的实体类型引用
	 * @param urlVariables 请求URL对应的变量
	 * @return
	 */
	protected <T> RestResult<T> execute(String url, HttpMethod method, 
			RequestCallback requestCallback, EntityTypeReference<T> entityTypeRef, Object... urlVariables) {
		UriTemplate uriTemplate = new HttpUrlTemplate(url);
		URI expanded = uriTemplate.expand(urlVariables);
		return doExecute(expanded, method, requestCallback, entityTypeRef);
	}

	/**
	 * 编码指定的URL和变量为URI，并执行编码后的URI请求。
	 * @param url 请求URL
	 * @param method 请求方法
	 * @param requestCallback 请求回调接口
	 * @param entityTypeRef 返回的实体类型引用
	 * @param urlVariables 请求URL对应的变量
	 * @return
	 */
	protected <T> RestResult<T> execute(String url, HttpMethod method, 
			RequestCallback requestCallback, EntityTypeReference<T> entityTypeRef, Map<String, ?> urlVariables) {
		UriTemplate uriTemplate = new HttpUrlTemplate(url);
		URI expanded = uriTemplate.expand(urlVariables);
		return doExecute(expanded, method, requestCallback, entityTypeRef);
	}
	
	/**
	 * 执行指定的URI请求并返回响应结果，允许子类覆盖。
	 * @param uri 请求URI
	 * @param method 请求方法
	 * @param requestCallback 请求回调接口
	 * @param entityTypeRef 返回的实体类型引用
	 * @return
	 */
	protected <T> RestResult<T> doExecute(URI uri, HttpMethod method, 
			RequestCallback requestCallback, EntityTypeReference<T> entityTypeRef) {
		Assert.notNull(uri, "'uri' must not be null.");
		Assert.notNull(method, "'method' must not be null.");
		Assert.notNull(requestCallback, "'requestCallback' must not be null.");
		Assert.notNull(entityTypeRef, "'entityTypeRef' must not be null.");
		
		RestResult<T> restResult = new RestResult<T>();
		ClientHttpResponse response = null;
		try {
			ClientHttpRequest request = createRequest(uri, method);
			requestCallback.doWithRequest(request);
			response = request.execute();			
			restResult = extractResult(uri, response, entityTypeRef);			
		} catch (Exception e) {
			if(logger.isWarnEnabled()){
				logger.warn("Executing happens exception, the url is {}.", uri);
				logger.warn("The exception is:", e);
			}
			restResult.setSuccess(false);
			restResult.setError(createError(e));
		} finally {
			if (response != null)
				response.close();
		}
		return restResult;
	}

	/**
	 * 从响应中提取错误信息，如果提取信息发生异常，则将异常向上抛以便统一封装错误信息。
	 * @param response 响应信息
	 * @return
	 * @throws Exception 当提取错误数据发生异常时
	 */
	protected abstract RestError extractErrorData(ClientHttpResponse response) throws Exception;
	
	/**
	 * 从响应中提取目标实体信息，如果提取信息发生异常，则将异常向上抛以便统一封装错误信息。
	 * @param response 响应信息
	 * @param entityTypeRef 返回的实体类型引用
	 * @return
	 * @throws Exception 当提取实体数据发生异常时
	 */
	protected abstract <T> T extractEntityData(ClientHttpResponse response, 
			EntityTypeReference<T> entityTypeRef) throws Exception;
	
	
	//提取响应中的结果
	private <T> RestResult<T> extractResult(URI uri, ClientHttpResponse response, 
			EntityTypeReference<T> entityTypeRef) throws Exception {
		RestResult<T> restResult = new RestResult<T>();
		if (getErrorHandler().hasError(response)) {
			restResult.setSuccess(false);
			restResult.setError(extractErrorData(response));			
			if(logger.isWarnEnabled()){
				logger.warn("Executing happens exception, the url is {}.", uri);
				logger.warn("The exception is:", restResult.getError().getThrowable());
			}
		} else {
			restResult.setSuccess(true);		
			restResult.setEntity(extractEntityData(response, entityTypeRef));
		}
		return restResult;
	}
}
