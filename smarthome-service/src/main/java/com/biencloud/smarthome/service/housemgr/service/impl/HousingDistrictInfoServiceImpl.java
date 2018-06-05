/*
 * Copyright 
 */

package com.biencloud.smarthome.service.housemgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictInfoService;

/**
 * 小区 Service 实现类
 * 
 * @author jsun
 * @version 1.0 2012-5-12
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HousingDistrictInfoServiceImpl extends BaseService<HousingDistrictInfo, String> implements IHousingDistrictInfoService {
	@Override
	public Paging<HousingDistrictInfo> queryHousingDistrictInfosForPaging(HousingDistrictInfo condition, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM HousingDistrictInfo hd");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "hd");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(hd)");
		queryString = queryString + " order by createTime desc ";

		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	/**
	 * 根据小区信息模型, 生成JPQL查询条件
	 * 
	 * @param jpql
	 * @param condition
	 * @return
	 */
	private Map<String, Object> createParams(StringBuilder jpql, HousingDistrictInfo condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (condition == null)
			return params;

		// 小区名
		if (StringUtils.isNotBlank(condition.getName())) {
			appendCondition(jpql, "hd.name LIKE :name", "name", "%" + condition.getName() + "%", params);
		}

		// 小区编号
		if (StringUtils.isNotBlank(condition.getCode())) {
			appendCondition(jpql, "hd.code = :code", "code", condition.getCode(), params);
		}

		// 小区ID
		if (StringUtils.isNotBlank(condition.getId())) {
			appendCondition(jpql, "hd.id = :id", "id", condition.getId(), params);
		}

		// 组织编码
		if (StringUtils.isNotBlank(condition.getGroupId())) {
			appendCondition(jpql, "hd.groupId = :groupId", "groupId", condition.getGroupId(), params);
		}
		
		// 物业公司
		if (condition!=null && condition.getPropertyCompanyInfo()!=null) {
			appendCondition(jpql, "hd.propertyCompanyInfo.id = :companyId", "companyId", condition.getPropertyCompanyInfo().getId(), params);
		}			

		return params;
	}

	@Override
	public List<HousingDistrictInfo> getDistricts(HousingDistrictInfo condition) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT hd FROM HousingDistrictInfo hd");

		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(HousingDistrictInfo district) {
		super.save(district);
		// 根据自增ID来设置小区初始的4位编码
		String code = district.getId();
		if(StringUtils.length(code) < 4){
			code = "1" + StringUtils.leftPad(code, 3, "0");
		}
		district.setCode(code);

		// 如果小区新增的时候没有对应的物业公司, 则新增一个默认信息都为空物业公司与小区匹配, 让用户自己去修改相关信息
		if (district.getPropertyCompanyInfo() == null) {
			PropertyCompanyInfo propertyCompanyInfo = new PropertyCompanyInfo();
			propertyCompanyInfo.setName("-");

			district.setPropertyCompanyInfo(propertyCompanyInfo);
		}

		super.update(district);
	}

	@Override
	public HousingDistrictInfo getByCode(String areaNo) {
		HousingDistrictInfo area = null;
		if (areaNo != null) {
			String hql = "from HousingDistrictInfo where code=?";
			List<HousingDistrictInfo> list = super.find(hql, areaNo);
			if (list != null && !list.isEmpty()) {
				area = list.get(0);
			}
		}
		return area;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void upateHousingDistrictInfoName(Long groupNo, String groupName) {
		String jpql = "UPDATE HousingDistrictInfo hd SET hd.name = ?1 WHERE hd.groupId = " + groupNo;
		Object[] values = { groupName };
		update(jpql.toString(), values);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(HousingDistrictInfo district) {
		StringBuilder jpql = new StringBuilder("UPDATE HousingDistrictInfo hd SET hd.code = ?1, hd.propertyCompanyInfo = ?2, hd.propertyCompanyAddress = ?3, hd.district = ?4, hd.constructionArea = ?5, hd.developer = ?6");
		Object[] values = { district.getCode(), district.getPropertyCompanyInfo(),
				district.getPropertyCompanyAddress(), district.getDistrict(), 
				district.getConstructionArea(), district.getDeveloper(), district.getId() };
		if(StringUtils.isNotBlank(district.getFloorPlan())){
			jpql.append(", hd.floorPlan = ?8");
			values = ArrayUtils.add(values, district.getFloorPlan());
		}
		jpql.append(" where hd.id = ?7");
		update(jpql.toString(), values);
	}

	@Override
	public long getDistrictCount() {
		return getTotalCount("SELECT COUNT(hd) FROM HousingDistrictInfo hd");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDistrictPropertyCompanyId(String districtId, int propertyCompanyId) {
		StringBuilder jpql = new StringBuilder("UPDATE HousingDistrictInfo hd SET hd.propertyCompanyInfo.id = ?1 where hd.id = ?2");
		Object[] values = { propertyCompanyId, districtId };

		update(jpql.toString(), values);
	}

	@Override
	public String getDistrictIdByGroupNo(Long groupNo) {
		HousingDistrictInfo condition = new HousingDistrictInfo();
		condition.setGroupId(groupNo.toString());

		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition);

		jpql.insert(0, "SELECT hd.id FROM HousingDistrictInfo hd");

		List<Object> result = getDao().findObjectsByNamedParams(jpql.toString(), params);
		return result.size() > 0 ? result.get(0).toString() : null;
	}

	@Override
	public boolean hasRegion(String districtId) {
		String hql = "select hdr.id FROM HousingDistrictRegionInfo hdr where hdr.housingDistrictInfo.id = ?1";
		return !isNotExist(hql, districtId);
	}

	@Override
	public List<String> queryDistrictIds(List<String> groupIds) {
		if(CollectionUtils.isEmpty(groupIds))
			return new ArrayList<String>();
		
		String jpql = "SELECT hd.id FROM HousingDistrictInfo hd WHERE hd.groupId IN (:groupIds)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupIds", groupIds);
		
		return findIdsByNamedParams(jpql, params);
	}
}
