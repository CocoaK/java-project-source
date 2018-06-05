package com.biencloud.smarthome.web.appdata.json;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：Json 类描述： json
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-14 下午7:26:01
 */
public class Json extends BaseVO{
	// 1表示成功，0表示失败
	public String code;
	// 设备编号
	public String deviceNo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

}
