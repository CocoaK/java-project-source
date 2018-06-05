package com.biencloud.smarthome.service.base.service;

import java.util.List;

import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;

public interface IBaseResService<T> {
	
	int deleteById(Integer id);

    int add(T record);

	Page<T> getForPage(Page<T> p,Object obj);	
    
    T getForOne(Integer id);

    int updateOnActiveById(T record);

    int updateById(T record);	
    
    /**
	 * 增加返回格式化结果
	 * @param record
	 * @return
	 */
	public ResultEntity<String> addForResultEntity(T record);
	
	public ResultEntity<String> addListForResultEntity(List<T> list);
	
	/**
	 * 删除返回格式化结果
	 * @param id
	 * @return
	 */
	public ResultEntity<String> deleteByIdForResultEntity(Integer id);
	
		
	/**
	 * 获取单个实体数据
	 * @param id
	 * @return
	 */
	public ResultEntity<T> getForOneResultEntity(Integer id);
	
	/**
	 * 更新返回格式化结果
	 * @param record
	 * @return
	 */
	public ResultEntity<String> updateForResultEntity(T record);
	
	/**
	 * 传递常用结果消息
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data);;
	/**
	 * 传递列表结果
	 * @param total
	 * @param timetamp
	 * @param info
	 * @return
	 */
	public <A> ResultEntity<ResultList<A>> proccessResultList(long total,long timetamp,A info);
	
	/**
	 * 获取分页查询结果
	 * @param p
	 * @param obj
	 * @return
	 */
	public ResultEntity<ResultList<List<T>>> getForResultList(Page<T> p,Object obj);
	
	/**
	 * 获取分页查询结果
	 * @param p
	 * @param obj
	 * @return
	 */
	public ResultEntity<ResultList<List<T>>> getForResultList(T record);


}
