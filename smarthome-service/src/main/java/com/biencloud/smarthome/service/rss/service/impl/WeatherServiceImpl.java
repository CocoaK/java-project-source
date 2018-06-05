package com.biencloud.smarthome.service.rss.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.common.utils.WeatherUtil;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.rss.model.RssServer;
import com.biencloud.smarthome.service.rss.model.Weather;
import com.biencloud.smarthome.service.rss.service.IRSSService;
import com.biencloud.smarthome.service.rss.service.IRssServerService;
import com.biencloud.smarthome.service.rss.service.IWeatherService;
import com.biencloud.smarthome.service.rss.vo.RssVO;
import com.biencloud.smarthome.service.rss.vo.WeatherData;
import com.biencloud.smarthome.service.rss.vo.WeatherReportData;
import com.biencloud.smarthome.service.sysgroup.service.ISystemGroupService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;
/**
 * 
 * 类名称：WeatherServiceImpl 
 * 类描述： 天气信息服务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-5 下午8:27:22
 */
public class WeatherServiceImpl extends BaseService<Weather,Long> implements IWeatherService{
	private IDeviceService deviceService;
	private IPushDao pushDao;
	private IRSSService rssService;
	private ISystemGroupService systemGroupService;
	private IRssServerService rssServerService;
    private static final String WEATHER_REPORT = "RSS0001";	//天气预报代码
    private static final String COUNTRY_NAME = "中国";	//天气预报国家
    private ISysParamService sysParamService;
	
