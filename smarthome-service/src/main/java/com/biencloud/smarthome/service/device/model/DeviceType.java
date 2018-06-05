package com.biencloud.smarthome.service.device.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 设备类型实体对象。
 * @author kouy
 * @since 1.0 2012-4-23
 */
@Entity
@Table(name="t_device_type")
public class DeviceType extends BaseEntity {

	private static final long serialVersionUID = 2454187600037168403L;

	@Id
	@Column(name="device_type")
	private String deviceType;	//设备类型

	@Column(name="device_name")
	private String deviceName;	//设备类型名称
	
	@Column(name="device_desc")
	private String deviceDesc;	//设备类型描述
	

    public DeviceType() {
    }

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceDesc() {
		return this.deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder(-531086097, 547647957)
				.append(this.deviceType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeviceType)) {
			return false;
		}
		DeviceType dt = (DeviceType) object;
		return new EqualsBuilder()
				.append(this.deviceType, dt.deviceType)
				.isEquals();
	}	
}