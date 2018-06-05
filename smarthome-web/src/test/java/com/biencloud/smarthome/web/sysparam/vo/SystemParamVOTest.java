package com.biencloud.smarthome.web.sysparam.vo;

import org.junit.Assert;
import org.junit.Test;

/**
 * 系统参数值对象测试类。
 * @author Matt Weng
 * @since 1.0 2012-4-16
 */
public class SystemParamVOTest {

	@Test
	public void hashCodeAndEquals(){
		SystemParamVO sp1 = new SystemParamVO();
		sp1.setParamCode("param_code");
		sp1.setParamValue("ParamValue1");
		sp1.setParamDesc("ParamDesc1");
		
		SystemParamVO sp2 = new SystemParamVO();
		sp2.setParamCode("param_code");
		sp2.setParamValue("ParamValue2");
		sp2.setParamDesc("ParamDesc2");
		
		Assert.assertTrue(sp1.hashCode() == sp2.hashCode());
		
		Assert.assertTrue(sp1.equals(sp2));
	}
}
