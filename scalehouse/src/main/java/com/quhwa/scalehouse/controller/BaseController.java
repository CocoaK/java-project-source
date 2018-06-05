package com.quhwa.scalehouse.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.model.ResultList;
import com.quhwa.scalehouse.common.page.Page;
import com.quhwa.scalehouse.service.scale.service.IBaseService;

/**
 * 基础转接控制器
 * @param <T>
 */
/**增加跨域支持注解*/
@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class BaseController<T> {
	
	public abstract IBaseService<T> getBaseResService();
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<T>>> list(Page<T> p,T entity) {
		return getBaseResService().getForResultList(p, entity);
	}
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> add(T entity) {
		return getBaseResService().addForResultEntity(entity);
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(Integer id) {
		return getBaseResService().deleteByIdForResultEntity(id);
	}
	
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> update(T record) {
		return getBaseResService().updateForResultEntity(record);
	}

	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data){
		return new ResultEntity<A>(code,message,data);
	}
	
	/*@InitBinder  
	protected  void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}*/
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
}
