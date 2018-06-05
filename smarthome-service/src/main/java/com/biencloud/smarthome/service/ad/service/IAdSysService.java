package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.base.service.IService;

/**
 * 广告投放目标系统领域服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IAdSysService extends IService<AdSys, String> {

	/**
	 * 获取所有广告投放目标系统。
	 * @return
	 */
	public List<AdSys> queryAllAdSystems();
}
