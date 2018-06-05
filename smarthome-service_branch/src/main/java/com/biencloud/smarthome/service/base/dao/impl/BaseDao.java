package com.biencloud.smarthome.service.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.base.enums.LockType;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 数据访问对象基类，基于JPA，查询字符串使用JPQL。
 * 
 * @author kouy
 * @since 1.0 2012-4-10
 * @see IDao
 */
public class BaseDao<T, Id extends Serializable> extends JpaDaoSupport implements IDao<T, Id> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private Class<T> entityClass = null;

	private static final Map<LockType, LockModeType> LOCK_TYPES;
	static {
		LOCK_TYPES = new HashMap<LockType, LockModeType>();
		LOCK_TYPES.put(LockType.PESSIMISTIC_READ, LockModeType.PESSIMISTIC_READ);
		LOCK_TYPES.put(LockType.PESSIMISTIC_WRITE, LockModeType.PESSIMISTIC_WRITE);
	}

	@SuppressWarnings("unchecked")
	public BaseDao() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获取实体类。
	 * 
	 * @return
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 设置查询对象基于索引标识的属性变量值。
	 * 
	 * @param query
	 *            查询对象
	 * @param values
	 *            可变属性值
	 */
	protected void setIndexedParams(Query query, Object... values) {
		if (ArrayUtils.isNotEmpty(values)) {
			for (int index = 0, size = values.length; index < size; index++)
				query.setParameter(index + 1, values[index]);
		}
	}

	/**
	 * 设置查询对象基于变量名标识的属性变量值。
	 * 
	 * @param query
	 *            查询对象
	 * @param params
	 *            属性变量名和属性值映射
	 */
	protected void setNamedParams(Query query, Map<String, ?> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
				String paramName = iterator.next();
				Object paramValue = params.get(paramName);
				if (paramValue instanceof String) {
					paramValue = escapeChar((String) paramValue);
				}
				query.setParameter(paramName, paramValue);
			}
		}
	}

	protected String escapeChar(String resource) {
		StringBuilder builder = new StringBuilder();
		int position = 0;
		char currentChar;
		boolean isLikeQuery=false;
		while (position < resource.length()) {
			currentChar = resource.charAt(position);
			if (currentChar == '%') {
				if (position == 0){
					builder.append(currentChar);
					isLikeQuery=true;
				}
				else if (position == resource.length() - 1)
					builder.append(currentChar);
				else
					builder.append("\\" + currentChar);
			} else if (currentChar == '_'&&isLikeQuery) {
				builder.append("\\" + currentChar);
			} else {
				builder.append(currentChar);
			}
			position++;
		}
		return builder.toString();
	}

	@Override
	public void save(T entity) {
		getJpaTemplate().persist(entity);
	}

	@Override
	public void saveAll(Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities))
			return;

		for (T entity : entities)
			save(entity);
	}

	@Override
	public T saveOrUpdate(T entity) {
		return getJpaTemplate().merge(entity);
	}

	@Override
	public void saveObject(Object object) {
		getJpaTemplate().persist(object);
	}

	@Override
	public void saveCollection(Collection<Object> collection) {
		if (CollectionUtils.isEmpty(collection))
			return;

		for (Object object : collection) {
			saveObject(object);
		}
	}

	@Override
	public void update(T entity) {
		getJpaTemplate().merge(entity);
	}

	@Override
	public void updateObject(Object object) {
		getJpaTemplate().merge(object);
	}

	@Override
	public void updateCollection(Collection<Object> collection) {
		if (CollectionUtils.isEmpty(collection))
			return;

		for (Object object : collection) {
			updateObject(object);
		}
	}

	@Override
	public void update(final String queryString, final Object... values) {
		getJpaTemplate().execute(new JpaCallback<Integer>() {
			@Override
			public Integer doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				setIndexedParams(query, values);
				int result = query.executeUpdate();
				if (result < 1)
					throw new PersistenceException("操作执行失败(未找到要更新的记录)，操作字符串：" + queryString + "，参数值："
							+ (ArrayUtils.isEmpty(values) ? "[]" : Arrays.toString(values)));
				return result;
			}
		});
	}

	@Override
	public void updateByNamedParams(final String queryString, final Map<String, ?> params) {
		getJpaTemplate().execute(new JpaCallback<Integer>() {
			@Override
			public Integer doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				setNamedParams(query, params);
				int result = query.executeUpdate();
				if (result < 1)
					throw new PersistenceException("操作执行失败(未找到要更新的记录)，操作字符串：" + queryString + "，参数键值：" + params);
				return result;
			}
		});
	}

	@Override
	public T get(Id entityId) {
		return getJpaTemplate().find(getEntityClass(), entityId);
	}

	@Override
	public void remove(T entity) {
		T t = getJpaTemplate().merge(entity);
		getJpaTemplate().remove(t);
	}

	@Override
	public void removeObject(Object obj) {
		obj = getJpaTemplate().merge(obj);
		getJpaTemplate().remove(obj);
	}

	@Override
	public void removeByIds(Id... entityIds) {
		for (Id entityId : entityIds)
			getJpaTemplate().remove(get(entityId));
	}

	@Override
	public void removeAll(Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities))
			return;

		for (T entity : entities)
			remove(entity);
	}

	@Override
	public void removeObjects(Collection<Object> objects) {
		if (CollectionUtils.isEmpty(objects))
			return;

		for (Object object : objects)
			removeObject(object);
	}

	@Override
	public void removeByParams(final String removeString, final Object... values) {
		getJpaTemplate().execute(new JpaCallback<Integer>() {
			@Override
			public Integer doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(removeString);
				setIndexedParams(query, values);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void removeByNamedParams(final String removeString, final Map<String, ?> params) {
		getJpaTemplate().execute(new JpaCallback<Integer>() {
			@Override
			public Integer doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(removeString);
				setNamedParams(query, params);
				return query.executeUpdate();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String queryString) {
		return getJpaTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(final LockType lockType, final String queryString) {
		List<T> result = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String queryString, Object... values) {
		return getJpaTemplate().find(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(final LockType lockType, final String queryString, final Object... values) {
		List<T> result = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setIndexedParams(query, values);
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedParams(String queryString, Map<String, ?> params) {
		return getJpaTemplate().findByNamedParams(queryString, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedParams(final LockType lockType, final String queryString, final Map<String, ?> params) {
		List<T> result = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setNamedParams(query, params);
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIds(String queryString) {
		return getJpaTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIds(final LockType lockType, final String queryString) {
		List<Id> result = getJpaTemplate().execute(new JpaCallback<List<Id>>() {
			@Override
			public List<Id> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIds(String queryString, Object... values) {
		return getJpaTemplate().find(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIds(final LockType lockType, final String queryString, final Object... values) {
		List<Id> result = getJpaTemplate().execute(new JpaCallback<List<Id>>() {
			@Override
			public List<Id> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setIndexedParams(query, values);
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIdsByNamedParams(String queryString, Map<String, ?> params) {
		return getJpaTemplate().findByNamedParams(queryString, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Id> findIdsByNamedParams(final LockType lockType, final String queryString, final Map<String, ?> params) {
		List<Id> result = getJpaTemplate().execute(new JpaCallback<List<Id>>() {
			@Override
			public List<Id> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setNamedParams(query, params);
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List excuteSql(final String sql) {
		return getJpaTemplate().executeFind(new JpaCallback<List>() {
			@Override
			public List doInJpa(EntityManager em) throws PersistenceException {
				List result = em.createNativeQuery(sql).getResultList();
				return result;
			}
		});
	}

	@Override
	public long getTotalCount(String queryStringCount) {
		return (Long) getJpaTemplate().find(queryStringCount).get(0);
	}

	@Override
	public long getTotalCount(String queryStringCount, Object... values) {
		return (Long) getJpaTemplate().find(queryStringCount, values).get(0);
	}

	@Override
	public long getTotalCountByNamedParams(String queryStringCount, Map<String, ?> params) {
		return (Long) getJpaTemplate().findByNamedParams(queryStringCount, params).get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Paging<T> queryForPaging(int pageNum, final int pageSize, final String queryString, String queryStringCount) {
		Paging<T> paging = new Paging<T>();
		paging.setPageSize(pageSize);
		paging.setTotalCount(getTotalCount(queryStringCount));

		final int first = getFirst(pageNum, pageSize, paging.getTotalPages());

		List<T> results = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setFirstResult(first);
				query.setMaxResults(pageSize);
				return query.getResultList();
			}

		});

		paging.setResults(results);
		return paging;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Paging<T> queryForPaging(int pageNum, final int pageSize, final String queryString, String queryStringCount,
			final Object... values) {
		Paging<T> paging = new Paging<T>();
		paging.setPageSize(pageSize);
		if (values == null) {
			paging.setTotalCount(getTotalCount(queryStringCount));
		} else {
			paging.setTotalCount(getTotalCount(queryStringCount, values));
		}

		final int first = getFirst(pageNum, pageSize, paging.getTotalPages());

		List<T> results = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				setIndexedParams(query, values);
				query.setFirstResult(first);
				query.setMaxResults(pageSize);
				return query.getResultList();
			}

		});

		paging.setResults(results);
		return paging;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Paging<T> queryByNamedParamsForPaging(int pageNum, final int pageSize, final String queryString, String queryStringCount,
			final Map<String, ?> params) {
		Paging<T> paging = new Paging<T>();
		paging.setPageSize(pageSize);
		paging.setTotalCount(getTotalCountByNamedParams(queryStringCount, params));

		final int first = getFirst(pageNum, pageSize, paging.getTotalPages());

		List<T> results = getJpaTemplate().execute(new JpaCallback<List<T>>() {
			@Override
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				setNamedParams(query, params);
				query.setFirstResult(first);
				query.setMaxResults(pageSize);
				return query.getResultList();
			}

		});

		paging.setResults(results);
		return paging;
	}

	@Override
	public boolean contains(T entity) {
		return getJpaTemplate().contains(entity);
	}

	@Override
	public void saveOrUpdateCollection(Collection<T> collection) {
		if (CollectionUtils.isEmpty(collection))
			return;

		for (T object : collection) {
			saveOrUpdate(object);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjects(String queryString) {
		return getJpaTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjects(final LockType lockType, final String queryString) {
		List<Object> result = getJpaTemplate().execute(new JpaCallback<List<Object>>() {
			@Override
			public List<Object> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjects(String queryString, Object... values) {
		return getJpaTemplate().find(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjects(final LockType lockType, final String queryString, final Object... values) {
		List<Object> result = getJpaTemplate().execute(new JpaCallback<List<Object>>() {
			@Override
			public List<Object> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setIndexedParams(query, values);
				return query.getResultList();
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjectsByNamedParams(String queryString, Map<String, ?> params) {
		return getJpaTemplate().findByNamedParams(queryString, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjectsByNamedParams(final LockType lockType, final String queryString, final Map<String, ?> params) {
		List<Object> result = getJpaTemplate().execute(new JpaCallback<List<Object>>() {
			@Override
			public List<Object> doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setLockMode(LOCK_TYPES.get(lockType));
				setNamedParams(query, params);
				return query.getResultList();
			}
		});
		return result;
	}

	// 获取起始记录位置（从0开始）
	private int getFirst(int pageNum, int pageSize, int totalPages) {
		if (pageNum < 1 || pageSize < 1)
			throw new IllegalArgumentException("pageNum(" + pageNum + ") or pageSize(" + pageSize + ") are illegal!");

		if (totalPages == 0)
			pageNum = 1;
		else if (pageNum > totalPages)
			pageNum = totalPages;

		return (pageNum - 1) * pageSize;
	}
}
