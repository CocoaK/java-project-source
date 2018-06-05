package com.biencloud.smarthome.web.base.interceptor;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.log.service.IOperationLogService;
import com.biencloud.smarthome.web.log.vo.OperationLogVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 日志记录拦截器。
 * @author kouy
 * @since 1.0 2012-6-7
 */
@SuppressWarnings({"serial", "rawtypes"})
public class LogInterceptor extends MethodFilterInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Set<String> errorResults;
	
	public Set<String> getErrorResults() {
		return errorResults;
	}

	public void setErrorResults(String errorResults) {
		this.errorResults = new HashSet<String>();
		if(StringUtils.isNotBlank(errorResults)){
			String[] ers = StringUtils.split(errorResults, ',');
			for (String er : ers) {
				this.errorResults.add(er);
			}
		}
	}
		
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		String loginName = getLoginName(actionInvocation);
		Exception ex = null;
		int operationResult = Constants.LOG_SUCCESS;
		String result = null;
		try {
			result = actionInvocation.invoke();
		} catch (Exception e) {
			ex = e;
			operationResult = Constants.LOG_EXCEPTION;
			//记录系统异常日志
			logger.error("********************调用{}发生异常:{}", new Object[]{action, e});			
		}
						
		if(!(action instanceof BaseAction)){//忽略记录操作日志
			if(ex != null)
				throw ex;
			return result;
		}
		
		BaseAction ba = (BaseAction)action;
		if(!ba.isIgnoreLog()){
			recordOperationLog(actionInvocation, ba, 
					operationResult, result, loginName, ex);
		}
			
		if(ex != null)
			throw ex;
		
		return result;
	}

	//记录操作日志
	private void recordOperationLog(ActionInvocation actionInvocation,
			BaseAction ba, int operationResult, String result,
			String loginName, Exception ex) {
		if(ba.getErrorResults() != null 
				&& !ba.getErrorResults().isEmpty()){
			//Action定义错误结果，则覆盖拦截器默认的错误结果
			if(ba.getErrorResults().contains(result))
				operationResult = Constants.LOG_FAILURE_RESULT;
		}else{//Action未定义错误结果，则使用拦截器默认的错误结果
			if(errorResults.contains(result))
				operationResult = Constants.LOG_FAILURE_RESULT;
		}
					
		OperationLogVO ol = createOperationLog(ba, 
				operationResult, getMsg(operationResult, ex));
		//预拦截登录时没有登录信息，需在后拦截重新获取
		if(loginName == null)
			loginName = getLoginName(actionInvocation);
		if(loginName != null){
			ol.setOperateUser(loginName);
			saveOperationLog(actionInvocation, ol);
		}
	}

	//获取登录名
	private String getLoginName(ActionInvocation actionInvocation) {
		ActionContext ac = actionInvocation.getInvocationContext();
		Map<String, Object> session = ac.getSession();
		if(session != null && !session.isEmpty()){
			LoginVO login = (LoginVO)session.get(Constants.KEY_LOGIN_SESSION);
			if(login != null)
				return login.getLoginName();
		}
		return null;
	}

	//保存操作日志，失败不影响当前操作
	private void saveOperationLog(ActionInvocation actionInvocation,
			OperationLogVO ol) {
		IOperationLogService operationLogService = getOperationLogService(
				actionInvocation.getInvocationContext());
		
		if(operationLogService != null){
			try {
				operationLogService.saveOrUpdateOperationLog(ol);
			} catch (Exception e) {
				logger.error("********************记录操作日志{}发生异常:{}", new Object[]{ol, e});
			}
		}
	}

	//获取操作日志服务
	private IOperationLogService getOperationLogService(ActionContext ac){
		IOperationLogService operationLogService = null;
		try {
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			operationLogService = ctx.getBean("operationLogService", IOperationLogService.class);
		} catch (Exception e) {
			logger.error("********************获取{}发生异常:{}", new Object[]{IOperationLogService.class, e});
		}
		return operationLogService;
	}

	//创建操作日志
	private OperationLogVO createOperationLog(BaseAction ba, int operationResult,
			String msg) {
		OperationLogVO ol = new OperationLogVO();
		ol.setIp(ba.getIp());
		ol.setMenuCode(ba.getMenuCode());
		ol.setOperationCode(ba.getOperationCode());
		ol.setOperateResult(operationResult);
		ol.setOperateTime(new Date());
		ol.setRemark(msg);		
		return ol;
	}
	
	//获取日志消息
	private String getMsg(int operationResult, Exception e){
		String msg = null;
//		String msg = "操作结果->成功";
//		if(operationResult == Constants.LOG_FAILURE_RESULT){
//			msg = "操作结果->返回错误结果";
//		}else 
		//只在发生异常时获得异常日志消息
		if(operationResult == Constants.LOG_EXCEPTION){
			msg = "操作结果->发生异常";
			if(e != null)
				msg = e.getMessage();
		}
		return msg;
	}
}
