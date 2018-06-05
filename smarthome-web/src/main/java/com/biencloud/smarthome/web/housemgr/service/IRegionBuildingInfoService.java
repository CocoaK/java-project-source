package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;

/**
 * 楼宇Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IRegionBuildingInfoService {
	/**
	 * 查询楼宇列表，分页。
	 * @param condition 楼宇信息（查询条件）
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<RegionBuildingInfoVo> queryRegionBuildingInfosForPaging(
			RegionBuildingInfoVo condition, int pageNum, int pageSize);
	
	/**
	 * 新增或更新楼宇信息。
	 * @param building 楼宇信息
	 */
	public void saveOrUpdate(RegionBuildingInfoVo building);

	/**
	 * 获取楼宇详细信息。
	 * @param id 楼宇编号
	 */
	public RegionBuildingInfoVo get(String id);

	/**
	 * 删除楼宇。
	 * @param id 楼宇编号
	 */
	public int remove(String id);

	/**
	 * 根据指定条件查询楼宇列表。
	 * @param condition 楼宇信息（查询条件）
	 * @return
	 */
	public List<RegionBuildingInfoVo> getBuildings(RegionBuildingInfoVo condition);
	
	/**
	 * 判断楼宇编码是否不存在（新增情况）
	 * @param buildingCode 楼宇编码
	 * @param regionId 区域编号
	 * @return
	 */
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId);
	
	/**
	 * 判断楼宇编码是否不存在（更新情况）
	 * @param buildingId 楼宇编号
	 * @param buildingCode 楼宇编码
	 * @param regionId 区域编号
	 * @return
	 */
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId);
	
	/**
	 * 判断指定楼宇下是否存在单元。
	 * @param buildingId 楼宇编号
	 * @return
	 */
	public boolean hasCell(String buildingId);
	
	/**
	 * 验证指定区域下的楼宇名称是否存在。
	 * 1）如果楼宇编号为空，则验证是否存在楼宇名称；<br/>
	 * 2）如果楼宇编号不为空，则验证楼宇名称对应的楼宇编号是否和当前楼宇编号相同；<br/>
	 * @param regionId 区域编号
	 * @param buildingId 楼宇编号
	 * @param buildingName 楼宇名称
	 * @return
	 */
	public boolean existBuildingName(String regionId, String buildingId, String buildingName);
}
