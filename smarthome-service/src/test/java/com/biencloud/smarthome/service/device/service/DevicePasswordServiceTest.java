package com.biencloud.smarthome.service.device.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DevicePassword;


public class DevicePasswordServiceTest extends BaseTest{
	
	@Autowired
	private IDevicePasswordService idevicePasswordService;
	
	public IDevicePasswordService getIdevicePasswordService() {
		return idevicePasswordService;
	}

	public void setIdevicePasswordService(IDevicePasswordService idevicePasswordService) {
		this.idevicePasswordService = idevicePasswordService;
	}

	@Test
	public void queryById(){
		logger.info("查询密码:{}",idevicePasswordService.get(new Long(1)));
	}
	
	@Test
	public void queryByDeviceNo(){
		logger.info("查询密码:{}",idevicePasswordService.queryDevicePassword("MC00010"));
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void save(){
		DevicePassword devicePassword = new DevicePassword();
		devicePassword.setPassword("121212");
		Device device = new Device();
		device.setDeviceId("18");
		devicePassword.setDevice(device);
		idevicePasswordService.saveAndPushDevicePassword(devicePassword);
	}
	
}
