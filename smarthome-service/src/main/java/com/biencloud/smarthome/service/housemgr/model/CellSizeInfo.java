package com.biencloud.smarthome.service.housemgr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * 户型 实体类
 * 一个单元包含多个户型, 多个房号对应一个户型
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_cell_size_info")
public class CellSizeInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7643759515328500575L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 户型ID
	 */
	private String id;
	
	/**
	 * 自定义户型标志
	 */
	@Column(name="CUSTOM_FLAG")
	private String customFlag;
	
	/**
	 * 有多少厅
	 */
	private String hall;
	/**
	 * 有多少室
	 */
	private String room;
	/**
	 * 户型图(只保存文件存放的路径)
	 */
	private String plan;

	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME")
	private Date createTime;
	/**
	 * 创建用户的登录帐号
	 */
	@Column(name="CREATE_USER_ID")
	private String createUserId;

	/**
	 * 属于这个户型的房号
	 */
	@Transient
	private List<CellHouseholdInfo> THmCellHouseholdInfos;

	/**
	 * 户型所属的单元
	 */
    @ManyToOne
	@JoinColumn(name="CELL_ID")
	private BuildingCellInfo THmBuildingCellInfo;

	/**
	 * 户型的房间信息
	 */
    @OneToMany(mappedBy = "sizeId",fetch=FetchType.EAGER, cascade={CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval=true)
    private Set<Room> rooms;

	public String getId() {
		return this.id;
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
	
	/**
	 * 用户在系统中创建户型的时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建户型的用户ID
	 * 
	 * @return
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 有多少厅
	 * 
	 * @return
	 */
	public String getHall() {
		return this.hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}

	/**
	 * 户型图(只保存文件存放的路径)
	 * 
	 * @return
	 */
	public String getPlan() {
		return this.plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * 有多少室
	 * 
	 * @return
	 */
	public String getRoom() {
		return this.room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * 属于这个户型的房号
	 * 
	 * @return
	 */
	public List<CellHouseholdInfo> getTHmCellHouseholdInfos() {
		return this.THmCellHouseholdInfos;
	}
	public void setTHmCellHouseholdInfos(List<CellHouseholdInfo> THmCellHouseholdInfos) {
		this.THmCellHouseholdInfos = THmCellHouseholdInfos;
	}

	/**
	 * 户型所属的单元
	 * 
	 * @return
	 */
	public BuildingCellInfo getTHmBuildingCellInfo() {
		return this.THmBuildingCellInfo;
	}
	public void setTHmBuildingCellInfo(BuildingCellInfo THmBuildingCellInfo) {
		this.THmBuildingCellInfo = THmBuildingCellInfo;
	}

	/**
	 * 户型的房间信息
	 * 
	 * @return
	 */
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
}