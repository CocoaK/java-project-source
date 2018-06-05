package com.biencloud.smarthome.web.log.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.vo.SystemLogVO;

public interface ISystemLogService{
	/**
	 * 描述：查询日志列表，分页。方法中参数如果为空，则不作为查询条件
	 * @param logId 日志编号
	 * @param operateTime 操作时间
	 * @param operateUser 操作用户名,如为空则不作为查询条件
	 * @param menuCode 菜单代码
	 * @param loginIp 用户登录IP
	 * @param details 操作内容
	 * @param SystemCode 操作代码
	 * @param operateResult 操作结果
	 * @param pageSize 每页条数
	 * @return
	 */
	
	public PagingVO<SystemLogVO> querySystemLogForPaging(String logId,String operateUser,String operateTime,
							String menuCode, int pageNum, int pageSize);
	
//	public void save(SystemLogVO systemLog);
//	
//	public void remove(SystemLogVO systemLog);
//	
	/**
	 * 方法的描述: 保存或更新系统日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 上午11:29:08
	 * @param systemLog
	 */
	public void saveOrUpdateSystemLog(SystemLogVO systemLog);
	
	/**
	 * 方法的描述: 根据ID获取系统日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 上午11:29:43
	 * @param logId
	 * @return
	 */
 	public SystemLogVO getSystemLogById(String logId);
}
