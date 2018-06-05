package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Position;
import com.biencloud.smarthome.service.rest.service.IPositionService;

@Controller
@RequestMapping("/pos")
public class PositionController extends BaseResController<Position>{
		
	@Autowired
	private IPositionService positionService;
	
	@Override
	public IBaseResService<Position> getBaseResService() {
		return positionService;
	}
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> create(String pos) {
		return positionService.addList(pos);
	}
	
	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> add(Position record) {
		return getBaseResService().addForResultEntity(record);
	}
}
