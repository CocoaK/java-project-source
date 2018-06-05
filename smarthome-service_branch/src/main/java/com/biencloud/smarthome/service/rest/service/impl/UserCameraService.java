package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.CameraMapper;
import com.biencloud.smarthome.service.rest.mapper.UserCameraMapper;
import com.biencloud.smarthome.service.rest.mapper.UserMapper;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.model.User;
import com.biencloud.smarthome.service.rest.model.UserCameraKey;
import com.biencloud.smarthome.service.rest.service.IUserCameraService;

@Service
public class UserCameraService extends BaseResService<UserCameraKey> implements
		IUserCameraService {

	@Autowired
	private UserCameraMapper userCameraMapper;
	
	@Autowired
	private CameraMapper cameraMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public BaseMapper<UserCameraKey> getBaseMapper() {
		return userCameraMapper;
	}

	@Override
	public ResultEntity<String> unbound(Long userId, Long cameraId) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		Camera camera = cameraMapper.getForOne(cameraId);
		if(camera==null){
			re.setCode(ResultEntity.NOT_EXIST);
		}else{
			UserCameraKey key = new UserCameraKey(userId,camera.getId());
			userCameraMapper.delete(key);
			re.setCode(ResultEntity.SUCCESS);
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> bound(UserCameraKey userCameraKey) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		List<UserCameraKey> list = userCameraMapper.getList(userCameraKey);
		if(list!=null && list.size()>0){
			re.setCode(ResultEntity.ALREADY_EXIST);
		}else{
			userCameraMapper.insert(userCameraKey);
			re.setCode(ResultEntity.SUCCESS);
		}
		return re;
	}

	@Override
	public List<UserCameraKey> getList(UserCameraKey userCameraKey) {
		return userCameraMapper.getList(userCameraKey);
	}

	@Override
	public ResultEntity<Camera> addAndBound(Camera camera) {
		ResultEntity<Camera> re = new ResultEntity<Camera>(ResultEntity.FAILD,"",null);
		if(camera==null || camera.getUserId()==null || "".equals(camera.getUuid())){
			re.setMessage("param error");
			return re;
		}
		User user = userMapper.getForOne(camera.getUserId());
		if(user == null){
			re.setCode(ResultEntity.NOT_EXIST);
			re.setMessage("user not exist!");
			return re;
		}
		UserCameraKey key = new UserCameraKey();
		key.setUserId(camera.getUserId());
		key.setIndx(camera.getIndx());
		Camera ca = cameraMapper.getByUuid(camera.getUuid());
		if(ca==null){
			cameraMapper.insert(camera);
			key.setCameraId(camera.getId());
			re.setData(camera);
		}else{
			key.setCameraId(ca.getId());
			ca.setIndx(camera.getIndx());
			re.setData(ca);
		}
		List<UserCameraKey> keys = userCameraMapper.getList(key);
		if(keys!=null && keys.size()>0){
			re.setCode(ResultEntity.ALREADY_EXIST);
			re.setMessage("camera exist");
			return re;
		}else{
			userCameraMapper.insert(key);
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		return re;
	}

	@Override
	public List<Camera> queryListByUserId(Long userId) {
		return userCameraMapper.getListByUserId(userId);
	}

}  
