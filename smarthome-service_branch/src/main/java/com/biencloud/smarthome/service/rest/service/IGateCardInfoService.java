package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;

public interface IGateCardInfoService extends IBaseResService<GateCardInfo>{
	
	List<GateCardInfo> queryForList(GateCardInfo gateCardInfo);
	
}
