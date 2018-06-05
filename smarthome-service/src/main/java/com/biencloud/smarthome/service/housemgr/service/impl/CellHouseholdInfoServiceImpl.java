package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;

/**
 * 房号 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-18
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CellHouseholdInfoServiceImpl extends AbstractEstateService<CellHouseholdInfo, String>
		implements ICellHouseholdInfoService {
	
	private static final char ROOM_INFO_SEPARATOR = '|';
	
	private IDeviceDao deviceDao;
	private IPushDao pushDao;
	private ISysParamService sysParamService;
	
	@Override
	public Paging<CellHouseholdInfo> queryCellHouseholdInfosForPaging(CellHouseholdInfo condition,
			int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM CellHouseholdInfo ch");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "ch");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(ch)");
		queryString = queryString + " ORDER BY ch.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code,ch.THmBuildingCellInfo.THmRegionBuildingInfo.code,ch.THmBuildingCellInfo.code,ch.code asc";

		Paging<CellHouseholdInfo> houseInfos = queryByNamedParamsForPaging(
				pageNum, pageSize, queryString, queryStringCount, params);

		return houseInfos;
	}

	private Map<String, Object> createParams(StringBuilder jpql, CellHouseholdInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 所属单元
		if (condition.getTHmBuildingCellInfo() != null) {
			// 单元ID
			if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getId())) {
				appendCondition(jpql, "ch.THmBuildingCellInfo.id = :cellId", "cellId", 
						condition.getTHmBuildingCellInfo().getId(), params);
			}
			// 单元名称
			if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getName())) {
				appendCondition(jpql, "ch.THmBuildingCellInfo.name LIKE :cellName", "cellName", 
						"%" + condition.getTHmBuildingCellInfo().getName() + "%", params);
			}

			// 所属楼宇
			if (condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo() != null) {
				// 楼宇名
				if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName())) {
					appendCondition(jpql, "ch.THmBuildingCellInfo.THmRegionBuildingInfo.name LIKE :buildingName", "buildingName", 
							"%" + condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName() + "%", params);
				}
				// 楼宇ID
				if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getId())) {
					appendCondition(jpql, "ch.THmBuildingCellInfo.THmRegionBuildingInfo.id = :buildingId", "buildingId", 
							condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getId(), params);
				}

				// 所属区域
				if (condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo() != null) {
					// 区域名
					if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName())) {
						appendCondition(jpql, "ch.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name LIKE :regionName", "regionName", 
								"%" + condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName() + "%", params);
					}
					// 区域ID
					if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getId())) {
						appendCondition(jpql, "ch.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.id = :regionId", "regionId", 
								condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getId(), params);
					}

					// 所属小区
					if (condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo() != null) {
						// 小区ID
						if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId())) {
							appendCondition(jpql, "ch.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.id = :districtId", "districtId", 
									condition.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId(), params);
						}
					}
				}
			}
		}
		// 房号
		if(StringUtils.isNotBlank(condition.getName())) {			
			appendCondition(jpql, "ch.name LIKE :name", "name",
					"%" + condition.getName() + "%", params);
		}

		// 状态
		if(StringUtils.isNotBlank(condition.getHousingStatus())) {
			if(Constants.HOUSE_STATUS_UNCHECK.equals(condition.getHousingStatus())){
				appendCondition(jpql, "(ch.housingStatus = :housingStatus OR ch.housingStatus is null)", "housingStatus",
						condition.getHousingStatus(), params);
			}else{
				appendCondition(jpql, "ch.housingStatus = :housingStatus", "housingStatus",
						condition.getHousingStatus(), params);
			}			
		}

		// 房号CODE
		if(StringUtils.isNotBlank(condition.getCode())) {
			appendCondition(jpql, "ch.code = :code", "code",
					condition.getCode(), params);
		}
		
		// 业主姓名
		if(condition.getOwner() != null){
			if(StringUtils.isNotBlank(condition.getOwner().getUserName()))
				appendCondition(jpql, "ch.owner.userName LIKE :userName", "userName", 
						"%" + condition.getOwner().getUserName() + "%", params);
		}
		
		return params;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSomeProperty(String id, String code, CellSizeInfo size, String area,
			List<ChargeType> chargeTypes) {
		CellHouseholdInfo house = get(id);
		house.setCode(code);
		house.setName(code);
		house.setTHmCellSizeInfo(size);
		house.setArea(area);
		house.setChargeTypes(chargeTypes);
		getDao().update(house);
	}

	@Override
	public List<CellHouseholdInfo> findHouseholds(CellHouseholdInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT ch FROM CellHouseholdInfo ch");

		List<CellHouseholdInfo> houseList = findByNamedParams(jpql.toString(), params);
		return houseList;
	}
	
	@Override
	public CellHouseholdInfo getByCode(String areaNo,String regionNo,String buildingNo,String unitNo,String houseCode) {
		CellHouseholdInfo house = null;
		if(areaNo!=null&&regionNo!=null&&buildingNo!=null&&unitNo!=null&&houseCode!=null)
		{
			String hql="from CellHouseholdInfo where code=?1 and THmBuildingCellInfo.code=?2 and THmBuildingCellInfo.THmRegionBuildingInfo.code=?3 and THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code=?4 and THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code=?5";
		    List<CellHouseholdInfo> list=super.find(hql, houseCode,unitNo,buildingNo,regionNo,areaNo);
		    if(list!=null&&!list.isEmpty())
		    {
		    	house=list.get(0);
		    }
		}
		return house;
	}

	@Override
	public CellHouseholdInfo get(String id) {
		CellHouseholdInfo house = super.get(id);
		if (house != null) {
			// FIXME 由于房号关联户型, 户型又关联了多个房间, 房号再关联了收费类型(多对多关系), 会造成取出重复的收费类型
			// 例如105房号 关联 42的户型, 再关联了2条收费类型
			// 42的户型如果只有1个房间, 那就没有问题, 如果有2个房间, 那么房号的记录就会变成2条
			// 105    42    房间1
			// 105    42    房间2
			// 这就造成关联的收费类型变为4条...
			// 必须去除重复的收费类型数据
			if (house.getChargeTypes() != null) {
				// 通过将List -> Set -> List, 去除重复记录
				house.setChargeTypes(new ArrayList<ChargeType>(new HashSet<ChargeType>(house.getChargeTypes())));
			}
			house.setOwner(getOwnerUserService().getUserByHouseId(house.getId()));
		}
		return house;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CellHouseholdInfo saveOrUpdateHousehold(CellHouseholdInfo household) {
		BuildingCellInfo cell = household.getTHmBuildingCellInfo();
		String householdId = household.getId();

		if (cell == null) {
			return null;
		}

		if (StringUtils.isBlank(householdId)) { // 新增
			if (newCodeNotRepeat(household.getCode(), cell.getId())) { // 没有重复CODE才新增
				save(household);
			} else {
				household = null;
			}
		} else if (StringUtils.isNotBlank(householdId)) { // 修改
			if (updateCodeNotRepeat(householdId, household.getCode(), cell.getId())) { // 没有重复才修改
				update(household);
			} else {
				household = null;
			}
		}

		return household;
	}

	@Override
	public boolean newCodeNotRepeat(String householdCode, String cellId) {
		String hql = "select ch.id FROM CellHouseholdInfo ch where ch.code = ?1 and ch.THmBuildingCellInfo.id = ?2";
		return isNotExist(hql, householdCode, cellId);
	}

	@Override
	public boolean updateCodeNotRepeat(String householdId, String householdCode, String cellId) {
		String hql = "select ch.id FROM CellHouseholdInfo ch where ch.id != ?1 and ch.code = ?2 and ch.THmBuildingCellInfo.id = ?3";
		return isNotExist(hql, householdId, householdCode, cellId);
	}

	@Override
	public void updateHouseSizeId(String houseId, String sizeId) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE CellHouseholdInfo ch SET ch.THmCellSizeInfo.id = ?1 where ch.id = ?2");
		Object[] values = { sizeId, houseId };

		update(jpql.toString(), values);
		//pushRoomData(houseId);
	}

	/**
	 * 推送房间信息
	 * 
	 * @param houseId
	 */
	private void pushRoomData(String houseId) {
		// 查找房号对应的设备		
		List<Device> list = deviceDao.find("from Device where cellHouseholdInfo.id='"+houseId+"'");
		
		if(CollectionUtils.isEmpty(list))
			return;
		
		for (Device device : list) {
			Set<Room> rooms = device.getCellHouseholdInfo().getTHmCellSizeInfo().getRooms();
			if(CollectionUtils.isNotEmpty(rooms)){
				Push push = new Push();
				push.setPushName(PushKindConstants.PUSH_ROOM_DATA_KIND);
				push.setPushKind(PushKindConstants.PUSH_ROOM_DATA_KIND);
				push.setPushClientId(device.getDeviceCode());
				push.setAddTime(new Date());					
				push.setPushContent(buildingPushContent(Constants.PUSH_OP_TYPE_UPDATE, 
						device.getDeviceCode(), rooms));										
				getPushDao().save(push);
			}			
		}
	}

