package com.biencloud.smarthome.service.device.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.device.model.DeviceType;

/**
 * 设备类型领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-3
 * @see IService
 */
public interface IDeviceTypeService extends IService<DeviceType, String> {

	/**
	 * 方法的描述: 查询所有的设备类型。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 下午2:04:39
	 * @return
	 */
	public List<DeviceType> queryAllDeviceTypes();
}
