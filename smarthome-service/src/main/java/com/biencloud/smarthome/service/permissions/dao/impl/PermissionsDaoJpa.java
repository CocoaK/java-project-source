package com.biencloud.smarthome.service.permissions.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.permissions.dao.IPermissionsDao;
import com.biencloud.smarthome.service.permissions.model.Role;

/**
 * 菜单和权限模块数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-4-16
 */
public class PermissionsDaoJpa extends BaseDao<Role, String> implements IPermissionsDao {
	
}
