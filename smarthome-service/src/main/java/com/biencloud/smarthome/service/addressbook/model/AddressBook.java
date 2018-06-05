package com.biencloud.smarthome.service.addressbook.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类名称：AddressBook 
 * 类描述： 地址薄
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-30 下午4:55:04
 */
@Entity
@Table(name = "t_addressbook", catalog = "smarthome")
public class AddressBook implements java.io.Serializable {

	// Fields

	private Long id;
	//呢称
	private String nickName;
	//小区编号
	private String areaCode;
	//区域编号
	private String regionCode;
	//楼宇编号
	private String storeyCode;
	//单元编号
	private String unitCode;
	//房号
	private String roomCode;
	//是否黑名单
	private String isBlack;
	//添加时间
	private Date addTime;
	//ip
	private String ip;
	//设备编号
	private String deviceNo;
	//app端记录id
	private Long appId;

	// Constructors

	/** default constructor */
	public AddressBook() {
	}

	

	/** full constructor */
	public AddressBook(String nickName, String areaCode, String regionCode, String storeyCode, String unitCode, String roomCode,
			String isBlack, Date addTime, String ip, String deviceNo,Long appId) {
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
		this.appId=appId;
	}

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

	@Column(name = "nickName", length = 45)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "areaCode", nullable = false, length = 45)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "regionCode", nullable = false, length = 45)
	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name = "storeyCode", nullable = false, length = 45)
	public String getStoreyCode() {
		return this.storeyCode;
	}

	public void setStoreyCode(String storeyCode) {
		this.storeyCode = storeyCode;
	}

	@Column(name = "unitCode", nullable = false, length = 45)
	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	@Column(name = "roomCode", nullable = false, length = 45)
	public String getRoomCode() {
		return this.roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	@Column(name = "isBlack", nullable = false)
	public String getIsBlack() {
		return this.isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ip", nullable = false, length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "deviceNo", nullable = false, length = 45)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	@Column(name = "appId", nullable = false)
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	
}