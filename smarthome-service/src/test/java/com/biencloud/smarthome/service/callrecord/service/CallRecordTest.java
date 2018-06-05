package com.biencloud.smarthome.service.callrecord.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.device.model.CallRecord;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.ICallRecordService;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;


public class CallRecordTest extends BaseTest{
	
	@Autowired
	private ICallRecordService callRecord;
	
	//@Test
	//@Transactional(propagation = Propagation.NEVER)
	public void save(){
		CallRecord cr = new CallRecord();
		cr.setCaller("caller");
		cr.setDeviceCode("MC0001");
		//cr.setDevice("6");
		//cr.setCallId("");
		cr.setCallTime(new Date());
		cr.setCallType(null);
		getCallRecord().saveCallRecord(cr);
	}

	@Test
	//@Transactional(propagation = Propagation.NEVER)
	public void query(){
		//CallRecord cr = new CallRecord();
		//cr.setCallTime(new Date());
		//cr.setDeviceId("6");
		String ip = "192.168.12.212";
		System.out.println("--------------:"+ip.lastIndexOf("."));
		String subip = ip.substring(0,ip.lastIndexOf("."));
		System.out.println("subip:"+subip);
		System.out.println("ip2:"+subip.substring(subip.lastIndexOf(".")+1));
		//System.out.println("--------------getCallRecord:"+getCallRecord().queryCallRecordForPaging(cr, 1, 10));
	}
	
	//@Test
	//@Transactional(propagation = Propagation.NEVER)
	public void query2(){
		CallRecord cr = new CallRecord();
		cr.setDeviceCode("MC0001");
		//cr.setDeviceId("6");
		cr.setCallId("7");
		cr.setCallType("");
		System.out.println("--------------getCallRecordById:"+getCallRecord().getCallRecordById(cr.getCallId()));
	}

	public ICallRecordService getCallRecord() {
		return callRecord;
	}

	public void setCallRecord(ICallRecordService callRecord) {
		this.callRecord = callRecord;
	}
	
	@Test
	public void queryCount(){
		CallRecord callRecord = new CallRecord();
		Device device = new Device();
		HousingDistrictInfo hdi = new HousingDistrictInfo();
		hdi.setId("2");
		device.setHousingDistrictInfo(hdi);
		callRecord.setDevice(device);
		Long count = getCallRecord().callRecordCount(callRecord);
		logger.info("查询留言记录：{}", count);
	}
	
}
