package com.biencloud.smarthome.service.rss.vo;

/**
 * 类名称：WeatherReportData 
 * 类描述： 天气预报数据
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-6 上午10:48:11
 */
public class WeatherReportData{
	//日期
	private String date;
	//星期
	private String week;
	//白天天气
	private String dayTemp;
	//晚上天气
	private String nightTemp;
	//白天天气说明
	private String dayWeatherDesc;
	//晚上天气说明
	private String nightWeatherDesc;
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getWeek()
	{
		return week;
	}
	
	public void setWeek(String week)
	{
		this.week = week;
	}
	
	public String getDayTemp()
	{
		return dayTemp;
	}
	
	public void setDayTemp(String dayTemp)
	{
		this.dayTemp = dayTemp;
	}
	
	public String getNightTemp()
	{
		return nightTemp;
	}
	
	public void setNightTemp(String nightTemp)
	{
		this.nightTemp = nightTemp;
	}
	
	public String getDayWeatherDesc()
	{
		return dayWeatherDesc;
	}
	
	public void setDayWeatherDesc(String dayWeatherDesc)
	{
		this.dayWeatherDesc = dayWeatherDesc;
	}
	
	public String getNightWeatherDesc()
	{
		return nightWeatherDesc;
	}
	
	public void setNightWeatherDesc(String nightWeatherDesc)
	{
		this.nightWeatherDesc = nightWeatherDesc;
	}
	public String toString()
	{
		return this.getDate()+","+this.getWeek()+","+this.getDayTemp()+","+this.nightTemp+",白天天气:"+this.getDayWeatherDesc()+",夜晚天气:"+this.getNightWeatherDesc();
	}
}
