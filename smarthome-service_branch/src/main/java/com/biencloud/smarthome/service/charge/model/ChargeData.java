package com.biencloud.smarthome.service.charge.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
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
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeData 
 * 类描述： 收费数据实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:27:10
 */
@Entity
@Table(name = "t_charge_data")
public class ChargeData extends BaseEntity {
	private static final long serialVersionUID = -782566452451886L;
	/** 表示未生成收费清单  */
	public static final Integer ChargeDataStatusNO=2;
	/**  表示已生成收费清单 */
	public static final Integer ChargeDataStatusYES=1;

	// Fields

	private Long id;
	private HousingDistrictInfo housingDistrictInfo;//所属小区
	private CellHouseholdInfo cellHouseholdInfo;//房间
	private PaUser PaUser;//物管用户
	private RegionBuildingInfo regionBuildingInfo;//楼栋
	private String ownerName;//业主姓名
	private String totalMoney;//总金额
	private String monetaryUnit;//货币单位
	private String chargeTime;//收费时间
	private String isproductDetail;//是否已产生收费清单
	private Date createTime;//创建时间
	private Date createStartTime;//创建开始时间
	private Date createEndTime;//创建结束时间
	private String remark;//描述
//	private Set<ChargeDetail> chargeDetails = new HashSet<ChargeDetail>(0);
	private Set<ChargeTypeResult> chargeTypeResults = new HashSet<ChargeTypeResult>(0);//收费项目集合

	// Constructors

	/** default constructor */
	public ChargeData() {
	}

	/** minimal constructor */
	public ChargeData(Long id, SystemGroup systemGroup, CellHouseholdInfo CellHouseholdInfo, RegionBuildingInfo RegionBuildingInfo,
			String ownerName, String totalMoney, String monetaryUnit) {
		this.id = id;
		this.cellHouseholdInfo = CellHouseholdInfo;
		this.regionBuildingInfo = RegionBuildingInfo;
		this.ownerName = ownerName;
		this.totalMoney = totalMoney;
		this.monetaryUnit = monetaryUnit;
	}

	/** full constructor */
	public ChargeData(Long id, SystemGroup systemGroup, CellHouseholdInfo CellHouseholdInfo, PaUser PaUser,
			RegionBuildingInfo RegionBuildingInfo, String ownerName, String totalMoney, String monetaryUnit, String chargeTime,
			String isproductDetail, Date createTime, String remark, Set<ChargeDetail> chargeDetails) {
		this.id = id;
		this.cellHouseholdInfo = CellHouseholdInfo;
		this.PaUser = PaUser;
		this.regionBuildingInfo = RegionBuildingInfo;
		this.ownerName = ownerName;
		this.totalMoney = totalMoney;
		this.monetaryUnit = monetaryUnit;
		this.chargeTime = chargeTime;
		this.isproductDetail = isproductDetail;
		this.createTime = createTime;
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
	public Date getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	@Transient
	public Date getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
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
	@JoinColumn(name = "roomId")
	public CellHouseholdInfo getCellHouseholdInfo() {
		return this.cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfo CellHouseholdInfo) {
		this.cellHouseholdInfo = CellHouseholdInfo;
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
	@JoinColumn(name = "homeId")
	public RegionBuildingInfo getRegionBuildingInfo() {
		return this.regionBuildingInfo;
	}

	public void setRegionBuildingInfo(RegionBuildingInfo RegionBuildingInfo) {
		this.regionBuildingInfo = RegionBuildingInfo;
	}

	@Column(name = "owner_name", length = 10)
	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "total_money", precision = 12, scale = 0)
	public String getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "monetaryUnit", length = 10)
	public String getMonetaryUnit() {
		return this.monetaryUnit;
	}

	public void setMonetaryUnit(String monetaryUnit) {
		this.monetaryUnit = monetaryUnit;
	}

	@Column(name = "charge_time", length = 30)
	public String getChargeTime() {
		return this.chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	@Column(name = "isproduct_detail", length = 2)
	public String getIsproductDetail() {
		return this.isproductDetail;
	}

	public void setIsproductDetail(String isproductDetail) {
		this.isproductDetail = isproductDetail;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chargeData")
	public Set<ChargeDetail> getChargeDetails() {
		return this.chargeDetails;
	}

	public void setChargeDetails(Set<ChargeDetail> chargeDetails) {
		this.chargeDetails = chargeDetails;
	}*/
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="chargeDataId")
	public Set<ChargeTypeResult> getChargeTypeResults() {
		return this.chargeTypeResults;
	}

	public void setChargeTypeResults(Set<ChargeTypeResult> chargeTypeResults) {
		this.chargeTypeResults = chargeTypeResults;
	}

}