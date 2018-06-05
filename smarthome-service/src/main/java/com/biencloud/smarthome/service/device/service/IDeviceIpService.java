package com.biencloud.smarthome.service.device.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.DeviceIp;

/**
 * 类名称：IDeviceIpService 
 * 类描述： 设备IP领域服务接口。
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-15 下午1:58:13
 */
public interface IDeviceIpService extends IService<DeviceIp,String>{
	
	/**
	 * 方法的描述: 查询设备IP列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-15 上午10:23:31
	 * @param deviceIp
	 * @param pageNum 页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<DeviceIp> queryDeviceIpForPaging(DeviceIp deviceIp, int pageNum,
			int pageSize);
	
	/**
	 * 方法的描述: 根据id查询设备IP。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-15上午10:24:41
	 * @param deviceId 设备Id
	 * @return
	 */
	public DeviceIp queryDeviceIpById(String deviceId);
	
	/**
	 * 方法的描述: 根据IP和小区查询设备IP对象。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-15 上午10:25:14
	 * @param ipAddress	ip地址
	 * @param districtId 小区Id
	 * @return
	 */
	public DeviceIp queryDeviceIpByIp(String ipAddress,String districtId);
	/**
	 * 
	 * 方法的描述: 通过小区编号和ip查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-10 上午9:30:47
	 * @param ipAddress
	 * @param areaCode
	 * @return
	 */
	public DeviceIp queryDeviceIpByIpAndAreaCode(String ipAddress,String areaCode);
	
	/**
	 * 方法的描述: 保存网段ip地址
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-15 上午10:25:54
	 * @param deviceIp 设备Ip
	 * @return
	 */
	public int saveSubnetIps(DeviceIp deviceIp);
	
	/**
	 * 方法的描述: 删除网段IP
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-15 上午10:26:34
	 * @param deviceIp 设备对象
	 * @return 1表示删除成功，0表示删除失败
	 */
	public int deleteSubnetIps(DeviceIp deviceIp);
	
	/**
	 * 方法的描述: 修改设备ip
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:26:58
	 * @param deviceIp
	 * @return 1表示修改成功，0表示修改失败
	 */
	public int updateDeviceIp(DeviceIp deviceIp);
	/**
	 * 
	 * 方法的描述: 根据小区编号判断ip是否存在
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午2:40:46
	 * @param ip
	 * @return 存在为true,否则为false
	 */
	public boolean isExistIP(String areaNo,String ip);
	/**
	 * 
	 * 方法的描述: 判断ip是否已被使用
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午2:44:25
	 * @param areaNo
	 * @param ip
	 * @param deviceID
	 * @return 如果ip被用则返回该ip对象，否则为null
	 */
	public DeviceIp isUsedIP(String areaNo,String ip,String deviceID); 
	
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
	 * 方法的描述: 保存设备Ip
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-18 上午9:34:56
	 * @param deviceIp
	 */
	public void saveDeviceIp(DeviceIp deviceIp);
}
