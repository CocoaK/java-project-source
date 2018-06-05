package com.quhwa.scalehouse.service.scale.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
    private Integer id;

    private String account;

    private String password;
    
    private String username;
    
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date birthday;

    private String gender;

    private String heightUnit;

    private String height;

    private String weightUnit;

    private String targetWeight;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date recordTime;
    
    private String countryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit == null ? null : heightUnit.trim();
    }

   

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

   

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(String targetWeight) {
		this.targetWeight = targetWeight;
	}

	
	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", account=" + account + ", password="
				+ password + ", username=" + username + ", birthday="
				+ birthday + ", gender=" + gender + ", heightUnit="
				+ heightUnit + ", height=" + height + ", weightUnit="
				+ weightUnit + ", targetWeight=" + targetWeight
				+ ", recordTime=" + recordTime + "]";
	}

	
}