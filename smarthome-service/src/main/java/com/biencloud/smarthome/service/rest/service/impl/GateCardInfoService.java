package com.biencloud.smarthome.service.rest.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.GateCardInfoMapper;
import com.biencloud.smarthome.service.rest.model.Device;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;
import com.biencloud.smarthome.service.rest.service.IDevicesService;
import com.biencloud.smarthome.service.rest.service.IGateCardInfoService;
import com.sun.corba.se.impl.orbutil.closure.Constant;

@Service
public class GateCardInfoService extends BaseResService<GateCardInfo> implements
		IGateCardInfoService {

	@Autowired
	private GateCardInfoMapper gateCardInfoMapper;
	@Autowired
	private IDevicesService devicesService;


	@Override
	public BaseMapper<GateCardInfo> getBaseMapper() {
		// TODO Auto-generated method stub
		return gateCardInfoMapper;
	}


	@Override
	public List<GateCardInfo> queryForList(GateCardInfo gateCardInfo) {
		return gateCardInfoMapper.queryForList(gateCardInfo);
	}
	
	@Override
	public ResultEntity<String> saveByParams(String cardNo,String deviceNo) {
		GateCardInfo gateCardInfo = new GateCardInfo();
		gateCardInfo.setCardNo(cardNo);
		
		Device dev = devicesService.getByDeviceNo(deviceNo);
		if(dev!=null){
			gateCardInfo.setDistrictId(Integer.parseInt(dev.getDistrictId()));
			gateCardInfo.setDeviceId(dev.getDeviceId().toString());
			
		}
		List<GateCardInfo> list = gateCardInfoMapper.getList(gateCardInfo);
		if(list!=null && list.size()>0){
			return new ResultEntity<String>(ResultEntity.ALREADY_EXIST,"","");
		}
		gateCardInfo.setStatus(Constants.GATE_CARD_ENABLED);
		gateCardInfo.setCreatedTime(new Date());
		
		int i = gateCardInfoMapper.insert(gateCardInfo);
		return super.proccessResultEntity(i>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
	}

}  
