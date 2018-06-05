package com.biencloud.smarthome.web.softwareupgrade.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

/**
 * 软件升级目标值对象。
 * @author kouy
 * @since 1.0 2012-7-30
 * @see BaseVO
 */
@SuppressWarnings("serial")
public class SoftwareUpgradeTargetVO extends BaseVO {

	private String suTargetId;//软件升级目标ID
	private String softwareId;//软件ID
	private HousingDistrictInfoVo housingDistrictInfo;//小区信息
	private DeviceTypeVO deviceType;//设备类型
	
	
	public String getSuTargetId() {
		return suTargetId;
	}
	public void setSuTargetId(String suTargetId) {
		this.suTargetId = suTargetId;
	}
	
	public String getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}
	
	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}
	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}
	
	public DeviceTypeVO getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceTypeVO deviceType) {
		this.deviceType = deviceType;
	}	
}
