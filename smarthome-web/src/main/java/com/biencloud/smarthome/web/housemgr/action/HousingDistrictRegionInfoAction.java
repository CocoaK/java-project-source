package com.biencloud.smarthome.web.housemgr.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;

/**
 * 小区区域Action
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
@SuppressWarnings("serial")
public class HousingDistrictRegionInfoAction extends
		HouseMgrBaseAction<HousingDistrictRegionInfoVo> {
	// 查询区域
	private String regionName;

	// 修改区域
	private String regionId;
	private HousingDistrictRegionInfoVo region;
	
	private List<HousingDistrictRegionInfoVo> regionVos;

	// 生成区域
	private String districtId;
	private String districtName;
	private String districtFloorPlan;
	private String[] regionCodes;
	private String[] regionNames;
	private String[] coordinates;

	private boolean successFlag;
	private boolean fromRemove;
	
	/**
	 * 新增的区域编码不重复
	 */
	private boolean newRegionCodeNotRepeat;
	/**
	 * 更新的区域编码不重复
	 */
	private boolean updateRegionCodeNotRepeat;
	
	private boolean existRegionName;

	private IHousingDistrictRegionInfoService housingDistrictRegionInfoService;
	private IHousingDistrictInfoService housingDistrictInfoService;

	
	/**
	 * 查询区域列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<HousingDistrictRegionInfoVo> page = getPage();
		if(page == null)
			page = new PagingVO<HousingDistrictRegionInfoVo>();

		// 默认归属小区为用户归属小区
		if (StringUtils.isBlank(districtId)) {
			districtId = super.getDistrictId();
		}

		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(districtId);
		HousingDistrictRegionInfoVo vo = new HousingDistrictRegionInfoVo();
		vo.setName(regionName);
		vo.setHousingDistrictInfo(district);
		PagingVO<HousingDistrictRegionInfoVo> pagingVO = housingDistrictRegionInfoService
				.queryHousingDistrictRegionInfosForPaging(vo, page.getPageNum(),
						page.getPageSize());

		setPage(pagingVO);
		if(ActionUtils.judgContainsValue(getRequest(), "print")) return "print";
		else return SUCCESS;
	}

	/**
	 * 查询所有符合条件的区域信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRegions() throws Exception {
		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(districtId);
		HousingDistrictRegionInfoVo vo = new HousingDistrictRegionInfoVo();
		vo.setHousingDistrictInfo(district);

		regionVos = housingDistrictRegionInfoService.getRegions(vo);
		return SUCCESS;
	}
	
	/**
	 * 查看区域详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		region = housingDistrictRegionInfoService.get(regionId);
		return SUCCESS;
	}

	/**
	 * 获取区域信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		viewDetail();
		return SUCCESS;
	}

	/**
	 * 更新区域信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		region.setCode(padCode(region.getCode()));
		housingDistrictRegionInfoService.update(region);

		setSuccessFlag(true);
		regionId = region.getId();
		return updateInput();
	}
	
	/**
	 * 生成区域
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateRegionsInput() throws Exception {
		if (districtId == null || StringUtils.isBlank(districtId)) {
			// 从登陆模块取出用户归属的小区
			districtId = super.getDistrictId();
		}

		HousingDistrictInfoVo vo = housingDistrictInfoService.getHousingDistrictInfo(districtId);
		districtName = vo.getName();
		districtFloorPlan = vo.getFloorPlan();
		return SUCCESS;
	}

	/**
	 * 提交需要生成的区域
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateRegions() throws Exception {
		for (int i = 0, length = regionNames.length; i < length; i++) {
			// 区域归属的小区信息, 新增时要保存这个关系
			HousingDistrictInfoVo district = new HousingDistrictInfoVo();
			district.setId(districtId);
			district.setName(districtName);

			HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();
			region.setName(regionNames[i]);
			region.setCoordinate(coordinates[i]);
			region.setHousingDistrictInfo(district);
			region.setCode(padCode(regionCodes[i]));

			housingDistrictRegionInfoService.saveOrUpdate(region);
		}
		successFlag = true;
		return generateRegionsInput();
	}

	/**
	 * 删除区域信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		fromRemove = true;
		int result = housingDistrictRegionInfoService.remove(regionId);		
		if (result == Constants.RESULT_SUCCESS)
			successFlag = true;
		else
			successFlag = false;
		
		return queryList();
	}

	/**
	 * 判断区域编码是否不存在（新增情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String newCodeNotRepeat() throws Exception {
		newRegionCodeNotRepeat = housingDistrictRegionInfoService.newCodeNotRepeat(padCode(regionCodes[0]), districtId);
		return SUCCESS;
	}

	/**
	 * 判断区域编码是否不存在（更新情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateCodeNotRepeat() throws Exception {
		updateRegionCodeNotRepeat = housingDistrictRegionInfoService.updateCodeNotRepeat(
				regionId, padCode(regionCodes[0]), districtId);
		return SUCCESS;
	}

	/**
	 * 判断区域名称是否已经存在。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String existRegionName() throws Exception {
		if(housingDistrictRegionInfoService.existRegionName(districtId, regionId, regionNames[0]))
			setExistRegionName(true);
		return SUCCESS;
	}
	
	
	private String padCode(String code) {
		// 保证编码一定是2位的数字, 数位不够的前面补0, 例如编码是1, 则补全为01
		return StringUtils.leftPad(code, 2, "0");
	}
	
	
	public IHousingDistrictRegionInfoService getHousingDistrictRegionInfoService() {
		return housingDistrictRegionInfoService;
	}
	public void setHousingDistrictRegionInfoService(
			IHousingDistrictRegionInfoService housingDistrictRegionInfoService) {
		this.housingDistrictRegionInfoService = housingDistrictRegionInfoService;
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public HousingDistrictRegionInfoVo getRegion() {
		return region;
	}
	public void setRegion(HousingDistrictRegionInfoVo region) {
		this.region = region;
	}
	
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String[] getRegionNames() {
		return regionNames;
	}	
	public void setRegionNames(String[] regionNames) {
		this.regionNames = regionNames;
	}
	
	public String[] getCoordinates() {
		return coordinates;
	}	
	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public List<HousingDistrictRegionInfoVo> getRegionVos() {
		return regionVos;
	}
	public void setRegionVos(List<HousingDistrictRegionInfoVo> regionVos) {
		this.regionVos = regionVos;
	}
	
	public String getDistrictFloorPlan() {
		return districtFloorPlan;
	}
	public void setDistrictFloorPlan(String districtFloorPlan) {
		this.districtFloorPlan = districtFloorPlan;
	}
	
	public String[] getRegionCodes() {
		return regionCodes;
	}
	public void setRegionCodes(String[] regionCodes) {
		this.regionCodes = regionCodes;
	}
	
	public boolean isNewRegionCodeNotRepeat() {
		return newRegionCodeNotRepeat;
	}
	public void setNewRegionCodeNotRepeat(boolean newRegionCodeNotRepeat) {
		this.newRegionCodeNotRepeat = newRegionCodeNotRepeat;
	}
	
	public boolean isUpdateRegionCodeNotRepeat() {
		return updateRegionCodeNotRepeat;
	}
	public void setUpdateRegionCodeNotRepeat(boolean updateRegionCodeNotRepeat) {
		this.updateRegionCodeNotRepeat = updateRegionCodeNotRepeat;
	}
	
	
	public boolean isExistRegionName() {
		return existRegionName;
	}
	public void setExistRegionName(boolean existRegionName) {
		this.existRegionName = existRegionName;
	}

	public boolean isFromRemove() {
		return fromRemove;
	}
	public void setFromRemove(boolean fromRemove) {
		this.fromRemove = fromRemove;
	}
}
