package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.base.service.IService;

/**
 * 广告投放位置领域服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IAdLocationService extends IService<AdLocation, String> {

	/**
	 * 通过广告目标系统代码获取广告投放目标位置。
	 * @return
	 */
	public List<AdLocation> queryAdLocations(String sysCode);
}
