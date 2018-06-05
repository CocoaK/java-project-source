package com.quhwa.scalehouse.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.model.ResultList;
import com.quhwa.scalehouse.common.page.Page;
import com.quhwa.scalehouse.service.scale.model.BodyInfo;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.service.IBaseService;
import com.quhwa.scalehouse.service.scale.service.IBodyInfoService;

@Controller
/**增加跨域支持注解*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bodyInfo")

public class BodyInfoController extends BaseController<BodyInfo>{

	@Autowired
	private IBodyInfoService bodyInfoService;
	
	
	/** 获取所有数据 **/
	@RequestMapping(value="/getList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getList(Person per) {
		return bodyInfoService.getList(per);
	}
	
	/** 获取当天数据 **/
	@RequestMapping(value="/getTodayData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getTodayData(Person per) {
		return bodyInfoService.getTodayData(per);
	}
	
	/** 获取某一天数据 **/
	@RequestMapping(value="/getOtherdayData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getOtherdayData(Date recordTime,String account,String password) {
		return bodyInfoService.getOtherdayData(recordTime,account,password);
	}
	
	/** 获取最近七天数据 **/
	@RequestMapping(value="/getSevenDaysData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getSevenDaysData(Person per){
		return bodyInfoService.getSevenDaysData(per);
	}
	
	/** 获取当月数据 **/
	@RequestMapping(value="/getCurrentMonthData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getCurrentMonthData(Person per){
		return bodyInfoService.getCurrentMonthData(per);
	}
	
	/** 获取某一段时间内的数据 **/
	@RequestMapping(value="/getByTimeslot", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getByTiemslot(Date startTime,Date endTime,String account,String password){
		return bodyInfoService.getByTimesolt(startTime, endTime, account, password);
	}
	
	/** 获取某账户下的所有数据 **/
	@RequestMapping(value="/getByPersonId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> getPersonId(Person per) {
		return bodyInfoService.getByPersonId(per);
	}
	
	/** 获取小于或等于某个weight的数据 **/
	@RequestMapping(value="/queryByWeight",method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> queryByWeight(Double weight){
		return bodyInfoService.queryByWeight(weight);
	}
	
	/** 获取大于或等于某个weight的数据 **/
	@RequestMapping(value="/queryByHighWeight",method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> queryByHighWeight(Double weight){
		return bodyInfoService.queryByHighWeight(weight);
	}
	
	/** 通过id获取某一条数据 **/
	@RequestMapping(value="/getOne", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<BodyInfo> getOne(Integer id) {
		return bodyInfoService.getOne(id);
	}
	
	/** 向数据库中插入数据 **/
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insert(BodyInfo per) {
		return bodyInfoService.insert(per);
	}
	
	@RequestMapping(value="/insertActive", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insertActive(BodyInfo per) {
		return bodyInfoService.insertActive(per);
	}
	
	@RequestMapping(value="/insertJsonGroup", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> insertJsonGroup(String jsonGroup,String account,String password) {
		return bodyInfoService.insertJsonGroup(jsonGroup,account,password);
	}
	
	/** 删除某条数据 **/
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(Integer id) {
		return bodyInfoService.delete(id);
	}
	
	/** 删除多条数据 **/
	@RequestMapping(value="/deleteGroup", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> deleteGroup(String ids[]) {
		return bodyInfoService.deleteGroup(ids);
	}
	
	/** 修改某一条数据 **/
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> update(BodyInfo per) {
		return bodyInfoService.update(per);
	}
	
	@RequestMapping(value="/updateActive", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateActive(BodyInfo per) {
		return bodyInfoService.updateActive(per);
	}
	
	/** 获取某账户下的所有数据 **/
	@RequestMapping(value="/queryByPersonId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<BodyInfo>> queryByPersonId(Person per) {
		return bodyInfoService.queryByPersonId(per);
	}

	/** 获取分页数据 **/
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<BodyInfo>>> queryList(Page<BodyInfo> page,@RequestParam Map<String,String> obj) {
		return bodyInfoService.getForResultList(page, obj);
	}

	@Override
	public IBaseService<BodyInfo> getBaseResService() {
		// TODO Auto-generated method stub
		return bodyInfoService;
	}
}
