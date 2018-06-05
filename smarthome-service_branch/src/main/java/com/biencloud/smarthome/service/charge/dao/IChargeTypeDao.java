package com.biencloud.smarthome.service.charge.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.charge.model.ChargeType;

/**
 * 收费项目数据访问对象接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IChargeTypeDao extends IDao<ChargeType, String> {
	
}
