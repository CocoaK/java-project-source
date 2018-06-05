package com.biencloud.smarthome.web.device;

import java.util.Date;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.push.service.IPushFinishService;
import com.biencloud.smarthome.web.wsclient.stub.Device;
public class DeviceTest extends BaseTest{
	public IDeviceService deviceService;
	
   public IDeviceService getDeviceService() {
		return (IDeviceService)this.getBean("deviceService");
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Test
   public void query()
   {
	System.out.println("-----deivce:"+getDeviceService().queryDeviceVOById("6"));
   }
	@Test
	public void update()
	{
		DeviceVO deviceVO = new DeviceVO();
		deviceVO = getDeviceService().queryDeviceVOById("6");
		deviceVO.setDeviceName("门口机3");
		deviceVO.setDeviceAlias("门口机test1");
		deviceVO.setUpdatedTime(new Date());
		System.out.println("-----1getHousingDistrictInfo:"+deviceVO.getHousingDistrictInfo());
		System.out.println("-----1getHousingDistrictRegionInfo:"+deviceVO.getHousingDistrictRegionInfo());
		System.out.println("-----1getRegionBuildingInfo:"+deviceVO.getRegionBuildingInfo());
		System.out.println("-----1getBuildingCellInfo:"+deviceVO.getBuildingCellInfo());
		System.out.println("-----1getBuildingCellInfo:"+deviceVO.getBuildingCellInfo());
		System.out.println("-----1deviceVO:"+getDeviceService().update(deviceVO,"deviceName"));
	}
	
	@Test
	   public void queryOwnerUnitDevice(){
		System.out.println("-----deivce:"+getDeviceService().queryOwnerUnitDevice("1", "1"));
	   }
	
	@Test
	   public void queryPropertyDevice(){
		System.out.println("-----deivce:"+getDeviceService().queryPropertyDevice("1"));
	   }
	
	@Test
	   public void queryDevices(){
		System.out.println("-----deivce:"+getDeviceService().queryDevices(null, null).size());
	   }
}
