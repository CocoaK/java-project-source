package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Ad;
import com.biencloud.smarthome.service.rest.service.IAdvService;

@Controller
@RequestMapping("/ad")
public class AdController extends BaseResController<Ad>{
		
	@Autowired
	private IAdvService advService;
	
	@Override
	public IBaseResService<Ad> getBaseResService() {
		return advService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Ad> queryList(Ad ad) {
		return advService.getListByEntity(ad);
	}
	
}
