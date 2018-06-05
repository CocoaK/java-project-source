package com.biencloud.smarthome.service.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.CallRecordMapper;
import com.biencloud.smarthome.service.rest.model.CallRecord;
import com.biencloud.smarthome.service.rest.service.ICallRecordsService;

@Service
public class CallRecordsService extends BaseResService<CallRecord> implements
		ICallRecordsService {

	@Autowired
	private CallRecordMapper callRecordMapper;
	
	@Override
	public BaseMapper<CallRecord> getBaseMapper() {
		return callRecordMapper;
	}
	
}  
