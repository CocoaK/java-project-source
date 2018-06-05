package com.biencloud.smarthome.web.monitor.service;

/**
 * 远程监控Sevice
 * 
 * @author jsun  
 * @since 1.0 2012-6-5
 */
public interface IMonitorService {
	/**
	 * 远程开锁
	 * 
	 * @param targetDeviceCode 目标设备编号
	 * @param personalDeviceCode 个人设备编号
	 * @param personalDevicePwd 个人设备密码
	 * @return
	 */
	public boolean remoteUnlock(String targetDeviceCode, String personalDeviceCode,
			String personalDevicePwd,String userType);
}
