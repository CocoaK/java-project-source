package com.biencloud.smarthome.service.charge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeCalMode;
import com.biencloud.smarthome.service.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 收费计算方式领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeCalModeService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeCalModeServiceImpl extends BaseService<ChargeCalMode, Long> implements IChargeCalModeService {

	@Override
	public Paging<ChargeCalMode> queryChargeCalModeForPaging(ChargeCalMode paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalMode dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name LIKE :name", "name", "%" + paramsOb.getName() + "%", params);
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	private void createParams(Map<String, Object> params, StringBuilder hql, ChargeCalMode paramsOb) {
		if (paramsOb != null) {
			if (paramsOb.getDistrictId() != null)
				appendCondition(hql, "dn.districtId = :districtId", "districtId", paramsOb.getDistrictId(), params);
		}
	}

	@Override
	public List<ChargeCalMode> queryChargeCalModeForList(ChargeCalMode paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalMode dn");
		Map<String, Object> params = new HashMap<String, Object>();
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	public ChargeCalMode queryChargeCalModeByParams(ChargeCalMode paramsOb) {
		ChargeCalMode result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeCalMode dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getName()))
			appendCondition(hql, "dn.name = :name", "name", paramsOb.getName(), params);
		createParams(params, hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		List<ChargeCalMode> list = findByNamedParams(queryString, params);
		if (list != null && !list.isEmpty())
			result = list.get(0);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeCalMode(ChargeCalMode entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeCalMode(ChargeCalMode entity) {
		super.save(entity);
	}

	@Override
	public ChargeCalMode getChargeCalMode(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeCalMode(ChargeCalMode entity) {
		String hql = "select count(*) from ChargeType where chargeCalMode.id=" + entity.getId();
		Long result = this.getTotalCount(hql);
		if (result > 0)
			throw new IllegalArgumentException("chargeCalMode not delete");
		else
			super.remove(entity);
	}

}
