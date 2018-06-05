package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Qrcode;
import com.biencloud.smarthome.service.rest.service.IQrcodeService;

@Controller
@RequestMapping("/qrcode")
public class QrcodeController extends BaseResController<Qrcode>{
		
	@Autowired
	private IQrcodeService qrcodeService;
	
	@Override
	public @ResponseBody ResultEntity<String> add(@RequestBody Qrcode record) {
		ResultEntity<String> re = qrcodeService.addForResultEntity(record);
		return re;
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Qrcode> query(@RequestBody Qrcode qrcode) {
		List<Qrcode> qrcodes = qrcodeService.queryList(qrcode);
		return qrcodes;
	}
	
	@RequestMapping(value="/getCount", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Integer getCount(@RequestBody Qrcode qrcode) {
		Integer count = qrcodeService.getQrcodeCount(qrcode);
		return count;
	}
	
	@Override
	public @ResponseBody ResultEntity<String> update(@RequestBody Qrcode qrcode) {
		return getBaseResService().updateForResultEntity(qrcode);
	}

	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> delete(@RequestBody Qrcode qrcode) {
		ResultEntity<String> re = qrcodeService.delete(qrcode);
		return re;
	}
	
	@RequestMapping(value="/check", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<String> check(String qrcode) {
		List<String> list = qrcodeService.check(qrcode);
		return list;
	}

	@Override
	public IBaseResService<Qrcode> getBaseResService() {
		return qrcodeService;
	}
	
}
