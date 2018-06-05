package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.model.UserCameraKey;

public interface IUserCameraService extends IBaseResService<UserCameraKey>{
	
	List<Camera> queryListByUserId(Long userId);
	
	ResultEntity<String> unbound(Long userId,Long cameraId);
	
	ResultEntity<Camera> addAndBound(Camera camera);
	
	ResultEntity<String> bound(UserCameraKey camera);
	
	List<UserCameraKey> getList(UserCameraKey userCameraKey);

}
