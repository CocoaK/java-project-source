package com.biencloud.smarthome.service.gate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.IdCardVisit;
import com.biencloud.smarthome.service.gate.service.IIdCardVisitService;

/**
 * 访客身份证刷卡管理领域服务实现类。
 * 
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class IdCardVisitServiceImpl extends BaseService<IdCardVisit, String>
		implements IIdCardVisitService {

	@Override
	/** 
	 * districtId：必须作为查询条件
	 * idCard：如果为空不作为查询条件，否则作为模糊查询条件
	 * visitorName：如果为空不作为查询条件，否则作为模糊查询条件
	 * gender：如果为空不作为查询条件
	 * beginTime：如果为Null不作为查询条件
	 * endTime：如果为Null不作为查询条件
	 */
	public Paging<IdCardVisit> queryIdCardVisitsForPaging(
			IdCardVisit idCardVisit, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(jpql, idCardVisit);
		jpql.append(" ORDER BY icv.visitTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM IdCardVisit icv");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "icv");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(icv)");
		
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}

	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,
			IdCardVisit idCardVisit) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (idCardVisit == null)
			return params;
		
		if (StringUtils.isNotBlank(idCardVisit.getVisitId()))
			appendCondition(jpql, "icv.visitId = :visitId", "visitId",
					idCardVisit.getVisitId(), params);
		
		if (StringUtils.isNotBlank(idCardVisit.getIdCard()))
			appendCondition(jpql, "icv.idCard LIKE :idCard", "idCard", "%"
					+ idCardVisit.getIdCard() + "%", params);

		if (StringUtils.isNotBlank(idCardVisit.getVisitorName()))
			appendCondition(jpql, "icv.visitorName LIKE :visitorName", "visitorName",
					"%" + idCardVisit.getVisitorName() + "%", params);

		appendCondition(jpql, "icv.districtId = :districtId", "districtId", 
				idCardVisit.getDistrictId(), params);
		
		appendCondition(jpql, "icv.gender = :gender", "gender",
				idCardVisit.getGender(), params);
		
		appendCondition(jpql, "icv.visitTime >= :beginTime", "beginTime",
				idCardVisit.getBeginTime(), params);

		appendCondition(jpql, "icv.visitTime <= :endTime", "endTime",
				idCardVisit.getEndTime(), params);

		return params;
	}

	@Override
	public List<IdCardVisit> queryIdCardVisits(IdCardVisit idCardVisit) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, idCardVisit);
		jpql.insert(0, "SELECT icv FROM IdCardVisit icv");
		return findByNamedParams(jpql.toString(), params);
	}
}
