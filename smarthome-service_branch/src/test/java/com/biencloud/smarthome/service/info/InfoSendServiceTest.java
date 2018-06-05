package com.biencloud.smarthome.service.info;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.service.IInfoSendService;

public class InfoSendServiceTest extends BaseTransactionalTest{
	@Test
	public void queryInfoSendForPaging(){
		Assert.assertNotNull(getInfoSendService());
		InfoSend paramsOb=new InfoSend();
		Paging<InfoSend> list=getInfoSendService().queryInfoSendForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list);
	}
	
	@Test
	public void queryInfoSendForList(){
		Assert.assertNotNull(getInfoSendService());
		InfoSend paramsOb=new InfoSend();
		List<InfoSend> list=getInfoSendService().queryInfoSendForList(paramsOb);
		logger.info("result:{}",list.size());
	}
	
	@Test
	public void DelInfoSend(){
		Assert.assertNotNull(getInfoSendService());
		InfoSend paramsOb=getInfoSendService().getInfoSend("2");
		getInfoSendService().DelInfoSend(paramsOb);
	}
	
	@Test
	public void sendTimingInfo(){
		Assert.assertNotNull(getInfoSendService());
		getInfoSendService().sendTimingInfo();
	}
	
	@Test
	public void getInfoCount(){
		Assert.assertNotNull(getInfoSendService());
	    InfoSend paramsOb=new InfoSend();
	    paramsOb.setType(InfoReceiver.INFO_TYPE_SYSTEM);
	    paramsOb.setSendUserId(new Long(1));
		Long result=getInfoSendService().getInfoCount(paramsOb);
		logger.info("result:{}",result);
	}
	
	@Test
	public void queryInfoSendForIndex(){
		Assert.assertNotNull(getInfoSendService());
	    InfoSend paramsOb=new InfoSend();
	   /* paramsOb.setType(InfoReceiver.INFO_TYPE_COMMUNITY);
	    paramsOb.setAreaId(new Long(1));*/
	   /* paramsOb.setType(InfoReceiver.INFO_TYPE_PERSON);
	    paramsOb.setSendUserId(new Long(1));*/
	    paramsOb.setType(InfoReceiver.INFO_TYPE_SYSTEM);
	    List<InfoSend> result=getInfoSendService().queryInfoSendForIndex(paramsOb);
		logger.info("result:{}",result.size());
	}
	
	@Test
	public void queryAreaData(){
		Assert.assertNotNull(getInfoSendService());
		InfoSend paramsOb=new InfoSend();
		paramsOb.setAreaId(new Long(1));
		List result=getInfoSendService().queryAreaData(paramsOb);
		logger.info("result:{}",result.size());
	}
	
	@Test
	public void saveInfoSend(){
		Assert.assertNotNull(getInfoSendService());
		InfoSend paramsOb=new InfoSend();
		paramsOb.setTitle("测试标题");
		paramsOb.setContent("测试内容");
		paramsOb.setSendMode(0);
		paramsOb.setSendOb("广东");
		paramsOb.setStatus(0);
		paramsOb.setSendTime(new Timestamp(System.currentTimeMillis()));
		paramsOb.setSendUserName("浮云哥");
		Set<InfoReceiver> set=new HashSet<InfoReceiver>();
		InfoReceiver info=new InfoReceiver();
		info.setAreaId(new Long(1));
		info.setReceviceUserName("神马哥");
		info.setStatus(0);
		set.add(info);
		InfoReceiver info2=new InfoReceiver();
		info2.setAreaId(new Long(1));
		info2.setReceviceUserName("神马哥");
		info2.setStatus(0);
		set.add(info2);
		paramsOb.setInfoReceivers(set);
		//getInfoSendService().saveInfoSend(paramsOb);
	}
	
	
	public IInfoSendService getInfoSendService() {
		return (IInfoSendService) this.getBean("infoSendService");
	}
}
