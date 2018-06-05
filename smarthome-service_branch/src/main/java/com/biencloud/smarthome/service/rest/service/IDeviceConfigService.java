package com.biencloud.smarthome.service.rest.service;


import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.DeviceConfig;

public interface IDeviceConfigService extends IBaseResService<DeviceConfig>{
	
	ResultEntity<String> addForEntity(DeviceConfig deviceConfig);
	
	ResultEntity<DeviceConfig> getByDeviceId(Long deviceId);
	
	ResultEntity<String> updateAndSave(DeviceConfig deviceConfig);
	
}
