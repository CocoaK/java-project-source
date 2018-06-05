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
import com.biencloud.smarthome.service.rest.model.PushClient;
import com.biencloud.smarthome.service.rest.service.IPushClientService;

@Controller
@RequestMapping("/push/client")
public class PushClientController extends BaseResController<PushClient>{
		
	@Autowired
	private IPushClientService pushClientService;
	
	@Override
	public IBaseResService<PushClient> getBaseResService() {
		return null;
	}


	@RequestMapping(value="/pushStatusChanged", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushStatusChanged(@RequestBody PushClient record) {
		if(record==null){
			return new ResultEntity<String>();
		}else{
			pushClientService.pushDefenceStatusToClient(record);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}

}
