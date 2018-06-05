package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.ExceptionLog;
import java.util.List;

public interface ExceptionLogMapper extends BaseMapper<ExceptionLog>{
    int delete(Long id);

    int insert(ExceptionLog record);

    List<ExceptionLog> getForList();

    ExceptionLog getForOne(Long id);

    int updateOnActive(ExceptionLog record);

    int update(ExceptionLog record);
}