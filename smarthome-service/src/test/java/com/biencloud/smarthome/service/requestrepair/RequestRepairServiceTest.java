package com.biencloud.smarthome.service.requestrepair;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;
import com.biencloud.smarthome.service.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.service.user.model.OwnerUser;

public class RequestRepairServiceTest extends BaseTransactionalTest{
	
	
	
	@Test
	public void queryRequestRepairForPaging(){
		Assert.assertNotNull(getRequestRepairService());
		RequestRepair paramsOb=new RequestRepair();
		paramsOb.setDistrictId(new Long(1));
		paramsOb.setRequestTime(new Date());
		Paging<RequestRepair> list=getRequestRepairService().queryRequestRepairForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list.getResultsSize());
	}
	
	@Test
	public void getRequestRepairCount(){
		Assert.assertNotNull(getRequestRepairService());
		RequestRepair paramsOb=new RequestRepair();
		OwnerUser ou=new OwnerUser();
		ou.setUserId("1");
		paramsOb.setOwnerUser(ou);
		Long result=getRequestRepairService().getRequestRepairCount(paramsOb);
		logger.info("result:{}",result);
	}
	
	
	public IRequestRepairService getRequestRepairService() {
		return (IRequestRepairService) this.getBean("requestRepairService");
	}
}
