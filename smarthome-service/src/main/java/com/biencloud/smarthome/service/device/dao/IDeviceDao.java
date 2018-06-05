package com.biencloud.smarthome.service.device.dao;

import java.util.List;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.Device;

/**
 * 设备管理数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-7
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IDeviceDao extends IDao<Device, String> {
	
	/**
	 * 方法的描述: 保存设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:30:38
	 * @param device 设备对象
	 * @return
	 */
	public int saveDevice(Device device);

	/**
	 * 方法的描述: 更新设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:31:13
	 * @param device 设备对象
	 * @return
	 */
	public int updateDevice(Device device);
	
	/**
	 * 方法的描述: 删除设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:31:36
	 * @param device 设备对象
	 * @return
	 */
	public int deleteDevice(Device device);
//	
//	/**
//	 * 根据Ip查询设备对象
//	 * @param String device
//	 * @return
//	 */
//	public Device queryDeviceByIp(String deviceIp,String districtId);
	/**
	 * 
	 * 方法的描述: 根据设备编号查询设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午3:46:38
	 * @param deviceNO
	 * @return
	 */
	public Device queryDeviceByDeviceNo(String deviceNo);
	 /**
	  * 
	  * 方法的描述: 查询所有
	  * @author: kouy  
	  * @version: 0.1
	  * @date: 2012-8-1 上午11:10:22
	  * @return
	  */
	public List<Device>  findAll(String areaNo);
}
