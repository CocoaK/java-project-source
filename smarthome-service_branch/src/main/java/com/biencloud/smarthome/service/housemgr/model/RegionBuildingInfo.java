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
 * 楼宇 实体类
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_region_building_info")
public class RegionBuildingInfo extends BaseEntity {
	private static final long serialVersionUID = -2184802367678867337L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 楼宇ID
	 */
	private String id;
	/**
	 * 楼宇编码(定长), 由用户输入的3位数字, 从001开始
	 */
	private String code;
	/**
	 * 楼宇名称
	 */
	private String name;

	/**
	 * 占地面积(平方米)
	 */
	@Column(name="COVER_AREA")
	private String coverArea;
	/**
	 * 建筑面积(平方米)
	 */
	@Column(name="CONSTRUCTION_AREA")
	private String constructionArea;

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
	 * 楼宇中包含的单元
	 */
	@Transient
	private List<BuildingCellInfo> THmBuildingCellInfos;

	/**
	 * 楼宇所属的区域
	 */
    @ManyToOne
	@JoinColumn(name="REGION_ID")
	private HousingDistrictRegionInfo THmHousingDistrictRegionInfo;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 楼宇编码(定长), 由用户输入的3位数字, 从001开始
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
	 * 建筑面积(平方米)
	 * 
	 * @return
	 */
	public String getConstructionArea() {
		return this.constructionArea;
	}
	public void setConstructionArea(String constructionArea) {
		this.constructionArea = constructionArea;
	}

	/**
	 * 楼宇在小区平面图上的坐标位置(屏幕坐标)
	 * 
	 * @return
	 */
	public String getCoordinate() {
		return this.coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 占地面积(平方米)
	 * 
	 * @return
	 */
	public String getCoverArea() {
		return this.coverArea;
	}
	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}

	/**
	 * 用户在系统中创建楼宇的时间
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
	 * 创建楼宇的用户ID
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
	 * 楼宇中包含的单元
	 * 
	 * @return
	 */
	public List<BuildingCellInfo> getTHmBuildingCellInfos() {
		return this.THmBuildingCellInfos;
	}
	public void setTHmBuildingCellInfos(List<BuildingCellInfo> THmBuildingCellInfos) {
		this.THmBuildingCellInfos = THmBuildingCellInfos;
	}

	/**
	 * 楼宇所属的区域
	 * 
	 * @return
	 */
	public HousingDistrictRegionInfo getTHmHousingDistrictRegionInfo() {
		return this.THmHousingDistrictRegionInfo;
	}
	public void setTHmHousingDistrictRegionInfo(HousingDistrictRegionInfo THmHousingDistrictRegionInfo) {
		this.THmHousingDistrictRegionInfo = THmHousingDistrictRegionInfo;
	}
}