package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;

/**
 * 区域Service
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
public interface IHousingDistrictRegionInfoService extends
		IService<HousingDistrictRegionInfo, String> {
	/**
	 * 分页查询区域信息
	 * 
	 * @param condition 以区域实体类作为模版来生成查询条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<HousingDistrictRegionInfo> queryHousingDistrictRegionInfosForPaging(
			HousingDistrictRegionInfo condition, int pageNum, int pageSize);

	/**
	 * 获取所有符合条件的区域信息
	 * 
	 * @param condition 以区域实体类作为模版来生成查询条件
	 * @return
	 */
	public List<HousingDistrictRegionInfo> getRegions(HousingDistrictRegionInfo condition);

	/**
	 * 
	 * 方法的描述:根据小区编号和区域编号查询区域信息 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午8:48:46
	 * @param areaNo 小区编号
	 * @param regionCode 区域编号
	 * @return
	 */
	public HousingDistrictRegionInfo getByCode(String areaNo,String regionCode);

	/**
	 * 新增/修改区域, 需要判断区域CODE是否重复.
	 * 如果区域没有归宿的小区, 区域CODE重复, 则返回null
	 * 否则返回区域信息
	 * 
	 * @param region
	 * @return
	 */
	public HousingDistrictRegionInfo saveOrUpdateRegion(HousingDistrictRegionInfo region);

	/**
	 * 新增区域时, 判断区域编码是不是重复的, 如果不重复则返回true
	 * PS: 不同小区的区域可以有相同的编码
	 * 
	 * @param regionCode
	 * @param regionId
	 * @return
	 */
	public boolean newCodeNotRepeat(String regionCode, String districtId);

	/**
	 * 更新区域时, 判断区域编码是不是重复的, 如果不重复则返回true
	 * PS: 不同小区的区域可以有相同的编码
	 * 
	 * @param regionId
	 * @param regionCode
	 * @param districtId
	 * @return
	 */
	public boolean updateCodeNotRepeat(String regionId, String regionCode, String districtId);
	
	/**
	 * 判断区域是否包含楼宇
	 * 
	 * @param regionId
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
	
	/**
	 * 删除指定编号的区域。
	 * @param id 区域编号
	 * @return 操作结果
	 */
	public int removeById(String id);
}
