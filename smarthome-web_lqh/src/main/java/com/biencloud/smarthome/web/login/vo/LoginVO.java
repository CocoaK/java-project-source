package com.biencloud.smarthome.web.login.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.common.util.Constants;

/**
 * 登录值对象。
 * @author kouy
 * @since 1.0 2012-5-14
 */
@SuppressWarnings("unused")
public class LoginVO extends BaseVO {
	
	private static final long serialVersionUID = 6046463451118757598L;
	
	private String loginId;//登录信息ID
	private Date createdTime;//创建时间
	private String createdUser;//创建用户的登录帐号
	private Date lastLoginTime;//最近登录时间
	private String loginAlias;//登录帐号别名
	private String loginName;//登录帐号
	private String password;//登录密码
	private String status;//状态，"0"：启用、"1"：禁用、"2"：锁定
	private String userId;//登录用户ID
	private String userType;//登录用户类型，参见 Constants.LOGIN_USER_TYPE_OWNER、Constants.LOGIN_USER_TYPE_PAUSER、Constants.LOGIN_USER_TYPE_SYSTEM
	private String onlineFlag;//在线标志
	private String ip;//IP

	private String userName;//登录用户姓名
	private String roleCode;//登录用户所属角色代码
	private String districtId;//小区ID
	private String districtName;//小区名称
	private String propertyCompanyName;//小区物业名称
	
	//登录验证结果，00：登录成功、01：帐号不存在、02：密码错误、03：帐号已禁用、04：帐号已锁定、05：帐号已删除、06：帐号已启用
	private String result;
	
	private String validCode;//验证码
	
	
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginAlias() {
		return this.loginAlias;
	}

	public void setLoginAlias(String loginAlias) {
		this.loginAlias = loginAlias;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}

	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}
}