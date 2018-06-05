package com.biencloud.smarthome.service.gate.model;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.common.constants.Constants;

import java.util.Date;
import java.util.List;


/**
 * 门卡实体对象。
 */
@SuppressWarnings("unused")
public class GateCardVo extends BaseEntity {
	
	private static final long serialVersionUID = -7807446872411851886L;

	private String gateCardId;//门卡ID

	private String cardNo;//门卡号

	private String ownerIdCard;//卡主身份证

	private String ownerName;//卡主姓名
	
	private String houseId;//房号

	private String roomNo;	//完整房号

	private String roomName;	//完整房号名称
	
	private String deviceNo;	//设备编号

    public GateCardVo() {
    }

	
	public String getGateCardId() {
		return gateCardId;
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
		return ownerIdCard;
	}


	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getDeviceNo() {
		return deviceNo;
	}


	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder(782565877, -227094017)
				.append(this.cardNo).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GateCardVo)) {
			return false;
		}
		GateCardVo gc = (GateCardVo) object;
		return new EqualsBuilder()
				.append(this.cardNo, gc.cardNo).isEquals();
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}