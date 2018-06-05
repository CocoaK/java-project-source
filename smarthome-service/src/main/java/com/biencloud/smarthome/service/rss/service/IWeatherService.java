package com.biencloud.smarthome.service.rss.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.rss.model.Weather;
import com.biencloud.smarthome.service.rss.vo.WeatherReportData;

/**
 * 
 * 类名称：IWeatherService 
 * 类描述：Rss天气预报服务 接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-5 下午8:23:06
 */
public interface IWeatherService extends IService<Weather,Long>{
	/**
	 * 
	 * 方法的描述: 查询天气预报信息并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午8:25:26
	 * @param weather
	 * @param pageNum
	 * @param pageSize
	 * @return paging
	 */
	public Paging<Weather> queryWeatherForPaging(Weather weather,int pageNum,int pageSize);
	
	/**
	 * 
	 * 方法的描述: 查询所有天气预报
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 下午3:16:57
	 * @param weather
	 * @return	list
	 */
	public List<Weather> queryAllWeather(Weather weather);
	
	/**
	 * 
	 * 方法的描述: 保存和推送天气
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 下午9:01:54
	 * @param weather
	 */
	public void updateAndPush(Weather weather);
	
	/**
	 * 
	 * 方法的描述: 定时执行获取天气预报
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-8 下午6:02:21
	 */
	public void excuteWeatherUpdate();
	
	/**
	 * 
	 * 方法的描述: 根据城市名查询天气数据
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-14 上午11:12:02
	 * @return
	 */
	public WeatherReportData queryReportDataByCityName(String cityName);
	
	/**
	 * 
	 * 方法的描述: 根据城市查询天气
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-14 上午11:19:27
	 * @param cityName
	 * @return Weather
	 */
	public Weather queryWeatherByCityName(String cityName);
}
