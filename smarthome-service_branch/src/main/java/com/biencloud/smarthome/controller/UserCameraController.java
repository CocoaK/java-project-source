package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.model.UserCameraKey;
import com.biencloud.smarthome.service.rest.service.IUserCameraService;

@Controller
@RequestMapping("/user/camera")
public class UserCameraController extends BaseResController<UserCameraKey>{
		
	@Autowired
	private IUserCameraService userCameraService;
	
	@Override
	public IBaseResService<UserCameraKey> getBaseResService() {
		return userCameraService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Camera> queryList(Long userId) {
		return userCameraService.queryListByUserId(userId);
	}

	@RequestMapping(value="/addAndBound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Camera> addAndBound(Camera record) {
		if(record==null){
			return new ResultEntity<Camera>();
		}
		return userCameraService.addAndBound(record);
	}
	
	@RequestMapping(value="/bound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> bound(UserCameraKey record) {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userCameraService.bound(record);
	}
	
	@RequestMapping(value="/unbound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> unbound(UserCameraKey record) {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userCameraService.unbound(record.getUserId(),record.getCameraId());
	}

}
