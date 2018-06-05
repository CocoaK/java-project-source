package com.biencloud.smarthome.service.log.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.SystemLog;

/**
 * 类名称：ISystemLogService 
 * 类描述： 系统日志接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-28 下午2:34:01
 */
public interface ISystemLogService extends IService<SystemLog, String>{
	/**
	 * 方法的描述：查询日志列表，分页。方法中参数如果为空，则不作为查询条件
	 * @author: ykou 
	 * @version: 0.1
	 * @date: 2012-5-9 下午8:45:57
	 * @param logId 日志编号
	 * @param operateTime 操作时间
	 * @param operateUser 操作用户名,如为空则不作为查询条件
	 * @param menuCode 菜单代码
	 * @param loginIp 用户登录IP
	 * @param details 操作内容
	 * @param operationCode 操作代码 01:表示查询操作，02:表示修改操作，03:表示增加操作，04:表示删除操作，00:表示其他操作
	 * @param operateResult 操作结果
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<SystemLog> querySystemLogForPaging(String logId,String operateUser,String operateTime,
							String menuCode, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述: 保存或者删除系统日志,true为保存成功，否则失败
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-9 下午8:45:57
	 * @param systemLog 系统日志实体
	 */
	public boolean saveOrUpdate(SystemLog systemLog);

	/**根据logid查询日志
	 * 方法的描述: 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 下午2:34:38
	 * @param logId 日志编号
	 * @return
	 */
	public SystemLog getSystemLogById(String logId);
}
