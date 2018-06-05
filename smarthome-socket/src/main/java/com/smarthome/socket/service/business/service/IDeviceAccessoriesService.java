package com.smarthome.socket.service.business.service;

import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.vo.DeviceAccessories;

public interface IDeviceAccessoriesService extends IBaseRestService<DeviceAccessories>{
	
	ResultEntity<String> save(String data,String mac);
	
	ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception;

	ResultEntity<String> refresh(String data, String mac);
	
	ResultEntity<String> clear(String mac);
	
}
