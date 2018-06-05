package com.biencloud.smarthome.service.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.SaUser;
import com.biencloud.smarthome.service.user.service.ISaUserService;

/**
 * 系统管理用户领域服务实现类。
 * 
 * @author kouy
 * @since 1.0 2012-5-12
 * @see BaseService
 * @see ISaUserService
 */
public class SaUserServiceImpl extends AbstractUserService<SaUser, String> implements
		ISaUserService {

	private static final String ENTITY_OBJECT_NAME = "SaUser";

	
	/** 
	 * userName：如果为空不作为查询条件，否则作为模糊查询条件
	 * idCard：如果为空不作为查询条件，否则作为模糊查询条件
	 * department：如果为空不作为查询条件，否则作为模糊查询条件
	 */
	@Override
	public Paging<SaUser> querySaUsersForPaging(
			SaUser user, int pageNum, int pageSize) {
		return queryUsersForPaging(user, 
				ENTITY_OBJECT_NAME, pageNum, pageSize);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(SaUser user) {
		getDao().save(user);

		Login login = user.getLogin();
		login.setUserId(user.getUserId());
		login.setCreatedUser(user.getCreatedUser());
		login.setPassword(getInitPassword());
		login.setUserType(UserType.SA.getValue());
		login.setOnlineFlag(Constants.LOGIN_OFFLINE);
		if(StringUtils.isEmpty(login.getLoginAlias()))
			login.setLoginAlias(login.getLoginName());

		getDao().saveObject(login);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(SaUser user) {
		getDao().update(user);
		getDao().updateObject(user.getLogin());
	}

	@Override
	public SaUser get(String userId) {
		SaUser user = getDao().get(userId);
		if(user != null){
			user.setLogin(getLogin(userId, UserType.SA.getValue()));
			user.setRoleName(getRole(user.getRoleCode()).getRoleName());
			user.setCreatedUser(getUserNameByLoginName(user.getCreatedUser()));
			user.setUpdatedUser(getUserNameByLoginName(user.getUpdatedUser()));
		}
		return user;
	}
	
	@Override
	public List<SaUser> findUsersByLikeName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "%" + userName + "%");
		List<SaUser> users = findByNamedParams("from SaUser user where user.userName like :userName", params);

		setProperties(users);
		return users;
	}
	
	@Override
	public String getUserNameById(String userId) {
		return getUserNameById(ENTITY_OBJECT_NAME, userId);
	}
	
	@Override
	public boolean existIdCard(String userId, String idCard) {
		return existUserIdCard(ENTITY_OBJECT_NAME, userId, idCard);
	}
	
	
	@Override
	protected void setProperties(List<SaUser> list){
		if(CollectionUtils.isEmpty(list))
			return;
		
		for (SaUser user : list) {
			//注入登录对象
			user.setLogin(getLogin(user.getUserId(), 
					UserType.SA.getValue()));
		}
	}
	
	@Override
	protected List<String> appendParams(SaUser user, 
			StringBuilder jpql, Map<String, Object> params) {
		if (user == null || user.getLogin() == null)
			return new ArrayList<String>();

		Login login = user.getLogin();
		List<Role> roles = getPermissionsService().queryRoles(user.getRoleCode(), 
				login.getLoginName(), UserType.SA.getValue(), UserType.SA.getValue(), false);
		List<String> roleCodes = extractRoleCodes(roles);
		if(CollectionUtils.isEmpty(roleCodes))
			return roleCodes;
		
		jpql.append(" WHERE user.roleCode in (");
		jpql.append(joinRoleCodeVars(params, roleCodes.toArray(new String[0])));
		jpql.append(")");
		
		if (StringUtils.isNotBlank(user.getUserName()))
			appendCondition(jpql, "user.userName LIKE :userName", "userName", "%"
					+ user.getUserName() + "%", params);

		if (StringUtils.isNotBlank(user.getIdCard()))
			appendCondition(jpql, "user.idCard LIKE :idCard", "idCard",
					"%" + user.getIdCard() + "%", params);

		if (StringUtils.isNotBlank(user.getDepartment()))
			appendCondition(jpql, "user.department LIKE :department",
					"department", "%" + user.getDepartment() + "%",
					params);

		return roleCodes;
	}
}
