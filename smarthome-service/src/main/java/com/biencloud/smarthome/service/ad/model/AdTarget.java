package com.biencloud.smarthome.service.ad.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.device.model.Device;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 广告投放目标实体类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseEntity
 */
@Entity
@Table(name="t_ad_target")
public class AdTarget extends BaseEntity {

	private static final long serialVersionUID = -3489639545230077976L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ad_target_id")
	private String adTargetId;//广告投放目标ID

	@ManyToOne
	@JoinColumn(name="device_id",updatable=false)
	private Device device;//设备信息
	
	@Column(name="ad_id")
	private String adId;//广告ID
	
    public AdTarget() {
    }

	public String getAdTargetId() {
		return this.adTargetId;
	}

	public void setAdTargetId(String adTargetId) {
		this.adTargetId = adTargetId;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1338519589, 1873431437)
				.append(this.adTargetId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdTarget)) {
			return false;
		}
		AdTarget at = (AdTarget) object;
		return new EqualsBuilder()
				.append(this.adTargetId, at.adTargetId)
				.isEquals();
	}
}