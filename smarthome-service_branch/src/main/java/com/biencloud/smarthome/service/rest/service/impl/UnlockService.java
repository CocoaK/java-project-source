package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.UnlockMapper;
import com.biencloud.smarthome.service.rest.model.Unlock;
import com.biencloud.smarthome.service.rest.service.IUnlockService;

@Service
public class UnlockService extends BaseResService<Unlock> implements
		IUnlockService {

	@Autowired
	private UnlockMapper unlockMapper;

	@Override
	public BaseMapper<Unlock> getBaseMapper() {
		return unlockMapper;
	}

	@Override
	public List<Unlock> queryList(Unlock unlock) {
		// TODO Auto-generated method stub
		return null;
	}

}  
