package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：Device 类描述： 设备实体对象
 * 
 * @author: ykou
 * @version: 0.1
 * @date: 2016-5-20 下午3:17:57
 */
public class Device extends BaseEntity {

	private static final long serialVersionUID = -6558411525652831913L;

	private Long deviceId;	//ID
	private String districtId; // 小区ID
	private String areaId; // 小区ID
	private String buildingId; // 楼宇ID
	private String unitId; // 单元ID
	private String roomId; // 房号ID
	private Date createdTime;	//注册时间
	private String createdUser;	//创建人
	private String deviceAlias;	//设备别名
	private String deviceCode;	//设备编号
	private String deviceName;	//设备名称
	private String devicePwd;	//设备密码
	private String deviceStatus;	//设备状态
	private String deviceType;	//设备类型
	private String districtGateFlag;	//小区大门标识
	private String deviceMac;	//设备mac地址
	private Date updatedTime;	//登录时间
	private String updatedUser;
	private String deviceIp;	//设备IP
	private String unitDoorNo;	//单元门口机编号
	private String position;	//位置号
	private String coordinate;	//坐标
	private String version;		//设备版本
	private String sipid;		//设备SIP用户名
	private String roomNo;		//完整房号

	public Device() {
	}


	public Long getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}


	public String getDistrictId() {
		return districtId;
	}


	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}


	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}


	public String getBuildingId() {
		return buildingId;
	}


	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}


	public String getUnitId() {
		return unitId;
	}


	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


	public String getRoomId() {
		return roomId;
	}


	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public String getCreatedUser() {
		return createdUser;
	}


	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}


	public String getDeviceAlias() {
		return deviceAlias;
	}


	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}


	public String getDeviceCode() {
		return deviceCode;
	}


	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public String getDevicePwd() {
		return devicePwd;
	}


	public void setDevicePwd(String devicePwd) {
		this.devicePwd = devicePwd;
	}


	public String getDeviceStatus() {
		return deviceStatus;
	}


	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}


	public String getDeviceType() {
		return deviceType;
	}


	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}


	public String getDistrictGateFlag() {
		return districtGateFlag;
	}


	public void setDistrictGateFlag(String districtGateFlag) {
		this.districtGateFlag = districtGateFlag;
	}


	public String getDeviceMac() {
		return deviceMac;
	}


	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}


	public Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	public String getUpdatedUser() {
		return updatedUser;
	}


	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}


	public String getDeviceIp() {
		return deviceIp;
	}


	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getUnitDoorNo() {
		return unitDoorNo;
	}


	public void setUnitDoorNo(String unitDoorNo) {
		this.unitDoorNo = unitDoorNo;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getCoordinate() {
		return coordinate;
	}


	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1959941635, 1952620985).append(this.deviceId).toHashCode();
	}

	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getSipid() {
		return sipid;
	}


	public void setSipid(String sipid) {
		this.sipid = sipid;
	}


	public String getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Device)) {
			return false;
		}
		Device device = (Device) object;
		return new EqualsBuilder().append(this.deviceId, device.deviceId).isEquals();
	}
}