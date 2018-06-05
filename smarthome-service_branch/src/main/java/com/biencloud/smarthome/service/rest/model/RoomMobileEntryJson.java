package com.biencloud.smarthome.service.rest.model;

import com.biencloud.smarthome.service.base.model.BaseEntity;

@SuppressWarnings("serial")
public class RoomMobileEntryJson extends BaseEntity{
	
    private String roomNo;

    private String targetUid;

    private Integer type;

    private String iosToken;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid == null ? null : targetUid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken == null ? null : iosToken.trim();
    }

}