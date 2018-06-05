package com.biencloud.smarthome.service.rest.service;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.AuthSession;

public interface IAuthSessionService extends IBaseResService<AuthSession>{
		
	ResultEntity<String> save(String sessionKey, Long userId);
	
	ResultEntity<AuthSession> getByUserId(long userId);
	
	AuthSession getBySessionKey(String sessionKey);
	
	ResultEntity<String> deleteBySessionKey(String sessionKey);
	
}
