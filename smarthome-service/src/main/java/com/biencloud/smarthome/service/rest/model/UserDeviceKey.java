package com.biencloud.smarthome.service.rest.model;

@SuppressWarnings("serial")
public class UserDeviceKey extends Device{
    private Long userId;

    private Long deviceId;
    
    private int type;			//用户设备类型，1:主人用户，0:非主人用户
    
    private int status;		//用户设备状态：默认0,用户设备免打扰：2，其他状态未定
    
    public UserDeviceKey(){};
    
    public UserDeviceKey(Long userId, Long deviceId){
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}