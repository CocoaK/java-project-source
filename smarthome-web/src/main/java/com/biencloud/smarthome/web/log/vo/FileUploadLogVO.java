package com.biencloud.smarthome.web.log.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;


public class FileUploadLogVO extends BaseVO {

	private static final long serialVersionUID = -409913626132453328L;
	private String deviceNo;//设备编号
	private String exception;//异常
	private String filePath;//文件路径
	private String format;//文件格式
	private Long id;//主键
	private String name;//名称
	private String size;//大小
	private String source;//来源
	private String userIp;//用户IP
	private Date addTime;//添加时间
	private Date addEndTime;//添加结束时间
	private Date addStartTime;//添加开始时间
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getAddEndTime() {
		return addEndTime;
	}
	public void setAddEndTime(Date addEndTime) {
		this.addEndTime = addEndTime;
	}
	public Date getAddStartTime() {
		return addStartTime;
	}
	public void setAddStartTime(Date addStartTime) {
		this.addStartTime = addStartTime;
	}


	
}
