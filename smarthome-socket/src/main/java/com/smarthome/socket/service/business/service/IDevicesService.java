package com.smarthome.socket.service.business.service;

import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.vo.Device;

public interface IDevicesService extends IBaseRestService<Device>{
		
	ResultEntity<String> updateAllDeviceStatus(String status);
	
	ResultEntity<Device> getById(String deviceId);

	/**
	 * @desc   @param deviceNo
	 * @desc   @return
	 * @return ResultEntity<Device>
	 * @param deviceNo
	 * @return
	 */
	ResultEntity<Device> getByDeviceNo(String deviceNo);
	
}
