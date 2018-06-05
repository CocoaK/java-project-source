package com.biencloud.smarthome.service.permissions.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.permissions.model.Role;

/**
 * 权限模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-16
 * @throws RuntimeException 如果操作执行失败
 */
public interface IPermissionsService extends IService<Role, String> {
	
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
	public Paging<Role> queryRolesForPaging(String roleCode, String loginName, 
			String roleName, String status, int pageNum, int pageSize);
	
	/**
	 * 通过角色获取关联的所有菜单。
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<Menu> queryMenusByRole(String roleCode);
	
	/**
	 * 获取用户角色列表。
	 * @param roleCode 角色代码
	 * @param loginName 登录名
	 * @param currUserType 当前用户类型
	 * @param targetUserType 目标用户类型
	 * @param filterDisabled 是否过滤被禁用的角色
	 * @return
	 */
	public List<Role> queryRoles(String roleCode, String loginName, 
			String currUserType, String targetUserType, boolean filterDisabled);
	
	/**
	 * 验证角色名称是否存在。
	 * 1）如果角色代码为空，则验证是否存在角色名称；<br/>
	 * 2）如果角色代码不为空，则验证角色名称对应的角色代码是否和当前角色代码相同；<br/>
	 * @param roleCode 角色代码
	 * @param roleName 角色名称
	 * @return
	 */
	public boolean existRoleName(String roleCode, String roleName);
	
	/**
	 * 通过角色代码获取权限状态，查询不到返回Null。
	 * @param roleCode 角色代码
	 * @return
	 */
	public String getPermissionsStatus(String roleCode);
	
	/**
	 * 通过角色代码获取最多可操作的菜单。
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<Menu> queryMenus(String roleCode);
}
