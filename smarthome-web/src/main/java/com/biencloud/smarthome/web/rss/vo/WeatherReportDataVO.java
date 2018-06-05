package com.biencloud.smarthome.web.rss.vo;

public class WeatherReportDataVO{
	
	private String date;
	
	private String week;
	
	private String dayTemp;
	
	private String nightTemp;
	
	private String dayWeatherDesc;
	
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
