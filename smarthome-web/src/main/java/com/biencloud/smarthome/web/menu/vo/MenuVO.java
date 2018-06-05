package com.biencloud.smarthome.web.menu.vo;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 菜单管理值对象。
 * @author kouy
 * @since 1.0 2012-4-26
 */
public class MenuVO extends BaseVO {

	private static final long serialVersionUID = 7803237083862884330L;
	
	private String menuCode;//菜单代码
	private String menuDesc;//菜单描述
	private String menuName;//菜单名称
	private String menuUrl;//菜单链接地址
	private String parentCode;//上级菜单代码	
	private String status;//状态，"0"：显示、"1"：隐藏
	private int menuOrder;//菜单排序值，值越小显示在越前面
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号



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
	public boolean equals(Object object) {
		if (!(object instanceof MenuVO)) {
			return false;
		}
		MenuVO menuVO = (MenuVO) object;
		return new EqualsBuilder().append(this.menuCode, menuVO.menuCode).isEquals();
	}
}
