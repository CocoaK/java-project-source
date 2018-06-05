package com.biencloud.smarthome.service.app.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.app.vo.AppVO;

/**
 * 
 * 类名称：IAppService 
 * 类描述： app专用业务处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-31 上午9:02:27
 */
public interface IAppService {
	/**
	 * 
	 * 方法的描述: app登陆
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午8:19:40
	 * @param device
	 * @return
	 */
	public AppVO login(AppLoginVO loginInfo);
	/**
	 * 
	 * 方法的描述: 离线
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-13 下午8:16:43
	 * @param deviceNo
	 * @return
	 */
	public boolean outLine(String mac);
	/**
	 * 
	 * 方法的描述:更新设备状态 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-2 下午4:27:06
	 */
	public void updateDeviceStatusForAll(String status);
	/**
	 * 
	 * 方法的描述: 更新设备状态
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-5 上午9:49:17
	 * @param status
	 * @param mac
	 */
	public void updateDeviceStatusByMac(@WebParam String status,String mac);
	/**
	 * 
	 * 方法的描述: 查询定时器时间设置更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-11 下午3:36:52
	 */	
	public String queryCronTimeUpdate();
	
	/**
	 * 
	 * 方法的描述: wifi模块登录方法，没有房号等信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2016-5-11 下午3:36:52
	 */	
	public AppVO easyLogin(AppLoginVO loginInfo);
}
