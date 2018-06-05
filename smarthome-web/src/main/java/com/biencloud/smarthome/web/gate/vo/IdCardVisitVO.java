package com.biencloud.smarthome.web.gate.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 身份证刷卡信息值对象。
 * @author kouy
 * @since 1.0 2012-5-7
 */
public class IdCardVisitVO extends BaseVO {

	private static final long serialVersionUID = -4636607124010559594L;
	
	private String visitId;//身份证刷卡记录ID
	private String districtId;//小区ID
	private String deviceId;//设备ID
	private String deviceCode;//设备代码
	private String deviceAlias;//设备别名	
	private String gender;//来访人性别
	private String idCard;//来访人身份证
	private Date visitTime;//刷卡时间
	private String visitorAddress;//来访人住址
	private String visitorName;//来访人姓名
	private String reason;//来访目的
	
	private Date beginTime;//起始时间，刷卡时间查询条件
	private Date endTime;//结束时间，刷卡时间查询条件
	
	
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getVisitorAddress() {
		return visitorAddress;
	}
	public void setVisitorAddress(String visitorAddress) {
		this.visitorAddress = visitorAddress;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
