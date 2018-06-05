package com.biencloud.smarthome.web.login.vo;

import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.user.vo.SaUserVO;

/**
 * 
 * 类名称：MainPageValueJson 
 * 类描述：物业 主页面值json对象
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-8 下午5:04:39
 */
public class PaMainPageVO {
	private Long liverCount;			//住户数
	private double chargeFinish;		//已收费额
	private double chargeTotal;		//应收费额
	private Long deviceCount;	//在线设备数
	private Long todayCcomplaintCount; //投诉数
	private Long goodsCount;			//商品数
	private SaUserVO saUserVO;		//管理员信息
	private WeatherReportDataVO weatherReportData; //天气
	public Long getLiverCount() {
		return liverCount;
	}
	public void setLiverCount(Long liverCount) {
		this.liverCount = liverCount;
	}
	public double getChargeFinish() {
		return chargeFinish;
	}
	public void setChargeFinish(double chargeFinish) {
		this.chargeFinish = chargeFinish;
	}
	public double getChargeTotal() {
		return chargeTotal;
	}
	public void setChargeTotal(double chargeTotal) {
		this.chargeTotal = chargeTotal;
	}
	public Long getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(Long deviceCount) {
		this.deviceCount = deviceCount;
	}
	public Long getTodayCcomplaintCount() {
		return todayCcomplaintCount;
	}
	public void setTodayCcomplaintCount(Long todayCcomplaintCount) {
		this.todayCcomplaintCount = todayCcomplaintCount;
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
