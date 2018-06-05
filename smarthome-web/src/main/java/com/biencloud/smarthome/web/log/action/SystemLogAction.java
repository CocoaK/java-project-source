package com.biencloud.smarthome.web.log.action;

import java.util.Date;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.ISystemLogService;
import com.biencloud.smarthome.web.log.vo.SystemLogVO;

/**
 * 系统日志管理模块动作类。
 * @author Cocoa
 * @since 1.0 2012-5-3
 */
public class SystemLogAction extends BaseAction<SystemLogVO>{

	private static final long serialVersionUID = 8978765403801771267L;
	private ISystemLogService systemLogService;
	public String errMessage;
	public String errLocation;
	public String operationCode;
	private SystemLogVO systemLog;
	public String logId;				//日志ID
	public String operateUser;			//操作用户
	public String operateTime;			//操作时间
	public String menuCode;			//菜单代码

	/**
	 * 描述：系统日志列表
	 * @return
	 * 
	 */
	public String systemLogList() throws Exception{
		systemLog = new SystemLogVO();
		try{
			PagingVO<SystemLogVO> page = getPage();
			if(page == null)
				page = new PagingVO<SystemLogVO>();
			PagingVO<SystemLogVO> listSystemLog = getSystemLogService()
					.querySystemLogForPaging(getLogId(),getOperateUser(), getOperateTime(), getMenuCode(), page.getPageNum(), page.getPageSize());
			if (listSystemLog != null){		
				setPage(listSystemLog);
			}else{
				systemLog.setMenuCode(getMenuCode());
				systemLog.setOperateUser(getOperateUser());
				systemLog.setOperateTime(new Date());
				systemLog.setOperationCode(getOperationCode());
				systemLog.setErrorMsg(getErrMessage());
				systemLog.setErrorLocation(getErrLocation());
				getSystemLogService().saveOrUpdateSystemLog(systemLog);	//写日志
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 描述：查看日志的详细信息
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String systemLogDetail() throws Exception{
		try{
			SystemLogVO sysLog = getSystemLogService().getSystemLogById(getRequest().getParameter("logId"));
			if(sysLog != null)
				setSystemLog(sysLog);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "viewdetail";
	}
	
	/**
	 * 描述：增加系统日志
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addSystemLog(SystemLogVO systemLog) throws Exception{
//		try{
//			logManageService.saveOrUpdateSystemLog(systemLog);
//			forward = "add_sucess";
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return null;
	}
	/**
	 * 描述：删除系统日志
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String delSystemLog(SystemLogVO systemLog) throws Exception{
//		try{
//			logManageService.delSystemLog(systemLog);
//			forward = "del_sucess";
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return null;
	}

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getErrLocation() {
		return errLocation;
	}

	public void setErrLocation(String errLocation) {
		this.errLocation = errLocation;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public SystemLogVO getSystemLog() {
		return systemLog;
	}

	public void setSystemLog(SystemLogVO systemLog) {
		this.systemLog = systemLog;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
}