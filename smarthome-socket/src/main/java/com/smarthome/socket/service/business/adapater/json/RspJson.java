package com.smarthome.socket.service.business.adapater.json;

import com.smarthome.socket.service.business.adapater.base.Base;
/**
 * 
 * 类名称：RspJson 
 * 类描述：服务器回复json对象类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:13:24
 */
public class RspJson extends Base{
	//推送id
	protected Long pushId;

	public Long getPushId() {
		return pushId;
	}

	public void setPushId(Long pushId) {
		this.pushId = pushId;
	}
}
