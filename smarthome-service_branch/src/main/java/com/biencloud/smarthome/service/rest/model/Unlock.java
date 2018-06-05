package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

public class Unlock {
    private Long id;

    private String deviceNo;	//门口机设备编号

    private String roomNo;		//手机房号

    private String doorSipId;	//门口机sip账号

    private String sipId;		//手机sip账号

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

    public String getDoorSipId() {
        return doorSipId;
    }

    public void setDoorSipId(String doorSipId) {
        this.doorSipId = doorSipId == null ? null : doorSipId.trim();
    }

    public String getSipId() {
        return sipId;
    }

    public void setSipId(String sipId) {
        this.sipId = sipId == null ? null : sipId.trim();
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