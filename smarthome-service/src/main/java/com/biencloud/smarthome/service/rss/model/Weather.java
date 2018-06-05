package com.biencloud.smarthome.service.rss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.biencloud.smarthome.service.base.model.BaseEntity;
/**
 * 类名称：Weather 
 * 类描述： 天气信息实体
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-29 上午10:50:49
 */
@Entity
@Table(name="t_weather_info")
public class Weather extends BaseEntity{

	private static final long serialVersionUID = -8436196482190441403L;
	
	public Weather(){}
	public Weather(Long id,String countryName,String provinceName,String 
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
	
	//编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	//国家名称
	@Column(name="country_name")
	private String countryName;
	@Column(name="province_name")
	//省份名称
	private String provinceName;
	@Column(name="city_name")
	//城市名称
	private String cityName;
	@Column(name="service_url")
	//服务地址
	private String serviceUrl;
	@Column(name="report_date")
	//发布时间
	private Date reportDate;
	@Column(name="status")
	//状态：1启用，0禁用
	private String status;
	//天气内容
	@Column(name="content")
	private String content;
	//白天天气图片
	@Column(name="day_image")
	private String dayImage;
	//晚上天气图片
	@Column(name="night_image")
	private String nightImage;

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
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1959941635, 1952620985)
				.append(this.countryName)
				.append(this.provinceName)
				.append(this.cityName).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Weather)) {
			return false;
		}
		Weather weather = (Weather) object;
		return new EqualsBuilder()
				.append(this.countryName, weather.countryName)
				.append(this.provinceName, weather.provinceName)
				.append(this.cityName, weather.cityName).isEquals();
	}
	
}
