package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 类名称：RoomSipVO 
 * 类描述： 获取管理机房号(完整房号即sip账号)信息
 * @author: ykou  
 * @version: 0.1
 * @date: 2015-3-15 上午10:18:26
 */
@SuppressWarnings("serial")
public class RoomSipVO extends BaseVO{
	//sip账号
	private String sipuid;
	//设备编号
	private String deviceNo;
	//设备名称
	private String deviceName;
	//mac地址
	private String mac;
	//ip地址
	private String ip;
	//区号
//	private String areaNo;
	//楼栋号
//	private String buildingNo;
	//单元号
//	private String UnitNo;
	//设备类型
//	private String deviceType;
	public String getSipuid() {
		return sipuid;
	}
	public void setSipuid(String sipuid) {
		this.sipuid = sipuid;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

}
