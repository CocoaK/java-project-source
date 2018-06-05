package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

/**
 * 小区Service
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public interface IHousingDistrictInfoService {
	
	/**
	 * 获取小区详细信息。
	 * @param id 小区编号
	 * @return
	 */
	public HousingDistrictInfoVo getHousingDistrictInfo(String id);

	/**
	 * 新增小区信息。
	 * @param district 小区信息
	 */
	public void save(HousingDistrictInfoVo district);

	/**
	 * 更新小区信息。
	 * @param district 小区信息
	 */
	public void update(HousingDistrictInfoVo district);

	/**
	 * 查询小区列表。分页。
	 * @param condition 小区信息（查询条件）
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<HousingDistrictInfoVo> queryHousingDistrictInfosForPaging(
			HousingDistrictInfoVo condition, int pageNum, int pageSize);

	/**
	 * 根据指定条件查询小区列表。
	 * @param condition 小区信息（查询条件）
	 * @return
	 */
	public List<HousingDistrictInfoVo> getDistricts(HousingDistrictInfoVo condition);

	/**
	 * 获取小区数量。
	 * @return
	 */
	public long getDistrictCount();

	/**
	 * 更新小区对应的物业公司。
	 * @param districtId 小区编号
	 * @param propertyCompanyId 物业公司编号
	 */
	public void updatePropertyCompanyId(String districtId, int propertyCompanyId);

	/**
	 * 通过组织编号获取对应的小区编号。
	 * @param groupNo 组织编号
	 * @return
	 */
	public String getDistrictIdByGroupNo(Long groupNo);

	/**
	 * 判断指定小区下是否存在区域。
	 * @param districtId 小区编号
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
