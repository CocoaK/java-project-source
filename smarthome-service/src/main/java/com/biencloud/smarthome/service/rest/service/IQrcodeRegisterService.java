package com.biencloud.smarthome.service.rest.service;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.QrcodeRegister;

public interface IQrcodeRegisterService extends IBaseResService<QrcodeRegister>{
	
	ResultEntity<String> delete(QrcodeRegister qrcodeRegister);
	
	ResultEntity<Integer> checkExist(QrcodeRegister qrcodeRegister);
	
}
