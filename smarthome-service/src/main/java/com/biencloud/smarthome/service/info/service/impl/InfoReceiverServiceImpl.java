package com.biencloud.smarthome.service.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.service.IInfoReceiverService;

/**
 * 信息接收领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IInfoReceiverService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InfoReceiverServiceImpl extends BaseService<InfoReceiver, Long> implements IInfoReceiverService {

	private IChargeDataService chargeDataService;

	@Override
	public Paging<InfoReceiver> queryInfoReceiverForPaging(InfoReceiver paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoReceiver dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getHouseId() != null) {// 业主查看
			if (paramsOb.getReceiverType() != null) {
				appendCondition(hql, "dn.receiverType = :receiverType", "receiverType", paramsOb.getReceiverType(), params);
				hql.append("  and dn.areaId =" + paramsOb.getAreaId());
			} else {
				appendCondition(hql, "((dn.houseId = :houseId", "houseId", paramsOb.getHouseId(), params);
				hql.append(")");
				if (paramsOb.getStatus() == null)
					hql.append(" or (dn.receiverType=" + InfoReceiver.INFO_TYPE_SYSTEM + " and dn.areaId =" + paramsOb.getAreaId() + ")");
				if (paramsOb.getStatus() == null)
					hql.append(" or (dn.receiverType=" + InfoReceiver.INFO_TYPE_PERSON);
				hql.append("  and dn.areaId =" + paramsOb.getAreaId());
				if (paramsOb.getStatus() == null)
					hql.append(")");
				hql.append(")");
			}
			if (paramsOb.getStatus() != null)
				appendCondition(hql, "dn.status = :status", "status", paramsOb.getStatus(), params);
			else
				appendCondition(hql, "dn.status != :status", "status", InfoReceiver.STATUSNOREAD, params);
		} else {// 物业管理员查看
			if (paramsOb.getReceiverType() != null) {
				appendCondition(hql, "dn.receiverType = :receiverType", "receiverType", paramsOb.getReceiverType(), params);
				hql.append("  and dn.areaId =" + paramsOb.getAreaId());
			} else {
				appendCondition(hql, "dn.areaId = :areaId", "areaId", paramsOb.getAreaId(), params);
				hql.append(" and ((dn.receiverType=" + InfoReceiver.INFO_TYPE_SYSTEM + ")");
				if (paramsOb.getStatus() == null)
					hql.append(" or (dn.receiverType=" + InfoReceiver.INFO_TYPE_PERSON + ")");
				hql.append(")");
			}
			if (paramsOb.getStatus() != null)
				appendCondition(hql, "dn.status = :status", "status", paramsOb.getStatus(), params);
			else
				appendCondition(hql, "dn.status != :status", "status", InfoReceiver.STATUSNOREAD, params);
		}
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public List<InfoReceiver> queryInfoReceiverForIndex(InfoReceiver paramsOb) {
		List<InfoReceiver> result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoReceiver dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getStatus() != null)
			appendCondition(hql, "dn.status = :status", "status", paramsOb.getStatus(), params);
		if (paramsOb.getHouseId() != null)
			appendCondition(hql, "dn.houseId = :houseId", "houseId", paramsOb.getHouseId(), params);
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		Paging<InfoReceiver> list = queryByNamedParamsForPaging(1, Constants.SELECT_COUNT_FOR_INDEX, queryString, queryStringCount, params);
		result = list.getResults();
		return result;
	}

	@Override
	public Long getNoReadReceiverCount(InfoReceiver paramsOb) {
		Long result = 0l;
		String hql = "select count(*) from InfoReceiver where status = " + InfoReceiver.STATUSNO + " and receiverType=" + paramsOb.getReceiverType();
		if (paramsOb.getReceiverType() == InfoReceiver.INFO_TYPE_SYSTEM) {
			hql += " and areaId = " + paramsOb.getAreaId();
		} else if (paramsOb.getReceiverType() == InfoReceiver.INFO_TYPE_COMMUNITY) {
			hql += " and houseId = " + paramsOb.getHouseId();
		}
		result = this.getTotalCount(hql);
		return result;
	}

	@Override
	public List<InfoReceiver> queryInfoReceiverForList(InfoReceiver paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoReceiver dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getReceiverType() != null)
			appendCondition(hql, "dn.receiverType = :receiverType", "receiverType", paramsOb.getReceiverType(), params);
		if (paramsOb.getInfoSendId() != null)
			appendCondition(hql, "dn.infoSendId = :infoSendId", "infoSendId", paramsOb.getInfoSendId(), params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	public List<InfoReceiver> queryInfoReceiverByHouseIds(List<String> houseIds) {
		StringBuilder jpql = new StringBuilder();
		StringBuilder conditionHouseId = new StringBuilder();
		jpql.insert(0, "SELECT infoReceiver FROM InfoReceiver infoReceiver WHERE 1=1 ");
		//组织条件
		if (houseIds != null && houseIds.size() > 0) {
			conditionHouseId.insert(0, "'" + houseIds.get(0) + "'");
			for (int i = 1; i < houseIds.size(); i++) {
				conditionHouseId.append(",'" + houseIds.get(i) + "'");
			}
			jpql.append(" AND infoReceiver.houseId in ");
			jpql.append("(" + conditionHouseId + ")");
			jpql.append(" ORDER BY infoReceiver.id DESC");
		}
		return super.find(jpql.toString());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateInfoReceiver(InfoReceiver entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveInfoReceiver(InfoReceiver entity) {
		super.save(entity);
	}

	@Override
	public InfoReceiver getInfoReceiver(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelInfoReceiver(InfoReceiver entity) {
		super.remove(entity);
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

}
