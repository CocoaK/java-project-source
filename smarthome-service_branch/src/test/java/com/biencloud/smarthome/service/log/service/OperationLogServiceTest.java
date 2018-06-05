package com.biencloud.smarthome.service.log.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.log.model.OperationLog;

public class OperationLogServiceTest extends BaseTest{
	
	@Autowired
	private IOperationLogService ilService;
	
	@Test
	//@Transactional(propagation=Propagation.SUPPORTS)
	public void save(){
		OperationLog log = new OperationLog();
		log.setMenuCode("MC0002");
		log.setIp("192.168.1.137");
		log.setOperateResult(01);
		log.setOperateTime(new Date());
		log.setOperateUser("cocoa002");
		log.setOperationCode("0");
		ilService.save(log);
	}
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void update(){
		OperationLog log = ilService.get("1");
		log.setOperateUser("cocoa002");
		ilService.update(log);
	}
	@Test
	public void queryForPaging(){
		OperationLog operationLog = new OperationLog();
		operationLog.setOperateResult(0);
		System.out.println("----------------paging:"+ilService.queryOperationLogForPaging(operationLog, 1, 10));
	}
	@Test
	public void removeOldLogs(){
		ilService.removeOldLogs();
	}

}
