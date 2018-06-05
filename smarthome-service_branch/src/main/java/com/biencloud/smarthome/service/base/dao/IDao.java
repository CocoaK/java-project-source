package com.biencloud.smarthome.service.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.service.base.enums.LockType;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 数据访问对象通用接口。
 * @author kouy
 * @since 1.0 2012-4-18
 * @throws RuntimeException 如果操作执行失败
 */
public interface IDao<T, Id extends Serializable> {

	/**
	 * 保存实体对象。
	 * @param entity 实体对象 
	 */
	public void save(T entity);
	
	/**
	 * 保存实体对象集。
	 * @param entities 实体对象集 
	 */
	public void saveAll(Collection<T> entities);
	
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:53:54
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity);
	
	/**
	 * 保存实体对象。
	 * @param object 待保存实体对象 
	 */
	public void saveObject(Object object);
	
	/**
	 * 保存集合中的实体对象。
	 * @param object 待保存对象 
	 */
	public void saveCollection(Collection<Object> collection);
	
	/**
	 * 
	 * 方法的描述: 保存或更新集合
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-16 上午9:00:54
	 * @param collection
	 */
	public void saveOrUpdateCollection(Collection<T> collection);
	
	/**
	 * 更新实体对象。
	 * @param entity 实体对象   
	 */
	public void update(T entity);
	
	/**
	 * 更新实体对象。
	 * @param object 待更新对象 
	 */
	public void updateObject(Object object);
	
	/**
	 * 更新集合中的实体对象。
	 * @param object 待保存对象 
	 */
	public void updateCollection(Collection<Object> collection);
	
	/**
	 * 更新实体对象。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值   
	 */
	public void update(String queryString, Object... values);
	
	/**
	 * 更新实体对象。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射   
	 */
	public void updateByNamedParams(String queryString, 
			Map<String, ?> params);
	
	/**
	 * 通过实体对象标识获取实体对象。
	 * @param entityId 实体对象标识
	 * @return 实体对象
	 */
	public T get(Id entityId);
	
	/**
	 * 删除实体对象。
	 * @param entity 实体对象  
	 */
	public void remove(T entity);
	
	/**
	 * 删除对象。
	 * @param obj 待删除对象  
	 */
	public void removeObject(Object obj);
	
	/**
	 * 删除指定实体对象标识 （个数可变）的实体对象 。
	 * @param entityIds 实体对象标识   
	 */
	public void removeByIds(Id... entityIds);
	
	/**
	 * 删除实体对象集。
	 * @param entities 实体对象集  
	 */
	public void removeAll(Collection<T> entities);
	
	/**
	 * 删除对象集。
	 * @param objects 待删除对象集  
	 */
	public void removeObjects(Collection<Object> objects);
	
	/**
	 * 删除实体对象。
	 * @param removeString 删除字符串(包含索引标识属性变量)
	 * @param values 可变属性值   
	 */
	public void removeByParams(String removeString, Object... values);
	
	/**
	 * 删除实体对象。
	 * @param removeString 删除字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射   
	 */
	public void removeByNamedParams(String removeString, 
			Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取实体对象集。
	 * @param queryString 查询字符串
	 * @return
	 */
	public List<T> find(String queryString);
	
	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串	 
	 * @return
	 */
	public List<T> find(LockType lockType, String queryString);
	
	/**
	 * 通过查询字符串获取实体对象集。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<T> find(String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<T> find(LockType lockType, 
			String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取实体对象集。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<T> findByNamedParams(String queryString, 
			Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<T> findByNamedParams(LockType lockType, 
			String queryString, Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取实体主键集。
	 * @param queryString 查询字符串
	 * @return
	 */
	public List<Id> findIds(String queryString);
	
	/**
	 * 通过查询字符串获取实体主键集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串
	 * @return
	 */
	public List<Id> findIds(LockType lockType, String queryString);
	
	/**
	 * 通过查询字符串获取实体主键集。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<Id> findIds(String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取实体主键集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<Id> findIds(LockType lockType, 
			String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取实体主键集。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<Id> findIdsByNamedParams(String queryString, 
			Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取实体主键集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<Id> findIdsByNamedParams(LockType lockType, 
			String queryString, Map<String, ?> params);
	
	/**
	 * 获取总记录数。
	 * @param queryStringCount 查询总记录数字符串	 
	 * @return
	 */
	public long getTotalCount(String queryStringCount);
	
	/**
	 * 获取总记录数。
	 * @param queryStringCount 查询总记录数字符串	(包含索引标识属性变量) 
	 * @param values 可变属性值
	 * @return
	 */
	public long getTotalCount(String queryStringCount, Object... values);
	
	/**
	 * 获取总记录数。
	 * @param queryStringCount 查询总记录数字符串	(包含属性变量名) 
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public long getTotalCountByNamedParams(String queryStringCount, 
			Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取分页数据。	 
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串
	 * @param queryStringCount 查询总记录数字符串	 
	 * @return
	 */
	public Paging<T> queryForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount);
	
	
	/**
	 * 通过查询字符串获取分页数据。	 
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串(包含索引标识属性变量)
	 * @param queryStringCount 查询总记录数字符串(包含索引标识属性变量)	 
	 * @param values 可变属性值
	 * @return
	 */
	public Paging<T> queryForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount, Object... values);
	
	/**
	 * 通过查询字符串获取分页数据。	 
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串(包含属性变量名)
	 * @param queryStringCount 查询总记录数字符串	(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public Paging<T> queryByNamedParamsForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount, Map<String, ?> params);
	
	/**
	 * 返回容器是否包含指定实体对象。
	 * @param entity 实体对象
	 * @return
	 */
	public boolean contains(T entity);
	/**
	 * 执行原生sql 。
	 * @param entity 实体对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List excuteSql(String sql);
	
	/**
	 * 通过查询字符串获取对象集。
	 * @param queryString 查询字符串
	 * @return
	 */
	public List<Object> findObjects(String queryString);
	
	/**
	 * 通过查询字符串获取对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串	 
	 * @return
	 */
	public List<Object> findObjects(LockType lockType, String queryString);
	
	/**
	 * 通过查询字符串获取对象集。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<Object> findObjects(String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	public List<Object> findObjects(LockType lockType, 
			String queryString, Object... values);
	
	/**
	 * 通过查询字符串获取对象集。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<Object> findObjectsByNamedParams(String queryString, 
			Map<String, ?> params);
	
	/**
	 * 通过查询字符串获取对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	public List<Object> findObjectsByNamedParams(LockType lockType, 
			String queryString, Map<String, ?> params);
}
