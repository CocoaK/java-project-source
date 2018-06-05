package com.smarthome.socket.service.vo;

/**
 * 
 * 类名称：HeartBeatVO 类描述： 心跳 VO类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 29 Jun 2012 10:30:01
 */
public class HeartBeatVO extends BaseVO {
	// 消息
	private String message;
	//设备编号
	private String deviceNo;
	//服务器时间（毫秒）
	private Long serverTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}
    
	
}
