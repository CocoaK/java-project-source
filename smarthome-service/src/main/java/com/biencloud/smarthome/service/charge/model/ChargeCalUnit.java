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
 * 类名称：ChargeCalMode 
 * 类描述： 计算单位实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:26:35
 */
@Entity
@Table(name = "t_charge_calunit")
public class ChargeCalUnit extends BaseEntity {
	private static final long serialVersionUID = -782564554851886L;

	// Fields

	private Long id;
	private String name;//计算单位名称
	private String reamrk;//描述

	// Constructors

	/** default constructor */
	public ChargeCalUnit() {
	}

	/** minimal constructor */
	public ChargeCalUnit(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public ChargeCalUnit(Long id, String name, String reamrk, Set<ChargeType> chargeTypes) {
		this.id = id;
		this.name = name;
		this.reamrk = reamrk;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "reamrk", length = 65535)
	public String getReamrk() {
		return this.reamrk;
	}

	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

	

}