package com.biencloud.smarthome.service.charge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeTypeResult;
import com.biencloud.smarthome.service.charge.service.IChargeTypeResultService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 收费项目结果领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeTypeResultService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeTypeResultServiceImpl extends BaseService<ChargeTypeResult, Long> implements IChargeTypeResultService {

	@Override
	public Paging<ChargeTypeResult> queryChargeTypeResultForPaging(ChargeTypeResult paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeTypeResult dn");
		Map<String, Object> params = new HashMap<String, Object>();
		/*
		 * if (StringUtils.isNotBlank(paramsOb.getChargeTypeResult()))
		 * appendCondition(hql, "dn.deviceNo LIKE :deviceNo", "deviceNo", "%" +
		 * paramsOb.getChargeTypeResult() + "%", params);
		 */
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public List<ChargeTypeResult> queryChargeTypeResultForList(ChargeTypeResult paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeTypeResult dn");
		Map<String, Object> params = new HashMap<String, Object>();
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeTypeResult(ChargeTypeResult entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeTypeResult(ChargeTypeResult entity) {
		super.save(entity);
	}

	@Override
	public ChargeTypeResult getChargeTypeResult(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeTypeResult(ChargeTypeResult entity) {
		super.remove(entity);
	}

}
