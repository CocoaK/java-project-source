package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.BuildingCellInfo;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.CellSizeInfo;
import com.biencloud.smarthome.web.wsclient.stub.ChargeType;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictRegionInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.RegionBuildingInfo;

/**
 * 房号 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-23
 */
public class CellHouseholdInfoServiceImpl extends BaseService<CellHouseholdInfoVo>
		implements ICellHouseholdInfoService {
	@Override
	public void saveOrUpdate(CellHouseholdInfoVo house) {
		getSmartHomeService().saveOrUpdateCellHouseholdInfo(
				HouseManagementModelVoConvert.house2Model(house));
	}

	@Override
	public PagingVO<CellHouseholdInfoVo> queryForPaging(CellHouseholdInfoVo condition,
			int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().queryCellHouseholdInfosForPaging(
				HouseManagementModelVoConvert.house2Model(condition), pageNum, pageSize);

		return HouseManagementModelVoConvert.pagingHouse2Vo(paging,
				convertToVO(paging, new String[] {
						HouseManagementModelVoConvert.CREATE_TIME,
						HouseManagementModelVoConvert.BUILDING_CELL_INFO,
						HouseManagementModelVoConvert.CELL_SIZE_INFO,
						HouseManagementModelVoConvert.OWNER},
						HouseManagementModelVoConvert.CHECKIN_DATE));
	}

	@Override
	public int remove(String id) {
		return getSmartHomeService().removeHouseById(id);
	}

	@Override
	public CellHouseholdInfoVo get(String id) {
		return HouseManagementModelVoConvert.house2Vo(
				getSmartHomeService().getCellHouseholdInfo(id));
	}

	@Override
	public void updateSomeProperty(String id, String code, CellSizeInfoVo size, String area,
			List<ChargeTypeVO> chargeTypes) {
		List<ChargeType> chargeTypeModels = new ArrayList<ChargeType>();
		if(chargeTypes != null){
			for (int i = 0, length = chargeTypes.size(); i < length; i++) {
				ChargeType model = new ChargeType();
				model.setId(chargeTypes.get(i).getId());
				chargeTypeModels.add(model);
			}
		}

		CellSizeInfo sizeModel = null;
		if (size != null) {
			sizeModel = new CellSizeInfo();
			sizeModel.setId(size.getId());
		}

		getSmartHomeService().updateCellHouseholdInfoSomeProperty(id, code, sizeModel, area,
				chargeTypeModels);
	}

	@Override
	public List<CellHouseholdInfoVo> find(String districtId, String regionName, String buildingName, String cellName, String householdName) {
		HousingDistrictInfo district = new HousingDistrictInfo();
		district.setId(districtId);

		HousingDistrictRegionInfo region = new HousingDistrictRegionInfo();
		region.setName(regionName);
		region.setHousingDistrictInfo(district);

		RegionBuildingInfo building = new RegionBuildingInfo();
		building.setName(buildingName);
		building.setTHmHousingDistrictRegionInfo(region);

		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setName(cellName);
		cell.setTHmRegionBuildingInfo(building);

		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setName(householdName);
		house.setTHmBuildingCellInfo(cell);

		List<CellHouseholdInfo> houses = getSmartHomeService().findHouseholds(house);
		List<CellHouseholdInfoVo> houseVos = new ArrayList<CellHouseholdInfoVo>(houses.size());
		for (int i = 0, length = houses.size(); i < length; i++) {
			houseVos.add(HouseManagementModelVoConvert.house2Vo(houses.get(i)));
		}
		return houseVos;
	}
	
	@Override
	public List<CellHouseholdInfoVo> queryListByCellId(String cellId) {
		BuildingCellInfo cell = new BuildingCellInfo();
		cell.setId(cellId);
		CellHouseholdInfo house = new CellHouseholdInfo();
		house.setTHmBuildingCellInfo(cell);
		List<CellHouseholdInfo> houses = getSmartHomeService().findHouseholds(house);
		List<CellHouseholdInfoVo> houseVos = new ArrayList<CellHouseholdInfoVo>(houses.size());
		for (int i = 0, length = houses.size(); i < length; i++) {
			houseVos.add(HouseManagementModelVoConvert.house2Vo(houses.get(i)));
		}
		return houseVos;
	}


	@Override
	public void updateHouseSizeId(String houseId, String sizeId) {
		getSmartHomeService().updateHouseSizeId(houseId, sizeId);
	}

	@Override
	public boolean newCodeNotRepeat(String householdCode, String cellId) {
		return getSmartHomeService().newHouseCodeNotRepeat(householdCode, cellId);
	}

	@Override
	public boolean updateCodeNotRepeat(String householdId, String householdCode, String cellId) {
		return getSmartHomeService().updateHouseCodeNotRepeat(householdId, householdCode, cellId);
	}

	@Override
	public boolean existHouseName(String cellId, String houseId,
			String houseName) {
		return getSmartHomeService().existHouseName(
				cellId, houseId, houseName);
	}
	
//	@Override
//	public String getFullRoomNo(String houseId) {
//		BuildingCellInfoVo cell = null;
//		RegionBuildingInfoVo building = null;
//		HousingDistrictRegionInfoVo region = null;
//		HousingDistrictInfoVo district = null;
//		
//		CellHouseholdInfoVo house = this.get(houseId);
//		if(house!=null){
//			cell = house.getTHmBuildingCellInfo();
//		}
//		if(cell!=null){
//			building = cell.getTHmRegionBuildingInfo();
//		}
//		if(building!=null){
//			region = building.getTHmHousingDistrictRegionInfo();
//		}
//		if(region!=null){
//			district = region.getHousingDistrictInfo();
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		if(district!=null){
//			sb.append(district.getCode());
//		}
//		if (region != null) {
//			sb.append(region.getCode());
//
//		}
//		if (building != null) {
//			sb.append(building.getCode());
//		}
//		if (cell != null) {
//			sb.append(cell.getCode());
//
//		}
//		if (house != null) {
//			sb.append(house.getCode());
//
//		}
//		return sb.toString();
//	}
}
