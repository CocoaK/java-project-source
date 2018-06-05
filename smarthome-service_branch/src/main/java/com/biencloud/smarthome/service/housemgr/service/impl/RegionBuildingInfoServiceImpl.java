package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.service.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;

/**
 * 楼宇 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class RegionBuildingInfoServiceImpl extends AbstractEstateService<RegionBuildingInfo, String> implements
		IRegionBuildingInfoService {
	
	private IPushDao pushDao;	
	private IDeviceService deviceService;
	
	public IPushDao getPushDao() {
		return pushDao;
	}
	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	
	@Override
	public Paging<RegionBuildingInfo> queryRegionBuildingInfosForPaging(
			RegionBuildingInfo condition, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM RegionBuildingInfo rb");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "rb");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(rb)");
		queryString = queryString + " order by createTime desc ";

		Paging<RegionBuildingInfo> buildingInfos = queryByNamedParamsForPaging(
				pageNum, pageSize, queryString, queryStringCount, params);

		// 由于房产管理级联关系太多, 需要改为手动来维护级联的数据, 实体属性标示为@Transient
		// 现在虽然是LAZY方式加载的, 但这里通过使用属性来手动加载了所有数据, 类似及时加载
		buildingInfos.toString();
		return buildingInfos;
	}

	private Map<String, Object> createParams(StringBuilder jpql,
			RegionBuildingInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 楼宇名
		if(StringUtils.isNotBlank(condition.getName()))
			appendCondition(jpql, "rb.name LIKE :name", "name", 
					"%" + condition.getName() + "%", params);

		// 所属区域ID
		if (condition.getTHmHousingDistrictRegionInfo() != null) {
			if(StringUtils.isNotBlank(condition.getTHmHousingDistrictRegionInfo().getId())) {
				appendCondition(jpql, "rb.THmHousingDistrictRegionInfo.id = :regionId", "regionId", 
						condition.getTHmHousingDistrictRegionInfo().getId(), params);
			}
			if(condition.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo() != null) {
				if (StringUtils.isNotBlank(condition.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId())) {
					appendCondition(jpql, "rb.THmHousingDistrictRegionInfo.housingDistrictInfo.id = :districtId", "districtId", 
							condition.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId(), params);
				}
			}
		}
		
		// 楼宇编号
		if(StringUtils.isNotBlank(condition.getCode())) {
			appendCondition(jpql, "rb.code = :code", "code", 
					condition.getCode(), params);
		}

		return params;
	}

	@Override
	public List<RegionBuildingInfo> getBuildings(RegionBuildingInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT rb FROM RegionBuildingInfo rb");

		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	public RegionBuildingInfo getByCode(String areaNo,String regionNo,String buildingCode) {
		RegionBuildingInfo building = null;
		if(areaNo!=null&&regionNo!=null&&buildingCode!=null)
		{
			String hql="from RegionBuildingInfo where code=?1 and THmHousingDistrictRegionInfo.code=?2 and THmHousingDistrictRegionInfo.housingDistrictInfo.code=?3";
			List<RegionBuildingInfo> list=super.find(hql, buildingCode,regionNo,areaNo);
			if(list!=null&&!list.isEmpty())
			{
				building=list.get(0);
			}
		}		
		return building;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RegionBuildingInfo saveOrUpdateRegionBuilding(RegionBuildingInfo building) {
		String buildingId = building.getId();
		RegionBuildingInfo oldBuilding = null;	
		//如果是修改楼宇则获取未修改前的楼宇信息
		if (StringUtils.isNotBlank(buildingId)){
			oldBuilding = getDao().get(buildingId);
			//如果是修改楼宇且楼宇坐标位置发生变化，则保存推送数据
			if (oldBuilding != null && !StringUtils.equals(
					building.getCoordinate(), oldBuilding.getCoordinate()))	{	
				//savePushData(oldBuilding, building.getCoordinate());
			}
		}
				
		getDao().saveOrUpdate(building);
		return building;
	}
	
	//保存推送数据
	private void savePushData(RegionBuildingInfo building, String newCoordinate) {
		HousingDistrictRegionInfo region = building.getTHmHousingDistrictRegionInfo();		
		HousingDistrictInfo district = region.getHousingDistrictInfo();
		List<Device> devices = getDeviceService().findAll(district.getCode(), region.getCode(), 
				building.getCode(), null, Constants.UNIT_DOOR_DEVICE, null);
		if(CollectionUtils.isEmpty(devices))
			return;
		
		for (Device device : devices) {
			Push push = new Push();
			push.setPushName("DeviceLocation");
			push.setPushKind(PushKindConstants.PUSH_DEVICE_LOCATION_KIND);
			push.setAddTime(new Date());
			push.setPushClientId(device.getDeviceCode());
			push.setPushContent(buildPushContent(
					district.getFloorPlan(), newCoordinate));
			getPushDao().save(push);
		}
		
	}
	
	// 构建推送内容
	private String buildPushContent(String downloadPath, String coordinate){
		StringBuilder pushContent = new StringBuilder();
		pushContent.append("downloadPath=");
		pushContent.append(downloadPath);
		pushContent.append(",coordinate=");
		pushContent.append(StringUtils.replace(coordinate, ",", "|"));
		pushContent.append(",changeTime=");
		pushContent.append(new Date().getTime());
		return pushContent.toString();
	}
	
	@Override
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId) {
		String hql = "FROM RegionBuildingInfo rb where rb.code = ?1 and rb.THmHousingDistrictRegionInfo.id = ?2";
		return isNotExist(hql, buildingCode, regionId);
	}

	@Override
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId) {
		String hql = "FROM RegionBuildingInfo rb where rb.id != ?1 and rb.code = ?2 and rb.THmHousingDistrictRegionInfo.id = ?3";
		return isNotExist(hql, buildingId, buildingCode, regionId);
	}

	@Override
	public boolean hasCell(String buildingId) {
		String hql = "select bc.id FROM BuildingCellInfo bc where bc.THmRegionBuildingInfo.id = ?1";
		return !isNotExist(hql, buildingId);
	}

	@Override
	public boolean existBuildingName(String regionId, 
			String buildingId, String buildingName) {
		String jpql = "SELECT building.id FROM RegionBuildingInfo building WHERE building.THmHousingDistrictRegionInfo.id = ?1 AND building.name = ?2";
		List<String> buildingIds = findIds(jpql, regionId, buildingName);
		if(CollectionUtils.isEmpty(buildingIds))
			return false;
		
		if(StringUtils.isEmpty(buildingId) 
				|| buildingIds.size() > 1)
			return true;
		
		return (!buildingIds.contains(buildingId));
	}

	@Override
	public boolean existBuildingByRegionId(String regionId) {
		String jpql = "SELECT COUNT(building) FROM RegionBuildingInfo building WHERE building.THmHousingDistrictRegionInfo.id = ?1";
		if(getTotalCount(jpql, regionId) > 0)
			return true;
		return false;
	}
	
	
	@Override
	protected boolean allowToRemove(String id){
		if(existCell(id)){
			logger.warn("****************编号为{}的楼宇已创建单元，不允许删除", id);
			return false;
		}
		if(existDevice(id)){
			logger.warn("****************编号为{}的楼宇已和设备绑定，不允许删除", id);
			return false;
		}
		if(existChargeData(id)){
			logger.warn("****************编号为{}的楼宇已和收费数据绑定，不允许删除", id);
			return false;
		}
		return true;
	}
	
	
	//当前楼宇是否和单元关联
	private boolean existCell(String buildingId){
		IBuildingCellInfoService cellService = (IBuildingCellInfoService)getBean(
				Constants.BEAN_NAME_CELL_SERVICE);
		return cellService.existCellByBuildingId(buildingId);
	}
	
	//当前楼宇是否和设备关联
	private boolean existDevice(String buildingId){
		IDeviceService deviceService = (IDeviceService)getBean(
				Constants.BEAN_NAME_DEVICE_SERVICE);
		return deviceService.isHaveBuilding(buildingId);
	}
	
	//当前楼宇是否和收费数据关联
	private boolean existChargeData(String buildingId){
		IChargeDataService chargeDataService = (IChargeDataService)getBean(
				Constants.BEAN_NAME_CHARGE_DATA_SERVICE);
		return chargeDataService.isExistForBuilding(buildingId);
	}
}
