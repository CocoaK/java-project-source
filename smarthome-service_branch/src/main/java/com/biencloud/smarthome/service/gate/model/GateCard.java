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
 * @author kouy
 * @since 1.0 2012-5-4
 */
@SuppressWarnings("unused")
@Entity
@Table(name="t_gate_card")
public class GateCard extends BaseEntity {
	
	private static final long serialVersionUID = -7807446872411851886L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gate_card_id")
	private String gateCardId;//门卡ID

	@Column(name="card_no")
	private String cardNo;//门卡号

	@Column(name="created_time", updatable=false)
	private Date createdTime;//创建时间

	@Column(name="created_user", updatable=false)
	private String createdUser;//创建用户的登录帐号

	@Column(name="district_id", updatable=false)
	private String districtId;//小区ID

	@Column(name="owner_id_card")
	private String ownerIdCard;//卡主身份证

	@Column(name="owner_name")
	private String ownerName;//卡主姓名
	
	@Column(name="house_id")
	private String houseId;//房号
	
	private String status;//状态，参见 Constants.GATE_CARD_ENABLED、Constants.GATE_CARD_DISABLED

	@Column(name="updated_time", insertable=false)
	private Date updatedTime;//修改时间

	@Column(name="updated_user", insertable=false)
	private String updatedUser;//修改用户的登录帐号

	@OneToMany(cascade={CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE},fetch=FetchType.EAGER,mappedBy="gateCardId",orphanRemoval=true)
	private List<GatePermissions> gatePermissions;//门禁权限列表
	
	@Transient
	private String roomNo;	//完整房号
	@Transient
	private String roomName;	//完整房号名称

    public GateCard() {
    }

	public String getGateCardId() {
		return this.gateCardId;
	}

	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public List<GatePermissions> getGatePermissions() {
		return this.gatePermissions;
	}

	public void setGatePermissions(List<GatePermissions> gatePermissions) {
		this.gatePermissions = gatePermissions;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(782565877, -227094017)
				.append(this.cardNo).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GateCard)) {
			return false;
		}
		GateCard gc = (GateCard) object;
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