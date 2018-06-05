package com.biencloud.smarthome.service.gate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.gate.model.GateCard;
import com.biencloud.smarthome.service.gate.model.GatePermissions;
import com.biencloud.smarthome.service.gate.service.IGateCardService;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;

/**
 * 门禁管理领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see BaseService
 * @see IGateCardService
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GateCardServiceImpl extends BaseService<GateCard, String> implements IGateCardService {

	private IPushDao pushDao;
	
	private IDeviceService deviceService;
	
	private ICellHouseholdInfoService cellHouseholdInfoService;
		
	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}
		
	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	
	@Override
	/** 
	 * districtId：必须作为查询条件
	 * cardNo：如果为空不作为查询条件，否则作为模糊查询条件
	 * ownerName：如果为空不作为查询条件，否则作为模糊查询条件
	 * ownerIdCard：如果为空不作为查询条件，否则作为模糊查询条件
	 * status：如果为空不作为查询条件
	 */
	public Paging<GateCard> queryGateCardsForPaging(
			GateCard gateCard, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> params = createParams(jpql, gateCard);
		jpql.append(" ORDER BY gc.createdTime desc");
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM GateCard gc");
		
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "gc");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(gc)");
		
		Paging<GateCard> pag = queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
		if(pag != null){
			List<GateCard> ls = pag.getResults();
			if(ls!=null && ls.size()>0){
				for(GateCard card : ls){
					if(card.getHouseId()!=null && !"".equals(card.getHouseId())){
						String roomNo=cellHouseholdInfoService.getFullHouseNo(card.getHouseId());
						String roomName= cellHouseholdInfoService.getFullHouseName(card.getHouseId());
						card.setRoomNo(roomNo);
						card.setRoomName(roomName);
					}
				}
				
			}
		}
		
		
		return pag;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(GateCard gateCard) {
		getDao().save(gateCard);
		
		List<GatePermissions> list = gateCard.getGatePermissions();
		if(CollectionUtils.isEmpty(list))
			return;
		
		for (GatePermissions gp : list) {
			gp.setGateCardId(gateCard.getGateCardId());
			getDao().saveObject(gp);
		}
		
		//保存新增推送数据
		savePushData(gateCard, list, Constants.PUSH_OP_TYPE_ADD);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(GateCard gateCard) {
		GateCard srcGateCard = get(gateCard.getGateCardId());
		if(srcGateCard == null)
			throw new IllegalArgumentException(
					"获取不到门卡编号为" + gateCard.getGateCardId() + "对应的实体！");
		
		List<GatePermissions> srcList = getOrNewGatePermissions(srcGateCard);		
		List<GatePermissions> currList = getOrNewGatePermissions(gateCard);
		
		//过滤出删除的门卡权限列表
		List removeList = (List) CollectionUtils.subtract(srcList, currList);
		getDao().removeObjects(removeList);
		//保存删除推送数据
		savePushData(gateCard, removeList, Constants.PUSH_OP_TYPE_REMOVE);
		
		//过滤出新增的门卡权限列表
		List<GatePermissions> addList = (List<GatePermissions>)CollectionUtils.subtract(currList, srcList);
		if(CollectionUtils.isNotEmpty(addList)){
			for (GatePermissions gp : addList){
				gp.setGateCardId(gateCard.getGateCardId());				
				getDao().saveObject(gp);
			}
		}
		//保存新增推送数据
		savePushData(gateCard, addList, Constants.PUSH_OP_TYPE_ADD);
		
		//过滤出修改的门卡权限列表
		List updateList = (List)CollectionUtils.retainAll(currList, srcList);
		getDao().updateCollection(updateList);
		//保存修改推送数据
		savePushData(gateCard, updateList, Constants.PUSH_OP_TYPE_UPDATE);
		
		getDao().update(gateCard);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeByIds(String... entityIds) {
		for (String entityId : entityIds) {
			GateCard gc = get(entityId);
			//保存删除推送数据
			savePushData(gc, gc.getGatePermissions(), Constants.PUSH_OP_TYPE_REMOVE);
			
			getDao().remove(gc);			
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGateCardStatus(String gateCardId, String status,
			String updatedUser) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE GateCard gc SET gc.status = ?1, gc.updatedUser = ?2, " +
				"gc.updatedTime = ?3 WHERE gc.gateCardId = ?4");
		Object[] values = {status, updatedUser, new Date(), gateCardId};
		update(jpql.toString(), values);
		
		if(Constants.GATE_CARD_ENABLED.equals(status) 
				|| Constants.GATE_CARD_DISABLED.equals(status)){
			GateCard gateCard = getDao().get(gateCardId);
			String opType = Constants.PUSH_OP_TYPE_STOP;
			if(Constants.GATE_CARD_ENABLED.equals(status))
				opType = Constants.PUSH_OP_TYPE_RESUME;
			//保存启用或禁用推送数据
			savePushData(gateCard, gateCard.getGatePermissions(), opType);
		}
	}
	
	/** 
	 * 同一个小区的门卡号不能重复，不同小区允许重复。
	 * 1）如果门卡编号为空，则验证是否存在卡号；<br/>
	 * 2）如果门卡编号不为空，则验证卡号对应的门卡编号是否和当前门卡编号相同；<br/>
	 */
	@Override
	public boolean existCardNo(GateCard gateCard) {
		String jpql = "SELECT gc.gateCardId FROM GateCard gc WHERE gc.cardNo = ?1 AND gc.districtId = ?2";
		List<String> gcIds = findIds(jpql, gateCard.getCardNo(), gateCard.getDistrictId());
		if(CollectionUtils.isEmpty(gcIds))
			return false;
		
		if(StringUtils.isEmpty(gateCard.getGateCardId()) 
				|| gcIds.size() > 1)
			return true;
		
		return (!gcIds.contains(gateCard.getGateCardId()));
	}

	@Override
	public List<GateCard> queryByDeviceCode(String deviceCode) {
		String jpql = "SELECT gc FROM GateCard gc JOIN gc.gatePermissions gp WHERE gp.device.deviceCode = ?1";	
		return find(jpql, deviceCode);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeGatePermissionsDevices(List<String> deviceIds) {
		if(CollectionUtils.isEmpty(deviceIds))
			return;
		
		removeByParams("DELETE FROM GatePermissions gp WHERE gp.device.deviceId IN ( ?1 )", deviceIds);
	}
	
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(
			StringBuilder jpql, GateCard gateCard) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(gateCard == null)
			return params;
		
		if(StringUtils.isNotBlank(gateCard.getCardNo()))
			appendCondition(jpql, "gc.cardNo LIKE :cardNo", "cardNo", 
					"%" + gateCard.getCardNo() + "%", params);
		
		if(StringUtils.isNotBlank(gateCard.getOwnerName()))
			appendCondition(jpql, "gc.ownerName LIKE :ownerName", "ownerName", 
					"%" + gateCard.getOwnerName() + "%", params);
		
		if(StringUtils.isNotBlank(gateCard.getOwnerIdCard()))
			appendCondition(jpql, "gc.ownerIdCard LIKE :ownerIdCard", "ownerIdCard", 
					"%" + gateCard.getOwnerIdCard() + "%", params);
		
		appendCondition(jpql, "gc.districtId = :districtId", "districtId", 
				gateCard.getDistrictId(), params);
		
		appendCondition(jpql, "gc.houseId = :houseId", "houseId", 
				gateCard.getHouseId(), params);
		
		appendCondition(jpql, "gc.status = :status", 
				"status", gateCard.getStatus(), params);
		
		return params;
	}
	
	//获取门卡的权限列表，如果为空则新建一个空的列表
	private List<GatePermissions> getOrNewGatePermissions(GateCard gateCard){
		List<GatePermissions> list = new ArrayList<GatePermissions>();
		if(gateCard == null || CollectionUtils.isEmpty(gateCard.getGatePermissions()))
			return list;
			
		return gateCard.getGatePermissions();
	}
	
	//保存推送数据
	private void savePushData(GateCard gateCard, List<GatePermissions> gps,
			String operationType) {
		if (CollectionUtils.isEmpty(gps))
			return;

		for (GatePermissions gp : gps) {
			Push push = new Push();
			push.setPushName("GateCard");
			push.setPushKind(PushKindConstants.PUSH_GATEDATA_KIND);
			push.setAddTime(new Date());
			String deviceCode = getDeviceService().getDeviceCodeById(
					gp.getDevice().getDeviceId());
			push.setPushClientId(deviceCode);
			push.setPushContent(buildPushContent(gateCard, gp, operationType,
					deviceCode));
			getPushDao().save(push);
		}
	}

	// 构建推送内容
	private String buildPushContent(GateCard gateCard, GatePermissions gp,
			String operationType, String deviceCode) {
		StringBuilder pushContent = new StringBuilder();
		pushContent.append("operationType=");
		pushContent.append(operationType);
		pushContent.append(",deviceNo=");
		pushContent.append(deviceCode);
		pushContent.append(",cardNo=");
		pushContent.append(gateCard.getCardNo());
		pushContent.append(",status=");
		pushContent.append(gateCard.getStatus());
		pushContent.append(",beginTime=");
		long beginTime = 0L;
		if (gp.getBeginTime() != null)
			beginTime = gp.getBeginTime().getTime();
		pushContent.append(beginTime);
		pushContent.append(",endTime=");
		long endTime = 0L;
		if (gp.getEndTime() != null)
			endTime = gp.getEndTime().getTime();
		pushContent.append(endTime);
		return pushContent.toString();
	}

	@Override
	public GateCard queryGateCardByCardNo(String districtId,String cardNo) {
		GateCard gateCard = null;
		String jpql = "SELECT gc FROM GateCard gc WHERE gc.districtId = ?1 AND gc.cardNo = ?2";
		List<GateCard> list = find(jpql,districtId,cardNo);
		if(list != null && list.size() != 0){
			gateCard = list.get(0);
			String roomNo=cellHouseholdInfoService.getFullHouseNo(gateCard.getHouseId());
			String roomName= cellHouseholdInfoService.getFullHouseName(gateCard.getHouseId());
			gateCard.setRoomNo(roomNo);
			gateCard.setRoomName(roomName);
		}
		return gateCard;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
	
}
