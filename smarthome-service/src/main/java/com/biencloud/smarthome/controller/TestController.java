package com.biencloud.smarthome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.menu.service.IMenuService;


@Controller
@RequestMapping("/test")
public class TestController extends BaseController<Menu>{
		
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Menu  add(HttpServletRequest request,HttpServletResponse response) {
		Menu m = menuService.get("M001001");
		return m;
	}
	
	@RequestMapping(value="/query", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Paging<Menu>> query(Menu menu) {
		Paging<Menu> paging = menuService.queryMenusForPaging(menu.getMenuName(), true, null, 1, 10);
		return proccessResultEntity(ResultEntity.SUCCESS, ResultEntity.MESSAGE_SUCCESS, paging);
	}
	
	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Menu get(HttpServletRequest request,HttpServletResponse response,Menu menu) {
		menu = new Menu();
		menu.setMenuCode("M001001");
		System.out.println("------menu:"+menu);
		System.out.println("-------request:"+request.getParameterMap());
		Menu me = menuService.get(menu.getMenuCode());
		return me;
		//return proccessResultEntity(ResultEntity.SUCCESS, ResultEntity.MESSAGE_SUCCESS, paging);
	}

	@Override
	public IService<Menu, String> getBaseService() {
		return menuService;
	}
		
}
