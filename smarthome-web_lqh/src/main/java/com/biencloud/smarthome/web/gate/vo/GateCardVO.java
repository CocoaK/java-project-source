package com.biencloud.smarthome.web.gate.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 门卡信息值对象。
 * @author kouy
 * @since 1.0 2012-5-7
 */
public class GateCardVO extends BaseVO {

	private static final long serialVersionUID = -7765812644813829020L;
	
	private String gateCardId;//门卡ID
	private String cardNo;//门卡号	
	private String districtId;//小区ID
	private String ownerIdCard;//卡主身份证
	private String ownerName;//卡主姓名
	private String houseId;//房间编号
	private String status;//状态，"0"：启用、"1"：禁用
	private Date createdTime;//创建时间
	private String createdUser;//创建用户的登录帐号
	private Date updatedTime;//修改时间
	private String updatedUser;//修改用户的登录帐号
	private String roomNo;	//完整房号code
	private String roomName;	//完整房号名称
	
	private String deviceId;//所属设备编号
	private String deviceName;//所属设备编号

	private List<GatePermissionsVO> gatePermissions;//门禁权限列表

	
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

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {		
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public List<GatePermissionsVO> getGatePermissions() {
		return gatePermissions;
	}

	public void setGatePermissions(List<GatePermissionsVO> gatePermissions) {
		this.gatePermissions = gatePermissions;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
