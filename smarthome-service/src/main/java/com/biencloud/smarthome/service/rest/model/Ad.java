package com.biencloud.smarthome.service.rest.model;

import java.util.Date;

public class Ad {
    private Integer adId;

    private String adName;

    private String adDesc;

    private String adPicUrl;

    private String adLinkUrl;

    private String adDetailPicUrl;

    private String typeCode;

    private String status;

    private String applyedUser;

    private Date applyedTime;

    private String publishedUser;

    private Date publishedTime;

    private Date adBeginTime;

    private Date adEndTime;

    private String locationCode;

    private String adSize;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public String getAdDesc() {
        return adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc == null ? null : adDesc.trim();
    }

    public String getAdPicUrl() {
        return adPicUrl;
    }

    public void setAdPicUrl(String adPicUrl) {
        this.adPicUrl = adPicUrl == null ? null : adPicUrl.trim();
    }

    public String getAdLinkUrl() {
        return adLinkUrl;
    }

    public void setAdLinkUrl(String adLinkUrl) {
        this.adLinkUrl = adLinkUrl == null ? null : adLinkUrl.trim();
    }

    public String getAdDetailPicUrl() {
        return adDetailPicUrl;
    }

    public void setAdDetailPicUrl(String adDetailPicUrl) {
        this.adDetailPicUrl = adDetailPicUrl == null ? null : adDetailPicUrl.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getApplyedUser() {
        return applyedUser;
    }

    public void setApplyedUser(String applyedUser) {
        this.applyedUser = applyedUser == null ? null : applyedUser.trim();
    }

    public Date getApplyedTime() {
        return applyedTime;
    }

    public void setApplyedTime(Date applyedTime) {
        this.applyedTime = applyedTime;
    }

    public String getPublishedUser() {
        return publishedUser;
    }

    public void setPublishedUser(String publishedUser) {
        this.publishedUser = publishedUser == null ? null : publishedUser.trim();
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Date getAdBeginTime() {
        return adBeginTime;
    }

    public void setAdBeginTime(Date adBeginTime) {
        this.adBeginTime = adBeginTime;
    }

    public Date getAdEndTime() {
        return adEndTime;
    }

    public void setAdEndTime(Date adEndTime) {
        this.adEndTime = adEndTime;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public String getAdSize() {
        return adSize;
    }

    public void setAdSize(String adSize) {
        this.adSize = adSize == null ? null : adSize.trim();
    }
}