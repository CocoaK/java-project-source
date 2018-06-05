package com.biencloud.smarthome.service.device.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.CallRecord;

public interface ICallRecordDao extends IDao<CallRecord,String>{
	
	/**
	 * 
	 * 方法的描述: 根据设备Id删除
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-6 下午3:59:56
	 * @param deviceId
	 */
	public void removeByDeviceId(String deviceId);
	
}
