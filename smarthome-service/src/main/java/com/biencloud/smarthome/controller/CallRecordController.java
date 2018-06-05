package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.CallRecord;
import com.biencloud.smarthome.service.rest.service.ICallRecordsService;

@Controller
@RequestMapping("/call/record")
public class CallRecordController extends BaseResController<CallRecord>{
		
	@Autowired
	private ICallRecordsService callRecordsService;
	
	@Override
	public IBaseResService<CallRecord> getBaseResService() {
		return callRecordsService;
	}
}
