package com.smarthome.socket.service.business.adapater;

import com.smarthome.socket.service.business.adapater.json.ReqJson;


/**
 * 
 * 类名称：RegistObj 类描述：注册对象
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-4 下午9:31:32
 */
public class RegistReq extends ReqJson{
	// 登陆帐号
	private String userAccount;
	// 密码
	private String userPassword;	
	// 设备ip
	private String deviceIp;
	// 设备mac地址
	private String deviceMac;
	// 设备类型
	private String deviceType;
	// 设备名称
	private String deviceName;
	// 设备密码
	private String devicePassword;
    //软件代码
	private String softwareCode;
	//软件版本
	private String softwareVersion;
	//小区编号
	private String estateNo;
	//区域号
	private String areaNo;
	//栋号
	private String ridgepole;
	//单元号
    private String unitNo;
    //房号
    private String houseNo;
    //ip强制切换
    private String ipState;
    //房号强制切换
    private String houseState;
    //固件版本
    private String version;
    //位置号
    private String position;
    //SIP账号
    private String sipid;
	
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
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
	public String getDevicePassword() {
		return devicePassword;
	}
	public void setDevicePassword(String devicePassword) {
		this.devicePassword = devicePassword;
	}
	public String getSoftwareCode() {
		return softwareCode;
	}
	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getEstateNo() {
		return estateNo;
	}
	public void setEstateNo(String estateNo) {
		this.estateNo = estateNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getRidgepole() {
		return ridgepole;
	}
	public void setRidgepole(String ridgepole) {
		this.ridgepole = ridgepole;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSipid() {
		return sipid;
	}
	public void setSipid(String sipid) {
		this.sipid = sipid;
	}
	
}
