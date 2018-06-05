package com.biencloud.smarthome.service.menu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.menu.service.IMenuService;

/**
 * 菜单模块领域服务实现类。
 * @author kouy
 * @since 1.0 2012-4-19
 * @see BaseService
 * @see IMenuService
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class MenuServiceImpl extends BaseService<Menu, String> implements IMenuService {	
	
	@Override
	public Paging<Menu> queryMenusForPaging(String menuName, 
			boolean hasParent, String status, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(jpql, menuName, hasParent,
				status);
		
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM Menu menu");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "menu");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(menu)");
		
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	
	@Override
	public Menu get(String entityId) {
		Menu menu = getDao().get(entityId);
		if(menu != null){
			menu.setUpdatedUser(getLoginService().getUserNameByLoginName(
					menu.getUpdatedUser()));
		}
		return menu;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateMenuStatus(String menuCode, 
			String status, String updatedUser) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE Menu menu SET menu.status = ?1, menu.updatedUser= ?2, " +
				"menu.updatedTime= ?3 WHERE menu.menuCode = ?4");
		Object[] values = {status, updatedUser, new Date(), menuCode};
		update(jpql.toString(), values);
	}
	
	@Override
	public List<Menu> queryMenusByParentCode(String parentCode) {
		return find("SELECT menu FROM Menu menu WHERE menu.parentCode = ?1", parentCode);
	}
	
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,
			String menuName, boolean hasParent, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(menuName)) {
			jpql.append(" WHERE menu.menuName LIKE :menuName");
			params.put("menuName", "%" + menuName + "%");
		}

		if (StringUtils.isNotBlank(status)) {
			String statusCondition = " menu.status = :status";
			if (StringUtils.isEmpty(jpql)) {
				jpql.append(" WHERE").append(statusCondition);
			} else {
				jpql.append(" AND").append(statusCondition);
			}
			params.put("status", status);
		}

		if (hasParent) {
			jpql.append(StringUtils.isEmpty(jpql) ? " WHERE" : " AND");
			jpql.append(" menu.parentCode is not null");
		}
		return params;
	}
	
	private ILoginService getLoginService() {
		return (ILoginService)getBean(Constants.BEAN_NAME_LOGIN_SERVICE);
	}
}
