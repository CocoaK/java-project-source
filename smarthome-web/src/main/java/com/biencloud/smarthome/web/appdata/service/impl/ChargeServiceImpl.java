package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.appdata.json.ChargeJson;
import com.biencloud.smarthome.web.appdata.service.IChargeService;
import com.biencloud.smarthome.web.appdata.vo.ChargeVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.ChargeData;
import com.biencloud.smarthome.web.wsclient.stub.ChargeDetail;
import com.biencloud.smarthome.web.wsclient.stub.ChargeTypeResult;
import com.biencloud.smarthome.web.wsclient.stub.Device;

public class ChargeServiceImpl extends BaseService<ChargeVO> implements IChargeService{

	@Override
	public ChargeJson queryList(String deviceNo,ChargeDetailVO chargeDetailVO) {
		Device device = getSmartHomeService().queryDeviceByCode(deviceNo);
		ChargeDetail chargeDetail = new ChargeDetail();
		ChargeJson chargeJson = new ChargeJson();
		ChargeData chargeData = new ChargeData();
		if(device==null){
			return chargeJson;
		}
		CellHouseholdInfo chi=new CellHouseholdInfo();
		chi.setId(device.getCellHouseholdInfo().getId());
		chargeData.setCellHouseholdInfo(chi);
		chargeDetail.setChargeData(chargeData);
		if(chargeDetailVO != null){
			chargeDetail.setChargeStartTime(chargeDetailVO.getChargeStartTime());
			if("".equals(chargeDetailVO.getChargeEndTime())){
				chargeDetail.setChargeEndTime(chargeDetailVO.getChargeEndTime());
			}else{
				chargeDetail.setChargeEndTime(chargeDetailVO.getChargeEndTime()+" 23:59:59");
			}
		}
		List<ChargeDetail> chargeDetails = getSmartHomeService().queryChargeDetailForList(chargeDetail);
		List<ChargeVO> charges = new ArrayList<ChargeVO>();
		
		if(chargeDetails != null && chargeDetails.size() != 0){
			for(ChargeDetail detail : chargeDetails){
				List<ChargeTypeResult> types = detail.getChargeTypeResults();
				if(types != null && types.size() != 0){
					for(ChargeTypeResult type : types){
						ChargeVO charge = new ChargeVO();
						charge.setChargeStatus(detail.getChargeStatus());
						charge.setChargeTime(detail.getChargeData().getChargeTime());
						charge.setChargeType(type.getChargeTypeName());
						charge.setTotalMoney(Float.parseFloat(type.getPlayMoney()));
						charges.add(charge);
					}
				}
			}
		}
		chargeJson.setCharge(charges);
		return chargeJson;
	}

}
