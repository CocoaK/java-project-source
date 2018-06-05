package com.biencloud.smarthome.service.user.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.login.model.Login;

import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 业主用户实体对象。
 * @author kouy
 * @since 1.0 2012-5-18
 * @see BaseEntity
 */
@Entity
@Table(name="t_owner_user")
public class OwnerUser extends BaseEntity {
	
	private static final long serialVersionUID = -5576909931940849990L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private String userId;//用户信息ID

	private String addr;//住址

    @Temporal( TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;//出生日期

	@Column(name="created_time",updatable=false)
	private Date createdTime;//创建日期

	@Column(name="created_user",updatable=false)
	private String createdUser;//创建用户的登录帐号

	private String email;//邮箱

	private String gender;//性别

	@Column(name="home_phone")
	private String homePhone;//住宅电话

	@Column(name="house_id")
	private String houseId;//房号ID

	@Column(name="id_card")
	private String idCard;//身份证

	@Column(name="mobile_phone")
	private String mobilePhone;//移动电话

	@Column(name="photo_path")
	private String photoPath;//照片链接地址

	@Column(name="updated_time",insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user",insertable=false)
	private String updatedUser;//修改用户的登录帐号

	@Column(name="user_name")
	private String userName;//姓名

	@Column(name="work_unit")
	private String workUnit;//工作单位
  
	@Column(name="role_code",updatable=false)
	private String roleCode;//角色代码
	
	@Column(name="district_id",updatable=false)
	private String districtId;//小区ID
	
	@Transient
	private String areaName;//所在区域名称
	@Transient
	private String building;//所在楼宇名称
	@Transient
	private String unitName;//所在单元名称
	@Transient
	private String houseName;//所在房号名称
	
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

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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

	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
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

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(591054435, 271358549)				
				.append(this.userId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OwnerUser)) {
			return false;
		}
		OwnerUser ou = (OwnerUser) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.userId, ou.userId).isEquals();
	}
}