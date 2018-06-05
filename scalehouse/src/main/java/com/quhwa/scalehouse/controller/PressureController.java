package com.quhwa.scalehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.Pressure;
import com.quhwa.scalehouse.service.scale.service.IPressureService;

@Controller
/**增加跨域支持注解*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pressure")
public class PressureController{

	@Autowired
	private IPressureService pressureService;
	
	
	/** 上传数据 **/
	@RequestMapping(value="/upload", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> upload(String jsonGroup,String account,String password) {
		return pressureService.upload(jsonGroup, account, password);
	}
	
	/** 获取所有数据 **/
	@RequestMapping(value="/download", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Pressure>> getList(Person per) {
		return pressureService.download(per);
	}
}
