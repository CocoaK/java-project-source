package com.biencloud.smarthome.service.softwareupgrade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 软件升级目标实体对象。
 * @author kouy
 * @since 1.0 2012-7-27
 * @see BaseEntity
 */
@Entity
@Table(name="t_software_upgrade_target")
public class SoftwareUpgradeTarget extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="su_target_id")
	private String suTargetId;//软件升级目标ID
	
	@Column(name="software_id")
	private String softwareId;//软件ID
	
	@OneToOne
	@JoinColumn(name="district_id", updatable=false)
	private HousingDistrictInfo housingDistrictInfo;//小区信息
	
	@OneToOne
	@JoinColumn(name="device_type", updatable=false)
	private DeviceType deviceType;//设备类型

	
	public String getSuTargetId() {
		return suTargetId;
	}

	public void setSuTargetId(String suTargetId) {
		this.suTargetId = suTargetId;
	}

	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public HousingDistrictInfo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-459625363, -517221933)
				.append(this.suTargetId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SoftwareUpgradeTarget)) {
			return false;
		}
		SoftwareUpgradeTarget sut = (SoftwareUpgradeTarget) object;
		return new EqualsBuilder()
				.append(this.suTargetId, sut.suTargetId)
				.isEquals();
	}	
}
