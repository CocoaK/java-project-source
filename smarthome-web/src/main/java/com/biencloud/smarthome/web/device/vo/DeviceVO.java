package com.biencloud.smarthome.web.device.vo;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;

/**
 * 类名称：DeviceVO 
 * 类描述： 设备实体VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:24:53
 */
public class DeviceVO extends BaseVO {

	private static final long serialVersionUID = -3623842551920161012L;
	//设备ID
	private String deviceId;
	//小区
	private HousingDistrictInfoVo housingDistrictInfo;
	//小区区域
	private HousingDistrictRegionInfoVo housingDistrictRegionInfo;
	//楼宇
	private RegionBuildingInfoVo regionBuildingInfo;
	//单元
	private BuildingCellInfoVo buildingCellInfo;
	//房号
	private CellHouseholdInfoVo cellHouseholdInfo;
	//注册时间
	private Date createdTime;
	//注册用户
	private String createdUser;
	//设备别名
	private String deviceAlias;
	//设备编号
	private String deviceCode;
	//设备名称
	private String deviceName;
	//设备密码
	private String devicePwd;
	//设备状态
	private String deviceStatus;
	//设备类型
	private DeviceTypeVO deviceType;
	//小区大门标志
	private String districtGateFlag;
	//设备mac地址
	private String deviceMac;
	//最后登录时间
	private Date updatedTime;
	//修改人
	private String updatedUser;
	//设备Ip
	private String deviceIp;
	//登录用户ID
	private String loginUuserId;
	//登录状态
	private String loginStatus;
	//设备背景URL
	private String deviceBgUrl;
	//设备铃声URL
	private String deviceRingUrl;
	//位置
	private String position;
	//坐标
	private String coordinate;
	//SIP账号
	private String sipid;

    public DeviceVO() {
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public HousingDistrictRegionInfoVo getHousingDistrictRegionInfo() {
		return housingDistrictRegionInfo;
	}

	public void setHousingDistrictRegionInfo(HousingDistrictRegionInfoVo housingDistrictRegionInfo) {
		this.housingDistrictRegionInfo = housingDistrictRegionInfo;
	}

	public RegionBuildingInfoVo getRegionBuildingInfo() {
		return regionBuildingInfo;
	}

	public void setRegionBuildingInfo(RegionBuildingInfoVo regionBuildingInfo) {
		this.regionBuildingInfo = regionBuildingInfo;
	}

	public CellHouseholdInfoVo getCellHouseholdInfo() {
		return cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfoVo cellHouseholdInfo) {
		this.cellHouseholdInfo = cellHouseholdInfo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getDeviceAlias() {
		return deviceAlias;
	}

	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDevicePwd() {
		return devicePwd;
	}

	public void setDevicePwd(String devicePwd) {
		this.devicePwd = devicePwd;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public DeviceTypeVO getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceTypeVO deviceType) {
		this.deviceType = deviceType;
	}

	public String getDistrictGateFlag() {
		return districtGateFlag;
	}

	public void setDistrictGateFlag(String districtGateFlag) {
		this.districtGateFlag = districtGateFlag;
	}

	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public BuildingCellInfoVo getBuildingCellInfo() {
		return buildingCellInfo;
	}

	public void setBuildingCellInfo(BuildingCellInfoVo buildingCellInfo) {
		this.buildingCellInfo = buildingCellInfo;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getLoginUuserId() {
		return loginUuserId;
	}

	public void setLoginUuserId(String loginUuserId) {
		this.loginUuserId = loginUuserId;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getDeviceBgUrl() {
		return deviceBgUrl;
	}

	public void setDeviceBgUrl(String deviceBgUrl) {
		this.deviceBgUrl = deviceBgUrl;
	}

	public String getDeviceRingUrl() {
		return deviceRingUrl;
	}

	public void setDeviceRingUrl(String deviceRingUrl) {
		this.deviceRingUrl = deviceRingUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1959941635, 1952620985)
				.append(this.deviceCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeviceVO)) {
			return false;
		}
		DeviceVO device = (DeviceVO) object;
		return new EqualsBuilder()
				.append(this.deviceCode, device.deviceCode).isEquals();
	}

	public String getSipid() {
		return sipid;
	}

	public void setSipid(String sipid) {
		this.sipid = sipid;
	}
}