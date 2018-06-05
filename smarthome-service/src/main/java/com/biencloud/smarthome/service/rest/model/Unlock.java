package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

public class Unlock {
    private Long id;

    private String deviceNo;	//门口机设备编号

    private String roomNo;		//手机房号
    
    private String type;		//开门类型(1室内机，2门口机)

    private String fromSipid;	//from sip账号

    private String toSipid;		//to sip账号

    private String picUrl;		//抓拍图片url(相对路径)

    private Date createTime;	

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
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFromSipid() {
		return fromSipid;
	}

	public void setFromSipid(String fromSipid) {
		this.fromSipid = fromSipid;
	}
	
	public String getToSipid() {
		return toSipid;
	}

	public void setToSipid(String toSipid) {
		this.toSipid = toSipid;
	}

	public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}