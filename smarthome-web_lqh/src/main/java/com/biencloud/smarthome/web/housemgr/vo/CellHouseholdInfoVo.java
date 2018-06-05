package com.biencloud.smarthome.web.housemgr.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 房号VO
 * 
 * @author jsun
 * @since 1.0 2012-5-16
 */
public class CellHouseholdInfoVo extends BaseVO {
	private static final long serialVersionUID = -8938090866450719990L;

	private String id;//房号ID
	private String area;//面积(平方米)
	private Date checkInDate;//入住时间
	private String code;//房号编码(定长) 由用户输入的4位数字组成
	private String coordinate;//楼宇在小区平面图上的坐标位置(屏幕坐标)
	private Date createTime;//创建时间
	private String createUserId;//创建用户的登录帐号
	private String housingStatus;//房屋状态(0: 未入住, 1: 已入住)
	private String name;//房号名称
	private OwnerUserVO owner;//房屋的业主
	
	private BuildingCellInfoVo THmBuildingCellInfo;//房号所属的单元信息
	private CellSizeInfoVo THmCellSizeInfo;//房屋的户型
	private List<ChargeTypeVO> chargeTypes;//房号的收费类型

	public List<ChargeTypeVO> getChargeTypes() {
		return chargeTypes;
	}
	public void setChargeTypes(List<ChargeTypeVO> chargeTypes) {
		this.chargeTypes = chargeTypes;
	}
	public CellSizeInfoVo getTHmCellSizeInfo() {
		return THmCellSizeInfo;
	}
	public void setTHmCellSizeInfo(CellSizeInfoVo tHmCellSizeInfo) {
		THmCellSizeInfo = tHmCellSizeInfo;
	}
	public BuildingCellInfoVo getTHmBuildingCellInfo() {
		return THmBuildingCellInfo;
	}
	public void setTHmBuildingCellInfo(BuildingCellInfoVo tHmBuildingCellInfo) {
		THmBuildingCellInfo = tHmBuildingCellInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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
	public String getHousingStatus() {
		return housingStatus;
	}
	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OwnerUserVO getOwner() {
		return owner;
	}
	public void setOwner(OwnerUserVO owner) {
		this.owner = owner;
	}
}
