package com.biencloud.smarthome.service.housemgr.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;

/**
 * 单元Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IBuildingCellInfoService extends
		IService<BuildingCellInfo, String> {
	/**
	 * 分页查询单元信息
	 * 
	 * @param condition 以单元实体类作为模版来生成查询条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<BuildingCellInfo> queryBuildingCellInfosForPaging(
			BuildingCellInfo condition, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述: 根据小区编号，区域编号，楼宇编号，单元编号查询单元信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:05:50
	 * @param areaNo 小区编号
	 * @param regionNo 区域编号
	 * @param buildingNo 楼宇编号
	 * @param cellCode 单元编号
	 * @return
	 */
	public BuildingCellInfo getByCode(String areaNo,String regionNo,String buildingNo,String cellCode);
	
	/**
	 * 新增/修改单元, 需要判断单元CODE是否重复.
	 * 如果单元没有归宿的楼宇, 单元CODE重复, 则返回null
	 * 否则返回单元信息
	 * 
	 * @param cell
	 * @return
	 */
	public BuildingCellInfo saveOrUpdateCell(BuildingCellInfo cell);
	
	/**
	 * 新增单元时, 判断单元编码是不是重复的, 如果不重复则返回true
	 * PS: 不同楼宇的单元可以有相同的编码
	 * 
	 * @param cellCode
	 * @param buildingId
	 * @return
	 */
	public boolean newCodeNotRepeat(String cellCode, String buildingId);
	
	/**
	 * 更新单元时, 判断单元编码是不是重复的, 如果不重复则返回true
	 * PS: 不同楼宇的单元可以有相同的编码
	 * 
	 * @param cellId
	 * @param cellCode
	 * @param buildingId
	 * @return
	 */
	public boolean updateCodeNotRepeat(String cellId, String cellCode, String buildingId);
	
	/**
	 * 判断单元是否包含房号
	 * 
	 * @param cellId
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
	 * 判断当前楼宇是否和单元关联。
	 * @param buildingId 楼宇编号
	 * @return
	 */
	public boolean existCellByBuildingId(String buildingId);
	
	/**
	 * 删除指定编号的单元。
	 * @param id 单元编号
	 * @return 操作结果
	 */
	public int removeById(String id);
}
