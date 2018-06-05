package com.biencloud.smarthome.service.ad.service.impl;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.ad.service.IAdLocationService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;

/**
 * 广告投放位置领域服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see BaseService
 * @see IAdLocationService
 */
public class AdLocationServiceImpl extends BaseService<AdLocation, String> implements
		IAdLocationService {

	@Override
	public List<AdLocation> queryAdLocations(String sysCode) {
		return find("FROM AdLocation al WHERE al.adSys.sysCode = ?1", sysCode);
	}
}
