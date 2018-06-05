package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;

/**
 * 楼宇Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IRegionBuildingInfoService extends
		IService<RegionBuildingInfo, String> {
	/**
	 * 分页查询楼宇信息
	 * 
	 * @param condition 以楼宇实体类作为模版来生成查询条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<RegionBuildingInfo> queryRegionBuildingInfosForPaging(
			RegionBuildingInfo condition, int pageNum, int pageSize);

	/**
	 * 获取所有符合条件的楼宇信息
	 * 
	 * @param condition 以楼宇实体类作为模版来生成查询条件
	 * @return
	 */
	public List<RegionBuildingInfo> getBuildings(RegionBuildingInfo condition);
	
	/**
	 * 
	 * 方法的描述: 根据小区编号，区域编号，楼宇编号查询楼宇信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午8:55:40
	 * @param areaNo 小区编号
	 * @param regionNo 区域编号
	 * @param buildingCode 楼宇编号
	 * @return
	 */
	public RegionBuildingInfo getByCode(String areaNo,String regionNo,String buildingCode);

	/**
	 * 新增/修改楼宇, 需要判断楼宇CODE是否重复.
	 * 如果楼宇没有归宿的区域, 楼宇CODE重复, 则返回null
	 * 否则返回楼宇信息
	 * 
	 * @param building
	 * @return
	 */
	public RegionBuildingInfo saveOrUpdateRegionBuilding(RegionBuildingInfo building);
	
	/**
	 * 新增楼宇时, 判断楼宇编码是不是重复的, 如果不重复则返回true
	 * PS: 不同区域的楼宇可以有相同的编码
	 * 
	 * @param buildingCode
	 * @param regionId
	 * @return
	 */
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId);
	
	/**
	 * 更新楼宇时, 判断楼宇编码是不是重复的, 如果不重复则返回true
	 * PS: 不同区域的楼宇可以有相同的编码
	 * 
	 * @param buildingId
	 * @param buildingCode
	 * @param regionId
	 * @return
	 */
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId);
	
	/**
	 * 判断楼宇是否包含单元
	 * 
	 * @param buildingId
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
	
	/**
	 * 判断当前区域是否和楼宇关联。
	 * @param regionId 区域编号
	 * @return
	 */
	public boolean existBuildingByRegionId(String regionId);
	
	/**
	 * 删除指定编号的楼宇。
	 * @param id 楼宇编号
	 * @return 操作结果
	 */
	public int removeById(String id);
}
