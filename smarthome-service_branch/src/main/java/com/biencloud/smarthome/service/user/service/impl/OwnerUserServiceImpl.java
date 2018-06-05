package com.biencloud.smarthome.service.user.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;

/**
 * 住户用户管理领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-18
 * @see BaseService
 * @see IOwnerUserService
 */
public class OwnerUserServiceImpl extends AbstractUserService<OwnerUser, String> implements
		IOwnerUserService {

	private static final String ENTITY_OBJECT_NAME = "OwnerUser";
	
	private ICellHouseholdInfoService cellHouseholdInfoService;

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	
	/** 
	 * districtId：必须作为查询条件
	 * userName：如果为空不作为查询条件，否则作为模糊查询条件
	 * idCard：如果为空不作为查询条件，否则作为模糊查询条件
	 * createdTime：如果为空不作为查询条件，否则查询条件
	 */
	@Override
	public Paging<OwnerUser> queryOwnerUsersForPaging(
			OwnerUser user, int pageNum, int pageSize) {	
		return queryUsersForPaging(user, 
				ENTITY_OBJECT_NAME, pageNum, pageSize);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(OwnerUser user) {
		saveUser(user);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addUsers(List<OwnerUser> users) {
		for (OwnerUser user : users) {
			saveUser(user);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(OwnerUser user) {
		OwnerUser srcUser = getDao().get(user.getUserId());				
		//取消之前的房间绑定
		updateHouse(srcUser.getHouseId(), null, 
				null, Constants.HOUSE_STATUS_UNCHECK);
				
		getDao().update(user);
		
		getDao().updateObject(user.getLogin());
		
		//绑定新房间
		updateHouse(user.getHouseId(), user.getUserId(), 
				new Date(), Constants.HOUSE_STATUS_CHECK);
	}

	@Override
	public OwnerUser get(String userId) {
		OwnerUser user = getDao().get(userId);
		if(user != null){
			user.setLogin(getLogin(userId, UserType.OWNER.getValue()));
			user.setCreatedUser(getUserNameByLoginName(user.getCreatedUser()));
			user.setUpdatedUser(getUserNameByLoginName(user.getUpdatedUser()));
			List<OwnerUser> list = new ArrayList<OwnerUser>();
			list.add(user);
			setProperties(list);
		}
		return user;
	}

	@Override
	public List<OwnerUser> findUsersByLikeName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "%" + userName + "%");
		List<OwnerUser> users = findByNamedParams("from OwnerUser user where user.userName like :userName", params);

		setProperties(users);
		return users;
	}
	
	/** 
	 * districtId：如果为空不作为查询条件，不作为查询条件
	 */
	@Override	
	public long countUsers(OwnerUser user) {
		StringBuilder jpql = new StringBuilder("SELECT COUNT(user) FROM OwnerUser user");
		Map<String, Object> params = new HashMap<String, Object>();
		if(user != null && StringUtils.isNotBlank(user.getDistrictId())){
			jpql.append(" WHERE user.districtId = :districtId");
			params.put("districtId", user.getDistrictId());
		}
		return getTotalCountByNamedParams(jpql.toString(), params);
	}
	
	@Override
	public OwnerUser getUserByHouseId(String houseId) {
		List<OwnerUser> users = find("FROM OwnerUser user WHERE user.houseId = ?1", houseId);
		if(CollectionUtils.isNotEmpty(users))
			return users.get(0);
		return null;
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
	protected void setProperties(List<OwnerUser> list) {
		if (CollectionUtils.isEmpty(list))
			return;

		for (OwnerUser user : list) {
			// 注入登录对象、小区区域名称、栋、单元、房间
			user.setLogin(getLogin(user.getUserId(), 
					UserType.OWNER.getValue()));
			setHouseInfo(user);
		}
	}
	
	//设置房间相关信息
	private void setHouseInfo(OwnerUser user){
		if(StringUtils.isNotBlank(user.getHouseId())){
			CellHouseholdInfo houseInfo = getCellHouseholdInfoService().get(user.getHouseId());
			if(houseInfo != null){
				user.setHouseName(houseInfo.getName());
				if(houseInfo.getTHmBuildingCellInfo() != null){
					user.setUnitName(houseInfo.getTHmBuildingCellInfo().getName());
					if(houseInfo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo() != null){
						user.setBuilding(houseInfo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName());
						if(houseInfo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo() != null)
							user.setAreaName(houseInfo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName());
					}
				}			
				
			}
		}
	}
	
	@Override
	protected List<String> appendParams(OwnerUser user, StringBuilder jpql,
			Map<String, Object> params) {
		List<String> roleCodes = new ArrayList<String>();
		if (user == null || StringUtils.isBlank(user.getDistrictId()))
			return roleCodes;

		appendCondition(jpql, "user.districtId = :districtId", "districtId", 
				user.getDistrictId(), params);
		
		if (StringUtils.isNotBlank(user.getUserName()))
			appendCondition(jpql, "user.userName LIKE :userName", "userName", "%"
					+ user.getUserName() + "%", params);

		if (StringUtils.isNotBlank(user.getIdCard()))
			appendCondition(jpql, "user.idCard LIKE :idCard", "idCard",
					"%" + user.getIdCard() + "%", params);

		if(user.getCreatedTime() != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(user.getCreatedTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date beginTime = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			Date endTime = cal.getTime();
			appendCondition(jpql, "user.createdTime >= :beginTime", "beginTime",
					beginTime, params);
			appendCondition(jpql, "user.createdTime <= :endTime", "endTime",
					endTime, params);			
		}
		
		roleCodes.add(RoleCode.OWNER.getValue());
		return roleCodes;
	}
	
	// 保存住户用户信息
	private void saveUser(OwnerUser user) {
		user.setRoleCode(RoleCode.OWNER.getValue());
		getDao().save(user);

		Login login = user.getLogin();
		login.setUserId(user.getUserId());
		login.setCreatedUser(user.getCreatedUser());
		login.setPassword(getInitPassword());
		login.setUserType(UserType.OWNER.getValue());
		login.setOnlineFlag(Constants.LOGIN_OFFLINE);
		if (StringUtils.isEmpty(login.getLoginAlias()))
			login.setLoginAlias(login.getLoginName());

		getDao().saveObject(login);
		
		updateHouse(user.getHouseId(), user.getUserId(), 
				new Date(), Constants.HOUSE_STATUS_CHECK);
	}
	
	// 更新房号信息
	private void updateHouse(String houseId, String userId, Date checkInDate, String housingStatus){
		CellHouseholdInfo house = new CellHouseholdInfo();		
		house.setId(houseId);
		OwnerUser owner = new OwnerUser();
		owner.setUserId(userId);
		house.setOwner(owner);
		house.setCheckInDate(checkInDate);
		house.setHousingStatus(housingStatus);
		cellHouseholdInfoService.updateHouse(house);
	}
}
