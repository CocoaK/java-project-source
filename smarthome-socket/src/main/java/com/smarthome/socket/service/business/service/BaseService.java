package com.smarthome.socket.service.business.service;

import com.smarthome.socket.wsservice.stub.SmartHomeTcpService;

/**
 * 
 * 类名称：BaseService 
 * 类描述： 服务基类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-15 下午9:01:30
 */
public class BaseService {
	private SmartHomeTcpService smartHomeTcpService;

	public SmartHomeTcpService getSmartHomeTcpService() {
		return smartHomeTcpService;
	}

	public void setSmartHomeTcpService(SmartHomeTcpService smartHomeTcpService) {
		this.smartHomeTcpService = smartHomeTcpService;
	}

}
