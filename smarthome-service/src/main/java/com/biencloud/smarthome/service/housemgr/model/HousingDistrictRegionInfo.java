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
 * 区域 实体类
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_housing_district_region_info")
public class HousingDistrictRegionInfo extends BaseEntity {
	private static final long serialVersionUID = -6938799039258078115L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 区域ID
	 */
	private String id;
	/**
	 * 区域编码(定长), 由用户输入的2位数字, 从01开始
	 */
	private String code;
	/**
	 * 区域名称
	 */
	private String name;

	/**
	 * 区域在小区平面图上的坐标位置(屏幕坐标)
	 */
	private String coordinate;

	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME", updatable=false)
	private Date createTime;
	/**
	 * 创建用户的登录帐号
	 */
	@Column(name="CREATE_USER_ID", updatable=false)
	private String createUserId;

	/**
	 * 区域所属的小区
	 */
    @ManyToOne
	@JoinColumn(name="HOUSING_DISTRICT_ID")
	private HousingDistrictInfo housingDistrictInfo;

    /**
     * 区域中包含的楼宇
     */
    @Transient
	private List<RegionBuildingInfo> THmRegionBuildingInfos;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 区域编码(定长), 由用户输入的2位数字, 从01开始
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
	 * 区域在小区平面图上的坐标位置(屏幕坐标)
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
	 * 用户在系统中创建区域的时间
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
	 * 创建区域的用户ID
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
	 * 区域所属的小区
	 * 
	 * @return
	 */
	public HousingDistrictInfo getHousingDistrictInfo() {
		return this.housingDistrictInfo;
	}
	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	/**
     * 区域中包含的楼宇
     * 
     * @return
     */
	public List<RegionBuildingInfo> getTHmRegionBuildingInfos() {
		return this.THmRegionBuildingInfos;
	}
	public void setTHmRegionBuildingInfos(List<RegionBuildingInfo> THmRegionBuildingInfos) {
		this.THmRegionBuildingInfos = THmRegionBuildingInfos;
	}
}