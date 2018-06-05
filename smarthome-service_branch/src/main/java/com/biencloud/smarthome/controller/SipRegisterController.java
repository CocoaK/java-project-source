package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import com.biencloud.smarthome.service.sip.service.ISipRegisterService;

//sip Controller
@Controller
@RequestMapping("/sip/reg")
public class SipRegisterController extends BaseResController<SipRegister>{
		
	@Autowired
	private ISipRegisterService sipRegisterService;

	@Override
	public IBaseResService<SipRegister> getBaseResService() {
		return sipRegisterService;
	}
	
	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<SipRegister> create(@RequestBody SipRegister record) {
		ResultEntity<SipRegister> re = sipRegisterService.register(record);
		return re;
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delByUsername(@RequestBody String username) {
		return sipRegisterService.deleteByUsername(username);
	}
	
	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<SipRegister> getByUsername(@RequestBody String username) {
		return sipRegisterService.getByUsername(username);
	}
	
	@RequestMapping(value="/register", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<SipRegister> register(SipRegister record) {
		ResultEntity<SipRegister> re = sipRegisterService.register(record);
		return re;
	}
}
