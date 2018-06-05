package com.biencloud.smarthome.service.rest.service;


import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Device;

public interface IDevicesService extends IBaseResService<Device>{
	
	ResultEntity<String> verifyDevice(Long deviceId,String password);
	
	ResultEntity<Long> getIdByDeviceNo(String deviceNo);
	
	ResultEntity<Device> addForEntity(Device device);
	
	ResultEntity<AppLoginVO> login(AppLoginVO appLoginVO);
}
