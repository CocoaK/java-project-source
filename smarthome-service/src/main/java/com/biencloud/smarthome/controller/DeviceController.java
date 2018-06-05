package com.biencloud.smarthome.controller;

import java.util.List;
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
import com.biencloud.smarthome.service.rest.model.DeviceJson;
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
	
	@RequestMapping(value="/queryDevice", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<DeviceJson> queryDevice(Device device) {
		return devicesService.getDeviceJosn(device);
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
	
	@RequestMapping(value="/updateByDeviceNo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateByDeviceNo(Device device) {
		ResultEntity<String> re = devicesService.updateByDeviceNo(device);
		return re;
	}
	
	@RequestMapping(value="/updateAllDeviceStatus", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateAllDeviceStatus(@RequestBody Map<String,String> map) {
		String status = map.get("status");
		ResultEntity<String> re = devicesService.updateAllDeviceStatus(status);
		return re;
	}
	
	@RequestMapping(value="/getById", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Device> getById(@RequestBody Map<String,String> map) {
		String deviceId = map.get("deviceId");
		if(deviceId!=null && !"".equals(deviceId)){
			return devicesService.getForOneResultEntity(Integer.parseInt(deviceId));
		}
		return new ResultEntity<Device>();
	}
	
	@RequestMapping(value="/getByDeviceNo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Device> getByDeviceNo(String deviceNo) {
		ResultEntity<Device> re = new ResultEntity<Device>(ResultEntity.FAILD,"",null);
		if(deviceNo!=null && !"".equals(deviceNo)){
			Device dev = devicesService.getByDeviceNo(deviceNo);
			re.setCode(ResultEntity.SUCCESS);
			re.setData(dev);
		}
		return re;
	}
	
	@RequestMapping(value="/getEntityByDeviceNo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Device> getEntityByDeviceNo(@RequestBody Map<String,String> map) {
		ResultEntity<Device> re = new ResultEntity<Device>(ResultEntity.FAILD,"",null);
		String deviceNo = map.get("deviceNo");
		if(deviceNo!=null && !"".equals(deviceNo)){
			Device dev = devicesService.getByDeviceNo(deviceNo);
			re.setCode(ResultEntity.SUCCESS);
			re.setData(dev);
		}
		return re;
	}

	@Override
	public IBaseResService<Device> getBaseResService() {
		return devicesService;
	}
	
}
