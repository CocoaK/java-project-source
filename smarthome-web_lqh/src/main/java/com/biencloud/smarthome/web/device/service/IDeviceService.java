package com.biencloud.smarthome.web.device.service;

import java.util.List;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.wsclient.stub.Device;

/**
 * 类名称：IDeviceService 
 * 类描述：设备管理服务接口 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-24 上午11:15:48
 */
public interface IDeviceService {

	/**
	 * 方法的描述: 根据条件查询设备列表。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:12:38
	 * @param deviceVO 设备对象，查询条件
	 * @return
	 */
	public List<DeviceVO> queryDevices(DeviceVO deviceVO);
	
	/**
	 * 方法的描述: 更新设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:13:05
	 * @param deviceVO
	 * @param updateType
	 * @return
	 */
	public int update(DeviceVO deviceVO,String updateType);
	
	/**
	 * 方法的描述: 根据设备编号查询设备列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:13:26
	 * @param deviceId
	 * @return DeviceVO
	 */
	public DeviceVO queryDeviceVOById(String deviceId);
	
	/**
	 * 方法的描述: 根据设备编列表分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:13:48
	 * @param device
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PagingVO<DeviceVO> queryDeviceForPaging(DeviceVO device,int pageNum,int pageSize);
	
	/**
	 * 
	 * 方法的描述: 查询单元下的公共设备和业主自己的设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 上午10:11:23
	 * @param unitId roomId
	 * @return List
	 */
	public List<DeviceVO> queryOwnerUnitDevice(String unitId,String roomId);
	
	/**
	 * 
	 * 方法的描述: 查询小区所有围墙机和门口机（远程开锁用）
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 上午10:18:11
	 * @param distrcitId
	 * @return List
	 */
	public List<DeviceVO> queryPropertyDevice(String distrcitId);
	/**
	 * 
	 * 方法的描述: 将Device转换成DeviceVO
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-31 下午4:01:28
	 * @param device
	 * @return DeviceVO
	 */
	public DeviceVO deviceToVO(Device device);
	/**
	 * 
	 * 方法的描述: DeviceVO转换成Device
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-31 下午4:02:13
	 * @param deviceVO
	 * @return Device
	 */
	public Device voToDevice(DeviceVO deviceVO);
	
	/**
	 * 
	 * 方法的描述: 根据组织小区ID，设备类型查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 上午9:45:09
	 * @param districtIds
	 * @param deviceTypes
	 * @return List
	 */
	public List<DeviceVO> queryDevices(List<String> districtIds,List<String> deviceTypes);
	
	/**
	 * 
	 * 方法的描述: 根据房号查询单个设备（多个中的一个）
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-7 上午10:34:36
	 * @param roomId
	 * @return
	 */
	public DeviceVO queryDeviceByRoomId(String roomId);
	
	/**
	 * 
	 * 方法的描述: 查询设备数，如果参数为空则不作为查询条件
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 下午8:02:45
	 * @param districtId
	 * @param status
	 * @return Long
	 */
	public Long deviceCount(String districtId,String status);
	
	/**
	 * 
	 * 方法的描述: 根据设备编号查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-18 下午12:36:43
	 * @param deviceCode
	 * @return DeviceVO
	 */
	public DeviceVO queryDeviceByCode(String deviceCode);
	
	/**
	 * 
	 * 方法的描述: 查询户户通设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-20 下午2:27:53
	 * @param device
	 * @return
	 */
	public List<DeviceVO> queryFamilyDevice(DeviceVO deviceVO);
	
	/**
	 * 
	 * 方法的描述: 根据房号查询是否有关联的设备。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-7-9 下午3:45:41
	 * @param roomId
	 * @return
	 */
	public boolean isHaveDevice(String roomId);
	
	/**
	 * 
	 * 方法的描述: 查询业主可以开锁的设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-10 下午3:50:58
	 * @param unitId
	 * @param districtId
	 * @return
	 */
	public List<DeviceVO> queryOwnerUnlockDevice(String unitId, String districtId);
		
	/**
	 * 方法的描述: 查询小区内的所有单元门口机和围墙机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-11 上午11:37:55
	 * @param districtId
	 * @return
	 */
	public List<DeviceVO> queryGateCardDevices(DeviceVO device);
	
	/**
	 * 方法的描述: 删除设备。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-20 上午11:14:34
	 * @param deviceId
	 */
	public boolean removeDeviceById(String deviceId);
	
	/**
	 * 方法的描述: 设备是否在线
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-9 上午10:28:21
	 * @param deviceId
	 * @return
	 */
	public boolean isDeviceOnline(String deviceId);
}
