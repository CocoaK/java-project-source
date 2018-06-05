package com.biencloud.smarthome.web.qrcode.vo;

import java.util.Date;

public class QrcodeVO {
    private Long id;

    private String type;

    private String sipUid;

    private String doorSipid;

    private String houseId;

    private Integer status;

    private Integer deviceType;
    
    private Date createTime;
    
    private String roomNo;
    
    private String roomName;
    
    private String pwd;
    
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSipUid() {
        return sipUid;
    }

    public void setSipUid(String sipUid) {
        this.sipUid = sipUid == null ? null : sipUid.trim();
    }

    public String getDoorSipid() {
        return doorSipid;
    }

    public void setDoorSipid(String doorSipid) {
        this.doorSipid = doorSipid == null ? null : doorSipid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

}