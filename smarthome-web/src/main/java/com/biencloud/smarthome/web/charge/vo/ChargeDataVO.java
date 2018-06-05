
package com.biencloud.smarthome.web.charge.vo;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeDataVO 
 * 类描述： 收费数据VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:36:10
 */
public class ChargeDataVO extends BaseVO {
	
	private static final long serialVersionUID = 7007943059652829948L;
	/** 判断是 */
	public static final Integer YES=1;
	/** 判断否 */
	public static final Integer NO=2;
	/**
	 * 判断从添加页面请求获取指定房间号的收费项目
	 */
	public static final String REQUEST_FORGETCHARGETYPE="getChargeType";
	/**
	 * 首次进入添加页面标识
	 */
	public static final String FIRSTGOTOPAGE="firstGotoPage";

    private RegionBuildingInfoVo regionBuildingInfo;//所属小区
    private CellHouseholdInfoVo cellHouseholdInfo;//房间
//    private List<ChargeDetailVO> chargeDetails;
    private List<ChargeTypeResultVO> chargeTypeResults;//收费项目结果集合
    private String chargeTime;//收费时间
    private Date createTime;//创建时间
    private Long id;
    private String isproductDetail;//是否已产生收费清单
    private String monetaryUnit;//货币单位
    private String ownerName;//业主姓名
    private PaUserVO paUser;//物管用户
    private String remark;//描述
    private HousingDistrictInfoVo housingDistrictInfo;//所属小区
    private String totalMoney;//总金额
    private Date createStartTime;//创建开始时间
	private Date createEndTime;//创建结束时间

    
    public List<ChargeTypeResultVO> getChargeTypeResultVOs() {
		return chargeTypeResults;
	}

	public void setChargeTypeResultVOs(List<ChargeTypeResultVO> chargeTypeResults) {
		this.chargeTypeResults = chargeTypeResults;
	}


    public RegionBuildingInfoVo getRegionBuildingInfo() {
		return regionBuildingInfo;
	}

	public void setRegionBuildingInfo(RegionBuildingInfoVo regionBuildingInfo) {
		this.regionBuildingInfo = regionBuildingInfo;
	}
	
	public Date getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	public Date getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	public CellHouseholdInfoVo getCellHouseholdInfo() {
		return cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfoVo cellHouseholdInfo) {
		this.cellHouseholdInfo = cellHouseholdInfo;
	}

	public List<ChargeTypeResultVO> getChargeTypeResults() {
		return chargeTypeResults;
	}

	public void setChargeTypeResults(List<ChargeTypeResultVO> chargeTypeResults) {
		this.chargeTypeResults = chargeTypeResults;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsproductDetail() {
		return isproductDetail;
	}

	public void setIsproductDetail(String isproductDetail) {
		this.isproductDetail = isproductDetail;
	}

	public String getMonetaryUnit() {
		return monetaryUnit;
	}

	public void setMonetaryUnit(String monetaryUnit) {
		this.monetaryUnit = monetaryUnit;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public PaUserVO getPaUser() {
		return paUser;
	}

	public void setPaUser(PaUserVO paUser) {
		this.paUser = paUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}
	


	
}
