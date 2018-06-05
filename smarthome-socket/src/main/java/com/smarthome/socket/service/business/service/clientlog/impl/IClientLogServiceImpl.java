package com.smarthome.socket.service.business.service.clientlog.impl;

import com.smarthome.socket.service.business.service.BaseService;
import com.smarthome.socket.service.business.service.clientlog.IClientLogService;
import com.smarthome.socket.wsservice.stub.ClientLog;

/**
 * 
 * 类名称：IClientLogServiceImpl 
 * 类描述： 终端日志接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-24 下午2:59:13
 */
public class IClientLogServiceImpl extends BaseService implements IClientLogService{

	@Override
	public boolean saveClientLog(ClientLog clientLog) {
		
		return super.getSmartHomeTcpService().saveClientLog(clientLog);
	}

}
