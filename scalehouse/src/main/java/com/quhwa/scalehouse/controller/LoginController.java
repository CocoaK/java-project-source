package com.quhwa.scalehouse.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Login;
import com.quhwa.scalehouse.service.scale.service.IBaseService;
import com.quhwa.scalehouse.service.scale.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController<Login>{

	@Autowired
	private ILoginService loginService;
	
	@Override
	public IBaseService<Login> getBaseResService() {
		return loginService;
	}
	
	@RequestMapping(value="/uploadLogin", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insertActive(String account,String loginTimeGroup) {
		return loginService.insertActive(account,loginTimeGroup);
	}

}
