package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

public class DeviceAccessories {
    private Long id;

    private Long deviceId;

    private String name;

    private String type;

    private Integer status;

    private String code;

    private Date createTime;
    
    private String defenceArea;
    
    private Integer continueAlarm;

    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getDefenceArea() {
		return defenceArea;
	}

	public void setDefenceArea(String defenceArea) {
		this.defenceArea = defenceArea;
	}

	public Integer getContinueAlarm() {
		return continueAlarm;
	}

	public void setContinueAlarm(Integer continueAlarm) {
		this.continueAlarm = continueAlarm;
	}
    
}