package com.biencloud.smarthome.service.rest.model;

public class MsgToken {
    private Long id;

    //1:安卓Jpush,2:安卓GCM,3:IOS
    private String type;

    private Long userId;

    private String token;
    /**token类型：安卓JPush*/
    public static final String TYPE_ANDROID_JPUSH = "1";
    /**token类型：安卓GCM*/
    public static final String TYPE_ANDROID_GCM = "2";
    /**token类型：IOS APNs*/
    public static final String TYPE_IOS_APNS = "3";
    /**token类型：IOS APNs 1byone*/
    public static final String TYPE_IOS_APNS_1BYONE = "4";
    /**token类型：安卓 JPUSH 1byone*/
    public static final String TYPE_ANDROID_JPUSH_1BYONE = "5";

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}