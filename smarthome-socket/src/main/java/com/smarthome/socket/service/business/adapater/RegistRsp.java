package com.smarthome.socket.service.business.adapater;

/**
 * 
 * 类名称：RegistObj 类描述：注册对象
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-4 下午9:31:32
 */
public class RegistRsp {
	// 错误代码:0：失败，1：成功
	private String code;
	// 设备编号
	private String deviceNo;
	// 数据服务器ip
	private String dataServerIP;
	// 文件服务器ip
	private String fileServerIP;
	// socket服务器ip
	private String socketServerIP;
	// json数据类型
	private String jsonType;

	public String getJsonType() {
		return jsonType;
	}

	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataServerIP() {
		return dataServerIP;
	}

	public void setDataServerIP(String dataServerIP) {
		this.dataServerIP = dataServerIP;
	}

	public String getFileServerIP() {
		return fileServerIP;
	}

	public void setFileServerIP(String fileServerIP) {
		this.fileServerIP = fileServerIP;
	}

	public String getSocketServerIP() {
		return socketServerIP;
	}

	public void setSocketServerIP(String socketServerIP) {
		this.socketServerIP = socketServerIP;
	}
    
}
