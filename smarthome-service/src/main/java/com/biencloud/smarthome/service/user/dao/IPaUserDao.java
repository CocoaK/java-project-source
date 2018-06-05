package com.biencloud.smarthome.service.user.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 物业管理用户数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IPaUserDao extends IDao<PaUser, String> {

}
