package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.appdata.json.GatePermissionsJson;
import com.biencloud.smarthome.web.appdata.service.IGatePermissionsService;
import com.biencloud.smarthome.web.appdata.vo.gate.GateDataVO;
import com.biencloud.smarthome.web.gate.service.IGateCardService;
import com.biencloud.smarthome.web.gate.vo.GateCardVO;
import com.biencloud.smarthome.web.gate.vo.GatePermissionsVO;
/**
 * 
 * 类名称：GatePermissionsServiceImpl 
 * 类描述： 门禁数据获取接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-16 下午3:41:37
 */
public class GatePermissionsServiceImpl implements IGatePermissionsService{

	private IGateCardService gateCardService;
	
	public IGateCardService getGateCardService() {
		return gateCardService;
	}
	
	public void setGateCardService(IGateCardService gateCardService) {
		this.gateCardService = gateCardService;
	}


	@Override
	public GatePermissionsJson queryAllGateDataByDeviceNo(String deviceNo) {
		List<GateCardVO> gcs = getGateCardService().queryByDeviceCode(deviceNo);
		GatePermissionsJson gpj = new GatePermissionsJson();
		gpj.setDeviceNo(deviceNo);
		List<GateDataVO> list = new ArrayList<GateDataVO>();
		gpj.setList(list);
		if(gcs == null || gcs.size() == 0)
			return gpj;
		
		for (GateCardVO gc : gcs) {
			list.addAll(buildGateData(deviceNo, gc));
		}
		return gpj;
	}
	
	private List<GateDataVO> buildGateData(String deviceNo, GateCardVO gc){
		List<GateDataVO> list = new ArrayList<GateDataVO>();
		List<GatePermissionsVO> gps = gc.getGatePermissions();
		if(gps == null || gps.size() == 0)
			return list;
		
		for (GatePermissionsVO gp : gps) {
			GateDataVO gd = new GateDataVO();
			gd.setDeviceNo(deviceNo);
			gd.setCardNo(gc.getCardNo());
			gd.setStatus(gc.getStatus());
			if(gp.getBeginTime() != null)
				gd.setBeginTime(gp.getBeginTime().getTime());
			if(gp.getEndTime() != null)
				gd.setEndTime(gp.getEndTime().getTime());
			list.add(gd);
		}
		
		return list;
	}
}
