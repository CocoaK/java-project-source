package com.biencloud.smarthome.service.devicetype.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;

/**
 * 设备管理服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-5-7
 */
public class DeviceServiceTest extends BaseTest {

	@Autowired
	private IDeviceService deviceService;
	
	@Test
	public void queryDevices(){
		Device device = new Device();
		List<Device> list = deviceService.queryDevices(device);
		logger.info("--------------返回无查询条件的设备列表：{}", list);
		
		//device.setAreaName("1区");
		//device.setBuildingName("A栋");
		//device.setUnitName("1单元");
		list = deviceService.queryDevices(device);
		logger.info("--------------返回查询条件的设备列表：{}", list);
	}
}
