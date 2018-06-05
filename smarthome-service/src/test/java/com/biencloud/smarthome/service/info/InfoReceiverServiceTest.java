package com.biencloud.smarthome.service.info;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.service.IInfoReceiverService;

public class InfoReceiverServiceTest extends BaseTransactionalTest{
	@Test
	public void queryInfoReceiverForPaging(){
		Assert.assertNotNull(getInfoReceiverService());
		InfoReceiver paramsOb=new InfoReceiver();
		Paging<InfoReceiver> list=getInfoReceiverService().queryInfoReceiverForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	
	@Test
	public void queryInfoReceiverForList(){
		Assert.assertNotNull(getInfoReceiverService());
		InfoReceiver paramsOb=new InfoReceiver();
		List<InfoReceiver> list=getInfoReceiverService().queryInfoReceiverForList(paramsOb);
		logger.info("result:{}",list.size());
	}
	
	@Test
	public void getNoReadReceiverCount(){
		Assert.assertNotNull(getInfoReceiverService());
		InfoReceiver paramsOb=new InfoReceiver();
	    paramsOb.setReceiverType(InfoReceiver.INFO_TYPE_COMMUNITY);
	    paramsOb.setAreaId(new Long(1));
		Long result=getInfoReceiverService().getNoReadReceiverCount(paramsOb);
		logger.info("result:{}",result);
	}
	
	@Test
	public void saveInfoReceiver(){
		Assert.assertNotNull(getInfoReceiverService());
		InfoReceiver paramsOb=new InfoReceiver();
		getInfoReceiverService().saveInfoReceiver(paramsOb);
	}
	
	
	public IInfoReceiverService getInfoReceiverService() {
		return (IInfoReceiverService) this.getBean("infoReceiverService");
	}
}
