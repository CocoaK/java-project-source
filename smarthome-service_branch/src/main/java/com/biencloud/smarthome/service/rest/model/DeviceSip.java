package com.biencloud.smarthome.service.rest.model;

public class DeviceSip {
    private Long id;

    private Long deviceId;

    private String sipid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getSipid() {
        return sipid;
    }

    public void setSipid(String sipid) {
        this.sipid = sipid == null ? null : sipid.trim();
    }
}