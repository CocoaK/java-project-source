package com.biencloud.smarthome.service.charge;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.charge.model.ChargeCalMode;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.service.common.model.Paging;

public class ChargeCalModeServiceTest extends BaseTransactionalTest{
	@Test
	public void queryChargeCalModeForPaging(){
		Assert.assertNotNull(getChargeCalModeService());
		ChargeCalMode paramsOb=new ChargeCalMode();
		paramsOb.setName("平米");
		Paging<ChargeCalMode> list=getChargeCalModeService().queryChargeCalModeForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	
	@Test
	public void queryChargeCalModeForList(){
		Assert.assertNotNull(getChargeCalModeService());
		ChargeCalMode paramsOb=new ChargeCalMode();
		paramsOb.setName("平米");
		List<ChargeCalMode> list=getChargeCalModeService().queryChargeCalModeForList(paramsOb);
		logger.info("result:{}",list.size());
	}
	
	@Test
	public void delChargeMonetaryUnit(){
		Assert.assertNotNull(getChargeCalModeService());
		ChargeCalMode paramsOb=new ChargeCalMode();
		paramsOb.setId(new Long(3));
		try {
			getChargeCalModeService().DelChargeCalMode(paramsOb);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("抓住了"+e.getMessage()+e.getCause()+e.getLocalizedMessage());
		}
	}
	
	
	public IChargeCalModeService getChargeCalModeService() {
		return (IChargeCalModeService) this.getBean("chargeCalModeService");
	}
}
