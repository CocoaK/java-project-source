package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.AdMapper;
import com.biencloud.smarthome.service.rest.model.Ad;
import com.biencloud.smarthome.service.rest.service.IAdvService;

@Service
public class AdvService extends BaseResService<Ad> implements
		IAdvService {

	@Autowired
	private AdMapper adMapper;

	@Override
	public BaseMapper<Ad> getBaseMapper() {
		return adMapper;
	}

	@Override
	public List<Ad> getListByEntity(Ad ad) {
		return adMapper.getListByEntity(ad);
	}

}  
