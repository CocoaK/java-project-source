package com.biencloud.smarthome.service.log.model;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * FileUploadLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_file_upload_log")
public class FileUploadLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	// Fields

	private Long id;
	private String userIp;
	private String source;
	private String name;
	private String format;
	private String size;
	private String exception;
	private String deviceNo;
	private String filePath;
	private Date addTime;
	private Date addEndTime;
	private Date addStartTime;

	// Constructors

	/** default constructor */
	public FileUploadLog() {
	}

	/** full constructor */
	public FileUploadLog(String userIp, String source, String name, String format, String size, String exception, String deviceNo, String filePath) {
		this.userIp = userIp;
		this.source = source;
		this.name = name;
		this.format = format;
		this.size = size;
		this.exception = exception;
		this.deviceNo = deviceNo;
		this.filePath = filePath;
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

	@Column(name = "user_ip", length = 20)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "source", length = 10)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "format", length = 20)
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "size", length = 10)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "exception", length = 65535)
	public String getException() {
		return this.exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Column(name = "device_no", length = 100)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Column(name = "file_path", length = 100)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Transient
	public Date getAddEndTime() {
		return addEndTime;
	}

	public void setAddEndTime(Date addEndTime) {
		this.addEndTime = addEndTime;
	}

	@Transient
	public Date getAddStartTime() {
		return addStartTime;
	}

	public void setAddStartTime(Date addStartTime) {
		this.addStartTime = addStartTime;
	}
	
	

}