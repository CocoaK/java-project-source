package com.smarthome.socket.service.business.service;

import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.vo.PushClient;

public interface IPushClientService extends IBaseRestService<PushClient>{
	
	ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception;

	ResultEntity<String> pushDefenceStatusChanged(String data,String mac) throws Exception;

	ResultEntity<String> pushDefenceStatusChanged(String data, String mac,String excludedUserId) throws Exception;
	
}
