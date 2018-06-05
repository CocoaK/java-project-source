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
 * 类名称：Publish 
 * 类描述： 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 上午10:59:08
 */
@Entity
@Table(name = "t_push")
public class Push implements java.io.Serializable {

	// Fields

	private Long id;
	//推送名称
	private String pushName;
	//推送类型
	private String pushKind;
	//推送内容
	private String pushContent;
	//推送版本
	private String pushVersion;
	//添加时间
	private Date addTime;
	//终端设备编号
	private String pushClientId;
	//文件大小
	private Long size;
	//描述
	private String pushDescription;

	// Constructors

	/** default constructor */
	public Push() {
	}

	/** minimal constructor */
	public Push(String pushName, String pushKind, String pushContent, Date addTime, String pushClientId) {
		this.pushName = pushName;
		this.pushKind = pushKind;
		this.pushContent = pushContent;
		this.addTime = addTime;
		this.pushClientId = pushClientId;
	}

	/** full constructor */
	public Push(String pushName, String pushKind, String pushContent, String pushVersion, Date addTime,
			String pushClientId,Long size) {
		this.pushName = pushName;
		this.pushKind = pushKind;
		this.pushContent = pushContent;
		this.pushVersion = pushVersion;
		this.addTime = addTime;
		this.pushClientId = pushClientId;
		this.size=size;
	}
	public Push(String pushName, String pushKind, String pushContent, Date addTime,
			String pushClientId,Long size) {
		this.pushName = pushName;
		this.pushKind = pushKind;
		this.pushContent = pushContent;
		this.addTime = addTime;
		this.pushClientId = pushClientId;
		this.size=size;
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

	public void setPushName(String publishName) {
		this.pushName = publishName;
	}

	@Column(name = "pushKind", nullable = false, length = 50)
	public String getPushKind() {
		return this.pushKind;
	}

	public void setPushKind(String pushKind) {
		this.pushKind = pushKind;
	}

	@Column(name = "pushContent", nullable = false, length = 65535)
	public String getPushContent() {
		return this.pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	@Column(name = "pushVersion", length = 50)
	public String getPushVersion() {
		return this.pushVersion;
	}

	public void setPushVersion(String pushVersion) {
		this.pushVersion = pushVersion;
	}

	@Column(name = "addTime", nullable = false, length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "pushClientID", nullable = false, length = 50)
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
	@Column(name = "pushDescription")
	public String getPushDescription() {
		return pushDescription;
	}

	public void setPushDescription(String pushDescription) {
		this.pushDescription = pushDescription;
	}
    
}