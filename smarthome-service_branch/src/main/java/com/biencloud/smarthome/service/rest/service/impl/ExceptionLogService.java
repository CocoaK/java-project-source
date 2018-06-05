package com.biencloud.smarthome.service.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.ExceptionLogMapper;
import com.biencloud.smarthome.service.rest.model.ExceptionLog;
import com.biencloud.smarthome.service.rest.service.IExceptionLogService;

@Service
public class ExceptionLogService extends BaseResService<ExceptionLog> implements
		IExceptionLogService {

	@Autowired
	private ExceptionLogMapper exceptionLogMapper;
	
	@Override
	public BaseMapper<ExceptionLog> getBaseMapper() {
		return exceptionLogMapper;
	}
}  
