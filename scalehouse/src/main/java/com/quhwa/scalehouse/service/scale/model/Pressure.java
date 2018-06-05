package com.quhwa.scalehouse.service.scale.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pressure {
	private Integer id;
	
    private String diastolic;

    private String systolic;

    private String pulse;
    
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date recordTime;

    private Integer personId;

    private String note;
    
    private String mac;
    
    private String name;
    
    private String type;
    
    private String model;
    
    private Integer deviceId;
    
    private String appType;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic == null ? null : diastolic.trim();
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic == null ? null : systolic.trim();
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse == null ? null : pulse.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Override
	public String toString() {
		return "Pressure [id=" + id + ", diastolic=" + diastolic
				+ ", systolic=" + systolic + ", pulse=" + pulse
				+ ", recordTime=" + recordTime + ", personId=" + personId
				+ ", note=" + note + ", mac=" + mac + ", name=" + name
				+ ", type=" + type + ", deviceId=" + deviceId + "]";
	}
    
	
    
}