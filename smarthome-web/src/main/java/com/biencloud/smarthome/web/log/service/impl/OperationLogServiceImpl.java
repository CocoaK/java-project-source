package com.biencloud.smarthome.web.log.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IOperationLogService;
import com.biencloud.smarthome.web.log.vo.OperationLogVO;
import com.biencloud.smarthome.web.wsclient.stub.OperationLog;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

public class OperationLogServiceImpl extends BaseService<OperationLogVO> implements IOperationLogService{
	
	private static final String OPERATE_TIME = "operateTime";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	
	@Override
	public PagingVO<OperationLogVO> queryOperationLogForPaging(
			String logId,String operateUser, String operateTime, String menuCode,
			String operateResult, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService()
				.queryOperationLogForPaging(logId,operateUser, operateTime, menuCode, operateResult, pageNum, pageSize);
		PagingVO<OperationLogVO> pagingVO = convertToPagingVO(paging,OPERATE_TIME,BEGIN_TIME,END_TIME);
		return pagingVO;
	}

	@Override
	public void saveOrUpdateOperationLog(OperationLogVO operationLog) {
		OperationLog _operationLog = new OperationLog();
		//OperationLogVO对象有一个Date属性，将其转换成XMLGregorianCalendar
		//将OperationLogVO对象属性值拷贝到OperationLog对象中
		copyProperties(operationLog,_operationLog,false,OPERATE_TIME,BEGIN_TIME,END_TIME);
		getSmartHomeService().saveOrUpdateOperationLog(_operationLog);
	}

	@Override
	public OperationLogVO getOperationLogById(String logId) {
		OperationLogVO operationLogVO = new OperationLogVO();
		OperationLog operationLog = getSmartHomeService().getOperationLogById(logId);
		//OperationLog对象有一个XMLGregorianCalendar属性，将其转换成Date
		//将OperationLog对象属性值拷贝到OperationLogVO对象中
		copyProperties(operationLog,operationLogVO,true,OPERATE_TIME,BEGIN_TIME,END_TIME);
		return operationLogVO;
	}

	@Override
	public PagingVO<OperationLogVO> queryOperationLogPaging(OperationLogVO operationLogVO, int pageNum, int pageSize) {
		OperationLog operationLog = new OperationLog();
		copyProperties(operationLogVO,operationLog,false,OPERATE_TIME,BEGIN_TIME,END_TIME);
		Paging paging =  getSmartHomeService().queryOperationLogPaging(operationLog, pageNum, pageSize);
		PagingVO<OperationLogVO> pagingVO = convertToPagingVO(paging,OPERATE_TIME,BEGIN_TIME,END_TIME);
		return pagingVO;
	}

}
