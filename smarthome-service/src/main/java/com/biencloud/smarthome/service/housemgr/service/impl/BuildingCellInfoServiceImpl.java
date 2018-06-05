package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellSizeInfoService;

/**
 * 单元 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class BuildingCellInfoServiceImpl extends AbstractEstateService<BuildingCellInfo, String> implements
		IBuildingCellInfoService {
	@Override
	public Paging<BuildingCellInfo> queryBuildingCellInfosForPaging(
			BuildingCellInfo condition, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM BuildingCellInfo bc");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "bc");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(bc)");
		queryString = queryString + " order by bc.THmRegionBuildingInfo.code, bc.code";

		Paging<BuildingCellInfo> cellInfos = queryByNamedParamsForPaging(
				pageNum, pageSize, queryString, queryStringCount, params);

		// 由于房产管理级联关系太多, 需要改为手动来维护级联的数据, 实体属性标示为@Transient
		// 现在虽然是LAZY方式加载的, 但这里通过使用属性来手动加载了所有数据, 类似及时加载
		cellInfos.toString();
		return cellInfos;
	}

	private Map<String, Object> createParams(StringBuilder jpql,
			BuildingCellInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 楼宇ID
		if (condition.getTHmRegionBuildingInfo() != null) {
			if(StringUtils.isNotBlank(condition.getTHmRegionBuildingInfo().getId())) {
				appendCondition(jpql, "bc.THmRegionBuildingInfo.id = :buildingId", "buildingId", 
						condition.getTHmRegionBuildingInfo().getId(), params);
			}
			
			// 所属区域
			if (condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo() != null) {
				// 区域ID
				if(StringUtils.isNotBlank(condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getId())) {
					appendCondition(jpql, "bc.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.id = :regionId", "regionId", 
							condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getId(), params);
				}

				// 所属小区
				if (condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo() != null) {
					// 小区ID
					if (StringUtils.isNotBlank(condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId())) {
						appendCondition(jpql, "bc.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.id = :districtId", "districtId", 
								condition.getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId(), params);
					}					
				}
			}						
		}

		// 单元名称
		if(StringUtils.isNotBlank(condition.getName()))
			appendCondition(jpql, "bc.name LIKE :name", "name", 
					"%" + condition.getName() + "%", params);

		// 单元编号
		if(StringUtils.isNotBlank(condition.getCode())) {
			appendCondition(jpql, "bc.code = :code", "code", 
					condition.getCode(), params);
		}

		return params;
	}

	@Override
	public BuildingCellInfo getByCode(String areaNo,String regionNo,String buildingNo,String cellCode) {
		BuildingCellInfo unit = null;
		if(areaNo!=null&&regionNo!=null&&buildingNo!=null&&cellCode!=null)
		{
			String hql="from BuildingCellInfo where code=?1 and THmRegionBuildingInfo.code=?2 and THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code=?3 and THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code=?4";
		    List<BuildingCellInfo> list=super.find(hql, cellCode,buildingNo,regionNo,areaNo);
		    if(list!=null&&!list.isEmpty())
		    {
		    	unit=list.get(0);
		    }
		}		
		return unit;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BuildingCellInfo saveOrUpdateCell(BuildingCellInfo cell) {
		RegionBuildingInfo building = cell.getTHmRegionBuildingInfo();
		String cellId = cell.getId();

		if (building == null) {
			return null;
		}

		if (StringUtils.isBlank(cellId)) { // 新增
			if (newCodeNotRepeat(cell.getCode(), building.getId())) { // 没有重复CODE才新增
				save(cell);
			} else {
				cell = null;
			}
		} else if (StringUtils.isNotBlank(cellId)) { // 修改
			if (updateCodeNotRepeat(cellId, cell.getCode(), building.getId())) { // 没有重复才修改
				update(cell);
			} else {
				cell = null;
			}
		}

		return cell;
	}

	@Override
	public boolean newCodeNotRepeat(String cellCode, String buildingId) {
		String hql = "FROM BuildingCellInfo bc where bc.code = ?1 and bc.THmRegionBuildingInfo.id = ?2";
		return isNotExist(hql, cellCode, buildingId);
	}

	@Override
	public boolean updateCodeNotRepeat(String cellId, String cellCode, String buildingId) {
		String hql = "FROM BuildingCellInfo bc where bc.id != ?1 and bc.code = ?2 and bc.THmRegionBuildingInfo.id = ?3";
		return isNotExist(hql, cellId, cellCode, buildingId);
	}

	@Override
	public boolean hasHouse(String cellId) {
		String hql = "select ch.id FROM CellHouseholdInfo ch where ch.THmBuildingCellInfo.id = ?1";
		return !isNotExist(hql, cellId);
	}

	@Override
	public boolean existCellName(String buildingId, String cellId,
			String cellName) {
		String jpql = "SELECT cell.id FROM BuildingCellInfo cell WHERE cell.THmRegionBuildingInfo.id = ?1 AND cell.name = ?2";
		List<String> cellIds = findIds(jpql, buildingId, cellName);
		if(CollectionUtils.isEmpty(cellIds))
			return false;
		
		if(StringUtils.isEmpty(cellId) 
				|| cellIds.size() > 1)
			return true;
		
		return (!cellIds.contains(cellId));
	}

	@Override
	public boolean existCellByBuildingId(String buildingId) {
		String jpql = "SELECT COUNT(cell) FROM BuildingCellInfo cell WHERE cell.THmRegionBuildingInfo.id = ?1";
		if(getTotalCount(jpql, buildingId) > 0)
			return true;
		return false;
	}
	
	
	@Override
	protected boolean allowToRemove(String id){
		if(existCellSize(id)){
			logger.warn("****************编号为{}的单元已创建户型，不允许删除", id);
			return false;
		}
		if(existHouse(id)){
			logger.warn("****************编号为{}的单元已创建房号，不允许删除", id);
			return false;
		}
		if(existDevice(id)){
			logger.warn("****************编号为{}的单元已和设备绑定，不允许删除", id);
			return false;
		}
		return true;
	}
	
	
	//当前楼宇是否和单元关联
	private boolean existCellSize(String cellId){
		ICellSizeInfoService cellSizeService = (ICellSizeInfoService)getBean(
				Constants.BEAN_NAME_CELL_SIZE_SERVICE);
		return cellSizeService.existCellSizeByCellId(cellId);
	}
	
	//当前单元是否和房号关联
	private boolean existHouse(String cellId) {
		ICellHouseholdInfoService houseService = (ICellHouseholdInfoService)getBean(
				Constants.BEAN_NAME_HOUSE_SERVICE);
		return houseService.existHouseByCellId(cellId);
	}
	
	//当前单元是否和设备关联
	private boolean existDevice(String cellId){
		IDeviceService deviceService = (IDeviceService)getBean(
				Constants.BEAN_NAME_DEVICE_SERVICE);
		return deviceService.isHaveUnit(cellId);
	}
}
