package com.biencloud.smarthome.web.housemgr.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.biencloud.smarthome.web.charge.vo.ChargeCalModeVO;
import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RoomVo;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.wsclient.stub.BuildingCellInfo;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.CellSizeInfo;
import com.biencloud.smarthome.web.wsclient.stub.ChargeType;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictRegionInfo;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.PropertyCompanyInfo;
import com.biencloud.smarthome.web.wsclient.stub.RegionBuildingInfo;
import com.biencloud.smarthome.web.wsclient.stub.Room;

/**
 * 转换房产管理模型中有级联关系的模型
 * 由于分离了Service层的Model和VO, 需要在他们之间相互转换.
 * 
 * @author jsun  
 * @since 1.0 2012-5-19
 */
public class HouseManagementModelVoConvert {
	/** 对象拷贝属性名：创建时间 */
	public static final String CREATE_TIME = "createTime";
	/** 对象拷贝属性名：入住时间 */
	public static final String CHECKIN_DATE = "checkInDate";
	/** 对象拷贝属性名：小区信息 */
	public static final String HOUSING_DISTRICT_INFO = "housingDistrictInfo";
	/** 对象拷贝属性名：区域信息 */
	public static final String HOUSING_DISTRICT_REGION_INFO = "THmHousingDistrictRegionInfo";
	/** 对象拷贝属性名：楼宇信息 */
	public static final String REGION_BUILDING_INFO = "THmRegionBuildingInfo";
	/** 对象拷贝属性名：单元信息 */
	public static final String BUILDING_CELL_INFO = "THmBuildingCellInfo";
	/** 对象拷贝属性名：户型信息 */
	public static final String CELL_SIZE_INFO = "THmCellSizeInfo";
	/** 对象拷贝属性名：房间列表 */
	public static final String CELL_SIZE_ROOMS = "rooms";
	/** 对象拷贝属性名：物业公司信息 */
	public static final String PROPERTY_COMPANY_INFO = "propertyCompanyInfo";
	/** 对象拷贝属性名：收费相关 */
	public static final String CHARGE_CAL_MODE[] = {"chargeCalMode","chargeMonetaryUnit","chargeCalUnit","paUser","systemGroup","createTime","housingDistrictInfo"};
	/** 对象拷贝属性名：货币单位信息 */
	public static final String CHARGE_MONETARY_UNIT = "chargeMonetaryUnit";
	/** 对象拷贝属性名：业主信息 */
	public static final String OWNER = "owner";

	/**
	 * 将区域实体信息转换为值信息。
	 * @param region 区域实体信息
	 * @return
	 */
	public static HousingDistrictRegionInfoVo region2Vo(HousingDistrictRegionInfo region) {
		HousingDistrictRegionInfoVo regionVo = new HousingDistrictRegionInfoVo();
		BeanUtils.copyProperties(region, regionVo, new String[] { CREATE_TIME, HOUSING_DISTRICT_INFO });

		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		if(region.getHousingDistrictInfo()!=null)
			BeanUtils.copyProperties(region.getHousingDistrictInfo(), district, new String[] { PROPERTY_COMPANY_INFO });
		regionVo.setHousingDistrictInfo(district);

		return regionVo;
	}

	/**
	 * 将单元值信息转换为实体信息。
	 * @param cellVo 单元值信息
	 * @return
	 */
	public static BuildingCellInfo cell2Model(BuildingCellInfoVo cellVo) {
		BuildingCellInfo cell = new BuildingCellInfo();
		BeanUtils.copyProperties(cellVo, cell, new String[] { REGION_BUILDING_INFO });

		if (cellVo.getTHmRegionBuildingInfo() != null) {
			RegionBuildingInfo building = new RegionBuildingInfo();
			BeanUtils.copyProperties(cellVo.getTHmRegionBuildingInfo(), building, new String[] { HOUSING_DISTRICT_REGION_INFO });
			
			if (cellVo.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo() != null) {
				building.setTHmHousingDistrictRegionInfo(hdRegion2Model(cellVo.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo()));
			}
			
			cell.setTHmRegionBuildingInfo(building);
		}

		return cell;
	}

