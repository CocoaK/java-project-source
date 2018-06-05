package com.biencloud.smarthome.service.device.service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.log.model.AppDataLog;
import com.biencloud.smarthome.service.log.service.IAppDataLogService;

public class AppDataLogServiceTest extends BaseTest{
	@Autowired
	private IAppDataLogService appDataLogService;
	

	public IAppDataLogService getAppDataLogService() {
		return appDataLogService;
	}


	public void setAppDataLogService(IAppDataLogService appDataLogService) {
		this.appDataLogService = appDataLogService;
	}


	@Test
	public void queryAppLog(){
		AppDataLog appDataLog = new AppDataLog();
		appDataLog.setAction("gate_data");
		logger.info("查询App请求日志：{}",appDataLogService.queryAppDataLogForPaging(appDataLog, 1, 10));
	}
}
