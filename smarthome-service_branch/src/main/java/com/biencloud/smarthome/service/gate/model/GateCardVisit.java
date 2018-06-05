package com.biencloud.smarthome.service.gate.model;

import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;


/**
 * 门卡刷卡记录实体对象。
 * @author kouy
 * @since 1.0 2012-5-4
 */
@Entity
@Table(name="t_gate_card_visit")
public class GateCardVisit extends BaseEntity {
	
	private static final long serialVersionUID = 3420602608572028739L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="visit_id")
	private String visitId;//门卡刷卡记录ID

	@Column(name="district_id")
	private String districtId;//小区ID
	
	@Column(name="device_id")
	private String deviceId;//设备ID
	
	@Column(name="device_code")
	private String deviceCode;//设备代码
	
	@Column(name="device_alias")
	private String deviceAlias;//设备别名

	@Column(name="gate_card_id")
	private String gateCardId;//门卡ID

	@Column(name="card_no")
	private String cardNo;//门卡号
	
	@Column(name="owner_id_card")
	private String ownerIdCard;//卡主身份证

	@Column(name="owner_name")
	private String ownerName;//卡主姓名

	@Column(name="pic_path1")
	private String picPath1;//刷卡抓拍图片1的链接地址

	@Column(name="pic_path2")
	private String picPath2;//刷卡抓拍图片2的链接地址

	@Column(name="pic_path3")
	private String picPath3;//刷卡抓拍图片3的链接地址

	@Column(name="visit_time")
	private Date visitTime;//刷卡时间

	@Transient
	private Date beginTime;//起始时间，刷卡时间查询条件
	@Transient
	private Date endTime;//结束时间，刷卡时间查询条件
	
    public GateCardVisit() {
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
		return this.deviceId;
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

	public String getGateCardId() {
		return this.gateCardId;
	}

	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOwnerIdCard() {
		return this.ownerIdCard;
	}

	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPicPath1() {
		return this.picPath1;
	}

	public void setPicPath1(String picPath1) {
		this.picPath1 = picPath1;
	}

	public String getPicPath2() {
		return this.picPath2;
	}

	public void setPicPath2(String picPath2) {
		this.picPath2 = picPath2;
	}

	public String getPicPath3() {
		return this.picPath3;
	}

	public void setPicPath3(String picPath3) {
		this.picPath3 = picPath3;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
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
		return new HashCodeBuilder(-41269137, -1462506879)				
				.append(this.visitId).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GateCardVisit)) {
			return false;
		}
		GateCardVisit gcv = (GateCardVisit) object;
		return new EqualsBuilder()
				.append(this.visitId, gcv.visitId)
				.isEquals();
	}	
}