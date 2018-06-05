package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

public class CallRecord {
    private Long id;

    private String callId;

    private Long deviceId;

    private String deviceCode;

    private String callType;

    private String caller;

    private Date callTime;

    private Long talkTime;

    private String videoLocalPath;

    private String videoNetPath;

    private String audioLocalPath;

    private String audioNetPath;

    private String pictureLocalPath;

    private String pictureNetPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId == null ? null : callId.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType == null ? null : callType.trim();
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public String getVideoLocalPath() {
        return videoLocalPath;
    }

    public void setVideoLocalPath(String videoLocalPath) {
        this.videoLocalPath = videoLocalPath == null ? null : videoLocalPath.trim();
    }

    public String getVideoNetPath() {
        return videoNetPath;
    }

    public void setVideoNetPath(String videoNetPath) {
        this.videoNetPath = videoNetPath == null ? null : videoNetPath.trim();
    }

    public String getAudioLocalPath() {
        return audioLocalPath;
    }

    public void setAudioLocalPath(String audioLocalPath) {
        this.audioLocalPath = audioLocalPath == null ? null : audioLocalPath.trim();
    }

    public String getAudioNetPath() {
        return audioNetPath;
    }

    public void setAudioNetPath(String audioNetPath) {
        this.audioNetPath = audioNetPath == null ? null : audioNetPath.trim();
    }

    public String getPictureLocalPath() {
        return pictureLocalPath;
    }

    public void setPictureLocalPath(String pictureLocalPath) {
        this.pictureLocalPath = pictureLocalPath == null ? null : pictureLocalPath.trim();
    }

    public String getPictureNetPath() {
        return pictureNetPath;
    }

    public void setPictureNetPath(String pictureNetPath) {
        this.pictureNetPath = pictureNetPath == null ? null : pictureNetPath.trim();
    }
}