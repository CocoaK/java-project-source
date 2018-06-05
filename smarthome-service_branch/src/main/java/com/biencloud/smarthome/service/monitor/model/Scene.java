package com.biencloud.smarthome.service.monitor.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类名称：Scene 
 * 类描述： 场景实体类
 * @author kouy  
 * @version 0.1
 * @date 2012-6-5 下午2:34:15
 */
@Entity
@Table(name = "t_scene")
public class Scene implements java.io.Serializable {

	// Fields
    //ID
	private Long id;
	//app端场景ID
	private String sceneId;
	//场景名称
	private String sceneName;
	//场景类别，0表示安防，1家庭控制
	private String sceneKind;
	//添加时间
	private Date addTime;
	//设备编号
	private String deviceNo;
	//场景模式
	private String mode;
	//场景是否使用,0表示未使用，1表示使用
	private Integer isUse;
	//房子编号
	private String roomNo;
	//场景设备集合
	//private Set<SceneDevice> sceneDeviceSet=new HashSet<SceneDevice>(0);

	// Constructors

	/** default constructor */
	public Scene() {
	}

	/** full constructor */
	public Scene(String sceneId, String sceneName, String sceneKind, Date addTime, String deviceNo,String mode,Integer isUse,String roomNo) {
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.sceneKind = sceneKind;
		this.addTime = addTime;
		this.deviceNo = deviceNo;
		this.mode=mode;
		this.isUse=isUse;
		this.roomNo=roomNo;
		//this.sceneDeviceSet=sceneDeviceSet;
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

	@Column(name = "sceneId", nullable = false, length = 10)
	public String getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	@Column(name = "sceneName", nullable = false, length = 45)
	public String getSceneName() {
		return this.sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	@Column(name = "sceneKind", nullable = false, length = 10)
	public String getSceneKind() {
		return this.sceneKind;
	}

	public void setSceneKind(String sceneKind) {
		this.sceneKind = sceneKind;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "deviceNo", nullable = false, length = 45)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	@Column(name = "mode",  length = 10)
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	/*@OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)   
    @JoinColumn(name = "sceneId")
	public Set<SceneDevice> getSceneDeviceSet() {
		return sceneDeviceSet;
	}

	public void setSceneDeviceSet(Set<SceneDevice> sceneDeviceSet) {
		this.sceneDeviceSet = sceneDeviceSet;
	}*/
	@Column(name = "isUse")
	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	@Column(name = "roomNo", nullable = false, length = 10)
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
    
}