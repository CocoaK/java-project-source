package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.AuthSession;
import java.util.List;

public interface AuthSessionMapper extends BaseMapper<AuthSession>{
    int delete(Long id);

    int insert(AuthSession record);

    List<AuthSession> getForList();

    AuthSession getForOne(Long id);

    int updateOnActive(AuthSession record);

    int update(AuthSession record);
    
    AuthSession getBySessionKey(String sessionKey);
}