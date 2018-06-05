package com.biencloud.smarthome.web.log.vo;

import java.util.Date;

/**
 * 
 * 类名称：ClientLogVO 类描述： 终端日志
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-24 下午4:10:27
 */
public class ClientLogVO {
	private Long id;
	// 设备编号
	private String deviceNo;
	// 设备MAC
	private String deviceMac;
	// 设备名称
	private String deviceName;
	// 数据类型
	private String dataType;
	// 数据内容
	private String dataContent;
	// 时间
	private Date addTime;
	// 终端ip
	private String ip;
	// 小区名称
	private String areaName;

	// 数据发起
	private String launch;

	public ClientLogVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientLogVO(Long id, String deviceNo, String deviceMac, String deviceName, String dataType, String dataContent, Date addTime,
			String ip, String areaName,String launch) {
		super();
		this.id = id;
		this.deviceNo = deviceNo;
		this.deviceMac = deviceMac;
		this.deviceName = deviceName;
		this.dataType = dataType;
		this.dataContent = dataContent;
		this.addTime = addTime;
		this.ip = ip;
		this.areaName = areaName;
		this.launch=launch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}
    
	
}
