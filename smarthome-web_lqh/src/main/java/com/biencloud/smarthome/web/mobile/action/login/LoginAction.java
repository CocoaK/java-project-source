package com.biencloud.smarthome.web.mobile.action.login;

import java.util.HashMap;
import java.util.Map;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.login.service.ILoginService;
import com.biencloud.smarthome.web.login.vo.LoginVO;

/**
 * Web移动版登录相关动作类。
 * @author kouy
 * @since 2.0 2012-11-6
 * @see BaseAction
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction<LoginVO> {

	//业主登录成功显示的主页
	private static final String VIEW_OWNER_MAIN = "owner";
	
	private static final Map<String, String> MSG_MAPPINGS;
	static{
		MSG_MAPPINGS = new HashMap<String, String>();
		MSG_MAPPINGS.put(Constants.ACC_ERROR, "login.action.mobile.acc.notexist");
		MSG_MAPPINGS.put(Constants.PASS_ERROR, "login.action.mobile.pwd.error");
		MSG_MAPPINGS.put(Constants.ACC_DISABLED, "login.action.mobile.acc.forbid");
		MSG_MAPPINGS.put(Constants.ACC_LOCKED, "login.action.mobile.acc.locked");
		MSG_MAPPINGS.put(Constants.ACC_REMOVED, "login.action.mobile.acc.removed");
		MSG_MAPPINGS.put(Constants.SESSION_TIMEOUT, "login.action.mobile.session.timeout");
		MSG_MAPPINGS.put(Constants.LOGIN_SYNC, "login.action.mobile.sync.login");
		MSG_MAPPINGS.put(Constants.ONLY_OWNER, "login.action.mobile.only.owner");
	}
	
	
	private LoginVO login;
	private String errorCode;
	private boolean promptFlag;
	
	private ILoginService loginService;
	
	
	public LoginVO getLogin() {
		return login;
	}
	public void setLogin(LoginVO login) {
		this.login = login;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}

	public ILoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	
	/**
	 * 登录系统。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String login() throws Exception {
		LoginVO retLogin = getLoginService().login(login);	
		String result = retLogin.getResult();
		if(!"00".equals(result)){
			addActionError(getText(MSG_MAPPINGS.get(result)));
			setPromptFlag(true);
			return ERROR;
		}
		
		//判断是否业主登录 
		if(!Constants.LOGIN_USER_TYPE_OWNER.equals(retLogin.getUserType())){
			addActionError(getText(MSG_MAPPINGS.get(Constants.ONLY_OWNER)));
			setPromptFlag(true);
			return ERROR;
		}
		
		retLogin.setIp(getIp());
		updateOnlineFlag(retLogin.getLoginName(), Constants.LOGIN_ONLINE, retLogin.getIp());
		setSessionAttribute(Constants.KEY_LOGIN_SESSION, retLogin);
		return SUCCESS;
	}
	
	/**
	 * 显示系统主页。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String index() throws Exception {
		return VIEW_OWNER_MAIN;
	}
	
	
	//更新用户的上线标志，失败不影响登录
	private void updateOnlineFlag(String loginName, String onlineFlag, String ip) {
		try {
			getLoginService().updateOnlineFlag(loginName, onlineFlag, ip);
		} catch (Exception e) {
			logger.error("********************更新登录名为{}的上线标志发生异常:{}",
					new Object[] { loginName, e });
		}
	}
}
