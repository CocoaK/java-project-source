package com.biencloud.smarthome.service.device.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.DeviceIp;

/**
 * 设备IP数据访问对象接口。
 * @author Cocoa
 * @since 1.0 2012-5-15
 */
public interface IDeviceIpDao extends IDao<DeviceIp,String>{

	/**
	 * 方法的描述: 保存设备IP
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:32:21
	 * @param DeviceIp
	 * @return
	 */
	public int saveDeviceIp(DeviceIp DeviceIp);

	/**
	 * 方法的描述: 更新设备IP
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:32:47
	 * @param DeviceIp
	 * @return
	 */
	public int updateDeviceIp(DeviceIp DeviceIp);
	
	/**
	 * 方法的描述:删除设备IP 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:33:08
	 * @param deviceIp
	 * @return
	 */
	public int deleteDeviceIp(DeviceIp deviceIp);
	
	/**
	 * 
	 * 方法的描述: 修改ip的设备id为空
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-6 下午5:03:47
	 * @param deviceId
	 */
	public void clearDeviceId(String deviceId);
	
	/**
	 * 方法的描述: 根据设备删除设备IP
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-20 上午10:52:02
	 * @param deviceId
	 */
	public void deleteDeviceIpByDevice(String deviceId);
}
