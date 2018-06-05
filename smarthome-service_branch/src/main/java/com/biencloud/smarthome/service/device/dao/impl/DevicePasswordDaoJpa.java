package com.biencloud.smarthome.service.device.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.device.dao.IDevicePasswordDao;
import com.biencloud.smarthome.service.device.model.DevicePassword;

/**
 * 类名称：DevicePasswordDaoJpa 
 * 类描述： 设备密码数据访问接口JPA实现
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-15 上午10:38:41
 */
public class DevicePasswordDaoJpa extends BaseDao<DevicePassword,Integer> implements IDevicePasswordDao{

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removePassword(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return;
		}
		String queryString = "SELECT COUNT(dp) FROM DevicePassword dp WHERE device.deviceId=?1 OR targetDevice.deviceId=?1";
		String removeString = "DELETE FROM DevicePassword WHERE device.deviceId=?1 OR targetDevice.deviceId=?1";
		Long count= super.getTotalCount(queryString, deviceId);
		if(count<1){
			return;
		}
		super.removeByParams(removeString, deviceId);
	}

}
