package com.biencloud.smarthome.service.charge;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;

public class ChargeDataServiceTest extends BaseTransactionalTest{
	@Test
	public void queryChargeDataForPaging(){
		Assert.assertNotNull(getChargeDataService());
		ChargeData paramsOb=new ChargeData();
		CellHouseholdInfo ob=new CellHouseholdInfo();
		ob.setName("02");
		paramsOb.setCellHouseholdInfo(ob);
		Paging<ChargeData> list=getChargeDataService().queryChargeDataForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	
	@Test
	public void statisticsCharge(){
		Assert.assertNotNull(getChargeDataService());
		ChargeData paramsOb=new ChargeData();
		List result=getChargeDataService().statisticsCharge(paramsOb);
		logger.info("result:{}",result.size());
	}
	
	@Test
	public void getChargeData(){
		Assert.assertNotNull(getChargeDataService());
		ChargeData list=getChargeDataService().getChargeData("1");
		logger.info("result:{}",list);
	}
	
	
	public IChargeDataService getChargeDataService() {
		return (IChargeDataService) this.getBean("chargeDataService");
	}
}
