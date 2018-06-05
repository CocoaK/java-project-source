package com.biencloud.smarthome.service.login.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.login.dao.ILoginDao;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 登录模块数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-11
 * @see BaseDao
 * @see ILoginDao
 */
public class LoginDaoJpa extends BaseDao<Login, String> implements ILoginDao {

}
