package com.biencloud.smarthome.service.page;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.page.model.Page;
import com.biencloud.smarthome.service.page.service.IPageService;

public class PageServiceTest extends BaseTransactionalTest{
	@Test
	public void queryPages(){
		Page p = new Page();
		List<Page> list=getPageService().queryAllPages(p);
		logger.info("result:{}",list);
	}
	
	@Test
	public void addPage(){
		Page p = new Page();
		p.setName("页面1");
		List<String> list = new ArrayList<String>();
		list.add("1");
		p.setDistricts(list);
		getPageService().save(p);
		logger.info("result:{}");
	}
	
	@Test
	public void queryPage(){
		Page p = new Page();
		p.setDistrictId("1");
		logger.info("result:{}",getPageService().queryPageForPaging(p, 1, 10));
	}	
	
	public IPageService getPageService() {
		return (IPageService) this.getBean("pageService");
	}
}
