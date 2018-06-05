package com.smarthome.socket.service.business.adapater.json;

import com.smarthome.socket.service.business.adapater.base.Base;
/**
 * 
 * 类名称：ComfirmJson 
 * 类描述： 确认Json类，用于终端和服务器上层确认
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:11:31
 */
public class ComfirmJson extends Base {
	//推送id
	protected Long pushId;

	public Long getPushId() {
		return pushId;
	}

	public void setPushId(Long pushId) {
		this.pushId = pushId;
	}

}
