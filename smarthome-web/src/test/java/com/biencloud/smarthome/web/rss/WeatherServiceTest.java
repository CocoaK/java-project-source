package com.biencloud.smarthome.web.rss;

import org.junit.Test;
import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.service.IRssServerService;
import com.biencloud.smarthome.web.rss.service.IWeatherService;
import com.biencloud.smarthome.web.rss.vo.RssServerVO;
import com.biencloud.smarthome.web.rss.vo.WeatherVO;

public class WeatherServiceTest extends BaseTest{
	public IWeatherService getWeatherService()
	{
		return (IWeatherService)this.getBean("weatherService");
	}

	@Test
	public void queryById(){
		System.out.println("queryRssServerById:"+getWeatherService().queryWeatherById(new Long("1")));
	}
	
//	@Test
//	public void update(){
//		RssServerVO rssServerVO = new RssServerVO();
//		rssServerVO.setRssId(new Long("2"));
//		rssServerVO.setRssName("股票");
//		rssServerVO.setStatus("2");
//		getRssServerService().update(rssServerVO);
//	}
	@Test
	public void queryWeatherForPaging(){
		WeatherVO weatherVO = new WeatherVO();
		PagingVO paging = getWeatherService().queryWeatherForPaging(weatherVO, 1, 10);
		System.out.println("paging:"+paging);
	}
}
