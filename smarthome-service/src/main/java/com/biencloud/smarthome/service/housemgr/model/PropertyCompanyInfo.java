package com.biencloud.smarthome.service.housemgr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 物业公司 实体类
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_property_company_info")
public class PropertyCompanyInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 物业公司ID
	 */
	private int id;
	/**
	 * 物业公司名称
	 */
	private String name;
	/**
	 * 公司简介
	 */
	private String profile;
	/**
	 * 负责人
	 */
	private String charge;
	/**
	 * 联系方式
	 */
	private String contact;

	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME")
	private Date createTime;
	/**
	 * 创建用户的登录帐号
	 */
	@Column(name="CREATE_USER_ID")
	private String createUserId;

	/**
	 * 物业公司管辖的小区
	 */
//	@OneToMany(mappedBy="propertyCompanyInfo")
	@Transient
	private List<HousingDistrictInfo> housingDistrictInfos;

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 负责人
	 * 
	 * @return
	 */
	public String getCharge() {
		return this.charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}

	/**
	 * 联系方式
	 * 
	 * @return
	 */
	public String getContact() {
		return this.contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 用户在系统中创建物业公司的时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建物业公司的用户ID
	 * 
	 * @return
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 公司简介
	 * 
	 * @return
	 */
	public String getProfile() {
		return this.profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * 物业公司管辖的小区
	 * 
	 * @return
	 */
	public List<HousingDistrictInfo> getHousingDistrictInfos() {
		return this.housingDistrictInfos;
	}
	public void setTHmHousingDistrictInfos(List<HousingDistrictInfo> housingDistrictInfos) {
		this.housingDistrictInfos = housingDistrictInfos;
	}
}