package com.biencloud.smarthome.web.ad.service;

import java.util.List;

import com.biencloud.smarthome.web.ad.vo.AdTypeVO;

/**
 * 广告类型调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public interface IAdTypeService {

	/**
	 * 获取所有广告类型。
	 * @return
	 */
	public List<AdTypeVO> queryAllAdTypes();
}
