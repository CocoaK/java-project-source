package com.quhwa.scalehouse.service.scale.model;

import java.util.Date;

public class Device {
    private Integer id;

    private String name;

    private String type;
    
    private String model;

    private String status;

    private String mac;

    private String remark;
    
    private String deviceType;

    private Date createTime;
    
    private Integer personId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", type=" + type
				+ ", model=" + model + ", status=" + status + ", mac=" + mac
				+ ", remark=" + remark + ", createTime=" + createTime + "]";
	}
    
    
}