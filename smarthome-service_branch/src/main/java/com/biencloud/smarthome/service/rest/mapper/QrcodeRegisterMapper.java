package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.QrcodeRegister;
import java.util.List;

public interface QrcodeRegisterMapper extends BaseMapper<QrcodeRegister>{
    int delete(Long id);

    int insert(QrcodeRegister record);

    List<QrcodeRegister> getForList();

    QrcodeRegister getForOne(Long id);
    
    QrcodeRegister getQrcodeRegister(QrcodeRegister qrcodeRegister);

    int updateOnActive(QrcodeRegister record);

    int update(QrcodeRegister record);
    
    List<QrcodeRegister> getList(QrcodeRegister qrcodeRegister);
}