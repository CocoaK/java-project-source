package com.biencloud.smarthome.web.base.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 值对象基类。
 * @author kouy
 * @since 1.0 2012-4-9
 * @see Object
 */
@SuppressWarnings("serial")
public class BaseVO implements Serializable {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}   
}
