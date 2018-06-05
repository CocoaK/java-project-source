package com.biencloud.smarthome.service.rest.handler;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.ObjectUtils;

/**
 * 基于Rest的错误信息类。
 * @since 1.0 2016-9-20
 * @since 1.0 2016-3-7
 */
public class RestError {

    private int code;
    private String message;
    private String data;

    /**
     * 获取响应失败时的HTTP状态码
     * @return
     */
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
        return code;
    }
	
	/**
     * 获取响应失败时的提示信息
     * @return
     */
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
        return message;
    }

	/**
     * 获取响应失败时的详细信息
     * @return
     */
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof RestError) {
            RestError re = (RestError) o;
            return getCode() == re.getCode() &&
                   ObjectUtils.nullSafeEquals(getMessage(), re.getMessage()) &&
                   ObjectUtils.nullSafeEquals(getData(), re.getData());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{
                getCode(), getMessage(),getData()});
    }

    @Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
