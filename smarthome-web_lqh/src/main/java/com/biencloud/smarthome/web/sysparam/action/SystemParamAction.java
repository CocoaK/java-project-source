package com.biencloud.smarthome.web.sysparam.action;

import java.util.Date;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.sysparam.vo.SystemParamVO;

/**
 * 系统参数管理模块动作类。
 * @author kouy
 * @since 1.0 2012-4-16
 */
@SuppressWarnings("serial")
public class SystemParamAction extends BaseAction<SystemParamVO> {

	private String paramCode;
	private String paramName;
	
	private boolean successFlag;
	
	private SystemParamVO systemParam;
	
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public boolean getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public SystemParamVO getSystemParam() {
		return systemParam;
	}
	public void setSystemParam(SystemParamVO systemParam) {
		this.systemParam = systemParam;
	}

	
	/**
	 * 查看指定系统参数的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setSystemParam(getSysParamService().getSystemParamDetail(paramCode));
		return SUCCESS;
	}
	
	/**
	 * 查询系统参数。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<SystemParamVO> page = getPage();
		if(page == null)
			page = new PagingVO<SystemParamVO>();
				
		PagingVO<SystemParamVO> pagingVO = getSysParamService().querySystemParams(
				paramName, page.getPageNum(), page.getPageSize());
		
		setPage(pagingVO);
		return SUCCESS;
	}
	
	/**
	 * 获取系统参数信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setSystemParam(getSysParamService().getSystemParamDetail(paramCode));
		return SUCCESS;
	}
	
	/**
	 * 更新系统参数信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		systemParam.setUpdatedUser(getLoginName());
		systemParam.setUpdatedTime(new Date());
		getSysParamService().updateSystemParam(systemParam);
		setSuccessFlag(true);
		return SUCCESS;
	}
	
	private ISysParamService sysParamService;

	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
}
