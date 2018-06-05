package com.biencloud.smarthome.service.customercomplaint.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.customercomplaint.dao.IComplaintDao;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;

/**
 * 客服投诉基于JPA的DAO实现
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
public class ComplaintJpaDao extends BaseDao<Complaint, String> implements IComplaintDao {
}
