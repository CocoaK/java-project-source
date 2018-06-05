package com.biencloud.smarthome.service.user.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 物业管理用户领域服务测试类。
 * @author kouy
 * @since 1.0 2012-5-14
 */
public class PaUserServiceTest extends BaseTest {

	@Autowired
	private IPaUserService paUserService;
	
	@Test
	public void queryForPaging(){
		PaUser user = new PaUser();
		user.setRoleCode(RoleCode.SUPER_ADMIN.getValue());
		Login login = new Login();
		login.setUserType(UserType.SA.getValue());
		login.setLoginName("test");
		user.setLogin(login);
		
		Paging<PaUser> paging = paUserService.queryPaUsersForPaging(
				user, 1, 10);
		logger.info("---------------------------返回物业管理用户列表分页信息：{}", paging);
		
		user.setRoleCode(RoleCode.SYS_ADMIN.getValue());
		paging = paUserService.queryPaUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回物业管理用户列表分页信息：{}", paging);
		
		user.setRoleCode(RoleCode.PROP_ADMIN.getValue());
		login.setUserType(UserType.PA.getValue());
		paging = paUserService.queryPaUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回物业管理用户列表分页信息：{}", paging);
		
		user.setUserName("王");
		user.setIdCard("35");
		paging = paUserService.queryPaUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回物业管理用户列表分页信息：{}", paging);
	}
	
	@Test
	public void getDetail(){
		PaUser saUser = paUserService.get("1");
		
		logger.info("返回物业管理用户详细信息：{}", saUser);
	}
	
	@Test	
	public void add(){
		PaUser user = new PaUser();
		user.setDistrictId("1");
		user.setUserName("王五");
		user.setDepartment("维修部");
		user.setGender("0");
		user.setIdCard("350421197412054512");
		user.setCreatedUser("test");
		user.setRoleCode(RoleCode.PROP_ADMIN.getValue());
		
		Login login = new Login();
		login.setLoginName("test2");
		login.setLoginAlias("王五");
		login.setStatus("0");
		
		user.setLogin(login);
		paUserService.save(user);
		logger.info("---------------------------新增物业管理用户信息：{}", user);
	}
	
	@Test
	public void update(){
		PaUser user = paUserService.get("1");
		if(user != null){
			user.setUpdatedUser("test");
			user.setUpdatedTime(new Date());
			user.setBirthDate(new Date());
			user.setMobilePhone("13922849195");
			Login login = user.getLogin();
			login.setLoginAlias("王五1");
			paUserService.update(user);
			logger.info("---------------------------更新物业管理用户信息：{}", user);
		}		
	}
	
	@Test
	public void existIdCard(){
		String userId = null;
		String idCard = "355010196412032610";
		boolean isExist = paUserService.existIdCard(userId, idCard);
		logger.info("---------------------------编号为{}的用户身份证{}是否存在：{}", new Object[]{userId, idCard, isExist});
	}
}
