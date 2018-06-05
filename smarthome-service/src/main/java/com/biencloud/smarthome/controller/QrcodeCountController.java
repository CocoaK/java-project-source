package com.biencloud.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.QrcodeCount;
import com.biencloud.smarthome.service.rest.service.IQrcodeCountService;

@Controller
@RequestMapping("/qrcode/count")
public class QrcodeCountController extends BaseResController<QrcodeCount>{
		
	@Autowired
	private IQrcodeCountService qrcodeCountService;
	
//	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
//	public @ResponseBody ResultEntity<String> add(QrcodeCount record) {
//		ResultEntity<String> re = qrcodeCountService.addForResultEntity(record);
//		return re;
//	}
//	
//	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
//	public @ResponseBody QrcodeCount get(@RequestBody String houseId) {
//		QrcodeCount qrcode = qrcodeCountService.getByHouseId(houseId);
//		return qrcode;
//	}

	@Override
	public IBaseResService<QrcodeCount> getBaseResService() {
		return qrcodeCountService;
	}
	
}
