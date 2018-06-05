package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;
import java.util.List;

public interface GateCardInfoMapper extends BaseMapper<GateCardInfo>{
    int delete(Long gateCardId);

    int insert(GateCardInfo record);

    List<GateCardInfo> queryForList(GateCardInfo gateCardInfo);

    GateCardInfo getForOne(Long gateCardId);

    int updateOnActive(GateCardInfo record);

    int update(GateCardInfo record);
}