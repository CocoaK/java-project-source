package com.biencloud.smarthome.web.rss.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.rss.vo.WeatherVO;

/**
 * 
 * 类名称：IWeatherService 
 * 类描述： 天气预报信息web端接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-6 上午10:29:22
 */
public interface IWeatherService{
	
	/**
	 * 
	 * 方法的描述: 根据Id查询天气预报信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 上午10:37:34
	 * @return WeatherVO
	 */
	public WeatherVO queryWeatherById(Long id);
	
	/**
	 * 
	 * 方法的描述: 查询天气信息并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 上午10:38:08
	 * @param weatherVO
	 * @param pageNum
	 * @param pageSize
	 * @return PagingVO
	 */
	public PagingVO<WeatherVO> queryWeatherForPaging(WeatherVO weatherVO,int pageNum,int pageSize);
	
	/**
	 * 
	 * 方法的描述: 更新天气信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 上午10:40:11
	 * @param weatherVO
	 */
	public void update(WeatherVO weatherVO);
	
	public WeatherReportDataVO queryReportDataByCityName(String cityName);
	
}
