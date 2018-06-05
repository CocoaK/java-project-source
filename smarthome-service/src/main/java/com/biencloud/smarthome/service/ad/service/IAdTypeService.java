package com.biencloud.smarthome.service.ad.service;

import java.util.List;

import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.base.service.IService;

/**
 * 广告类型领域服务接口。
 * @author kouy
 * @since 1.0 2012-6-1
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IAdTypeService extends IService<AdType, String> {

	/**
	 * 获取所有广告类型。
	 * @return
	 */
	public List<AdType> queryAllAdTypes();
}
