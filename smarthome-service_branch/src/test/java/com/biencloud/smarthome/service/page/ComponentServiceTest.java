package com.biencloud.smarthome.service.page;

import java.util.List;
import org.junit.Test;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.page.model.Component;
import com.biencloud.smarthome.service.page.service.IComponentService;

public class ComponentServiceTest extends BaseTransactionalTest{
	@Test
	public void queryComponents(){
		Component c = new Component();
		c.setPageId("1");
		List<Component> list=getService().queryComponents(c);
		logger.info("result:{}",list);
	}
	
	@Test
	public void addComponent(){
		Component c = new Component();
		c.setPageId("1");
		//c.setName("页面1");
		c.setRightOfId("1");
		c.setType("1");
		c.setGroups("1");
		c.setBelowOfId("3");
		//c.setName("haha");
		getService().saveComponent(c);
		logger.info("result:{}",c);
	}
	
	
	public IComponentService getService() {
		return (IComponentService) this.getBean("componentService");
	}
}
