package com.biencloud.smarthome.service.charge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeCalUnit;
import com.biencloud.smarthome.service.charge.service.IChargeCalUnitService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 收费单位领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeCalUnitService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeCalUnitServiceImpl extends BaseService<ChargeCalUnit, Long> implements IChargeCalUnitService {

	@Override
	public Paging<ChargeCalUnit> queryChargeCalUnitForPaging(ChargeCalUnit paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name LIKE :name", "name", "%" + paramsOb.getName() + "%", params);
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	private void createParams(Map<String, Object> params, StringBuilder hql, ChargeCalUnit paramsOb) {
		if (paramsOb != null) {
		}
	}

	@Override
	public List<ChargeCalUnit> queryChargeCalUnitForList(ChargeCalUnit paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	public ChargeCalUnit queryChargeCalUnitByParams(ChargeCalUnit paramsOb) {
		ChargeCalUnit result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name = :name", "name", paramsOb.getName(), params);
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		List<ChargeCalUnit> list = findByNamedParams(queryString, params);
		if (list != null && !list.isEmpty())
			result = list.get(0);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeCalUnit(ChargeCalUnit entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeCalUnit(ChargeCalUnit entity) {
		super.save(entity);
	}

	@Override
	public ChargeCalUnit getChargeCalUnit(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeCalUnit(ChargeCalUnit entity) {
		String hql = "select count(*) from ChargeType where chargeCalUnit.id=" + entity.getId();
		Long result = this.getTotalCount(hql);
		if (result > 0)
			throw new IllegalArgumentException("chargeCalUnit not delete");
		else
			super.remove(entity);
	}

}
