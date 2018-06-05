package com.biencloud.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.rest.model.DeviceAccessories;
import com.biencloud.smarthome.service.rest.service.IDeviceAccessoriesService;

@Controller
@RequestMapping("/device/accessories")
public class DeviceAccessoriesController extends BaseResController<DeviceAccessories>{
		
	@Autowired
	private IDeviceAccessoriesService deviceAccessoriesService;
	
	@Override
	public IBaseResService<DeviceAccessories> getBaseResService() {
		return deviceAccessoriesService;
	}

	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> create(@RequestBody DeviceAccessories record) {
		return deviceAccessoriesService.save(record);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> del(@RequestBody DeviceAccessories record) {
		return deviceAccessoriesService.deleteByEntity(record);
	}
	
	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody DeviceAccessories get(@RequestBody DeviceAccessories record) {
		return deviceAccessoriesService.getByEntity(record);
	}
	
	@RequestMapping(value="/update/before/search", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateBeforeSearch(DeviceAccessories record) {
		return deviceAccessoriesService.searchDeviceAccessories(record);
	}
	
	@RequestMapping(value="/searchList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<DeviceAccessories>>> searchList(DeviceAccessories record) {
		return deviceAccessoriesService.searchList(record);
	}
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> addList(@RequestBody List<DeviceAccessories> list) {
		return deviceAccessoriesService.addListForResultEntityAndSync(list);
	}
	
	@RequestMapping(value="/clear", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> clear(@RequestBody DeviceAccessories record) {
		return deviceAccessoriesService.deleteByEntity(record);
	}
}
