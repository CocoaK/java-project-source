package com.biencloud.smarthome.service.housemgr.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.housemgr.enums.Result;

/**
 * 房产管理服务抽象基类。
 * @author kouy
 * @since 1.0 2012-8-13
 * @see BaseService
 */
public abstract class AbstractEstateService<T, Id extends Serializable> extends BaseService<T, Id> {
	
	/**
	 * 删除指定标识的实体。
	 * @param id 实体标识
	 * @return 操作结果
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public final int removeById(Id id) {
		if(allowToRemove(id)){
			getDao().removeByIds(id);
			return Result.SUCCESS.getValue();
		}
		return Result.FAIL_REMOVE.getValue();
	}

	/**
	 * 是否允许删除指定标识的实体，默认允许删除，由子类去覆盖。
	 * @param id 实体标识
	 * @return
	 */
	protected boolean allowToRemove(Id id) {
		return true;
	}
}
