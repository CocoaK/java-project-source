package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Unlock;
import com.biencloud.smarthome.service.rest.service.IUnlockService;

@Controller
@RequestMapping("/unlock")
public class UnlockController extends BaseResController<Unlock>{
		
	@Autowired
	private IUnlockService unlockService;
	
	@Override
	public IBaseResService<Unlock> getBaseResService() {
		return unlockService;
	}
}
