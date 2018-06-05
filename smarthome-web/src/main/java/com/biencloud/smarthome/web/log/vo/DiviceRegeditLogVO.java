package com.biencloud.smarthome.web.log.vo;

import java.util.Date;

public class DiviceRegeditLogVO {

	private Date addEndTime;//添加结束时间
	private Date addStartTime;//添加开始时间
	private Date addTime;//添加时间
	private String buildingName;//栋名称
	private String cellName;//单元名称
	private String deviceNo;//设备编号
	private String districtName;//小区名称
	private String diveceType;//设备类型
	private String hourseNo;//房间号
	private Long id;//主键
	private String location;//位置
	private String macAddr;//MAC地址
	private String name;//名称
	private String regionName;//区域名称
	private String siteNo;//网点编号
	private String userIp;//用户IP
	private String eventAciton;//事件动作
	public Date getAddEndTime() {
		return addEndTime;
	}
	public void setAddEndTime(Date addEndTime) {
		this.addEndTime = addEndTime;
	}
	public Date getAddStartTime() {
		return addStartTime;
	}
	public void setAddStartTime(Date addStartTime) {
		this.addStartTime = addStartTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDiveceType() {
		return diveceType;
	}
	public void setDiveceType(String diveceType) {
		this.diveceType = diveceType;
	}
	public String getHourseNo() {
		return hourseNo;
	}
	public void setHourseNo(String hourseNo) {
		this.hourseNo = hourseNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getSiteNo() {
		return siteNo;
	}
	public void setSiteNo(String siteNo) {
		this.siteNo = siteNo;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getEventAciton() {
		return eventAciton;
	}
	public void setEventAciton(String eventAciton) {
		this.eventAciton = eventAciton;
	}


	
}
