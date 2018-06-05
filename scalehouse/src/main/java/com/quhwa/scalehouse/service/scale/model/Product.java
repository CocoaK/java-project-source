/**
 * 
 */
package com.quhwa.scalehouse.service.scale.model;

/** 
 * @Title:        Product 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-23 上午9:14:28 
 */
public class Product {
	private String deviceType;
	
	private String deviceName;
	
//	private boolean selected;
//
//	public boolean isSelected() {
//		return selected;
//	}
//
//	public void setSelected(boolean selected) {
//		this.selected = selected;
//	}

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

	@Override
	public String toString() {
		return "Product [deviceType=" + deviceType + ", deviceName="
				+ deviceName + "]";
	}
	
	
}
