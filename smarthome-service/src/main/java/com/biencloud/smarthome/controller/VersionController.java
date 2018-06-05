package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Version;
import com.biencloud.smarthome.service.rest.service.IVersionService;

@Controller
@RequestMapping("/version")
public class VersionController extends BaseResController<Version>{
		
	@Autowired
	private IVersionService versionService;
	
	@Override
	public IBaseResService<Version> getBaseResService() {
		return versionService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Version> queryList(Version version) {
		return versionService.queryList(version);
	}

}
