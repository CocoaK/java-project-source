package com.biencloud.smarthome.service.rest.model;

import com.biencloud.smarthome.service.base.model.BaseEntity;

@SuppressWarnings("serial")
public class UserRoomNoVo extends BaseEntity{
    private Long id;
    //房号
    private String roomNo;
    //设备名称
    private String deviceName;
    //设备sip账号
    private String deviceSipid;
    //房号绑定sip账号
    private Long sipid;
    //房号编号
    private Long houseId;
    //设备编号
    private Long deviceId;
    //设备别名
    private String deviceAlias;
    
    private String deviceNo;
    
    private String status;
    
    private String deviceType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getDeviceSipid() {
		return deviceSipid;
	}
	public void setDeviceSipid(String deviceSipid) {
		this.deviceSipid = deviceSipid;
	}

	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Long getSipid() {
		return sipid;
	}
	public void setSipid(Long sipid) {
		this.sipid = sipid;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoomNoVo other = (UserRoomNoVo) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		return true;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
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
	
}