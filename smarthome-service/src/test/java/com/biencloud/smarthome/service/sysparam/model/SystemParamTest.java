package com.biencloud.smarthome.service.sysparam.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * 系统参数实体对象测试类。
 * @author kouy
 * @since 1.0 2012-4-16
 */
public class SystemParamTest {

	@Test
	public void hashCodeAndEquals(){
		SystemParam sp1 = new SystemParam();
		sp1.setParamCode("param_code");
		sp1.setParamValue("ParamValue1");
		sp1.setParamDesc("ParamDesc1");
		
		SystemParam sp2 = new SystemParam();
		sp2.setParamCode("param_code");
		sp2.setParamValue("ParamValue2");
		sp2.setParamDesc("ParamDesc2");
		
		Assert.assertTrue(sp1.hashCode() == sp2.hashCode());
		Assert.assertTrue(sp1.equals(sp2));
	}
}
