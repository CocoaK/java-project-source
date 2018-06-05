package com.smarthome.socket.service.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.BaseRestService;
import com.smarthome.socket.service.business.service.IAuthSessionService;
import com.smarthome.socket.service.vo.AuthSession;

public class AuthSessionService extends BaseRestService<AuthSession> implements IAuthSessionService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	@Override
	public AuthSession getBySessionKey(String sessionKey) {
		super.setQueryUrl("/auth/session/getBySessionKey");
		AuthSession auth = new AuthSession();
		auth.setSessionKey(sessionKey);
		ResultEntity<AuthSession> re = super.postForObject(restServiceUrl+queryUrl, auth, new ParameterizedTypeReference<ResultEntity<AuthSession>>(){});
		if(re.getCode()!=ResultEntity.SUCCESS){
			return null;
		}
		return re.getData();
	}
}
