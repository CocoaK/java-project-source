package com.biencloud.smarthome.web.rss.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.service.IWeatherService;
import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.rss.vo.WeatherVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.Weather;
import com.biencloud.smarthome.web.wsclient.stub.WeatherReportData;

public class WeatherServiceImpl extends BaseService<WeatherVO> implements IWeatherService{

	@Override
	public WeatherVO queryWeatherById(Long id) {
		Weather weather = getSmartHomeService().queryWeatherById(id);
		WeatherVO weatherVO = new WeatherVO();
		copyProperties(weather,weatherVO,true,"reportDate");
		return weatherVO;
	}

	@Override
	public PagingVO<WeatherVO> queryWeatherForPaging(WeatherVO weatherVO, int pageNum, int pageSize) {
		Weather weather = new Weather();
		copyProperties(weatherVO,weather,false,"reportDate");
		Paging paging = getSmartHomeService().queryWeatherForPaging(weather, pageNum, pageSize);
		return convertToPagingVO(paging,"reportDate");
	}

	@Override
	public void update(WeatherVO weatherVO) {
		Weather weather = new Weather();
		copyProperties(weatherVO,weather,false,"reportDate");
		getSmartHomeService().updateWeather(weather);
	}

	@Override
	public WeatherReportDataVO queryReportDataByCityName(String cityName) {
		WeatherReportDataVO weatherReportDataVO = new WeatherReportDataVO();
		WeatherReportData weatherReportData = getSmartHomeService().queryReportDataByCityName(cityName);
		copyProperties(weatherReportData,weatherReportDataVO);
		return weatherReportDataVO;
	}

}
