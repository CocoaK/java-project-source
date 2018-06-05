package com.biencloud.smarthome.web.rest.client;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 定义该类为抽象类目的是用来获取子类定义的实体泛型信息，还有就是避免其它类直接与第三方类耦合。<br/>
 * 可通过创建匿名类来实现，例如：<br/>
 * 1、new EntityTypeReference<String>(){}；<br/>
 * 2、new EntityTypeReference<List<String>>(){}；<br/>
 * @author kouy
 * @since 1.0 2013-3-15
 * @see TypeReference
 */
public abstract class EntityTypeReference<T> extends TypeReference<T> {
	
	/**
	 * 判断当前类型是否可以强制转换为类。
	 * @param type 类型
	 * @return
	 */
	public boolean canConvertToClass(Type type){
		return (type instanceof Class);
	}
}
