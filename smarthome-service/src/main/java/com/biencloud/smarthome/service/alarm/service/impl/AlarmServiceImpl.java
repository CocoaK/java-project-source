package com.biencloud.smarthome.service.alarm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.alarm.model.Alarm;
import com.biencloud.smarthome.service.alarm.service.IAlarmService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;

/**
 * 
 * 项目名称：smarthome-service-new 类名称：AlarmServiceImpl 类描述： 报警服务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 上午11:42:19
 */
public class AlarmServiceImpl extends BaseService<Alarm, String> implements IAlarmService {
	private IOwnerUserService ownerUserService;
	private ICellHouseholdInfoService cellHouseholdInfoService;

	@Override
	public List<Alarm> queryAlarms(Alarm alarm) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, alarm);
		jpql.insert(0, "SELECT alarm FROM Alarm alarm");
		return findByNamedParams(jpql.toString(), params);
	}

	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, Alarm alarm) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (alarm == null)
			return params;
		if (StringUtils.isNotBlank(alarm.getAlarmId()))
			appendCondition(jpql, "alarm.alarmId = :alarmId", "alarmId", alarm.getAlarmId(), params);

		if (StringUtils.isNotBlank(alarm.getDeviceCode()))
			appendCondition(jpql, "alarm.deviceCode = :deviceCode", "deviceCode", alarm.getDeviceCode(), params);

		if (StringUtils.isNotBlank(alarm.getStatus()))
			appendCondition(jpql, "alarm.status = :status", "status", alarm.getStatus(), params);
		/*
		 * else if (alarm.getSystemGroup() != null &&
		 * alarm.getSystemGroup().getGroupNo()!=null)//小区登录把业主取消的记录过漏
		 * appendCondition(jpql, "alarm.status != :status", "status",
		 * Alarm.HANLDER_STATUS_CANCEL.toString(), params);
		 */

		if (StringUtils.isNotBlank(alarm.getContent()))
			appendCondition(jpql, "alarm.content LIKE :content", "content", "%" + alarm.getContent() + "%", params);

		if (alarm.getCreatedTime() != null) {// 此条件设置只查询某天
			appendCondition(jpql, "alarm.createdTime >= :createdTime", "createdTime", Utils.covertStringToDate((Utils.formatCurrentDayStart())), params);
			appendCondition(jpql, "alarm.createdTime <= :createdTime2", "createdTime2", Utils.covertStringToDate((Utils.formatCurrentDayEnd())), params);
		}

		if (alarm.getAlarmStartTime() != null)
			appendCondition(jpql, "alarm.createdTime >= :alarmStartTime", "alarmStartTime", alarm.getAlarmStartTime(), params);

		if (alarm.getAlarmEndTime() != null)
			appendCondition(jpql, "alarm.createdTime <= :alarmEndTime", "alarmEndTime", alarm.getAlarmEndTime(), params);

		if (alarm.getHandlerStartTime() != null)
			appendCondition(jpql, "alarm.hanlderTime >= :hanlderStartTime", "hanlderStartTime", alarm.getHandlerStartTime(), params);

		if (alarm.getHandlerEndTime() != null)
			appendCondition(jpql, "alarm.hanlderTime <= :hanlderEndTime", "hanlderEndTime", alarm.getHandlerEndTime(), params);

		if (StringUtils.isNotBlank(alarm.getHouseNo())) {
			CellHouseholdInfo ch = new CellHouseholdInfo();
			ch.setName(alarm.getHouseNo());
			List<CellHouseholdInfo> list = cellHouseholdInfoService.findHouseholds(ch);
			List<String> houseId = new ArrayList<String>();
			if(list!=null&&!list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					houseId.add(list.get(i).getId());
				}
				appendCondition(jpql, "alarm.houseNo in :houseNo", "houseNo", houseId, params);
			}else {
				if (!StringUtils.contains(jpql, " WHERE ")) {
					jpql.append(" WHERE 1!=1 ");
				}else jpql.append(" AND 1!=1 ");
			}
		}

		if (alarm.getownerUser() != null && StringUtils.isNotBlank(alarm.getownerUser().getUserName()))
			appendCondition(jpql, "alarm.ownerUser.userName LIKE :userName", "userName", "%" + alarm.getownerUser().getUserName() + "%", params);

		if (alarm.getownerUser() != null && StringUtils.isNotBlank(alarm.getownerUser().getMobilePhone()))
			appendCondition(jpql, "alarm.ownerUser.mobilePhone LIKE :mobilePhone", "mobilePhone", "%" + alarm.getownerUser().getMobilePhone() + "%", params);

		if (alarm.getpaUser() != null && StringUtils.isNotBlank(alarm.getpaUser().getUserName()))
			appendCondition(jpql, "alarm.paUser.userName LIKE :paUserName", "paUserName", "%" + alarm.getpaUser().getUserName() + "%", params);

		if (alarm.getAlarmType() != null && StringUtils.isNotBlank(alarm.getAlarmType().getAlarmType()))
			appendCondition(jpql, "alarm.alarmType.alarmType = :alarmType", "alarmType", alarm.getAlarmType().getAlarmType(), params);
		if (alarm.getHousingDistrictInfo() != null && StringUtils.isNotBlank(alarm.getHousingDistrictInfo().getId()))
			appendCondition(jpql, "alarm.housingDistrictInfo.id = :housingDistrictInfoId", "housingDistrictInfoId", alarm.getHousingDistrictInfo().getId(), params);
		if (alarm.getownerUser() != null && alarm.getownerUser().getUserId() != null) {
			String roomId = ownerUserService.get(alarm.getownerUser().getUserId()).getHouseId();// 要用房间ID查询的原因是用户使用APP报修时没有绑定业主，故只有userid为null
			appendCondition(jpql, "alarm.houseNo = :roomId", "roomId", roomId, params);
		}
		if (alarm.isCancelAndNoHanlder()){
			if (!StringUtils.contains(jpql, " WHERE ")) {
				jpql.append(" WHERE 1=1 ");
			}
			jpql.append(" and (status=" + Alarm.HANLDER_STATUS_CANCEL + " or status=" + Alarm.HANLDER_STATUS_NO + ")");
		}
		return params;
	}

	@Override
	public Paging<Alarm> queryAlarmForPaging(Alarm paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Alarm alarm");
		Map<String, Object> params = createQueryParams(hql, paramsOb);
		hql.append(" order by alarmId desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "alarm");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		// if(Constants.SELECT_COUNT_FOR_INDEX==pageSize) return
		// queryByNamedParamsForPaging(pageNum, pageSize,queryString,
		// queryStringCount, params);
		// else return queryByNamedParamsForPaging(pageNum, queryString,
		// queryStringCount, params);
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public Long getOwnerAlarmCount(Alarm paramsOb) {
		Long result = 0l;
		try {
			String roomId = ownerUserService.get(paramsOb.getownerUser().getUserId()).getHouseId();
			String hql = "select count(*) from Alarm where houseNo = " + roomId;
			result = this.getTotalCount(hql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAlarm(Alarm entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAlarm(Alarm entity) {
		super.save(entity);
	}

	@Override
	public Alarm getAlarm(String entityId) {
		return super.get(entityId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelAlarm(Alarm entity) {
		super.remove(entity);
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

}
