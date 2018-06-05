package com.biencloud.smarthome.service.app.vo;

/**
 * 
 * 类名称：AppVO 类描述： app vo类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-12 下午8:18:27
 */
public class AppLoginVO {
	// 设备编号
	private String deviceNo;
	// 小区编号
	private String areaNo;
	// 区域编号
	private String regionNo;
	// 楼宇编号
	private String buildingNo;
	// 单元编号
	private String unitNo;
	// 房屋编号
	private String houseNo;
	// mac地址
	private String mac;
	// ip
	private String ip;
	// 设备名称
	private String deviceName;
	// 设备类型
	private String deviceType;
	// 设备密码
	private String devicePassword;
	// 位置
	private String position;
	//固件版本
	private String version;

	// ip强制与设备解除绑定关系,如果ipState为1，表示使用该ip登录，你切断以前那个相同的ip,0表示不切断
	private String ipState;
	// 如果房号下存在设备,如果houseState为1，表示使用该房号登录，你切断以前那个相同的房号设备,0表示不切断
	private String houseState;
	//sip账号
	private String sipId;
	//sip密码
	private String sipPwd;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getRegionNo() {
		return regionNo;
	}

	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDevicePassword() {
		return devicePassword;
	}

	public void setDevicePassword(String devicePassword) {
		this.devicePassword = devicePassword;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIpState() {
		return ipState;
	}

	public void setIpState(String ipState) {
		this.ipState = ipState;
	}

	public String getHouseState() {
		return houseState;
	}

	public void setHouseState(String houseState) {
		this.houseState = houseState;
	}

	public String getSipId() {
		return sipId;
	}

	public void setSipId(String sipId) {
		this.sipId = sipId;
	}

	public String getSipPwd() {
		return sipPwd;
	}

	public void setSipPwd(String sipPwd) {
		this.sipPwd = sipPwd;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
