package com.biencloud.smarthome.web.devicetype.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 设备类型值对象。
 * @author kouy
 * @since 1.0 2012-5-2
 */
public class DeviceTypeVO extends BaseVO {

	private static final long serialVersionUID = 8857663270114197076L;
	//设备类型
	private String deviceType;
	//设备类型名称
	private String deviceName;
	//设备类型描述
	private String deviceDesc;
	
	
	public String getDeviceType() {
		return deviceType;
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
		return deviceDesc;
	}
	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1583275643, -24816349)
				.append(this.deviceType)
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeviceTypeVO)) {
			return false;
		}
		DeviceTypeVO dt = (DeviceTypeVO) object;
		return new EqualsBuilder()
				.append(this.deviceType, dt.deviceType)
				.isEquals();
	}
}
