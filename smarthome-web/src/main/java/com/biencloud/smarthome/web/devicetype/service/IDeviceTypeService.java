package com.biencloud.smarthome.web.devicetype.service;

import java.util.List;

import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;

/**
 * 类名称：IDeviceTypeService 
 * 类描述： 设备类型调用服务接口
 * @author: kouy
 * @version: 0.1
 * @date: 2012-11-28 上午9:46:56
 */
public interface IDeviceTypeService {

	/**
	 * 查询所有的设备类型。
	 * @return
	 */
	/**
	 * 方法的描述: 查询所有的设备类型。
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-28 上午9:47:17
	 * @return
	 */
	public List<DeviceTypeVO> queryAllDeviceTypes();
}
