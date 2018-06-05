package com.biencloud.smarthome.service.device.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;

/**
 * 设备管理领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-7
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IDeviceService extends IService<Device, String> {

	/**
	 * 方法的描述: 根据条件查询设备列表。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-7 下午1:59:10
	 * @param device
	 * @return
	 */
	public List<Device> queryDevices(Device device);
	
	/**
	 * 方法的描述:保存设备
	 * @author: ykou
	 * @date: 2012-5-7 下午1:59:10
	 * @param device 设备对象
	 * @return 1表示保存成功，0表示保存失败
	 */
	public int saveDevice(Device device);
	
	/**
	 * 方法的描述:更新设备
	 * @author: ykou
	 * @date: 2012-5-7 下午1:59:10
	 * @param device 设备对象，updaeType修改类型
	 * @return 1表示更新成功，0表示更新失败，2表示同一房间的名称有重复，更新失败。
	 */
	public int updateDevice(Device device,String updaeType);
	/**
	 * 
	 * 方法的描述:ip冲突情况下 更新设备与ip
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-17 上午11:23:52
	 */
	public Device updateDeviceForIPConflict(Device device,DeviceIp deviceIp);
	
	/**
	 * 方法的描述:删除设备
	 * @author: ykou
	 * @date: 2012-5-7 下午1:59:10
	 * @param: device 设备对象
	 * @return: 1表示删除成功，0表示删除失败
	 */
	public int deleteDevice(Device device);

	/**
	 * 方法的描述: 根据设备编码查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-7 下午2:03:25
	 * @param deviceId
	 * @return
	 */
	public Device queryDeviceById(String deviceId);
	
	/**
	 * 方法的描述: 查询设备列表并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-7 下午2:03:57
	 * @param device
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<Device> queryDeviceForPaging(Device device,int pageNum,int pageSize);
	/**
	 * 
	 * 方法的描述: 根据mac地址查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-16 下午2:34:00
	 * @param mac
	 * @return
	 */
	public Device queryDeviceByMac(String mac);
	/**
	 * 
	 * 方法的描述: 根据房产编号信息进行查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-27 下午3:51:43
	 * @param deviceCode
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @return
	 */
	public Device queryDeviceByMac(String mac,String areaNo,String regionNo,String buildingNo,String unitNo,String houseNo);
	
	/**
	 * 
	 * 方法的描述: 查询单元下的公共设备和业主自己的设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 上午10:11:23
	 * @param unitId roomId
	 * @return List
	 */
	public List<Device> queryOwnerUnitDevice(String unitId,String roomId);
	
	/**
	 * 
	 * 方法的描述: 查询小区所有围墙机和门口机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 上午10:18:11
	 * @param distrcitId
	 * @return
	 */
	public List<Device> queryPropertyDevice(String distrcitId);
	
	/**
	 * 
	 * 方法的描述: 验证密码
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 上午11:15:10
	 * @param passwd
	 * @return
	 */
	public boolean validatePasswd(String deviceCode,String passwd);
	
	/**
	 * 
	 * 方法的描述: 根据设备编码查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午12:06:02
	 * @param deviceCode
	 * @return Device
	 */
	public Device queryDeviceByCode(String deviceCode);
	
	

	/**
	 * 
	 * 方法的描述: 查询所有设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午8:53:34
	 * @param groupIds:小区ID对应的群组Id(组织表Id) ,deviceTypes：设备类型列表,为空则不做查询条件
	 * @param deviceType
	 * @return	List
	 */
	public List<Device> queryDevices(List<String> groupIds,List<String> deviceTypes);
	
	/**
	 * 
	 * 方法的描述: 根据房号查询单个设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-7 上午10:17:29
	 * @param roomId
	 * @return
	 */
	public Device queryDeviceByRoomId(String roomId);
	
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
	 * 方法的描述: 根据条件查询户户通设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-18 上午9:38:55
	 * @param device
	 * @return
	 */
	public List<Device> queryFamilyDevice(Device device);
	
	/**
	 * 
	 * 方法的描述: 根据设备编号查询所在位置的监控设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-26 下午4:22:11
	 * @param device
	 * @return
	 */
	public List<Device> queryMonitorDevice(Device device);
	
	/**
	 * 通过设备编号获取设备编码，获取不到返回空串。
	 * @param deviceId 设备编号
	 * @return
	 */
	public String getDeviceCodeById(String deviceId);
	
	/**
	 * 
	 * 方法的描述: 根据房号，判断是否关联有设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-7-9 下午3:10:24
	 * @return
	 */
	public boolean isHaveDevice(String roomId);
	/**
	 * 
	 * 方法的描述: 查询小区内除当前设备之外的所有设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-13 下午6:44:01
	 * @param areaNo 小区编号
	 * @param currentDeviceNo 当前设备编号
	 * @return
	 */
	public List<Device> queryAllDeviceExcludeCurrentDevice(String areaNo,String currentDeviceNo);
	/**
	 * 
	 * 方法的描述:  判断设备名称是否重复
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午3:17:54
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @param deviceType
	 * @param deviceName
	 * @param existDeviceId
	 * @return
	 */
	public boolean isRepeatDeviceName(String areaNo,String regionNo,String buildingNo,String unitNo,String houseNo,String deviceName,String existDeviceId);
	/**
	 * 
	 * 方法的描述: 判断非室内机位置是否重复
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午3:50:39
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param deviceType
	 * @param postion
	 * @param existDeviceId
	 * @return
	 */
	public boolean isRepeatDevicePosition(String areaNo,String regionNo,String buildingNo,String unitNo,String deviceType,String position,String existDeviceId);
	/**
	 * 
	 * 方法的描述: 同一个房号下存在多个室内机，这些室内机的设备编号为同一个，查询同一个房号下室内机编号
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午3:21:41
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @return
	 */
	public String queryIndoorDeviceNo(String areaNo,String regionNo,String buildingNo,String unitNo,String houseNo);
	/**
	 * 
	 * 方法的描述: 查询某个房号下的设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午4:11:45
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @return
	 */
	public List<Device> queryIndoorDevice(String areaNo,String regionNo,String buildingNo,String unitNo,String houseNo);
	/**
	 * 
	 * 方法的描述: 查询非室内机设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-7 下午5:36:19
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param houseNo
	 * @param postion
	 * @return
	 */
	public List<Device> queryNotIndoorDevice(String areaNo,String regionNo,String buildingNo,String unitNo,String postion);
	/**
	 * 
	 * 方法的描述:查询所有 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-2 下午4:30:26
	 * @return
	 */
	public List<Device> findAll();
	/**
	 * 
	 * 方法的描述: 根据设备状态查询，获得设备列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-23 下午4:17:14
	 * @param status 设备状态
	 * @return
	 */
	
	public List<Device> findDeviceByStatus(String status);
	/**
	 * 
	 * 方法的描述: 查询设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-2 下午5:44:31
	 * @param areaNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param deviceType
	 * @return
	 */
	public List<Device> findAll(String areaNo,String regionNo,String buildingNo,String unitNo,String deviceType,String currentDeviceId);
	/**
	 * 
	 * 方法的描述: 根据id删除设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-6 下午2:06:40
	 * @param deviceId
	 * @return
	 */
	public boolean deleteDeviceById(String deviceId);
	/**
	 * 
	 * 方法的描述: 根据设备编号查询，但排除某个设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-6 下午3:03:41
	 * @param deviceNo
	 * @param excludeDeviceNo
	 * @return
	 */
	public Device queryDeviceByDeviceNo(String deviceNo,String excludeMac);
	
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
	public List<Device> queryOwnerUnlockDevice(String unitId, String districtId);
	
	/**
	 * 方法的描述: 查询小区内的所有单元门口机和围墙机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-11 上午11:37:55
	 * @param districtId
	 * @return
	 */
	public List<Device> queryGateCardDevices(Device device);
	
	/**
	 * 方法的描述: 判断设备是否有关联小区，true为有关联，false为没有关联
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:07:22
	 * @param districtId
	 * @return
	 */
	public boolean isHaveDistrict(String districtId);
	
	/**
	 * 方法的描述: 判断是否有关联的区域，true为有关联，false为没有关联
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:09:37
	 * @param areaId
	 * @return
	 */
	public boolean isHaveArea(String areaId);
	
	/**
	 * 方法的描述: 判断是否有关联的楼栋，true为有关联，false为没有关联
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:09:59
	 * @param buildingId
	 * @return
	 */
	public boolean isHaveBuilding(String buildingId);
	
	/**
	 * 方法的描述: 判断设备是否有关联的单元，true为有关联，false为没有关联
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:20:54
	 * @param unitId
	 * @return
	 */
	public boolean isHaveUnit(String unitId);
	
	/**
	 * 方法的描述: 判断是否有关联的房号，true为有关联，false为没有关联
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 下午3:10:21
	 * @param roomId
	 * @return
	 */
	public boolean isHaveRoomId(String roomId);
	
	/**
	 * 方法的描述: 根据单元id查询单元机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-15 下午6:10:18
	 * @param unitNo
	 * @return
	 */
	public List<Device> queryUnitDoorDeviceByUnitId(String unitId);
	
	/**
	 * 
	 * 方法的描述: 根据房号信息和设备类型，查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-21 上午11:40:29
	 * @param deviceType
	 * @param districtNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param roomNo
	 * @return
	 */
	public Device queryDeviceByRoomNo(String districtNo,String regionNo,String buildingNo,String unitNo,String roomNo);
	
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
