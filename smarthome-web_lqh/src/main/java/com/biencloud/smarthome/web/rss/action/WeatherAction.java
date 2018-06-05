package com.biencloud.smarthome.web.rss.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.service.IWeatherService;
import com.biencloud.smarthome.web.rss.vo.WeatherVO;


/**
 * 
 * 类名称：WeatherAction 
 * 类描述：天气信息 动作类 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-4 下午7:07:49
 */
@SuppressWarnings("serial")
public class WeatherAction extends BaseAction<WeatherVO>{

	private IWeatherService weatherService;
	private WeatherVO weatherVO;
	private boolean successFlag;
	
	/**
	 * 
	 * 方法的描述: 查询分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:18:02
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception{
		PagingVO<WeatherVO> page = getPage();
		if(page == null)
			page = new PagingVO<WeatherVO>();
		//查询并分页
		PagingVO<WeatherVO> weatherList = getWeatherService()
				.queryWeatherForPaging(weatherVO, page.getPageNum(), page.getPageSize());
		setPage(weatherList);
		return "query_list";
	}
	
	
	/**
	 * 
	 * 方法的描述: 修改页面
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午5:14:42
	 * @return
	 * @throws Exception
	 */
	public String updateDetail() throws Exception{
		WeatherVO weather = getWeatherService().queryWeatherById(weatherVO.getId());
		setWeatherVO(weather);
		return "update_detail";
	}
	
	/**
	 * 
	 * 方法的描述:修改天气信息 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:27:18
	 * @return
	 * @throws Exception
	 */
	public String weatherUpdate() throws Exception{
		WeatherVO weather = getWeatherService().queryWeatherById(weatherVO.getId());
		if(weatherVO.getStatus()!=null)
			weather.setStatus(weatherVO.getStatus());
		getWeatherService().update(weather);
		setSuccessFlag(true);
		setWeatherVO(weather);
		return "weather_update";
	}
	

	public IWeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(IWeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public WeatherVO getWeatherVO() {
		return weatherVO;
	}

	public void setWeatherVO(WeatherVO weatherVO) {
		this.weatherVO = weatherVO;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
}
