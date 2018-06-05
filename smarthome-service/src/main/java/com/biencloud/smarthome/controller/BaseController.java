package com.biencloud.smarthome.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.ResultEntity;

/**
 * 基础转接控制器
 * @param <T>
 */

public abstract class BaseController<T> {
	
	public abstract IService<T,String> getBaseService();
	
	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody T create(T entity) {
		return getBaseService().save_update(entity);
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void delete(T entity) {
		getBaseService().remove(entity);
	}

	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data){
		return new ResultEntity<A>(code,message,data);
	}
}
