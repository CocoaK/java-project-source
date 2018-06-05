package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.rest.model.Unlock;

public interface IUnlockService extends IBaseResService<Unlock>{
	
	List<Unlock> queryList(Unlock unlock);
	
}
