package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.appdata.json.Json;

/**
 * 类名称：RoomVO 
 * 类描述： 户户通房号信息VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:18:26
 */
@SuppressWarnings("serial")
public class RoomVO extends Json{
	//房间号
	private String roomNo;
	//设备编号
	private String deviceNo;
	//设备名称
	private String deviceName;
	//mac地址
	private String mac;
	//ip地址
	private String ip;
	//区号
	private String areaNo;
	//楼栋号
	private String buildingNo;
	//单元号
	private String UnitNo;
	//设备类型
	private String deviceType;

	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getUnitNo() {
		return UnitNo;
	}
	public void setUnitNo(String unitNo) {
		UnitNo = unitNo;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
