package com.biencloud.smarthome.service.gate.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;

/**
 * 门卡刷卡管理数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IDao
 * @throws RuntimeException 如果操作执行失败
 */
public interface IGateCardVisitDao extends IDao<GateCardVisit, String> {

}