	/**
	 * 将单元实体信息转换为值信息。
	 * @param cell 单元实体信息
	 * @return
	 */
	public static BuildingCellInfoVo cell2Vo(BuildingCellInfo cell) {
		BuildingCellInfoVo cellVo = new BuildingCellInfoVo();
		BeanUtils.copyProperties(cell, cellVo, new String[] { CREATE_TIME, REGION_BUILDING_INFO });

		if (cell.getTHmRegionBuildingInfo() != null) { // 转换单元所属的楼宇信息
			RegionBuildingInfo building = cell.getTHmRegionBuildingInfo();
			RegionBuildingInfoVo buildingVo = new RegionBuildingInfoVo();
			BeanUtils.copyProperties(building, buildingVo, new String[] { CREATE_TIME, HOUSING_DISTRICT_REGION_INFO });
			cellVo.setTHmRegionBuildingInfo(buildingVo);

			if (building.getTHmHousingDistrictRegionInfo() != null) { // 转换楼宇所属的区域信息
				HousingDistrictRegionInfo region = building.getTHmHousingDistrictRegionInfo();
				HousingDistrictRegionInfoVo regionVo = new HousingDistrictRegionInfoVo();
				BeanUtils.copyProperties(region, regionVo, new String[] { CREATE_TIME, HOUSING_DISTRICT_INFO });
				buildingVo.setTHmHousingDistrictRegionInfo(regionVo);

				if (region.getHousingDistrictInfo() != null) { // 转换区域所属的小区信息
					HousingDistrictInfo district = region.getHousingDistrictInfo();
					HousingDistrictInfoVo districtVo = new HousingDistrictInfoVo();
					BeanUtils.copyProperties(district, districtVo, new String[] { PROPERTY_COMPANY_INFO });
					regionVo.setHousingDistrictInfo(districtVo);
				}
			}
		}

		return cellVo;
	}
	
	/**
	 * 将小区值信息转换为实体信息。
	 * @param districtVo 小区值信息
	 * @return
	 */
	public static HousingDistrictInfo district2Model(HousingDistrictInfoVo districtVo) {
		HousingDistrictInfo district = new HousingDistrictInfo();
		BeanUtils.copyProperties(districtVo, district, new String[] { PROPERTY_COMPANY_INFO });

		if (districtVo.getPropertyCompanyInfo() != null) {
			if (districtVo.getPropertyCompanyInfo().getId() != 0) {
				PropertyCompanyInfo propertyCompany = new PropertyCompanyInfo();
				BeanUtils.copyProperties(districtVo.getPropertyCompanyInfo(), propertyCompany);
				district.setPropertyCompanyInfo(propertyCompany);
			} else {
				district.setPropertyCompanyInfo(null);
			}
		} else {
			district.setPropertyCompanyInfo(null);
		}

		return district;
	}

	/**
	 * 将区域分页实体信息转换为值信息。
	 * @param paging 区域分页实体信息
	 * @param pagingVo 区域分页值信息
	 * @return
	 */
	public static PagingVO<HousingDistrictRegionInfoVo> pagingRegion2Vo(Paging paging, PagingVO<HousingDistrictRegionInfoVo> pagingVo) {
		List<Object> regions = paging.getResults();
		for (int i = 0, length = regions.size(); i < length; i++) {
			HousingDistrictRegionInfo region = (HousingDistrictRegionInfo) regions.get(i);
			HousingDistrictInfo district = region.getHousingDistrictInfo();

			if (district != null) {
				HousingDistrictInfoVo districtVo = new HousingDistrictInfoVo();
				BeanUtils.copyProperties(district, districtVo, new String[] { PROPERTY_COMPANY_INFO });

				pagingVo.getResults().get(i).setHousingDistrictInfo(districtVo);
			}
		}

		return pagingVo;
	}

	/**
	 * 将楼宇分页实体信息转换为值信息。
	 * @param paging 楼宇分页实体信息
	 * @param pagingVo 楼宇分页值信息
	 * @return
	 */
	public static PagingVO<RegionBuildingInfoVo> pagingBuilding2Vo(Paging paging, PagingVO<RegionBuildingInfoVo> pagingVo) {
		List<Object> buildings = paging.getResults();
		for (int i = 0, length = buildings.size(); i < length; i++) {
			RegionBuildingInfoVo buildingVo = pagingVo.getResults().get(i);

			RegionBuildingInfo building = (RegionBuildingInfo) buildings.get(i);
			HousingDistrictRegionInfo region = building.getTHmHousingDistrictRegionInfo();

			if (region != null) {
				HousingDistrictRegionInfoVo regionVo = new HousingDistrictRegionInfoVo();
				BeanUtils.copyProperties(region, regionVo, new String[] { CREATE_TIME, HOUSING_DISTRICT_INFO });

				HousingDistrictInfo district = region.getHousingDistrictInfo();
				if (district != null) {
					HousingDistrictInfoVo districtVo = new HousingDistrictInfoVo();
					BeanUtils.copyProperties(district, districtVo, new String[] { CREATE_TIME, PROPERTY_COMPANY_INFO });

					regionVo.setHousingDistrictInfo(districtVo);
				}

				buildingVo.setTHmHousingDistrictRegionInfo(regionVo);
			}
		}

		return pagingVo;
	}

