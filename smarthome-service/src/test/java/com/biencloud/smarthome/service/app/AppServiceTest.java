package com.biencloud.smarthome.service.app;

import java.util.Date;

import org.junit.Test;

import com.biencloud.smarthome.service.app.service.IAppService;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;

public class AppServiceTest extends BaseTransactionalTest{
	public IAppService getAppService() {
		return (IAppService) this.getBean("appService");
	}
	@Test
	public void insertPush() {
			Device device = new Device();
			device.setDeviceMac("x01");
			device.setDeviceIp("127.0.0.1");
			device.setDeviceAlias("test");
			device.setDeviceName("test1");
			device.setDeviceStatus("0");
			device.setDeviceCode("S20120531215208B");
			device.setDevicePwd("123456");
			DeviceType deviceType=new DeviceType();
			deviceType.setDeviceType("03");
			device.setDeviceType(deviceType);
			HousingDistrictInfo housingDistrictInfo=new HousingDistrictInfo();
			housingDistrictInfo.setCode("0004");
			device.setHousingDistrictInfo(housingDistrictInfo);
			//getAppService().saveOrUpdateDevice(device);
		}
}
