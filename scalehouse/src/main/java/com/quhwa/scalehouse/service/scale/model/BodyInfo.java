package com.quhwa.scalehouse.service.scale.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BodyInfo {
    private String id;

    private String note;

    private String scaleType;
    
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date recordTime;

    private String unitType;

    private String weight;

    private String bmi;

    private String boneMass;

    private String bodyFat;

    private String muscle;

    private String bodyWater;

    private String visceralFat;

    private String bmr;
    
    private String personId;
    
    private String uploaded;
    
    private String mac;
    
    private String name;
    
    private String type;
    
    private String model;
    
    private Integer deviceId;
    
    private String appType;
   

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getScaleType() {
		return scaleType;
	}



	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}



	public Date getRecordTime() {
		return recordTime;
	}



	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}



	public String getUnitType() {
		return unitType;
	}



	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}



	public String getWeight() {
		return weight;
	}



	public void setWeight(String weight) {
		this.weight = weight;
	}



	public String getBmi() {
		return bmi;
	}



	public void setBmi(String bmi) {
		this.bmi = bmi;
	}



	public String getBoneMass() {
		return boneMass;
	}



	public void setBoneMass(String boneMass) {
		this.boneMass = boneMass;
	}



	public String getBodyFat() {
		return bodyFat;
	}



	public void setBodyFat(String bodyFat) {
		this.bodyFat = bodyFat;
	}



	public String getMuscle() {
		return muscle;
	}



	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}



	public String getBodyWater() {
		return bodyWater;
	}



	public void setBodyWater(String bodyWater) {
		this.bodyWater = bodyWater;
	}



	public String getVisceralFat() {
		return visceralFat;
	}



	public void setVisceralFat(String visceralFat) {
		this.visceralFat = visceralFat;
	}



	public String getBmr() {
		return bmr;
	}



	public void setBmr(String bmr) {
		this.bmr = bmr;
	}



	public String getPersonId() {
		return personId;
	}



	public void setPersonId(String personId) {
		this.personId = personId;
	}

	

	public String getUploaded() {
		return uploaded;
	}



	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
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
		return "BodyInfo [id=" + id + ", note=" + note + ", scaleType="
				+ scaleType + ", recordTime=" + recordTime + ", unitType="
				+ unitType + ", weight=" + weight + ", bmi=" + bmi
				+ ", boneMass=" + boneMass + ", bodyFat=" + bodyFat
				+ ", muscle=" + muscle + ", bodyWater=" + bodyWater
				+ ", visceralFat=" + visceralFat + ", bmr=" + bmr
				+ ", personId=" + personId + ", uploaded=" + uploaded + "]";
	}

	

	
    
   /* public static void main(String[] args){
    	String jsonString = "[{\"note\":1312331,\"scaleType\":\"CA\",\"recordTime\":\"2018-04-17 10:47:36\",\"unitType\":\"00\",\"weight\":54,\"bmi\":12.5,\"boneMass\":12.5,\"bodyFat\":12.5,\"muscle\":12.5,\"bodyWater\":12.5,\"visceralFat\":12.5,\"bmr\":12.5,\"personAccount\":\"kkkk\"},{\"note\":1312331,\"scaleType\":\"CA\",\"recordTime\":\"2018-04-17 10:47:36\",\"unitType\":\"00\",\"weight\":54,\"bmi\":12.5,\"boneMass\":12.5,\"bodyFat\":12.5,\"muscle\":12.5,\"bodyWater\":12.5,\"visceralFat\":12.5,\"bmr\":12.5,\"personAccount\":\"kkkk\"}]";
    }*/
}