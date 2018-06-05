package com.biencloud.smarthome.service.device.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.DeviceIp;

public class DeviceIpServiceTest extends BaseTest{
	
	@Autowired
	private IDeviceIpService ipService;
	
	//@Test
	public void query(){
		DeviceIp ip = new DeviceIp();
		ip.setIpAddress("192.168.1.138");
	}
	
	//@Test
	public void save(){
		DeviceIp ip = new DeviceIp();
		ip.setIpAddress("192.168.1.137");
		//ipService.save(ip);
	}
	//@Test
	public void query2(){
		DeviceIp ip = new DeviceIp();
		ip.setIpAddress("192.168.1.137");
		ipService.save(ip);
		System.out.println("------------------------save:" +ipService.queryDeviceIpByIp(ip.getIpAddress(),"1"));

	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void saveIps(){
		DeviceIp ip = new DeviceIp();
		ip.setIpAddress("192.168.3.0");
		ip.setSubnet("1");
		System.out.println("------------------------saveIps:" +ipService.saveSubnetIps(ip));
	}
	
	@Test
	@Transactional(propagation=Propagation.NEVER)
	public void clearDeviceId(){
		ipService.clearDeviceId("6");
	}
}
