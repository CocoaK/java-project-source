package com.biencloud.smarthome.service.device.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.DevicePassword;

/**
 * 
 * 类名称：IDevicePasswordDao 
 * 类描述： 设备开锁密码数据访问接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午3:20:07
 */
public interface IDevicePasswordDao extends IDao<DevicePassword,Integer>{

	/**
	 * 方法的描述: 根据设备ID删除相关的设备密码
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-15 下午5:03:25
	 * @param deviceId
	 */
	public void removePassword(String deviceId);
}
