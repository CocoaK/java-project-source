package com.biencloud.smarthome.service.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 服务接口。
 * 
 * @author kouy
 * @since 1.0 2012-4-19
 * @throws RuntimeException
 *             如果操作执行失败
 */
public interface IService<T, Id extends Serializable> {

	/**
	 * 保存实体对象。
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void save(T entity);

	/**
	 * 
	 * 方法的描述: 保存或更新
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:54:27
	 * @param entity
	 * @return
	 */
	public T save_update(T entity);

	/**
	 * 更新实体对象。
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void update(T entity);

	/**
	 * 通过实体对象标识获取实体对象。
	 * 
	 * @param entityId
	 *            实体对象标识
	 * @return 实体对象
	 */
	public T get(Id entityId);

	/**
	 * 删除实体对象。
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void remove(T entity);

	/**
	 * 删除指定实体对象标识 （个数可变）的实体对象 。
	 * 
	 * @param entityIds
	 *            实体对象标识
	 */
	public void removeByIds(Id... entityIds);

	/**
	 * 删除实体对象集。
	 * 
	 * @param entities
	 *            实体对象集
	 */
	public void removeAll(Collection<T> entities);

	/**
	 * 返回容器是否包含指定实体对象。
	 * 
	 * @param entity
	 *            实体对象
	 * @return
	 */
	public boolean contains(T entity);

	/**
	 * 获取总记录数。
	 * 
	 * @param queryStringCount
	 *            查询总记录数字符串
	 * @return
	 */
	public long getTotalCount(String queryStringCount);

	/**
	 * 获取总记录数。
	 * 
	 * @param queryStringCount
	 *            查询总记录数字符串 (包含索引标识属性变量)
	 * @param values
	 *            可变属性值
	 * @return
	 */
	public long getTotalCount(String queryStringCount, Object... values);

	/**
	 * 获取总记录数。
	 * 
	 * @param queryStringCount
	 *            查询总记录数字符串 (包含属性变量名)
	 * @param params
	 *            属性变量名和属性值映射
	 * @return
	 */
	public long getTotalCountByNamedParams(String queryStringCount, Map<String, ?> params);
	/**
	 * 
	 * 方法的描述: 保存集合
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-16 上午8:57:59
	 * @param collection
	 */
	public void saveCollection(Collection<T> collection);
	/**
	 * 
	 * 方法的描述: 保存或更新集合
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-16 上午8:59:00
	 * @param collection
	 */
	public void saveOrUpdateCollection(Collection<T> collection);
}
