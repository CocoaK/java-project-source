package com.biencloud.smarthome.service.rss;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.rss.model.RssServer;
import com.biencloud.smarthome.service.rss.service.IRssServerService;

public class RssServerServiceTest extends BaseTest{
	@Autowired
	private IRssServerService rssServerService;
	
	@Test
	public void queryRssServerById(){
		System.out.println("get:"+rssServerService.get(new Long("1")));
	}
	
	@Test
	public void queryRssServerForPaging(){
		RssServer rssServer = new RssServer();
		Paging<RssServer> paging = rssServerService.queryRssServerForPaging(rssServer, 1, 10);
		System.out.println("paging:"+paging);
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void update(){
		RssServer rssServer = new RssServer();
		rssServer.setRssId(new Long("1"));
		rssServer.setRssName("天气预报2");
		rssServer.setStatus("0");
		rssServer.setCreatedUser("12");
		rssServerService.update(rssServer);
	}
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void remove(){
		RssServer rssServer = new RssServer();
		rssServer.setRssId(new Long("1"));
		rssServerService.remove(rssServer);
	}
}
