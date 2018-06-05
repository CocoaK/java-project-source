package com.biencloud.smarthome.service.ad.dao.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.ad.dao.IAdDao;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.base.dao.impl.BaseDao;

/**
 * 广告投放管理数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseDao
 * @see IAdDao
 */
@Transactional(propagation=Propagation.REQUIRED)
public class AdDaoJpa extends BaseDao<Advertisement, String> implements IAdDao {

	@Override
	public void removeAdTargetDevices(String deviceId) {
		if(deviceId == null)
			return;
		String queryString = "SELECT COUNT(adTarget) FROM AdTarget adTarget WHERE device.deviceId=?1";
		Long count= super.getTotalCount(queryString, deviceId);
		//System.out.println("------------------count3:"+count);
		if(count<1)
			return;
		removeByParams("DELETE FROM AdTarget ad WHERE ad.device.deviceId = ?1 ", deviceId);
		
	}

}
