package com.biencloud.smarthome.service.ad.service.impl;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.ad.service.IAdTypeService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;

/**
 * 广告类型领域服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see BaseService
 * @see IAdTypeService
 */
public class AdTypeServiceImpl extends BaseService<AdType, String> implements
		IAdTypeService {

	@Override
	public List<AdType> queryAllAdTypes() {
		return find("FROM AdType");
	}
}
