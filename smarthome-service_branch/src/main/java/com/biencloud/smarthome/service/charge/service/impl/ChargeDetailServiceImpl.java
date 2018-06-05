package com.biencloud.smarthome.service.charge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;
import com.biencloud.smarthome.service.charge.model.ChargeTypeResult;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.charge.service.IChargeDetailService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;

/**
 * 收费清单领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IChargeDetailService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ChargeDetailServiceImpl extends BaseService<ChargeDetail, Long> implements IChargeDetailService {

	private IChargeDataService chargeDataService;
	private IOwnerUserService ownerUserService;
	private IPushService pushService;
	private IDeviceService deviceService;

	@Override
	public Paging<ChargeDetail> queryChargeDetailForPaging(ChargeDetail paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeDetail dn");
		Map<String, Object> params = createParams(hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		if (Constants.SELECT_COUNT_FOR_INDEX == pageSize)
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		else
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	private Map<String, Object> createParams(StringBuilder hql, ChargeDetail paramsOb) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb == null)
			return params;
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getCellHouseholdInfo() != null && StringUtils.isNotBlank(paramsOb.getChargeData().getCellHouseholdInfo().getName()))
			appendCondition(hql, "dn.chargeData.cellHouseholdInfo.name LIKE :cellHousename", "cellHousename", "%" + paramsOb.getChargeData().getCellHouseholdInfo().getName() + "%", params);
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getRegionBuildingInfo() != null && StringUtils.isNotBlank(paramsOb.getChargeData().getRegionBuildingInfo().getName()))
			appendCondition(hql, "dn.chargeData.regionBuildingInfo.name LIKE :regionBuildingname", "regionBuildingname", "%" + paramsOb.getChargeData().getRegionBuildingInfo().getName() + "%", params);
		if (paramsOb.getChargeData() != null && StringUtils.isNotBlank(paramsOb.getChargeData().getOwnerName()))
			appendCondition(hql, "dn.chargeData.ownerName LIKE :ownerName", "ownerName", "%" + paramsOb.getChargeData().getOwnerName() + "%", params);
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getCellHouseholdInfo() != null && paramsOb.getChargeData().getCellHouseholdInfo().getId() != null) {// App查询
			appendCondition(hql, "dn.chargeData.cellHouseholdInfo.id = :roomId", "roomId", paramsOb.getChargeData().getCellHouseholdInfo().getId(), params);
		} else {// 业主登录查询
			if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getCellHouseholdInfo() != null && paramsOb.getChargeData().getCellHouseholdInfo().getOwner() != null && StringUtils.isNotBlank(paramsOb.getChargeData().getCellHouseholdInfo().getOwner().getUserId())) {
				String roomId = ownerUserService.get(paramsOb.getChargeData().getCellHouseholdInfo().getOwner().getUserId()).getHouseId();
				appendCondition(hql, "dn.chargeData.cellHouseholdInfo.id = :roomId", "roomId", roomId, params);
			}
		}
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getHousingDistrictInfo() != null && paramsOb.getChargeData().getHousingDistrictInfo().getId() != null)
			appendCondition(hql, "dn.chargeData.housingDistrictInfo.id = :housingDistrictInfoId", "housingDistrictInfoId", paramsOb.getChargeData().getHousingDistrictInfo().getId(), params);
		if (paramsOb.getChargeData() != null && StringUtils.isNotBlank(paramsOb.getChargeData().getChargeTime()))
			appendCondition(hql, "dn.chargeData.chargeTime like :chargeDataChargeTime", "chargeDataChargeTime", "%" + paramsOb.getChargeData().getChargeTime() + "%", params);
		if (StringUtils.isNotBlank(paramsOb.getChargeStatus()))
			appendCondition(hql, "dn.chargeStatus = :chargeStatus", "chargeStatus", paramsOb.getChargeStatus(), params);
		if (StringUtils.isNotBlank(paramsOb.getChargeStartTime()))
			appendCondition(hql, "dn.chargeTime >= :chargeStartTime", "chargeStartTime", paramsOb.getChargeStartTime(), params);
		if (StringUtils.isNotBlank(paramsOb.getChargeEndTime()))
			appendCondition(hql, "dn.chargeTime <= :chargeEndTime", "chargeEndTime", paramsOb.getChargeEndTime(), params);
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getCreateStartTime() != null)
			appendCondition(hql, "dn.chargeData.createTime >= :chargeDataStartCreateTime", "chargeDataStartCreateTime", paramsOb.getChargeData().getCreateStartTime(), params);
		if (paramsOb.getChargeData() != null && paramsOb.getChargeData().getCreateEndTime() != null)
			appendCondition(hql, "dn.chargeData.createTime <= :chargeDataStartEndTime", "chargeDataStartEndTime", paramsOb.getChargeData().getCreateEndTime(), params);
		hql.append(" order by id desc");
		return params;
	}

	@Override
	public List<ChargeDetail> queryChargeDetailForList(ChargeDetail paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM ChargeDetail dn");
		Map<String, Object> params = createParams(hql, paramsOb);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateChargeDetail(ChargeDetail entity) {
		// entity.setChargeStatus(ChargeDetail.FeeStatusYES.toString());
		// entity.setChargeTime(Utils.formatTime());
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveChargeDetail(ChargeDetail entity) {
		ChargeData paramsOb = new ChargeData();
		paramsOb.setIsproductDetail(ChargeData.ChargeDataStatusNO.toString());
		List<ChargeData> dataList = chargeDataService.queryChargeDataForList(paramsOb);
		if(dataList!=null){
			for (int i = 0; i < dataList.size(); i++) {
				ChargeData dataOb = (ChargeData) dataList.get(i);
				ChargeDetail target = new ChargeDetail();
				target.setChargeData(dataOb);
				target.setPrintSn(Utils.generatorDigit());
				target.setChargeStatus(ChargeDetail.FeeStatusNO.toString());
				super.save(target);
				String updateSql = "update ChargeTypeResult set chargeDetailId=" + target.getId() + " where chargeDataId=" + dataOb.getId();
				super.update(updateSql);
				dataOb.setIsproductDetail(ChargeData.ChargeDataStatusYES.toString());
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean publishChargeInfo(Push pushOb, String roomId) {
		try {
			Device ob = new Device();
			CellHouseholdInfo chi = new CellHouseholdInfo();
			chi.setId(roomId);
			ob.setCellHouseholdInfo(chi);
			List<Device> list = deviceService.queryDevices(ob);
			List<Push> pushList = new ArrayList<Push>();
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					Device rsOb = list.get(i);
					Push psOb = new Push();
					psOb.setPushClientId(rsOb.getDeviceCode());
					psOb.setPushContent(pushOb.getPushContent());
					psOb.setPushName("缴费通知");
					psOb.setAddTime(new Date(System.currentTimeMillis()));
					psOb.setPushKind(PushKindConstants.PUSH_CHAEGE_NOTICE_KIND);
					pushList.add(psOb);
				}
			}
			return pushService.inserPushCollection(pushList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChargeDetail getChargeDetail(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelChargeDetail(ChargeDetail entity) {
		Set<ChargeTypeResult> typeList = entity.getChargeTypeResults();
		Long dataId = null;
		if (typeList != null && !typeList.isEmpty()) {
			@SuppressWarnings("rawtypes")
			Iterator it = typeList.iterator();
			ChargeTypeResult ob = (ChargeTypeResult) it.next();
			dataId = ob.getChargeDataId();
		}
		String updateSql = "update ChargeTypeResult set chargeDetailId = ? where chargeDetailId=" + entity.getId();
		Object[] ob = { null };
		super.update(updateSql, ob);
		super.remove(entity);
		updateSql = "update ChargeData set isproductDetail=" + ChargeData.ChargeDataStatusNO + " where id=" + dataId;
		super.update(updateSql);
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
