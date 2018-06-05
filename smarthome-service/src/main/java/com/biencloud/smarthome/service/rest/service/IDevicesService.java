package com.biencloud.smarthome.service.rest.service;


import java.util.List;

import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceJson;

public interface IDevicesService extends IBaseResService<Device>{
	
	ResultEntity<String> verifyDevice(Long deviceId,String password);
	
	ResultEntity<Long> getIdByDeviceNo(String deviceNo);
	
	ResultEntity<Device> addForEntity(Device device);
	
	ResultEntity<AppLoginVO> login(AppLoginVO appLoginVO);
	
	ResultEntity<String> updateByDeviceNo(Device device);
	
	ResultEntity<String> updateAllDeviceStatus(String status);

	Device getByDeviceNo(String deviceNo);

	/**
	 * @desc   @param device
	 * @desc   @return
	 * @return ResultEntity<List<DeviceJson>>
	 * @param device
	 * @return
	 */
	List<DeviceJson> getDeviceJosn(Device device);
}
