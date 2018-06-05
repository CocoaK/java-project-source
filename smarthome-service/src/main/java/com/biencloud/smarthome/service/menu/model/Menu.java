package com.biencloud.smarthome.service.menu.model;

import javax.persistence.*;

import java.util.Date;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.common.constants.Constants;


/**
 * 菜单实体对象。
 * @author kouy
 * @since 1.0 2012-4-16
 */
@SuppressWarnings("unused")
@Entity
@Table(name="t_menu")
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 5166461918727832573L;

	@Id
	@Column(name="menu_code")
	private String menuCode;//菜单代码

	@Column(name="menu_desc")
	private String menuDesc;//菜单描述

	@Column(name="menu_name", updatable=false)
	private String menuName;//菜单名称

	@Column(name="menu_url")
	private String menuUrl;//菜单链接地址

	@Column(name="parent_code")
	private String parentCode;//上级菜单代码

	private String status;//状态，参见 Constants.MENU_STATUS_VISIBLE、Constants.MENU_STATUS_HIDDEN

	@Column(name="menu_order", updatable=false)
	private int menuOrder;//菜单排序值，值越小显示在越前面
	
	@Column(name="updated_time", insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user", insertable=false)
	private String updatedUser;//修改用户的登录帐号


    public Menu() {
    }

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
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
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(156923857, -748765885)
				.append(this.menuCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Menu)) {
			return false;
		}
		Menu menu = (Menu) object;
		return new EqualsBuilder()
				.append(this.menuCode, menu.menuCode).isEquals();
	}		
}