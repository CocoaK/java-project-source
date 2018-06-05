package com.biencloud.smarthome.service.rest.model;

public class UserCameraKey extends Camera{
    private Long userId;

    private Long cameraId;
    
    private Integer indx;			//下标，位置编号

    public Long getUserId() {
        return userId;
    }
    public UserCameraKey(){}
    public UserCameraKey(Long userId,Long cameraId){
    	this.userId = userId;
    	this.cameraId = cameraId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }
	public Integer getIndx() {
		return indx;
	}
	public void setIndx(Integer indx) {
		this.indx = indx;
	}

}