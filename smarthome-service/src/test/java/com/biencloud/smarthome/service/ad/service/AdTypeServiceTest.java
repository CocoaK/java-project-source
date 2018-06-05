package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.base.BaseTest;

/**
 * 广告类型服务测试类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdTypeServiceTest extends BaseTest {

	@Autowired
	private IAdTypeService adTypeService;
	
	@Test
	public void queryAllAdTypes(){
		List<AdType> list = adTypeService.queryAllAdTypes();
		logger.info("---------------------------返回所有广告类型列表：{}", list);
	}
}
