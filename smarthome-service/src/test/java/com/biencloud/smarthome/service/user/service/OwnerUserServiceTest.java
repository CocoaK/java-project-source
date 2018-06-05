package com.biencloud.smarthome.service.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.permissions.enums.RoleCode;
import com.biencloud.smarthome.service.user.model.OwnerUser;

/**
 * 住户用户管理领域服务测试类。
 * @author kouy
 * @since 1.0 2012-5-18
 */
public class OwnerUserServiceTest extends BaseTest {

	@Autowired
	private IOwnerUserService ownerUserService;
	
	
	@Test
	public void queryForPaging(){
		OwnerUser user = new OwnerUser();
		user.setDistrictId("1");
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 4, 22);
		user.setCreatedTime(cal.getTime());
		Paging<OwnerUser> paging = ownerUserService.queryOwnerUsersForPaging(
				user, 1, 10);
		logger.info("---------------------------返回住户用户列表分页信息：{}", paging);
		
		
		user.setUserName("李");
		user.setIdCard("35");
		paging = ownerUserService.queryOwnerUsersForPaging(user, 1, 10);
		logger.info("---------------------------返回住户用户列表分页信息：{}", paging);
	}
	
	@Test
	public void getDetail(){
		OwnerUser user = ownerUserService.get("1");
		
		logger.info("返回住户用户详细信息：{}", user);
	}
	
	@Test	
	public void add(){
		OwnerUser user = createUser("test1001");
		ownerUserService.save(user);
		logger.info("---------------------------新增住户用户信息：{}", user);
	}
	
	@Test	
	public void addUsers(){		
		List<OwnerUser> users = new ArrayList<OwnerUser>();
		users.add(createUser("test2001"));
		users.add(createUser("test2002"));
		
		ownerUserService.addUsers(users);
		logger.info("---------------------------新增住户用户信息列表：{}", users);
	}
	
	@Test
	public void update(){
		OwnerUser user = ownerUserService.get("1");
		if(user != null){
			user.setUpdatedUser("test");
			user.setUpdatedTime(new Date());
			user.setAddr("xxxx");
			user.setBirthDate(new Date());
			user.setMobilePhone("13922849195");
			Login login = user.getLogin();
			login.setLoginAlias("李四1");
			ownerUserService.update(user);
			logger.info("---------------------------更新住户用户信息：{}", user);
		}		
	}
	
	@Test
	public void countUsers(){
		OwnerUser user = new OwnerUser();
		long countUsers = ownerUserService.countUsers(user);
		logger.info("---------------------------总住户用户数：{}", countUsers);
		
		user.setDistrictId("1");
		countUsers = ownerUserService.countUsers(user);
		logger.info("---------------------------小区住户用户数：{}", countUsers);
	}
	
	@Test
	public void existIdCard(){
		String userId = null;
		String idCard = "350421197412054512";
		boolean isExist = ownerUserService.existIdCard(userId, idCard);
		logger.info("---------------------------编号为{}的用户身份证{}是否存在：{}", new Object[]{userId, idCard, isExist});
	}
	
	
	private OwnerUser createUser(String loginName){
		OwnerUser user = new OwnerUser();
		user.setUserName("张五");
		user.setGender("0");
		user.setIdCard("350421197412054512");
		user.setCreatedUser("test");
		user.setBirthDate(new Date());
		user.setMobilePhone("13922229999");
		user.setHomePhone("56784512");
		user.setAddr("不知道");
		user.setWorkUnit("不知道");
		user.setDistrictId("1");
		user.setHouseId("1");
		user.setRoleCode(RoleCode.OWNER.getValue());
		
		Login login = new Login();
		login.setLoginName(loginName);
		login.setLoginAlias("李六");
		login.setStatus("0");
		login.setIp("192.168.1.123");
		login.setCreatedUser(user.getCreatedUser());
		
		user.setLogin(login);
		
		return user;
	}
}
