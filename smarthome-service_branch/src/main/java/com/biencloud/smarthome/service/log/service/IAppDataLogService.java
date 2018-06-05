package com.biencloud.smarthome.service.log.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.AppDataLog;
/**
 * 类名称：IAppDataLogService 
 * 类描述： app端请求数据日志服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-17 下午3:36:22
 */
public interface IAppDataLogService extends IService<AppDataLog,Integer>{
	
	/**
	 * 方法的描述: 查询app请求数据并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-17 下午3:40:10
	 * @param appDataLog
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<AppDataLog> queryAppDataLogForPaging(AppDataLog appDataLog, int pageNum, int pageSize);

	/**
	 * 方法的描述: 删除X天之前的App请求日志，条件为参数列表中的参数值
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-30 下午4:21:03
	 */
	public void deleteAppDataLogs();
	
}
