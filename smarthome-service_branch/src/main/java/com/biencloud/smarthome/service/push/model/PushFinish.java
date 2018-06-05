package com.biencloud.smarthome.service.push.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类名称：PushFinish 类描述：已推送实体
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 上午11:03:32
 */
@Entity
@Table(name = "t_push_finish")
public class PushFinish implements java.io.Serializable {

	// Fields

	private Long id;
	//推送名称
	private String pushName;
	//推送内容
	private String pushContent;
	//推送类型
	private String pushKind;
	//添加时间
	private Date addTime;
	//推送完成时间
	private Date pushFinishTime;
	//设备编号
	private String pushClientId;
	//文件大小
	private Long size;
	//版本
	private String pushVersion;
	//描述
	private String pushDescription;
	// Constructors

	/** default constructor */
	public PushFinish() {
	}

	/** full constructor */
	public PushFinish(String pushName, String pushContent, String pushKind, Date addTime, Date pushFinishTime, String pushClientId,
			Long size, String pushVersion) {
		this.pushName = pushName;
		this.pushContent = pushContent;
		this.pushKind = pushKind;
		this.addTime = addTime;
		this.pushFinishTime = pushFinishTime;
		this.pushClientId = pushClientId;
		this.size = size;
		this.pushVersion = pushVersion;
	}

	public PushFinish(String pushName, String pushContent, String pushKind, Date addTime, Date pushFinishTime, String pushClientId) {
		this.pushName = pushName;
		this.pushContent = pushContent;
		this.pushKind = pushKind;
		this.addTime = addTime;
		this.pushFinishTime = pushFinishTime;
		this.pushClientId = pushClientId;

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

	@Column(name = "pushName", nullable = false)
	public String getPushName() {
		return this.pushName;
	}

	public void setPushName(String pushName) {
		this.pushName = pushName;
	}

	@Column(name = "pushContent", nullable = false, length = 65535)
	public String getPushContent() {
		return this.pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	@Column(name = "pushKind", nullable = false, length = 50)
	public String getPushKind() {
		return this.pushKind;
	}

	public void setPushKind(String pushKind) {
		this.pushKind = pushKind;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "pushFinishTime", nullable = false, length = 19)
	public Date getPushFinishTime() {
		return this.pushFinishTime;
	}

	public void setPushFinishTime(Date pushFinishTime) {
		this.pushFinishTime = pushFinishTime;
	}

	@Column(name = "pushClientId", nullable = false)
	public String getPushClientId() {
		return this.pushClientId;
	}

	public void setPushClientId(String pushClientId) {
		this.pushClientId = pushClientId;
	}

	@Column(name = "size")
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Column(name = "pushVersion", length = 50)
	public String getPushVersion() {
		return this.pushVersion;
	}

	public void setPushVersion(String pushVersion) {
		this.pushVersion = pushVersion;
	}
	@Column(name = "pushDescription")
	public String getPushDescription() {
		return pushDescription;
	}

	public void setPushDescription(String pushDescription) {
		this.pushDescription = pushDescription;
	}
    
}