package com.biencloud.smarthome.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.AuthSession;
import com.biencloud.smarthome.service.rest.service.IAuthSessionService;


@Controller
@RequestMapping("/auth/session")
public class AuthSessionController extends BaseResController<AuthSession>{
		
	@Autowired
	private IAuthSessionService authSessionService;

	@Override
	public IBaseResService<AuthSession> getBaseResService() {
		return authSessionService;
	}
	
	@RequestMapping(value="/getBySessionKey", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<AuthSession> getBySessionKey(@RequestBody AuthSession authSession) {
		ResultEntity<AuthSession> re =  proccessResultEntity(ResultEntity.FAILD, "", null);
		if(authSession==null || StringUtils.isBlank(authSession.getSessionKey())){
			return re;
		}
		
		AuthSession auth = authSessionService.getBySessionKey(authSession.getSessionKey());
		if(auth==null){
			return re;
		}
		return proccessResultEntity(ResultEntity.SUCCESS, "", auth);
	}
}
