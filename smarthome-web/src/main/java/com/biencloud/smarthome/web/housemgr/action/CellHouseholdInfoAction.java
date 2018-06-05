package com.biencloud.smarthome.web.housemgr.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.charge.service.IChargeTypeService;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.service.ICellSizeInfoService;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeCountService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeCountVO;

/**
 * 房号Action
 * 
 * @author jsun
 * @since 1.0 2012-5-23
 */
@SuppressWarnings("serial")
public class CellHouseholdInfoAction extends HouseMgrBaseAction<CellHouseholdInfoVo> {
	
	private static final int PAGE_SIZE = 6;//查询房号每页记录数
	
	private int pageNum;//当前页
	private int totalPages;//总页数
	
	private String cellId;
	private BuildingCellInfoVo cell;

	/**
	 * 手动输入的多个楼宇名称
	 */
	private String[] houseCodes;
	/**
	 * 与房号对应的户型
	 */
	private String[] sizeIds;
	
	private boolean successFlag;
	private boolean fromRemove;
	
	private boolean unallowedFlag;
	
	/**
	 * 新增/修改房号时房号是否没有重复
	 */
	private boolean notRepeat;
	
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IBuildingCellInfoService buildingCellInfoService;
	private ICellSizeInfoService cellSizeInfoService;
	private IChargeTypeService chargeTypeService;
	
	private String cellName;
	private String housingStatus;
	private String houseName;
	private String ownerName;
	
	private String houseId;
	private String regionId;
	private String buildingId;
	private CellHouseholdInfoVo house;
	private Map<String, String> sizeMap;
	private String sizeId;
	private List<ChargeTypeVO> chargeTypes;
	private String[] selectedChargeTypeIds;

	private String regionName;
	private String buildingName;
	private List<CellHouseholdInfoVo> foundHouses;
	
	private IDeviceService deviceService;
	
	//二维码服务
	private IQrcodeCountService qrcodeCountService;
	//二维码VO
	private QrcodeCountVO qrcodeCountVO;
	
