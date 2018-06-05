package com.biencloud.smarthome.service.charge.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;

/**
 * 收费清单管理数据访问对象接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IChargeDetailDao extends IDao<ChargeDetail, String> {
	
}
