package com.biencloud.smarthome.service.log.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.log.dao.IOperationLogDao;
import com.biencloud.smarthome.service.log.dao.ISystemLogDao;
import com.biencloud.smarthome.service.log.model.OperationLog;

/**
 * 日志模块数据访问对象，基于JPA实现。
 * @author 
 * @since 
 * @see ISystemLogDao
 */
public class OperationLogDaoJpa extends BaseDao<OperationLog,String> implements IOperationLogDao{

}