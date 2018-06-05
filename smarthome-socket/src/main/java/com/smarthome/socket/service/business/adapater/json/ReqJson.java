package com.smarthome.socket.service.business.adapater.json;

import com.smarthome.socket.service.business.adapater.base.Base;
/**
 * 
 * 类名称：ReqJson 
 * 类描述： 终端请求json类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:12:33
 */
public class ReqJson extends Base {
	//设备编号
	protected String deviceNo;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

}
