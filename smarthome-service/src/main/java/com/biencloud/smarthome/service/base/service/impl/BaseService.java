package com.biencloud.smarthome.service.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.base.enums.LockType;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 服务基类。
 * 
 * @author kouy
 * @since 1.0 2012-4-10
 * @see IService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BaseService<T, Id extends Serializable> extends ApplicationObjectSupport implements IService<T, Id> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String REPLACE_CHARS = "{0}";

	private IDao<T, Id> dao;

	public IDao<T, Id> getDao() {
		return dao;
	}

	public void setDao(IDao<T, Id> dao) {
		this.dao = dao;
	}

	/**
	 * 通过Bean名称获取指定Bean对象。
	 * 
	 * @param beanName
	 * @return
	 */
	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(T entity) {
		getDao().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T save_update(T entity) {

		return getDao().saveOrUpdate(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T entity) {
		getDao().update(entity);
	}

	@Override
	public T get(Id entityId) {
		return getDao().get(entityId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(T entity) {
		getDao().remove(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeByIds(Id... entityIds) {
		getDao().removeByIds(entityIds);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll(Collection<T> entities) {
		getDao().removeAll(entities);
	}

	@Override
	public boolean contains(T entity) {
		return getDao().contains(entity);
	}

	/**
	 * 更新实体对象。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 */
	protected void update(String queryString, Object... values) {
		getDao().update(queryString, values);
	}

	/**
	 * 更新实体对象。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 */
	protected void updateByNamedParams(String queryString, Map<String, ?> params) {
		getDao().updateByNamedParams(queryString, params);
	}

	/**
	 * 删除实体对象。
	 * @param removeString 删除字符串(包含索引标识属性变量)
	 * @param values 可变属性值   
	 */
	protected void removeByParams(String removeString, Object... values) {
		getDao().removeByParams(removeString, values);
	}

	/**
	 * 删除实体对象。
	 * @param removeString 删除字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射   
	 */
	protected void removeByNamedParams(String removeString, Map<String, ?> params) {
		getDao().removeByNamedParams(removeString, params);
	}
	
	/**
	 * 通过查询字符串获取实体对象集。 
	 * @param queryString 查询字符串
	 * @return
	 */
	protected List<T> find(String queryString) {
		return getDao().find(queryString);
	}

	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串	 
	 * @return
	 */
	protected List<T> find(LockType lockType, String queryString){
		return getDao().find(lockType, queryString);
	}
	
	/**
	 * 通过查询字符串获取实体对象集。
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected List<T> find(String queryString, Object... values) {
		return getDao().find(queryString, values);
	}

	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected List<T> find(LockType lockType, 
			String queryString, Object... values){
		return getDao().find(lockType, queryString, values);
	}
	
	/**
	 * 通过查询字符串获取实体对象集。 
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected List<T> findByNamedParams(String queryString, Map<String, ?> params) {
		return getDao().findByNamedParams(queryString, params);
	}

	/**
	 * 通过查询字符串获取实体对象集，并根据锁类型锁定对象。 
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected List<T> findByNamedParams(LockType lockType, 
			String queryString, Map<String, ?> params) {
		return getDao().findByNamedParams(lockType, queryString, params);
	}
	
	/**
	 * 通过查询字符串获取实体主键集。 
	 * @param queryString 查询字符串
	 * @return
	 */
	protected List<Id> findIds(String queryString) {
		return getDao().findIds(queryString);
	}

	/**
	 * 通过查询字符串获取实体主键集，并根据锁类型锁定对象。 
	 * @param lockType 锁类型
	 * @param queryString 查询字符串
	 * @return
	 */
	protected List<Id> findIds(LockType lockType, String queryString) {
		return getDao().findIds(lockType, queryString);
	}
	
	/**
	 * 通过查询字符串获取实体主键集。 
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected List<Id> findIds(String queryString, Object... values) {
		return getDao().findIds(queryString, values);
	}

	/**
	 * 通过查询字符串获取实体主键集，并根据锁类型锁定对象。 
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected List<Id> findIds(LockType lockType, 
			String queryString, Object... values) {
		return getDao().findIds(lockType, queryString, values);
	}
	
	/**
	 * 通过查询字符串获取实体主键集。
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected List<Id> findIdsByNamedParams(String queryString, Map<String, ?> params) {
		return getDao().findIdsByNamedParams(queryString, params);
	}

	/**
	 * 通过查询字符串获取实体主键集。
	 * @param lockType 锁类型
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected List<Id> findIdsByNamedParams(LockType lockType, 
			String queryString, Map<String, ?> params) {
		return getDao().findIdsByNamedParams(lockType, queryString, params);
	}
	
	/**
	 * 通过查询字符串获取分页数据。
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串
	 * @param queryStringCount 查询总记录数字符串
	 * @return
	 */
	protected Paging<T> queryForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount) {
		return resetPagingNum(getDao().queryForPaging(pageNum, 
				pageSize, queryString, queryStringCount), pageNum);
	}

	/**
	 * 通过查询字符串获取分页数据(按默认每页记录数)。
	 * @param pageNum 页码
	 * @param queryString 查询数据字符串
	 * @param queryStringCount 查询总记录数字符串
	 * @return
	 */
	protected Paging<T> queryForPaging(int pageNum, 
			String queryString, String queryStringCount) {
		return resetPagingNum(queryForPaging(pageNum, Constants.DEFAULT_PAGE_SIZE, 
				queryString, queryStringCount), pageNum);
	}

	/**
	 * 通过查询字符串获取分页数据。 
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串(包含索引标识属性变量)
	 * @param queryStringCount 查询总记录数字符串 (包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected Paging<T> queryForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount, Object... values) {
		return resetPagingNum(getDao().queryForPaging(pageNum, 
				pageSize, queryString, queryStringCount, values), pageNum);
	}

	/**
	 * 通过查询字符串获取分页数据(按默认每页记录数)。
	 * @param pageNum 页码
	 * @param queryString 查询数据字符串(包含索引标识属性变量)
	 * @param queryStringCount 查询总记录数字符串 (包含索引标识属性变量)
	 * @param values 可变属性值
	 * @return
	 */
	protected Paging<T> queryForPaging(int pageNum, String queryString, 
			String queryStringCount, Object... values) {
		return resetPagingNum(queryForPaging(pageNum, Constants.DEFAULT_PAGE_SIZE, 
				queryString, queryStringCount, values), pageNum);
	}

	/**
	 * 通过查询字符串获取分页数据。
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param queryString 查询数据字符串(包含属性变量名)
	 * @param queryStringCount 查询总记录数字符串 (包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected Paging<T> queryByNamedParamsForPaging(int pageNum, int pageSize, 
			String queryString, String queryStringCount, Map<String, ?> params) {
		return resetPagingNum(getDao().queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params), pageNum);
	}

	/**
	 * 通过查询字符串获取分页数据(按默认每页记录数)。
	 * @param pageNum 页码
	 * @param queryString 查询字符串(包含属性变量名)
	 * @param queryStringCount 查询总记录数字符串 (包含属性变量名)
	 * @param params 属性变量名和属性值映射
	 * @return
	 */
	protected Paging<T> queryByNamedParamsForPaging(int pageNum, 
			String queryString, String queryStringCount, Map<String, ?> params) {
		return resetPagingNum(queryByNamedParamsForPaging(pageNum, Constants.DEFAULT_PAGE_SIZE, 
				queryString, queryStringCount, params), pageNum);
	}

	/**
	 * 追加查询条件并设置属性变量名值对。 
	 * @param jpql JPA查询语言
	 * @param condition 查询条件，如gc.cardNo LIKE :cardNo
	 * @param key 属性变量名，如cardNo
	 * @param value 属性变量值
	 * @param params 属性变量名值对
	 */
	protected void appendCondition(StringBuilder jpql, String condition, String key, Object value, Map<String, Object> params) {
		if (!isEmpty(value)) {
			if (StringUtils.contains(jpql, " WHERE ")) {
				jpql.append(" AND ").append(condition);
			} else {
				jpql.append(" WHERE ").append(condition);
			}
			params.put(key, value);
		}
	}

	/**
	 * 验证指定对象是否为空，满足下面条件返回True，否则返回False。 1、字符串且为空； 2、非字符串为Null；
	 * 
	 * @param value
	 *            指定对象
	 * @return
	 */
	protected boolean isEmpty(Object value) {
		if (value instanceof String) {
			if (StringUtils.isEmpty((String) value))
				return true;
			else
				return false;
		}

		if (value == null)
			return true;

		return false;
	}


	// 设置页码和每页大小
	private Paging<T> resetPagingNum(Paging<T> paging, int pageNum) {
		int totalPages = paging.getTotalPages();
		if(totalPages == 0)
			pageNum = 1;
		else if(pageNum > totalPages)
			pageNum = totalPages;
		paging.setPageNum(pageNum);		
		return paging;
	}

	
	/**
	 * 
	 * 方法的描述: 通过实体名称和实体id名称进行分页
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午5:58:57
	 * @param pageNum
	 * @param pageSize
	 * @param entityName
	 *            实现名称
	 * @param entityIdName
	 *            实体id名称
	 * @return
	 */
	public Paging<T> queryForPagingByEntityNameAndEntityIdName(int pageNum, int pageSize, String entityName, String entityIdName,
			String condition, String orderString) {
		String queryString = " FROM " + entityName;
		if (condition != null && !"".equals(condition.trim())) {
			if (condition.contains("where")) {
				queryString += " " + condition;
			} else {
				queryString += " where " + condition;
			}
		}
		if (orderString != null && !"".equals(orderString.trim())) {

			queryString += " " + orderString;

		}
		String queryStringCount = "SELECT COUNT(" + entityIdName + ") " + queryString;
		return queryForPaging(pageNum, pageSize, queryString, queryStringCount);
	}
	/**
	 * 
	 * 方法的描述: 按条件键值对查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-13 上午8:56:00
	 * @param pageNum
	 * @param pageSize
	 * @param entityName
	 * @param entityIdName
	 * @param condition
	 * @param values
	 * @param orderString
	 * @return
	 */
	public Paging<T> queryForPagingByEntityNameAndEntityIdName(int pageNum, int pageSize, String entityName, String entityIdName,
			String condition,String orderString, final Object... values) {
		String queryString = " FROM " + entityName;
		if (condition != null && !"".equals(condition.trim())) {
			if (condition.contains("where")) {
				queryString += " " + condition;
			} else {
				queryString += " where " + condition;
			}
		}
		if (orderString != null && !"".equals(orderString.trim())) {

			queryString += " " + orderString;

		}
		String queryStringCount = "SELECT COUNT(" + entityIdName + ") " + queryString;
		//return queryForPaging(pageNum, pageSize, queryString, queryStringCount);
		return this.queryForPaging(pageNum, pageSize, queryString, queryStringCount, values);
	}

	/**
	 * 
	 * 方法的描述: 通过实体名称进行分页，前提是该实体主键名称默认为id
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午5:59:52
	 * @param pageNum
	 * @param pageSize
	 * @param entityName实体名称
	 * @return
	 */
	public Paging<T> queryForPagingByEntityName(int pageNum, int pageSize, String entityName) {

		return queryForPagingByEntityNameAndEntityIdName(pageNum, pageSize, entityName, "id", null, null);
	}

	/**
	 * 
	 * 方法的描述: 根据实体名和条件查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:48:50
	 * @param pageNum
	 * @param pageSize
	 * @param entityName
	 * @param condition
	 * @return
	 */
	public Paging<T> queryForPagingByEntityNameAndCondition(int pageNum, int pageSize, String entityName, String condition,
			String orderString) {

		return queryForPagingByEntityNameAndEntityIdName(pageNum, pageSize, entityName, "id", condition, orderString);
	}
	/**
	 * 
	 * 方法的描述: 根据实体名和条件键值对查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-13 上午9:00:50
	 * @param pageNum
	 * @param pageSize
	 * @param entityName
	 * @param condition
	 * @param orderString
	 * @param values
	 * @return
	 */
	public Paging<T> queryForPagingByEntityNameAndCondition(int pageNum, int pageSize, String entityName, String condition,
			String orderString,final Object... values) {

		return queryForPagingByEntityNameAndEntityIdName(pageNum, pageSize, entityName, "id", condition, orderString,values);
	}
	@Override
	public long getTotalCount(String queryStringCount) {
		return getDao().getTotalCount(queryStringCount);
	}

	@Override
	public long getTotalCount(String queryStringCount, Object... values) {
		return getDao().getTotalCount(queryStringCount, values);
	}

	@Override
	public long getTotalCountByNamedParams(String queryStringCount, Map<String, ?> params) {
		return getDao().getTotalCountByNamedParams(queryStringCount, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveCollection(Collection<T> collection) {
		getDao().saveOrUpdateCollection(collection);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateCollection(Collection<T> collection) {
		getDao().saveOrUpdateCollection(collection);
		
	}

	/**
	 * 判断是否不存在实体, 不存在则返回true
	 * 
	 * @return
	 */
	public boolean isNotExist(String queryString, Object... values) {
		boolean notExist = false;
		List<T> entities = find(queryString, values);
		if (entities.isEmpty()) {
			notExist = true;
		}
		return notExist;
	}
}
