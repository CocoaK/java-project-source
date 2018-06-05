package com.biencloud.smarthome.service.user.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.login.model.Login;

import java.util.Date;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;


/**
 * 物业管理用户实体对象。
 * @author kouy
 * @since 1.0 2012-5-11
 */
@Entity
@Table(name="t_pa_user")
public class PaUser extends BaseEntity {
	
	private static final long serialVersionUID = -8564705641335214699L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private String userId;//用户信息ID

    @Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;//出生日期

	@Column(name="created_time",updatable=false)
	private Date createdTime;//创建日期

	@Column(name="created_user",updatable=false)
	private String createdUser;//创建用户的登录帐号

	private String degree;//学历

	private String department;//所在部门

	@Column(name="district_id")
	private String districtId;//小区ID

	private String email;//邮箱

	private String gender;//性别

	@Column(name="id_card")
	private String idCard;//身份证

	private String intro;//简介

	private String major;//专业
	
	private String addr;//住址

	@Column(name="mobile_phone")
	private String mobilePhone;//移动电话

	@Column(name="office_phone")
	private String officePhone;//办公电话

	@Column(name="photo_path")
	private String photoPath;//照片链接地址

	private String post;//职位

	@Column(name="updated_time",insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user",insertable=false)
	private String updatedUser;//修改用户的登录帐号

	@Column(name="user_name")
	private String userName;//姓名
	
	@Column(name="role_code")
	private String roleCode;//角色代码

	@Transient
	private String districtName;//小区名称
	
	@Transient
	private String roleName;//角色名称
	@Transient
	private Login login;//登录信息
	
	
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

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1446258649, -816722469)
				.append(this.userId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PaUser)) {
			return false;
		}
		PaUser pi = (PaUser) object;
		return new EqualsBuilder()
				.append(this.userId, pi.userId)
				.isEquals();
	}
}