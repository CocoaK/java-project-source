package com.biencloud.smarthome.service.device.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.device.model.DevicePassword;

/**
 * 
 * 类名称：IDevicePasswordService 
 * 类描述： 设备开锁服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午3:24:35
 */
public interface IDevicePasswordService extends IService<DevicePassword,Long>{
	/**
	 * 方法的描述: 根据设备编号，查询设备开锁密码
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-6 下午2:46:01
	 * @param deviceNo
	 * @return DevicePassword
	 */
	public List<DevicePassword> queryDevicePassword(String deviceNo);
	
	/**
	 * 方法的描述: 保存设备开锁密码，推送本设备的密码到设备所属单元的所有门口机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-15 下午6:45:14
	 * @param devicePassword
	 */
	public void saveAndPushDevicePassword(DevicePassword devicePassword);
	
	/**
	 * 方法的描述: 根据设备Id查询设备密码
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-17 下午3:00:42
	 * @param deviceId
	 * @return
	 */
	public DevicePassword getDevicePasswordByDeviceId(String deviceId,String targetDeviceId);
	
	/**
	 * 
	 * 方法的描述: 验证开锁密码是否匹配
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-20 上午11:05:06
	 * @param deviceNo目标
	 * @param password
	 * @return
	 */
	public boolean validatePasswd(String deviceNo,String password);
	
	/**
	 * 方法的描述: 根据目标设备的设备编号查询设备开锁密码
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-10 下午2:01:21
	 * @param targetDeviceNo
	 * @return
	 */
	public List<DevicePassword> queryDevicePasswordByTargetDeviceNo(String targetDeviceNo);

}
