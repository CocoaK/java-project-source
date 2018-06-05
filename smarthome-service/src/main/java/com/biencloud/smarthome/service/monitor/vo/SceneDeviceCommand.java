package com.biencloud.smarthome.service.monitor.vo;

/**
 * 场景设备Command
 * 
 * @author kouy
 * @since 2012-6-5
 */
public class SceneDeviceCommand {
	// app端场景id
	private String sceneId;
	// app端场景设备id
	private String deviceId;
	// 设备状态，0关闭，1打开
	private String status;
    
	public SceneDeviceCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public SceneDeviceCommand(String sceneId, String deviceId, String status) {
		super();
		this.sceneId = sceneId;
		this.deviceId = deviceId;
		this.status = status;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString()
	{
		return "sceneId:"+sceneId+",deviceId:"+deviceId+",status:"+status;
	}
}
