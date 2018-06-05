package com.biencloud.smarthome.web.login.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.login.service.ILoginService;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 
 * 类名称：SessionInterceptor 
 * 类描述： session拦截器  
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-3 下午5:02:45
 */
@SuppressWarnings("serial")
public class SessionInterceptor extends MethodFilterInterceptor {
	
	private static final String RELOGIN = "reLogin";
	private static final String ERROR_CODE = "errorCode";
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		try {
			ActionContext ac = actionInvocation.getInvocationContext();
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			Map<String, Object> session = ac.getSession();
			if (session == null || !session.containsKey(Constants.KEY_LOGIN_SESSION)) {			
				request.setAttribute(ERROR_CODE, Constants.SESSION_TIMEOUT);
				return RELOGIN;
			}
			
			LoginVO login = (LoginVO)session.get(Constants.KEY_LOGIN_SESSION);
			//判断同一账号是否允许在不同IP同时登录
			String retCode = getLoginService(actionInvocation).validatePassing(
					login.getLoginName(), login.getIp());
			
			if(!Constants.LOGIN_SUCCESS.equals(retCode)){
				session.clear();
				if(Constants.LOGIN_SYNC.equals(retCode))
					request.setAttribute(ERROR_CODE, retCode);
				return RELOGIN;
			}
		} catch (Exception e) {
			LOGGER.error("**********Session拦截器处理发生异常：{}", e);
			return Constants.GLOBAL_EXCEPTION_RESULT;
		}
		
		return actionInvocation.invoke();
	} 
	
	
	private ILoginService getLoginService(ActionInvocation actionInvocation){
		ILoginService loginService = null;
		try {
			ServletContext sc = (ServletContext) actionInvocation.getInvocationContext().get(
					ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			loginService = ctx.getBean(Constants.BEAN_NAME_LOGIN_SERVICE, ILoginService.class);
		} catch (Exception e) {
			LOGGER.error("********************获取{}发生异常:{}", new Object[]{ILoginService.class, e});
		}
		return loginService;
	}
}
