package com.biencloud.smarthome.service.device.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.ICallRecordDao;
import com.biencloud.smarthome.service.device.model.CallRecord;
import com.biencloud.smarthome.service.device.service.ICallRecordService;
/**
 * 类名称：CallRecordServiceImpl 
 * 类描述： 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 下午2:05:01
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class CallRecordServiceImpl extends BaseService<CallRecord,String> implements ICallRecordService{

	private ICallRecordDao callRecordDao;
	
	@Override
	public CallRecord getCallRecordById(String id) {
		return getCallRecordDao().get(id);
	}

	@Override
	public Paging<CallRecord> queryCallRecordForPaging(CallRecord callRecord, int pageNum,int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql,callRecord);
		jpql.insert(0, "SELECT "+ REPLACE_CHARS +" FROM CallRecord callRecord");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "callRecord");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(callRecord)");
		queryString = queryString + " order by callRecord.callTime desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveCallRecord(CallRecord callRecord) {
		getCallRecordDao().saveOrUpdate(callRecord);
		
	}
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			CallRecord callRecord) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (callRecord == null)
			return params;
		if (StringUtils.isNotBlank(callRecord.getId()))
			appendCondition(jpql, "callRecord.id = :id", "id", 
					callRecord.getId(), params);
		
		if (StringUtils.isNotBlank(callRecord.getCallId()))
			appendCondition(jpql, "callRecord.callId = :callId", "callId", 
					callRecord.getCallId(), params);

		if (callRecord.getDevice()!=null && StringUtils.isNotBlank(callRecord.getDevice().getDeviceId()))
			appendCondition(jpql, "callRecord.device.deviceId = :deviceId", "deviceId",
					callRecord.getDevice().getDeviceId(), params);

		if (callRecord.getDevice()!=null && StringUtils.isNotBlank(callRecord.getDevice().getDeviceCode()))
			appendCondition(jpql, "callRecord.device.deviceCode like :deviceCode",
					"deviceCode","%"+callRecord.getDevice().getDeviceCode()+"%", params);
		
		if (StringUtils.isNotBlank(callRecord.getCallType()))
			appendCondition(jpql, "callRecord.callType = :callType",
					"callType",callRecord.getCallType(), params);
		
		if (StringUtils.isNotBlank(callRecord.getCaller()))
			appendCondition(jpql, "callRecord.caller like :caller",
					"caller","%"+callRecord.getCaller()+"%", params);
		
		if (StringUtils.isNotBlank(callRecord.getDeviceCode()))
			appendCondition(jpql, "callRecord.deviceCode = :deviceNo",
					"deviceNo",callRecord.getDeviceCode(), params);

		if (callRecord.getDevice()!=null && StringUtils.isNotBlank(callRecord.getDevice().getDeviceName()))
			appendCondition(jpql, "callRecord.device.deviceName like :deviceName",
					"deviceName","%"+callRecord.getDevice().getDeviceName()+"%", params);
		
		if (callRecord.getDevice()!=null && callRecord.getDevice().getCellHouseholdInfo()!=null 
				&& callRecord.getDevice().getCellHouseholdInfo().getOwner()!=null 
				&& StringUtils.isNotBlank(callRecord.getDevice().getCellHouseholdInfo().getOwner().getHouseId()))
			appendCondition(jpql, "callRecord.device.cellHouseholdInfo.id = :houseId",
					"houseId",callRecord.getDevice().getCellHouseholdInfo().getOwner().getHouseId(), params);
		
		if (callRecord.getDevice()!=null && callRecord.getDevice().getHousingDistrictInfo() != null 
				&& StringUtils.isNotBlank(callRecord.getDevice().getHousingDistrictInfo().getId()))
			appendCondition(jpql, "callRecord.device.housingDistrictInfo.id = :districtId",
					"districtId",callRecord.getDevice().getHousingDistrictInfo().getId(), params);

		appendCondition(jpql, "callRecord.callTime >= :beginTime", "beginTime",
				callRecord.getBeginTime(), params);
		
		appendCondition(jpql, "callRecord.callTime <= :endTime", "endTime",
				callRecord.getEndTime(), params);

		return params;
	}
	
	public ICallRecordDao getCallRecordDao() {
		return callRecordDao;
	}

	public void setCallRecordDao(ICallRecordDao callRecordDao) {
		this.callRecordDao = callRecordDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCallRecord(CallRecord callRecord) {
		getCallRecordDao().remove(callRecord);
		
	}

	@Override
	public List<CallRecord> queryCallRecords(CallRecord callRecord) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, callRecord);
		jpql.insert(0, "SELECT callRecord FROM CallRecord callRecord");
		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	public Long callRecordCount(CallRecord callRecord) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, callRecord);
		jpql.insert(0, "SELECT COUNT(callRecord) FROM CallRecord callRecord");
		return getTotalCountByNamedParams(jpql.toString(), params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeByDeviceId(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return;
		}
		String removeString = "DELETE FROM CallRecord WHERE device.deviceId=?1";
		super.removeByParams(removeString, deviceId);
	}

}
