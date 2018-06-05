package com.biencloud.smarthome.service.log.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.DiviceRegeditLog;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IDiviceRegeditLogService 
 * 类描述：设备注册日志管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IDiviceRegeditLogService extends IService<DiviceRegeditLog, Long> {
	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：设备注册日志对象
	 * @return
	 */
	public List<DiviceRegeditLog> queryDiviceRegeditLogForList(DiviceRegeditLog paramsOb);
	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：设备注册日志对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<DiviceRegeditLog> queryDiviceRegeditLogForPaging(DiviceRegeditLog paramsOb, int pageNum, int pageSize);
	/**
	 * 更新设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:设备注册日志对象
	 */
	public void updateDiviceRegeditLog(DiviceRegeditLog entity) ;
	/**
	 * 保存设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:设备注册日志对象
	 */
	public void saveDiviceRegeditLog(DiviceRegeditLog entity);
	/**
	 * 
	 * 方法的描述: 获取单个设备注册日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：设备注册日志对象主键
	 * @return
	 */
	public DiviceRegeditLog getDiviceRegeditLog(String entityId);
	/**
	 * 
	 * 方法的描述: 删除设备注册日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：设备注册日志对象
	 */
	public void DelDiviceRegeditLog(DiviceRegeditLog entity);
}
