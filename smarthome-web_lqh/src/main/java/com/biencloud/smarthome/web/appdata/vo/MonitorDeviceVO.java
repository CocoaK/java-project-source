package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.appdata.json.Json;

/**
 * 
 * 类名称：MonitorDeviceVO 
 * 类描述： 监控设备VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-26 下午5:36:38
 */
@SuppressWarnings("serial")
public class MonitorDeviceVO extends Json{
	//设备名称
	private String deviceName;
	//设备IP
	private String deviceIp;
	//设备类型
	private String deviceType;

	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
