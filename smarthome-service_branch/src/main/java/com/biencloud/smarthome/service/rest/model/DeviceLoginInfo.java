package com.biencloud.smarthome.service.rest.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：DeviceLoginInfo 类描述： 设备实体对象
 * 
 * @author: ykou
 * @version: 0.1
 * @date: 2016-5-20 下午3:17:57
 */
public class DeviceLoginInfo extends BaseEntity {

	private static final long serialVersionUID = -6558411525652831913L;

	private Long deviceId;	//ID
	private String districtNo; // 小区号
	private String areaNo; // 区域号
	private String buildingNo; // 楼宇号
	private String unitNo; // 单元号
	private String roomNo; // 房号
	//private Date createdTime;	//注册时间
	//private String createdUser;	//创建人
	private String sipId;	//设备别名
	private String sipPwd;	//sip账号密码
	private String deviceNo;	//设备编号
	private String deviceName;	//设备名称
	private String devicePwd;	//设备密码
	private String status;	//设备状态
	private String deviceType;	//设备类型
	//private String districtGateFlag;	//小区大门标识
	private String deviceMac;	//设备mac地址
	//private Date updatedTime;	//登录时间
	//private String updatedUser;
	private String deviceIp;	//设备IP
	//private String deviceRingUrl;	//设备铃声
	private String position;	//位置号
	//private String coordinate;	//坐标

	public DeviceLoginInfo() {
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getSipId() {
		return sipId;
	}

	public void setSipId(String sipId) {
		this.sipId = sipId;
	}

	public String getSipPwd() {
		return sipPwd;
	}

	public void setSipPwd(String sipPwd) {
		this.sipPwd = sipPwd;
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

	public String getDevicePwd() {
		return devicePwd;
	}

	public void setDevicePwd(String devicePwd) {
		this.devicePwd = devicePwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1959941635, 1952620985).append(this.deviceId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeviceLoginInfo)) {
			return false;
		}
		DeviceLoginInfo device = (DeviceLoginInfo) object;
		return new EqualsBuilder().append(this.deviceId, device.deviceId).isEquals();
	}
}