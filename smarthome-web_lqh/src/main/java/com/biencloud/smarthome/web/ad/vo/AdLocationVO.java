package com.biencloud.smarthome.web.ad.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 广告投放位置值对象。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public class AdLocationVO extends BaseVO {

	private static final long serialVersionUID = -6119060288581206030L;
	
	private String locationCode;//广告投放位置代码
	private String locationCoordinate;//广告投放位置坐标
	private String locationName;//广告投放位置名称
	
	private AdSysVO adSys;//广告投放目标系统

	
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationCoordinate() {
		return locationCoordinate;
	}

	public void setLocationCoordinate(String locationCoordinate) {
		this.locationCoordinate = locationCoordinate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public AdSysVO getAdSys() {
		return adSys;
	}

	public void setAdSys(AdSysVO adSys) {
		this.adSys = adSys;
	}
}
