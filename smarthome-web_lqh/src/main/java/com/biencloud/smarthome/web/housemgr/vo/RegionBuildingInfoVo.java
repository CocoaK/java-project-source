package com.biencloud.smarthome.web.housemgr.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 楼宇VO
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public class RegionBuildingInfoVo extends BaseVO {
	private static final long serialVersionUID = -4965247808953553799L;

	private String id;//楼宇ID
	private String code;//楼宇编码(定长), 由用户输入的3位数字组成
	private String constructionArea;//建筑面积(平方米)
	private String coordinate;//楼宇在小区平面图上的坐标位置(屏幕坐标)
	private String coverArea;//占地面积(平方米)
	private Date createTime;//创建时间
	private String createUserId;//创建用户的登录帐号
	private String name;//楼宇名称

	private HousingDistrictRegionInfoVo THmHousingDistrictRegionInfo;//楼宇所属的区域

	public HousingDistrictRegionInfoVo getTHmHousingDistrictRegionInfo() {
		return THmHousingDistrictRegionInfo;
	}
	public void setTHmHousingDistrictRegionInfo(HousingDistrictRegionInfoVo tHmHousingDistrictRegionInfo) {
		THmHousingDistrictRegionInfo = tHmHousingDistrictRegionInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getConstructionArea() {
		return constructionArea;
	}
	public void setConstructionArea(String constructionArea) {
		this.constructionArea = constructionArea;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getCoverArea() {
		return coverArea;
	}
	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
