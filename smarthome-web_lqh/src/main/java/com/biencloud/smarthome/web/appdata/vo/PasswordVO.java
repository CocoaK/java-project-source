package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 类名称：PasswordVO 
 * 类描述： 密码实体VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:27:21
 */
@SuppressWarnings("serial")
public class PasswordVO extends BaseVO{
	
	private String districtNo;	//小区编号
	private String areaNo;	//区域编号
	private String buildingNo; //楼栋编号
	private String unitNo;	//单元编号
	private String roomNo;	//房号
	private String password;
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
