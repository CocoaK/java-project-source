package com.biencloud.smarthome.service.login.model;

import java.util.Date;

import javax.persistence.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.user.enums.UserType;

/**
 * 登录实体对象。
 * @author kouy
 * @since 1.0 2012-5-11
 */
@SuppressWarnings("unused")
@Entity
@Table(name="t_login")
public class Login extends BaseEntity {
	
	private static final long serialVersionUID = 7170624682993989937L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="login_id",updatable=false)
	private String loginId;//登录信息ID

	@Column(name="created_time",updatable=false)
	private Date createdTime;//创建时间

	@Column(name="created_user",updatable=false)
	private String createdUser;//创建用户的登录帐号

	@Column(name="last_login_time",updatable=false)
	private Date lastLoginTime;//最近登录时间

	@Column(name="login_alias")
	private String loginAlias;//登录帐号别名

	@Column(name="login_name",updatable=false)
	private String loginName;//登录帐号

	@Column(name="password",updatable=false)
	private String password;//登录密码

	private String status;//状态，"0"：启用、"1"：禁用、"2"：锁定

	@Column(name="user_id",updatable=false)
	private String userId;//登录用户ID
	
	@Column(name="user_type",updatable=false)
	private String userType;//登录用户类型，参见 UserType
	
	@Column(name="online_flag",updatable=false)
	private String onlineFlag;//在线标志
	
	private String ip;//IP
	
	@Transient
	private String userName;//登录用户姓名
	@Transient
	private String roleCode;//登录用户所属角色代码
	
	@Transient
	private String districtId;//小区ID
	@Transient
	private String districtName;//小区名称
	@Transient
	private String propertyCompanyName;//小区物业公司
	@Transient
	private String result;//登录结果，参见 LoginResult


	public Login(){
	}
	
	public Login(String userId, String userType){
		this.userId = userId;
		this.userType = userType;
	}
	
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder(2069375785, 925522185)
				.append(this.userId)
				.append(this.userType).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Login)) {
			return false;
		}
		Login login = (Login) object;
		return new EqualsBuilder()
				.append(this.userId, login.userId)
				.append(this.userType, login.userType)
				.isEquals();
	}	
}