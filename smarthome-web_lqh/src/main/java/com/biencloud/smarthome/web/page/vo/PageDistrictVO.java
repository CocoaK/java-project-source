package com.biencloud.smarthome.web.page.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

@SuppressWarnings("serial")
public class PageDistrictVO extends BaseVO{
	private String id;
	
	private String pageId;
	
	private HousingDistrictInfoVo housingDistrictInfo;

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

	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

}
