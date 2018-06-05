package com.biencloud.smarthome.web.permissions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.permissions.vo.RoleVO;
import com.biencloud.smarthome.web.wsclient.stub.Menu;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.Role;

/**
 * 权限管理服务实现。
 * @author kouy
 * @since 1.0 2012-5-2
 * @see IPermissionsService
 */
public class PermissionsServiceImpl extends BaseService<RoleVO> implements IPermissionsService {

	private static final String MENUS = "menus";
	private static final String CREATED_TIME = "createdTime";
	private static final String UPDATED_TIME = "updatedTime";
	private static final String RESULTS = "results";
	
	@Override
	public PagingVO<RoleVO> queryRolesForPaging(String roleCode, String loginName, 
			String roleName, String status, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().queryRolesForPaging(
				roleCode, loginName, roleName, status, pageNum, pageSize);
		
		PagingVO<RoleVO> pagingVO = new PagingVO<RoleVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<RoleVO> results = new ArrayList<RoleVO>();
			for (Object obj : list) {
				results.add(convertToVO((Role)obj, MENUS));
			}
			pagingVO.setResults(results);
		}
			
		return pagingVO;
	}
	
	@Override
	public void addRole(RoleVO roleVO) {
		getSmartHomeService().addRole(convertToEntity(roleVO));
	}

	@Override
	public RoleVO getRoleDetail(String roleCode) {
		Role role = getSmartHomeService().getRoleDetail(roleCode);
		return convertToVO(role);
	}

	@Override
	public void updateRole(RoleVO roleVO) {
		getSmartHomeService().updateRole(convertToEntity(roleVO));
	}

	@Override
	public List<MenuVO> queryMenusByRole(String roleCode) {
		List<Menu> list = getSmartHomeService().queryMenusByRole(roleCode);
		return convertToMenuVOList(list);
	}
	
	@Override
	public List<MenuVO> queryMenus(String roleCode) {
		return convertToMenuVOList(
				getSmartHomeService().queryMenus(roleCode));
	}
	
	@Override
	public List<RoleVO> queryRoles(String roleCode, String loginName, 
			String currUserType, String targetUserType, boolean filterDisabled) {
		List<Role> roles = getSmartHomeService().queryRoles(
				roleCode, loginName, currUserType, targetUserType, filterDisabled);
		List<RoleVO> roleVOs = new ArrayList<RoleVO>();
		for (Role role : roles) {
			roleVOs.add(convertToVO(role, MENUS));
		}
		return roleVOs;
	}
	
	@Override
	public boolean existRoleName(String roleCode, String roleName) {
		return getSmartHomeService().existRoleName(roleCode, roleName);
	}

	
	//转换为角色值对象
	private RoleVO convertToVO(Role role, 
			String... ignoreProperties) {
		RoleVO roleVO = new RoleVO();
		copyObject(role, roleVO, true, ignoreProperties);
		return roleVO;
	}

	// 转换为角色实体对象
	private Role convertToEntity(RoleVO roleVO, 
			String... ignoreProperties) {
		Role role = new Role();
		copyObject(role, roleVO, false, ignoreProperties);
		return role;
	}

	// 角色值对象和实体对象相互拷贝
	private void copyObject(Role role, RoleVO roleVO, 
			boolean convertToVO, String[] ignoreProperties) {
		boolean contains = ArrayUtils.contains(
				ignoreProperties, MENUS);
		if (!contains)
			ignoreProperties = (String[]) ArrayUtils.add(
					ignoreProperties, MENUS);

		if (convertToVO) {
			copyProperties(role, roleVO, ignoreProperties, 
					convertToVO, CREATED_TIME, UPDATED_TIME);
		} else {
			copyProperties(roleVO, role, ignoreProperties, 
					convertToVO, CREATED_TIME, UPDATED_TIME);
		}

		if (!contains) {
			if (convertToVO) {
				roleVO.setMenus(convertToMenuVOList(role.getMenus()));
			} else {
				copyToMenuEntityList(roleVO.getMenus(), role.getMenus());
			}
		}
	}

	// 菜单实体列表转换为菜单值对象列表
	private List<MenuVO> convertToMenuVOList(List<Menu> menus) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		if(menus != null){
			for (Menu menu : menus) {
				MenuVO menuVO = new MenuVO();
				copyProperties(menu, menuVO, null, true, UPDATED_TIME);
				list.add(menuVO);
			}
		}
		return list;
	}

	// 菜单值对象列表转换为菜单实体列表
	private void copyToMenuEntityList(
			List<MenuVO> menuVOs, List<Menu> menus) {
		for (MenuVO menuVO : menuVOs) {
			Menu menu = new Menu();
			copyProperties(menuVO, menu, null, false, UPDATED_TIME);
			menus.add(menu);
		}
	}
}
