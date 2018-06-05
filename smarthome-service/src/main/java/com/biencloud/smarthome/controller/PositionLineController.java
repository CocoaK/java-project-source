package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.PositionLine;
import com.biencloud.smarthome.service.rest.service.IPositionLineService;

@Controller
@RequestMapping("/pos/line")
public class PositionLineController extends BaseResController<PositionLine>{
		
	@Autowired
	private IPositionLineService positionLineService;
	
	@Override
	public IBaseResService<PositionLine> getBaseResService() {
		return positionLineService;
	}
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> create(String pos) {
		return positionLineService.addList(pos);
	}
	
	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> add(PositionLine record) {
		return getBaseResService().addForResultEntity(record);
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<PositionLine> queryList(PositionLine record) {
		return positionLineService.queryList(record);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> del(String sipUid) {
		return positionLineService.deleteBySipUid(sipUid);
	}
}
