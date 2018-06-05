package com.biencloud.smarthome.service.rest.model;

public class DeviceConfig {

    private Long deviceId;

    private Integer status;

    private Integer defenceStatus;

    private String defenceTime;

    private String defenceDay;

    private Integer disarmStatus;

    private String disarmTime;

    private String disarmDay;

    private String ring;

    private Integer mute;

    private Integer volume;

    private Integer continueAlarm;
    
    private String pushSound;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDefenceStatus() {
        return defenceStatus;
    }

    public void setDefenceStatus(Integer defenceStatus) {
        this.defenceStatus = defenceStatus;
    }

    public String getDefenceTime() {
        return defenceTime;
    }

    public void setDefenceTime(String defenceTime) {
        this.defenceTime = defenceTime == null ? null : defenceTime.trim();
    }

    public String getDefenceDay() {
        return defenceDay;
    }

    public void setDefenceDay(String defenceDay) {
        this.defenceDay = defenceDay == null ? null : defenceDay.trim();
    }

    public Integer getDisarmStatus() {
        return disarmStatus;
    }

    public void setDisarmStatus(Integer disarmStatus) {
        this.disarmStatus = disarmStatus;
    }

    public String getDisarmTime() {
        return disarmTime;
    }

    public void setDisarmTime(String disarmTime) {
        this.disarmTime = disarmTime == null ? null : disarmTime.trim();
    }

    public String getDisarmDay() {
        return disarmDay;
    }

    public void setDisarmDay(String disarmDay) {
        this.disarmDay = disarmDay == null ? null : disarmDay.trim();
    }

    public String getRing() {
        return ring;
    }

    public void setRing(String ring) {
        this.ring = ring == null ? null : ring.trim();
    }

    public Integer getMute() {
        return mute;
    }

    public void setMute(Integer mute) {
        this.mute = mute;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getContinueAlarm() {
        return continueAlarm;
    }

    public void setContinueAlarm(Integer continueAlarm) {
        this.continueAlarm = continueAlarm;
    }

	public String getPushSound() {
		return pushSound;
	}

	public void setPushSound(String pushSound) {
		this.pushSound = pushSound;
	}
    
}