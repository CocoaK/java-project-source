package com.biencloud.smarthome.web.log.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：AppDataLogVO 
 * 类描述： app请求数据日志VO类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-17 下午3:30:36
 */
@SuppressWarnings("serial")
public class AppDataLogVO extends BaseVO{
	//编号
	private Integer id;
	//设备编号
	private String deviceNo;
	//设备名称
	private String deviceName;
	//完整房号
	private String fullRoomNo;
	//位置
	private String position;
	//设备ip
	private String ip;
	//设备mac
	private String mac;
	//请求时间
	private Date requestTime;
	//动作
	private String action;
	//请求数据
	private String requestData;
	//响应数据
	private String responseData;
	//请求结果
	private String result;
	//结果描述
	private String resultDesc;
	//备注
	private String remark;
	//请求开始时间
	private Date beginTime;
	//请求结束时间
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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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
}
