package com.biencloud.smarthome.service.rest.mapper;

import java.util.List;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Camera;
import com.biencloud.smarthome.service.rest.model.UserCameraKey;

public interface UserCameraMapper extends BaseMapper<UserCameraKey>{
    int delete(UserCameraKey key);

    int insert(UserCameraKey record);

	List<Camera> getListByUserId(Long userId);
}