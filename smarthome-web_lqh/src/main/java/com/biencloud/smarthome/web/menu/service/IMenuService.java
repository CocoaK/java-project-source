package com.biencloud.smarthome.web.menu.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.menu.vo.MenuVO;

/**
 * 菜单管理模块调用服务接口。
 * @author kouy
 * @since 1.0 2012-4-26
 */
public interface IMenuService {

	/**
	 * 查询菜单列表，分页。
	 * @param menuName 菜单名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param hasParent 是否存在上级菜单
	 * @param status 菜单状态，如果为空不作为查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return 分页信息
	 */
	public PagingVO<MenuVO> queryMenusForPaging(String menuName, 
			boolean hasParent, String status, int pageNum, int pageSize);
	
	/**
	 * 获取菜单详细信息。
	 * @param menuCode 菜单代码
	 * @return
	 */
	public MenuVO getMenuDetail(String menuCode);
	
	/**
	 * 更新菜单信息。
	 * @param menuVO 菜单值对象
	 */
	public void updateMenu(MenuVO menuVO);
	
	/**
	 * 更新菜单状态。
	 * @param menuCode 菜单代码
	 * @param status 菜单状态
	 * @param updatedUser 操作人员登录名
	 */
	public void updateMenuStatus(String menuCode, 
			String status, String updatedUser);
}
