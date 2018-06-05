package com.biencloud.smarthome.service.log.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * DiviceRegeditLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_divice_regedit_log")
public class DiviceRegeditLog extends BaseEntity {

	private static final long serialVersionUID = 3319571596052777657L;
	// Fields

	private Long id;
	/**注册时间*/
	private Date addTime;
	/**MAC地址*/
	private String macAddr;
	/**登录IP*/
	private String userIp;
	/**设备名称*/
	private String name;
	/**设备编号*/
	private String deviceNo;
	/**小区名称*/
	private String districtName;
	/**区域名称*/
	private String regionName;
	/**楼宇名称*/
	private String buildingName;
	/**单元各称*/
	private String cellName;
	/**房号*/
	private String hourseNo;
	/**位置*/
	private String location;
	/**网点编号*/
	private String siteNo;
	/**设备类型 */
	private String diveceType;
	/**事件动作 */
	private String eventAciton;
	/***/
	private Date addEndTime;
	private Date addStartTime;

	// Constructors

	/** default constructor */
	public DiviceRegeditLog() {
	}

	/** minimal constructor */
	public DiviceRegeditLog(Long id) {
		this.id = id;
	}

	/** full constructor */
	public DiviceRegeditLog(Long id, Timestamp addTime, String macAddr, String userIp, String name, String deviceNo, String districtName, String regionName, String buildingName, String cellName, String hourseNo, String location, String siteNo, String diveceType) {
		this.id = id;
		this.addTime = addTime;
		this.macAddr = macAddr;
		this.userIp = userIp;
		this.name = name;
		this.deviceNo = deviceNo;
		this.districtName = districtName;
		this.regionName = regionName;
		this.buildingName = buildingName;
		this.cellName = cellName;
		this.hourseNo = hourseNo;
		this.location = location;
		this.siteNo = siteNo;
		this.diveceType = diveceType;
	}

	public DiviceRegeditLog(Date addTime, String macAddr, String userIp, String name, String deviceNo, String districtName,
			String regionName, String buildingName, String cellName, String hourseNo, String location, String siteNo, String diveceType,String eventAciton) {
		super();
		this.addTime = addTime;
		this.macAddr = macAddr;
		this.userIp = userIp;
		this.name = name;
		this.deviceNo = deviceNo;
		this.districtName = districtName;
		this.regionName = regionName;
		this.buildingName = buildingName;
		this.cellName = cellName;
		this.hourseNo = hourseNo;
		this.location = location;
		this.siteNo = siteNo;
		this.diveceType = diveceType;
		this.eventAciton=eventAciton;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "add_time", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "mac_addr", length = 50)
	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Column(name = "user_ip", length = 20)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "device_no", length = 50)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Column(name = "district_name", length = 20)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "region_name", length = 20)
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "building_name", length = 20)
	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	@Column(name = "cell_name", length = 20)
	public String getCellName() {
		return this.cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	@Column(name = "hourse_no", length = 20)
	public String getHourseNo() {
		return this.hourseNo;
	}

	public void setHourseNo(String hourseNo) {
		this.hourseNo = hourseNo;
	}

	@Column(name = "location", length = 100)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "site_no", length = 100)
	public String getSiteNo() {
		return this.siteNo;
	}

	public void setSiteNo(String siteNo) {
		this.siteNo = siteNo;
	}

	@Column(name = "divece_type", length = 20)
	public String getDiveceType() {
		return this.diveceType;
	}

	public void setDiveceType(String diveceType) {
		this.diveceType = diveceType;
	}

	@Transient
	public Date getAddEndTime() {
		return addEndTime;
	}

	public void setAddEndTime(Date addEndTime) {
		this.addEndTime = addEndTime;
	}

	@Transient
	public Date getAddStartTime() {
		return addStartTime;
	}

	public void setAddStartTime(Date addStartTime) {
		this.addStartTime = addStartTime;
	}

	@Column(name = "event_aciton", length = 20)
	public String getEventAciton() {
		return eventAciton;
	}

	public void setEventAciton(String eventAciton) {
		this.eventAciton = eventAciton;
	}

}