package com.biencloud.smarthome.service.requestrepair.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;
import com.biencloud.smarthome.service.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;

/**
 * 
 * 项目名称：smarthome-service-new 类名称：RequestRepairServiceImpl 类描述： 报修领域服务实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:19:59
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RequestRepairServiceImpl extends BaseService<RequestRepair, Long> implements IRequestRepairService {

	private IOwnerUserService ownerUserService;

	@Override
	public Paging<RequestRepair> queryRequestRepairForPaging(RequestRepair paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM RequestRepair dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getDistrictId() != null) {// 物业管理员查看
			appendCondition(hql, "dn.districtId = :districtId", "districtId", paramsOb.getDistrictId(), params);
		} else if (paramsOb.getOwnerUser() != null && paramsOb.getOwnerUser().getUserId() != null) {// 业主查看
			String roomId = ownerUserService.get(paramsOb.getOwnerUser().getUserId()).getHouseId();// 要用房间ID查询的原因是用户使用APP报修时没有绑定业主，故只有userid为null
			// appendCondition(hql, "dn.ownerUser.userId = :userId", "userId",
			// paramsOb.getOwnerUser().getUserId() , params);
			appendCondition(hql, "dn.cellHouseholdInfo.id = :roomId", "roomId", roomId, params);
		}

		if (StringUtils.isNotBlank(paramsOb.getTitle()))
			appendCondition(hql, "dn.title like :title", "title", "%" + paramsOb.getTitle() + "%", params);
		if (StringUtils.isNotBlank(paramsOb.getContent()))
			appendCondition(hql, "dn.content like :content", "content", "%" + paramsOb.getContent() + "%", params);
		if (paramsOb.getRepairStartTime() != null)
			appendCondition(hql, "dn.repairTime >= :repairStartTime", "repairStartTime", paramsOb.getRepairStartTime(), params);

		if (paramsOb.getRepairEndTime() != null)
			appendCondition(hql, "dn.repairTime <= :repairEndTime", "repairEndTime", paramsOb.getRepairEndTime(), params);
		if (paramsOb.getRequestTime() != null) { // 此条件只查询某天
			appendCondition(hql, "dn.requestTime >= :requestTime", "requestTime", Utils.covertStringToDate((Utils.formatCurrentDayStart())), params);
			appendCondition(hql, "dn.requestTime <= :requestTime2", "requestTime2", Utils.covertStringToDate((Utils.formatCurrentDayEnd())), params);
		}
		if (paramsOb.getRequestStartTime() != null)
			appendCondition(hql, "dn.requestTime >= :requestStartTime", "requestStartTime", paramsOb.getRequestStartTime(), params);

		if (paramsOb.getRequestEndTime() != null)
			appendCondition(hql, "dn.requestTime <= :requestEndTime", "requestEndTime", paramsOb.getRequestEndTime(), params);

		if (paramsOb.getStatus() != null)
			appendCondition(hql, "dn.status = :status", "status", paramsOb.getStatus(), params);
		else if (paramsOb.isIsexcuteNoSubmit())
			appendCondition(hql, "dn.status != :status", "status", RequestRepair.STATUS_NOSUMBIT.toString(), params);
		/*
		 * else if(paramsOb.getDistrictId()!=null){ appendCondition(hql,
		 * "dn.status != :status",
		 * "status",RequestRepair.STATUS_NOSUMBIT.toString(), params); }
		 */
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		if (Constants.SELECT_COUNT_FOR_INDEX == pageSize)
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		else
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public boolean isExistForHouseHold(String houseId) {
		boolean result = false;
		String sql = "select count(*) from RequestRepair dn where cellHouseholdInfo.id =" + houseId;
		long count = getTotalCount(sql);
		if (count > 0)
			result = true;
		return result;
	}

	@Override
	public Long getRequestRepairCount(RequestRepair paramsOb) {
		Long result = 0l;
		String roomId = ownerUserService.get(paramsOb.getOwnerUser().getUserId()).getHouseId();// 要用房间ID查询的原因是用户使用APP报修时没有绑定业主，故只有userid为null
		String hql = "select count(*) from RequestRepair where cellHouseholdInfo.id = " + roomId + " and status!=" + RequestRepair.STATUS_NOSUMBIT.toString();
		result = this.getTotalCount(hql);
		return result;
	}

	@Override
	public List<RequestRepair> queryRequestRepairForList(RequestRepair paramsOb) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRequestRepair(RequestRepair entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRequestRepair(RequestRepair entity) {
		if (entity.getOwnerUser().getUserId() != null) {
			OwnerUser ou = ownerUserService.get(entity.getOwnerUser().getUserId());
			CellHouseholdInfo ob = new CellHouseholdInfo();
			ob.setId(ou.getHouseId());
			entity.setCellHouseholdInfo(ob);
		} else {
			entity.setOwnerUser(null);
		}
		entity.setPaUser(null);
		super.save(entity);
	}

	@Override
	public RequestRepair getRequestRepair(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelRequestRepair(RequestRepair entity) {
		super.remove(entity);
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

}
