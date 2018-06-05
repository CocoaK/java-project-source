/*
 * Copyright 
 */

package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.sysgroup.service.ISystemGroupService;

/**
 * 小区信息Service
 * 
 * @author jsun
 * @version 1.0 2012-5-12
 */
public interface IHousingDistrictInfoService extends
        IService<HousingDistrictInfo, String> {
	/**
	 * 根据小区模型中的属性条件分页查询所有符合该条件的小区信息.
	 * 例如设置小区模型(condition)中的name属性 = a, 就会以like的形式, 将所有类似小区都查询出来.
	 * 
	 * @param condition 模版方式的查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<HousingDistrictInfo> queryHousingDistrictInfosForPaging(
			HousingDistrictInfo condition, int pageNum, int pageSize);

	/**
	 * 根据小区模型中的属性条件查询所有符合该条件的小区信息
	 * 
	 * @param condition 模版方式的查询条件
	 * @return
	 */
	public List<HousingDistrictInfo> getDistricts(HousingDistrictInfo condition);

	public void save(HousingDistrictInfo district);
	
	/**
	 * 
	 * 方法的描述:根据小区编号查询小区信息 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午8:43:35
	 * @param districtCode
	 * @return
	 */
	public HousingDistrictInfo getByCode(String areaNo);
	/**
	
	
	/**
	 * 更新组织机构名称时, 同步更新对应的小区名称
	 * 
	 * @param groupNo 组织机构编号
	 * @param groupName 组织机构名称
	 * @see ISystemGroupService#saveOrUpdateSystemGroup
	 */
	public void upateHousingDistrictInfoName(Long groupNo, String groupName);

	/**
	 * 变更小区归属的物业公司ID
	 * 
	 * @param districtId
	 * @param propertyCompanyId
	 */
	public void updateDistrictPropertyCompanyId(String districtId, int propertyCompanyId);

	/**
	 * 查询小区总数
	 * 
	 * @return
	 */
	public long getDistrictCount();

	/**
	 * 根据组织编号查询小区ID
	 * 
	 * @param groupNo
	 * @return 小区ID, 如果查找不到则返回null
	 */
	public String getDistrictIdByGroupNo(Long groupNo);

	/**
	 * 判断小区是否包含区域
	 * 
	 * @param regionId
	 * @return
	 */
	public boolean hasRegion(String districtId);
	
	/**
	 * 通过组织编号列表查询对应的小区编号列表。
	 * @param groupIds 组织编号列表
	 * @return
	 */
	public List<String> queryDistrictIds(List<String> groupIds);
}
