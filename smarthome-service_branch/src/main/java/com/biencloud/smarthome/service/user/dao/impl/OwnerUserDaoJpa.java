package com.biencloud.smarthome.service.user.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.user.dao.IOwnerUserDao;
import com.biencloud.smarthome.service.user.model.OwnerUser;

/**
 * 业主用户管理模块数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-18
 * @see BaseDao
 * @see IOwnerUserDao
 */
public class OwnerUserDaoJpa extends BaseDao<OwnerUser, String> implements IOwnerUserDao {

}
