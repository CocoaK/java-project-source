package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.MsgToken;
import com.biencloud.smarthome.service.rest.service.IMsgTokenService;

@Controller
@RequestMapping("/msg/token")
public class MsgTokenController extends BaseResController<MsgToken>{
		
	@Autowired
	private IMsgTokenService msgTokenService;
	
	@Override
	public IBaseResService<MsgToken> getBaseResService() {
		return msgTokenService;
	}

	@Override
	public @ResponseBody ResultEntity<String> add(MsgToken entity) {
		return msgTokenService.saveOrUpdate(entity);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> get(MsgToken entity) {
		return msgTokenService.delete(entity);
	}
}
