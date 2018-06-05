package com.biencloud.smarthome.web.login.service;

import java.util.List;

import com.biencloud.smarthome.web.login.vo.LoginVO;
/**
 * 
 * 类名称：ILoginService 
 * 类描述： 登录业务逻辑处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-2 下午4:21:26
 */
public interface ILoginService {
//	/**
//	 * 
//	 * 方法的描述: 登录
//	 * @author: kouy  
//	 * @version: 0.1
//	 * @date: 2012-5-2 下午4:21:49
//	 * @param user 登录对象
//	 * @return
//	 * @throws Exception
//	 */
//    public LoginUserVO login(LoginUserVO user)throws Exception;
	/**
	 * 
	 * 方法的描述: 获得验证码
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-3 下午6:53:23
	 * @return
	 */
	public String getRandomCode();
	
	/**
	 * 用户登录，如果登录成功则返回登录帐号信息且结果标志为成功，<br/>
	 * 否则结果标志为失败原因。
	 * @author kouy
	 * @since 1.0 2012-5-24 
	 * @param loginVO 登录值对象
	 * @throws RuntimeException 当方法执行发生异常时
	 * @return
	 */
	public LoginVO login(LoginVO loginVO);
	
	/**
	 * 通过登录名获取登录信息，如果找不到，则返回Null。
	 * @author kouy
	 * @since 1.0 2012-5-15 
	 * @param loginName 登录名
	 * @throws RuntimeException 当方法执行发生异常时
	 * @return
	 */
	public LoginVO getLoginByLoginName(String loginName);

	/**
	 * 重置用户密码。
	 * @author kouy
	 * @since 1.0 2012-5-15
	 * @param loginName 登录名
	 * @throws RuntimeException 当方法执行发生异常时
	 */
	public void resetPassword(String loginName);
	
	/**
	 * 修改用户密码。
	 * @author kouy
	 * @since 1.0 2012-6-19
	 * @param loginName 登录名
	 * @param password 密码
	 * @throws RuntimeException 当方法执行发生异常时
	 */
	public void updatePassword(String loginName, String password);
	
	/**
	 * 更新登录账号状态。
	 * @author kouy
	 * @since 1.0 2012-5-15
	 * @param loginId 登录编号
	 * @param status 登录账号状态
	 * @throws RuntimeException 当方法执行发生异常时
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
	 * 返回是否需要校验验证码。
	 * @return
	 */
	public boolean isValidCodeOpenFlag();
	
	/**
	 * 校验用户输入的验证码是否正确。
	 * @param userValidCode 用户输入的验证码
	 * @param sysValidCode 系统保存的验证码
	 * @return
	 */
	public boolean checkValidationCode(String userValidCode, String sysValidCode);
	
	/**
	 * 方法的描述: 获取首页上的tab
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-30 上午11:35:22
	 * @param userType 用户类型
	 * @param roleCode 角色代码
	 * @param position 位置，指tab上部和下部
	 * @return List<String>
	 */
	public List<String> getTabs(String userType,String roleCode,String position);
	
	/**
	 * 验证用户能否访问系统，并返回相应的标志。
	 * @param loginName 登录名
	 * @param ip IP地址
	 * @return
	 */
	public String validatePassing(String loginName, String ip);
}
