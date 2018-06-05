package com.biencloud.smarthome.service.rss.service.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.rss.help.weather.AcquisitEngine;
import com.biencloud.smarthome.service.rss.help.weather.AnalyzEngine;
import com.biencloud.smarthome.service.rss.help.weather.EngineInitial;
import com.biencloud.smarthome.service.rss.model.RssInfo;
import com.biencloud.smarthome.service.rss.service.IRSSService;
import com.biencloud.smarthome.service.rss.vo.RssVO;
import com.biencloud.smarthome.service.rss.vo.WeatherData;
import com.biencloud.smarthome.service.sysparam.dao.ISysParamDao;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.utils.FileUtil;
import com.biencloud.smarthome.service.common.utils.ServerPathUtil;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * 
 * 类名称：RSSServiceImpl 类描述： rss业务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-23 上午9:48:12
 */
public class RSSServiceImpl extends BaseService<RssInfo, Long> implements IRSSService {
	private IPushDao pushDao;
	private ISysParamDao sysParamDao;
	private IDeviceService deviceService;
	private ISysParamService sysParamService;

	@Override
	public List<RssVO> getRSSInfo(String rssUrl) {
		List<RssVO> list = new ArrayList<RssVO>();
		try {
			if (rssUrl != null) {
				URL url = new URL(rssUrl);
				// 读取Rss源
				XmlReader reader = new XmlReader(url);
				SyndFeedInput input = new SyndFeedInput();
				// 得到SyndFeed对象，即得到Rss源里的所有信息
				SyndFeed feed = input.build(reader);

				// 得到Rss子项列表
				List entries = feed.getEntries();
				// 循环得到每个子项信息
				for (int i = 0; i < entries.size(); i++) {
					SyndEntry entry = (SyndEntry) entries.get(i);

					// 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
					// System.out.println("标题：" + entry.getTitle());
					// System.out.println("连接地址：" + entry.getLink());
					SyndContent description = entry.getDescription();
					// System.out.println("简介：" + description.getValue());
					// System.out.println("发布时间：" + entry.getPublishedDate());
					//
					// // 以下是Rss源可先的几个部分
					// System.out.println("发布者：" + entry.getAuthor());
					RssVO rv = new RssVO(entry.getTitle(), entry.getLink(), description.getValue(), entry.getPublishedDate(),
							entry.getAuthor());
					list.add(rv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String queryWeatherInfoByCity(String cityName, String cityCode, String url) {
//		String httpUrl = url;
//		 System.out.println("wwwwwwwww1-----"+ServerPathUtil.class_path);
//		 System.out.println("wwwwwwwww2-----"+ServerPathUtil.rss_root_path);
//		 String rssPath=this.queryParamValueByName("rss_info_path");
//
//		String fileName = ServerPathUtil.rss_root_path + "weather-info_" + cityCode + ".xml";
//		String configPath = ServerPathUtil.class_root_path + "rss/weather-config.xml";
//		String newConfigPath = ServerPathUtil.rss_root_path + "weather-config.xml";
//		 System.out.println("****fileName："+fileName);
//		 System.out.println("****configPath："+configPath);
//		 System.out.println("****newConfigPath："+newConfigPath);
//		File file = new File(newConfigPath);
//		if (!file.exists()) {
//			FileUtil.copyFile(configPath, newConfigPath);
//		}
//		System.out.println("weather-config:" + configPath);
//		EngineInitial.modifyXML(newConfigPath, httpUrl, fileName);
//
//		// modifyXML(configPath, httpUrl, fileName);
//		String outPutPath = fileName;
//		AcquisitEngine.acquisitData(newConfigPath, outPutPath);
//
//		WeatherData weatherInfo = AnalyzEngine.analyzData(outPutPath);
//		return weatherInfo.toString();
		return null;
	}

	/**
	 * 
	 * 方法的描述: 查询rss信息文件存放的路径
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-23 上午11:43:12
	 * @param name
	 * @return
	 */
	private String queryParamValueByName(String name) {
		String pValue = null;
		if (name != null) {
			List<SystemParam> list = sysParamDao.find("from SystemParam where paramCode='" + name + "'");
			if (list != null && !list.isEmpty()) {
				pValue = list.get(0).getParamValue();
			}
		}
		return pValue;
	}

	@Override
	public Map<String, String> getRSSInfoMapForTest(String rssUrl) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			URL url = new URL(rssUrl);
			// 读取Rss源
			XmlReader reader = new XmlReader(url);
			SyndFeedInput input = new SyndFeedInput();
			// 得到SyndFeed对象，即得到Rss源里的所有信息
			SyndFeed feed = input.build(reader);

			// 得到Rss子项列表
			List entries = feed.getEntries();
			// 循环得到每个子项信息
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);

				// 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
				System.out.println("标题：" + entry.getTitle());
				System.out.println("连接地址：" + entry.getLink());
				SyndContent description = entry.getDescription();
				System.out.println("简介：" + description.getValue());
				System.out.println("发布时间：" + entry.getPublishedDate());

				// 以下是Rss源可先的几个部分
				System.out.println("发布者：" + entry.getAuthor());
				map.put(entry.getTitle(), entry.getLink());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void excuteWeatherUpdate() {
		// 天气预报
//		RssServer rssServer = getRssServerService().queryRssServerByCode(WEATHER_REPORT);
//		if (rssServer == null || "0".equals(rssServer.getStatus())) // 0表示禁用，1表示启用
//			rssServer = new RssServer();
//		String rssUrl = rssServer.getServerUrl();
//		List<Weather> weathers = new ArrayList<Weather>();
//		Weather w = new Weather();
//		w.setCountryName(COUNTRY_NAME);
//		List<Weather> lis = getWeatherService().queryAllWeather(w);
//		List<RssVO> list = getRSSInfo(rssUrl);
//		if (list != null && list.size() != 0)
//			list.remove(0);
//		for (RssVO rssVO : list) {
//			Weather weather = new Weather();
//			weather.setCountryName(COUNTRY_NAME);
//			weather.setProvinceName(rssVO.getTitle().substring(0, rssVO.getTitle().indexOf("-")));
//			weather.setCityName(rssVO.getTitle().substring(rssVO.getTitle().indexOf("-") + 1));
//			weather.setServiceUrl(rssVO.getUrl());
//			weather.setStatus("1");
//			// 如果lis为空或者lis不包含此weather,则将weather添加到weathers中保存
//			if (lis == null || !lis.contains(weather)) {
//				weathers.add(weather);
//			}
//		}
//		getWeatherService().saveCollection(weathers);
//
//		// 更新和推送
//		w.setStatus("1"); // "1"表示启用，0表示禁用
//		// 查询出状态为“1”的所有天气列表
//		List<Weather> weatherList = getWeatherService().queryAllWeather(w);
//		for (Weather wt : weatherList) {
//
//			getWeatherService().updateAndPush(wt);
//		}
	}

	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}

	public ISysParamDao getSysParamDao() {
		return sysParamDao;
	}

	public void setSysParamDao(ISysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public WeatherData queryWeatherDataByCity(String cityName, String cityCode, String url) {
		String httpUrl = url;
		WeatherData weatherInfo = null;
		String weatherConfigPath = ServerPathUtil.weather_root_path;
		String fileName = weatherConfigPath + "weather-info_" + cityCode + ".xml";
		String newConfigPath = weatherConfigPath+"weather-config.xml";
		File file = new File(newConfigPath);
		if (!file.exists()) {
			if(!file.getParentFile().exists()){
				if(!file.getParentFile().mkdirs()){
					System.out.println("目录创建失败！");
				}
			}
		}
		try{
			EngineInitial.modifyXML(newConfigPath, httpUrl, fileName);
	
			// modifyXML(configPath, httpUrl, fileName);
			String outPutPath = fileName;
			AcquisitEngine.acquisitData(newConfigPath, outPutPath);
	
			weatherInfo = AnalyzEngine.analyzData(outPutPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return weatherInfo;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

}
