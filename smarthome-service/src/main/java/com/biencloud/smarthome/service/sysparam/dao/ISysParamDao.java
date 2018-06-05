package com.biencloud.smarthome.service.sysparam.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;

/**
 * 系统参数数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-4-14
 * @see IDao
 * @throws RuntimeException 如果方法操作失败
 */
public interface ISysParamDao extends IDao<SystemParam, String> {

}
