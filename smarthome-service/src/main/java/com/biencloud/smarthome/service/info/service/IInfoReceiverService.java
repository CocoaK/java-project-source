package com.biencloud.smarthome.service.info.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiver;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IInfoReceiverService 
 * 类描述： 信息接收管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IInfoReceiverService extends IService<InfoReceiver, Long> {
	/**
	 * 查询信息接收列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：信息接收对象
	 * @return
	 */
	public List<InfoReceiver> queryInfoReceiverForList(InfoReceiver paramsOb);
	/**
	 * 查询信息接收列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：信息接收对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<InfoReceiver> queryInfoReceiverForPaging(InfoReceiver paramsOb, int pageNum, int pageSize);
	/**
	 * 更新信息接收
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:信息接收对象
	 */
	public void updateInfoReceiver(InfoReceiver entity) ;
	/**
	 * 保存信息接收
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:信息接收对象
	 */
	public void saveInfoReceiver(InfoReceiver entity);
	/**
	 * 
	 * 方法的描述: 获取单个信息接收对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：信息接收对象主键
	 * @return
	 */
	public InfoReceiver getInfoReceiver(String entityId);
	/**
	 * 
	 * 方法的描述: 删除信息接收对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：信息接收对象
	 */
	public void DelInfoReceiver(InfoReceiver entity);
	/**
	 * 
	 * 方法的描述: 查询5条业主未读信息量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:48
	 * @param paramsOb：信息接收对象
	 * @return
	 */
	public List<InfoReceiver> queryInfoReceiverForIndex(InfoReceiver paramsOb);
	
	/**
	 * 
	 * 方法的描述: 查询未读消息数量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午2:32:26
	 * @param paramsOb
	 * @return
	 */
	public Long getNoReadReceiverCount(InfoReceiver paramsOb);
	
	/**
	 * 根据房号列表查询信息
	 * @param houseIds
	 * @return
	 */
	public List<InfoReceiver> queryInfoReceiverByHouseIds(List<String> houseIds);
}
