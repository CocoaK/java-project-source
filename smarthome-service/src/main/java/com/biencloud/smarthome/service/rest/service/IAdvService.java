package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Ad;

public interface IAdvService extends IBaseResService<Ad>{
	
	List<Ad> getListByEntity(Ad ad);
	
}
