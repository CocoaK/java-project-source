package com.biencloud.smarthome.web.qrcode.service;

import com.biencloud.smarthome.web.base.service.IBaseRestService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeCountVO;

public interface IQrcodeCountService extends IBaseRestService<QrcodeCountVO>{
	
	QrcodeCountVO getByHouseId(Integer houseId) throws Exception;

}
