package com.biencloud.smarthome.service.alarm;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.biencloud.smarthome.service.alarm.model.Alarm;
import com.biencloud.smarthome.service.alarm.service.IAlarmService;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.user.model.OwnerUser;

public class AlarmServiceTest extends BaseTransactionalTest{
	@Test
	public void queryAlarmForPaging(){
		Assert.assertNotNull(getAlarmService());
		Alarm paramsOb=new Alarm();
//		paramsOb.setStatus("0");
		paramsOb.setCreatedTime(new Date());
		Paging<Alarm> list=getAlarmService().queryAlarmForPaging(paramsOb, 1, 10);
		logger.info("result:{}",list.getResultsSize());
	}
	
	
	@Test
	public void DelAlarm(){
		Assert.assertNotNull(getAlarmService());
		Alarm paramsOb=getAlarmService().getAlarm("2");
		getAlarmService().DelAlarm(paramsOb);
	}
	
	@Test
	public void getOwnerAlarmCount(){
		Assert.assertNotNull(getAlarmService());
		Alarm paramsOb=new Alarm();
		OwnerUser ou=new OwnerUser();
		ou.setUserId("1");
		paramsOb.setownerUser(ou);
		Long result=getAlarmService().getOwnerAlarmCount(paramsOb);
		logger.info("result:{}",result);
	}
	
	public IAlarmService getAlarmService() {
		return (IAlarmService) this.getBean("alarmService");
	}
}
