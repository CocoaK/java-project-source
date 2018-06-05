package com.biencloud.smarthome.service.housemgr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.user.model.OwnerUser;


/**
 * 房号 实体类
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_cell_household_info")
public class CellHouseholdInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -6214591878091716763L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", updatable=false)
	/**
     * 房号ID
     */
	private String id;
    /**
     * 房号编码(定长) 由用户输入的4位数字, 从0001开始
     */
	private String code;
	/**
     * 房号名称
     */
	private String name;
	/**
	 * 房屋的业主
	 */
	@OneToOne
	@JoinColumn(name="user_id")
	private OwnerUser owner;

	/**
	 * 面积(平方米)
	 */
	private String area;

	/**
	 * 入住时间
	 */
    @Temporal( TemporalType.DATE)
	@Column(name="CHECK_IN_DATE")
	private Date checkInDate;

	/**
	 * 楼宇在小区平面图上的坐标位置(屏幕坐标)
	 */
	private String coordinate;

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
	 * 房屋状态(0: 未入住, 1: 已入住)
	 */
	@Column(name="HOUSING_STATUS")
	private String housingStatus;

	/**
	 * 房号所属的单元信息
	 */
    @ManyToOne
	@JoinColumn(name="CELL_ID")
	private BuildingCellInfo THmBuildingCellInfo;

    /**
     * 房屋的户型
     */
    @ManyToOne
	@JoinColumn(name="SIZE_ID")
	private CellSizeInfo THmCellSizeInfo;

    /**
     * 房号的收费类型
     */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_hm_housing_charge_setting", joinColumns = { @JoinColumn(name = "HOUSEHOLD_ID") }, inverseJoinColumns = { @JoinColumn(name = "CHARGE_TYPE_ID") })
	private List<ChargeType> chargeTypes;

    /**
     * 房号的收费类型
     * 
     * @return
     */
	public List<ChargeType> getChargeTypes() {
		return chargeTypes;
	}
	public void setChargeTypes(List<ChargeType> chargeTypes) {
		this.chargeTypes = chargeTypes;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 面积(平方米)
	 * 
	 * @return
	 */
	public String getArea() {
		return this.area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 入住时间
	 * 
	 * @return
	 */
	public Date getCheckInDate() {
		return this.checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

    /**
     * 房号编码(定长) 由用户输入的4位数字, 从0001开始
     * 
     * @return
     */
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 楼宇在小区平面图上的坐标位置(屏幕坐标)
	 * 
	 * @return x,y坐标, 例如1,1
	 */
	public String getCoordinate() {
		return this.coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 用户在系统中创建房号的时间
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
	 * 创建房号的用户ID
	 * 
	 * @return
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 房屋状态(0: 未入住, 1: 已入住)
	 * 
	 * @return
	 */
	public String getHousingStatus() {
		return this.housingStatus;
	}
	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 房号所属的单元信息
	 * 
	 * @return
	 */
	public BuildingCellInfo getTHmBuildingCellInfo() {
		return this.THmBuildingCellInfo;
	}
	public void setTHmBuildingCellInfo(BuildingCellInfo THmBuildingCellInfo) {
		this.THmBuildingCellInfo = THmBuildingCellInfo;
	}
	
    /**
     * 房屋的户型
     * 
     * @return
     */
	public CellSizeInfo getTHmCellSizeInfo() {
		return this.THmCellSizeInfo;
	}
	public void setTHmCellSizeInfo(CellSizeInfo THmCellSizeInfo) {
		this.THmCellSizeInfo = THmCellSizeInfo;
	}

	/**
	 * 房屋的业主
	 * 
	 * @return
	 */
	public OwnerUser getOwner() {
		return owner;
	}
	public void setOwner(OwnerUser owner) {
		this.owner = owner;
	}
}