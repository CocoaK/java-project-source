package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Version;
import java.util.List;

public interface VersionMapper extends BaseMapper<Version>{
    int delete(Long id);

    int insert(Version record);

    List<Version> getForList();

    Version getForOne(Long id);

    int updateOnActive(Version record);

    int update(Version record);
    
    List<Version> queryList(Version record);
}