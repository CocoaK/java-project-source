package com.biencloud.smarthome.service.devicetype.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.device.service.IDeviceTypeService;

/**
 * 设备类型管理服务测试类。
 * @author kouy
 * @since 1.0 2012-5-3
 */
public class DeviceTypeServiceTest extends BaseTest {

	@Autowired
	private IDeviceTypeService deviceTypeService;
	
	@Test
	public void queryAllDeviceTypes(){
		List<DeviceType> list = deviceTypeService.queryAllDeviceTypes();
		logger.info("--------------返回所有设备类型信息：{}", list);
	}
}
