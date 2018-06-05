package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.rest.mapper.GateCardInfoMapper;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;
import com.biencloud.smarthome.service.rest.service.IGateCardInfoService;

@Service
public class GateCardInfoService extends BaseResService<GateCardInfo> implements
		IGateCardInfoService {

	@Autowired
	private GateCardInfoMapper gateCardInfoMapper;


	@Override
	public BaseMapper<GateCardInfo> getBaseMapper() {
		// TODO Auto-generated method stub
		return gateCardInfoMapper;
	}


	@Override
	public List<GateCardInfo> queryForList(GateCardInfo gateCardInfo) {
		return gateCardInfoMapper.queryForList(gateCardInfo);
	}

}  
