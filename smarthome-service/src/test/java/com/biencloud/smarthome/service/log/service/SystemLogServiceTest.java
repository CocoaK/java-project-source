package com.biencloud.smarthome.service.log.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.log.model.SystemLog;

public class SystemLogServiceTest extends BaseTest{
	
	@Autowired
	private ISystemLogService slService;
	
	@Test
	@Transactional(propagation = Propagation.SUPPORTS)
	public void save(){
		SystemLog log = new SystemLog();
		log.setMenuCode("MC0001");
		log.setErrorLocation("local2");
		log.setErrorMsg("ERROR MESSAGE2");
		log.setOperateTime(new Date());
		log.setOperateUser("lily");
		log.setOperationCode("01");

		System.out.println("------------------------log:"+log);
		slService.saveOrUpdate(log);
	}
	//@Test
	//@AfterTransaction
	public void updateT(){
		SystemLog log = slService.get("1");
		log.setOperateUser("cocoa003");
		System.out.println("----------------log2:"+log);
		slService.update(log);
	}
	
	//@Test
	public void remove(){
		SystemLog log = slService.get("1");
		System.out.println("----------------log2:"+log);
		//slService.update(log);
		slService.remove(log);
	}
	//@Test
	public void queryForPaging(){
		System.out.println("----------------paging:"+slService.querySystemLogForPaging("","", "", "", 1, 10));
	}
	//@Test
	public void get(){
		//return 
				slService.getSystemLogById("1");
	}
}
