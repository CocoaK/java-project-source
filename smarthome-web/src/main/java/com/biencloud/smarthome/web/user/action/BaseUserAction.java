package com.biencloud.smarthome.web.user.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 用户管理动作基类。
 * @author kouy
 * @since 1.0 2012-7-20
 * @see BaseAction
 */
@SuppressWarnings("serial")
public abstract class BaseUserAction<VO> extends BaseAction<VO> {

	private String currUserId;
	
	private boolean promptFlag;
	
	private ISysParamService sysParamService;

	public String getCurrUserId() {
		return currUserId;
	}
	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}	
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	
	
	/**
	 * 设置登录帐号初始密码。
	 */
	protected void setLoginInitPass() throws Exception {
		setRequestAttribute(Constants.KEY_LOGIN_INIT_PASS, getLoginInitPass());
	}
	
	
	//获取登录帐号初始密码
	private String getLoginInitPass() throws Exception {
		return getSysParamService().getParamValue(
				Constants.PARAM_CODE_LOGIN_INIT_PASS);
	}
}
