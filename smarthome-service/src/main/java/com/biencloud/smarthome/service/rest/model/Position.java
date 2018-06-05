package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

import com.biencloud.smarthome.service.base.model.BaseEntity;

@SuppressWarnings("serial")
public class Position extends BaseEntity{
    private Long id;

    private String sipUid;

    private String longitude;

    private String latitude;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSipUid() {
        return sipUid;
    }

    public void setSipUid(String sipUid) {
        this.sipUid = sipUid == null ? null : sipUid.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}