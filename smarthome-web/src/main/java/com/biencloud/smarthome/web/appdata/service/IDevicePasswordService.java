package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.json.PasswordJson;

/**
 * 
 * 类名称：IDevicePasswprdService 
 * 类描述： 设备开锁密码app接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午5:08:37
 */
public interface IDevicePasswordService {
	/**
	 * 方法的描述:保存设备开锁密码 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-3 下午5:12:39
	 * @param jsonString
	 */
	public Json save(String jsonString);
	
	/**
	 * 方法的描述: 获取室内机的开锁密码
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-9-10 下午1:54:39
	 * @param 单元机本机的设备编号
	 * @return
	 */
	public PasswordJson queryPasswordByTargetDeviceNo(String deviceNo);

}
