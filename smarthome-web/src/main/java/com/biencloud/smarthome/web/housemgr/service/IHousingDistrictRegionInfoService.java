package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;

/**
 * 小区区域Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IHousingDistrictRegionInfoService {
	/**
	 * 查询区域列表，分页。
	 * @param condition 区域信息（查询条件）
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<HousingDistrictRegionInfoVo> queryHousingDistrictRegionInfosForPaging(
			HousingDistrictRegionInfoVo condition, int pageNum, int pageSize);

	/**
	 * 更新区域信息。
	 * @param region 区域信息
	 */
	public void update(HousingDistrictRegionInfoVo region);

	/**
	 * 新增区域信息。
	 * @param region 区域信息
	 */
	public void save(HousingDistrictRegionInfoVo region);

	/**
	 * 删除区域。
	 * @param id 区域编号
	 * @return
	 */
	public int remove(String id);

	/**
	 * 获取区域详细信息。
	 * @param id 区域编号
	 * @return
	 */
	public HousingDistrictRegionInfoVo get(String id);

	/**
	 * 根据指定条件查询区域列表。
	 * @param condition 区域信息（查询条件）
	 * @return
	 */
	public List<HousingDistrictRegionInfoVo> getRegions(HousingDistrictRegionInfoVo condition);

	/**
	 * 新增或更新区域信息。
	 * @param region 区域信息
	 * @return
	 */
	public HousingDistrictRegionInfoVo saveOrUpdate(HousingDistrictRegionInfoVo region);

	/**
	 * 判断区域编码是否不存在（新增情况）
	 * @param regionCode 区域编码
	 * @param districtId 小区编号
	 * @return
	 */
	public boolean newCodeNotRepeat(String regionCode, String districtId);
	
	/**
	 * 判断区域编码是否不存在（更新情况）
	 * @param regionId 区域编号
	 * @param regionCode 区域编码
	 * @param districtId 小区编号
	 * @return
	 */
	public boolean updateCodeNotRepeat(String regionId, String regionCode, String districtId);
	
	/**
	 * 判断指定区域下是否存在楼宇。
	 * @param regionId 区域编号
	 * @return
	 */
	public boolean hasBuilding(String regionId);
	
	/**
	 * 验证指定小区下的区域名称是否存在。
	 * 1）如果区域编号为空，则验证是否存在区域名称；<br/>
	 * 2）如果区域编号不为空，则验证区域名称对应的区域编号是否和当前区域编号相同；<br/>
	 * @param districtId 小区编号
	 * @param regionId 区域编号
	 * @param regionName 区域名称
	 * @return
	 */
	public boolean existRegionName(String districtId, String regionId, String regionName);
}
