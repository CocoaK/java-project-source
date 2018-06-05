package com.biencloud.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntry;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntryJson;
import com.biencloud.smarthome.service.rest.service.IRoomMobileEntryService;

@Controller
@RequestMapping("/room/mobile/entry")
public class RoomMobileEntryController extends BaseResController<RoomMobileEntry>{
		
	@Autowired
	private IRoomMobileEntryService roomMobileEntryService;
	
	@Override
	public IBaseResService<RoomMobileEntry> getBaseResService() {
		return roomMobileEntryService;
	}
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<RoomMobileEntryJson>> addList(String jsonString) {
		return roomMobileEntryService.addList(jsonString);
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<RoomMobileEntry>> queryList(RoomMobileEntry roomMobileEntry) {
		return roomMobileEntryService.queryList(roomMobileEntry);
	}

}
