package com.quhwa.scalehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Background;
import com.quhwa.scalehouse.service.scale.service.IBackgroundService;
import com.quhwa.scalehouse.service.scale.service.IBaseService;

@Controller
@RequestMapping("/background")
public class BackgroundController extends BaseController<Background>{

	@Autowired
	private IBackgroundService backgroundService;
	
	@Override
	public IBaseService<Background> getBaseResService() {
		return backgroundService;
	}
	
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Background>> login(Background back){
		return backgroundService.queryByUserAndPassword(back);
	}
	
}
