package com.biencloud.smarthome.service.housemgr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;


/**
 * 单元  实体类
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_building_cell_info")
public class BuildingCellInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 单元ID
	 */
	private String id;
	/**
	 * 单元编码(定长), 由用户输入的2位数字, 从01开始
	 */
	private String code;
	/**
	 * 单元名称
	 */
	private String name;
	/**
	 * 单元在小区平面图上的坐标位置(屏幕坐标)
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
	 * 单元所属楼宇
	 */
    @ManyToOne
	@JoinColumn(name="BUILDING_ID")
	private RegionBuildingInfo THmRegionBuildingInfo;

    /**
     * 单元中的房号
     */
    @Transient
	private List<CellHouseholdInfo> THmCellHouseholdInfos;

    /**
     * 单元的户型信息
     */
	@Transient
	private List<CellSizeInfo> THmCellSizeInfos;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 单元编码(定长), 由用户输入的2位数字, 从01开始
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
	 * 单元在小区平面图上的坐标位置(屏幕坐标)
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
	 * 用户在系统中创建单元的时间
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
	 * 创建单元的用户ID
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
	 * 单元所属楼宇
	 * 
	 * @return
	 */
	public RegionBuildingInfo getTHmRegionBuildingInfo() {
		return this.THmRegionBuildingInfo;
	}
	public void setTHmRegionBuildingInfo(RegionBuildingInfo THmRegionBuildingInfo) {
		this.THmRegionBuildingInfo = THmRegionBuildingInfo;
	}
	
	/**
	 * 单元中的房号
	 * 
	 * @return
	 */
	public List<CellHouseholdInfo> getTHmCellHouseholdInfos() {
		return this.THmCellHouseholdInfos;
	}
	public void setTHmCellHouseholdInfos(List<CellHouseholdInfo> THmCellHouseholdInfos) {
		this.THmCellHouseholdInfos = THmCellHouseholdInfos;
	}
	
	/**
	 * 单元的户型信息
	 * 
	 * @return
	 */
	public List<CellSizeInfo> getTHmCellSizeInfos() {
		return this.THmCellSizeInfos;
	}
	public void setTHmCellSizeInfos(List<CellSizeInfo> THmCellSizeInfos) {
		this.THmCellSizeInfos = THmCellSizeInfos;
	}
}