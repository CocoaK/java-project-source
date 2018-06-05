package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.MsgToken;

public interface IMsgTokenService extends IBaseResService<MsgToken>{
	
	//保存或修改
	ResultEntity<String> saveOrUpdate(MsgToken msgToken);
	
	List<MsgToken> getListByDeviceId(Long deviceId);
	
	//删除
	ResultEntity<String> delete(MsgToken msgToken);
	
}
