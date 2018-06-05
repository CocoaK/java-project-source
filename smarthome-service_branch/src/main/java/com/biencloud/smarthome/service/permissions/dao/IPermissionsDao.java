package com.biencloud.smarthome.service.permissions.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.permissions.model.Role;

/**
 * 权限数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-4-16
 * @throws RuntimeException 如果操作执行失败
 */
public interface IPermissionsDao extends IDao<Role, String> {
	
}
