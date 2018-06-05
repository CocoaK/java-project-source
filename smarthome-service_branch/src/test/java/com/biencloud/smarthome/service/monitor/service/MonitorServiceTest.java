package com.biencloud.smarthome.service.monitor.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;

/**
 * 远程开锁 Service 单元测试
 * 
 * @author jsun  
 * @since 1.0 2012-6-5
 */
public class MonitorServiceTest extends BaseTest {
	@Autowired
	private IMonitorService monitorService;
	
	@Test
	public void testPaUserRemoteUnlock() {
		// 物管用户远程开锁单元门口机(通过管理机授权)
		//logger.info("物管用户远程开锁: {}", monitorService.remoteUnlock("MC00015", "MC00011", "123456"));
	}

	@Test
	public void testOwnerUserRemoteUnlock() {
		// 业主用户远程开锁单元门口机(通过室内机授权)
		//logger.info("业主用户远程开锁: {}", monitorService.remoteUnlock("MC00015", "MC00021", "888888"));
	}
}
