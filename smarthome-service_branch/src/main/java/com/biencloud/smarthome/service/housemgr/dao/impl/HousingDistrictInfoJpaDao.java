/*
 * Copyright 
 */

package com.biencloud.smarthome.service.housemgr.dao.impl;

import java.util.List;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.housemgr.dao.IHousingDistrictInfoDao;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

/**
 * 小区 实体类基于JPA的DAO实现
 * 
 * @author jsun
 * @version 1.0 2012-5-11
 */
public class HousingDistrictInfoJpaDao extends BaseDao<HousingDistrictInfo, String> implements IHousingDistrictInfoDao {

	@Override
	public String queryAreaName(String areaNo) {
		String areaName = null;
		if (areaNo != null) {
			List<HousingDistrictInfo> list = super.find("from HousingDistrictInfo where code=?", areaNo);
			if (list != null && !list.isEmpty()) {
				HousingDistrictInfo area = list.get(0);
				if (area != null) {
					areaName = area.getName();
				}
			}
		}
		return areaName;
	}

}
