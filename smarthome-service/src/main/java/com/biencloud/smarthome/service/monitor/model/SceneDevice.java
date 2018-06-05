package com.biencloud.smarthome.service.monitor.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：SceneDevice 
 * 类描述：场景设备实体类 
 * @author kouy  
 * @version 0.1
 * @date 2012-6-5 下午2:37:28
 */
@Entity
@Table(name = "t_scene_device")
public class SceneDevice extends BaseEntity implements java.io.Serializable {

	// Fields
    //ID
	private Long id;
	
	//设备名称
	private String deviceName;
	//坐标位置
	private String position;
	//状态,0表示关闭，1表示开启
	private String status;
	//app端设备Id
	private String deviceId;
	//添加时间
	private Date addTime;
	//位置名称
	private String positionName;
	//类型
	private String kind;
	//设备编号
	private String deviceNo;
	//房间编号
	private String roomNo;
	//场景编号
	private Long sceneId;
	

	// Constructors

	/** default constructor */
	public SceneDevice() {
	}
    
	

	public SceneDevice(String positionName) {
		super();
		this.positionName = positionName;
	}



	/** minimal constructor */
	public SceneDevice(String deviceName, String position, String status, String deviceId, Date addTime, String kind,String positionName,String deviceNo,String roomNo) {		
		this.deviceName = deviceName;
		this.position = position;
		this.status = status;
		this.deviceId = deviceId;
		this.addTime = addTime;
		this.kind = kind;
		this.positionName=positionName;
		this.deviceNo=deviceNo;
		this.roomNo=roomNo;
	}

	

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	@Column(name = "deviceName", nullable = false, length = 45)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "position",  length = 20)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "deviceId", nullable = false, length = 45)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "positionName", length = 45)
	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "kind", nullable = false, length = 10)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	@Column(name = "deviceNo", nullable = false, length = 45)
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	@Column(name = "roomNo", nullable = false, length = 10)
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Column(name = "sceneId", nullable = true, length = 20)
	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	
}