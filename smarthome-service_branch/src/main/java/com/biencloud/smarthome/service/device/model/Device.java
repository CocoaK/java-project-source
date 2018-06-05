package com.biencloud.smarthome.service.device.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;

/**
 * 
 * 类名称：Device 类描述： 设备实体对象
 * 
 * @author: ykou
 * @version: 0.1
 * @date: 2012-5-30 下午3:17:57
 */
@Entity
@Table(name = "t_device")
public class Device extends BaseEntity {

	private static final long serialVersionUID = -6558411525652831913L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "device_id")
	private String deviceId;	//ID

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	private HousingDistrictRegionInfo housingDistrictRegionInfo; // 小区区域

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id")
	private RegionBuildingInfo regionBuildingInfo; // 楼宇

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id")
	private CellHouseholdInfo cellHouseholdInfo; // 房号

	@Column(name = "created_time", updatable = false)
	private Date createdTime;	//注册时间

	@Column(name = "created_user", updatable = false)
	private String createdUser;

	@Column(name = "device_alias")
	private String deviceAlias;	//设备别名

	@Column(name = "device_code", updatable = false)
	private String deviceCode;	//设备编号

	@Column(name = "device_name")
	private String deviceName;	//设备名称

	@Column(name = "device_pwd")
	private String devicePwd;	//设备密码

	@Column(name = "device_status")
	private String deviceStatus;	//设备状态

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "device_type", updatable = false)
	private DeviceType deviceType;	//设备类型

	@Column(name = "district_gate_flag", updatable = false)
	private String districtGateFlag;	//小区大门标识

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id", updatable = false)
	private HousingDistrictInfo housingDistrictInfo; // 小区

	@Column(name = "device_mac", updatable = false)
	private String deviceMac;	//设备mac地址

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id")
	private BuildingCellInfo buildingCellInfo; // 单元

	@Column(name = "updated_time")
	private Date updatedTime;	//登录时间

	@Column(name = "updated_user")
	private String updatedUser;

	@Column(name = "device_ip")
	private String deviceIp;	//设备IP

	@Column(name = "login_userid")
	private String loginUuserId;	//注册用户ID

	@Column(name = "login_status")
	private String loginStatus; // 登录状态，1已登录，0未登录

	@Column(name = "devicebg_url")
	private String deviceBgUrl;	//设备背景

	@Column(name = "devicering_url")
	private String deviceRingUrl;	//设备铃声

	@Column(name = "position")
	private String position;	//位置号
	
	@Column(name = "coordinate")
	private String coordinate;	//坐标
	
	@Column(name = "version")
	private String version;	//版本
	
	@Transient
	private List<String> deviceTypeList;
	@Transient
	private boolean areaIsNull;

	public Device() {
	}

	public Device(HousingDistrictRegionInfo housingDistrictRegionInfo, RegionBuildingInfo regionBuildingInfo,
			CellHouseholdInfo cellHouseholdInfo, Date createdTime, String deviceAlias, String deviceCode, String deviceName,
			String devicePwd, String deviceStatus, DeviceType deviceType, HousingDistrictInfo housingDistrictInfo, String deviceMac,
			BuildingCellInfo buildingCellInfo, Date updatedTime, String deviceIp, String position) {
		super();
		this.housingDistrictRegionInfo = housingDistrictRegionInfo;
		this.regionBuildingInfo = regionBuildingInfo;
		this.cellHouseholdInfo = cellHouseholdInfo;
		this.createdTime = createdTime;
		this.deviceAlias = deviceAlias;
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.devicePwd = devicePwd;
		this.deviceStatus = deviceStatus;
		this.deviceType = deviceType;
		this.housingDistrictInfo = housingDistrictInfo;
		this.deviceMac = deviceMac;
		this.buildingCellInfo = buildingCellInfo;
		this.updatedTime = updatedTime;
		this.deviceIp = deviceIp;
		this.position = position;
	}
	
	public boolean isAreaIsNull() {
		return areaIsNull;
	}

	public void setAreaIsNull(boolean areaIsNull) {
		this.areaIsNull = areaIsNull;
	}

	public List<String> getDeviceTypeList() {
		return deviceTypeList;
	}

	public void setDeviceTypeList(List<String> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public HousingDistrictRegionInfo getHousingDistrictRegionInfo() {
		return housingDistrictRegionInfo;
	}

	public void setHousingDistrictRegionInfo(HousingDistrictRegionInfo housingDistrictRegionInfo) {
		this.housingDistrictRegionInfo = housingDistrictRegionInfo;
	}

	public RegionBuildingInfo getRegionBuildingInfo() {
		return regionBuildingInfo;
	}

	public void setRegionBuildingInfo(RegionBuildingInfo regionBuildingInfo) {
		this.regionBuildingInfo = regionBuildingInfo;
	}

	public CellHouseholdInfo getCellHouseholdInfo() {
		return cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfo cellHouseholdInfo) {
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

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getDistrictGateFlag() {
		return districtGateFlag;
	}

	public void setDistrictGateFlag(String districtGateFlag) {
		this.districtGateFlag = districtGateFlag;
	}

	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public BuildingCellInfo getBuildingCellInfo() {
		return buildingCellInfo;
	}

	public void setBuildingCellInfo(BuildingCellInfo buildingCellInfo) {
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
		return new HashCodeBuilder(-1959941635, 1952620985).append(this.deviceId).toHashCode();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Device)) {
			return false;
		}
		Device device = (Device) object;
		return new EqualsBuilder().append(this.deviceId, device.deviceId).isEquals();
	}
}