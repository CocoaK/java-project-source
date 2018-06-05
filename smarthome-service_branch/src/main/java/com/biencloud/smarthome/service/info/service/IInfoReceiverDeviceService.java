package com.biencloud.smarthome.service.info.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IInfoReceiverDeviceService 
 * 类描述： 信息接收设备管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IInfoReceiverDeviceService extends IService<InfoReceiverDevice, Long> {
	/**
	 * 查询信息接收设备列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：信息接收设备对象
	 * @return
	 */
	public List<InfoReceiverDevice> queryInfoReceiverDeviceForList(InfoReceiverDevice paramsOb);
	/**
	 * 查询信息接收设备列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：信息接收设备对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<InfoReceiverDevice> queryInfoReceiverDeviceForPaging(InfoReceiverDevice paramsOb, int pageNum, int pageSize);
	/**
	 * 更新信息接收设备
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:信息接收设备对象
	 */
	public void updateInfoReceiverDevice(InfoReceiverDevice entity) ;
	/**
	 * 保存信息接收设备
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:信息接收设备对象
	 */
	public void saveInfoReceiverDevice(InfoReceiverDevice entity);
	/**
	 * 
	 * 方法的描述: 获取单个信息接收设备对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：信息接收设备对象主键
	 * @return
	 */
	public InfoReceiverDevice getInfoReceiverDevice(String entityId);
	/**
	 * 
	 * 方法的描述: 删除信息接收设备对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：信息接收设备对象
	 */
	public void DelInfoReceiverDevice(InfoReceiverDevice entity);
}
