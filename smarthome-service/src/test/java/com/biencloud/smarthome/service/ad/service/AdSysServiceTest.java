package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.base.BaseTest;

/**
 * 广告投放目标系统服务测试类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdSysServiceTest extends BaseTest {

	@Autowired
	private IAdSysService adSysService;
	
	@Test
	public void queryAllAdSystems(){
		List<AdSys> list = adSysService.queryAllAdSystems();
		logger.info("---------------------------返回所有广告投放目标系统列表：{}", list);
	}
}
