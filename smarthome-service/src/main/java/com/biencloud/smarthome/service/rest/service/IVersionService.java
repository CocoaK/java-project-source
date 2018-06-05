package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Version;

public interface IVersionService extends IBaseResService<Version>{
	
	List<Version> queryList(Version version);
	
}
