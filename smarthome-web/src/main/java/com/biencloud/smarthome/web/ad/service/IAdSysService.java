package com.biencloud.smarthome.web.ad.service;

import java.util.List;

import com.biencloud.smarthome.web.ad.vo.AdSysVO;

/**
 * 广告投放目标系统调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public interface IAdSysService {

	/**
	 * 获取所有广告投放目标系统。
	 * @return
	 */
	public List<AdSysVO> queryAllAdSystems();
}
