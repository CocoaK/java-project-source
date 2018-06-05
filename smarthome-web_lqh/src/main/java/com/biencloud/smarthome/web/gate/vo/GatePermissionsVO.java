package com.biencloud.smarthome.web.gate.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;

/**
 * 门禁信息值对象。
 * @author kouy
 * @since 1.0 2012-5-7
 */
public class GatePermissionsVO extends BaseVO {

	private static final long serialVersionUID = 6024088576421025233L;
	
	private String gatePermissionsId;//门禁权限ID
	private Date beginTime;//允许刷卡起始时间，为空不做限制
	private Date endTime;//允许刷卡结束时间，为空不做限制

	private DeviceVO device;//设备信息
	
	private String gateCardId;//门卡ID

	
	public String getGatePermissionsId() {
		return gatePermissionsId;
	}

	public void setGatePermissionsId(String gatePermissionsId) {
		this.gatePermissionsId = gatePermissionsId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public DeviceVO getDevice() {
		return device;
	}

	public void setDevice(DeviceVO device) {
		this.device = device;
	}

	public String getGateCardId() {
		return gateCardId;
	}

	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}
}