	/**
	 * 查询房号列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<CellHouseholdInfoVo> page = getPage();
		if(page == null)
			page = new PagingVO<CellHouseholdInfoVo>();

		RegionBuildingInfoVo building = new RegionBuildingInfoVo();
		HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();		
		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(getDistrictId());
		region.setId(regionId);
		region.setHousingDistrictInfo(district);
		building.setId(buildingId);
		building.setTHmHousingDistrictRegionInfo(region);
		
		BuildingCellInfoVo cell = new BuildingCellInfoVo();
		cell.setId(cellId);
		cell.setName(cellName);
		cell.setTHmRegionBuildingInfo(building);
	
		if(house == null)
			house = new CellHouseholdInfoVo();

		house.setTHmBuildingCellInfo(cell);

		PagingVO<CellHouseholdInfoVo> pagingVO = cellHouseholdInfoService.queryForPaging(
				house, page.getPageNum(), page.getPageSize());
		postSettingHouses(pagingVO.getResults());
		setPage(pagingVO);
		if(ActionUtils.judgContainsValue(getRequest(), "print")) return "print";
		else return SUCCESS;
	}
	
	/**
	 * 获取房号信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		house = cellHouseholdInfoService.get(houseId);

		if(house != null && house.getTHmCellSizeInfo() != null)
			sizeId = house.getTHmCellSizeInfo().getId();
		
		// 查询收费类型的条件(组织ID, 就是小区ID)
		ChargeTypeVO paramsOb = new ChargeTypeVO();
		HousingDistrictInfoVo hdVo=new HousingDistrictInfoVo();
		hdVo.setId(getDistrictId());
		paramsOb.setHousingDistrictInfo(hdVo);
		// 通过分页查询出所有小区对应的收费类型
		chargeTypes = chargeTypeService.queryChargeTypeVOForPaging(paramsOb, 1, Integer.MAX_VALUE).getResults();
		initSizeMap(house.getTHmBuildingCellInfo());
		
		postSettingHouse(house);
		return SUCCESS;
	}
	
	/**
	 * 更新房号信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		CellSizeInfoVo size = null;
		if (StringUtils.isNotBlank(sizeId)) {
			size = new CellSizeInfoVo();
			size.setId(sizeId);
		}
		if (StringUtils.isBlank(house.getArea())) {
			house.setArea("0");
		}

		List<ChargeTypeVO> modifiedChargeTypes = null;
		if (selectedChargeTypeIds != null) {
			modifiedChargeTypes = new ArrayList<ChargeTypeVO>();
			for (int i = 0, length = selectedChargeTypeIds.length; i < length; i++) {
				ChargeTypeVO ct = new ChargeTypeVO();
				ct.setId(Long.parseLong(selectedChargeTypeIds[i]));
				modifiedChargeTypes.add(ct);
			}
		}

		cellHouseholdInfoService.updateSomeProperty(houseId, padCode(house.getCode()),
				size, house.getArea(), modifiedChargeTypes);
		setSuccessFlag(true);
		updateInput();
		return SUCCESS;
	}
	
	/**
	 * 删除房号信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		fromRemove = true;
		int result = cellHouseholdInfoService.remove(houseId);
		if (result == Constants.RESULT_SUCCESS)
			successFlag = true;
		else
			successFlag = false;
		return queryList();
	}

	/**
	 * 查询房号信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String find() throws Exception {
		PagingVO<CellHouseholdInfoVo> paging = getPage();
		if(paging == null)
			paging = new PagingVO<CellHouseholdInfoVo>();

		CellHouseholdInfoVo ch = buildConditionForHouse();
		
		paging = cellHouseholdInfoService.queryForPaging(
				ch, paging.getPageNum(), PAGE_SIZE);
		
		pageNum = paging.getPageNum();
		totalPages = paging.getTotalPages();
		foundHouses = paging.getResults();
		
		return SUCCESS;
	}

	/**
	 * 生成房号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateHouseholdsInput() throws Exception {
		cell = buildingCellInfoService.get(cellId);

		BuildingCellInfoVo cellCondition = new BuildingCellInfoVo();
		cellCondition.setId(cellId);
//		初始化单元
		initSizeMap(cellCondition);
		return SUCCESS;
	}

	/**
	 * 提交需要生成的房号信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateHouseholds() throws Exception {
		List<CellHouseholdInfoVo> houseList = manualGenerate(cell);

		for (int i = 0, length = houseList.size(); i < length; i++) {
			CellHouseholdInfoVo house = houseList.get(i);

			if (sizeIds != null) {
				CellSizeInfoVo size = new CellSizeInfoVo();
				size.setId(sizeIds[i]);
				house.setTHmCellSizeInfo(size);
			}

			cellHouseholdInfoService.saveOrUpdate(house);
		}

		successFlag = true;
		cellId = cell.getId();
		return generateHouseholdsInput();
	}

	/**
	 * 判断房号编码是否不存在（新增情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String newCodeNotRepeat() throws Exception {
		notRepeat = cellHouseholdInfoService.newCodeNotRepeat(padCode(houseCodes[0]), cellId);
		return SUCCESS;
	}

	/**
	 * 判断房号编码是否不存在（更新情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateCodeNotRepeat() throws Exception {
		notRepeat = cellHouseholdInfoService.updateCodeNotRepeat(houseId, padCode(houseCodes[0]), cellId);
		return SUCCESS;
	}
	
	/**
	 * 查看房号详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		house = cellHouseholdInfoService.get(houseId);
//		qrcodeCountVO = qrcodeCountService.getByHouseId(Integer.parseInt(houseId));
		postSettingHouse(house);
		return SUCCESS;
	}
	
	
	private void postSettingHouses(List<CellHouseholdInfoVo> houses){
		if(houses == null)
			return;
		
		for (CellHouseholdInfoVo house : houses) 
			postSettingHouse(house);
	}
	
	private String padCode(String code) {
		// 保证编码一定是4位的数字, 数位不够的前面补0, 例如编码是1, 则补全为0001
		return StringUtils.leftPad(code, 4, "0");
	}
	
	private void postSettingHouse(CellHouseholdInfoVo house){
		if(house == null)
			return;
		
		if(equalZero(house.getArea()))
			house.setArea(null);
	}
	
	//初始化户型信息
	private void initSizeMap(BuildingCellInfoVo cell) {
		sizeMap = new HashMap<String, String>();
		
		CellSizeInfoVo size = new CellSizeInfoVo();
		size.setTHmBuildingCellInfo(cell);
		List<CellSizeInfoVo> sizeList = cellSizeInfoService.find(size);

		for (int i = 0, length = sizeList.size(); i < length; i++) {
			CellSizeInfoVo s = sizeList.get(i);
			String sizeDesc = s.getRoom() + getText("housemgr.size.generate.room") + s.getHall()
					+ getText("housemgr.size.generate.hall");
			sizeMap.put(s.getId(), sizeDesc);
		}
		
		if(sizeMap.isEmpty())
			setUnallowedFlag(true);
	}
	
	//手工输入房号信息用于生成房号
	private List<CellHouseholdInfoVo> manualGenerate(BuildingCellInfoVo cell) {
		List<CellHouseholdInfoVo> houseList = new ArrayList<CellHouseholdInfoVo>();
		for (int i = 0, length = houseCodes.length; i < length; i++) {
			CellHouseholdInfoVo house = new CellHouseholdInfoVo();
			house.setTHmBuildingCellInfo(cell);
			house.setCode(padCode(houseCodes[i]));
			// 让房号名称和房号CODE保持一致, 有些接口用到了房号名称
			house.setName(house.getCode());

			houseList.add(house);
		}
		return houseList;
	}

	//创建房号查询条件
	private CellHouseholdInfoVo buildConditionForHouse() {		
		HousingDistrictInfoVo hd = new HousingDistrictInfoVo();
		hd.setId(getDistrictId());
		
		HousingDistrictRegionInfoVo dr = new HousingDistrictRegionInfoVo();
		dr.setName(regionName);
		dr.setHousingDistrictInfo(hd);
		
		RegionBuildingInfoVo rb = new RegionBuildingInfoVo();
		rb.setName(buildingName);
		rb.setTHmHousingDistrictRegionInfo(dr);

		BuildingCellInfoVo bc = new BuildingCellInfoVo();
		bc.setName(cellName);
		bc.setTHmRegionBuildingInfo(rb);

		CellHouseholdInfoVo ch = new CellHouseholdInfoVo();
		ch.setName(houseName);
		ch.setTHmBuildingCellInfo(bc);
		return ch;
	}
	
		
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}
	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
	
	public IBuildingCellInfoService getBuildingCellInfoService() {
		return buildingCellInfoService;
	}
	public void setBuildingCellInfoService(IBuildingCellInfoService buildingCellInfoService) {
		this.buildingCellInfoService = buildingCellInfoService;
	}
	
	public BuildingCellInfoVo getCell() {
		return cell;
	}
	public void setCell(BuildingCellInfoVo cell) {
		this.cell = cell;
	}
	
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getHousingStatus() {
		return housingStatus;
	}
	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}

	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public CellHouseholdInfoVo getHouse() {
		return house;
	}
	public void setHouse(CellHouseholdInfoVo house) {
		this.house = house;
	}
	
	public ICellSizeInfoService getCellSizeInfoService() {
		return cellSizeInfoService;
	}
	public void setCellSizeInfoService(ICellSizeInfoService cellSizeInfoService) {
		this.cellSizeInfoService = cellSizeInfoService;
	}
	
	public void setSizeMap(Map<String, String> sizeMap) {
		this.sizeMap = sizeMap;
	}
	public Map<String, String> getSizeMap() {
		return sizeMap;
	}
	
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	
	public IChargeTypeService getChargeTypeService() {
		return chargeTypeService;
	}
	public void setChargeTypeService(IChargeTypeService chargeTypeService) {
		this.chargeTypeService = chargeTypeService;
	}
	
	public List<ChargeTypeVO> getChargeTypes() {
		return chargeTypes;
	}
	public void setChargeTypes(List<ChargeTypeVO> chargeTypes) {
		this.chargeTypes = chargeTypes;
	}
	
	public String[] getSelectedChargeTypeIds() {
		return selectedChargeTypeIds;
	}
	public void setSelectedChargeTypeIds(String[] selectedChargeTypeIds) {
		this.selectedChargeTypeIds = selectedChargeTypeIds;
	}
	
	public List<CellHouseholdInfoVo> getFoundHouses() {
		return foundHouses;
	}
	public void setFoundHouses(List<CellHouseholdInfoVo> foundHouses) {
		this.foundHouses = foundHouses;
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public String[] getHouseCodes() {
		return houseCodes;
	}
	public void setHouseCodes(String[] houseCodes) {
		this.houseCodes = houseCodes;
	}
	
	public String[] getSizeIds() {
		return sizeIds;
	}
	public void setSizeIds(String[] sizeIds) {
		this.sizeIds = sizeIds;
	}
	
	public boolean isNotRepeat() {
		return notRepeat;
	}
	public void setNotRepeat(boolean notRepeat) {
		this.notRepeat = notRepeat;
	}
	
	public boolean isFromRemove() {
		return fromRemove;
	}
	public void setFromRemove(boolean fromRemove) {
		this.fromRemove = fromRemove;
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public boolean isUnallowedFlag() {
		return unallowedFlag;
	}
	public void setUnallowedFlag(boolean unallowedFlag) {
		this.unallowedFlag = unallowedFlag;
	}

	public IQrcodeCountService getQrcodeCountService() {
		return qrcodeCountService;
	}

	public void setQrcodeCountService(IQrcodeCountService qrcodeCountService) {
		this.qrcodeCountService = qrcodeCountService;
	}

	public QrcodeCountVO getQrcodeCountVO() {
		return qrcodeCountVO;
	}

	public void setQrcodeCountVO(QrcodeCountVO qrcodeCountVO) {
		this.qrcodeCountVO = qrcodeCountVO;
	}
}
