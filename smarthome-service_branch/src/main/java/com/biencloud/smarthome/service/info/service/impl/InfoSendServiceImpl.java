package com.biencloud.smarthome.service.info.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.info.model.DistrictData;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.model.NoticeData;
import com.biencloud.smarthome.service.info.service.IInfoReceiverDeviceService;
import com.biencloud.smarthome.service.info.service.IInfoReceiverService;
import com.biencloud.smarthome.service.info.service.IInfoSendService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;

/**
 * 信息发布领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IInfoSendService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InfoSendServiceImpl extends BaseService<InfoSend, Long> implements IInfoSendService {

	private IChargeDataService chargeDataService;

	private IDeviceService deviceService;

	private IPushService pushService;

	private ICellHouseholdInfoService cellHouseholdInfoService;

	private IInfoReceiverDeviceService infoReceiverDeviceService;
	
	private IInfoReceiverService infoReceiverService;

	@Override
	public Paging<InfoSend> queryInfoSendForPaging(InfoSend paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoSend dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(paramsOb.getRemark()))
			appendCondition(hql, "dn.type = :type", "type", Integer.parseInt(paramsOb.getRemark()), params);// 实际是信息类型,搜索处使用
		else {
			if (paramsOb.getType() != null) {
				if (paramsOb.getType() == InfoReceiver.INFO_TYPE_COMMUNITY) {// 物业管理员登录时，加上业主发布的未审核的信息
					appendCondition(hql, "(dn.type = :type", "type", paramsOb.getType(), params);
					// hql.append(" or (dn.type="+InfoReceiver.INFO_TYPE_PERSON+" and dn.status="+InfoSend.STATUSNOAUDIT+")) and dn.areaId="+paramsOb.getAreaId());
					hql.append(" or dn.type=" + InfoReceiver.INFO_TYPE_PERSON + ") and dn.areaId=" + paramsOb.getAreaId());
				} else {
					appendCondition(hql, "dn.type = :type", "type", paramsOb.getType(), params);
				}
			}
		}
		if (paramsOb.getSendUserId() != null)
			appendCondition(hql, "dn.sendUserId = :sendUserId", "sendUserId", paramsOb.getSendUserId(), params);
		if (paramsOb.getSendMode() != null)
			appendCondition(hql, "dn.sendMode = :sendMode", "sendMode", paramsOb.getSendMode(), params);
		if (paramsOb.getTitle() != null)
			appendCondition(hql, "dn.title like :title", "title", "%" + paramsOb.getTitle() + "%", params);
		// if(paramsOb.getSendTime()!=null) appendCondition(hql,
		// "dn.sendTime like :sendTime", "sendTime",paramsOb.getSendTime(),
		// params);
		if (paramsOb.getSendStartTime() != null)
			appendCondition(hql, "dn.sendTime >= :sendStartTime", "sendStartTime", paramsOb.getSendStartTime(), params);
		if (paramsOb.getSendEndTime() != null)
			appendCondition(hql, "dn.sendTime <= :sendEndTime", "sendEndTime", paramsOb.getSendEndTime(), params);
		if (paramsOb.getStatus() != null)
			appendCondition(hql, "dn.status = :status", "status", paramsOb.getStatus(), params);
		hql.append(" order by createTime desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		// else return queryByNamedParamsForPaging(pageNum, queryString,
		// queryStringCount, params);
	}

	@Override
	public List<InfoSend> queryInfoSendForIndex(InfoSend paramsOb) {
		List<InfoSend> result = null;
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoSend dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getType() != null) {
			if (paramsOb.getType() == InfoReceiver.INFO_TYPE_COMMUNITY) {// 物业管理员登录
				appendCondition(hql, "dn.areaId = :areaId", "areaId", paramsOb.getAreaId(), params);
				hql.append(" and dn.type=" + InfoReceiver.INFO_TYPE_PERSON + " and dn.status=" + InfoSend.STATUSNOAUDIT);
			} else {// 系统管理员、业主登录
				appendCondition(hql, "dn.type = :type", "type", paramsOb.getType(), params);
				if (paramsOb.getSendUserId() != null)
					appendCondition(hql, "dn.sendUserId = :sendUserId", "sendUserId", paramsOb.getSendUserId(), params);
			}
		}
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		Paging<InfoSend> list = queryByNamedParamsForPaging(1, 5, queryString, queryStringCount, params);
		result = list.getResults();
		return result;
	}

	@Override
	public Long getInfoCount(InfoSend paramsOb) {
		Long result = 0l;
		String hql = "";
		if (paramsOb.getType() == InfoReceiver.INFO_TYPE_SYSTEM) {
			hql = "select count(*) from InfoSend where sendTime like '" + Utils.formatDate() + "%' and type=" + InfoReceiver.INFO_TYPE_SYSTEM;
		} else if (paramsOb.getType() == InfoReceiver.INFO_TYPE_PERSON) {
			hql = "select count(*) from InfoSend where sendUserId = " + paramsOb.getSendUserId() + " and type=" + InfoReceiver.INFO_TYPE_PERSON;
		}
		result = this.getTotalCount(hql);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<DistrictData> queryAreaData(InfoSend ob) {
		StringBuilder varname1 = new StringBuilder();
		varname1.append("SELECT (SELECT tou.user_id ");
		varname1.append("        FROM   t_owner_user tou ");
		varname1.append("        WHERE  tou.house_id = chi.id) AS userid, ");
		varname1.append("       chi.id AS houseid, ");
		varname1.append("       chi.name AS housename, ");
		varname1.append("       bci.id AS cellid, ");
		varname1.append("       bci.name AS cellname, ");
		varname1.append("       rbi.id AS buildingid, ");
		varname1.append("       rbi.name AS buildingname, ");
		varname1.append("       hdri.id AS regionid, ");
		varname1.append("       hdri.name AS regionname, ");
		varname1.append("       hdi.id AS districtid, ");
		varname1.append("       hdi.name AS districtname ");
		varname1.append("FROM   t_hm_cell_household_info chi ");
		varname1.append("       INNER JOIN t_hm_building_cell_info bci ");
		varname1.append("         ON chi.cell_id = bci.id ");
		varname1.append("       INNER JOIN t_hm_region_building_info rbi ");
		varname1.append("         ON bci.building_id = rbi.id ");
		varname1.append("       INNER JOIN t_hm_housing_district_region_info hdri ");
		varname1.append("         ON rbi.region_id = hdri.id ");
		varname1.append("       INNER JOIN t_hm_housing_district_info hdi ");
		varname1.append("         ON hdri.housing_district_id = hdi.id ");
		if (ob.getAreaId() != null)
			varname1.append("WHERE  hdi.id =" + ob.getAreaId());
		List result = getDao().excuteSql(varname1.toString());
		List<DistrictData> target = new ArrayList<DistrictData>();
		if(result!=null){
			for (int i = 0; i < result.size(); i++) {
				DistrictData csOb = new DistrictData();
				Object[] rsOb = (Object[]) result.get(i);
				csOb.setUserId(((BigInteger) rsOb[0]) + "");
				csOb.setHouseId(((BigInteger) rsOb[1]) + "");
				csOb.setHouseName((String) rsOb[2]);
				csOb.setCellId(((Integer) rsOb[3]) + "");
				csOb.setCellName(((String) rsOb[4]) + "");
				csOb.setBuildingId(((Integer) rsOb[5]) + "");
				csOb.setBuildingName(((String) rsOb[6]) + "");
				csOb.setRegionId(((Integer) rsOb[7]) + "");
				csOb.setRegionName(((String) rsOb[8]) + "");
				csOb.setDistrictId(((BigInteger) rsOb[9]) + "");
				csOb.setDistrictName(((String) rsOb[10]) + "");
				target.add(csOb);
			}
		}
		return target;
	}

	private boolean publishInfo(InfoSend isOb) {
		try {
			if (isOb.getStatus() == InfoSend.STATUSYESSEND) {
				if (isOb.getType() == InfoReceiver.INFO_TYPE_PERSON) {
					Device ob = new Device();
					HousingDistrictInfo hdiOb = new HousingDistrictInfo();
					hdiOb.setId(isOb.getAreaId().toString());
					ob.setHousingDistrictInfo(hdiOb);
					inserPush(ob, isOb);
				} else if (isOb.getType() == InfoReceiver.INFO_TYPE_COMMUNITY) {
					Iterator<InfoReceiver> it = isOb.getInfoReceivers().iterator();
					while (it.hasNext()) {
						Device ob = new Device();
						CellHouseholdInfo chi = new CellHouseholdInfo();
						chi.setId(it.next().getHouseId().toString());
						ob.setCellHouseholdInfo(chi);
						inserPush(ob, isOb);
					}
				} else if (isOb.getType() == InfoReceiver.INFO_TYPE_SYSTEM) {
					Iterator<InfoReceiver> it = isOb.getInfoReceivers().iterator();
					while (it.hasNext()) {
						Device ob = new Device();
						HousingDistrictInfo hdiOb = new HousingDistrictInfo();
						hdiOb.setId(it.next().getAreaId().toString());
						ob.setHousingDistrictInfo(hdiOb);
						inserPush(ob, isOb);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean publishInfoReceiverDevice(InfoSend isOb, String deviceTypeIds, Set<InfoReceiver> receivers, String districtId) {
		try {
			Iterator<InfoReceiver> it = receivers.iterator();
			Set<String> cellIds = new HashSet<String>();
			while (it.hasNext()) {
				String cellId = cellHouseholdInfoService.get(it.next().getHouseId().toString()).getId();
				if (!cellIds.contains(cellId)) {
					cellIds.add(cellId);
				}
			}
			String[] typeIds = deviceTypeIds.split(",");
			List<String> typeListIds = new ArrayList<String>();
			if(typeIds!=null){
				for (int i = 0; i < typeIds.length; i++) {
					typeListIds.add(typeIds[i]);
				}
			}
			Iterator<String> cellSet = cellIds.iterator();
			// 处理所属单元的设备
			while (cellSet.hasNext()) {
				Device ob = new Device();
				BuildingCellInfo params = new BuildingCellInfo();
				params.setId(cellSet.next());
				ob.setBuildingCellInfo(params);
				ob.setDeviceTypeList(typeListIds);
				inserPush(ob, isOb);
			}
			// 处理小区级别的设备
			Device ob = new Device();
			HousingDistrictInfo params = new HousingDistrictInfo();
			params.setId(districtId);
			ob.setHousingDistrictInfo(params);
			ob.setAreaIsNull(true);
			ob.setDeviceTypeList(typeListIds);
			inserPush(ob, isOb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean inserPush(Device ob, InfoSend isOb) {
		List<Device> list = deviceService.queryDevices(ob);
		List<Push> pushList = new ArrayList<Push>();
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Device rsOb = list.get(i);
				Push psOb = new Push();
				psOb.setPushClientId(rsOb.getDeviceCode());
				psOb.setPushContent(isOb.getContent());
				psOb.setPushName(isOb.getTitle());
				psOb.setAddTime(new Date(System.currentTimeMillis()));
				psOb.setPushKind("message");
				pushList.add(psOb);
			}
		}
		return pushService.inserPushCollection(pushList);
	}

	@Override
	public List<InfoSend> queryInfoSendForList(InfoSend paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoSend dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getSendMode() != null)
			appendCondition(hql, "dn.sendMode = :sendMode", "sendMode", paramsOb.getSendMode(), params);
		if (StringUtils.isNotBlank(paramsOb.getRemark()))
			hql.append(" and dn.timimgTime is not null and sendTime is null"); // appendCondition(hql,
																				// "dn.timimgTime != :timimgTime",
																				// "timimgTime",
																				// null
																				// ,
																				// params);
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void sendTimingInfo() {
		try {
			InfoSend entity = new InfoSend();
			entity.setSendMode(InfoSend.SENDMODE_TIMIMG);
			entity.setRemark("00");
			List<InfoSend> result = queryInfoSendForList(entity);
			if (result!=null&&!result.isEmpty() && result.size() > 0) {
				for (int j = 0; j < result.size(); j++) {
					InfoSend ob = result.get(j);
					Date time = new Date(System.currentTimeMillis());
					Date startTime = Utils.addMinute(time, -4);
					Date endTime = Utils.addMinute(time, 4);
					Date currentTime = ob.getTimimgTime();
					if (currentTime.before(endTime) && currentTime.after(startTime)) {
						ob.setSendTime(currentTime);
						ob.setStatus(InfoSend.STATUSYESSEND);
						Set<InfoReceiver> irSet = ob.getInfoReceivers();
						Iterator<InfoReceiver> it = irSet.iterator();
						while (it.hasNext()) {
							InfoReceiver irOb = it.next();
							irOb.setStatus(InfoReceiver.STATUSNO);
						}
						publishInfo(ob);
						// 推送发布设备
						InfoReceiverDevice paramsOb = new InfoReceiverDevice();
						paramsOb.setInfoSend(ob);
						List<InfoReceiverDevice> list = infoReceiverDeviceService.queryInfoReceiverDeviceForList(paramsOb);
						if (list!=null&&!list.isEmpty()) {
							publishInfoReceiverDevice(entity, list.get(0).getDeviceTypeId(), entity.getInfoReceivers(), entity.getAreaId().toString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateInfoSend(InfoSend entity, String deviceTypeIds) {
		String updateSql = "delete from InfoReceiver where infoSendId=" + entity.getId();
		super.update(updateSql);
		super.update(entity);
		publishInfo(entity);
		// 更新接收设备
		if (StringUtils.isNotBlank(deviceTypeIds)) {// 更新信息内容
			InfoReceiverDevice paramsOb = new InfoReceiverDevice();
			paramsOb.setInfoSend(entity);
			List<InfoReceiverDevice> list = infoReceiverDeviceService.queryInfoReceiverDeviceForList(paramsOb);
			if (!list.isEmpty()) {
				InfoReceiverDevice resultOb = list.get(0);
				resultOb.setDeviceTypeId(deviceTypeIds);
				infoReceiverDeviceService.update(resultOb);
			} else {// 不存在，添加一条
				InfoReceiverDevice ird = new InfoReceiverDevice();
				ird.setInfoSend(entity);
				ird.setDeviceTypeId(deviceTypeIds);
				ird.setStatus(InfoSend.STATUSNOSEND.toString());
				infoReceiverDeviceService.save(ird);
			}
			// 推送发布设备
			if (entity.getStatus() == InfoSend.STATUSYESSEND) {
				publishInfoReceiverDevice(entity, deviceTypeIds, entity.getInfoReceivers(), entity.getAreaId().toString());
			}
		} else {// 更新状态时
				// 推送发布设备
			if (entity.getStatus() == InfoSend.STATUSYESSEND) {
				InfoReceiverDevice paramsOb = new InfoReceiverDevice();
				paramsOb.setInfoSend(entity);
				List<InfoReceiverDevice> list = infoReceiverDeviceService.queryInfoReceiverDeviceForList(paramsOb);
				if (list!=null&&!list.isEmpty()) {
					publishInfoReceiverDevice(entity, list.get(0).getDeviceTypeId(), entity.getInfoReceivers(), entity.getAreaId().toString());
				}
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveInfoSend(InfoSend entity, String deviceTypeIds) {
		super.save(entity);
		publishInfo(entity);
		// 对于未发布的信息，如果发布设备要先保存到数据库，否则不保存
		if (StringUtils.isNotBlank(deviceTypeIds)) {
			InfoReceiverDevice ird = new InfoReceiverDevice();
			ird.setInfoSend(entity);
			ird.setDeviceTypeId(deviceTypeIds);
			ird.setStatus(InfoSend.STATUSNOSEND.toString());
			infoReceiverDeviceService.save(ird);
			if (entity.getStatus() == InfoSend.STATUSYESSEND) {
				publishInfoReceiverDevice(entity, deviceTypeIds, entity.getInfoReceivers(), entity.getAreaId().toString());
			}
		}
	}

	@Override
	public InfoSend getInfoSend(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelInfoSend(InfoSend entity) {
		InfoReceiverDevice params = new InfoReceiverDevice();
		params.setInfoSend(entity);
		List<InfoReceiverDevice> list = infoReceiverDeviceService.queryInfoReceiverDeviceForList(params);
		if (list!=null&&!list.isEmpty()) {
			infoReceiverDeviceService.DelInfoReceiverDevice(list.get(0));
		}
		super.remove(entity);
	}
	
	@Override
	public Paging<NoticeData> queryNoticeForPaging(String roomNo,
			int pageNum, int pageSize) {
		Paging<NoticeData> paging = new Paging<NoticeData>();
		String areaNo = null;
		String regionNo = null;
		String buildingNo = null;
		String unitNo = null;
		String houseCode = null;
		if(StringUtils.isBlank(roomNo)){
			return paging;
		}
		
		areaNo = roomNo.substring(0,4);
		regionNo = roomNo.substring(4, 6);
		buildingNo = roomNo.substring(6, 9);
		unitNo = roomNo.substring(9, 11);
		houseCode = roomNo.substring(11, 15);
		CellHouseholdInfo house = cellHouseholdInfoService.getByCode(areaNo, regionNo, buildingNo, unitNo, houseCode);
		InfoReceiver infoReceiver = null;
		if(house == null){
			return null;
		}
		infoReceiver = new InfoReceiver();
		infoReceiver.setHouseId(new Long(house.getId()));
		Paging<InfoReceiver> p = infoReceiverService.queryInfoReceiverForPaging(infoReceiver, pageNum, pageSize);
		List<InfoReceiver> infoReceivers = p.getResults();
		List<NoticeData> results = new ArrayList<NoticeData>();
		if(infoReceivers != null && infoReceivers.size()>0){
			for(InfoReceiver ir : infoReceivers){
				InfoSend infoSend = super.get(ir.getInfoSendId());
				NoticeData noticeData = new NoticeData();
				noticeData.setInfoId(infoSend.getId());
				noticeData.setTitle(infoSend.getTitle());
				noticeData.setContent(infoSend.getContent());
				noticeData.setPublishBy(infoSend.getSendUserName());
				noticeData.setPublishTime(infoSend.getSendTime());
				results.add(noticeData);
			}
		}
		
		paging.setPageNum(pageNum);
		paging.setPageSize(pageSize);
		paging.setResults(results);
		paging.setTotalCount(p.getTotalCount());
		return paging;
	}
	

	@Override
	public List<NoticeData> queryNoticeForList(String roomNos) {
		List<NoticeData> list = new ArrayList<NoticeData>();
		String areaNo = null;
		String regionNo = null;
		String buildingNo = null;
		String unitNo = null;
		String houseCode = null;
		List<String> houseIds = new ArrayList<String>();
		if(roomNos==null || "".equals(roomNos)){
			return null;
		}
		String[] roomNoArrays = roomNos.split(",");
		if(roomNoArrays==null || roomNoArrays.length<=0){
			return null;
		}
		//从完整(例如100101002010203)房号中截取房号信息
		for(int i=0;i<roomNoArrays.length;i++){
			areaNo = roomNoArrays[i].substring(0,4);			//1001    区号
			regionNo = roomNoArrays[i].substring(4, 6);		//01         区域号
			buildingNo = roomNoArrays[i].substring(6, 9);	//002      楼栋号
			unitNo = roomNoArrays[i].substring(9, 11);		//01         单元号
			houseCode = roomNoArrays[i].substring(11, 15);	//0203    房间号
			CellHouseholdInfo house = cellHouseholdInfoService.getByCode(areaNo, regionNo, buildingNo, unitNo, houseCode);
			if(house != null){
				houseIds.add(house.getId());
			}
		}
		if(houseIds==null || houseIds.size()<=0){
			return null;
		}		
		List<InfoReceiver> ls = infoReceiverService.queryInfoReceiverByHouseIds(houseIds);
		//去重复
		Set<InfoReceiver> set = new HashSet<InfoReceiver>();
		set.addAll(ls);
		List<InfoReceiver> infoReceivers = new ArrayList<InfoReceiver>();
		infoReceivers.addAll(set);
		//组装NoticeData
		if(infoReceivers != null && infoReceivers.size()>0){
			for(InfoReceiver ir : infoReceivers){
				InfoSend infoSend = super.get(ir.getInfoSendId());
				NoticeData noticeData = new NoticeData();
				if(infoSend !=null){
					noticeData.setInfoId(infoSend.getId());
					noticeData.setTitle(infoSend.getTitle());
					noticeData.setContent(infoSend.getContent());
					noticeData.setPublishBy(infoSend.getSendUserName());
					noticeData.setPublishTime(infoSend.getSendTime());
					list.add(noticeData);
				}			
			}
		}
		return list;
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public IInfoReceiverDeviceService getInfoReceiverDeviceService() {
		return infoReceiverDeviceService;
	}

	public void setInfoReceiverDeviceService(IInfoReceiverDeviceService infoReceiverDeviceService) {
		this.infoReceiverDeviceService = infoReceiverDeviceService;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

}