	/**
	 * 将单元分页实体信息转换为值信息。
	 * @param paging 单元分页实体信息
	 * @param pagingVo 单元分页值信息
	 * @return
	 */
	public static PagingVO<BuildingCellInfoVo> pagingCell2Vo(Paging paging, PagingVO<BuildingCellInfoVo> pagingVo) {
		List<Object> cells = paging.getResults();
		for (int i = 0, length = cells.size(); i < length; i++) {
			pagingVo.getResults().set(i, cell2Vo((BuildingCellInfo) cells.get(i)));
		}

		return pagingVo;
	}

	/**
	 * 将房号值信息转换为实体信息。
	 * @param vo 房号值信息
	 * @return
	 */
	public static CellHouseholdInfo house2Model(CellHouseholdInfoVo vo) {
		CellHouseholdInfo model = new CellHouseholdInfo();
		BeanUtils.copyProperties(vo, model, new String[] { BUILDING_CELL_INFO, OWNER, CELL_SIZE_INFO,CHECKIN_DATE,CREATE_TIME });

		BuildingCellInfoVo cellVo = vo.getTHmBuildingCellInfo();
		if (cellVo != null) {
			model.setTHmBuildingCellInfo(cell2Model(vo.getTHmBuildingCellInfo()));
		}
		
		OwnerUserVO ownerVo = vo.getOwner();
		if (ownerVo != null) {
			OwnerUser owner = new OwnerUser();
			owner.setUserName(ownerVo.getUserName());
			owner.setUserId(ownerVo.getUserId());
			owner.setHouseId(ownerVo.getHouseId());
			model.setOwner(owner);
		}

		CellSizeInfoVo sizeVo = vo.getTHmCellSizeInfo();
		if (sizeVo != null) {
			CellSizeInfo size = new CellSizeInfo();
			BeanUtils.copyProperties(sizeVo, size, new String[] { CREATE_TIME, BUILDING_CELL_INFO });

			model.setTHmCellSizeInfo(size);
		}

		return model;
	}

	/**
	 * 将房号实体信息转换为值信息。
	 * @param model 房号实体信息
	 * @return
	 */
	public static CellHouseholdInfoVo house2Vo(CellHouseholdInfo model) {
		CellHouseholdInfoVo vo = new CellHouseholdInfoVo();
		BeanUtils.copyProperties(model, vo, new String[] { CHECKIN_DATE, CREATE_TIME,
				BUILDING_CELL_INFO, CELL_SIZE_INFO, OWNER });

		if (model.getCheckInDate() != null) {
			vo.setCheckInDate(model.getCheckInDate().toGregorianCalendar().getTime());
		}

		BuildingCellInfo cell = model.getTHmBuildingCellInfo();
		if (cell != null) {
			vo.setTHmBuildingCellInfo(cell2Vo(cell));
		}
		
		CellSizeInfo size = model.getTHmCellSizeInfo();
		if (size != null) {
			vo.setTHmCellSizeInfo(size2Vo(size));
		}

		List<ChargeType> chargeTypes = model.getChargeTypes();
		if (chargeTypes != null) {
			List<ChargeTypeVO> chargeTypeVos = new ArrayList<ChargeTypeVO>();
			for (int i = 0, length = chargeTypes.size(); i < length; i++) {
				chargeTypeVos.add(chargeType2Vo(chargeTypes.get(i)));
			}
			vo.setChargeTypes(chargeTypeVos);
		}
		
		OwnerUser owner = model.getOwner();
		if (owner != null) {
			OwnerUserVO ownerVo = new OwnerUserVO();
			ownerVo.setUserId(owner.getUserId());
			ownerVo.setUserName(owner.getUserName());
			ownerVo.setHouseId(owner.getHouseId());
			ownerVo.setGender(owner.getGender());
			ownerVo.setHomePhone(owner.getHomePhone());
			ownerVo.setIdCard(owner.getIdCard());
			ownerVo.setHouseName(owner.getHouseName());
			ownerVo.setMobilePhone(owner.getMobilePhone());
			ownerVo.setPhotoPath(owner.getPhotoPath());
			vo.setOwner(ownerVo);
		}
		
		return vo;
	}
	
