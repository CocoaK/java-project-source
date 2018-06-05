package com.smarthome.socket.service.business.service.push;



/**
 * 
 * 类名称：IPushFinishService 
 * 类描述： 推送完成业务接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:16:37
 */
public interface IPushFinishService {
	/**
	 * 
	 * 方法的描述:插入已推送内容
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午1:55:34
	 * @param pushFinish
	 * @return
	 */
	
	public boolean insertPushFinish(Long pushId);
}
