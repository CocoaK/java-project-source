package com.biencloud.smarthome.service.log.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类名称：ClientLog 
 * 类描述： 终端日志
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-24 下午2:03:34
 */
@Entity
@Table(name = "t_client_log")
public class ClientLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//设备编号
	private String deviceNo;
	//设备MAC
	private String deviceMac;
	//设备名称
	private String deviceName;
	//数据类型
	private String dataType;
	//数据内容
	private String dataContent;
	//时间
	private Date addTime;
	//终端ip
	private String ip;
	//小区名称
	private String areaName;
	//小区编号
	private String areaNo;
    //数据发起
	private String launch;
	

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "deviceNo", length = 45)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Column(name = "deviceMac", length = 45)
	public String getDeviceMac() {
		return this.deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	@Column(name = "deviceName", length = 45)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "dataContent", length = 65535)
	public String getDataContent() {
		return this.dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "dataType", length = 45)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	@Column(name = "ip", length = 45, nullable = false)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "areaName", length = 45)
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Transient
	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	@Column(name = "dataSources", length = 45)
	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}
    
	
	
}