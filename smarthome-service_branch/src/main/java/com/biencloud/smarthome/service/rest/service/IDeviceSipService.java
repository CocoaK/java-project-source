package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.DeviceSip;

public interface IDeviceSipService extends IBaseResService<DeviceSip>{
	
	List<DeviceSip> queryList(DeviceSip deviceSip);
	
	ResultEntity<String> delete(DeviceSip deviceSip);
	
	ResultEntity<String> deleteByDeviceId(String deviceId);
	
}
