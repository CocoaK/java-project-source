package com.biencloud.smarthome.service.info.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.DistrictData;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.model.NoticeData;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IInfoSendService 
 * 类描述： 信息发布管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:59:16
 */
public interface IInfoSendService extends IService<InfoSend, Long> {
	/**
	 * 
	 * 方法的描述: 查询信息发布列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:59:24
	 * @param paramsOb：信息发布对象
	 * @return
	 */
	public List<InfoSend> queryInfoSendForList(InfoSend paramsOb);
	/**
	 * 
	 * 方法的描述: 查询信息发布列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:59:40
	 * @param paramsOb：信息发布对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<InfoSend> queryInfoSendForPaging(InfoSend paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:07
	 * @param entity：信息发布对象
	 */
	public void updateInfoSend(InfoSend entity,String deviceTypeIds) ;
	/**
	 * 
	 * 方法的描述: 保存信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:18
	 * @param entity：信息发布对象
	 */
	public void saveInfoSend(InfoSend entity,String deviceTypeIds);
	/**
	 * 
	 * 方法的描述: 获取信息发布对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:31
	 * @param entityId：信息发布对象主键
	 * @return
	 */
	public InfoSend getInfoSend(String entityId);
	/**
	 * 
	 * 方法的描述: 删除信息发布对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:08
	 * @param entity:信息发布对象
	 */
	public void DelInfoSend(InfoSend entity);
	/**
	 * 
	 * 方法的描述: 根据小区ID查询下面的区域、楼宇
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:21
	 * @param ob:信息发布对象
	 * @return
	 */
	public List<DistrictData> queryAreaData(InfoSend ob);
	/**
	 * 
	 * 方法的描述: 发送定时任务
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:35
	 */
	public void sendTimingInfo();
	/**
	 * 
	 * 方法的描述:登录成功首页查询相关信息发布 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:46
	 * @param paramsOb:信息发布对象
	 * @return
	 */
	public List<InfoSend> queryInfoSendForIndex(InfoSend paramsOb);
	/**
	 * 
	 * 方法的描述: 登录成功首页查询相关信息发布数量统计
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:57
	 * @param paramsOb:信息发布对象
	 * @return
	 */
	public Long getInfoCount(InfoSend paramsOb);
	
	/**
	 * 小区公告分页信息
	 * @param roomNo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Paging<NoticeData> queryNoticeForPaging(String roomNo,
			int pageNum, int pageSize);
	
	/**
	 * 小区公告列表信息
	 * @param roomNo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<NoticeData> queryNoticeForList(String roomNos);
}
