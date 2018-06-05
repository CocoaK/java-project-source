package com.biencloud.smarthome.service.login.service.impl;

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
import com.biencloud.smarthome.service.common.utils.CryptoUtils;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.service.login.enums.LoginResult;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.permissions.service.IPermissionsService;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;
import com.biencloud.smarthome.service.user.model.SaUser;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;
import com.biencloud.smarthome.service.user.service.IPaUserService;
import com.biencloud.smarthome.service.user.service.ISaUserService;

/**
 * 登录模块领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-14
 */
public class LoginServiceImpl extends BaseService<Login, String> implements
		ILoginService {

	private IDao<SystemParam, String> sysParamDao;
	
	private IPermissionsService permissionsService;
	
	private IHousingDistrictInfoService housingDistrictInfoService; 
		
	public IDao<SystemParam, String> getSysParamDao() {
		return sysParamDao;
	}
	public void setSysParamDao(IDao<SystemParam, String> sysParamDao) {
		this.sysParamDao = sysParamDao;
	}
	
	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}
	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}
	
	
	@Override
	public Login login(Login login) {
		Login retLogin = getLoginByLoginName(login.getLoginName());
		if(retLogin == null){
			login.setResult(LoginResult.ACC_ERROR.getValue());
			return login;
		}
		
		if(disabledPermissions(retLogin.getRoleCode())){
			login.setResult(LoginResult.LOCKED.getValue());
			return login;
		}
		
		LoginResult result = LoginResult.convertToResult(
				retLogin.getStatus());
		if(LoginResult.ENABLED != result){
			login.setResult(result.getValue());
			return login;
		}
		
		if(!CryptoUtils.validateByMD5(retLogin.getPassword(), 
				login.getPassword())){
			login.setResult(LoginResult.PASS_ERROR.getValue());
			return login;
		}
		
		retLogin.setResult(LoginResult.SUCCESS.getValue());
		
		return retLogin;
	}
	
	@Override
	public Login getLoginByLoginName(String loginName) {
		String jpql = "SELECT login FROM Login login WHERE login.loginName = ?1";
		
		List<Login> list = find(jpql, loginName);
		if(CollectionUtils.isEmpty(list))
			return null;
		
		Login login = list.get(0);
		setProperties(login);
		
		return login;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void resetPassword(String loginName) {
		setNewPassword(loginName, getInitPassword());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updatePassword(String loginName, String password) {
		setNewPassword(loginName, password);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateStatus(String loginId, String status) {
		String jpql = "UPDATE Login login SET login.status = ?1 WHERE login.loginId = ?2";
		Object[] values = {status, loginId};
		update(jpql, values);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOnlineFlag(String loginName, String onlineFlag, String ip) {
		String jpql = "UPDATE Login login SET login.onlineFlag = ?1, login.ip = ?2 WHERE login.loginName = ?3";
		Object[] values = {onlineFlag, ip, loginName};
		update(jpql, values);
	}
	
	@Override
	public long countUsersByOnlineFlag(String onlineFlag) {
		StringBuilder jpql = new StringBuilder("SELECT COUNT(login) FROM Login login");
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(onlineFlag)){
			jpql.append(" WHERE login.onlineFlag = :onlineFlag");
			params.put("onlineFlag", onlineFlag);
		}
		return getTotalCountByNamedParams(jpql.toString(), params);
	}
	
	@Override
	public String getUserNameByLoginName(String loginName) {
		if(StringUtils.isBlank(loginName))
			return null;
		
		String jpql = "SELECT NEW com.biencloud.smarthome.service.login.model.Login(login.userId, login.userType) FROM Login login WHERE login.loginName = ?1";
		List<Login> list = getDao().find(jpql, loginName);
		if(CollectionUtils.isEmpty(list))
			return null;
		
		Login login = list.get(0);
		
		if(UserType.SA.getValue().equals(login.getUserType()))
			return getSaUserService().getUserNameById(login.getUserId());
		
		if(UserType.PA.getValue().equals(login.getUserType()))
			return getPaUserService().getUserNameById(login.getUserId());
		
		return getOwnerUserService().getUserNameById(login.getUserId());
	}
	
	
	//设置用户相关属性
	private void setProperties(Login login){
		if (UserType.OWNER.getValue().equals(login.getUserType())) {
			//业主用户
			OwnerUser user = getOwnerUserService().get(login.getUserId());
			if (user != null){
				login.setUserName(user.getUserName());
				login.setRoleCode(user.getRoleCode());
				login.setDistrictId(user.getDistrictId());
			}
			
		} else if (UserType.PA.getValue().equals(login.getUserType())) {
			//物业管理用户
			PaUser user = getPaUserService().get(login.getUserId());
			PropertyCompanyInfo pci = new PropertyCompanyInfo();
			if(user!=null){
				HousingDistrictInfo districtInfo = housingDistrictInfoService.get(user.getDistrictId());
				if(districtInfo!=null){
					pci = districtInfo.getPropertyCompanyInfo();
				}
			}
			
			if (user != null){
				login.setUserName(user.getUserName());
				login.setRoleCode(user.getRoleCode());
				login.setDistrictId(user.getDistrictId());
				login.setDistrictName(user.getDistrictName());
				login.setPropertyCompanyName(pci.getName());
			}
		} else {
			//系统管理用户
			SaUser user = getSaUserService().get(login.getUserId());
			if (user != null){
				login.setUserName(user.getUserName());
				login.setRoleCode(user.getRoleCode());
			}
		}
	}
	
	//设置新密码
	private void setNewPassword(String loginName, String password){
		String jpql = "UPDATE Login login SET login.password = ?1 WHERE login.loginName = ?2";
		Object[] values = {CryptoUtils.encodeByMD5(password), loginName};
		update(jpql, values);
	}
	
	//获取初始密码
	private String getInitPassword(){
		return getSysParamDao().get(Constants.PARAM_LOGIN_INIT_PASS).getParamValue();
	}
	
	//验证权限是否禁用
	private boolean disabledPermissions(String roleCode){
		boolean isDisabled = false;
		if(!Constants.PERMISSIONS_ENABLED.equals(
				getPermissionsService().getPermissionsStatus(roleCode)))
			isDisabled = true;
		return isDisabled;
	}
	
	private ISaUserService getSaUserService(){
		return (ISaUserService)getBean(Constants.BEAN_NAME_SA_USER_SERVICE);
	}
	
	private IPaUserService getPaUserService(){
		return (IPaUserService)getBean(Constants.BEAN_NAME_PA_USER_SERVICE);
	}
	
	private IOwnerUserService getOwnerUserService(){
		return (IOwnerUserService)getBean(Constants.BEAN_NAME_OWNER_USER_SERVICE);
	}
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
}
