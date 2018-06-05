package com.smarthome.socket.service.vo;

import com.smarthome.socket.service.business.adapater.RegistRsp;

/**
 * 
 * 类名称：LoginVO 类描述：登陆信息VO
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-10 上午10:28:54
 */
public class LoginVO extends BaseVO {
	//注册回复对象
	private RegistRsp registRsp;
	//设备编号
	private String deviceNo;
	//冲突MAC
	private String conflictMac;

	public RegistRsp getRegistRsp() {
		return registRsp;
	}

	public void setRegistRsp(RegistRsp registRsp) {
		this.registRsp = registRsp;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getConflictMac() {
		return conflictMac;
	}

	public void setConflictMac(String conflictMac) {
		this.conflictMac = conflictMac;
	}

	

}
