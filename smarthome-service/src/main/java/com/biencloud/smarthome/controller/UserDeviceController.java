package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.DeviceUserVo;
import com.biencloud.smarthome.service.rest.model.UserDeviceKey;
import com.biencloud.smarthome.service.rest.service.IUserDeviceService;

@Controller
@RequestMapping("/user/device")
public class UserDeviceController extends BaseResController<UserDeviceKey>{
		
	@Autowired
	private IUserDeviceService userDeviceService;
	
	@Override
	public IBaseResService<UserDeviceKey> getBaseResService() {
		return userDeviceService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Device> queryList(Long userId) {
		return userDeviceService.queryList(userId);
	}
	
	@RequestMapping(value="/queryDeviceList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Device> queryDeviceList(Long userId) {
		return userDeviceService.queryDeviceList(userId);
	}

	@RequestMapping(value="/bound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> bound(UserDeviceKey record) {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userDeviceService.bound(record.getUserId(), record.getDeviceCode(), record.getDevicePwd());
	}
	
	/**
	 * 楼宇对讲app绑定室内机
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/boundDevice", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Device> boundDevice(UserDeviceKey record) {
		if(record==null){
			return new ResultEntity<Device>();
		}
		return userDeviceService.boundDevice(record.getUserId(), record.getDeviceCode(), record.getDevicePwd());
	}
	
	@RequestMapping(value="/unbound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> unbound(UserDeviceKey record) {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userDeviceService.unbound(record.getUserId(),record.getDeviceCode());
	}
	
	@RequestMapping(value="/searchBound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> searchBound(UserDeviceKey record) throws InterruptedException {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userDeviceService.searchBound(record.getUserId(),record.getDeviceCode());
	}
	
	@RequestMapping(value="/deviceUserList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<DeviceUserVo> getExcludeList(UserDeviceKey record) throws InterruptedException {
		return userDeviceService.getDeviceUserList(record);
	}
	
	@RequestMapping(value="/userList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<DeviceUserVo> getUserListByDeviceId(UserDeviceKey record) throws InterruptedException {
		return userDeviceService.getUserListByDeviceId(record);
	}
}