	/**
	 * 将房号实体信息转换为值信息（只包括房号本身信息，不转换单元、户型、业主等信息）。
	 * @param model 房号实体信息
	 * @return
	 */
	public static CellHouseholdInfoVo house2VoLite(CellHouseholdInfo model) {
		CellHouseholdInfoVo vo = new CellHouseholdInfoVo();
		BeanUtils.copyProperties(model, vo, new String[] { CHECKIN_DATE, CREATE_TIME,
				BUILDING_CELL_INFO, CELL_SIZE_INFO, OWNER });
		return vo;
	}
	
	/**
	 * 将户型值信息转换为实体信息。
	 * @param vo 户型值信息
	 * @return
	 */
	public static CellSizeInfo size2Model(CellSizeInfoVo vo) {
		CellSizeInfo model = new CellSizeInfo();
		BeanUtils.copyProperties(vo, model, new String[] { BUILDING_CELL_INFO });

		BuildingCellInfoVo cellVo = vo.getTHmBuildingCellInfo();
		if (cellVo != null) {
			BuildingCellInfo cell = new BuildingCellInfo();
			BeanUtils.copyProperties(cellVo, cell, new String[] { CREATE_TIME, REGION_BUILDING_INFO });

			model.setTHmBuildingCellInfo(cell);
		}
		
		if(vo.getRooms() != null){
			List<Room> rooms = model.getRooms();
			for (RoomVo roomvo : vo.getRooms()) {
				Room room = new Room();
				BeanUtils.copyProperties(roomvo, room);
				rooms.add(room);
			}
		}

		return model;
	}

	/**
	 * 将户型实体信息转换为值信息。
	 * @param model 户型实体信息
	 * @return
	 */
	public static CellSizeInfoVo size2Vo(CellSizeInfo model) {
		CellSizeInfoVo vo = new CellSizeInfoVo();
		BeanUtils.copyProperties(model, vo, new String[] { CREATE_TIME, BUILDING_CELL_INFO, CELL_SIZE_ROOMS });
		List<Room> rooms = model.getRooms();
		if(rooms != null && rooms.size() > 0){
			List<RoomVo> roomvos = new ArrayList<RoomVo>();
			for (Room room : rooms) {
				RoomVo roomvo = new RoomVo();
				BeanUtils.copyProperties(room, roomvo);
				roomvos.add(roomvo);
			}
			vo.setRooms(roomvos);
		}
		return vo;
	}

	/**
	 * 将房号分页实体信息转换为值信息。
	 * @param paging 房号分页实体信息
	 * @param pagingVo 房号分页值信息
	 * @return
	 */
	public static PagingVO<CellHouseholdInfoVo> pagingHouse2Vo(Paging paging,
			PagingVO<CellHouseholdInfoVo> pagingVo) {
		List<Object> houses = paging.getResults();
		for (int i = 0, length = houses.size(); i < length; i++) {
			CellHouseholdInfoVo houseVo = pagingVo.getResults().get(i);
			CellHouseholdInfo house = (CellHouseholdInfo) houses.get(i);

			BuildingCellInfo cell = house.getTHmBuildingCellInfo();
			if (cell != null) {
				houseVo.setTHmBuildingCellInfo(cell2Vo(cell));
			}

			CellSizeInfo size = house.getTHmCellSizeInfo();
			if (size != null) {
				houseVo.setTHmCellSizeInfo(size2Vo(size));
			}
			
			List<ChargeType> chargeTypes = house.getChargeTypes();
			if (chargeTypes != null) {
				List<ChargeTypeVO> chargeTypeVos = new ArrayList<ChargeTypeVO>(chargeTypes.size());
				for (int j = 0, jlength = chargeTypes.size(); j < jlength; j++) {
					ChargeTypeVO chargeTypeVo = new ChargeTypeVO();
					BeanUtils.copyProperties(chargeTypes.get(j), chargeTypeVo, CHARGE_CAL_MODE);
					chargeTypeVos.add(chargeTypeVo);
				}
				houseVo.setChargeTypes(chargeTypeVos);
			}

			OwnerUser owner = house.getOwner();
			if (owner != null) {
				OwnerUserVO ownerVo = new OwnerUserVO();
				ownerVo.setUserId(owner.getUserId());
				ownerVo.setUserName(owner.getUserName());

				houseVo.setOwner(ownerVo);
			}
		}

		return pagingVo;
	}

