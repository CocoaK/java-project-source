package com.biencloud.smarthome.service.log.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.OperationLog;

/**
 * 类名称：IOperationLogService 
 * 类描述： 操作日志接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-9  下午2:36:56
 */
public interface IOperationLogService extends IService<OperationLog, String>{
	/**
	 * 描述：查询日志列表，分页。方法中参数如果为空，则不作为查询条件
	 * @param operateTime 操作时间
	 * @param operateUser 操作用户名
	 * @param menuCode 菜单代码
	 * @param ip 用户登录IP
	 * @param details 操作内容
	 * @param operateCode 操作代码
	 * @param operateResult 操作结果
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<OperationLog> queryOperationLogForPaging(String logId,String operateUser,String operateTime,
							String menuCode,String operateResult, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述:保存或者修改操作日志
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-9 下午9:13:48
	 * @param operationLog操作日志实体对象
	 */
	public boolean saveOrUpdate(OperationLog operationLog);
	
	/**
	 * 方法的描述: 根据logid查询日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 下午2:36:15
	 * @param logId 日志Id
	 * @return
	 */
	public OperationLog getOperationLog(String logId);
	
	/**
	 * 
	 * 方法的描述: 查询操作日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 下午1:59:31
	 * @param operationLog 操作日志实体
	 * @param pageNum	页码
	 * @param pageSize	每页大小
	 * @return paging	分页
	 */
	public Paging<OperationLog> queryOperationLogForPaging(OperationLog operationLog, int pageNum, int pageSize);
	
	/**
	 * 方法的描述:删除3个月前的日志
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-7 下午6:03:32
	 */
	public void deleteOperationLogs();
	
	/**
	 * 方法的描述: 删除3个月前的数据库表中的操作日志。删除一个月以前的应用日志和JBoss日志，以免占用空间。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-3 下午3:51:21
	 */
	public void removeOldLogs();
}
