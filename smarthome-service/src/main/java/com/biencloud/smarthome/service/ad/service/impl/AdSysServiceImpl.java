package com.biencloud.smarthome.service.ad.service.impl;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.ad.service.IAdSysService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;

/**
 * 广告投放目标系统领域服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see BaseService
 * @see IAdSysService
 */
public class AdSysServiceImpl extends BaseService<AdSys, String> implements
		IAdSysService {

	@Override
	public List<AdSys> queryAllAdSystems() {
		return find("FROM AdSys");
	}
}
