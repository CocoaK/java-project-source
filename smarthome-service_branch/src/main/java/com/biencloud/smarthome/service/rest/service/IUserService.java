package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.User;

public interface IUserService extends IBaseResService<User>{
	
	List<User> queryList(User user);
	
	ResultEntity<User> save(User user);
	
	/**
	 * 校验密码
	 * @param username
	 * @param password
	 * @return
	 */
	ResultEntity<User> verify(String username,String password);
	
	/**
	 * 修改密码
	 * @param username
	 * @param password
	 * @param newPasswd
	 * @return
	 */
	ResultEntity<User> updatePwd(String username, String password,String newPasswd);

	/**
	 * 重置密码
	 * @param username
	 * @param sid
	 * @param timeFlag
	 * @return
	 */
	ResultEntity<String> resetPwd(String username, String sid,Long timeFlag);

	/**
	 * 进入重置密码
	 * @param username
	 * @return
	 */
	ResultEntity<String> toResetPwd(String username);

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	ResultEntity<User> queryByUsername(String username);

	/**
	 * 注册普通用户和SIP用户
	 * @param user
	 * @return
	 */
	ResultEntity<User> saveAndRegSip(User user);

	/**
	 * 带返回sip账号的校验密码
	 * @param username
	 * @param password
	 * @return
	 */
	ResultEntity<User> sipVerify(String username, String password);
}
