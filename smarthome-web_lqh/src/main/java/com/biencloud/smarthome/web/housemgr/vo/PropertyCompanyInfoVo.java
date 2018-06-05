package com.biencloud.smarthome.web.housemgr.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 物业公司VO
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public class PropertyCompanyInfoVo  extends BaseVO {
	private static final long serialVersionUID = 6486529441507862321L;

	private int id;//物业公司ID
	private String charge;//负责人
	private String contact;//联系方式
	private Date createTime;//创建时间
	private String createUserId;//创建用户的登录帐号
	private String name;//物业公司名称
	private String profile;//公司简介

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
