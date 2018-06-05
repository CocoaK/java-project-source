package com.biencloud.smarthome.web.appdata.json;

/**
 * 
 * 类名称：ServerTimeJson 类描述：服务器时间Json
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-4 下午4:20:12
 */
public class ServerTimeJson extends Json {
	// 服务器 时间毫秒数
	private Long serverTime;

	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}

	

}
