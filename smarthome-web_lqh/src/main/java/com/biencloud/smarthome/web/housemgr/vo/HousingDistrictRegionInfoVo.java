package com.biencloud.smarthome.web.housemgr.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 区域VO
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public class HousingDistrictRegionInfoVo extends BaseVO {
	private static final long serialVersionUID = 7595361770433281621L;

	private String id;//区域ID
	private String code;//区域编码(定长), 由用户输入的2位数字组成
	private String coordinate;//区域在小区平面图上的坐标位置(屏幕坐标)
	private Date createTime;//创建时间
	private String createUserId;//创建用户的登录帐号
	private String name;//区域名称
	private HousingDistrictInfoVo housingDistrictInfo;//区域所属的小区

	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}
	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
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
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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
