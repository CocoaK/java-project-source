package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellSizeInfoService;

/**
 * 户型 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-23
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CellSizeInfoServiceImpl extends AbstractEstateService<CellSizeInfo, String>
		implements ICellSizeInfoService {
	
	private ICellHouseholdInfoService cellHouseholdInfoService;
	
	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	
	@Override
	public List<CellSizeInfo> findCellSizeInfo(CellSizeInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT cs FROM CellSizeInfo cs");

		List<CellSizeInfo> sizeList = findByNamedParams(jpql.toString(), params);
		return sizeList;
	}

	@Override
	public boolean existCellSizeByCellId(String cellId) {
		String jpql = "SELECT COUNT(cs) FROM CellSizeInfo cs WHERE cs.THmBuildingCellInfo.id = ?1";
		if(getTotalCount(jpql, cellId) > 0)
			return true;
		return false;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CellSizeInfo save_update(CellSizeInfo size) {
		size.setCustomFlag(Constants.SIZE_CUSTOM_FLAG_NO);
		return saveCellSize(size);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String saveCustomSize(CellSizeInfo size, String houseId) {
		CellSizeInfo oldSize = get(size.getId());
		//是否首次自定义户型
		boolean firstCustom = Constants.SIZE_CUSTOM_FLAG_NO.equals(oldSize.getCustomFlag());
		saveCustomSize(firstCustom, size, oldSize);
		
		//将当前房间和新的户型关联
		updateHouseSize(houseId, size.getId());
		return size.getId();
	}
	
	
	@Override
	protected boolean allowToRemove(String id) {
		if(existHouse(id)){
			logger.warn("****************编号为{}的户型已和房号绑定，不允许删除", id);
			return false;
		}
		return true;
	}

	
	//当前楼宇是否和单元关联
	private boolean existHouse(String sizeId){
		return cellHouseholdInfoService.existHouseBySizeId(sizeId);
	}
	
	private Map<String, Object> createParams(StringBuilder jpql, CellSizeInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 所属单元ID
		if (condition.getTHmBuildingCellInfo() != null) {
			if(StringUtils.isNotBlank(condition.getTHmBuildingCellInfo().getId())) {
				appendCondition(jpql, "cs.THmBuildingCellInfo.id = :cellId", "cellId",
						condition.getTHmBuildingCellInfo().getId(), params);
			}
		}
		
		appendCondition(jpql, "cs.customFlag = :customFlag", "customFlag",
				Constants.SIZE_CUSTOM_FLAG_NO, params);
		
		return params;
	}
	
	private CellSizeInfo saveCellSize(CellSizeInfo size) {		
		getDao().save(size);
		updateSizeId(size.getId(), size.getRooms());		
		return size;
	}
	
	private void saveCustomSize(boolean firstCustom, 
			CellSizeInfo newSize, CellSizeInfo oldSize){
		if(firstCustom){
			newSize.setId(null);//新增自定义户型
			newSize.setPlan(oldSize.getPlan());
			newSize.setRoom(oldSize.getRoom());
			newSize.setHall(oldSize.getHall());
			newSize.setCustomFlag(Constants.SIZE_CUSTOM_FLAG_YES);
			newSize.setTHmBuildingCellInfo(oldSize.getTHmBuildingCellInfo());
			getDao().save(newSize);
			updateSizeId(newSize.getId(), newSize.getRooms());
		}else{
			List<Room> orderedRooms = sortRooms(newSize.getRooms());
			List<Room> orderedOldRooms = sortRooms(oldSize.getRooms());
			int currRoomsSize = orderedRooms.size();
			int oldRoomsSize = orderedOldRooms.size();
			if(currRoomsSize == oldRoomsSize){
				updateRooms(orderedRooms, orderedOldRooms);
			}else if(currRoomsSize > oldRoomsSize){
				updateRooms(orderedRooms.subList(0, oldRoomsSize), orderedOldRooms);
				for (int index = oldRoomsSize; index < currRoomsSize; index++){
					orderedRooms.get(index).setSizeId(oldSize.getId());
					orderedOldRooms.add(orderedRooms.get(index));
				}
			}else{
				updateRooms(orderedRooms, orderedOldRooms);
				orderedOldRooms = orderedOldRooms.subList(0, currRoomsSize);
			}
			Set<Room> currRooms = oldSize.getRooms();
			currRooms.clear();
			currRooms.addAll(orderedOldRooms);
		}
	}
	
	private List<Room> sortRooms(Set<Room> rooms){
		List<Room> orderedRooms = new ArrayList<Room>();
		if(CollectionUtils.isEmpty(rooms))
			return orderedRooms;
		orderedRooms.addAll(rooms);
		Collections.sort(orderedRooms, 
				new Comparator<Room>() {
					@Override
					public int compare(Room r1, Room r2) {
						return (r1.getOrderIndex() - r2.getOrderIndex());
					}
				});
		return orderedRooms;
	}
	
//	private void saveRooms(List<Room> rooms, String sizeId){
//		if(CollectionUtils.isEmpty(rooms))
//			return;
//		
//		for (Room room : rooms) {
//			room.setSizeId(sizeId);
//			getDao().saveObject(room);
//		}
//	}
	
	private void updateSizeId(String sizeId, Set<Room> rooms){
		if(CollectionUtils.isNotEmpty(rooms)){
			for (Room room : rooms)
				room.setSizeId(sizeId);
		}
	}
	
	private void updateRooms(List<Room> newRooms, List<Room> oldRooms){
		if(CollectionUtils.isEmpty(newRooms) 
				|| CollectionUtils.isEmpty(oldRooms) 
				|| newRooms.size() > oldRooms.size())
			return;
		
		for (int index = 0, size = newRooms.size(); index < size; index++) {
			updateRoom(newRooms.get(index), oldRooms.get(index));			
		}
	}
	
	private void updateRoom(Room newRoom, Room oldRoom){
		oldRoom.setName(newRoom.getName());
		oldRoom.setPlan(newRoom.getPlan());
	}
	
//	private void removeRooms(List<Room> rooms){
//		if(CollectionUtils.isEmpty(rooms))
//			return;
//		
//		for (Room room : rooms)
//			getDao().removeObject(room);
//	}
	
//	private void removeCustomSize(String sizeId){
//		CellSizeInfo size = get(sizeId);
//		if(size != null && Constants.SIZE_CUSTOM_FLAG_YES.equals(size.getCustomFlag()))
//			getDao().remove(size);
//	}
	
	private void updateHouseSize(String houseId, String sizeId){
		getCellHouseholdInfoService().updateHouseSizeId(houseId, sizeId);
	}
}
