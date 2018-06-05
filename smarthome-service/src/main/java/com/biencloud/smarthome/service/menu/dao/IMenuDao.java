package com.biencloud.smarthome.service.menu.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.menu.model.Menu;

/**
 * 菜单数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-4-19
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IMenuDao extends IDao<Menu, String> {
	
}
