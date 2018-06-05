package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.QrcodeRegister;
import com.biencloud.smarthome.service.rest.service.IQrcodeRegisterService;
/**
 * 二维码注册类：主要用来判断二维码是否已经注册过
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/qrcode/reg")
public class QrcodeRegisterController extends BaseResController<QrcodeRegister>{
		
	@Autowired
	private IQrcodeRegisterService qrcodeRegisterService;
	
	@Override
	public @ResponseBody ResultEntity<String> add(QrcodeRegister qrcodeRegister) {
		ResultEntity<String> re = qrcodeRegisterService.addForResultEntity(qrcodeRegister);
		return re;
	}

	@RequestMapping(value="/isConflict", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Integer> check(QrcodeRegister qrcodeRegister) {
		ResultEntity<Integer> re = qrcodeRegisterService.checkExist(qrcodeRegister);
		return re;
	}

	@Override
	public IBaseResService<QrcodeRegister> getBaseResService() {
		return qrcodeRegisterService;
	}
	
}
