package com.biencloud.smarthome.service.gate.model;

import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.device.model.Device;


/**
 * 门禁权限实体对象。
 * @author kouy
 * @since 1.0 2012-5-5
 */
@Entity
@Table(name="t_gate_permissions")
public class GatePermissions extends BaseEntity {
	
	private static final long serialVersionUID = 959075559855937377L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gate_permissions_id")
	private String gatePermissionsId;//门禁权限ID

	@Column(name="begin_time")
	private Date beginTime;//允许刷卡起始时间，为空不做限制

	@Column(name="end_time")
	private Date endTime;//允许刷卡结束时间，为空不做限制

	@OneToOne
	@JoinColumn(name="device_id")
	private Device device;//设备信息
	
	@Column(name="gate_card_id")
	private String gateCardId;//门卡ID

    public GatePermissions() {
    }

	public String getGatePermissionsId() {
		return this.gatePermissionsId;
	}

	public void setGatePermissionsId(String gatePermissionsId) {
		this.gatePermissionsId = gatePermissionsId;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	public String getGateCardId() {
		return gateCardId;
	}

	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1634005313, -1319936771)
				.append(this.gatePermissionsId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GatePermissions)) {
			return false;
		}
		GatePermissions gp = (GatePermissions) object;
		return new EqualsBuilder()
				.append(this.gatePermissionsId, gp.gatePermissionsId)
				.isEquals();
	}	
}