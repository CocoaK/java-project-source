package com.biencloud.smarthome.service.sysgroup;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.sysgroup.service.ISystemGroupService;

public class SystemGroupServiceTest extends BaseTransactionalTest{
	
	
	@Test
	public void getCompletePosition(){
		Assert.assertNotNull(getSystemGroupService());
		String list=getSystemGroupService().getCompletePosition("12022178");
		logger.info("result:{}",list);
	}
	
	@Test
	public void getCityNameByDistrictId(){
		Assert.assertNotNull(getSystemGroupService());
		String list=getSystemGroupService().getCityNameByDistrictId("12022178");
		logger.info("result:{}",list);
	}
	
	@Test
	public void getDistrictIdByCityName(){
		Assert.assertNotNull(getSystemGroupService());
		List<String> list=getSystemGroupService().getDistrictIdByCityName("东莞");
		logger.info("result:{}",list.size());
	}
	
	public ISystemGroupService getSystemGroupService() {
		return (ISystemGroupService) this.getBean("systemGroupService");
	}
}
