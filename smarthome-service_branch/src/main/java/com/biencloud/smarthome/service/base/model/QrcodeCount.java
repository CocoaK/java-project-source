package com.biencloud.smarthome.service.base.model;

@SuppressWarnings("serial")
public class QrcodeCount extends BaseEntity{
    private Long id;

    private String type;

    private Integer sipCount;

    private Integer lockCount;

    private Integer houseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSipCount() {
        return sipCount;
    }

    public void setSipCount(Integer sipCount) {
        this.sipCount = sipCount;
    }

    public Integer getLockCount() {
        return lockCount;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}