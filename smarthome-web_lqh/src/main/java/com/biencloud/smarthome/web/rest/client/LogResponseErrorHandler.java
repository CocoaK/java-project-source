package com.biencloud.smarthome.web.rest.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * 用于记录响应的错误信息的处理器。
 * 
 * @since 1.0 2013-9-20
 * @see DefaultResponseErrorHandler
 */
public class LogResponseErrorHandler extends DefaultResponseErrorHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if(logger.isWarnEnabled())
			logger.warn("Request occurs exception, status code is {}.", response.getStatusCode());
	}
}
