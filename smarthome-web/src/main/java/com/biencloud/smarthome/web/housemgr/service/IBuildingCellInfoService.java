package com.biencloud.smarthome.web.housemgr.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;

/**
 * 单元信息Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IBuildingCellInfoService {
	/**
	 * 查询单元列表，分页。
	 * @param condition 单元信息
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<BuildingCellInfoVo> queryBuildingCellInfosForPaging(
			BuildingCellInfoVo condition, int pageNum, int pageSize);
	
	/**
	 * 新增或更新单元信息。
	 * @param cell 单元信息
	 */
	public void saveOrUpdate(BuildingCellInfoVo cell);
	
	/**
	 * 获取单元详细信息。
	 * @param id 单元编号
	 * @return
	 */
	public BuildingCellInfoVo get(String id);

	/**
	 * 删除单元。
	 * @param id 单元编号
	 * @return
	 */
	public int remove(String id);
	
	/**
	 * 判断单元编码是否不存在（新增情况）
	 * @param cellCode 单元编码
	 * @param buildingId 楼宇编号
	 * @return
	 */
	public boolean newCodeNotRepeat(String cellCode, String buildingId);
	
	/**
	 * 判断单元编码是否不存在（更新情况）
	 * @param cellId 单元编号
	 * @param cellCode 单元编码
	 * @param buildingId 楼宇编号
	 * @return
	 */
	public boolean updateCodeNotRepeat(String cellId, String cellCode, String buildingId);
	
	/**
	 * 判断当前单元是否存在房号。
	 * @param cellId 单元编号
	 * @return
	 */
	public boolean hasHouse(String cellId);
	
	/**
	 * 验证指定楼宇下的单元名称是否存在。
	 * 1）如果单元编号为空，则验证是否存在单元名称；<br/>
	 * 2）如果单元编号不为空，则验证单元名称对应的单元编号是否和当前单元编号相同；<br/>
	 * @param buildingId 楼宇编号
	 * @param cellId 单元编号
	 * @param cellName 单元名称
	 * @return
	 */
	public boolean existCellName(String buildingId, String cellId, String cellName);
	
	/**
	 * 新增或更新单元信息。
	 * @param cell 单元信息
	 * return cell
	 */
	public BuildingCellInfoVo saveOrUpdateVo(BuildingCellInfoVo cell);
}
