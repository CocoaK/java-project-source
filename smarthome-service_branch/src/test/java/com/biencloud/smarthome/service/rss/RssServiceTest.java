package com.biencloud.smarthome.service.rss;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.rss.service.IRSSService;

public class RssServiceTest extends BaseTransactionalTest{
	public IRSSService getRSSService() {
		return (IRSSService) this.getBean("rssService");
	}
	
	@Test
	public void excute(){
		getRSSService().queryWeatherInfoByCity("深圳", "shenzhen", "http://www.weather.gov.cn/publish/forecast/AGD/shenzhen.html");
	}
	@Test
	public void getRSSInfoMapForTest(){
		String url = "http://www.weather.gov.cn/publish/rss/forecast.html";
		getRSSService().getRSSInfoMapForTest(url);
	}
	
	@Test
	public void getRSSInfo(){
		String url = "http://www.weather.gov.cn/publish/forecast/AGD/shenzhen.html";
		getRSSService().getRSSInfo(url);
	}
	
	@Test
	public void queryWeatherInfoByCity(){
		String url = "http://www.weather.gov.cn/publish/forecast/AGD/shenzhen.html";
		getRSSService().queryWeatherInfoByCity("广东省-深圳","shenzhen",url);
	}
}
