package com.biencloud.smarthome.service.charge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.charge.service.IChargeTypeService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;

/**
 * 收费项目领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeTypeService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeTypeServiceImpl extends BaseService<ChargeType, Long> implements IChargeTypeService {

	private ICellHouseholdInfoService cellHouseholdInfoService;

	@Override
	public Paging<ChargeType> queryChargeTypeForPaging(ChargeType paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeType dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name LIKE :name", "name", "%" + paramsOb.getName() + "%", params);
		appendCondition(hql, "dn.housingDistrictInfo.id = :housingDistrictInfoId", "housingDistrictInfoId", paramsOb.getHousingDistrictInfo().getId(), params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public ChargeType queryChargeTypeByParams(ChargeType paramsOb) {
		ChargeType result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeType dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name = :name", "name", paramsOb.getName(), params);
		if (paramsOb.getHousingDistrictInfo() != null && paramsOb.getHousingDistrictInfo().getId() != null)
			appendCondition(hql, "dn.housingDistrictInfo.id = :housingDistrictInfoId", "housingDistrictInfoId", paramsOb.getHousingDistrictInfo().getId(), params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		List<ChargeType> list = findByNamedParams(queryString, params);
		if (list != null && !list.isEmpty())
			result = list.get(0);
		return result;
	}

	@Override
	public List<ChargeType> queryChargeTypeForList(ChargeType paramsOb) {
		CellHouseholdInfo chiOb = cellHouseholdInfoService.get(paramsOb.getRemark());
		if (chiOb == null || chiOb.getChargeTypes().isEmpty())
			return null;
		List<ChargeType> cyList = chiOb.getChargeTypes();
		List<Long> ids = new ArrayList<Long>();
		if(cyList!=null){
			for (int i = 0; i < cyList.size(); i++) {
				ChargeType ctOb = cyList.get(i);
				ids.add(ctOb.getId());
			}
		}
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeType dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (ids.size() > 0)
			appendCondition(hql, "dn.id IN :id", "id", ids, params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeType(ChargeType entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeType(ChargeType entity) {
		super.save(entity);
	}

	@Override
	public ChargeType getChargeType(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeType(ChargeType entity) {
		super.remove(entity);
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

}
