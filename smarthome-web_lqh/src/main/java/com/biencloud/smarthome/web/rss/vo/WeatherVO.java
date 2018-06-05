package com.biencloud.smarthome.web.rss.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

@SuppressWarnings("serial")
public class WeatherVO extends BaseVO{
	private Long id;
	private String countryName;
	private String provinceName;
	private String cityName;
	private String serviceUrl;
	private Date reportDate;
	private String status;
	private String content;
	private String dayImage;
	private String nightImage;
	
	public WeatherVO(){}
	public WeatherVO(Long id,String countryName,String provinceName,String 
			cityName,String serviceUrl,Date reportDate,String status,String content){
		this.id = id;
		this.countryName = countryName;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.serviceUrl = serviceUrl;
		this.reportDate = reportDate;
		this.status = status;
		this.content = content;	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDayImage() {
		return dayImage;
	}
	public void setDayImage(String dayImage) {
		this.dayImage = dayImage;
	}
	public String getNightImage() {
		return nightImage;
	}
	public void setNightImage(String nightImage) {
		this.nightImage = nightImage;
	}
	
}
