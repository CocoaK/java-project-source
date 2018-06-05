package com.biencloud.smarthome.web.ad.service;

import java.util.List;

import com.biencloud.smarthome.web.ad.vo.AdTargetVO;

/**
 * 广告投放目标调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 */
public interface IAdTargetService {

	/**
	 * 通过广告编号获取相关的投放目标。
	 * @param adId 广告编号
	 * @return
	 */
	public List<AdTargetVO> queryAdTargets(String adId);
}
