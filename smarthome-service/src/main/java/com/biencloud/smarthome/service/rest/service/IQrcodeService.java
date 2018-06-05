package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Qrcode;

public interface IQrcodeService extends IBaseResService<Qrcode>{
	
	List<Qrcode> queryList(Qrcode qrcode);
	
	Integer getQrcodeCount(Qrcode qrcode);
	
	ResultEntity<String> delete(Qrcode qrcode);
	
	List<String> check(String str);
	
}
