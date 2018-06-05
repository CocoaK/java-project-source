package com.biencloud.smarthome.web.log.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.vo.OperationLogVO;

public interface IOperationLogService{
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
	public PagingVO<OperationLogVO> queryOperationLogForPaging(String logId,String operateUser,String operateTime,
							String menuCode,String operateResult, int pageNum, int pageSize);
	
	/**
	 * 描述：保存或修改操作日志
	 * @param OperationLogVO 操作日志实体对象
	 * @return 
	 */
	public void saveOrUpdateOperationLog(OperationLogVO operationLog);
	
	/**
	 * 描述：根据Id查询操作日志
	 * @param logId 操作日志Id
	 * @return OperationLogVO 操作日志实体对象
	 */
	public OperationLogVO getOperationLogById(String logId);
	
	/**
	 * 
	 * 方法的描述: 查询操作日志并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 下午2:26:46
	 * @param operationLogVO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PagingVO<OperationLogVO> queryOperationLogPaging(OperationLogVO operationLogVO, int pageNum, int pageSize);
}
