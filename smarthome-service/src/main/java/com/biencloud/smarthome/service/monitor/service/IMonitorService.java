package com.biencloud.smarthome.service.monitor.service;

/**
 * 类名称：IMonitorService 
 * 类描述： 监控业务接口
 * 
 * @author kouy  
 * @version 0.1
 * @date 2012-6-4 下午9:53:41
 */
public interface IMonitorService {
	/**
	 * 远程开锁
	 * 
	 * 开锁业务流程
	 * 需要3个参数(目标机编号, 个人机编号, 个人机密码)来授权开锁
	 * 目标机为除开室内机, 管理机以外的设备
	 * 个人机相对于物管用户为管理机, 相对于业主用户未室内机
	 * 
	 * @param targetDeviceCode 目标机CODE
	 * @param personalDeviceCode 个人机CODE
	 * @param personalDevicePwd 个人机密码
	 * @param userType 登录用户的类型
	 * @return
	 * @author jsun
	 */
	public boolean remoteUnlock(String targetDeviceCode, String personalDeviceCode,
			String personalDevicePwd,String userType);
}
