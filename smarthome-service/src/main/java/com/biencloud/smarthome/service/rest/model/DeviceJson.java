package com.biencloud.smarthome.service.rest.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 
 * 类名称：Device 类描述： 设备实体对象
 * 
 * @author: ykou
 * @version: 0.1
 * @date: 2016-5-20 下午3:17:57
 */
public class DeviceJson extends BaseEntity {

	private static final long serialVersionUID = -6558411525652831913L;

	private String deviceId;	//ID
	private String deviceNo;	//设备编号
	private String deviceName;	//设备名称

	public DeviceJson() {
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeviceJson)) {
			return false;
		}
		DeviceJson device = (DeviceJson) object;
		return new EqualsBuilder().append(this.deviceId, device.deviceId).isEquals();
	}
}