package com.biencloud.smarthome.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.service.IDevicesService;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseResController<Device>{
		
	@Autowired
	private IDevicesService devicesService;

	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Device> get(Integer deviceId) {
		ResultEntity<Device> re = devicesService.getForOneResultEntity(deviceId);
		return re;
	}
	
	@RequestMapping(value="/getId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Long> getIdByDeviceNo(@RequestBody Map<String,String> map) {
		return devicesService.getIdByDeviceNo((String)map.get("deviceNo"));
	}
	
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<AppLoginVO> login(AppLoginVO appLoginVO) {
		ResultEntity<AppLoginVO> re = devicesService.login(appLoginVO);
		return re;
	}

	@Override
	public IBaseResService<Device> getBaseResService() {
		return devicesService;
	}
	
}
