package com.biencloud.smarthome.service.rest.handler;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 将Rest请求处理的异常信息解析为统一的错误对象。
 * @author <a href="mailto:Qian.Keane@gmail.com">keane</a>
 * @since 1.0 2013-9-20
 */
public interface RestErrorResolver {

    /**
     * Returns a {@code RestError} instance to render as the response body based on the given exception.
     *
     * @param request current {@link ServletWebRequest} that can be used to obtain the source request/response pair.
     * @param handler the executed handler, or <code>null</code> if none chosen at the time of the exception
     *                (for example, if multipart resolution failed)
     * @param ex      the exception that was thrown during handler execution
     * @return a resolved {@code RestError} instance to render as the response body or <code>null</code> for default
     *         processing
     */
    RestError resolveError(ServletWebRequest request, Object handler, Exception ex);

	void afterPropertiesSet() throws Exception;
}