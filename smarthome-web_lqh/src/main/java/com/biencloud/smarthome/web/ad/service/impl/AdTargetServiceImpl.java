package com.biencloud.smarthome.web.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.ad.service.IAdTargetService;
import com.biencloud.smarthome.web.ad.vo.AdTargetVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.AdTarget;

/**
 * 广告投放位置调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdTargetServiceImpl extends BaseService<AdTargetVO> implements
		IAdTargetService {

	@Override
	public List<AdTargetVO> queryAdTargets(String adId) {
		List<AdTarget> ats = getSmartHomeService().queryAdTargets(adId);
		
		List<AdTargetVO> atVOs = new ArrayList<AdTargetVO>();
		for (AdTarget at : ats) {
			AdTargetVO atVO = new AdTargetVO();
			
			copyProperties(at, atVO, "device");
			
			if(at.getDevice() != null){
				DeviceVO deviceVO = new DeviceVO();
				deviceVO.setDeviceId(at.getDevice().getDeviceId());
				deviceVO.setDeviceAlias(at.getDevice().getDeviceAlias());
				if(at.getDevice().getHousingDistrictInfo() != null){
					HousingDistrictInfoVo hdi = new HousingDistrictInfoVo();
					hdi.setId(at.getDevice().getHousingDistrictInfo().getId());
					hdi.setName(at.getDevice().getHousingDistrictInfo().getName());
					hdi.setGroupId(at.getDevice().getHousingDistrictInfo().getGroupId());
					deviceVO.setHousingDistrictInfo(hdi);
				}
				atVO.setDevice(deviceVO);
			}
			
			atVOs.add(atVO);
		}
		return atVOs;
	}
}
