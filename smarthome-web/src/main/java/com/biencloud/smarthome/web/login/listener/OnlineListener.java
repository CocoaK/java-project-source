package com.biencloud.smarthome.web.login.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.login.service.ILoginService;
import com.biencloud.smarthome.web.login.vo.LoginVO;

/**
 * 退出或会话失效监听器。
 * @author kouy
 * @since 1.0 2012-6-12
 * @see HttpSessionListener
 */
public class OnlineListener implements HttpSessionListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//Don't need to implement
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		LoginVO login = (LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
		if(login != null){
			logger.info("********************会话过期的用户登录信息:{}", login);
			try {
				getLoginService(session.getServletContext()).updateOnlineFlag(
						login.getLoginName(), Constants.LOGIN_OFFLINE, login.getIp());
			} catch (Exception e) {
				logger.error("********************更新用户{}的下线标志发生异常:{}", new Object[]{login, e});
			}
		}
	}

	
	//获取登录服务
	private ILoginService getLoginService(ServletContext sc){
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		return ctx.getBean("loginService", ILoginService.class);
	}
}
