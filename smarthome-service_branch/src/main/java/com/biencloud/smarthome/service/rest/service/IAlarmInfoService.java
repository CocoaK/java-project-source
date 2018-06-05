package com.biencloud.smarthome.service.rest.service;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.AlarmInfo;

public interface IAlarmInfoService extends IBaseResService<AlarmInfo>{
	
	void pushAlarmToClient(AlarmInfo alarmInfo);
	
	ResultEntity<String> deleteAlarms(String ids);
	
}
