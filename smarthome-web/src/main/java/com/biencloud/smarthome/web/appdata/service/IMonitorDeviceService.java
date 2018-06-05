package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.json.MonitorJson;
/**
 * 
 * 类名称：IMonitorService 
 * 类描述： App监控接口类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-21 下午2:29:10
 */
public interface IMonitorDeviceService {
	
	/**
	 * 方法的描述: 查询监控设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 下午2:02:45
	 * @param deviceCode 设备编号
	 * @return
	 */
	public MonitorJson queryMonitors(String deviceCode);

}
