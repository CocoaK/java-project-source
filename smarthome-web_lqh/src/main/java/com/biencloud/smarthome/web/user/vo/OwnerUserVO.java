package com.biencloud.smarthome.web.user.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;

/**
 * 住户用户值对象。
 * @author kouy
 * @since 1.0 2012-5-18
 */
public class OwnerUserVO extends BaseVO {

	private static final long serialVersionUID = 4704219349760144186L;
	
	private String userId;//用户信息ID
	private String addr;//住址
	private Date birthDate;//出生日期
	private Date createdTime;//创建日期
	private String createdUser;//创建用户的登录帐号
	private String email;//邮箱
	private String gender;//性别
	private String homePhone;//住宅电话
	private String houseId;//房号ID
	private String idCard;//身份证
	private String mobilePhone;//移动电话
	private String photoPath;//照片链接地址
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号
	private String userName;//姓名
	private String workUnit;//工作单位
	private String districtId;//小区ID
	private String roleCode;//角色代码
	
	private String areaName;//所在区域名称
	private String building;//所在楼宇名称
	private String unitName;//所在单元名称
	private String houseName;//所在房号名称
	
	private String roleName;//角色名称
	
	private LoginVO login;//登录信息

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
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
