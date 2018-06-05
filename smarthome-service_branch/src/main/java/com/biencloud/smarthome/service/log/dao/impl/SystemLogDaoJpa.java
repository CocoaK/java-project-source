package com.biencloud.smarthome.service.log.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.log.dao.ISystemLogDao;
import com.biencloud.smarthome.service.log.model.SystemLog;

/**
 * 日志模块数据访问对象，基于JPA实现。
 * @author 
 * @since 
 * @see ISystemLogDao
 */
public class SystemLogDaoJpa extends BaseDao<SystemLog,String> implements ISystemLogDao{

}