package com.biencloud.smarthome.web.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.ad.service.IAdSysService;
import com.biencloud.smarthome.web.ad.vo.AdSysVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.wsclient.stub.AdSys;

/**
 * 广告投放目标系统调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdSysServiceImpl extends BaseService<AdSysVO> implements IAdSysService {

	@Override
	public List<AdSysVO> queryAllAdSystems() {
		List<AdSys> adSystems = getSmartHomeService().queryAllAdSystems();
		
		List<AdSysVO> adSysVOs = new ArrayList<AdSysVO>();
		for (AdSys adSys : adSystems) {
			AdSysVO adSysVO = new AdSysVO();
			copyProperties(adSys, adSysVO);
			adSysVOs.add(adSysVO);
		}
		return adSysVOs;
	}
}
