package com.biencloud.smarthome.service.rss.service;

import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.service.rss.vo.RssVO;
import com.biencloud.smarthome.service.rss.vo.WeatherData;

/**
 * 
 * 类名称：IRSSService 
 * 类描述： RSS业务处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-23 上午9:47:42
 */
public interface IRSSService {
	/**
	 * 
	 * 方法的描述: 获得rss信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-18 下午3:58:54
	 * @param url
	 * @return
	 */
   public List<RssVO> getRSSInfo(String url);
   /**
	 * 
	 * 方法的描述: 获得rss信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-18 下午3:58:54
	 * @param url
	 * @return
	 */
  public Map<String,String> getRSSInfoMapForTest(String url);
   /**
    * 
    * 方法的描述: 查询城市天气信息
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-18 下午4:26:49
    * @param city
    * @return
    */
   public String queryWeatherInfoByCity(String cityName,String cityCode,String url);
   
   /**
    * 
    * 方法的描述: 查询城市天气信息
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-18 下午4:26:49
    * @param city
    * @return
    */
   public WeatherData queryWeatherDataByCity(String cityName,String cityCode,String url);
   
   /**
    * 
    * 方法的描述: 执行天气预报更新
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-21 下午1:58:25
    */
   public void excuteWeatherUpdate();
}
