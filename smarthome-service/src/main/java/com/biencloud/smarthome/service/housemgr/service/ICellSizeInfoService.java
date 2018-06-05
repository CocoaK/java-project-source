package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;

/**
 * 户型Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface ICellSizeInfoService extends IService<CellSizeInfo, String> {
	/**
	 * 获取所有符合条件的户型信息
	 * 
	 * @param condition 以户型实体类作为模版来生成查询条件
	 * @return
	 */
	public List<CellSizeInfo> findCellSizeInfo(CellSizeInfo condition);
	
	/**
	 * 判断当前单元是否和户型关联。
	 * @param cellId 单元编号
	 * @return
	 */
	public boolean existCellSizeByCellId(String cellId);
	
	/**
	 * 保存业主自定义的房间对应的户型信息。<br/>
	 * 1）、如果业主已有当前房间对应的自定义户型，则需删除；
	 * 2）、将业主当前房间关联到新的自定义户型；
	 * @param size 户型信息
	 * @param houseId 当前房间编号
	 * @return 自定义户型编号
	 */
	public String saveCustomSize(CellSizeInfo size, String houseId);
	
	/**
	 * 删除指定编号的户型。
	 * @param id 户型编号
	 * @return 操作结果
	 */
	public int removeById(String id);
}
