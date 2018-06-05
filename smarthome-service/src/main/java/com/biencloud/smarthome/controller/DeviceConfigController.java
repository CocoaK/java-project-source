package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.DeviceConfig;
import com.biencloud.smarthome.service.rest.service.IDeviceConfigService;

@Controller
@RequestMapping("/device/config")
public class DeviceConfigController extends BaseResController<DeviceConfig>{
		
	@Autowired
	private IDeviceConfigService deviceConfigService;

	@Override
	public @ResponseBody ResultEntity<String> add(DeviceConfig deviceConfig) {
		ResultEntity<String> re = deviceConfigService.addForEntity(deviceConfig);
		return re;
	}
	
	@Override
	public @ResponseBody ResultEntity<String> update(DeviceConfig deviceConfig) {
		ResultEntity<String> re = deviceConfigService.updateAndSave(deviceConfig);
		return re;
	}
	
	@Override
	public @ResponseBody ResultEntity<String> delete(Integer deviceId) {
		ResultEntity<String> re = deviceConfigService.deleteByIdForResultEntity(deviceId);
		return re;
	}
	
	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<DeviceConfig> get(Integer id) {
		ResultEntity<DeviceConfig> re = deviceConfigService.getForOneResultEntity(id);
		return re;
	}
	
	@RequestMapping(value="/getByDeviceId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<DeviceConfig> getByDeviceId(Long deviceId) {
		ResultEntity<DeviceConfig> re = deviceConfigService.getByDeviceId(deviceId);
		return re;
	}

	@Override
	public IBaseResService<DeviceConfig> getBaseResService() {
		return deviceConfigService;
	}
	
}
