package com.biencloud.smarthome.web.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.ad.service.IAdLocationService;
import com.biencloud.smarthome.web.ad.vo.AdLocationVO;
import com.biencloud.smarthome.web.ad.vo.AdSysVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.wsclient.stub.AdLocation;

/**
 * 广告投放位置调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdLocationServiceImpl extends BaseService<AdLocationVO> implements
		IAdLocationService {

	@Override
	public List<AdLocationVO> queryAdLocations(String sysCode) {
		List<AdLocation> als = getSmartHomeService().queryAdLocations(sysCode);		
		
		List<AdLocationVO> alVOs = new ArrayList<AdLocationVO>();
		for (AdLocation al : als) {
			AdLocationVO alVO = new AdLocationVO();
			copyProperties(al, alVO, "adSys");
			AdSysVO asVO = new AdSysVO();
			copyProperties(al.getAdSys(), asVO);
			alVO.setAdSys(asVO);
			alVOs.add(alVO);
		}
		
		return alVOs;
	}
}
