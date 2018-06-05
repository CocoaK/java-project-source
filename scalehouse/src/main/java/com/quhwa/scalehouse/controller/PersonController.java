package com.quhwa.scalehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.service.IPersonService;


@Controller
/**增加跨域支持注解*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/person")
public class PersonController{
	
	@Autowired
	private IPersonService personService;
	
	/** 查询某个用户 **/
	@RequestMapping(value="/getOne", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Person> getOne(Integer id) {
		return personService.getOne(id);
	}
	
	/** 获取所有用户 **/
	@RequestMapping(value="/getAll", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Person>> getAll(String countryCode,String type) {
		return personService.getAll(countryCode,type);
	}
	
	/** 增加一名用户 **/
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insert(Person per) {
		return personService.insert(per);
	}
	
	@RequestMapping(value="/register", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insertActive(Person per) {
		return personService.insertActive(per);
	}
	
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Person> login(Person per) {
		return personService.queryByAccountAndPassword(per);
	}
	
	/** 删除用户 **/
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(Integer id) {
		return personService.delete(id);
	}
	
	/** 删除多条数据 **/
	@RequestMapping(value="/deleteGroup", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> deleteGroup(String ids[]) {
		return personService.deleteGroup(ids);
	}
	
	/** 修改用户信息 **/
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> update(Person per) {
		return personService.update(per);
	}
	
	@RequestMapping(value="/updateActive", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateActive(Person per) {
		return personService.updateActive(per);
	}
	
	@RequestMapping(value="/updateEasyui", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateEasyui(Person per) {
		return personService.updateEasyui(per);
	}
	
	
	@RequestMapping(value="/queryForList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Person>> queryForList(Person person) {
		return personService.queryForList(person);
	}
	
	@RequestMapping(value="/getChartsData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type) {
		return personService.getChartsData(intervals,countryCode,type);
	}
	
	@RequestMapping(value="/getPersons", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Long>> getPersons(int intervals,String countryCode,String type){
		return personService.getPersons(intervals, countryCode, type);
	}
	
	//重置密码
	@RequestMapping(value="/toResetPwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> toResetPwd(String account) {
		return personService.toResetPwd(account);
	}
	
	//重置密码
	@RequestMapping(value="/resetPwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ModelAndView resetPwd(String account, String sid,Long timeFlag) {
		ModelAndView view = new ModelAndView();
		ResultEntity<String> re = personService.resetPwd(account, sid, timeFlag);
		if(re.getCode()==1){
			view.addObject("data", re.getData());
			view.setViewName("passwd");
		}else{
			view.setViewName("error");
		}
		return view;
	}
	
	@RequestMapping(value="/getCountry", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Device>> getCountry() {
		return personService.getCountry();
	}
	
	@RequestMapping(value="/personNum", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> personNum(String countryCode,String type) {
		return personService.personNum(countryCode,type);
	}
	
	@RequestMapping(value="/todayActivePerN",method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> todayActivePerN(String countryCode,String type){
		return personService.todayActivePerN(countryCode, type);
	}

}
