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
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.service.housemgr.service.IRegionBuildingInfoService;

/**
 * 区域 Service 实现类
 * 
 * @author jsun
 * @since 1.0 2012-5-17
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HousingDistrictRegionInfoServiceImpl extends AbstractEstateService<HousingDistrictRegionInfo, String> implements
		IHousingDistrictRegionInfoService {
	@Override
	public Paging<HousingDistrictRegionInfo> queryHousingDistrictRegionInfosForPaging(HousingDistrictRegionInfo condition, int pageNum,
			int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM HousingDistrictRegionInfo hdr");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "hdr");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(hdr)");
		queryString = queryString + " order by createTime desc ";

		Paging<HousingDistrictRegionInfo> regionInfos = queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount,
				params);

		// 由于房产管理级联关系太多, 需要改为手动来维护级联的数据, 实体属性标示为@Transient
		// 现在虽然是LAZY方式加载的, 但这里通过使用属性来手动加载了所有数据, 类似及时加载
		regionInfos.toString();
		return regionInfos;
	}

	/**
	 * 根据小区区域信息模型, 生成JPQL查询条件
	 * 
	 * @param jpql
	 * @param condition
	 * @return
	 */
	private Map<String, Object> createParams(StringBuilder jpql, HousingDistrictRegionInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (condition == null)
			return params;

		// 区域名
		if (StringUtils.isNotBlank(condition.getName()))
			appendCondition(jpql, "hdr.name LIKE :name", "name", "%" + condition.getName() + "%", params);

		// 所属小区ID
		if (condition.getHousingDistrictInfo() != null) {
			if (StringUtils.isNotBlank(condition.getHousingDistrictInfo().getId())) {
				appendCondition(jpql, "hdr.housingDistrictInfo.id = :districtId", "districtId", condition.getHousingDistrictInfo().getId(),
						params);
			}
		}

		// 区域编号
		if (StringUtils.isNotBlank(condition.getCode())) {
			appendCondition(jpql, "hdr.code = :code", "code", condition.getCode(), params);
		}

		return params;
	}

	@Override
	public HousingDistrictRegionInfo get(String entityId) {
		HousingDistrictRegionInfo region = super.get(entityId);
		region.toString();
		return region;
	}

	@Override
	public List<HousingDistrictRegionInfo> getRegions(HousingDistrictRegionInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT hdr FROM HousingDistrictRegionInfo hdr");

		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	public HousingDistrictRegionInfo getByCode(String areaNo,String regionCode) {
		HousingDistrictRegionInfo region = null;
		if(areaNo!=null&&regionCode!=null)
		{
			String hql="from HousingDistrictRegionInfo where code=?1 and housingDistrictInfo.code=?2";
			List<HousingDistrictRegionInfo> list=super.find(hql, regionCode,areaNo);
			if(list!=null&&!list.isEmpty())
			{
				region=list.get(0);
			}
		}		
		return region;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public HousingDistrictRegionInfo saveOrUpdateRegion(HousingDistrictRegionInfo region) {
		HousingDistrictInfo district = region.getHousingDistrictInfo();
		String regionId = region.getId();

		if (district == null) {
			return null;
		}

		if (StringUtils.isBlank(regionId)) { // 新增
			if (newCodeNotRepeat(region.getCode(), district.getId())) { // 没有重复CODE才新增
				save(region);
			} else {
				region = null;
			}
		} else if (StringUtils.isNotBlank(regionId)) { // 修改
			if (updateCodeNotRepeat(regionId, region.getCode(), district.getId())) { // 没有重复才修改
				update(region);
			} else {
				region = null;
			}
		}

		return region;
	}

	@Override
	public boolean newCodeNotRepeat(String regionCode, String districtId) {
		String hql = "FROM HousingDistrictRegionInfo hdr where hdr.code = ?1 and hdr.housingDistrictInfo.id = ?2";
		return isNotExist(hql, regionCode, districtId);
	}

	@Override
	public boolean updateCodeNotRepeat(String regionId, String regionCode, String districtId) {
		String hql = "FROM HousingDistrictRegionInfo hdr where hdr.id != ?1 and hdr.code = ?2 and hdr.housingDistrictInfo.id = ?3";
		return isNotExist(hql, regionId, regionCode, districtId);
	}

	@Override
	public boolean hasBuilding(String regionId) {
		String hql = "select rb.id FROM RegionBuildingInfo rb where rb.THmHousingDistrictRegionInfo.id = ?1";
		return !isNotExist(hql, regionId);
	}

	@Override
	public boolean existRegionName(String districtId, 
			String regionId, String regionName) {
		String jpql = "SELECT region.id FROM HousingDistrictRegionInfo region WHERE region.housingDistrictInfo.id = ?1 AND region.name = ?2";
		List<String> regionIds = findIds(jpql, districtId, regionName);
		if(CollectionUtils.isEmpty(regionIds))
			return false;
		
		if(StringUtils.isEmpty(regionId) 
				|| regionIds.size() > 1)
			return true;
		
		return (!regionIds.contains(regionId));
	}
	
	
	@Override
	protected boolean allowToRemove(String id) {
		if(existBuilding(id)){
			logger.warn("****************编号为{}的区域已创建楼宇，不允许删除", id);
			return false;
		}
		if(existDevice(id)){
			logger.warn("****************编号为{}的区域已和设备绑定，不允许删除", id);
			return false;
		}
		return true;
	}

	
	//当前区域是否和楼宇关联
	private boolean existBuilding(String regionId){
		IRegionBuildingInfoService buildingService = (IRegionBuildingInfoService)getBean(
				Constants.BEAN_NAME_BUILDING_SERVICE);
		return buildingService.existBuildingByRegionId(regionId);
	}
	
	//当前区域是否和设备关联
	private boolean existDevice(String regionId){
		IDeviceService deviceService = (IDeviceService)getBean(
				Constants.BEAN_NAME_DEVICE_SERVICE);
		return deviceService.isHaveArea(regionId);
	}
}
