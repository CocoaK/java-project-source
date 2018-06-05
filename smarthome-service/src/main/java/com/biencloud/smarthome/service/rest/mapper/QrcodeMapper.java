package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Qrcode;
import java.util.List;

public interface QrcodeMapper extends BaseMapper<Qrcode>{
    int delete(Long id);

    int insert(Qrcode record);

    List<Qrcode> getForList();
    
    List<Qrcode> getListByHouseId(Integer houseId);

    Qrcode getForOne(Long id);

    int updateOnActive(Qrcode record);

    int update(Qrcode record);
    
    List<Qrcode> queryList(Qrcode qrcode);
    
    int getQrcodeCount(Qrcode qrcode);
}