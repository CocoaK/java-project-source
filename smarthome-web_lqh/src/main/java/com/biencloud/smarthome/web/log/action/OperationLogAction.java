package com.biencloud.smarthome.web.log.action;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IOperationLogService;
import com.biencloud.smarthome.web.log.vo.OperationLogVO;

/**
 * 操作日志管理模块动作类。
 * @author Cocoa
 * @since 1.0 2012-5-3
 */
public class OperationLogAction extends BaseAction<OperationLogVO>{

	private static final long serialVersionUID = 8978765403801771267L;

	private IOperationLogService operationLogService;
	public String logId;					//日志ID
	private OperationLogVO operationLog;

	/**
	 * 描述：操作日志列表
	 * @return
	 *
	 */
	public String operationLogList() throws Exception{
		if(operationLog != null){
			setDateRange(operationLog.getBeginTime(), 
					operationLog.getEndTime());
		}else{
			operationLog = new OperationLogVO();
		}
		PagingVO<OperationLogVO> page = getPage();
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(this.getUserType())){
			operationLog.setOperateUser(this.getLoginName());
		}
		if(page == null)
			page = new PagingVO<OperationLogVO>();
		PagingVO<OperationLogVO> paging = getOperationLogService()
				.queryOperationLogPaging(operationLog, page.getPageNum(), page.getPageSize());
		List<OperationLogVO> list = new ArrayList<OperationLogVO>();
		if(paging!=null){
			List<OperationLogVO> operationLogs = paging.getResults();
			OperationLogVO log = new OperationLogVO();
			for(OperationLogVO vo : operationLogs){
				log = vo;
				log.setMenuCode(this.getText(vo.getMenuCode()));	//操作日志菜单国际化
				//操作日志操作代码国际化
				log.setOperationCode(this.getText(Constants.OPERATION_CODE+vo.getOperationCode()));
				list.add(log);
			}
			paging.setResults(list);
		}
		setPage(paging);
		return "list";
	}
	
	/**
	 * 描述：查看日志的详细信息
	 * @return
	 */
	public String operationLogDetail() throws Exception{
			OperationLogVO operLog = getOperationLogService().getOperationLogById(getLogId());
			operLog.setMenuCode(this.getText(operLog.getMenuCode()));	//操作日志菜单国际化
			//操作日志操作代码国际化
			operLog.setOperationCode(this.getText(Constants.OPERATION_CODE+operLog.getOperationCode()));
				setOperationLog(operLog);
		return "viewdetail";
	}
	
	/**
	 * 描述：查询操作日志
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryOperationLog() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 描述：增加操作日志
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addOperationLog() throws Exception {
		return SUCCESS;
	}
	/**
	 * 描述：删除操作日志
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String delOperationLog() throws Exception {
		return SUCCESS;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public OperationLogVO getOperationLog() {
		return operationLog;
	}

	public void setOperationLog(OperationLogVO operationLog) {
		this.operationLog = operationLog;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

}