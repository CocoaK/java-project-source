package com.biencloud.smarthome.web.log.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.ISystemLogService;
import com.biencloud.smarthome.web.log.vo.SystemLogVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.SystemLog;

public class SystemLogServiceImpl extends BaseService<SystemLogVO> implements ISystemLogService{
	
	private static final String OPERATE_TIME = "operateTime";
	/**
	 * 查询系统日志并分页
	 * @author: Cocoa
	 * @version: 0.1
	 * @date: 2012-5-4
	 * @param String logId
	 * @param String operateUser
	 * @param String operateTime
	 * @param String menuCode
	 * @param pageNum
	 * @param pageSize
	 * @return PagingVO
	 */
	@Override
	public PagingVO<SystemLogVO> querySystemLogForPaging(String logId,String operateUser,
			String operateTime, String menuCode, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().querySystemLogForPaging(logId,operateUser, operateTime, menuCode, pageNum, pageSize);
		PagingVO<SystemLogVO> pagingVO = convertToPagingVO(paging,OPERATE_TIME);
		return pagingVO;
	}
	
	@Override
	public SystemLogVO getSystemLogById(String logId) {
		SystemLogVO systemLogVO = new SystemLogVO();
		SystemLog systemLog = getSmartHomeService().getSystemLogById(logId);
		//SystemLogVO对象有个Date属性，将其转换成XMLGregorianCalendar
		//将SystemLogVO对象属性值拷贝到SystemLog对象中
		copyProperties(systemLog,systemLogVO,true,OPERATE_TIME);
		return systemLogVO;
	}

	@Override
	public void saveOrUpdateSystemLog(SystemLogVO systemLog) {
		SystemLog _systemLog = new SystemLog();
		//SystemLog对象有一个XMLGregorianCalendar属性，将其转换成Date
		//将SystemLog对象属性值拷贝到SystemLogVO对象中
		copyProperties(systemLog,_systemLog,false,OPERATE_TIME);
		getSmartHomeService().saveOrUpdateSystemLog(_systemLog);	
	}
}
