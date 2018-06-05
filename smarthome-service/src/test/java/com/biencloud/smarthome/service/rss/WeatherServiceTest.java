package com.biencloud.smarthome.service.rss;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.rss.model.Weather;
import com.biencloud.smarthome.service.rss.service.IWeatherService;

public class WeatherServiceTest extends BaseTest{
	@Autowired
	private IWeatherService weatherService;
	
	public IWeatherService getWeatherService() {
		return weatherService;
	}

	@Test
	public void queryWeatherById(){
		System.out.println("get:"+weatherService.get(new Long("4556")));
	}
	
	@Test
	public void queryWeatherForPaging(){
		Weather weather = new Weather();
		Paging<Weather> paging = weatherService.queryWeatherForPaging(weather, 1, 10);
		System.out.println("paging:"+paging);
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void update(){
		Weather weather = new Weather();
		weather.setId(new Long("4556"));
		weather.setContent("test");
		weather.setStatus("0");
		weatherService.update(weather);
	}
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateAndPush(){
		Weather weather = weatherService.get(new Long("6627"));
		weather.setContent("update");
		weatherService.updateAndPush(weather);
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void excuteWeatherUpdate(){
		weatherService.excuteWeatherUpdate();
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void weatherImage(){
		String weatherDesc = "阴转多云";
		//weatherService.weatherImage(weatherDesc);
	}
}
