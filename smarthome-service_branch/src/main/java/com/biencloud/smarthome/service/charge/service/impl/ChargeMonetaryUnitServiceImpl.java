package com.biencloud.smarthome.service.charge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 收费货币单位领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeMonetaryUnitService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeMonetaryUnitServiceImpl extends BaseService<ChargeMonetaryUnit, Long> implements IChargeMonetaryUnitService {

	@Override
	public Paging<ChargeMonetaryUnit> queryChargeMonetaryUnitForPaging(ChargeMonetaryUnit paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeMonetaryUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getCodeName()))
			appendCondition(hql, "dn.codeName LIKE :codeName", "codeName", "%" + paramsOb.getCodeName() + "%", params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public List<ChargeMonetaryUnit> queryChargeMonetaryUnitForList(ChargeMonetaryUnit paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeMonetaryUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	public ChargeMonetaryUnit queryChargeMonetaryUnitByParams(ChargeMonetaryUnit paramsOb) {
		ChargeMonetaryUnit result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeMonetaryUnit dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getCodeName()))
			appendCondition(hql, "dn.codeName = :codeName", "codeName", paramsOb.getCodeName(), params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		List<ChargeMonetaryUnit> list = findByNamedParams(queryString, params);
		if (list != null && !list.isEmpty())
			result = list.get(0);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		super.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		String hql = "select count(*) from ChargeType where chargeMonetaryUnit.id=" + entity.getId();
		Long result = this.getTotalCount(hql);
		if (result > 0)
			throw new IllegalArgumentException("chargeMonetaryUnit not delete");
		else
			super.remove(entity);
	}

	@Override
	public ChargeMonetaryUnit getChargeMonetaryUnit(String entityId) {
		return super.get(new Long(entityId));
	}

}
