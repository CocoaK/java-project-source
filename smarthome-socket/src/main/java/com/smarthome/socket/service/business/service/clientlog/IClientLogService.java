package com.smarthome.socket.service.business.service.clientlog;

import com.smarthome.socket.wsservice.stub.ClientLog;
/**
 * 
 * 类名称：IClientLogService 
 * 类描述： 终端日志接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-24 下午2:58:19
 */
public interface IClientLogService {
	/**
	 * 
	 * 方法的描述:保存终端日志 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:15:01
	 * @param clientLog
	 * @return
	 */
   public boolean saveClientLog(ClientLog clientLog);
}
