package com.biencloud.smarthome.service.page;

import java.util.List;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.page.model.PageLayout;
import com.biencloud.smarthome.service.page.model.PageContent;
import com.biencloud.smarthome.service.page.service.IPageContentService;
import com.biencloud.smarthome.service.page.service.IPageService;

public class PageContentServiceTest extends BaseTransactionalTest{
	@Test
	public void queryPages(){
		PageContent p = new PageContent();
		List<PageContent> list=getPageContentService().queryAllPageContents(p);
		logger.info("result:{}",list);
	}
	
	@Test
	public void addPage(){
		logger.info("result:{}");
	}
	
	
	public IPageContentService getPageContentService() {
		return (IPageContentService) this.getBean("pageContentService");
	}
}
