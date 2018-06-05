package com.biencloud.smarthome.web.ad.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;

/**
 * 广告投放目标值对象。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public class AdTargetVO extends BaseVO {

	private static final long serialVersionUID = 7080305669121621503L;
	
	private String adTargetId;//广告投放目标ID
	private DeviceVO device;//设备信息
	private String adId;//广告ID
	
	
	public String getAdTargetId() {
		return adTargetId;
	}
	public void setAdTargetId(String adTargetId) {
		this.adTargetId = adTargetId;
	}
	
	public DeviceVO getDevice() {
		return device;
	}
	public void setDevice(DeviceVO device) {
		this.device = device;
	}
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}	
}
