package com.biencloud.smarthome.service.charge;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.charge.service.IChargeTypeService;
import com.biencloud.smarthome.service.common.model.Paging;

public class ChargeTypeServiceTest extends BaseTransactionalTest{
	@Test
	public void queryChargeTypeForPaging(){
		Assert.assertNotNull(getChargeTypeService());
		ChargeType paramsOb=new ChargeType();
		paramsOb.setName("水");
		Paging<ChargeType> list=getChargeTypeService().queryChargeTypeForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	
	@Test
	public void getChargeType(){
		Assert.assertNotNull(getChargeTypeService());
		ChargeType list=getChargeTypeService().getChargeType("1");
		logger.info("result:{}",list);
	}
	@Test
	public void queryChargeTypeForList(){
		Assert.assertNotNull(getChargeTypeService());
		ChargeType paramsOb=new ChargeType();
		paramsOb.setRemark("1");
		List<ChargeType> list=getChargeTypeService().queryChargeTypeForList(paramsOb);
		logger.info("result:{}",list);
	}
	
	
	public IChargeTypeService getChargeTypeService() {
		return (IChargeTypeService) this.getBean("chargeTypeService");
	}
}
