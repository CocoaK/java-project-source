package com.biencloud.smarthome.service.softwareupgrade.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.softwareupgrade.dao.ISoftwareUpgradeDao;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;

/**
 * 软件升级模块数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-4-23
 * @see BaseDao
 * @see ISoftwareUpgradeDao
 */
public class SoftwareUpgradeDaoJpa extends BaseDao<SoftwareUpgrade, String> implements ISoftwareUpgradeDao {

}
