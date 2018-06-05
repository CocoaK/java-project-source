package com.biencloud.smarthome.service.gate.model;

import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 访客身份证刷卡记录实体对象。
 * @author kouy
 * @since 1.0 2012-5-4
 */
@Entity
@Table(name="t_id_card_visit")
public class IdCardVisit extends com.biencloud.smarthome.service.base.model.BaseEntity {
	
	private static final long serialVersionUID = 5156965258692604134L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="visit_id")
	private String visitId;//身份证刷卡记录ID

	@Column(name="district_id")
	private String districtId;//小区ID
	
	@Column(name="device_id")
	private String deviceId;//设备ID
	
	@Column(name="device_code")
	private String deviceCode;//设备代码
	
	@Column(name="device_alias")
	private String deviceAlias;//设备别名
	
	private String gender;//来访人性别

	@Column(name="id_card")
	private String idCard;//来访人身份证

	@Column(name="visit_time")
	private Date visitTime;//刷卡时间

	@Column(name="visitor_address")
	private String visitorAddress;//来访人住址

	@Column(name="visitor_name")
	private String visitorName;//来访人姓名

	@Column(name="reason")
	private String reason;//来访目的
	
	@Transient
	private Date beginTime;//起始时间，刷卡时间查询条件
	@Transient
	private Date endTime;//结束时间，刷卡时间查询条件

    public IdCardVisit() {
    }

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceAlias() {
		return deviceAlias;
	}

	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitorAddress() {
		return this.visitorAddress;
	}

	public void setVisitorAddress(String visitorAddress) {
		this.visitorAddress = visitorAddress;
	}

	public String getVisitorName() {
		return this.visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-756475807, -1434740749)
				.append(this.visitId).toHashCode();
	}
    
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof IdCardVisit)) {
			return false;
		}
		IdCardVisit icv = (IdCardVisit) object;
		return new EqualsBuilder()				
				.append(this.visitId, icv.visitId)
				.isEquals();
	}
}