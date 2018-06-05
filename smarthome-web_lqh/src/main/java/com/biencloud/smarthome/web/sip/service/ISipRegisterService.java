package com.biencloud.smarthome.web.sip.service;

import java.util.List;
import com.biencloud.smarthome.web.base.service.IBaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.sip.VO.SipRegister;

public interface ISipRegisterService extends IBaseRestService<SipRegister>{
	
	List<SipRegister> queryList(SipRegister sipRegister) throws Exception;
	
	ResultEntity<SipRegister> get(Integer id) throws Exception;
	
	ResultEntity<String> delete(Integer id) throws Exception;
	
	ResultEntity<String> update(SipRegister sipRegister) throws Exception;
	
	ResultEntity<String> add(SipRegister sipRegister) throws Exception;
	
	ResultEntity<String> delete(SipRegister sipRegister) throws Exception;

	ResultEntity<SipRegister> create(SipRegister sipRegister) throws Exception;
	
	ResultEntity<SipRegister> getByUsername(String username) throws Exception;
	
}
