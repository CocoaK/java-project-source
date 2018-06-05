package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.base.BaseTest;

/**
 * 广告投放目标服务测试类。
 * @author kouy
 * @since 1.0 2012-5-31
 */
public class AdTargetServiceTest extends BaseTest {

	@Autowired
	private IAdTargetService adTargetService;
	
	@Test
	public void queryAdTargets(){
		List<AdTarget> list = adTargetService.queryAdTargets("7");
		logger.info("---------------------------返回广告投放目标列表：{}", list);
	}
}
