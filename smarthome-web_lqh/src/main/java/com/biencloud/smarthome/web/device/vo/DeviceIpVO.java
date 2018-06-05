package com.biencloud.smarthome.web.device.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

/**
 * 类名称：DeviceIpVO 
 * 类描述： 设备IP实体VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:24:25
 */
public class DeviceIpVO extends BaseVO{
	private static final long serialVersionUID = 3641695939467189817L;
	//编号
	private String ipId;
	//ip地址
	private String ipAddress;
	//网段
	private String subnet;
	//状态
	private String status;
	//小区
	private HousingDistrictInfoVo housingDistrictInfo;
	//设备实体VO
	private DeviceVO device;

	public String getIpId() {
		return ipId;
	}

	public void setIpId(String ipId) {
		this.ipId = ipId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	public DeviceVO getDevice() {
		return device;
	}

	public void setDevice(DeviceVO device) {
		this.device = device;
	}

}
