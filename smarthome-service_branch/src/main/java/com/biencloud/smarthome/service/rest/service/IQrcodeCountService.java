package com.biencloud.smarthome.service.rest.service;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.QrcodeCount;

public interface IQrcodeCountService extends IBaseResService<QrcodeCount>{
	
	QrcodeCount getByHouseId(String houseId);
}
