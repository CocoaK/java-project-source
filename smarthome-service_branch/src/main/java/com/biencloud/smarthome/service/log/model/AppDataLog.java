package com.biencloud.smarthome.service.log.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：AppDataLog 
 * 类描述： app请求数据日志实体类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-17 下午3:30:36
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_appdata_log")
public class AppDataLog extends BaseEntity{
	//编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	//请求设备编号
	@Column(name="device_no")
	private String deviceNo;
	//请求设备名称
	@Column(name="device_name")
	private String deviceName;
	//完整房号
	@Column(name="full_room_no")
	private String fullRoomNo;
	//位置
	@Column(name="position")
	private String position;
	//ip地址
	@Column(name="ip")
	private String ip;
	//mac地址
	@Column(name="mac")
	private String mac;
	//请求时间
	@Column(name="request_time")
	private Date requestTime;
	//动作
	@Column(name="action")
	private String action;
	//终端请求数据
	@Column(name="request_data")
	private String requestData;
	//服务器响应数据
	@Column(name="response_data")
	private String responseData;
	//请求结果
	@Column(name="result")
	private String result;
	//请求结果说明
	@Column(name="result_desc")
	private String resultDesc;
	//备注
	@Column(name="remark")
	private String remark;
	//开始时间
	@Transient
	private Date beginTime;
	//结束时间
	@Transient
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getFullRoomNo() {
		return fullRoomNo;
	}

	public void setFullRoomNo(String fullRoomNo) {
		this.fullRoomNo = fullRoomNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
