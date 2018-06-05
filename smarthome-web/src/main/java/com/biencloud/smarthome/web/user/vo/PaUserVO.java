package com.biencloud.smarthome.web.user.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;

import java.util.Date;


/**
 * 物业管理用户值对象。
 * @author kouy
 * @since 1.0 2012-5-14
 */
public class PaUserVO extends BaseVO {
	
	private static final long serialVersionUID = -8860730727405923613L;
	
	private String userId;//用户信息ID
	private Date birthDate;//出生日期
	private Date createdTime;//创建时间
	private String createdUser;//创建用户
	private String degree;//学历
	private String department;//所在部门
	private String districtId;//小区ID
	private String email;//邮箱
	private String gender;//性别
	private String idCard;//身份证
	private String intro;//简介
	private String major;//专业
	private String addr;//住址
	private String mobilePhone;//移动电话
	private String officePhone;//办公电话
	private String photoPath;//照片链接地址
	private String post;//职位
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号
	private String userName;//姓名
	private String roleCode;//角色代码

	private String districtName;//小区名称
	
	private String roleName;//角色名称
	
	private LoginVO login;//登录信息
	
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getUserName() {
		return this.userName;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public LoginVO getLogin() {
		return login;
	}

	public void setLogin(LoginVO login) {
		this.login = login;
	}
}