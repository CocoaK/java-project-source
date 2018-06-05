package com.biencloud.smarthome.web.deviceno.vo;

import com.biencloud.smarthome.web.device.vo.DeviceVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：DeviceNoVo 
 * 类描述： 设置编号VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午12:00:01
 */
public class DeviceNoVo {

	private String areaId;
	private String deviceNo;
	private DeviceVO device;
	private Long id;
	private String remark;
	private String deviceName;
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceVO getDevice() {
		return device;
	}
	public void setDevice(DeviceVO device) {
		this.device = device;
	}

}
