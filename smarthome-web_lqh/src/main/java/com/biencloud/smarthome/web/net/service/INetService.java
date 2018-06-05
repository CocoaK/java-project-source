package com.biencloud.smarthome.web.net.service;



/**
 * 
 * 类名称：INetService 
 * 类描述： 网络service
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-17 下午7:27:25
 */
public interface INetService {

	/**
	 * 
	 * 方法的描述: ping ip功能
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-17 下午7:28:16
	 * @param ip
	 * @return
	 */
	public String ping(String ip);
}
