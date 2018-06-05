package com.biencloud.smarthome.service.sysparam.service;

import java.util.Date;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;

/**
 * 系统参数模块服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-4-14
 */
public class SysParamServiceTest extends BaseTest {

	@Autowired
	private ISysParamService sysParamService;
	
	@Test
	public void querySystemParams(){
		Paging<SystemParam> paging = sysParamService.querySystemParamsForPaging(
				"not_existed_param", 1, 10);
		
		paging = sysParamService.querySystemParamsForPaging(
				null, 1, 10);
		logger.info("返回分页信息：{}", paging);
	}
	
	@Test
	public void getSystemParamDetail(){
		SystemParam systemParam = sysParamService.get("not_existed_param");
		
		Assert.assertNull(systemParam);
		
		systemParam = sysParamService.get(Constants.PARAM_LOGIN_INIT_PASS);
		
		logger.info("返回系统参数详细信息：{}", systemParam);
	}
	
	@Test
	public void updateSystemParam(){
		SystemParam systemParam = sysParamService.get(Constants.PARAM_LOGIN_INIT_PASS);
		if(systemParam != null){
			systemParam.setUpdatedUser("test");
			systemParam.setUpdatedTime(new Date());
			sysParamService.update(systemParam);
		}
	}
}
