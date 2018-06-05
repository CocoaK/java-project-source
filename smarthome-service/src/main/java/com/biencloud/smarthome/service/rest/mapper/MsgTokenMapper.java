package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.MsgToken;
import java.util.List;

public interface MsgTokenMapper extends BaseMapper<MsgToken>{
    int delete(Long id);

    int insert(MsgToken record);

    List<MsgToken> getForList();

    MsgToken getForOne(Long id);

    int updateOnActive(MsgToken record);

    int update(MsgToken record);
    
    MsgToken getEntityByToken(String token);

	List<MsgToken> getListByDeviceId(Long deviceId);
	
	int deleteByEntity(MsgToken record);
	
	List<MsgToken> getListByEntity(MsgToken record);
}