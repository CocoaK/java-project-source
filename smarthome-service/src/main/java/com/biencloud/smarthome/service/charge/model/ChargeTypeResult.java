package com.biencloud.smarthome.service.charge.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeTypeResult 
 * 类描述： 收费项目结果实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:28:37
 */
@Entity
@Table(name = "t_charge_type_result")
public class ChargeTypeResult extends BaseEntity {
	private static final long serialVersionUID = -78256454851886L;

	// Fields

	private Long id;
//	private ChargeType chargeType;
//	private ChargeDetail chargeDetail;
	//private ChargeData chargeData;
	private String startTotal;//起始使用量
	private Long chargeDataId;//收费数据ID
	private Long chargeDetailId;//收费清单ID
	private String endTotal;//结束使用量
	private String actualTotal;//实际使用量
	private String playMoney;//支付总金额
	private String remark;//描述
	private String standard;//收费标准
	private String calUnit;//计算单位
	private String chargeTypeName;//收费项目名称
	private String calMode;//计算模式

	
	// Constructors
	@Column(name = "chargeDataId")
	public Long getChargeDataId() {
		return chargeDataId;
	}

	public void setChargeDataId(Long chargeDataId) {
		this.chargeDataId = chargeDataId;
	}

	/** default constructor */
	public ChargeTypeResult() {
	}

	/** minimal constructor */
	public ChargeTypeResult(Long id, ChargeType chargeType, String playMoney) {
		this.id = id;
//		this.chargeType = chargeType;
		this.playMoney = playMoney;
	}

	/** full constructor */
	public ChargeTypeResult(Long id, ChargeType chargeType, String startTotal, String endTotal, String actualTotal, String playMoney,
			String remark, Long chargeDataId, Long chargeDetailId) {
		this.id = id;
//		this.chargeType = chargeType;
		this.startTotal = startTotal;
		this.endTotal = endTotal;
		this.actualTotal = actualTotal;
		this.playMoney = playMoney;
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

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chargeTypeId", nullable = false)
	public ChargeType getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}*/

	@Column(name = "start_total", precision = 12, scale = 0)
	public String getStartTotal() {
		return this.startTotal;
	}

	public void setStartTotal(String startTotal) {
		this.startTotal = startTotal;
	}

	@Column(name = "end_total", precision = 12, scale = 0)
	public String getEndTotal() {
		return this.endTotal;
	}

	public void setEndTotal(String endTotal) {
		this.endTotal = endTotal;
	}

	@Column(name = "actual_total", precision = 12, scale = 0)
	public String getActualTotal() {
		return this.actualTotal;
	}

	public void setActualTotal(String actualTotal) {
		this.actualTotal = actualTotal;
	}
	
	@Column(name = "chargeTypeName", length = 100)
	public String getChargeTypeName() {
		return this.chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}

	@Column(name = "calMode", length = 20)
	public String getCalMode() {
		return this.calMode;
	}

	public void setCalMode(String calMode) {
		this.calMode = calMode;
	}

	@Column(name = "play_money", nullable = false, precision = 12, scale = 0)
	public String getPlayMoney() {
		return this.playMoney;
	}

	public void setPlayMoney(String playMoney) {
		this.playMoney = playMoney;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	@Column(name = "standard", nullable = false, precision = 12, scale = 0)
	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@Column(name = "calUnit")
	public String getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(String calUnit) {
		this.calUnit = calUnit;
	}
	@Column(name = "chargeDetailId")
	public Long getChargeDetailId() {
		return chargeDetailId;
	}

	public void setChargeDetailId(Long chargeDetailId) {
		this.chargeDetailId = chargeDetailId;
	}
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chargeDetailId",insertable=false, updatable=false)
	public ChargeDetail getChargeDetail() {
		return this.chargeDetail;
	}

	public void setChargeDetail(ChargeDetail chargeDetail) {
		this.chargeDetail = chargeDetail;
	}*/

	/*@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name = "chargeDataId",insertable=false, updatable=false)
	public ChargeData getChargeData() {
		return this.chargeData;
	}

	public void setChargeData(ChargeData chargeData) {
		this.chargeData = chargeData;
	}*/

}