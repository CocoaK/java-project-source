package com.biencloud.smarthome.web.login.vo;

import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.user.vo.SaUserVO;

/**
 * 
 * 类名称：MainPageValueJson 
 * 类描述：系统管理员 主页面值VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-8 下午5:04:39
 */
public class SaMainPageVO {
	private Long liverCount;			//住户数
	private Long districtCount;		//小区数
	private Long onlineUserCount;	//在线用户数
	private Long onlineDeviceCount;	//在线设备数
	private Long todayInfoCount;		//今日信息数
	private Long complaintCount;		//投诉数
	private Long goodsCount;			//商品数
	private SaUserVO saUserVO;		//用户信息
	private WeatherReportDataVO weatherReportData;		//天气
	public Long getLiverCount() {
		return liverCount;
	}
	public void setLiverCount(Long liverCount) {
		this.liverCount = liverCount;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getOnlineUserCount() {
		return onlineUserCount;
	}
	public void setOnlineUserCount(Long onlineUserCount) {
		this.onlineUserCount = onlineUserCount;
	}
	public Long getOnlineDeviceCount() {
		return onlineDeviceCount;
	}
	public void setOnlineDeviceCount(Long onlineDeviceCount) {
		this.onlineDeviceCount = onlineDeviceCount;
	}
	public Long getTodayInfoCount() {
		return todayInfoCount;
	}
	public void setTodayInfoCount(Long todayInfoCount) {
		this.todayInfoCount = todayInfoCount;
	}
	public Long getComplaintCount() {
		return complaintCount;
	}
	public void setComplaintCount(Long complaintCount) {
		this.complaintCount = complaintCount;
	}
	public Long getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Long goodsCount) {
		this.goodsCount = goodsCount;
	}
	public SaUserVO getSaUserVO() {
		return saUserVO;
	}
	public void setSaUserVO(SaUserVO saUserVO) {
		this.saUserVO = saUserVO;
	}
	public WeatherReportDataVO getWeatherReportData() {
		return weatherReportData;
	}
	public void setWeatherReportData(WeatherReportDataVO weatherReportData) {
		this.weatherReportData = weatherReportData;
	}
}
