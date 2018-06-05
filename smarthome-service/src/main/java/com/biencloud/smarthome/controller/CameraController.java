package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.service.ICameraService;

@Controller
@RequestMapping("/camera")
public class CameraController extends BaseResController<Camera>{
		
	@Autowired
	private ICameraService cameraService;
	
	@Override
	public IBaseResService<Camera> getBaseResService() {
		return cameraService;
	}
}
