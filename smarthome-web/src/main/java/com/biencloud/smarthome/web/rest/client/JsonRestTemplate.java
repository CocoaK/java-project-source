package com.biencloud.smarthome.web.rest.client;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.biencloud.smarthome.web.rest.handler.RestError;


/**
 * 基于Rest（JSON格式）请求的客户端操作类。
 * 
 * @since 1.0 2013-9-20
 * @see RestService
 * @see AbstractRestTemplate
 */
public class JsonRestTemplate extends AbstractRestTemplate implements RestService {
	
	private static final boolean JACKSON_PRESENT = ClassUtils.isPresent(
			"org.codehaus.jackson.map.ObjectMapper", CLASS_LOADER) && ClassUtils.isPresent(
					"org.codehaus.jackson.JsonGenerator", CLASS_LOADER);

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonRestTemplate(){
		super();	
		if (JACKSON_PRESENT)
			getMessageConverters().add(new MappingJacksonHttpMessageConverter());
	}
	
	public JsonRestTemplate(ClientHttpRequestFactory requestFactory){
		this();
		setRequestFactory(requestFactory);
	}		
	
	
	@Override
	public <T> RestResult<T> sendByGet(String url,
			EntityTypeReference<T> entityTypeRef, Object... urlVars) {
		RequestCallback requestCallback = new AcceptHeaderRequestCallback(String.class);		
		return execute(url, HttpMethod.GET, requestCallback, entityTypeRef, urlVars);
	}
	
	@Override
	public <T> RestResult<T> sendByGet(String url,
			EntityTypeReference<T> entityTypeRef, Map<String, ?> urlVars) {
		RequestCallback requestCallback = new AcceptHeaderRequestCallback(String.class);		
		return execute(url, HttpMethod.GET, requestCallback, entityTypeRef, urlVars);
	}

	@Override
	public <T> RestResult<T> sendByPost(String url, Object request,
			EntityTypeReference<T> entityTypeRef, Object... urlVars) {
		RequestCallback requestCallback = new HttpEntityRequestCallback(request, String.class);
		return execute(url, HttpMethod.POST, requestCallback, entityTypeRef, urlVars);
	}

	@Override
	public <T> RestResult<T> sendByPost(String url, Object request,
			EntityTypeReference<T> entityTypeRef, Map<String, ?> urlVars) {
		RequestCallback requestCallback = new HttpEntityRequestCallback(request, String.class);
		return execute(url, HttpMethod.POST, requestCallback, entityTypeRef, urlVars);
	}	
	

	@Override
	protected RestError extractErrorData(ClientHttpResponse response)
			throws Exception {
		ResponseExtractor<RestError> re = new HttpMessageConverterExtractor<RestError>(
				RestError.class, getMessageConverters());
		return re.extractData(response);
	}

	@Override
	protected <T> T extractEntityData(ClientHttpResponse response,
			EntityTypeReference<T> entityTypeRef) throws Exception {
		ResponseExtractor<String> responseExtractor =
				new HttpMessageConverterExtractor<String>(String.class, getMessageConverters());
		String jsonEntity = responseExtractor.extractData(response);
		
		if(logger.isDebugEnabled())
			logger.debug("************返回的实体信息（Json格式）：{} ************", jsonEntity);
		
		if(StringUtils.isEmpty(jsonEntity))
			return null;
				
		@SuppressWarnings("unchecked")
		T entity = (T)objectMapper.readValue(jsonEntity, entityTypeRef);	
		return entity;
	}
}
