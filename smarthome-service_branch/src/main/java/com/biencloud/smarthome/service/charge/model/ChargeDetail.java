package com.biencloud.smarthome.service.charge.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeDetail 
 * 类描述： 收费清单实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:27:27
 */
@Entity
@Table(name = "t_charge_detail")
public class ChargeDetail extends BaseEntity {
	private static final long serialVersionUID = -78256645244851886L;
	
	/** 表示未缴费 */
	public static final Integer FeeStatusNO=2;
	/** 表示已缴费 */
	public static final Integer FeeStatusYES=1;

	// Fields

	private Long id;
	private ChargeData chargeData;//收费数据
	private String printSn;//打印流水号
	private String chargeStatus;//缴费状态
	private String chargeTime;//收费时间
	private String chargeStartTime;//收费开始时间
	private String chargeEndTime;//收费结束时间
	private Long infoId;//已发送催费信息次数
	private String remark;//描述
	private Set<ChargeTypeResult> chargeTypeResults = new HashSet<ChargeTypeResult>(0);//收费项目集合

	// Constructors

	/** default constructor */
	public ChargeDetail() {
	}

	/** minimal constructor */
	public ChargeDetail(Long id, ChargeData chargeData) {
		this.id = id;
		this.chargeData = chargeData;
	}

	/** full constructor */
	public ChargeDetail(Long id, ChargeData chargeData, String printSn, String chargeStatus, String chargeTime, Long infoId, String remark) {
		this.id = id;
		this.chargeData = chargeData;
		this.printSn = printSn;
		this.chargeStatus = chargeStatus;
		this.chargeTime = chargeTime;
		this.infoId = infoId;
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
	
	
	@Transient
	public String getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(String chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	@Transient
	public String getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(String chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chargeDataId", nullable = false)
	public ChargeData getChargeData() {
		return this.chargeData;
	}

	public void setChargeData(ChargeData chargeData) {
		this.chargeData = chargeData;
	}

	@Column(name = "print_sn", length = 30)
	public String getPrintSn() {
		return this.printSn;
	}

	public void setPrintSn(String printSn) {
		this.printSn = printSn;
	}

	@Column(name = "charge_status", length = 2)
	public String getChargeStatus() {
		return this.chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	@Column(name = "charge_time", length = 10)
	public String getChargeTime() {
		return this.chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	@Column(name = "infoId")
	public Long getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@OneToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH}, fetch = FetchType.EAGER, mappedBy = "chargeDetailId")
	public Set<ChargeTypeResult> getChargeTypeResults() {
		return this.chargeTypeResults;
	}

	public void setChargeTypeResults(Set<ChargeTypeResult> chargeTypeResults) {
		this.chargeTypeResults = chargeTypeResults;
	}

}