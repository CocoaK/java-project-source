package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;

public interface IGateCardInfoService extends IBaseResService<GateCardInfo>{
	
	List<GateCardInfo> queryForList(GateCardInfo gateCardInfo);

	/**
	 * @desc   @param cardNo
	 * @desc   @param deviceNo
	 * @desc   @return
	 * @return ResultEntity<String>
	 * @param cardNo
	 * @param deviceNo
	 * @return
	 */
	ResultEntity<String> saveByParams(String cardNo, String deviceNo);
	
}
