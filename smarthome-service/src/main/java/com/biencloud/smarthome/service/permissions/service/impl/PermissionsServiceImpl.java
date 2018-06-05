package com.biencloud.smarthome.service.permissions.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.enums.LockType;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.menu.service.IMenuService;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.permissions.service.IPermissionsService;
import com.biencloud.smarthome.service.user.enums.UserType;

/**
 * 菜单和权限模块领域服务实现类。
 * @author kouy
 * @since 1.0 2012-4-16
 * @see BaseService
 * @see IPermissionsService
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PermissionsServiceImpl extends BaseService<Role, String> implements
		IPermissionsService {
	
	//默认角色代码和父菜单代码的映射表
	private static final Map<String, String> RC_PMC_MAPPINGS = new HashMap<String, String>();
	
	static{
		RC_PMC_MAPPINGS.put(RoleCode.SUPER_ADMIN.getValue(), Constants.MENU_PARENT_CODE_SA);
		RC_PMC_MAPPINGS.put(RoleCode.SYS_ADMIN.getValue(), Constants.MENU_PARENT_CODE_SA);
		RC_PMC_MAPPINGS.put(RoleCode.PROP_ADMIN.getValue(), Constants.MENU_PARENT_CODE_PA);
		RC_PMC_MAPPINGS.put(RoleCode.OWNER.getValue(), Constants.MENU_PARENT_CODE_OWNER);
	}
	
	private IMenuService menuService;
	
	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	
	@Override
	public Paging<Role> queryRolesForPaging(String roleCode, String loginName, 
			String roleName, String status, int pageNum, int pageSize) {
		if(StringUtils.isBlank(roleCode))
			throw new IllegalArgumentException("The role code is empty!");
			
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<String> roleCodes = appendParamForRoleCodes(
				roleCode, loginName, jpql, params);
		if(CollectionUtils.isEmpty(roleCodes))
			return createEmptyPaging(pageNum, pageSize);
		
		appendParams(roleName, status, jpql, params);
		jpql.append(" ORDER BY role.createdTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM Role role");
		
		StringBuilder returnResult = new StringBuilder();
		returnResult.append("NEW com.biencloud.smarthome.service.permissions.model.Role(");
		returnResult.append("role.roleCode, role.parentCode, role.roleName, ");
		returnResult.append("role.isDefault, role.status, role.roleDesc)");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, returnResult);
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(role)");
		
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Role role) {
		role.setRoleCode(getNextRoleCode());
		role.setIsDefault("1");
		getDao().save(role);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Role role) {
		Role srcRole = get(role.getRoleCode());
		List<Menu> srcMenus = srcRole.getMenus();
		List removeMenus = (List) CollectionUtils.subtract(srcMenus, role.getMenus());
		if(CollectionUtils.isNotEmpty(removeMenus)){
			List<String> subRoleCodes = new ArrayList<String>();
			addSubRoleCodes(subRoleCodes, new String[]{role.getRoleCode()}, null, null);		
			for (String rc : subRoleCodes) {
				Role srcSubRole = get(rc);
				srcSubRole.setMenus((List<Menu>)CollectionUtils.subtract(srcSubRole.getMenus(), removeMenus));
				getDao().update(srcSubRole);
			}
		}		
		getDao().update(role);
	}
	
	@Override
	public Role get(String entityId) {
		Role role = getDao().get(entityId);
		if(role != null){
			role.setCreatedUser(getLoginService().getUserNameByLoginName(
					role.getCreatedUser()));
			role.setUpdatedUser(getLoginService().getUserNameByLoginName(
					role.getUpdatedUser()));
		}
		return role;
	}
	
	@Override
	public List<Menu> queryMenusByRole(String roleCode) {
		List<Menu> menus = get(roleCode).getMenus();
		if(CollectionUtils.isEmpty(menus))
			return menus;

		for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();) {
			if(Constants.MENU_STATUS_HIDDEN.equals(iterator.next().getStatus()) 
					&& !ignoreHiddenMenu(roleCode)){
				iterator.remove();
				continue;
			}
		}
		
		return  menus;
	}
	
	@Override
	public List<Menu> queryMenus(String roleCode) {
		if(RoleCode.getAllValues().contains(roleCode))
			return getMenuService().queryMenusByParentCode(
					RC_PMC_MAPPINGS.get(roleCode));
		
		return queryMenusByRole(getParentCode(roleCode));
	}
	
	@Override
	public List<Role> queryRoles(String roleCode, String loginName, 
			String currUserType, String targetUserType, boolean filterDisabled) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> roleCodes = new ArrayList<String>();
		if(UserType.SA.getValue().equals(currUserType)){
			if(UserType.SA.getValue().equals(targetUserType)){
				if(RoleCode.SUPER_ADMIN.getValue().equals(roleCode)){
					addSubRoleCodes(roleCodes, new String[]{RoleCode.SYS_ADMIN.getValue(), roleCode}, 
							null, new String[]{RoleCode.PROP_ADMIN.getValue()});
					roleCodes.add(RoleCode.SYS_ADMIN.getValue());
				}else if(RoleCode.SYS_ADMIN.getValue().equals(roleCode)){
					addSubRoleCodes(roleCodes, new String[]{roleCode}, null, 
							new String[]{RoleCode.PROP_ADMIN.getValue()});
				}else{
					addSubRoleCodes(roleCodes, new String[]{roleCode}, loginName, null);
				}
			}else{
				roleCodes.add(RoleCode.PROP_ADMIN.getValue());
			}
		}else{
			if(RoleCode.PROP_ADMIN.getValue().equals(roleCode)){
				//获取默认物业管理员角色的所有后代角色代码
				addSubRoleCodes(roleCodes, new String[]{roleCode}, null, null);
			}else{
				//获取当前用户及角色的所有后代角色代码
				addSubRoleCodes(roleCodes, new String[]{roleCode}, loginName, null);
			}
		}
		
		if(CollectionUtils.isEmpty(roleCodes))
			return new ArrayList<Role>();
		
		StringBuilder jpql = new StringBuilder("SELECT NEW com.biencloud.smarthome.service.permissions.model.Role(");
		jpql.append("role.roleCode, role.parentCode, role.roleName) FROM Role role");
		jpql.append(" WHERE role.roleCode in (");
		jpql.append(joinRoleCodeVars(params, roleCodes.toArray(new String[0])));
		jpql.append(")");
		if(filterDisabled)
			jpql.append(" AND role.status = 0");
		
		return getDao().findByNamedParams(jpql.toString(), params);
	}
	
	@Override
	public boolean existRoleName(String roleCode, String roleName) {		
		String jpql = "SELECT role.roleCode FROM Role role WHERE role.roleName = ?1";
		List<String> roleCodes = findIds(jpql, roleName);
		if(CollectionUtils.isEmpty(roleCodes))
			return false;
		
		if(StringUtils.isEmpty(roleCode) 
				|| roleCodes.size() > 1)
			return true;
		
		return (!roleCodes.contains(roleCode));
	}
	
	@Override
	public String getPermissionsStatus(String roleCode) {
		String jpql = "SELECT role.status FROM Role role WHERE role.roleCode = ?1";
		List<Object> list = getDao().findObjects(jpql, roleCode);
		if(CollectionUtils.isEmpty(list))
			return null;
		return (String)list.get(0);
	}
	
	
	// 创建无记录的分页信息
	private Paging<Role> createEmptyPaging(int pageNum, int pageSize) {
		Paging<Role> paging = new Paging<Role>();
		paging.setPageNum(pageNum);
		paging.setPageSize(pageSize);
		paging.setTotalCount(0L);
		return paging;
	}
	
	// 添加角色名称和状态参数。
	private void appendParams(String roleName, 
			String status, StringBuilder jpql, Map<String, Object> params) {	
		
		if(StringUtils.isNotBlank(roleName))
			appendCondition(jpql, " role.roleName LIKE :roleName", 
					"roleName", "%" + roleName + "%", params);
		
		appendCondition(jpql, " role.status = :status", 
				"status", status, params);
	}
	
	//添加角色代码参数
	private List<String> appendParamForRoleCodes(String roleCode, 
			String loginName, StringBuilder jpql, Map<String, Object> params){
		List<String> roleCodes = new ArrayList<String>();
		if(RoleCode.SUPER_ADMIN.getValue().equals(roleCode)){
			roleCodes.add(roleCode);
			jpql.append(" WHERE role.roleCode != :roleCode");
			params.put("roleCode", roleCode);
			return roleCodes;
		}
		
		if(RoleCode.SYS_ADMIN.getValue().equals(roleCode)){
			//获取超级管理员的所有后代角色代码
			addSubRoleCodes(roleCodes, new String[]{RoleCode.SUPER_ADMIN.getValue()}, null, null);
			roleCodes.add(RoleCode.SUPER_ADMIN.getValue());
			roleCodes.add(roleCode);
			jpql.append(" WHERE role.roleCode not in (");
			jpql.append(joinRoleCodeVars(params, roleCodes.toArray(new String[0])));
			jpql.append(")");
		}else{
			if(RoleCode.PROP_ADMIN.getValue().equals(roleCode)){
				//获取默认物业管理员角色的所有后代角色代码
				addSubRoleCodes(roleCodes, new String[]{roleCode}, null, null);
			}else{
				//获取当前用户及角色的所有后代角色代码
				addSubRoleCodes(roleCodes, new String[]{roleCode}, loginName, null);
			}
						
			if(CollectionUtils.isEmpty(roleCodes))
				return roleCodes;
			
			jpql.append(" WHERE role.roleCode in (");
			jpql.append(joinRoleCodeVars(params, roleCodes.toArray(new String[0])));
			jpql.append(")");
		}	
		return roleCodes;
	}
	
	//递归获取和添加指定父角色代码的子角色代码
	private void addSubRoleCodes(List<String> subRoleCodes, 
			String[] parentCodes, String createdUser, String[] ignoreRoleCodes) {
		if (ArrayUtils.isEmpty(parentCodes))
			return;

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder(
				"SELECT role.roleCode FROM Role role WHERE role.parentCode in (");
		jpql.append(joinRoleCodeVars(params, parentCodes));
		jpql.append(")");
		if(StringUtils.isNotBlank(createdUser)){
			jpql.append(" AND role.createdUser = :createdUser");
			params.put("createdUser", createdUser);
		}
		List<String> subCodes = getDao().findIdsByNamedParams(jpql.toString(),
				params);
		if (CollectionUtils.isNotEmpty(subCodes)) {
			removeIgnoreRoleCodes(subCodes, ignoreRoleCodes);
			subRoleCodes.addAll(subCodes);
			addSubRoleCodes(subRoleCodes, subCodes.toArray(new String[0]), null, ignoreRoleCodes);
		}
	}
	
	//删除指定的角色代码
	private void removeIgnoreRoleCodes(List<String> roleCodes, 
			String[] ignoreRoleCodes){
		if(CollectionUtils.isEmpty(roleCodes) 
				|| ArrayUtils.isEmpty(ignoreRoleCodes))
			return;
		
		for (String string : ignoreRoleCodes) {
			if(roleCodes.contains(string))
				roleCodes.remove(string);
		}
	}
	
	//添加查询条件
	private void appendCondition(StringBuilder jpql, String condition, 
			String key, String value, Map<String, Object> params) {
		if (StringUtils.isNotBlank(value)) {
			if (StringUtils.contains(jpql, " WHERE ")) {
				jpql.append(" AND").append(condition);
			} else {
				jpql.append(" WHERE").append(condition);
			}			
			params.put(key, value);
		}
	}
	
	//拼接角色代码变量
	private String joinRoleCodeVars(Map<String, Object> params, String[] roleCodes){
		if(ArrayUtils.isEmpty(roleCodes))
			return "";
		
		String[] vars = new String[roleCodes.length];
		for (int index = 0, size = roleCodes.length; index < size; index++) {
			String varName = "RoleCode" + index;
			vars[index] = ":" + varName;
			params.put(varName, roleCodes[index]);
		}
		
		return StringUtils.join(vars, ',');
	}
	
	//通过角色代码获取父角色代码
	private String getParentCode(String roleCode){
		String jpql = "SELECT role.parentCode FROM Role role WHERE role.roleCode = ?1";
		List<Object> list = getDao().findObjects(jpql, roleCode);
		if(CollectionUtils.isEmpty(list))
			return null;
		return (String)list.get(0);
	}
	
	//获取下一个可新增的角色代码
	private String getNextRoleCode(){
		String jpql = "SELECT MAX(role.roleCode) FROM Role role";
		List<Object> list = getDao().findObjects(LockType.PESSIMISTIC_WRITE, jpql);
		if(CollectionUtils.isEmpty(list))
			throw new RuntimeException("不存在默认的角色代码，初始数据错误！");
		String maxRoleCode = (String)list.get(0);
		String digits = getDigits(maxRoleCode);
		if(StringUtils.isEmpty(digits))
			throw new RuntimeException("角色代码数据格式错误！");
		StringBuilder nextRoleCode = new StringBuilder();
		nextRoleCode.append(maxRoleCode.substring(0, 
				(maxRoleCode.length() - digits.length())));
		nextRoleCode.append(StringUtils.leftPad(
				String.valueOf(Long.parseLong(digits) + 1), digits.length(), '0'));
		return nextRoleCode.toString();
	}
	
	//获取字符串的数字开始的子串，如果子串不是数字串则返回空
	private static String getDigits(String value){
		String digits = "";
		if(StringUtils.isNotEmpty(value)){	
			int beginIndex = -1;
			for (int index = 0; index < value.length(); index++) {
				if(NumberUtils.isDigits(String.valueOf(value.charAt(index)))){				
					beginIndex = index;
					break;
				}
			}
			
			if(beginIndex > -1){
				digits = value.substring(beginIndex);
				if(NumberUtils.isDigits(digits))
					return digits;
				digits = "";
			}
		}
		
		return digits;
	}
	
	//是否忽略隐藏的菜单
	private boolean ignoreHiddenMenu(String roleCode){
		return (RoleCode.SUPER_ADMIN.getValue().equals(roleCode) 
				|| RoleCode.SYS_ADMIN.getValue().equals(roleCode));
	}
	
	private ILoginService getLoginService() {
		return (ILoginService)getBean(Constants.BEAN_NAME_LOGIN_SERVICE);
	}
}
