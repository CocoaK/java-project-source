package com.biencloud.smarthome.service.charge.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeType 
 * 类描述： 收费项目实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:28:25
 */
@Entity
@Table(name = "t_charge_type")
public class ChargeType  extends BaseEntity {
	private static final long serialVersionUID = -7825664487851886L;
	public static final String OwnerChargeType="selected";//用于标识判断业主有哪些收费项目

	// Fields

	private Long id;
	private HousingDistrictInfo housingDistrictInfo;//所属小区
	private PaUser PaUser;//物管用户
	private ChargeMonetaryUnit chargeMonetaryUnit;//收费单位
	private ChargeCalUnit chargeCalUnit;//计算单位
	private ChargeCalMode chargeCalMode;//计算模式
	private String name;//收费项目名称
	private String standard;//收费标准
	private String chargeMode;//收费模式
	private String remark;//描述
	private Date createTime;//创建时间

	// Constructors

	/** default constructor */
	public ChargeType() {
	}

	/** minimal constructor */
	public ChargeType(Long id, SystemGroup systemGroup, ChargeMonetaryUnit chargeMonetaryUnit, ChargeCalMode chargeCalMode, String name,
			String standard, String chargeMode) {
		this.id = id;
		this.chargeMonetaryUnit = chargeMonetaryUnit;
		this.chargeCalMode = chargeCalMode;
		this.name = name;
		this.standard = standard;
		this.chargeMode = chargeMode;
	}

	/** full constructor */
	public ChargeType(Long id, SystemGroup systemGroup, PaUser PaUser, ChargeMonetaryUnit chargeMonetaryUnit,
			ChargeCalMode chargeCalMode, String name, String standard, String chargeMode, String remark, Date createTime,
			Set<ChargeTypeResult> chargeTypeResults) {
		this.id = id;
		this.PaUser = PaUser;
		this.chargeMonetaryUnit = chargeMonetaryUnit;
		this.chargeCalMode = chargeCalMode;
		this.name = name;
		this.standard = standard;
		this.chargeMode = chargeMode;
		this.remark = remark;
		this.createTime = createTime;
	}

	// Property accessors
	@Id @GeneratedValue(strategy=IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comId")
	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public PaUser getPaUser() {
		return this.PaUser;
	}

	public void setPaUser(PaUser PaUser) {
		this.PaUser = PaUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "monetaryUnitId")
	public ChargeMonetaryUnit getChargeMonetaryUnit() {
		return this.chargeMonetaryUnit;
	}

	public void setChargeMonetaryUnit(ChargeMonetaryUnit chargeMonetaryUnit) {
		this.chargeMonetaryUnit = chargeMonetaryUnit;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "calModeId")
	public ChargeCalMode getChargeCalMode() {
		return this.chargeCalMode;
	}

	public void setChargeCalMode(ChargeCalMode chargeCalMode) {
		this.chargeCalMode = chargeCalMode;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "standard",  precision = 12, scale = 0)
	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@Column(name = "charge_mode",  length = 10)
	public String getChargeMode() {
		return this.chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "calUnitId")
	public ChargeCalUnit getChargeCalUnit() {
		return chargeCalUnit;
	}

	public void setChargeCalUnit(ChargeCalUnit chargeCalUnit) {
		this.chargeCalUnit = chargeCalUnit;
	}
	

}