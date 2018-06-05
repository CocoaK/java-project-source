package com.biencloud.smarthome.web.permissions.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.permissions.vo.RoleVO;


/**
 * 权限管理模块调用服务接口。
 * @author kouy
 * @since 1.0 2012-4-27
 */
public interface IPermissionsService {

	/**
	 * 查询权限列表，分页。
	 * @param roleCode 角色代码
	 * @param loginName 登录名
	 * @param roleName 角色名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param status 角色状态，如果为空不作为查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<RoleVO> queryRolesForPaging(String roleCode, String loginName, 
			String roleName, String status, int pageNum, int pageSize);
	
	/**
	 * 新增权限信息。
	 * @param roleVO 角色值对象
	 */
	public void addRole(RoleVO roleVO);
	
	/**
	 * 获取权限详细信息。
	 * @param roleCode 角色代码
	 * @return
	 */
	public RoleVO getRoleDetail(String roleCode);
	
	/**
	 * 更新权限信息。
	 * @param roleVO 角色值对象
	 */
	public void updateRole(RoleVO roleVO);
	
	/**
	 * 通过角色获取关联的所有菜单。
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<MenuVO> queryMenusByRole(String roleCode);
	
	/**
	 * 通过角色代码获取最多可操作的菜单。
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<MenuVO> queryMenus(String roleCode);
	
	/**
	 * 获取用户角色列表。
	 * @param roleCode 角色代码
	 * @param loginName 登录名
	 * @param currUserType 当前用户类型
	 * @param targetUserType 目标用户类型
	 * @param filterDisabled 是否过滤被禁用的角色
	 * @return
	 */
	public List<RoleVO> queryRoles(String roleCode, String loginName, 
			String currUserType, String targetUserType, boolean filterDisabled);
	
	/**
	 * 验证角色名称是否存在。
	 * @param roleCode 角色代码
	 * @param roleName 角色名称
	 * @return
	 */
	public boolean existRoleName(String roleCode, String roleName);
}
