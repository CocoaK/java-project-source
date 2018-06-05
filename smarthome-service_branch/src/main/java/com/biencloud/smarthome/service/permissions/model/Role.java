package com.biencloud.smarthome.service.permissions.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.menu.model.Menu;


/**
 * 角色实体对象。
 * @author kouy
 * @since 1.0 2012-4-16
 */
@SuppressWarnings("unused")
@Entity
@Table(name="t_role")
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 6108073372169776623L;

	@Id
	@Column(name="role_code")
	private String roleCode;//角色代码

	@Column(name="parent_code" ,updatable=false)
	private String parentCode;//上级角色代码
	
	@Column(name="role_name")
	private String roleName;//角色名称
	
	@Column(name="created_time" ,updatable=false)
	private Date createdTime;//创建时间

	@Column(name="created_user" ,updatable=false)
	private String createdUser;//创建用户的登录帐号

	@Column(name="is_default" ,updatable=false)
	private String isDefault;//是否系统默认角色

	@Column(name="role_desc")
	private String roleDesc;//角色描述

	private String status;//状态，参见 Constants.PERMISSIONS_ENABLED、Constants.PERMISSIONS_DISABLED

	@Column(name="updated_time", insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user", insertable=false)
	private String updatedUser;//修改用户的登录帐号

	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="t_menu_role"
		, joinColumns={
			@JoinColumn(name="role_code")
			}
		, inverseJoinColumns={
			@JoinColumn(name="menu_code")
			}
		)
	private List<Menu> menus;//菜单列表

    public Role() {
    }

    public Role(String roleCode, String parentCode, String roleName) {
    	this.roleCode = roleCode;
    	this.parentCode = parentCode;
    	this.roleName = roleName;
    }
    
    public Role(String roleCode, String parentCode, String roleName, 
    		String isDefault, String status, String roleDesc) {
    	this.roleCode = roleCode;
    	this.parentCode = parentCode;
    	this.roleName = roleName;
    	this.isDefault = isDefault;
    	this.status = status;
    	this.roleDesc = roleDesc;
    }
    
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1736456943, 436367721)
				.append(this.roleCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Role)) {
			return false;
		}
		Role role = (Role) object;
		return new EqualsBuilder()
				.append(this.roleCode, role.roleCode).isEquals();
	}	
}