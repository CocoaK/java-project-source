package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Camera;
import java.util.List;

public interface CameraMapper extends BaseMapper<Camera>{
    int delete(Long id);

    int insert(Camera record);

    List<Camera> getForList();

    Camera getForOne(Long id);

    int updateOnActive(Camera record);

    int update(Camera record);

	Camera getByUuid(String uuid);
}