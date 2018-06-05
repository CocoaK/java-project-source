package com.biencloud.smarthome.web.monitor.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 类名称：SceneDeviceVo 
 * 类描述： 场景设备VO
 * @version: 0.1
 * @date: 1.0 2012-6-7 上午10:29:59
 */
public class SceneDeviceVo extends BaseVO {
	private static final long serialVersionUID = 2247353048479902362L;
	//编号
	private Long id;
	//场景Id
	private Long sceneId;
	//场景设备Id
	private String deviceId;
	//场景设备名称
	private String deviceName;
	//设备编号
	private String deviceNo;
	//位置
	private String position;
	//位置名称
	private String positionName;
	//场景类型
	private String kind;
	//状态
	private String status;
	//房间编号
	private String roomNo;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
}
