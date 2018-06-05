package com.smarthome.socket.service.business.service;

import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.vo.AlarmInfo;

public interface IAlarmService extends IBaseRestService<AlarmInfo>{
	
	ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception;

	ResultEntity<String> save(String data, String mac);
	
}
