package com.biencloud.smarthome.service.requestrepair.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;

/**
 * 报修数据访问对象接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IRequestRepairDao extends IDao<RequestRepair, String> {
	
}
