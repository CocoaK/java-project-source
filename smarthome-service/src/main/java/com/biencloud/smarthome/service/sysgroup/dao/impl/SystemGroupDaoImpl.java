package com.biencloud.smarthome.service.sysgroup.dao.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.sysgroup.dao.ISystemGroupDao;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：SystemGroupDaoImpl 
 * 类描述： 组织模块领域服务实现类。
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-11-28 下午3:00:27
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class SystemGroupDaoImpl extends BaseDao<SystemGroup, Long> implements ISystemGroupDao {


}
