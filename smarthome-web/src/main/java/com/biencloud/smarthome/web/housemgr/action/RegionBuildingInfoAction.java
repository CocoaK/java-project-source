package com.biencloud.smarthome.web.housemgr.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.web.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;

/**
 * 楼宇Action
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
@SuppressWarnings("serial")
public class RegionBuildingInfoAction extends HouseMgrBaseAction<RegionBuildingInfoVo> {
	private String regionId;
	private String regionName;
	private String districtName;
	private String districtFloorPlan;
	
	/**
	 * 手动输入的多个楼宇名称
	 */
	private String[] buildingNames;
	private String[] buildingCodes;
	private String[] coordinates;
	
	/**
	 * 模糊查询
	 */
	private String buildingName;

	private String buildingId;
	private RegionBuildingInfoVo building;

	private List<RegionBuildingInfoVo> buildingVos;

	private IRegionBuildingInfoService regionBuildingInfoService;
	private IHousingDistrictRegionInfoService housingDistrictRegionInfoService;

	private boolean successFlag;
	private boolean fromRemove;

	/**
	 * 新增/修改楼宇时编号是否没有重复
	 */
	private boolean notRepeat;
	
	private boolean existBuildingName;

	
	/**
	 * 查询楼宇列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<RegionBuildingInfoVo> page = getPage();
		if(page == null)
			page = new PagingVO<RegionBuildingInfoVo>();

		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(getDistrictId());
		HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();
		region.setId(regionId);
		region.setHousingDistrictInfo(district);

		RegionBuildingInfoVo building = new RegionBuildingInfoVo();
		building.setName(buildingName);
		building.setTHmHousingDistrictRegionInfo(region);

		PagingVO<RegionBuildingInfoVo> pagingVO = regionBuildingInfoService
				.queryRegionBuildingInfosForPaging(building, page.getPageNum(),
						page.getPageSize());
		postSettingBuildings(pagingVO.getResults());
		setPage(pagingVO);
		if(ActionUtils.judgContainsValue(getRequest(), "print")) return "print";
		else return SUCCESS;
	}

	/**
	 * 生成楼宇
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateBuildingsInput() throws Exception {
		HousingDistrictRegionInfoVo region = housingDistrictRegionInfoService.get(regionId);
		regionName = region.getName();
		districtName = region.getHousingDistrictInfo().getName();
		districtFloorPlan = region.getHousingDistrictInfo().getFloorPlan();
		return SUCCESS;
	}

	/**
	 * 提交需要生成的楼宇
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateBuildings() throws Exception {
		HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();
		region.setId(regionId);

		List<RegionBuildingInfoVo> buildings = manualGenerateBuildings(region);

		for (int i = 0, length = buildings.size(); i < length; i++) {
			regionBuildingInfoService.saveOrUpdate(buildings.get(i));
		}

		successFlag = true;
		return generateBuildingsInput();
	}

	/**
	 * 判断楼宇编码是否不存在（新增情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String newCodeNotRepeat() throws Exception {
		notRepeat = regionBuildingInfoService.newBuildingCodeNotRepeat(padCode(buildingCodes[0]), regionId);
		return SUCCESS;
	}

	/**
	 * 判断楼宇编码是否不存在（更新情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateCodeNotRepeat() throws Exception {
		notRepeat = regionBuildingInfoService.updateBuildingCodeNotRepeat(buildingId, padCode(buildingCodes[0]), regionId);
		return SUCCESS;
	}
	
	/**
	 * 判断楼宇名称是否已经存在。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String existBuildingName() throws Exception {
		if(regionBuildingInfoService.existBuildingName(regionId, buildingId, buildingNames[0]))
			setExistBuildingName(true);
		return SUCCESS;
	}
	
	/**
	 * 查询所有符合条件的楼宇信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBuildings() throws Exception {
		//根据小区来获取楼栋，否则区域为空的时候会查询所有小区下的楼栋
		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(super.getDistrictId());
		HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();
		region.setId(regionId);
		region.setHousingDistrictInfo(district);
		RegionBuildingInfoVo building = new RegionBuildingInfoVo();
		building.setName(buildingName);
		building.setTHmHousingDistrictRegionInfo(region);

		buildingVos = regionBuildingInfoService.getBuildings(building);
		postSettingBuildings(buildingVos);
		return SUCCESS;
	}
	
	/**
	 * 获取楼宇信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		building = regionBuildingInfoService.get(buildingId);
		postSettingBuilding(building);
		return SUCCESS;
	}

	/**
	 * 更新楼宇信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		if (StringUtils.isBlank(building.getCoverArea())) {
			building.setCoverArea("0");
		}
		if (StringUtils.isBlank(building.getConstructionArea())) {
			building.setConstructionArea("0");
		}
		building.setCode(padCode(building.getCode()));
		regionBuildingInfoService.saveOrUpdate(building);

		buildingId = building.getId();
		successFlag = true;
		return updateInput();
	}
	
	/**
	 * 删除楼宇信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		fromRemove = true;
		int result = regionBuildingInfoService.remove(buildingId);
		if (result == Constants.RESULT_SUCCESS)
			successFlag = true;
		else
			successFlag = false;
		return queryList();
	}

	
	private String padCode(String code) {
		// 保证编码一定是3位的数字, 数位不够的前面补0, 例如编码是1, 则补全为001
		return StringUtils.leftPad(code, 3, "0");
	}
	
	//手工输入楼宇信息用于生成楼宇
	private List<RegionBuildingInfoVo> manualGenerateBuildings(HousingDistrictRegionInfoVo region) {
		List<RegionBuildingInfoVo> buildings = new ArrayList<RegionBuildingInfoVo>();
		for (int i = 0, length = buildingNames.length; i < length; i++) {
			RegionBuildingInfoVo building = new RegionBuildingInfoVo();
			building.setName(buildingNames[i]);
			building.setTHmHousingDistrictRegionInfo(region);
			building.setCode(padCode(buildingCodes[i]));
			building.setCoordinate(coordinates[i]);
			buildings.add(building);
		}
		return buildings;
	}

	private void postSettingBuildings(List<RegionBuildingInfoVo> buildings){
		if(buildings == null)
			return;
		
		for (RegionBuildingInfoVo building : buildings) 
			postSettingBuilding(building);
	}
	
	private void postSettingBuilding(RegionBuildingInfoVo building){
		if(building == null)
			return;
		
		if(equalZero(building.getCoverArea()))
			building.setCoverArea(null);
		if(equalZero(building.getConstructionArea()))
			building.setConstructionArea(null);
	}
	
	
	public IRegionBuildingInfoService getRegionBuildingInfoService() {
		return regionBuildingInfoService;
	}
	public void setRegionBuildingInfoService(
			IRegionBuildingInfoService regionBuildingInfoService) {
		this.regionBuildingInfoService = regionBuildingInfoService;
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public IHousingDistrictRegionInfoService getHousingDistrictRegionInfoService() {
		return housingDistrictRegionInfoService;
	}
	public void setHousingDistrictRegionInfoService(IHousingDistrictRegionInfoService housingDistrictRegionInfoService) {
		this.housingDistrictRegionInfoService = housingDistrictRegionInfoService;
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String[] getBuildingNames() {
		return buildingNames;
	}
	public void setBuildingNames(String[] buildingNames) {
		this.buildingNames = buildingNames;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	public RegionBuildingInfoVo getBuilding() {
		return building;
	}
	public void setBuilding(RegionBuildingInfoVo building) {
		this.building = building;
	}
	
	public List<RegionBuildingInfoVo> getBuildingVos() {
		return buildingVos;
	}
	public void setBuildingVos(List<RegionBuildingInfoVo> buildingVos) {
		this.buildingVos = buildingVos;
	}
	
	public String[] getBuildingCodes() {
		return buildingCodes;
	}
	public void setBuildingCodes(String[] buildingCodes) {
		this.buildingCodes = buildingCodes;
	}
	
	public boolean isNotRepeat() {
		return notRepeat;
	}
	public void setNotRepeat(boolean notRepeat) {
		this.notRepeat = notRepeat;
	}
	
	public boolean isExistBuildingName() {
		return existBuildingName;
	}
	public void setExistBuildingName(boolean existBuildingName) {
		this.existBuildingName = existBuildingName;
	}

	public boolean isFromRemove() {
		return fromRemove;
	}
	public void setFromRemove(boolean fromRemove) {
		this.fromRemove = fromRemove;
	}

	public String[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getDistrictFloorPlan() {
		return districtFloorPlan;
	}
	public void setDistrictFloorPlan(String districtFloorPlan) {
		this.districtFloorPlan = districtFloorPlan;
	}
}
