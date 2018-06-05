package com.biencloud.smarthome.web.login.vo;

import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 
 * 类名称：MainPageValueJson 
 * 类描述：业主主页面值json对象
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-8 下午5:04:39
 */
public class OwnerMainPageVO{
	
	private Long infoCount;	//个人发布信息数
	private Long complaintCount; //个人投诉数
	private Long repairCount;	//个人报修数
	private Long visitorCount;	//访客记录
	private Long alarmCount;		//报警数
	private Long goodsCount;		//商品数
	private PaUserVO paUser;	//物业管理员信息
	private WeatherReportDataVO weatherReportData;
	public Long getInfoCount() {
		return infoCount;
	}
	public void setInfoCount(Long infoCount) {
		this.infoCount = infoCount;
	}
	public Long getComplaintCount() {
		return complaintCount;
	}
	public void setComplaintCount(Long complaintCount) {
		this.complaintCount = complaintCount;
	}
	public Long getRepairCount() {
		return repairCount;
	}
	public void setRepairCount(Long repairCount) {
		this.repairCount = repairCount;
	}
	public Long getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(Long visitorCount) {
		this.visitorCount = visitorCount;
	}
	public Long getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(Long alarmCount) {
		this.alarmCount = alarmCount;
	}
	public Long getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Long goodsCount) {
		this.goodsCount = goodsCount;
	}
	public PaUserVO getPaUser() {
		return paUser;
	}
	public void setPaUser(PaUserVO paUser) {
		this.paUser = paUser;
	}
	public WeatherReportDataVO getWeatherReportData() {
		return weatherReportData;
	}
	public void setWeatherReportData(WeatherReportDataVO weatherReportData) {
		this.weatherReportData = weatherReportData;
	}
}
