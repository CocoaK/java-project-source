package com.biencloud.smarthome.service.rest.model;

public class UserRoomNo {
    private Long id;
    //房号
    private String roomNo;
    //房间编号
    private Long houseId;
    //sip账号
    private String sipid;
    //门口机编号
    private String deviceNo;
    //状态：0无效，1有效
    private String status;
    //单元号
    private String unitId;
    //小区编号
    private String districtId;
    
    private String deviceId;

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
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public Long getHouseId(){
    	return houseId;
    }
    
    public void setHouseId(Long houseId){
    	this.houseId=houseId;
    }
    
    public String getSipid() {
        return sipid;
    }

    public void setSipid(String sipid) {
        this.sipid = sipid == null ? null : sipid.trim();
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}