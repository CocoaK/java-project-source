package com.biencloud.smarthome.web.device.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceIpVO;

/**
 * 类名称：IDeviceIpService 
 * 类描述： 设备IP地址web端接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-24 上午11:15:34
 */
public interface IDeviceIpService {
	
	/**
	 * 方法的描述: 根据条件查询设备IP列表。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:14:20
	 * @param deviceIp
	 * @param pageNum
	 * @param pageSize
	 * @return PagingVO
	 */
	public PagingVO<DeviceIpVO> queryDeviceIpForPaging(DeviceIpVO deviceIp, int pageNum,
			int pageSize);
	
	/**
	 * 方法的描述: 根据IP地址查询设备ip列表。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:14:41
	 * @param ipAddress
	 * @param distrcitId
	 * @return
	 */
	public DeviceIpVO queryDeviceIpByIp(String ipAddress,String distrcitId);
	
	/**
	 * 方法的描述: 保存IP地址段。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:14:57
	 * @param deviceIp
	 * @return
	 */
	public int saveSubnetIps(DeviceIpVO deviceIp);
}
