package com.biencloud.smarthome.web.base.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.ResultEntity;

public interface IBaseRestService<T> {	
    
    /**
	 * 增加返回格式化结果
	 * @param record
	 * @return
	 */
	public ResultEntity<String> addForResultEntity(T record) throws Exception;
	
	public <A> A addForResultEntity(Map map) throws Exception;
	
	/**
	 * 删除返回格式化结果
	 * @param id
	 * @return
	 */
	public ResultEntity<String> deleteByIdForResultEntity(final Integer id) throws Exception;
	
	public ResultEntity<String> deleteByIdForResultEntity(Map map) throws Exception;
	

	/**
	 * 获取分页查询结果
	 * @param url
	 * @param p
	 * @param obj
	 * @return
	 */
	public ResultEntity getResultListForPage(Map map) throws Exception;
	
	public ResultEntity getResultListForPage(T record) throws Exception;
	
	public ResultEntity getResultListForPage(PagingVO<T> p, T record) throws Exception;
	
	public  T getEntity(T record) throws Exception;
	
	/**
	 * 更新返回格式化结果
	 * @param record
	 * @return
	 */
	public ResultEntity<String> updateForResultEntity(T record) throws Exception;
	
	public ResultEntity<String> updateForResultEntity(Map map) throws Exception;
	
	/**
	 * 传递常用结果消息
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data);
		
	/**
	 * POST提交，根据指定clazz反序列化
	 * @param url
	 * @param params
	 * @param clazz
	 * @return
	 */
	<A> A postForResultObject(String url,Map params);
	
	/**
	 * POST提交，返回对象集合
	 * @param url
	 * @param params
	 * @return
	 */
	Object postForResultMap(String url,Map params);

	<A> A postForObject(String url, Object params,ParameterizedTypeReference<A> ptr);


}