	public IRssServerService getRssServerService() {
		return rssServerService;
	}
	public void setRssServerService(IRssServerService rssServerService) {
		this.rssServerService = rssServerService;
	}
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	public IPushDao getPushDao() {
		return pushDao;
	}
	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}
	public IRSSService getRssService() {
		return rssService;
	}
	public void setRssService(IRSSService rssService) {
		this.rssService = rssService;
	}
	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}
	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	@Override
	public Paging<Weather> queryWeatherForPaging(Weather weather, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql,weather);
		jpql.insert(0, "SELECT "+ REPLACE_CHARS +" FROM Weather weather");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "weather");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(weather)");
		queryString = queryString + " order by weather.reportDate desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			Weather weather) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (weather == null)
			return params;
		if (StringUtils.isNotBlank(weather.getCountryName()))
			appendCondition(jpql, " weather.countryName = :countryName", "countryName", 
					weather.getCountryName(), params);
		
		if (StringUtils.isNotBlank(weather.getProvinceName()))
			appendCondition(jpql, " weather.provinceName LIKE :provinceName", "provinceName", 
					"%" + weather.getProvinceName() + "%", params);
		
		if (StringUtils.isNotBlank(weather.getCityName()))
			appendCondition(jpql, " weather.cityName LIKE :cityName", "cityName", 
					"%" + weather.getCityName() + "%", params);
		
		if (StringUtils.isNotBlank(weather.getStatus()))
			appendCondition(jpql, " weather.status = :status", "status", 
					weather.getStatus(), params);
		return params;
	}

	@Override
	public List<Weather> queryAllWeather(Weather weather) {
	StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, weather);
		jpql.insert(0, "SELECT weather FROM Weather weather");
		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAndPush(Weather weather) {
		if(weather==null){
			return;
		}
		String cityName = weather.getProvinceName()+"-"+weather.getCityName();
		String url = weather.getServiceUrl();
		String cityCode = "";
		if(url!=null){
			cityCode = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		}
			
		WeatherData weatherData= getRssService().queryWeatherDataByCity(cityName, cityCode,url);
		WeatherReportData reportData = new WeatherReportData();
		if(weatherData!=null){
			reportData.setDate(weatherData.getDate());
			reportData.setDayTemp(weatherData.getDayTemp());
			reportData.setDayWeatherDesc(weatherData.getDayWeatherDesc());
			reportData.setNightTemp(weatherData.getNightTemp());
			reportData.setNightWeatherDesc(weatherData.getNightWeatherDesc());
			reportData.setWeek(weatherData.getWeek());
			weather.setContent(reportData.toString());
			String dayImage = WeatherUtil.weatherName(weatherData.getDayWeatherDesc());
			String nightImage = WeatherUtil.weatherName(weatherData.getNightWeatherDesc());
			weather.setDayImage(dayImage);
			weather.setNightImage(nightImage);
			weather.setReportDate(toDate(weatherData.getDate()));
			super.update(weather);
		
			List<Device> devices = null;
			List<String> groupIds = getSystemGroupService().getDistrictIdByCityName(weather.getCityName());
			if(groupIds!=null && groupIds.size()!=0){
				devices = getDeviceService().queryDevices(groupIds, null);
			}
		//推送数据
			if(devices != null && devices.size() != 0){
				for(Device device : devices){
					if(device != null){
						//pushName格式为：weather 2012-11-28
						String temp = "";	//温度
						String status = "";	//天气状态
						String image = "";	//天气图片
						if(StringUtils.isNotBlank(weatherData.getDayTemp())){
							temp = weatherData.getDayTemp();
						}else if(StringUtils.isNotBlank(weatherData.getNightTemp())){
							temp = weatherData.getNightTemp();
						}
						
						if(StringUtils.isNotBlank(weatherData.getDayWeatherDesc())){
							status = weatherData.getDayWeatherDesc();
						}else if(StringUtils.isNotBlank(weatherData.getNightWeatherDesc())){
							status = weatherData.getNightWeatherDesc();
						}
						
						if(StringUtils.isNotBlank(weather.getDayImage())){
							//天气图标放在webUploadFile下,因此是web下载相对路径
							image = sysParamService.queryParamValueByParamCode(Constants.PARAM_WEB_DOWNLOAD_REL_URL)+weather.getDayImage();
						}else if(StringUtils.isNotBlank(weather.getNightImage())){
							image = sysParamService.queryParamValueByParamCode(Constants.PARAM_WEB_DOWNLOAD_REL_URL)+weather.getNightImage();
						}
						Push push = new Push("weahter "+Utils.formatDate(), "weather", "temp:"+temp+",status:"+status+",image:"+image, new Date(), device.getDeviceCode());
						pushDao.insertPush(push);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * 方法的描述: 将 X月X日 的字符串日期转换为Date
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-7 下午6:10:16
	 * @param dateString
	 * @return
	 */
	private Date toDate(String dateString){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(sdf.format(calendar.getTime()));
		int month = Integer.parseInt(dateString.substring(0,dateString.indexOf("月")))-1;
		int day = Integer.parseInt(dateString.substring(dateString.indexOf("月")+1,dateString.indexOf("日")));
		GregorianCalendar gc = new GregorianCalendar(year,month,day,0,0,0);
		return gc.getTime();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void excuteWeatherUpdate(){
		//天气预报
				RssServer rssServer = getRssServerService().queryRssServerByCode(WEATHER_REPORT);
				if(rssServer==null || Constants.WEATHER_DISABLED.equals(rssServer.getStatus()))	//0表示禁用，1表示启用
					rssServer = new RssServer();
				String rssUrl = rssServer.getServerUrl();
				List<Weather> weathers = new ArrayList<Weather>();
				Weather w = new Weather();
				w.setCountryName(COUNTRY_NAME);
				List<Weather> lis = queryAllWeather(w);
				List<RssVO> list = getRssService().getRSSInfo(rssUrl);
				if(list != null && list.size()!=0)
					list.remove(0);		//第一条为中央气象台的信息
				for(RssVO rssVO : list){
					Weather weather = new Weather();
					weather.setCountryName(COUNTRY_NAME);
					weather.setProvinceName(rssVO.getTitle().substring(0, rssVO.getTitle().indexOf("-")));
					weather.setCityName(rssVO.getTitle().substring(rssVO.getTitle().indexOf("-")+1));
					weather.setServiceUrl(rssVO.getUrl());
					weather.setStatus(Constants.WEATHER_ENABLED);
					//如果lis为空或者lis不包含此weather,则将weather添加到weathers中保存
					if(lis==null || !lis.contains(weather)){
						weathers.add(weather);
					}
				}
				saveCollection(weathers);
				
				//更新和推送
				w.setStatus(Constants.WEATHER_ENABLED);	//"1"表示启用，0表示禁用
				//查询出状态为“1”的所有天气列表
				List<Weather> weatherList = queryAllWeather(w);
				for(Weather wt : weatherList){
					updateAndPush(wt);
				}
	}
	@Override
	public WeatherReportData queryReportDataByCityName(String cityName) {
		if("市".equals(cityName.substring(cityName.length()-1))){
			cityName = cityName.substring(0, cityName.length()-1);
		}
		Weather weather = queryWeatherByCityName(cityName);
		WeatherData weatherData = null;
		if(weather != null){
			String city = weather.getProvinceName()+"-"+weather.getCityName();
			String url = weather.getServiceUrl();
			String cityCode = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
			weatherData= getRssService().queryWeatherDataByCity(city, cityCode,weather.getServiceUrl());
		}
		WeatherReportData reportData = new WeatherReportData();
		if(weatherData!=null){
			reportData.setDate(weatherData.getDate());
			reportData.setDayTemp(weatherData.getDayTemp());
			reportData.setDayWeatherDesc(weatherData.getDayWeatherDesc());
			reportData.setNightTemp(weatherData.getNightTemp());
			reportData.setNightWeatherDesc(weatherData.getNightWeatherDesc());
			reportData.setWeek(weatherData.getWeek());
		}
		return reportData;
	}
	@Override
	public Weather queryWeatherByCityName(String cityName) {
		Weather weather=null;
		String queryString = "from Weather where cityName='"+cityName+"'";
		if(cityName!=null)
		{
			List<Weather> list=super.find(queryString);
			if(list!=null&&!list.isEmpty())
			{
				weather=list.get(0);
			}
		}
		return weather;
	}
}
