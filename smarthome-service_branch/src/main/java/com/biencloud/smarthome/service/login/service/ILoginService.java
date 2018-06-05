package com.biencloud.smarthome.service.login.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 登录模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-14
 */
public interface ILoginService extends IService<Login, String> {

	/**
	 * 用户登录，如果登录成功则返回登录帐号信息且结果标志为成功，<br/>
	 * 否则结果标志为失败原因。
	 * @since 1.0 2012-5-24 
	 * @param login 登录对象
	 * @return
	 */
	public Login login(Login login);
	
	/**
	 * 通过登录名获取登录信息，如果找不到，则返回Null。
	 * @param loginName 登录名
	 * @return
	 */
	public Login getLoginByLoginName(String loginName);
	
	/**
	 * 重置密码。
	 * @param loginName 登录名
	 */
	public void resetPassword(String loginName);
	
	/**
	 * 修改密码。
	 * @param loginName 登录名
	 * @param password 新密码
	 */
	public void updatePassword(String loginName, String password);
	
	/**
	 * 更新登录账号状态。
	 * @param loginId 登录编号
	 * @param status 登录账号状态
	 * @param updatedUser 操作人员登录名
	 */
	public void updateStatus(String loginId, String status);
	
	/**
	 * 更新用户在线标志。
	 * @param loginName 登录名
	 * @param onlineFlag 在线标志
	 * @param ip IP地址
	 */
	public void updateOnlineFlag(String loginName, String onlineFlag, String ip);
	
	/**
	 * 根据在线标志统计用户数。
	 * @param onlineFlag 在线标志，不为空作为查询条件
	 * @return
	 */
	public long countUsersByOnlineFlag(String onlineFlag);
	
	/**
	 * 通过登录名获取真实姓名，获取不到返回Null。
	 * @param loginName 登录名
	 */
	public String getUserNameByLoginName(String loginName);
}
