package com.biencloud.smarthome.service.device.service.impl;

import java.util.List;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.device.service.IDeviceTypeService;

/**
 * 设备类型领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-3
 * @see IDeviceTypeService
 * @see BaseService
 */
public class DeviceTypeServiceImpl extends BaseService<DeviceType, String> implements
		IDeviceTypeService {

	@Override
	public List<DeviceType> queryAllDeviceTypes() {
		return find("SELECT deviceType FROM DeviceType deviceType");
	}
}
