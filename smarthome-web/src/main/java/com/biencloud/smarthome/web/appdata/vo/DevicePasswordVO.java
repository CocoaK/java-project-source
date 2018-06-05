package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;

/**
 * 类名称：DevicePasswordVO 
 * 类描述： 设备开锁密码VO类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-8-3 下午5:10:39
 */
public class DevicePasswordVO extends BaseVO{

	private static final long serialVersionUID = 7602856368154824823L;
	//ID
	private Long id;
	//密码
	private String password;
	//本设备
	private DeviceVO deviceVO;
	//目标设备
	private DeviceVO targetDeviceVO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DeviceVO getDeviceVO() {
		return deviceVO;
	}
	public void setDeviceVO(DeviceVO deviceVO) {
		this.deviceVO = deviceVO;
	}
	public DeviceVO getTargetDeviceVO() {
		return targetDeviceVO;
	}
	public void setTargetDeviceVO(DeviceVO targetDeviceVO) {
		this.targetDeviceVO = targetDeviceVO;
	}

}
