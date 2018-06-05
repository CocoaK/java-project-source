package com.biencloud.smarthome.web.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.ad.service.IAdTypeService;
import com.biencloud.smarthome.web.ad.vo.AdTypeVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.wsclient.stub.AdType;

/**
 * 广告类型调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public class AdTypeServiceImpl extends BaseService<AdTypeVO> implements IAdTypeService {

	@Override
	public List<AdTypeVO> queryAllAdTypes() {
		List<AdType> adTypes = getSmartHomeService().queryAllAdTypes();
		
		List<AdTypeVO> adTypeVOs = new ArrayList<AdTypeVO>();
		for (AdType adType : adTypes) {
			AdTypeVO adTypeVO = new AdTypeVO();
			copyProperties(adType, adTypeVO);
			adTypeVOs.add(adTypeVO);
		}		
		return adTypeVOs;
	}
}
