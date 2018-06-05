package com.smarthome.socket.service.business.service;

import com.smarthome.socket.service.vo.AuthSession;

public interface IAuthSessionService extends IBaseRestService<AuthSession>{

	AuthSession getBySessionKey(String sessionKey);
	
}
