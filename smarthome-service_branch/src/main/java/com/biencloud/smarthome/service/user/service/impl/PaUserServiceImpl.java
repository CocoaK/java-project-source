package com.biencloud.smarthome.service.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.PaUser;
import com.biencloud.smarthome.service.user.service.IPaUserService;

/**
 * 物业管理用户领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-12
 * @see BaseService
 * @see IPaUserService
 */
public class PaUserServiceImpl extends AbstractUserService<PaUser, String> implements
		IPaUserService {
	
	private static final String ENTITY_OBJECT_NAME = "PaUser";
	
	private IDao<HousingDistrictInfo, String> housingDistrictInfoDao;
	
	public IDao<HousingDistrictInfo, String> getHousingDistrictInfoDao() {
		return housingDistrictInfoDao;
	}

	public void setHousingDistrictInfoDao(
			IDao<HousingDistrictInfo, String> housingDistrictInfoDao) {
		this.housingDistrictInfoDao = housingDistrictInfoDao;
	}

	
	/** 
	 * userName：如果为空不作为查询条件，否则作为模糊查询条件
	 * idCard：如果为空不作为查询条件，否则作为模糊查询条件
	 * department：如果为空不作为查询条件，否则作为模糊查询条件
	 */
	@Override
	public Paging<PaUser> queryPaUsersForPaging(
			PaUser user, int pageNum, int pageSize) {
		return queryUsersForPaging(user, 
				ENTITY_OBJECT_NAME, pageNum, pageSize);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(PaUser user) {
		getDao().save(user);

		Login login = user.getLogin();
		login.setUserId(user.getUserId());
		login.setCreatedUser(user.getCreatedUser());
		login.setPassword(getInitPassword());
		login.setUserType(UserType.PA.getValue());
		login.setOnlineFlag(Constants.LOGIN_OFFLINE);
		if(StringUtils.isEmpty(login.getLoginAlias()))
			login.setLoginAlias(login.getLoginName());

		getDao().saveObject(login);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(PaUser user) {
		getDao().update(user);
		getDao().updateObject(user.getLogin());
	}

	@Override
	public PaUser get(String userId) {
		PaUser user = getDao().get(userId);
		if(user != null){
			user.setLogin(getLogin(userId, UserType.PA.getValue()));
			user.setDistrictName(getDistrictName(user.getDistrictId()));
			user.setRoleName(getRole(user.getRoleCode()).getRoleName());
			user.setCreatedUser(getUserNameByLoginName(user.getCreatedUser()));
			user.setUpdatedUser(getUserNameByLoginName(user.getUpdatedUser()));
		}
		
		return user;
	}
	
	@Override
	public List<PaUser> findUsersByLikeName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "%" + userName + "%");
		List<PaUser> users = findByNamedParams("from PaUser user where user.userName like :userName", params);

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
	public boolean existUsersByDistrictId(String districtId) {
		String jpql = "SELECT COUNT(user) FROM PaUser user WHERE user.districtId = ?1";		
		return (getDao().getTotalCount(jpql, districtId) > 0);
	}
	
	
	@Override
	protected void setProperties(List<PaUser> list) {
		if (CollectionUtils.isEmpty(list))
			return;

		for (PaUser user : list) {
			// 注入登录对象和小区名称
			user.setLogin(getLogin(user.getUserId(), UserType.PA.getValue()));
			user.setDistrictName(getDistrictName(user.getDistrictId()));
		}
	}
	
	@Override
	protected List<String> appendParams(PaUser user, StringBuilder jpql,
			Map<String, Object> params) {
		if (user == null || user.getLogin() == null)
			return new ArrayList<String>();

		Login login = user.getLogin();
		List<Role> roles = getPermissionsService().queryRoles(user.getRoleCode(), 
				login.getLoginName(), user.getLogin().getUserType(), UserType.PA.getValue(), false);
		List<String> roleCodes = extractRoleCodes(roles);
		if(CollectionUtils.isEmpty(roleCodes))
			return roleCodes;
		
		jpql.append(" WHERE user.roleCode in (");
		jpql.append(joinRoleCodeVars(params, roleCodes.toArray(new String[0])));
		jpql.append(")");
		
		appendCondition(jpql, "user.districtId = :districtId", "districtId",
				user.getDistrictId(), params);
		
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
	
	
	//获取小区名称
	private String getDistrictName(String districtId){	
		HousingDistrictInfo hdi = getHousingDistrictInfoDao().get(districtId);
		if(hdi == null)
			return null;
		return hdi.getName();
	}
}
