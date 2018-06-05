package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.base.service.IService;

/**
 * 广告投放目标领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-31
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IAdTargetService extends IService<AdTarget, String> {

	/**
	 * 通过广告编号获取相关的投放目标。
	 * @param adId 广告编号
	 * @return
	 */
	public List<AdTarget> queryAdTargets(String adId);
}
