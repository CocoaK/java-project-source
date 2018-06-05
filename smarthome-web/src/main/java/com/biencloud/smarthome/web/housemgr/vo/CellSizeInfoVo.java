package com.biencloud.smarthome.web.housemgr.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 户型VO
 * 一个单元包含多个户型, 多个房号对应一个户型
 * 
 * @author jsun  
 * @since 1.0 2012-5-24
 */
public class CellSizeInfoVo extends BaseVO {
	private static final long serialVersionUID = -8587576322864663163L;

	private String id;//户型ID
	private String customFlag;//自定义户型标志
	private Date createTime;//创建时间
	private String createUserId;//创建用户的登录帐号
	private String plan;//户型图(只保存文件存放的路径)
	private String hall;//有多少厅
	private String room;//有多少室

	private BuildingCellInfoVo THmBuildingCellInfo;//户型所属的单元
	
	private List<RoomVo> rooms;//户型的房间信息

	public List<RoomVo> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomVo> rooms) {
		this.rooms = rooms;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCustomFlag() {
		return customFlag;
	}
	public void setCustomFlag(String customFlag) {
		this.customFlag = customFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public BuildingCellInfoVo getTHmBuildingCellInfo() {
		return THmBuildingCellInfo;
	}
	public void setTHmBuildingCellInfo(BuildingCellInfoVo tHmBuildingCellInfo) {
		THmBuildingCellInfo = tHmBuildingCellInfo;
	}
}
