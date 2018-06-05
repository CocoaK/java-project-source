package com.biencloud.smarthome.web.net.vo;
/**
 * 
 * 类名称：NetVO 
 * 类描述：VO类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-17 下午2:58:55
 */
public class NetVO {
	//ip
	private String ip;
	//ping结果
	private String pingResult;
	//操作
	private String action;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPingResult() {
		return pingResult;
	}

	public void setPingResult(String pingResult) {
		this.pingResult = pingResult;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
    
	
}
