package com.biencloud.smarthome.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.PositionTrack;
import com.biencloud.smarthome.service.rest.service.IPositionTrackService;

@Controller
@RequestMapping("/pos/track")
public class PositionTrackController extends BaseResController<PositionTrack>{
		
	@Autowired
	private IPositionTrackService positionTrackService;
	
	@Override
	public IBaseResService<PositionTrack> getBaseResService() {
		return positionTrackService;
	}
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> create(String pos) {
		return positionTrackService.addList(pos);
	}
	
	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> add(PositionTrack record) {
		return getBaseResService().addForResultEntity(record);
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<PositionTrack> queryList(PositionTrack record,String beginTime,String endTime) {
		//beginTime = new Date();
		//endTime = new Date();
		return positionTrackService.queryList(record,beginTime,endTime);
	}
}
