package com.biencloud.smarthome.service.gate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;
import com.biencloud.smarthome.service.gate.service.IGateCardVisitService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 门卡刷卡管理领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IService
 * @see BaseService
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GateCardVisitServiceImpl extends BaseService<GateCardVisit, String> implements IGateCardVisitService {

	private ISysParamService sysParamService;
		
	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	
	@Override
	/** 
	 * districtId：必须作为查询条件
	 * cardNo：如果为空不作为查询条件，否则作为模糊查询条件
	 * ownerName：如果为空不作为查询条件，否则作为模糊查询条件
	 * beginTime：如果为Null不作为查询条件
	 * endTime：如果为Null不作为查询条件
	 */
	public Paging<GateCardVisit> queryGateCardVisitsForPaging(
			GateCardVisit gateCardVisit, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(jpql, gateCardVisit);
		jpql.append(" ORDER BY gcv.visitTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM GateCardVisit gcv");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "gcv");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(gcv)");
		
		Paging<GateCardVisit> paging = queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
		
		List<GateCardVisit> results = paging.getResults();
		if(CollectionUtils.isNotEmpty(results)){
			for (GateCardVisit gcv : results)
				setPicPath(gcv);
		}
		
		return paging;
	}

	
	
	@Override
	public GateCardVisit get(String entityId) {
		GateCardVisit gcv = getDao().get(entityId);
		setPicPath(gcv);
		return gcv;
	}
	
	@Override
	public List<GateCardVisit> queryAllGateCardVisits(GateCardVisit gateCardVisit) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, gateCardVisit);
		jpql.insert(0, "SELECT gcv FROM GateCardVisit gcv");
		return findByNamedParams(jpql.toString(), params);
	}
	
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,
			GateCardVisit gateCardVisit) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (gateCardVisit == null)
			return params;

		if (StringUtils.isNotBlank(gateCardVisit.getCardNo()))
			appendCondition(jpql, "gcv.cardNo LIKE :cardNo", "cardNo", "%"
					+ gateCardVisit.getCardNo() + "%", params);

		if (StringUtils.isNotBlank(gateCardVisit.getOwnerName()))
			appendCondition(jpql, "gcv.ownerName LIKE :ownerName", "ownerName",
					"%" + gateCardVisit.getOwnerName() + "%", params);

		if (StringUtils.isNotBlank(gateCardVisit.getDeviceCode()))
			appendCondition(jpql, "gcv.deviceCode = :deviceCode", "deviceCode", 
					gateCardVisit.getDeviceCode(), params);
		
		if (StringUtils.isNotBlank(gateCardVisit.getGateCardId()))
			appendCondition(jpql, "gcv.gateCardId = :gateCardId", "gateCardId", 
					gateCardVisit.getGateCardId(), params);
		
		appendCondition(jpql, "gcv.districtId = :districtId", "districtId", 
				gateCardVisit.getDistrictId(), params);
		
		appendCondition(jpql, "gcv.visitTime >= :beginTime", "beginTime",
				gateCardVisit.getBeginTime(), params);
		
		appendCondition(jpql, "gcv.visitTime <= :endTime", "endTime",
				gateCardVisit.getEndTime(), params);

		return params;
	}
	
	//设置抓拍图片的完整路径
	private void setPicPath(GateCardVisit gcv){
		if(gcv != null){
			String basePicPath = getSysParamService().getAppDownloadAbsoluteUrl();
			if(StringUtils.isNotBlank(gcv.getPicPath1()))
				gcv.setPicPath1(basePicPath + gcv.getPicPath1());
			if(StringUtils.isNotBlank(gcv.getPicPath2()))
				gcv.setPicPath2(basePicPath + gcv.getPicPath2());
			if(StringUtils.isNotBlank(gcv.getPicPath3()))
				gcv.setPicPath3(basePicPath + gcv.getPicPath3());
		}
	}
}
