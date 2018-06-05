package com.biencloud.smarthome.service.deviceno;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.deivceno.service.IDeviceNoService;
import com.biencloud.smarthome.service.device.model.Device;

public class DeviceNoServiceTest extends BaseTransactionalTest{
	/**
	 * 查询设备编号列表。
	 * @param device 设备编号对象 
	 * @return
	 */
	@Test
	public void queryDeviceForPaging(){
		Assert.assertNotNull(getDeviceNoService());
		Device de=new Device();
		DeviceNo paramsOb=new DeviceNo();
//		de.setDeviceName("deviceName");
//		de.setAreaId("1");
		paramsOb.setDeviceNo("111");
		paramsOb.setDevice(de);
		//Paging<DeviceNo> list=getDeviceNoService().queryDeviceForPaging(paramsOb, 1, 20);
		//logger.info("result:{}",list);
	}
	
	/**
	 * 后台自动生成设备编号列表。
	 * @return
	 */
	@Test
	public void generatorDeviceNo(){
		//logger.info("结果：{}",getDeviceNoService().generatorDeviceNo());
	}
	
	/**
	 * 更新设备编号的设备ID。
	 * @return
	 */
	@Test
	public void updateDeviceId(){
		Device ob=new Device();
		ob.setDeviceId("2");
		//logger.info("结果：{}",getDeviceNoService().updateDeviceId(ob, "g20120511165454I"));
		
	}
	
	public IDeviceNoService getDeviceNoService() {
		return (IDeviceNoService) this.getBean("deviceNoService");
	}
}
