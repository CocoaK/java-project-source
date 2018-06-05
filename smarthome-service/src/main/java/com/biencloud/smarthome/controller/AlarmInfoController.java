package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.AlarmInfo;
import com.biencloud.smarthome.service.rest.service.IAlarmInfoService;

@Controller
@RequestMapping("/alarm/info")
public class AlarmInfoController extends BaseResController<AlarmInfo>{
		
	@Autowired
	private IAlarmInfoService alarmInfoService;
	
	@Override
	public IBaseResService<AlarmInfo> getBaseResService() {
		return alarmInfoService;
	}

	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> create(@RequestBody AlarmInfo record) {
		return alarmInfoService.addForResultEntity(record);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> del(String ids) {
		return alarmInfoService.deleteAlarms(ids);
	}
}
