package com.biencloud.smarthome.service.sip.service;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.sip.model.SipRegister;

/**
 * 注册接口
 * @author Administrator
 *
 */
public interface ISipRegisterService extends IBaseResService<SipRegister>{
	
	/*** 注册   */
	ResultEntity<SipRegister> register(SipRegister sipRegister);
	
	/*** 删除 */
	ResultEntity<String> deleteByUsername(String username);
	
	/*** 查询*/
	ResultEntity<SipRegister> getByUsername(String username);

}
