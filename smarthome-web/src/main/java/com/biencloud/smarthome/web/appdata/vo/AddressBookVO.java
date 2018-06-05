package com.biencloud.smarthome.web.appdata.vo;

import java.util.Date;

/**
 * 
 * 类名称：AddressBookVO 类描述： 地址薄vo类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-30 下午6:41:32
 */
public class AddressBookVO {
	
	private Long id;
	// 呢称
	private String nickName;
	// 小区编号
	private String areaCode;
	// 区域编号
	private String regionCode;
	// 楼宇编号
	private String storeyCode;
	// 单元编号
	private String unitCode;
	// 房号
	private String roomCode;
	// 是否黑名单
	private String isBlack;
	// 添加时间
	private Date addTime;
	// ip
	private String ip;
	// 设备编号
	private String deviceNo;
	// app端记录id
	private Long appId;
	

	public AddressBookVO() {
		super();
	}

	public AddressBookVO(String nickName, String areaCode, String regionCode, String storeyCode, String unitCode, String roomCode,
			String isBlack, Date addTime, String ip, String deviceNo,Long appId) {
		super();
		this.nickName = nickName;
		this.areaCode = areaCode;
		this.regionCode = regionCode;
		this.storeyCode = storeyCode;
		this.unitCode = unitCode;
		this.roomCode = roomCode;
		this.isBlack = isBlack;
		this.addTime = addTime;
		this.ip = ip;
		this.deviceNo = deviceNo;
		this.appId = appId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getStoreyCode() {
		return storeyCode;
	}

	public void setStoreyCode(String storeyCode) {
		this.storeyCode = storeyCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
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

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	
	
}
