/**
 * 
 */
package com.quhwa.scalehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.PersonDeviceMac;
import com.quhwa.scalehouse.service.scale.service.IBaseService;
import com.quhwa.scalehouse.service.scale.service.IPersonDeviceMacService;

/** 
 * @Title:        PersonDeviceMacController 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-25 下午2:46:07 
 */
@Controller
@RequestMapping("/mac")
public class PersonDeviceMacController extends BaseController<PersonDeviceMac>{

	@Autowired
	private IPersonDeviceMacService personDeviceMacService;
	
	@Override
	public IBaseService<PersonDeviceMac> getBaseResService() {
		return personDeviceMacService;
	}
	
	@RequestMapping(value="/getMac",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<List<PersonDeviceMac>> getMac(String account){
		return personDeviceMacService.getMac(account);
	}
	
	@RequestMapping(value="/uploadMac",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<String> uploadMac(String macGroup,String account){
		return personDeviceMacService.uploadMac(macGroup, account);
	}
}
