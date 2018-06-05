package com.biencloud.smarthome.service.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.CryptoUtils;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.permissions.service.IPermissionsService;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;

/**
 * 用户管理服务基类。
 * @author kouy
 * @since 1.0 2012-5-16
 */
public abstract class AbstractUserService<T, Id extends Serializable> extends
		BaseService<T, Id> {

	private IDao<Login, String> loginDao;
	
	private ILoginService loginService;
	
	private IDao<SystemParam, String> sysParamDao;
	
	private IPermissionsService permissionsService;

	public IDao<Login, String> getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(IDao<Login, String> loginDao) {
		this.loginDao = loginDao;
	}
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

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

	
	protected Paging<T> queryUsersForPaging(T user, 
			String objName, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();

		Map<String, Object> params = new HashMap<String, Object>();
		List<String> roleCodes = appendParams(user, jpql, params);
		if(CollectionUtils.isEmpty(roleCodes))
			return createEmptyPaging(pageNum, pageSize);
		
		jpql.append(" ORDER BY user.createdTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM " + objName + " user");

		String queryString = jpql.toString().replace(REPLACE_CHARS, "user");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(user)");
		
		Paging<T> paging = queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
		System.out.println();
		System.out.println("jpql:"+jpql.toString());
		System.out.println("params:"+params);
		System.out.println();
		setProperties(paging.getResults());
		
		return paging;
	}
	
	/**
	 * 判断用户的身份证是否存在。
	 * 1）如果用户编号为空，则验证是否存在身份证；<br/>
	 * 2）如果用户编号不为空，则验证身份证对应的用户编号是否和当前用户编号相同；<br/>
	 * @param entityName 实体名
	 * @param userId 用户编号
	 * @param idCard 身份证
	 * @return
	 */
	protected boolean existUserIdCard(String entityName, String userId, String idCard){
		String jpql = "SELECT user.userId FROM " + entityName + " user WHERE user.idCard = ?1";
		List<Id> userIds = findIds(jpql, idCard);
		if(CollectionUtils.isEmpty(userIds))
			return false;
		
		if(StringUtils.isEmpty(userId) 
				|| userIds.size() > 1)
			return true;
		
		return (!userIds.contains(userId));
	}
	
	/**
	 * 通过用户编号和用户类型获取登录信息。
	 * @param userId 用户编号
	 * @param userType 用户类型
	 * @return
	 */
	protected Login getLogin(String userId, String userType) {
		String jpql = "SELECT login FROM Login login WHERE login.userId = ?1 AND login.userType = ?2";
		return getLoginDao().find(jpql, userId, userType).get(0);
	}
	
	/**
	 * 通过用户编号和用户类型获取登录信息。
	 * @param userId 用户编号
	 * @param userType 用户类型
	 * @return
	 */
	protected Login getSaLogin(String userId) {
		String jpql = "SELECT login FROM Login login WHERE login.userId = ?1 AND login.userType in ('03','04')";
		return getLoginDao().find(jpql, userId).get(0);
	}
	
	/**
	 * 通过角色代码获取角色信息。
	 * @param roleCode 角色代码
	 * @return
	 */
	protected Role getRole(String roleCode) {
		return getPermissionsService().get(roleCode);
	}
	
	/**
	 * 往用户列表的用户对象设置相关属性。
	 * @param list 用户列表
	 */
	protected abstract void setProperties(List<T> list);
	
	/**
	 * 添加属性变量名和属性值映射。
	 * @param jpql JPA查询语句
	 * @param user 用户
	 * @return
	 */
	protected abstract List<String> appendParams(
			T user, StringBuilder jpql, Map<String, Object> params);
	
	/**
	 * 拼接角色代码变量。
	 * @param params 参数
	 * @param roleCodes 角色代码
	 * @return
	 */
	protected String joinRoleCodeVars(Map<String, Object> params,
			String[] roleCodes) {
		if (ArrayUtils.isEmpty(roleCodes))
			return "";

		String[] vars = new String[roleCodes.length];
		for (int index = 0, size = roleCodes.length; index < size; index++) {
			String varName = "RoleCode" + index;
			vars[index] = ":" + varName;
			params.put(varName, roleCodes[index]);
		}

		return StringUtils.join(vars, ',');
	}
	
	/**
	 * 提取角色代码。
	 * @param roles 角色列表
	 * @return
	 */
	protected List<String> extractRoleCodes(List<Role> roles){
		List<String> roleCodes = new ArrayList<String>();
		for (Role role : roles)
			roleCodes.add(role.getRoleCode());
		return roleCodes;
	}
	
	/**
	 * 获取初始密码。
	 * @return
	 */
	protected String getInitPassword(){
		return CryptoUtils.encodeByMD5(getSysParamDao().get(
				Constants.PARAM_LOGIN_INIT_PASS).getParamValue());
	}
	
	/**
	 * 通过登录名获取用户真实姓名。
	 * @param loginName 登录名
	 * @return
	 */
	protected String getUserNameByLoginName(String loginName){
		return getLoginService().getUserNameByLoginName(loginName);
	}
	
	/**
	 * 通过用户编号获取用户真实姓名，获取不到返回Null。				
	 * @param entityObjName 实体对象名称
	 * @param userId 用户编号
	 * @return
	 */
	protected String getUserNameById(String entityObjName, String userId){
		List<Object> list = getDao().findObjects("SELECT user.userName FROM " + entityObjName + " user WHERE user.userId = ?1", userId);
		if(CollectionUtils.isEmpty(list))
			return null;
		return (String)list.get(0);
	}
	
	
	// 创建无记录的分页信息
	private Paging<T> createEmptyPaging(int pageNum, int pageSize) {
		Paging<T> paging = new Paging<T>();
		paging.setPageNum(pageNum);
		paging.setPageSize(pageSize);
		paging.setTotalCount(0L);
		return paging;
	}
}
