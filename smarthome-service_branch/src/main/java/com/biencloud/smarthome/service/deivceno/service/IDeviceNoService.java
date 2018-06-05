package com.biencloud.smarthome.service.deivceno.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.device.model.Device;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IDeviceNoService 
 * 类描述： 设备编号管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:56:32
 */
public interface IDeviceNoService extends IService<DeviceNo, String> {

	/**
	 * 查询设备编号列表。
	 * @param device 设备编号对象
	 * @return
	 */
	/**
	 * 
	 * 方法的描述: 查询设备编号列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:56:42
	 * @param paramsOb：设备编号对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<DeviceNo> queryDeviceForPaging(DeviceNo paramsOb, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述: 后台自动生成设备编号列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:57:32
	 * @return
	 */
	public String generatorDeviceNo();
	
	/**
	 * 
	 * 方法的描述: 更新设备编号的设备ID
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:57:42
	 * @param device：设备对象
	 * @param deviceNo：设备编号
	 * @return
	 */
	public boolean updateDeviceId(Device device,String deviceNo);
	/**
	 * 
	 * 方法的描述: 通过房号产生设备编号
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-8 下午1:54:23
	 * @param houseNo
	 * @param deviceNo
	 * @return
	 */
	public String generatorDeviceNoByFullHouseNo(String houseNo);
	/**
	 * 
	 * 方法的描述: 通过房号查询设备编号
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-10-8 下午2:01:40
	 * @param houseNo
	 * @return
	 */
	public String queryDeviceNoByFullHouseNo(String houseNo);
	/**
	 * 
	 * 方法的描述: 根据设备编号查询实体
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:58:05
	 * @param diveceNo:设备编号
	 * @return
	 */
	public DeviceNo getDeviceNoByNo(String diveceNo);
}
