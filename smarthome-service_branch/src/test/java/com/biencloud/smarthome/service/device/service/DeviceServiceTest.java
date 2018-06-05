package com.biencloud.smarthome.service.device.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

public class DeviceServiceTest extends BaseTransactionalTest{
	
	@Autowired
	private IDeviceService idService;
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateDevice(){
		Device _device = new Device();
		BuildingCellInfo bc = new BuildingCellInfo();
		bc.setCode("01");
		bc.setId("1");
		DeviceType deviceType = new DeviceType();
		deviceType.setDeviceType("01");
		DeviceIp deviceIp = new DeviceIp();
		deviceIp.setIpId("1");
		deviceIp.setIpAddress("192.168.1.111");
		deviceIp.setStatus("1");
		deviceType.setDeviceName("111");
		_device.setCreatedTime(new Date());  	//update false
		_device.setCreatedUser("cocoa");	//update false
		_device.setDeviceAlias("AK49");
		_device.setDeviceCode("MC000003");		//update false
		_device.setDeviceId("38");		//auto increment
		_device.setDeviceName("新媒体");
		_device.setDevicePwd("123456");
		_device.setDeviceStatus("0");
		_device.setDeviceType(deviceType);		//update false
		_device.setDistrictGateFlag("0");	//update false
		_device.setDeviceMac("11-11-22-22-33-33");
		_device.setUpdatedTime(new Date());
		_device.setUpdatedUser("cocoa2");
		_device.setBuildingCellInfo(bc);
		
		logger.info("设备更新：{}",idService.updateDevice(_device,"deviceName"));
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void saveDevice(){
		Device _device = new Device();
		BuildingCellInfo bc = new BuildingCellInfo();
		Set<DeviceIp> deviceIps = new HashSet<DeviceIp>();
		DeviceIp deviceIp1 = new DeviceIp();
		DeviceIp deviceIp2 = new DeviceIp();
		deviceIp1.setIpId("1");
		deviceIp2.setIpId("2");
		deviceIp1.setIpAddress("192.168.1.11");
		deviceIps.add(deviceIp1);
		deviceIps.add(deviceIp2);
		bc.setCode("01");
		bc.setId("1");
		DeviceType deviceType = new DeviceType();
		deviceType.setDeviceType("03");
		deviceType.setDeviceName("111");
		_device.setCreatedTime(new Date());  	//update false
		_device.setCreatedUser("cocoa");	//update false
		_device.setDeviceAlias("AK49");
		_device.setDeviceCode("MC000003---");		//update false
		//_device.setDeviceId("20");		//auto increment
		_device.setDeviceName("新媒体");
		_device.setDevicePwd("123456");
		_device.setDeviceStatus("0");
		_device.setDeviceType(deviceType);		//update false
		_device.setDistrictGateFlag("0");	//update false
		_device.setDeviceMac("11-11-22-22-33-33");
		_device.setUpdatedTime(new Date());
		_device.setUpdatedUser("cocoa2");
		//_device.setBuildingCellInfo(bc);
		_device.setDeviceBgUrl("/background/device_bg.jpg");
		_device.setDeviceRingUrl("/sound/device_ring.wav");
		_device.setLoginUuserId("12121");
		//_device = idService.queryDeviceById("7");
		//_device.setDeviceId(null);
		_device.setCellHouseholdInfo(null);
		_device.setHousingDistrictRegionInfo(null);
		_device.setRegionBuildingInfo(null);
		_device.setBuildingCellInfo(bc);
		logger.info("设备保存：{}",idService.saveDevice(_device));
	}

	//@Test
		public void queryDeviceForPaging(){
			Device _device = new Device();
			BuildingCellInfo bc = new BuildingCellInfo();
			bc.setCode("01");
			bc.setId("1");
			DeviceType deviceType = new DeviceType();
			deviceType.setDeviceType("01");
			deviceType.setDeviceName("111");
			_device.setCreatedTime(new Date());  	//update false
			_device.setCreatedUser("cocoa");	//update false
			_device.setDeviceAlias("AK49");
			_device.setDeviceCode("MC000003---");		//update false
			_device.setDeviceId("20");		//auto increment
			_device.setDeviceName("新媒体");
			_device.setDevicePwd("123456");
			_device.setDeviceStatus("0");
			_device.setDeviceType(deviceType);		//update false
			_device.setDistrictGateFlag("0");	//update false
			_device.setDeviceMac("11-11-22-22-33-33");
			_device.setUpdatedTime(new Date());
			_device.setUpdatedUser("cocoa2");
			_device.setBuildingCellInfo(bc);
			_device.setDeviceBgUrl("/background/device_bg.jpg");
			//_device.setDeviceIp("192.168.1.137");
			_device.setDeviceRingUrl("/sound/device_ring.wav");
			_device.setLoginUuserId("12121");
			logger.info("查询分页：{}",idService.queryDeviceForPaging(_device, 1, 10));
		}
	@Test
	public void queryById(){
		logger.info("根据Id查询：{}",idService.queryDeviceById("6"));
	}
	@Test
	public void queryPropertyDevice(){
		logger.info("查询分页：{}",idService.queryPropertyDevice("1"));
	}
	
	@Test
	public void queryOwnerUnitDevice(){
		logger.info("查询设备：{}",idService.queryOwnerUnitDevice("1","1").size());
	}
	@Test
	public void validatePasswd(){
		logger.info("校验密码：{}",idService.validatePasswd("MC00011", "ISGMyneATSuhkiwz4BURBQ=="));
	}
	
	@Test
	public void queryList(){
		List<String> districtIds = new ArrayList<String>();
		List<String> deviceTypes = new ArrayList<String>();
		deviceTypes.add("01");
		districtIds.add("1");
		districtIds.add("3");
		
		logger.info("查询设备:{}",idService.queryDevices(districtIds,null));
	}
	
	@Test
	public void queryDeviceByRoomId(){
		logger.info("查询设备:{}",idService.queryDeviceByRoomId("1"));
	}
	
	@Test
	public void queryFamilyDevice(){
		Device device = new Device();
		BuildingCellInfo buildingCellInfo = new BuildingCellInfo();
		HousingDistrictInfo hdi = new HousingDistrictInfo();
		hdi.setId("1");
		buildingCellInfo.setId("1");
		//device.setDeviceCode("MC00015");
		DeviceType type = new DeviceType();
		type.setDeviceType("02");
		
		device.setDeviceType(type);
		device.setBuildingCellInfo(buildingCellInfo);
		device.setHousingDistrictInfo(hdi);
		List<Device> list = idService.queryFamilyDevice(device);
		for(Device d : list){
			System.out.println("roomNo:"+d.getCellHouseholdInfo().getId()+" ip:"+d.getDeviceIp()+" time:"+d.getCreatedTime());
		}
		///logger.info("查询设备:{}",idService.queryFamilyDevice(device));
	}
	
	@Test
	public void queryMonitorDevice(){
		Device device = new Device();
		device = idService.queryDeviceByCode("MC00011");
		logger.info("查询设备:{}",idService.queryMonitorDevice(device));
	}
	
	@Test
	public void isHaveDevice(){
		
		logger.info("是否有设备:{}",idService.isHaveDevice("2"));
	}
	
	@Test
	public void deleteById(){
		idService.deleteDeviceById("1");
	}
	
	@Test
	public void queryGateCardDevices(){
		Device device = new Device();
		HousingDistrictInfo hdi = new HousingDistrictInfo();
		hdi.setId("1");
		device.setHousingDistrictInfo(hdi);
		logger.info("查询设备：{}",idService.queryGateCardDevices(device));
	}
	
	@Test
	public void isDeviceOnline(){
		logger.info("设备是否在线：{}",idService.isDeviceOnline("8"));
	}
}
