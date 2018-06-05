package com.biencloud.smarthome.service.info.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.info.model.InfoSend;

/**
 * 信息发布数据访问对象接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IInfoSendDao extends IDao<InfoSend, String> {
	
}
