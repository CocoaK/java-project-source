package com.biencloud.smarthome.service.permissions.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.user.enums.UserType;

/**
 * 权限模块服务测试类。
 * @author kouy
 * @since 1.0 2012-4-20
 */
public class PermissionsServiceTest extends BaseTest {

	@Autowired
	private IPermissionsService permissionsService;
	
	@Test
	public void queryRolesForPaging(){
		Paging<Role> paging = permissionsService.queryRolesForPaging(
				RoleCode.SUPER_ADMIN.getValue(), null, null, "0", 1, 10);
		logger.info("********************返回角色分页信息：{}", paging);
		
		paging = permissionsService.queryRolesForPaging(
				"P0002", "test", null , "0", 1, 20);		
		logger.info("********************返回角色分页信息：{}", paging);
		
		paging = permissionsService.queryRolesForPaging(
				"P0005", "test", null , "0", 1, 20);		
		logger.info("********************返回角色分页信息：{}", paging);
		
		paging = permissionsService.queryRolesForPaging(
				"P0007", "test", null , "0", 1, 20);		
		logger.info("********************返回角色分页信息：{}", paging);
	}
	
	@Test
	public void getRoleDetail(){
		Role role = permissionsService.get("not_existed_role");
		
		Assert.assertNull(role);
		
		role = permissionsService.get(RoleCode.SUPER_ADMIN.getValue());
		
		logger.info("********************返回角色详细信息：{}", role);
	}
	
	@Test
	public void addRole(){
		String roleCode = "P9999";
		if(permissionsService.get(roleCode) == null){		
			Role role = new Role();
			role.setStatus("0");
			role.setRoleDesc("PM");
			role.setCreatedUser("test");
			role.setCreatedTime(new Date());
			
			List<Menu> menus = new ArrayList<Menu>();
			Menu menu1 = new Menu();
			menu1.setMenuCode("M001001");
			menus.add(menu1);
			Menu menu2 = new Menu();
			menu2.setMenuCode("M001002");
			menus.add(menu2);
			role.setMenus(menus);
			permissionsService.save(role);
			logger.info("********************新增角色：{}", role);
		}
	}
	
	@Test
	public void updateRole(){
		Role role = permissionsService.get(RoleCode.SUPER_ADMIN.getValue());
		
		if(role != null){
			role.setUpdatedUser("test");
			role.setUpdatedTime(new Date());
			
			List<Menu> menus = role.getMenus();
			Menu menu1 = new Menu();
			menu1.setMenuCode("M001003");
			menus.add(menu1);
			permissionsService.update(role);
			logger.info("********************更新角色：{}", role);
		}
	}
	
	@Test
	public void queryMenusByRole(){
		List<Menu> list = permissionsService.queryMenusByRole(RoleCode.SUPER_ADMIN.getValue());
		logger.info("********************返回角色关联的所有菜单：{}", list);
	}
	
	@Test
	public void queryRoles(){
		List<Role> list = permissionsService.queryRoles(RoleCode.SUPER_ADMIN.getValue(), "test", UserType.SA.getValue(), UserType.SA.getValue(), false);
		logger.info("********************返回角色列表：{}", list);
		
		list = permissionsService.queryRoles(RoleCode.SYS_ADMIN.getValue(), "test", UserType.SA.getValue(), UserType.SA.getValue(), false);
		logger.info("********************返回角色列表：{}", list);
		
		list = permissionsService.queryRoles(RoleCode.PROP_ADMIN.getValue(), "test", UserType.SA.getValue(), UserType.PA.getValue(), false);
		logger.info("********************返回角色列表：{}", list);
		
		list = permissionsService.queryRoles(RoleCode.PROP_ADMIN.getValue(), "test", UserType.PA.getValue(), UserType.PA.getValue(), true);
		logger.info("********************返回角色列表：{}", list);
		
		list = permissionsService.queryRoles("P0005", "test", UserType.SA.getValue(), UserType.SA.getValue(), true);
		logger.info("********************返回角色列表：{}", list);
		
		list = permissionsService.queryRoles("P0011", "test", UserType.PA.getValue(), UserType.PA.getValue(), true);
		logger.info("********************返回角色列表：{}", list);
	}
	
	@Test
	public void existRoleName(){
		String roleCode = null;
		String roleName = "系统管理员";
		logger.info("********************当前角色代码为{}和角色名称为{}是否存在：{}", new Object[]{
				roleCode, roleName, permissionsService.existRoleName(roleCode, roleName)});
		roleCode = "P0002";
		logger.info("********************当前角色代码为{}和角色名称为{}是否存在：{}", new Object[]{
				roleCode, roleName, permissionsService.existRoleName(roleCode, roleName)});
	}
}
