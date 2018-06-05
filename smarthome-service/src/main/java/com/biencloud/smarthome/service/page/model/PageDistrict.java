package com.biencloud.smarthome.service.page.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_page_district")
public class PageDistrict extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private String id;
	
	@Column(name="page_id")
	private String pageId;
	
	@OneToOne
	@JoinColumn(name="district_id")
	private HousingDistrictInfo housingDistrictInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}
}
