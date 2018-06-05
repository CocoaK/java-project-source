package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Unlock;
import java.util.List;

public interface UnlockMapper extends BaseMapper<Unlock>{
    int delete(Long id);

    int insert(Unlock record);

    List<Unlock> getForList();

    Unlock getForOne(Long id);

    int updateOnActive(Unlock record);

    int update(Unlock record);
}