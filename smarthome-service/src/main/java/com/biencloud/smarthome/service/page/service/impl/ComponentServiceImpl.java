package com.biencloud.smarthome.service.page.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.page.dao.IComponentDao;
import com.biencloud.smarthome.service.page.model.Component;
import com.biencloud.smarthome.service.page.service.IComponentService;
/**
 * 类名称：CallRecordServiceImpl 
 * 类描述： 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 下午2:05:01
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class ComponentServiceImpl extends BaseService<Component,String> implements IComponentService{

	private IComponentDao componentDao;
	
	@Override
	public Component getComponentById(String id) {
		return getComponentDao().get(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveComponent(Component component) {
		getComponentDao().saveOrUpdate(component);
		
	}
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			Component component) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (component == null)
			return params;
		if (StringUtils.isNotBlank(component.getId()))
			appendCondition(jpql, "component.id = :id", "id", 
					component.getId(), params);
		
		if (StringUtils.isNotBlank(component.getGroups()))
			appendCondition(jpql, "component.groups = :groups", "groups", 
					component.getGroups(), params);

		if (StringUtils.isNotBlank(component.getType()))
			appendCondition(jpql, "component.type = :type",
					"type",component.getType(), params);
		
		if (StringUtils.isNotBlank(component.getPageId()))
			appendCondition(jpql, "component.pageId = :pageId",
					"pageId",component.getPageId(), params);

		return params;
	}

	@Override
	public List<Component> queryComponents(Component component) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, component);
		jpql.insert(0, "SELECT component FROM Component component");
		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(String id) {
		if(StringUtils.isBlank(id)){
			return;
		}
		String removeString = "DELETE FROM Component WHERE id=?1";
		super.removeByParams(removeString, id);
	}

	public IComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(IComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	@Override
	public void updateComponent(Component component) {
		super.save_update(component);
		
	}

	@Override
	public Paging<Component> queryComponentForPaging(Component component,
			int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, component);
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Component component");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "component");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(component)");
		//queryString = queryString + " order by c.createdTime desc ";
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

}
