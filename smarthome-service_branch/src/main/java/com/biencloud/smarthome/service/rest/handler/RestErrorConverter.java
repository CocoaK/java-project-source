package com.biencloud.smarthome.service.rest.handler;

import org.springframework.core.convert.converter.Converter;

/**
 * 将错误信息转换为期望的其它对象。
 * @author <a href="mailto:Qian.Keane@gmail.com">keane</a>
 * @since 1.0 2013-9-20
 * @see Converter
 */
public interface RestErrorConverter<T> extends Converter<RestError, T> {

    /**
     * Converts the RestError instance into an object that will then be used to render the response body.
     * @param re the instance to rest error
     * @return an object suited for HTTP response
     */
    T convert(RestError re);
}
