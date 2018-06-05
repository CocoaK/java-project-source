package com.biencloud.smarthome.service.app.vo;
/**
 * 
 * 类名称：AppVO 
 * 类描述： app vo类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-12 下午8:18:27
 */
public class AppVO {
	// 设备编号
	private String deviceNo;		
	// 登陆结果
	private String result;
	//数据服务器ip
	private String dataServerIP;
	//文件服务器ip
	private String fileServerIP;
	//socket服务器ip
	private String socketServerIP;
	//冲突MAC
	private String conflictMac;
	//是否是新注册
	private boolean newFlag;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getConflictMac() {
		return conflictMac;
	}

	public void setConflictMac(String conflictMac) {
		this.conflictMac = conflictMac;
	}

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}

}
