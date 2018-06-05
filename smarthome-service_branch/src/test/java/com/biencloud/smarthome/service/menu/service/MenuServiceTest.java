package com.biencloud.smarthome.service.menu.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.menu.model.Menu;

/**
 * 菜单模块服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-4-19
 */
public class MenuServiceTest extends BaseTest {
	
	@Autowired
	private IMenuService menuService;
	
	@Test
	public void queryMenus(){
		Paging<Menu> paging = menuService.queryMenusForPaging(
				"not_existed_menu", true, "0", 1, 10);
		
		paging = menuService.queryMenusForPaging(
				null, true, "0", 1, 10);
		logger.info("----------------------返回分页信息：{}", paging);
	}
	
	@Test
	public void getMenuDetail(){
		Menu menu = menuService.get("not_existed_menu");
		
		Assert.assertNull(menu);
		
		menu = menuService.get("M001001");
		
		logger.info("----------------------返回菜单详细信息：{}", menu);
	}
	
	@Test
	public void updateMenu(){
		Menu menu = menuService.get("M001001");
		if(menu != null){
			menu.setMenuName("个人信息");
			menu.setMenuDesc("个人信息");
			menu.setParentCode("M001");
			menu.setStatus("0");
			menu.setUpdatedTime(new Date());
			menuService.update(menu);
			logger.info("----------------------更新菜单详细信息：{}", menu);
		}
	}
	
	@Test
	public void updateMenuStatus(){
		String menuCode = "M001001";
		menuService.updateMenuStatus(menuCode, "0", "test");
		logger.info("----------------------更新编号为{}的菜单状态", menuCode);
	}
}
