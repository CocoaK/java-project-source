package com.biencloud.smarthome.service.charge.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeMonetaryUnit 
 * 类描述： 收费货币单位实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:27:49
 */
@Entity
@Table(name = "t_charge_monetaryunit")
public class ChargeMonetaryUnit extends BaseEntity {
	private static final long serialVersionUID = -782566872411851886L;
	// Fields

	private Long id;
	private String monetaryCode;//货币编码
	private String codeName;//货币名称
	private String remark;//描述

	// Constructors

	/** default constructor */
	public ChargeMonetaryUnit() {
	}

	/** minimal constructor */
	public ChargeMonetaryUnit(Long id, String monetaryCode, String codeName) {
		this.id = id;
		this.monetaryCode = monetaryCode;
		this.codeName = codeName;
	}

	/** full constructor */
	public ChargeMonetaryUnit(Long id, String monetaryCode, String codeName, String remark, Set<ChargeType> chargeTypes) {
		this.id = id;
		this.monetaryCode = monetaryCode;
		this.codeName = codeName;
		this.remark = remark;
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

	@Column(name = "monetary_code", nullable = false, length = 20)
	public String getMonetaryCode() {
		return this.monetaryCode;
	}

	public void setMonetaryCode(String monetaryCode) {
		this.monetaryCode = monetaryCode;
	}

	@Column(name = "code_name", nullable = false, length = 20)
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}