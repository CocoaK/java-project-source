package com.biencloud.smarthome.service.info.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbInformationReceiverDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_information_receiver_device")
public class InfoReceiverDevice implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4759117596293392013L;
	private Long id;
	private InfoSend infoSend;//信息发布对象
	private String deviceTypeId;//设备类型ID
	private String status;//状态
	private String cellIds;//单元ID
	private String builldingIds;//栋ID
	private String areaIds;//区域ID
	private String districtIds;//小区ID

	// Constructors

	/** default constructor */
	public InfoReceiverDevice() {
	}

	/** full constructor */
	public InfoReceiverDevice(InfoSend infoSend, String deviceTypeId, String status, String cellIds, String builldingIds, String areaIds, String districtIds) {
		this.infoSend = infoSend;
		this.deviceTypeId = deviceTypeId;
		this.status = status;
		this.cellIds = cellIds;
		this.builldingIds = builldingIds;
		this.areaIds = areaIds;
		this.districtIds = districtIds;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_id")
	public InfoSend getInfoSend() {
		return this.infoSend;
	}

	public void setInfoSend(InfoSend infoSend) {
		this.infoSend = infoSend;
	}

	@Column(name = "device_type_id", length = 3)
	public String getDeviceTypeId() {
		return this.deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	@Column(name = "status", length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "cell_ids", length = 500)
	public String getCellIds() {
		return this.cellIds;
	}

	public void setCellIds(String cellIds) {
		this.cellIds = cellIds;
	}

	@Column(name = "buillding_ids", length = 500)
	public String getBuilldingIds() {
		return this.builldingIds;
	}

	public void setBuilldingIds(String builldingIds) {
		this.builldingIds = builldingIds;
	}

	@Column(name = "area_ids", length = 500)
	public String getAreaIds() {
		return this.areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	@Column(name = "district_ids", length = 500)
	public String getDistrictIds() {
		return this.districtIds;
	}

	public void setDistrictIds(String districtIds) {
		this.districtIds = districtIds;
	}

}