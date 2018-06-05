package com.biencloud.smarthome.service.sip.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import java.util.List;

public interface SipRegisterMapper extends BaseMapper<SipRegister>{
    int delete(Integer id);

    int insert(SipRegister record);

    List<SipRegister> getForList();

    SipRegister getForOne(Integer id);

    int updateOnActive(SipRegister record);

    int update(SipRegister record);
    
    int deleteByUsername(String username);
    
    String getMaxUsername(Integer type);
    
    SipRegister getByUsername(String username);
}