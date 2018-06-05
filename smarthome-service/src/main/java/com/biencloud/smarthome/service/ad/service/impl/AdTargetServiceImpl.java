package com.biencloud.smarthome.service.ad.service.impl;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.service.IAdTargetService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;

/**
 * 广告投放目标领域服务实现类。
 * @author kouy
 * @since 1.0 2012-5-31
 * @see BaseService
 * @see IAdTargetService
 */
public class AdTargetServiceImpl extends BaseService<AdTarget, String> implements
		IAdTargetService {

	@Override
	public List<AdTarget> queryAdTargets(String adId) {
		return find("SELECT at FROM AdTarget at WHERE at.adId = ?1", adId);
	}
}
