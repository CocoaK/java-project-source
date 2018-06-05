package com.biencloud.smarthome.web.ad.service;

import java.util.List;

import com.biencloud.smarthome.web.ad.vo.AdLocationVO;

/**
 * 广告投放位置调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public interface IAdLocationService {

	/**
	 * 通过广告目标系统代码获取广告投放目标位置。
	 * @return
	 */
	public List<AdLocationVO> queryAdLocations(String sysCode);
}
