package com.biencloud.smarthome.service.rest.model;

public class DeviceUserVo {
    private Long userId;

    private Long deviceId;
    
    private int type;			//用户设备类型，1:主人用户，0:非主人用户
    
    private String username;
    
    public DeviceUserVo(){};
    
    public DeviceUserVo(Long userId, Long deviceId){
    	this.userId = userId;
    	this.deviceId = deviceId;
    };

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}