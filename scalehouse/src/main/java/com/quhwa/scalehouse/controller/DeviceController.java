package com.quhwa.scalehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.Product;
import com.quhwa.scalehouse.service.scale.service.IBaseService;
import com.quhwa.scalehouse.service.scale.service.IDeviceService;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController<Device>{

	@Autowired
	private IDeviceService deviceService;
	
	@Override
	public IBaseService<Device> getBaseResService() {
		return deviceService;
	}
	
	@RequestMapping(value="/uploadDeviceInfo",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<String> uploadDeviceInfo(Device device){
		return deviceService.insertActive(device);
	}
	
	@RequestMapping(value="/getAll",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Device>> getAll(String countryCode,String type){
		return deviceService.getAll(countryCode,type);
	}
	
	@RequestMapping(value="/getProduct",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Product>> getProduct(){
		return deviceService.getProduct();
	}
	
	@RequestMapping(value="/deviceNum", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> deviceNum(String countryCode,String type) {
		return deviceService.deviceNum(countryCode,type);
	}
	
	@RequestMapping(value="/todayActiveDevN", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> todayActiveDevN(String countryCode,String type) {
		return deviceService.todayActiveDevN(countryCode, type);
	}
	
	@RequestMapping(value="/getChartsData", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type) {
		return deviceService.getChartsData(intervals,countryCode,type);
	}
	
	@RequestMapping(value="/getDevices", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<Long>> getDevices(int intervals,String countryCode,String type){
		return deviceService.getDevices(intervals, countryCode, type);
	}

}
