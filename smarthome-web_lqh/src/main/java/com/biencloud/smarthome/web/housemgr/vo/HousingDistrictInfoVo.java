package com.biencloud.smarthome.web.housemgr.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 小区VO
 * 从属关系: 小区 -> 区域 -> 楼宇 -> 单元 -> 房号(户)
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public class HousingDistrictInfoVo extends BaseVO {
	private static final long serialVersionUID = 7094120905508025106L;

	private String id;//小区ID
	private String code;//小区编码(定长), 由用户输入的4位数字组成
	private String constructionArea;//总建筑面积(平方米)
	private String developer;//开发商
	private String district;//地址
	private String floorPlan;//小区平面图(只保存文件的路径)
	private String name;//小区名称
	private String propertyCompanyAddress;//物业公司地址
	private String groupId;//所属的组织机构ID
	private String position;

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	private PropertyCompanyInfoVo propertyCompanyInfo;//所属物业公司

	public PropertyCompanyInfoVo getPropertyCompanyInfo() {
		return propertyCompanyInfo;
	}
	public void setPropertyCompanyInfo(PropertyCompanyInfoVo propertyCompanyInfo) {
		this.propertyCompanyInfo = propertyCompanyInfo;
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
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getFloorPlan() {
		return floorPlan;
	}
	public void setFloorPlan(String floorPlan) {
		this.floorPlan = floorPlan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropertyCompanyAddress() {
		return propertyCompanyAddress;
	}
	public void setPropertyCompanyAddress(String propertyCompanyAddress) {
		this.propertyCompanyAddress = propertyCompanyAddress;
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(534378607, -1590002485)
				.append(this.id)
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HousingDistrictInfoVo)) {
			return false;
		}
		HousingDistrictInfoVo hdi = (HousingDistrictInfoVo) object;
		return new EqualsBuilder()
				.append(this.id, hdi.id)
				.isEquals();
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}	
	
}
