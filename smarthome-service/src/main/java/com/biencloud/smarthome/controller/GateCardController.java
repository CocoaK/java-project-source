package com.biencloud.smarthome.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.gate.model.GateCard;
import com.biencloud.smarthome.service.gate.model.GateCardVo;
import com.biencloud.smarthome.service.gate.service.IGateCardService;
import com.biencloud.smarthome.service.rest.model.GateCardInfo;
import com.biencloud.smarthome.service.rest.service.IGateCardInfoService;

@Controller
@RequestMapping("/gate/card")
public class GateCardController extends BaseResController<GateCardInfo>{
		
	@Autowired
	private IGateCardService gateCardService;
	
	@Autowired
	private IGateCardInfoService gateCardInfoService;
	
	@Autowired
	private IDeviceService deviceService;
	
	@RequestMapping(value="/upload", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> save(String cardNo,String deviceNo) {
		return gateCardInfoService.saveByParams(cardNo, deviceNo);
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<GateCardInfo> queryList(GateCardInfo gateCardInfo) {
		return gateCardInfoService.queryForList(gateCardInfo);
	}

	ResultEntity<String> buildAndSave(GateCardVo vo){
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.SUCCESS,"","");
		GateCard gc = new GateCard();
		if(vo==null){
			re.setCode(ResultEntity.FAILD);
			return re;
		}
		if(vo.getDeviceNo()==null && "".equals(vo.getDeviceNo())){
			re.setCode(ResultEntity.FAILD);
			re.setMessage("deviceNo is null");
			return re;
		}
		Device d = deviceService.queryDeviceByCode(vo.getDeviceNo());
		if(d==null){
			re.setCode(ResultEntity.FAILD);
			re.setMessage("no this deviceNo");
			return re;
		}
		gc.setCreatedUser(d.getDeviceName());
		if(d.getHousingDistrictInfo()!=null){
			gc.setDistrictId(d.getHousingDistrictInfo().getId());
		}
		gc.setCardNo(vo.getCardNo());
		gc.setOwnerName(vo.getOwnerName());
		gc.setStatus(Constants.GATE_CARD_DEVICE_AUTH);
		gc.setCreatedTime(new Date());
		
		gateCardService.save_update(gc);
		return re;
	}

	@Override
	public IBaseResService<GateCardInfo> getBaseResService() {
		// TODO Auto-generated method stub
		return gateCardInfoService;
	}
}
