package com.biencloud.smarthome.service.charge.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeStatitics;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 收费数据领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeDataService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeDataServiceImpl extends BaseService<ChargeData, Long> implements IChargeDataService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Paging<ChargeData> queryChargeDataForPaging(ChargeData paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeData dn ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getCellHouseholdInfo() != null && StringUtils.isNotBlank(paramsOb.getCellHouseholdInfo().getName()))
			appendCondition(hql, "dn.cellHouseholdInfo.name LIKE :cellHousename", "cellHousename", "%" + paramsOb.getCellHouseholdInfo().getName() + "%", params);
		if (paramsOb.getRegionBuildingInfo() != null && StringUtils.isNotBlank(paramsOb.getRegionBuildingInfo().getName()))
			appendCondition(hql, "dn.regionBuildingInfo.name LIKE :regionBuildingname", "regionBuildingname", "%" + paramsOb.getRegionBuildingInfo().getName() + "%", params);
		if (StringUtils.isNotBlank(paramsOb.getOwnerName()))
			appendCondition(hql, "dn.ownerName LIKE :ownerName", "ownerName", "%" + paramsOb.getOwnerName() + "%", params);
		if (StringUtils.isNotBlank(paramsOb.getChargeTime()))
			appendCondition(hql, "dn.chargeTime = :chargeTime", "chargeTime", paramsOb.getChargeTime(), params);
		if (StringUtils.isNotBlank(paramsOb.getIsproductDetail()))
			appendCondition(hql, "dn.isproductDetail = :isproductDetail", "isproductDetail", paramsOb.getIsproductDetail(), params);
		if (paramsOb.getCreateStartTime() != null)
			appendCondition(hql, "dn.createTime >= :startCreateTime", "startCreateTime", paramsOb.getCreateStartTime(), params);
		if (paramsOb.getCreateEndTime() != null)
			appendCondition(hql, "dn.createTime <= :endCreateTime", "endCreateTime", paramsOb.getCreateEndTime(), params);
		appendCondition(hql, "dn.housingDistrictInfo.id = :housingDistrictInfoId", "housingDistrictInfoId", paramsOb.getHousingDistrictInfo().getId(), params);
		hql.append(" order by createTime desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public boolean isExistForBuilding(String buildingId) {
		boolean result = false;
		String sql = "select count(*) from ChargeData dn where regionBuildingInfo.id =" + buildingId;
		long count = getTotalCount(sql);
		if (count > 0)
			result = true;
		return result;
	}

	@Override
	public boolean isExistForHouseHold(String houseId) {
		boolean result = false;
		String sql = "select count(*) from ChargeData dn where cellHouseholdInfo.id =" + houseId;
		long count = getTotalCount(sql);
		if (count > 0)
			result = true;
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ChargeStatitics> statisticsCharge(ChargeData ob) {
		StringBuilder varname1 = new StringBuilder();
		varname1.append("SELECT (SELECT name ");
		varname1.append("        FROM   t_hm_region_building_info ");
		varname1.append("        WHERE  id = tota.homeid), ");
		varname1.append("       tota.chargetypename, ");
		varname1.append("       tota.tal, ");
		varname1.append("       actal.atal, ");
		varname1.append("       tota.talmoney, ");
		varname1.append("       actal.atalmoney, ");
		varname1.append("       (tota.talmoney ");
		varname1.append("          - actal.atalmoney), ");
		varname1.append("       tota.monetaryUnit, ");
		varname1.append("       Concat(TRUNCATE(((actal.atalmoney ");
		varname1.append("                           / tota.talmoney) ");
		varname1.append("                          * 100),2),'%'), ");
		varname1.append("       Concat(TRUNCATE(((actal.atal ");
		varname1.append("                           / tota.tal) ");
		varname1.append("                          * 100),2),'%'), ");
		varname1.append("       tota.charge_time ");
		varname1.append("FROM   (SELECT   cd.homeid, ");
		varname1.append("                 cd.comId, ");
		varname1.append("                 cd.charge_time, ");
		varname1.append("                 ctr.chargetypename, ");
		varname1.append("                 cd.monetaryUnit, ");
		varname1.append("                 COUNT(ctr.chargetypename) AS tal, ");
		varname1.append("                 TRUNCATE(SUM(ctr.play_money),2) AS talmoney ");
		varname1.append("        FROM     t_charge_type_result ctr ");
		varname1.append("                 INNER JOIN t_charge_data cd ");
		varname1.append("                   ON ctr.chargedataid = cd.id ");
		varname1.append("        GROUP BY cd.homeid,ctr.chargetypename) tota, ");
		varname1.append("       (SELECT   cd.homeid, ");
		varname1.append("                 cd.comId, ");
		varname1.append("                 ctr.chargetypename, ");
		varname1.append("                 cd.monetaryUnit, ");
		varname1.append("                 COUNT(ctr.chargetypename) AS atal, ");
		varname1.append("                 TRUNCATE(SUM(ctr.play_money),2) AS atalmoney ");
		varname1.append("        FROM     t_charge_type_result ctr ");
		varname1.append("                 INNER JOIN t_charge_data cd ");
		varname1.append("                   ON ctr.chargedataid = cd.id ");
		varname1.append("                 INNER JOIN t_charge_detail detail ");
		varname1.append("                   ON detail.id = ctr.chargedetailid ");
		varname1.append("                      AND detail.charge_time IS NOT NULL ");
		varname1.append("        GROUP BY cd.homeid,ctr.chargetypename) actal ");
		varname1.append("WHERE  tota.homeid = actal.homeid ");
		varname1.append("       AND tota.chargetypename = actal.chargetypename ");
		if (StringUtils.isNotBlank(ob.getChargeTime()))
			varname1.append("       AND tota.charge_time <= '" + ob.getChargeTime() + "' ");
		if (StringUtils.isNotBlank(ob.getMonetaryUnit()))
			varname1.append("       AND tota.charge_time >= '" + ob.getMonetaryUnit() + "' ");
		if (ob.getRegionBuildingInfo() != null && ob.getRegionBuildingInfo().getId() != null)
			varname1.append("       AND tota.homeid = " + ob.getRegionBuildingInfo().getId());
		if (ob.getHousingDistrictInfo() != null && ob.getHousingDistrictInfo().getId() != null)
			varname1.append("       AND tota.comId = " + ob.getHousingDistrictInfo().getId());
		List result = getDao().excuteSql(varname1.toString());
		List<ChargeStatitics> target = new ArrayList<ChargeStatitics>();
		if(result!=null){
			for (int i = 0; i < result.size(); i++) {
				ChargeStatitics csOb = new ChargeStatitics();
				Object[] rsOb = (Object[]) result.get(i);
				csOb.setBuildingName((String) rsOb[0]);
				csOb.setTypeName((String) rsOb[1]);
				BigInteger totalCustomer = (BigInteger) rsOb[2];
				csOb.setTotalCustomer(totalCustomer.intValue());
				csOb.setAcTotalCustomer(((BigInteger) rsOb[3]).intValue());
				csOb.setTotalMoney((Double) rsOb[4]);
				csOb.setAcTotalMoney((Double) rsOb[5]);
				csOb.setFeeTotalMoney((Double) rsOb[6]);
				csOb.setMonetaryUnit((String) rsOb[7]);
				csOb.setMoneyPercent((String) rsOb[8]);
				csOb.setCustomerPercent((String) rsOb[9]);
				csOb.setChargetTime((String) rsOb[10]);
				target.add(csOb);
			}
		}
		return target;
	}

	@Override
	public List<ChargeData> queryChargeDataForList(ChargeData paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeData dn");
		Map<String, Object> params = createParams(hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql, ChargeData paramsOb) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb == null)
			return params;
		if (StringUtils.isNotBlank(paramsOb.getIsproductDetail()))
			appendCondition(jpql, "dn.isproductDetail = :isproductDetail", "isproductDetail", "" + paramsOb.getIsproductDetail() + "", params);
		if (paramsOb.getCellHouseholdInfo() != null && StringUtils.isNotBlank(paramsOb.getCellHouseholdInfo().getId()))
			appendCondition(jpql, "dn.cellHouseholdInfo.id = :cellHouseholdInfoId", "cellHouseholdInfoId", paramsOb.getCellHouseholdInfo().getId(), params);
		if (StringUtils.isNotBlank(paramsOb.getChargeTime()))
			appendCondition(jpql, "dn.chargeTime = :chargeTime", "chargeTime", "" + paramsOb.getChargeTime() + "", params);

		return params;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeData(ChargeData entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeData(ChargeData entity) {
		super.save(entity);
	}

	@Override
	public ChargeData getChargeData(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeData(ChargeData entity) {
		super.remove(entity);
	}

}
