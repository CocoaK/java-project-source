package com.biencloud.smarthome.service.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.QrcodeCountMapper;
import com.biencloud.smarthome.service.rest.model.QrcodeCount;
import com.biencloud.smarthome.service.rest.service.IQrcodeCountService;

@Service
public class QrcodeCountService extends BaseResService<QrcodeCount> implements IQrcodeCountService{

	@Autowired
	private QrcodeCountMapper qrcodeCountMapper;
	
	@Override
	public BaseMapper<QrcodeCount> getBaseMapper() {
		return qrcodeCountMapper;
	}

	@Override
	public QrcodeCount getByHouseId(String houseId) {
		return qrcodeCountMapper.getByHouseId(houseId);
	}

}
