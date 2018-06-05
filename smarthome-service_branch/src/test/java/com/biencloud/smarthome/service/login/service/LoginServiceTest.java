package com.biencloud.smarthome.service.login.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 登录模块领域服务测试类。
 * @author Matt Weng
 * @since 1.0 2012-5-14
 */
public class LoginServiceTest extends BaseTest {

	@Autowired
	private ILoginService loginService;
	
	@Test
	public void login(){
		Login login = new Login();
		login.setUserName("test0");
		login.setPassword(Constants.VALUE_LOGIN_INIT_PASS);
		Login retLogin = loginService.login(login);
		logger.info("---------------------------返回登录信息：{}", retLogin);
		
		login.setPassword("sshyuyyww");
		retLogin = loginService.login(login);
		logger.info("---------------------------返回登录信息：{}", retLogin);
		
		login.setUserName("test1");
		retLogin = loginService.login(login);
		logger.info("---------------------------返回登录信息：{}", retLogin);
	}
	
	@Test
	public void getLoginByLoginName(){
		Login login = loginService.getLoginByLoginName("test0");
		logger.info("---------------------------返回登录信息：{}", login);
	}
	
	@Test
	public void resetPassword(){
		String loginName = "test0";
		if(loginService.getLoginByLoginName(loginName) != null){
			loginService.resetPassword(loginName);
			logger.info("---------------------------重置用户名为{}的密码", loginName);
		}
	}
	
	@Test
	public void updateStatus(){
		String loginId = "1";
		if(loginService.get(loginId) != null){
			loginService.updateStatus(loginId, "1");
			logger.info("---------------------------禁用用户编号为{}的用户", loginId);
		
			loginService.updateStatus(loginId, "0");
			logger.info("---------------------------启用用户编号为{}的用户", loginId);
		}
	}
	
	@Test
	public void updateOnlineFlag(){
		String loginName = "test0";
		if(loginService.getLoginByLoginName(loginName) != null){
//			loginService.updateOnlineFlag(loginName, Constants.LOGIN_OFFLINE);
			logger.info("---------------------------更新用户名为{}的在线标志", loginName);
		}
	}
	
	@Test
	public void countUsersByOnlineFlag(){
		logger.info("---------------------------所有的用户数为{}", loginService.countUsersByOnlineFlag(null));
		logger.info("---------------------------所有的在线用户数为{}", loginService.countUsersByOnlineFlag(Constants.LOGIN_ONLINE));
		logger.info("---------------------------所有的下线用户数为{}", loginService.countUsersByOnlineFlag(Constants.LOGIN_OFFLINE));
	}
}
