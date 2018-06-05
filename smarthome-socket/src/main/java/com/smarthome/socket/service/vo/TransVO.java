package com.smarthome.socket.service.vo;

/**
 * 
 * 类名称：TransVO 类描述： 透传数据 VO类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 29 Jun 2012 10:30:01
 */
public class TransVO {
	// json类型
	private String jsonType;
	//数据
	private Object data;
	//mac地址
	private String mac;
	//其他key值
	private String key;
	public String getJsonType() {
		return jsonType;
	}
	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
