package com.biencloud.smarthome.service.user.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.user.dao.IPaUserDao;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 物业管理用户模块数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-12
 * @see BaseDao
 * @see IPaUserDao
 */
public class PaUserDaoJpa extends BaseDao<PaUser, String> implements IPaUserDao {

}
