package com.biencloud.smarthome.service.device.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

/**
 * 设备IP实体对象。
 * @author Cocoa
 * @since 1.0 2012-5-4
 */
@Entity
@Table(name="t_device_ip")
public class DeviceIp extends BaseEntity {

	private static final long serialVersionUID = 6423126110478630196L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ip_id")
	private String ipId;	//ip编号

	@Column(name="ip_address")
	private String ipAddress;	//ip地址
	
	@Column(name="subnet")
	private String subnet;		//网段
	
	@Column(name="status")
	private String status;		//状态，是否被分配，1:被分配，0:未分配
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="district_id", updatable=false)
	private HousingDistrictInfo housingDistrictInfo;	//小区
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="device_id")
	private Device device;	//设备编号

	public String getIpId() {
		return ipId;
	}

	public void setIpId(String ipId) {
		this.ipId = ipId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
