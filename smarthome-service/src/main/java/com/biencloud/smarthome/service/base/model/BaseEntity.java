package com.biencloud.smarthome.service.base.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实体对象基类。
 * @author kouy
 * @since 1.0 2012-4-9
 * @see Serializable
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
