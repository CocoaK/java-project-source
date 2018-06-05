package com.biencloud.smarthome.web.monitor.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 类名称：SceneVo 
 * 类描述：场景实体VO 
 * @author: jsun  
 * @version: 0.1
 * @date: 1.0 2012-6-7 上午10:57:28
 */
public class SceneVo extends BaseVO {
	private static final long serialVersionUID = -7276776273317984916L;
	//编号
	private Long id;
	//场景编号
	private String sceneId;
	//场景名称
	private String sceneName;
	//设备编号
	private String deviceNo;
	//场景模式
	private String mode;
	//是否在用
	private Integer isUse;
	//房号
	private String roomNo;
	//场景类型
	private String sceneKind;

	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getSceneKind() {
		return sceneKind;
	}
	public void setSceneKind(String sceneKind) {
		this.sceneKind = sceneKind;
	}
	
}
