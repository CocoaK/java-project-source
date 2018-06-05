package com.biencloud.smarthome.web.rest.client;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.biencloud.smarthome.web.rest.handler.RestError;

/**
 * 基于Rest的响应结果。
 * 
 * @since 1.0 2013-9-20
 * @serial Serializable
 */
public class RestResult<T> implements Serializable {

	private static final long serialVersionUID = -6721663356190064373L;

	private boolean isSuccess;
	
	private RestError error;
	
	private T entity;

	
	/**
	 * 请求是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 获取请求失败时的错误信息
	 * @return
	 */
	public RestError getError() {
		return error;
	}
	public void setError(RestError error) {
		this.error = error;
	}

	/**
	 * 获取请求成功时的实体对象
	 * @return
	 */
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
