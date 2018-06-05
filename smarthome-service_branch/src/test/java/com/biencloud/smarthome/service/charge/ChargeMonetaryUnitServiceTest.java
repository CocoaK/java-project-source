package com.biencloud.smarthome.service.charge;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.device.model.Device;

public class ChargeMonetaryUnitServiceTest extends BaseTransactionalTest{
	@Test
	public void queryChargeMonetaryUnitForPaging(){
		Assert.assertNotNull(getChargeMonetaryUnitService());
		ChargeMonetaryUnit paramsOb=new ChargeMonetaryUnit();
		paramsOb.setCodeName("人民");
		Paging<ChargeMonetaryUnit> list=getChargeMonetaryUnitService().queryChargeMonetaryUnitForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	@Test
	public void delChargeMonetaryUnit(){
		Assert.assertNotNull(getChargeMonetaryUnitService());
		ChargeMonetaryUnit paramsOb=new ChargeMonetaryUnit();
		paramsOb.setId(new Long(1));
		try {
			getChargeMonetaryUnitService().DelChargeMonetaryUnit(paramsOb);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("抓住了"+e.getMessage()+e.getCause()+e.getLocalizedMessage());
		}
	}
	
	public IChargeMonetaryUnitService getChargeMonetaryUnitService() {
		return (IChargeMonetaryUnitService) this.getBean("chargeMonetaryUnitService");
	}
}
