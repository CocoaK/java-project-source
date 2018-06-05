package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictRegionInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.RegionBuildingInfo;

/**
 * 楼宇 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17
 */
public class RegionBuildingInfoServiceImpl extends BaseService<RegionBuildingInfoVo>
		implements IRegionBuildingInfoService {
	private static final String HOUSING_DISTRICT_REGION_INFO = "THmHousingDistrictRegionInfo";
	private static final String HOUSING_DISTRICT_INFO = "housingDistrictInfo";
	
	@Override
	public PagingVO<RegionBuildingInfoVo> queryRegionBuildingInfosForPaging(
			RegionBuildingInfoVo condition, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService()
				.queryRegionBuildingInfosForPaging(convert2Model(condition), pageNum, pageSize);
		return HouseManagementModelVoConvert.pagingBuilding2Vo(paging,
				convertToVO(paging, new String[] {HOUSING_DISTRICT_REGION_INFO, HouseManagementModelVoConvert.CREATE_TIME}));
	}

	@Override
	public void saveOrUpdate(RegionBuildingInfoVo building) {
		getSmartHomeService().saveOrUpdateRegionBuildingInfo(convert2Model(building));
	}

	@Override
	public RegionBuildingInfoVo get(String id) {
		RegionBuildingInfoVo buildingVo = new RegionBuildingInfoVo();
		RegionBuildingInfo building = getSmartHomeService().getRegionBuildingInfo(id);
		copyProperties(building, buildingVo, HOUSING_DISTRICT_REGION_INFO, HouseManagementModelVoConvert.CREATE_TIME);

		HousingDistrictRegionInfoVo regionVo = HouseManagementModelVoConvert.region2Vo(
				building.getTHmHousingDistrictRegionInfo());
		buildingVo.setTHmHousingDistrictRegionInfo(regionVo);

		return buildingVo;
	}

	private RegionBuildingInfo convert2Model(RegionBuildingInfoVo building) {
		RegionBuildingInfo rb = new RegionBuildingInfo();		
		copyProperties(building, rb, HOUSING_DISTRICT_REGION_INFO);

		if (building.getTHmHousingDistrictRegionInfo() !=null) {
			HousingDistrictRegionInfo dr = new HousingDistrictRegionInfo();
			copyProperties(building.getTHmHousingDistrictRegionInfo(), dr, HOUSING_DISTRICT_INFO);
			
			if (building.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo() != null) {
				HousingDistrictInfo hd = new HousingDistrictInfo();
				copyProperties(building.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo(), hd);
				dr.setHousingDistrictInfo(hd);
			}
			
			rb.setTHmHousingDistrictRegionInfo(dr);
		}

		return rb;
	}

	@Override
	public int remove(String id) {
		return getSmartHomeService().removeBuildingById(id);
	}

	@Override
	public List<RegionBuildingInfoVo> getBuildings(RegionBuildingInfoVo condition) {
		List<RegionBuildingInfo> buildings = getSmartHomeService().getBuildings(convert2Model(condition));

		List<RegionBuildingInfoVo> buildingVos = new ArrayList<RegionBuildingInfoVo>();
		
		if(buildings == null || buildings.isEmpty())
			return buildingVos;
		
		for (int i = 0, length = buildings.size(); i < length; i++) {
			RegionBuildingInfoVo buildingVo = new RegionBuildingInfoVo();
			copyProperties(buildings.get(i), buildingVo, HOUSING_DISTRICT_REGION_INFO, HouseManagementModelVoConvert.CREATE_TIME);

			buildingVos.add(buildingVo);
		}

		return buildingVos;
	}

	@Override
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId) {
		return getSmartHomeService().newBuildingCodeNotRepeat(buildingCode, regionId);
	}

	@Override
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId) {
		return getSmartHomeService().updateBuildingCodeNotRepeat(buildingId, buildingCode, regionId);
	}

	@Override
	public boolean hasCell(String buildingId) {
		return getSmartHomeService().hasCell(buildingId);
	}

	@Override
	public boolean existBuildingName(String regionId, String buildingId,
			String buildingName) {
		return getSmartHomeService().existBuildingName(
				regionId, buildingId, buildingName);
	}
}
