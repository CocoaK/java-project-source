package com.smarthome.socket.service.vo;

import java.util.Date;

public class AuthSession {
    private Long id;

    private String sessionKey;

    private Long userId;

    private Date createTime;

    private Long activeTime;
    
    public static final String AUTH_SESSION_KEY = "AuthSession";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey == null ? null : sessionKey.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }
}