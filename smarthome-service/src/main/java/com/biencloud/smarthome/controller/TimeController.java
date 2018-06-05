package com.biencloud.smarthome.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.utils.JsonUtil;

@Controller
@RequestMapping("/time")
public class TimeController extends BaseResController{

	@RequestMapping(value="/now", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String getTime() {
		return this.fullTime();
	}
	
	@RequestMapping(value="/now2", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String getTime2() {
		return System.currentTimeMillis()+"";
	}

	@Override
	public IBaseResService getBaseResService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String fullTime(){
		Map<String,String> map = new HashMap<String,String>();
		Calendar cal = Calendar.getInstance();
		map.put("year",cal.get(Calendar.YEAR)+"");
		map.put("month", cal.get(Calendar.MONTH)+1+"");
		//老外把一月份整成了0，翻译成中国月份要加1
		map.put("day", cal.get(Calendar.DAY_OF_MONTH)+"");
		map.put("hour", cal.get(Calendar.HOUR_OF_DAY)+"");
		map.put("min", cal.get(Calendar.MINUTE)+"");
		map.put("sec", cal.get(Calendar.SECOND)+"");
		String json = JsonUtil.objectToJsonString(map);
		return json;
	}
	
}
