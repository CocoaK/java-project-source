package com.biencloud.smarthome.service.gate.dao.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.gate.dao.IGateCardDao;
import com.biencloud.smarthome.service.gate.model.GateCard;

/**
 * 门禁管理数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IGateCardDao
 * @see BaseDao
 */
@Transactional(propagation=Propagation.REQUIRED)
public class GateCardDaoJpa extends BaseDao<GateCard, String> implements IGateCardDao {

	@Override
	public void removeGatePermissionsDevices(String deviceId) {
		if(deviceId == null)
			return;
		String queryString = "SELECT COUNT(gatePermissions) FROM GatePermissions gatePermissions WHERE device.deviceId=?1";
		Long count= super.getTotalCount(queryString, deviceId);
		//System.out.println("------------------count1:"+count);
		if(count<1)
			return;
		removeByParams("DELETE FROM GatePermissions gp WHERE gp.device.deviceId = ?1 ", deviceId);
	}

}
