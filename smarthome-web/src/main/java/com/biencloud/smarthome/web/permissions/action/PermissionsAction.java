package com.biencloud.smarthome.web.permissions.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.permissions.vo.RoleVO;

/**
 * 权限管理模块动作类。
 * @author kouy
 * @since 1.0 2012-4-27
 */
@SuppressWarnings("serial")
public class PermissionsAction extends BaseAction<RoleVO> {

	private String currRoleCode;
	private String roleName;
	private String status;
	
	private boolean successFlag;
	
	private boolean existRoleName;
	
	String[] menuCodes;
	
	private RoleVO role;
	
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getCurrRoleCode() {
		return currRoleCode;
	}
	
	public void setCurrRoleCode(String currRoleCode) {
		this.currRoleCode = currRoleCode;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public boolean isExistRoleName() {
		return existRoleName;
	}

	public void setExistRoleName(boolean existRoleName) {
		this.existRoleName = existRoleName;
	}

	public String[] getMenuCodes() {
		return menuCodes;
	}
	public void setMenuCodes(String[] menuCodes) {
		this.menuCodes = menuCodes;
	}
	
	public RoleVO getRole() {
		return role;
	}
	public void setRole(RoleVO role) {
		this.role = role;
	}

	
	/**
	 * 查看指定权限的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setRole(convertMenuNameToI18N(
				getPermissionsService().getRoleDetail(currRoleCode)));		
		return SUCCESS;
	}
	
	/**
	 * 查询权限列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<RoleVO> page = getPage();
		if(page == null)
			page = new PagingVO<RoleVO>();
				
		PagingVO<RoleVO> pageVO = getPermissionsService().queryRolesForPaging(
				getRoleCode(), getLoginName(), roleName, status, page.getPageNum(), page.getPageSize());
		
		setPage(pageVO);
		
		return SUCCESS;
	}

	/**
	 * 新增权限信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		if(role == null)
			role = new RoleVO();					
		
		role.setMenus(convertMenuNameToI18N(
				getPermissionsService().queryMenusByRole(getRoleCode())));
		return SUCCESS;
	}
	
	/**
	 * 保存新增权限信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {
		role.setParentCode(getRoleCode());
		role.setCreatedUser(getLoginName());
		role.setCreatedTime(new Date());
		role.setMenus(buildMenus());
		getPermissionsService().addRole(role);
		setCurrRoleCode(null);
		role = null;
		setSuccessFlag(true);
		return addInput();
	}
	
	/**
	 * 获取权限信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		List<MenuVO> maxMenus = getPermissionsService().queryMenus(currRoleCode);
		setRequestAttribute("menus", convertMenuNameToI18N(maxMenus));
		setRole(convertMenuNameToI18N(
				getPermissionsService().getRoleDetail(currRoleCode)));
		return SUCCESS;
	}
	
	/**
	 * 更新权限信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		role.setUpdatedUser(getLoginName());
		role.setUpdatedTime(new Date());
		role.setMenus(buildMenus());
		getPermissionsService().updateRole(role);
		setCurrRoleCode(role.getRoleCode());
		setSuccessFlag(true);
		return updateInput();
	}
	
	/**
	 * 判断角色名称是否已经存在。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String existRoleName() throws Exception {
		if(getPermissionsService().existRoleName(currRoleCode, roleName))
			setExistRoleName(true);
		return SUCCESS;
	}
	
	
	private List<MenuVO> buildMenus() {
		List<MenuVO> menus = new ArrayList<MenuVO>();
		if(ArrayUtils.isNotEmpty(menuCodes)){
			for (String menuCode : menuCodes) {
				MenuVO menuVO = new MenuVO();
				menuVO.setMenuCode(menuCode);
				menus.add(menuVO);
			}
		}
		return menus;
	}
	
	//将菜单名称国际化
	private List<MenuVO> convertMenuNameToI18N(List<MenuVO> menus){
		if(menus == null)
			return menus;
		
		for (MenuVO menuVO : menus)
			menuVO.setMenuName(getText(menuVO.getMenuCode()));
		
		return menus;
	}
	
	//将权限所属的菜单名称国际化
	private RoleVO convertMenuNameToI18N(RoleVO roleVO){
		if(roleVO == null || roleVO.getMenus() == null)
			return roleVO;
		
		roleVO.setMenus(convertMenuNameToI18N(roleVO.getMenus()));
			
		return roleVO;
	}
	
	private IPermissionsService permissionsService;
	
	public void setPermissionsService(
			IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}
	
	private IPermissionsService getPermissionsService() {
		return permissionsService;
	}
}
