package com.biencloud.smarthome.service.info.model;

import com.biencloud.smarthome.service.base.model.BaseEntity;
/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：DistrictData 
 * 类描述： 业主、小区相关实体类型
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:02:58
 */
public class DistrictData extends BaseEntity {
	
	private static final long serialVersionUID = -4280776516618553397L;
	private String userId;//用户ID
	private String houseId;//房间ID
	private String houseName;//房间名
	private String cellId;//单元ID
	private String cellName;//单元名称
	private String buildingId;//栋ID
	private String buildingName;//栋名称
	private String regionId;//区域ID
	private String regionName;//区域名称
	private String districtId;//小区ID
	private String districtName;//小区名称
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	

}
