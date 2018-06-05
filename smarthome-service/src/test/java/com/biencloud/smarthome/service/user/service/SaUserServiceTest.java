package com.biencloud.smarthome.service.user.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.user.model.SaUser;

/**
 * 系统管理用户领域服务测试类。
 * @author kouy
 * @since 1.0 2012-5-12
 */
public class SaUserServiceTest extends BaseTest {

	@Autowired
	private ISaUserService saUserService;
	
	@Test
	public void queryForPaging(){
		SaUser user = new SaUser();
		Login login = new Login();
		user.setRoleCode(RoleCode.SUPER_ADMIN.getValue());
		login.setLoginName("test");
		user.setLogin(login);
		
		Paging<SaUser> paging = saUserService.querySaUsersForPaging(
				user, 1, 10);
		logger.info("---------------------------返回系统管理用户列表分页信息：{}", paging);
		
		user.setDepartment("运营");
		user.setUserName("张");
		user.setIdCard("35");
		paging = saUserService.querySaUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回系统管理用户列表分页信息：{}", paging);
		
		user.setRoleCode(RoleCode.SYS_ADMIN.getValue());
		user.setDepartment(null);
		user.setUserName(null);
		user.setIdCard("35");
		paging = saUserService.querySaUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回系统管理用户列表分页信息：{}", paging);
	}
	
	@Test
	public void getDetail(){
		SaUser saUser = saUserService.get("1");
		
		logger.info("返回系统管理用户详细信息：{}", saUser);
	}
	
	@Test	
	public void add(){
		SaUser user = new SaUser();
		user.setUserName("李四");
		user.setDepartment("运营二部");
		user.setGender("0");
		user.setIdCard("350421197412054512");
		user.setRoleCode(RoleCode.SYS_ADMIN.getValue());
		
		Login login = new Login();
		login.setLoginName("test1");
		login.setLoginAlias("李四");
		login.setStatus("0");
		
		user.setLogin(login);
		saUserService.save(user);
		logger.info("---------------------------新增系统管理用户信息：{}", user);
	}
	
	@Test
	public void update(){
		SaUser user = saUserService.get("1");
		if(user != null){
			user.setUpdatedUser("test");
			user.setUpdatedTime(new Date());
			user.setAddr("xxxx");
			user.setBirthDate(new Date());
			user.setMobilePhone("13922849195");
			Login login = user.getLogin();
			login.setLoginAlias("李四1");
			saUserService.update(user);
			logger.info("---------------------------更新系统管理用户信息：{}", user);
		}		
	}
	
	@Test
	public void existIdCard(){
		String userId = "1";
		String idCard = "350421197412054512";
		boolean isExist = saUserService.existIdCard(userId, idCard);
		logger.info("---------------------------编号为{}的用户身份证{}是否存在：{}", new Object[]{userId, idCard, isExist});
	}
}
