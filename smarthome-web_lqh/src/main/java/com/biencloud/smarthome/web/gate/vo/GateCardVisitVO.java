package com.biencloud.smarthome.web.gate.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 门卡刷卡信息值对象。
 * @author kouy
 * @since 1.0 2012-5-7
 */
public class GateCardVisitVO extends BaseVO {

	private static final long serialVersionUID = -3764668185088525347L;
	
	private String visitId;//门卡刷卡记录ID
	private String districtId;//小区ID
	private String deviceId;//设备ID
	private String deviceCode;//设备代码
	private String deviceAlias;//设备别名
	private String gateCardId;//门卡ID
	private String cardNo;//门卡号
	private String ownerIdCard;//卡主身份证
	private String ownerName;//卡主姓名
	private String picPath1;//刷卡抓拍图片1的链接地址
	private String picPath2;//刷卡抓拍图片2的链接地址
	private String picPath3;//刷卡抓拍图片3的链接地址
	private Date visitTime;//刷卡时间
	
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
	public String getGateCardId() {
		return gateCardId;
	}
	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getOwnerIdCard() {
		return ownerIdCard;
	}
	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPicPath1() {
		return picPath1;
	}
	public void setPicPath1(String picPath1) {
		this.picPath1 = picPath1;
	}
	public String getPicPath2() {
		return picPath2;
	}
	public void setPicPath2(String picPath2) {
		this.picPath2 = picPath2;
	}
	public String getPicPath3() {
		return picPath3;
	}
	public void setPicPath3(String picPath3) {
		this.picPath3 = picPath3;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
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
}
