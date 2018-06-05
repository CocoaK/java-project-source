package com.biencloud.smarthome.web.rss;

import org.junit.Test;
import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.service.IRssServerService;
import com.biencloud.smarthome.web.rss.vo.RssServerVO;

public class RssServerServiceTest extends BaseTest{
	public IRssServerService getRssServerService()
	{
		return (IRssServerService)this.getBean("rssServerService");
	}

	@Test
	public void queryById(){
		System.out.println("queryRssServerById:"+getRssServerService().queryRssServerById(new Long("2")));
	}
	
	@Test
	public void update(){
		RssServerVO rssServerVO = new RssServerVO();
		rssServerVO.setRssId(new Long("2"));
		rssServerVO.setRssName("股票");
		rssServerVO.setStatus("2");
		getRssServerService().update(rssServerVO);
	}
	@Test
	public void queryRssServerForPaging(){
		RssServerVO rssServerVO = new RssServerVO();
		PagingVO paging = getRssServerService().queryRssServerForPaging(rssServerVO, 1, 10);
		System.out.println("paging:"+paging);
	}
}