	/**
	 * 将收费类型实体信息转换为值信息。
	 * @param model 收费类型实体信息
	 * @return
	 */
	public static ChargeTypeVO chargeType2Vo(ChargeType model){
		ChargeTypeVO ctVo = new ChargeTypeVO();
		BeanUtils.copyProperties(model, ctVo, CHARGE_CAL_MODE);

		if (model.getChargeCalMode() != null) {
			ChargeCalModeVO dOb = new ChargeCalModeVO();
			BeanUtils.copyProperties(model.getChargeCalMode(), dOb);
			ctVo.setChargeCalMode(dOb);
		}
		
		if (model.getChargeMonetaryUnit() != null) {
			ChargeMonetaryUnitVO cmuVo = new ChargeMonetaryUnitVO();
			BeanUtils.copyProperties(model.getChargeMonetaryUnit(), cmuVo);
			ctVo.setChargeMonetaryUnit(cmuVo);
		}

		return ctVo;
	}
	
	/**
	 * 
	 * 方法的描述: RegionBuildingInfoVO转换成RegionBuildingInfo
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:35:05
	 * @param vo
	 * @return RegionBuildingInfo
	 */
	public static RegionBuildingInfo regionBuildingInfo2Model(RegionBuildingInfoVo vo) {
		RegionBuildingInfo model = new RegionBuildingInfo();
		BeanUtils.copyProperties(vo, model, new String[] { HOUSING_DISTRICT_REGION_INFO, CREATE_TIME });

		HousingDistrictRegionInfoVo housingDistrictRegionInfoVo = vo.getTHmHousingDistrictRegionInfo();
		HousingDistrictRegionInfo housingDistrictRegionInfo = new HousingDistrictRegionInfo();			
		if(housingDistrictRegionInfoVo!=null && housingDistrictRegionInfoVo.getHousingDistrictInfo()!=null){
			BeanUtils.copyProperties(housingDistrictRegionInfoVo, housingDistrictRegionInfo, new String[] { CREATE_TIME,HOUSING_DISTRICT_INFO});
			HousingDistrictInfo housingDistrictInfo = district2Model(housingDistrictRegionInfoVo.getHousingDistrictInfo());
			housingDistrictRegionInfo.setHousingDistrictInfo(housingDistrictInfo);
		}
		model.setTHmHousingDistrictRegionInfo(housingDistrictRegionInfo);
		return model;
	}
	
	/**
	 * 
	 * 方法的描述: housingDistrictRegionInfoVO转换成HousingDistrictRegionInfo
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:35:55
	 * @param housingDistrictRegionInfoVO
	 * @return HousingDistrictRegionInfo
	 */
	public static HousingDistrictRegionInfo hdRegion2Model(HousingDistrictRegionInfoVo housingDistrictRegionInfoVO){
		HousingDistrictRegionInfo housingDistrictRegionInfo = new HousingDistrictRegionInfo();
		BeanUtils.copyProperties(housingDistrictRegionInfoVO, housingDistrictRegionInfo, new String[] { CREATE_TIME,HOUSING_DISTRICT_INFO});
		if(housingDistrictRegionInfoVO.getHousingDistrictInfo()!=null){
			HousingDistrictInfo housingDistrictInfo = district2Model(housingDistrictRegionInfoVO.getHousingDistrictInfo());
			housingDistrictRegionInfo.setHousingDistrictInfo(housingDistrictInfo);
		}
		return housingDistrictRegionInfo;
	}
	
	/**
	 * 
	 * 方法的描述: regionBuildingInfo转换成RegionBuildingInfoVo
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:36:19
	 * @param regionBuildingInfo
	 * @return RegionBuildingInfoVo
	 */
	public static RegionBuildingInfoVo regionBuildingInfo2Vo(RegionBuildingInfo regionBuildingInfo){
		RegionBuildingInfoVo regionBuildingInfoVo = new RegionBuildingInfoVo();
		BeanUtils.copyProperties(regionBuildingInfo, regionBuildingInfoVo, new String[] { HOUSING_DISTRICT_REGION_INFO, CREATE_TIME });
		return regionBuildingInfoVo;
	}
	
	/**
	 * 
	 * 方法的描述: HousingDistrictInfo转换成HousingDistrictInfoVo
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:36:50
	 * @param housingDistrictInfo
	 * @return HousingDistrictInfoVo
	 */
	public static HousingDistrictInfoVo housingDistrict2Vo(HousingDistrictInfo housingDistrictInfo){
		HousingDistrictInfoVo housingDistrictInfoVo = new HousingDistrictInfoVo();
		BeanUtils.copyProperties(housingDistrictInfo, housingDistrictInfoVo, new String[] { PROPERTY_COMPANY_INFO });
		return housingDistrictInfoVo;
	}
}
