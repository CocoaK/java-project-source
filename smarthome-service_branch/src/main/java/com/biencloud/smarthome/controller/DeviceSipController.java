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
import com.biencloud.smarthome.service.rest.model.DeviceSip;
import com.biencloud.smarthome.service.rest.service.IDeviceSipService;

@Controller
@RequestMapping("/device/sip")
public class DeviceSipController extends BaseResController<DeviceSip>{
		
	@Autowired
	private IDeviceSipService deviceSipService;
	
	@Override
	public @ResponseBody ResultEntity<String> add(@RequestBody DeviceSip record) {
		ResultEntity<String> re = deviceSipService.addForResultEntity(record);
		return re;
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<DeviceSip> query(@RequestBody DeviceSip deviceSip) {
		List<DeviceSip> qrcodes = deviceSipService.queryList(deviceSip);
		return qrcodes;
	}

	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(@RequestBody DeviceSip deviceSip) {
		ResultEntity<String> re = deviceSipService.delete(deviceSip);
		return re;
	}

	@Override
	public IBaseResService<DeviceSip> getBaseResService() {
		return deviceSipService;
	}
	
}
