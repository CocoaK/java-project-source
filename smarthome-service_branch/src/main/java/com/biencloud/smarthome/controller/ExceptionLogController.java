package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.ExceptionLog;
import com.biencloud.smarthome.service.rest.service.IExceptionLogService;

@Controller
@RequestMapping("/exception/log")
public class ExceptionLogController extends BaseResController<ExceptionLog>{
		
	@Autowired
	private IExceptionLogService exceptionLogService;
	
	@Override
	public IBaseResService<ExceptionLog> getBaseResService() {
		return exceptionLogService;
	}
}
