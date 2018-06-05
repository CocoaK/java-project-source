package com.biencloud.smarthome.service.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.CameraMapper;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.service.ICameraService;

@Service
public class CameraService extends BaseResService<Camera> implements ICameraService{

	@Autowired
	private CameraMapper cameraMapper;
	
	@Override
	public BaseMapper<Camera> getBaseMapper() {
		return cameraMapper;
	}

}
