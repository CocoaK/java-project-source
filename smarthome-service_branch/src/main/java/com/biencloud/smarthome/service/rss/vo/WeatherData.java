package com.biencloud.smarthome.service.rss.vo;

import java.util.List;

/**
 * 
 * description: 对应xml的data
 * 
 * @fileName:Data.java
 * @createTime:2010-11-23 下午04:23:27
 * @author:kouy
 * @version 1.0.0
 * 
 */
public class WeatherData
{
	//日期
	private String date;
	//周
	private String week;
	//白天温度
	private String dayTemp;
	//晚上温度
	private String nightTemp;
	//白天天气状态
	private String dayWeatherDesc;
	//晚上天气状态
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
		return "date:"+this.getDate()+",week:"+this.getWeek()+",dayTemp:"+this.getDayTemp()+",dayWeatherDesc:"+this.getDayWeatherDesc()+",nightTemp:"+this.nightTemp+",nightWeatherDesc:"+this.getNightWeatherDesc();
	}
}
