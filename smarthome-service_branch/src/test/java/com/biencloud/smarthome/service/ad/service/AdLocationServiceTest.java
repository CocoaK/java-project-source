package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.base.BaseTest;

/**
 * 广告投放位置服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-6-1
 */
public class AdLocationServiceTest extends BaseTest {

	@Autowired
	private IAdLocationService adLocationService;
	
	@Test
	public void queryAdLocations(){
		List<AdLocation> list = adLocationService.queryAdLocations("01");
		logger.info("---------------------------返回App系统的所有广告投放位置列表：{}", list);
		
		list = adLocationService.queryAdLocations("02");
		logger.info("---------------------------返回Web系统的所有广告投放位置列表：{}", list);
	}
}
