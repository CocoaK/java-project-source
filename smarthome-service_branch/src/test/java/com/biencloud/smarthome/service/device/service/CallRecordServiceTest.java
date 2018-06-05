package com.biencloud.smarthome.service.device.service;

import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.CallRecord;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

public class CallRecordServiceTest extends BaseTest{
	
	@Autowired
	private ICallRecordService callRecordService;

	public ICallRecordService getCallRecordService() {
		return callRecordService;
	}

	public void setCallRecordService(ICallRecordService callRecordService) {
		this.callRecordService = callRecordService;
	}
	
	@Test
	@Transactional(propagation = Propagation.SUPPORTS)
	public void save(){
		CallRecord cr = new CallRecord();
		cr.setCaller("caller");
		cr.setDeviceCode("MC0001");
		//cr.setDevice("6");
		//cr.setCallId("");
		cr.setCallTime(new Date());
		cr.setCallType(null);
		callRecordService.saveCallRecord(cr);
	}

	@Test
	@Transactional(propagation = Propagation.SUPPORTS)
	public void query(){
		CallRecord cr = new CallRecord();
		cr.setCallTime(new Date());
		logger.info("查询分页：",callRecordService.queryCallRecordForPaging(cr, 1, 10));
	}
	
	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void query2(){
		CallRecord cr = new CallRecord();
		cr.setDeviceCode("MC0001");
		//cr.setDeviceId("6");
		cr.setCallId("7");
		cr.setCallType("");
		logger.info("根据ID查询通话记录：",callRecordService.getCallRecordById(cr.getCallId()));
	}
	
	@Test
	public void queryCount(){
		CallRecord callRecord = new CallRecord();
		Device device = new Device();
		HousingDistrictInfo hdi = new HousingDistrictInfo();
		hdi.setId("2");
		device.setHousingDistrictInfo(hdi);
		callRecord.setDevice(device);
		Long count = callRecordService.callRecordCount(callRecord);
		logger.info("查询留言记录：{}", count);
	}
	
	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void removeByDeviceId(){
		callRecordService.removeByDeviceId("7");
	}
}
