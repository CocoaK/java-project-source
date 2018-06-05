package com.biencloud.smarthome.service.log.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.DiviceRegeditLog;
import com.biencloud.smarthome.service.log.service.IDiviceRegeditLogService;

/**
 * 设备注册日志领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IDiviceRegeditLogService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DiviceRegeditLogServiceImpl extends BaseService<DiviceRegeditLog, Long> implements IDiviceRegeditLogService {

	private IChargeDataService chargeDataService;

	@Override
	public Paging<DiviceRegeditLog> queryDiviceRegeditLogForPaging(DiviceRegeditLog paramsOb, int pageNum, int pageSize) {
		try {
			StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM DiviceRegeditLog dn");
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(paramsOb.getName()))
				appendCondition(hql, "dn.name like :name", "name", "%" + paramsOb.getName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getLocation()))
				appendCondition(hql, "dn.location like :location", "location", "%" + paramsOb.getLocation().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getDistrictName()))
				appendCondition(hql, "dn.districtName like :districtName", "districtName", "%" + paramsOb.getDistrictName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getRegionName()))
				appendCondition(hql, "dn.regionName like :regionName", "regionName", "%" + paramsOb.getRegionName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getBuildingName()))
				appendCondition(hql, "dn.buildingName like :buildingName", "buildingName", "%" + paramsOb.getBuildingName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getCellName()))
				appendCondition(hql, "dn.cellName like :cellName", "cellName", "%" + paramsOb.getCellName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getHourseNo()))
				appendCondition(hql, "dn.hourseNo like :hourseNo", "hourseNo", "%" + paramsOb.getHourseNo().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getDeviceNo()))
				appendCondition(hql, "dn.deviceNo like :deviceNo", "deviceNo", "%" + paramsOb.getDeviceNo().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getMacAddr()))
				appendCondition(hql, "dn.macAddr like :macAddr", "macAddr", "%" + paramsOb.getMacAddr().trim() + "%", params);
			if (paramsOb.getAddTime()!=null)
				appendCondition(hql, "dn.addTime like :addTime",
						"addTime", "%" + paramsOb.getAddTime()+ "%" , params);
			if (paramsOb.getAddStartTime()!=null){	
				appendCondition(hql, "dn.addTime >= :addStartTime",
						"addStartTime",paramsOb.getAddStartTime(), params);
			}
			if (paramsOb.getAddEndTime()!=null)
				appendCondition(hql, "dn.addTime <= :addEndTime",
						"addEndTime", paramsOb.getAddEndTime() , params);
			hql.append(" order by addTime desc");
			String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
			String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DiviceRegeditLog> queryDiviceRegeditLogForList(DiviceRegeditLog paramsOb) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDiviceRegeditLog(DiviceRegeditLog entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDiviceRegeditLog(DiviceRegeditLog entity) {
		entity.setAddTime(new Date(System.currentTimeMillis()));
		super.save(entity);
	}

	@Override
	public DiviceRegeditLog getDiviceRegeditLog(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelDiviceRegeditLog(DiviceRegeditLog entity) {
		super.remove(entity);
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

}
