package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.model.ResultList;
import com.quhwa.scalehouse.common.page.Page;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.service.IBaseService;

public abstract class BaseService<T> implements IBaseService<T> {

	public abstract BaseMapper<T> getBaseMapper();

	@Override
	public Page<T> getPageList(Page<T> p, Object obj) {
		return getBaseMapper().getPageList(p, obj);
	}
	
	@Transactional
	@Override
	public int deleteById(Integer id) {
		return getBaseMapper().delete(id);
	}

	@Override
	public T getForOne(Integer id) {
		return getBaseMapper().getForOne(id);
	}

	@Transactional
	@Override
	public int updateOnActiveById(T record) {
		return getBaseMapper().updateOnActive(record);
	}

	@Transactional
	@Override
	public int updateById(T record) {
		return getBaseMapper().update(record);
	}

	@Override
	public int add(T record) {
		return getBaseMapper().insert(record);
	}

	/**
	 * 增加返回格式化结果
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ResultEntity<String> addForResultEntity(T record) {
		return proccessResultEntity(add(record) > 0 ? ResultEntity.SUCCESS
				: ResultEntity.FAILD, "", "");
	}
	
	/**
	 * 增加列表返回格式化结果
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ResultEntity<String> addListForResultEntity(List<T> list) {
		if(list!=null && list.size()>0){
			for(T t : list){
				add(t);
			}
		}
		return proccessResultEntity(ResultEntity.SUCCESS,"","");
	}

	/**
	 * 删除返回格式化结果
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResultEntity<String> deleteByIdForResultEntity(Integer id) {
		return proccessResultEntity(deleteById(id) > 0 ? ResultEntity.SUCCESS
				: ResultEntity.FAILD, "", "");
	}
	 
	
	/**
	 * 获取单个实体数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResultEntity<T> getForOneResultEntity(Integer id) {
		return proccessResultEntity(ResultEntity.SUCCESS, "", getForOne(id));
	}

	/**
	 * 更新返回格式化结果
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public ResultEntity<String> updateForResultEntity(T record) {
		return proccessResultEntity(
				updateOnActiveById(record) > 0 ? ResultEntity.SUCCESS
						: ResultEntity.FAILD, "", "");
	}

	/**
	 * 传递常用结果消息
	 * 
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	@Override
	public <A> ResultEntity<A> proccessResultEntity(int code, String message,
			A data) {
		return new ResultEntity<A>(code, message, data);
	}

	/**
	 * 获取分页查询结果
	 * @param p
	 * @param obj
	 * @return
	 */
	@Override
	public ResultEntity<ResultList<List<T>>> getForResultList(Page<T> p,Object obj){
		getBaseMapper().getPageList(p, obj);
		return proccessResultList(p.getTotal(),System.currentTimeMillis(),p.getResult());
	}

	@Override
	public <A> ResultEntity<ResultList<A>> proccessResultList(long total,
			long timetamp, A info) {
		return proccessResultEntity(ResultEntity.SUCCESS, "",
				new ResultList<A>(total, timetamp, info));
	}
}
