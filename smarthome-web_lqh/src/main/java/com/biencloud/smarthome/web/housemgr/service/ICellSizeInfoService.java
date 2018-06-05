package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;

/**
 * 户型Service
 * 
 * @author jsun  
 * @since 1.0 2012-5-24
 */
public interface ICellSizeInfoService {
	
	/**
	 * 新增或更新户型信息。
	 * @param size 户型信息
	 */
	public void saveOrUpdate(CellSizeInfoVo size);
	
	/**
	 * 查询户型列表。
	 * @param condition 户型信息
	 * @return
	 */
	public List<CellSizeInfoVo> find(CellSizeInfoVo condition);

	/**
	 * 删除户型。
	 * @param id 户型编号
	 * @return
	 */
	public int remove(String id);

	/**
	 * 保存业主自定义的房间对应的户型信息。
	 * @param size 户型信息
	 * @param houseId 当前房间编号
	 * @return 自定义户型编号
	 */
	public String saveCustomSize(CellSizeInfoVo size, String houseId);
}
