package com.biencloud.smarthome.web.menu.action;

import java.util.Date;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.service.IMenuService;
import com.biencloud.smarthome.web.menu.vo.MenuVO;

/**
 * 菜单管理模块动作类。
 * @author kouy
 * @since 1.0 2012-4-21
 */
@SuppressWarnings("serial")
public class MenuAction extends BaseAction<MenuVO> {

	private String menuCode;
	private String menuName;
	private String status;
	
	private boolean promptFlag;
	
	private MenuVO menu;
	
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}
	
	public MenuVO getMenu() {
		return menu;
	}
	public void setMenu(MenuVO menu) {
		this.menu = menu;
	}
	
	
	/**
	 * 查看指定菜单的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setMenu(getMenuService().getMenuDetail(menuCode));
		return SUCCESS;
	}
	
	/**
	 * 查询菜单列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<MenuVO> page = getPage();
		if(page == null)
			page = new PagingVO<MenuVO>();
		
		String menuName = null;
		String status = null;
		if(menu != null){
			menuName = menu.getMenuName();
			status = menu.getStatus();
		}
		
		PagingVO<MenuVO> pageVO = getMenuService().queryMenusForPaging(
				menuName, true, status, page.getPageNum(), page.getPageSize());
		
		setPage(pageVO);
		
		return SUCCESS;
	}
	
	/**
	 * 获取菜单信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setMenu(getMenuService().getMenuDetail(menuCode));
		return SUCCESS;
	}
	
	/**
	 * 更新菜单信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		menu.setUpdatedUser(getLoginName());
		menu.setUpdatedTime(new Date());
		getMenuService().updateMenu(menu);
		menuCode = menu.getMenuCode();
		setPromptFlag(true);
		return updateInput();
	}
	
	/**
	 * 隐藏或显示菜单。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String hideOrDisplay() throws Exception {
		getMenuService().updateMenuStatus(menuCode, status, getLoginName());
		menuName = null;
		status = null;
		setPromptFlag(true);
		return queryList();
	}
	
	private IMenuService menuService;
	
	public IMenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
}
