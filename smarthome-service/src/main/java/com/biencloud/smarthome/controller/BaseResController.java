package com.biencloud.smarthome.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;

/**
 * 基础转接控制器
 * @param <T>
 */

public abstract class BaseResController<T> {
	
	public abstract IBaseResService<T> getBaseResService();
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> add(T entity) {
		return getBaseResService().addForResultEntity(entity);
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(Integer id) {
		return getBaseResService().deleteByIdForResultEntity(id);
	}
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<T>>> list(Page<T> p,T entity) {
		return getBaseResService().getForResultList(p, entity);
	}
	
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> update(T record) {
		return getBaseResService().updateForResultEntity(record);
	}
	
	@RequestMapping(value="/getList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<T>>> getList(T entity) {
		return getBaseResService().getForResultList(entity);
	}

	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data){
		return new ResultEntity<A>(code,message,data);
	}
	
	@InitBinder  
	protected  void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}  
}
