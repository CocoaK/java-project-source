package com.biencloud.smarthome.web.permissions.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.menu.vo.MenuVO;

/**
 * 角色值对象。
 * @author kouy
 * @since 1.0 2012-4-27
 */
public class RoleVO extends BaseVO {

	private static final long serialVersionUID = 3423817122607890738L;
	
	private String roleCode;//角色代码
	private String parentCode;//上级角色代码
	private String roleName;//角色名称	
	private Date createdTime;//创建时间
	private String createdUser;//创建用户的登录帐号
	private String isDefault;//是否系统默认角色
	private String roleDesc;//角色描述
	private String status;//状态，"0"：启用、"1"：禁用
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号
	
	private List<MenuVO> menus;//菜单列表

	
	public String getRoleCode() {
		return roleCode;
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

	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public List<MenuVO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1759844337, 2140317817)
				.append(this.roleCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RoleVO)) {
			return false;
		}
		RoleVO role = (RoleVO) object;
		return new EqualsBuilder()				
				.append(this.roleCode, role.roleCode).isEquals();
	}	
}
