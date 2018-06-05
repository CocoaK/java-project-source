package com.biencloud.smarthome.service.softwareupgrade.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;

/**
 * 软件升级数据访问对象接口。
 * @author kouy
 * @since 1.0 2012-4-23
 * @see IDao
 * @throws RuntimeException 如果方法操作失败
 */
public interface ISoftwareUpgradeDao extends IDao<SoftwareUpgrade, String> {

}
