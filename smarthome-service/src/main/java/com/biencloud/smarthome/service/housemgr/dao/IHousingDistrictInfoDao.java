/*
 * Copyright 
 */

package com.biencloud.smarthome.service.housemgr.dao;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;

/**
 * 小区信息 实体类DAO
 * 
 * @author jsun
 * @since 1.0 2012-5-11
 */
public interface IHousingDistrictInfoDao extends
		IDao<HousingDistrictInfo, String> {
	/**
	 * 
	 * 方法的描述: 查询小区名称
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午3:22:42
	 * @param areaNo
	 * @return
	 */
	public String queryAreaName(String areaNo);
}