//	private void setOwnerInfo(List<CellHouseholdInfo> houses){
//		if (houses != null) {
//			for (CellHouseholdInfo house : houses) {
//				house.setOwner(getOwnerUserService().getUserByHouseId(house.getId()));
//			}
//		}
//	}
	
	private String buildingPushContent(String operationType, String deviceCode, Set<Room> rooms) {
		StringBuilder pushContent = new StringBuilder();

		pushContent.append("operationType=");
		pushContent.append(operationType);
		pushContent.append(",deviceNo=");
		pushContent.append(deviceCode);
		
		List<String> roomIds = new ArrayList<String>();
		List<String> roomNames = new ArrayList<String>();
		List<String> roomPlans = new ArrayList<String>();
		for (Room room : rooms) {
			roomIds.add(room.getId());
			roomNames.add(room.getName());
			roomPlans.add(room.getPlan());
		}
		
		pushContent.append(",room_id=");
		pushContent.append(StringUtils.join(roomIds, ROOM_INFO_SEPARATOR));
		pushContent.append(",room_name=");
		pushContent.append(StringUtils.join(roomNames, ROOM_INFO_SEPARATOR));
		pushContent.append(",image_url=");
		pushContent.append(StringUtils.join(roomPlans, ROOM_INFO_SEPARATOR));

		return pushContent.toString();
	}

	@Override
	public List<Room> queryRoomByDeviceNo(String deviceNo) {
		// 由于deviceService中引用了cellHouseholdInfoService, 这里再用deviceService会造成循环引用问题
		// 因此没有通过deviceService来查找设备信息
		// Bean with name 'deviceService' has been injected into
		// other beans [cellHouseholdInfoService] in its raw version
		// as part of a circular reference, but has eventually been wrapped.
		Device device = null;
		List<Device> list = deviceDao.find("from Device where deviceCode='"+deviceNo+"'");
		if(list != null && !list.isEmpty()) {
			device = list.get(0);
		}

		List<Room> rooms = null;
		if (device != null && device.getCellHouseholdInfo() != null) {
			if (device.getCellHouseholdInfo().getTHmCellSizeInfo() != null
					&& device.getCellHouseholdInfo().getTHmCellSizeInfo().getRooms() != null) {
				Set<Room> roomSet = device.getCellHouseholdInfo().getTHmCellSizeInfo().getRooms();
				rooms = new ArrayList<Room>(roomSet);
			}
		}
		return rooms;
	}
	
	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}
	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public IOwnerUserService getOwnerUserService() {
		return (IOwnerUserService)getBean("ownerUserService");
	}

	@Override
	public boolean existHouseName(String cellId, String houseId,
			String houseName) {
		String jpql = "SELECT house.id FROM CellHouseholdInfo house WHERE house.THmBuildingCellInfo.id = ?1 AND house.name = ?2";
		List<String> houseIds = findIds(jpql, cellId, houseName);
		if(CollectionUtils.isEmpty(houseIds))
			return false;
		
		if(StringUtils.isEmpty(houseId) 
				|| houseIds.size() > 0)
			return true;
		
		return (!houseIds.contains(houseId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateHouse(CellHouseholdInfo house) {
		String jpql = "UPDATE CellHouseholdInfo ch SET ch.owner.userId = ?2, ch.checkInDate = ?3, ch.housingStatus = ?4 where ch.id = ?1";
		Object[] values = { house.getId(), house.getOwner().getUserId(), house.getCheckInDate(), house.getHousingStatus() };
		getDao().update(jpql, values);
	}

	@Override
	public boolean existHouseByCellId(String cellId) {
		String jpql = "SELECT COUNT(house) FROM CellHouseholdInfo house WHERE house.THmBuildingCellInfo.id = ?1";
		if(getTotalCount(jpql, cellId) > 0)
			return true;
		return false;
	}
	
	@Override
	public boolean existHouseBySizeId(String sizeId) {
		String jpql = "SELECT COUNT(house) FROM CellHouseholdInfo house WHERE house.THmCellSizeInfo.id = ?1";
		if(getTotalCount(jpql, sizeId) > 0)
			return true;
		return false;
	}
	
	
	@Override
	protected boolean allowToRemove(String id){
		if(existOwner(id)){
			logger.warn("****************编号为{}的房号已和业主绑定，不允许删除", id);
			return false;
		}
		if(existDevice(id)){
			logger.warn("****************编号为{}的房号已和设备绑定，不允许删除", id);
			return false;
		}
		if(existChargeData(id)){
			logger.warn("****************编号为{}的房号已和收费数据绑定，不允许删除", id);
			return false;
		}
		if(existRequestRepair(id)){
			logger.warn("****************编号为{}的房号已和物业保修绑定，不允许删除", id);
			return false;
		}
		return true;
	}
	
	
	//当前房号是否和设备关联
	private boolean existDevice(String houseId){
		IDeviceService deviceService = (IDeviceService)getBean(
				Constants.BEAN_NAME_DEVICE_SERVICE);
		return deviceService.isHaveRoomId(houseId);
	}
	
	//当前房号是否和收费数据关联
	private boolean existChargeData(String houseId){
		IChargeDataService chargeDataService = (IChargeDataService)getBean(
				Constants.BEAN_NAME_CHARGE_DATA_SERVICE);
		return chargeDataService.isExistForHouseHold(houseId);
	}
	
	//当前房号是否和报修关联
	private boolean existRequestRepair(String houseId) {
		IRequestRepairService repairService = (IRequestRepairService)getBean(
				Constants.BEAN_NAME_REQUEST_REPAIR_SERVICE);
		return repairService.isExistForHouseHold(houseId);
	}
	
	//当前房号是否和业主关联
	private boolean existOwner(String houseId) {
		IOwnerUserService ownerUserService = (IOwnerUserService)getBean(
				Constants.BEAN_NAME_OWNER_USER_SERVICE);
		return ownerUserService.getUserByHouseId(houseId) != null;
	}

	@Override
	public String getFullHouseNo(String id) {
		CellHouseholdInfo house = super.get(id);
		BuildingCellInfo cell = null;
		RegionBuildingInfo building = null;
		HousingDistrictRegionInfo region = null;
		HousingDistrictInfo district = null;
		
		if(house!=null){
			cell = house.getTHmBuildingCellInfo();
		}
		if(cell!=null){
			building = cell.getTHmRegionBuildingInfo();
		}
		if(building!=null){
			region = building.getTHmHousingDistrictRegionInfo();
		}
		if(region!=null){
			district = region.getHousingDistrictInfo();
		}
		
		StringBuilder sb = new StringBuilder();
		if(district!=null){
			sb.append(district.getCode());
		}
		if (region != null) {
			sb.append(region.getCode());

		}
		if (building != null) {
			sb.append(building.getCode());
		}
		if (cell != null) {
			sb.append(cell.getCode());

		}
		if (house != null) {
			sb.append(house.getCode());

		}
		return sb.toString();
	}

	@Override
	public String getFullHouseName(String id) {
		CellHouseholdInfo house = super.get(id);
		BuildingCellInfo cell = null;
		RegionBuildingInfo building = null;
		HousingDistrictRegionInfo region = null;
		HousingDistrictInfo district = null;
		
		if(house!=null){
			cell = house.getTHmBuildingCellInfo();
		}
		if(cell!=null){
			building = cell.getTHmRegionBuildingInfo();
		}
		if(building!=null){
			region = building.getTHmHousingDistrictRegionInfo();
		}
		if(region!=null){
			district = region.getHousingDistrictInfo();
		}
		
		StringBuilder sb = new StringBuilder();
		if(district!=null){
			sb.append(district.getName());
		}
		if (region != null) {
			sb.append(" ");
			sb.append(region.getName());
		}
		if (building != null) {
			sb.append(" ");
			sb.append(building.getName());
		}
		if (cell != null) {
			sb.append(" ");
			sb.append(cell.getName());
		}
		if (house != null) {
			sb.append(" ");
			sb.append(house.getName());
		}
		return sb.toString();
	}

	@Override
	public String getHouseIdByFullHouseNo(String fullHouseNo) {
		try{
			String areaNo = fullHouseNo.substring(0,4);			//1001    区号
			String regionNo = fullHouseNo.substring(4, 6);		//01         区域号
			String buildingNo = fullHouseNo.substring(6, 9);	//002      楼栋号
			String unitNo = fullHouseNo.substring(9, 11);		//01         单元号
			String houseCode = fullHouseNo.substring(11, 15);	//0203    房间号
			CellHouseholdInfo house = getByCode(areaNo, regionNo, buildingNo, unitNo, houseCode);
			if(house!=null){
				return house.getId();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
