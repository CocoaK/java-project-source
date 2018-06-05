package com.biencloud.smarthome.service.charge;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;
import com.biencloud.smarthome.service.charge.service.IChargeDetailService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.user.model.OwnerUser;

public class ChargeDetailServiceTest extends BaseTransactionalTest{
	@Test
	public void queryChargeDetailForPaging(){
		Assert.assertNotNull(getChargeDetailService());
		ChargeDetail paramsOb=new ChargeDetail();
		ChargeData cdOb=new ChargeData();
		CellHouseholdInfo chiOb=new CellHouseholdInfo();
		OwnerUser ouOb=new OwnerUser();
		ouOb.setUserId("2");
		chiOb.setOwner(ouOb);
		cdOb.setCellHouseholdInfo(chiOb);
		paramsOb.setChargeData(cdOb);
		Paging<ChargeDetail> list=getChargeDetailService().queryChargeDetailForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	} 
	
	@Test
	public void getChargeDetail(){
		Assert.assertNotNull(getChargeDetailService());
		ChargeDetail list=getChargeDetailService().getChargeDetail("1");
		logger.info("result:{}",list);
	}
	
	@Test
	public void delChargeDetail(){
		Assert.assertNotNull(getChargeDetailService());
		ChargeDetail ob=getChargeDetailService().getChargeDetail("9");
		getChargeDetailService().DelChargeDetail(ob);
	}
	
	
	public IChargeDetailService getChargeDetailService() {
		return (IChargeDetailService) this.getBean("chargeDetailService");
	}
}
