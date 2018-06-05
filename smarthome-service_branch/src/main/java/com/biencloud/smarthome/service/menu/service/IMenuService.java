package com.biencloud.smarthome.service.menu.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.menu.model.Menu;

/**
 * 菜单模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-19
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IMenuService extends IService<Menu, String> {

	/**
	 * 查询菜单列表，分页。
	 * @param menuName 菜单名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param hasParent 是否存在上级菜单
	 * @param status 菜单状态，如果为空不作为查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return 分页信息
	 */
	public Paging<Menu> queryMenusForPaging(String menuName, 
			boolean hasParent, String status, int pageNum, int pageSize);
	
	/**
	 * 更新菜单状态。
	 * @param menuCode 菜单代码
	 * @param status 菜单状态
	 * @param updatedUser 操作人员登录名
	 */
	public void updateMenuStatus(String menuCode, 
			String status, String updatedUser);
	
	
	/**
	 * 通过父菜单代码获取所有下级菜单。
	 * @param parentCode 父菜单代码
	 * @return
	 */
	public List<Menu> queryMenusByParentCode(String parentCode);
}
