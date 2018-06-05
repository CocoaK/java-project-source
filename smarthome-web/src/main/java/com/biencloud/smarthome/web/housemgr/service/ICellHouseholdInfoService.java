package com.biencloud.smarthome.web.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;

/**
 * 房号Service
 * 
 * @author jsun  
 * @since 1.0 2012-5-23
 */
public interface ICellHouseholdInfoService {

	/**
	 * 新增或更新房号信息。
	 * @param house 房号信息
	 */
	public void saveOrUpdate(CellHouseholdInfoVo house);

	/**
	 * 更新房号的部分信息 
	 * @param id 房号ID
	 * @param code 房号编码
	 * @param size 房号户型
	 * @param area 面积
	 * @param chargeTypes 收费类型
	 */
	public void updateSomeProperty(String id, String code, CellSizeInfoVo size, String area,
			List<ChargeTypeVO> chargeTypes);

	/**
	 * 查询房号列表，分页。
	 * @param condition 房号信息
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<CellHouseholdInfoVo> queryForPaging(CellHouseholdInfoVo condition,
			int pageNum, int pageSize);

	/**
	 * 删除房号。
	 * @param id 房号编号
	 * @return
	 */
	public int remove(String id);

	/**
	 * 获取房号信息。
	 * @param id 房号编号
	 * @return
	 */
	public CellHouseholdInfoVo get(String id);

	/**
	 * 查询房号信息（列表）。
	 * @param districtId 小区编号
	 * @param regionName 区域名称
	 * @param buildingName 楼宇名称
	 * @param cellName 单元名称
	 * @param householdName 房号名称
	 * @return
	 */
	public List<CellHouseholdInfoVo> find(String districtId, String regionName,
			String buildingName, String cellName, String householdName);

	/**
	 * 更新房号的户型。
	 * @param houseId 房号编号
	 * @param sizeId 户型编号
	 */
	public void updateHouseSizeId(String houseId, String sizeId);

	/**
	 * 判断房号编码是否不存在（新增情况）
	 * @param householdCode 房号编码
	 * @param cellId 单元编号
	 * @return
	 */
	public boolean newCodeNotRepeat(String householdCode, String cellId);
	
	/**
	 * 判断房号编码是否不存在（更新情况）
	 * @param householdId 房号编号
	 * @param householdCode 房号编码
	 * @param cellId 单元编号
	 * @return
	 */
	public boolean updateCodeNotRepeat(String householdId, String householdCode, String cellId);
	
	/**
	 * 验证指定单元下的房屋名称是否存在。
	 * 1）如果房屋编号为空，则验证是否存在房屋名称；<br/>
	 * 2）如果房屋编号不为空，则验证房屋名称对应的房屋编号是否和当前房屋编号相同；<br/>
	 * @param cellId 单元编号
	 * @param houseId 房屋编号
	 * @param houseName 房屋名称
	 * @return
	 */
	public boolean existHouseName(String cellId, String houseId, String houseName);
	
	/**
	 * 查询指定单元下的房号列表。
	 * @param cellId 单元编号
	 * @return
	 */
	public List<CellHouseholdInfoVo> queryListByCellId(String cellId);
	
//	/**
//	 * 查询完整房号
//	 * @param houseId
//	 * @return
//	 */
//	public String getFullRoomNo(String houseId);
}
