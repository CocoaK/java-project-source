package com.biencloud.smarthome.web.menu.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.service.IMenuService;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.wsclient.stub.Menu;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 菜单管理服务实现。
 * @author kouy
 * @since 1.0 2012-4-26
 * @see IMenuService
 */
public class MenuServiceImpl extends BaseService<MenuVO> implements IMenuService {
	
	private static final String UPDATED_TIME = "updatedTime";


	@Override
	public PagingVO<MenuVO> queryMenusForPaging(String menuName,
			boolean hasParent, String status, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().queryMenusForPaging(
				menuName, hasParent, status, pageNum, pageSize);		
		PagingVO<MenuVO> pagingVO = convertToPagingVO(paging, UPDATED_TIME);		
		return pagingVO;
	}
	
	@Override
	public MenuVO getMenuDetail(String menuCode) {
		MenuVO menuVO = new MenuVO();
		copyProperties(getSmartHomeService().getMenuDetail(menuCode), 
				menuVO, true, UPDATED_TIME);
		return menuVO;
	}

	
	@Override
	public void updateMenu(MenuVO menuVO) {
		Menu menu = new Menu();		
		copyProperties(menuVO, menu, false, UPDATED_TIME);
		getSmartHomeService().updateMenu(menu);
	}

	
	@Override
	public void updateMenuStatus(String menuCode, String status,
			String updatedUser) {
		getSmartHomeService().updateMenuStatus(
				menuCode, status, updatedUser);
	}
}
