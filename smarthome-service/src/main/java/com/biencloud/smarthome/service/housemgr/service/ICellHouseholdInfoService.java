package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;

/**
 * 房号(户)Service
 * 
 * @author jsun  
 * @since 1.0 2012-5-18
 */
public interface ICellHouseholdInfoService extends IService<CellHouseholdInfo, String> {
	/**
	 * 分页查询房号信息
	 * 
	 * @param condition 以房号实体类作为模版来生成查询条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<CellHouseholdInfo> queryCellHouseholdInfosForPaging(
			CellHouseholdInfo condition, int pageNum, int pageSize);

	/**
	 * 只更新房号的部分信息
	 * 
	 * @param id 房号ID
	 * @param code 房号编码
	 * @param size 房号户型
	 * @param area 面积
	 * @param chargeTypes 收费类型
	 */
	public void updateSomeProperty(String id, String code, CellSizeInfo size, String area,
			List<ChargeType> chargeTypes);

	/**
	 * 获取所有符合条件的房号
	 * 
	 * @param condition 以房号实体类作为模版来生成查询条件
	 * @return
	 */
	public List<CellHouseholdInfo> findHouseholds(CellHouseholdInfo condition);
	
	/**
	 * 
	 * 方法的描述: 根据小区编号，区域编号，楼宇编号，单元编号，房号查询房屋信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-12 下午9:14:47
	 * @param areaNo 小区编号
	 * @param regionNo 区域编号
	 * @param buildingNo 楼宇编号
	 * @param unitNo 单元编号
	 * @param houseCode 房号
	 * @return
	 */
	public CellHouseholdInfo getByCode(String areaNo,String regionNo,String buildingNo,String unitNo,String houseCode);
	
	/**
	 * 新增/修改房号, 需要判断房号CODE是否重复.
	 * 如果房号没有归宿的单元, 房号CODE重复, 则返回null
	 * 否则返回房号信息
	 * 
	 * @param household
	 * @return
	 */
	public CellHouseholdInfo saveOrUpdateHousehold(CellHouseholdInfo household);

	/**
	 * 更新房号的户型ID
	 * 
	 * @param houseId
	 * @param sizeId
	 */
	public void updateHouseSizeId(String houseId, String sizeId);
	
	/**
	 * 根据设备编号查询房号的房间信息
	 * 
	 * @param deviceNo
	 * @return
	 */
	public List<Room> queryRoomByDeviceNo(String deviceNo);
	
	/**
	 * 新增房号时, 判断房号编码是不是重复的, 如果不重复则返回true
	 * PS: 不同单元的房号可以有相同的编码
	 * 
	 * @param householdCode
	 * @param cellId
	 * @return
	 */
	public boolean newCodeNotRepeat(String householdCode, String cellId);
	
	/**
	 * 更房号时, 判断房号编码是不是重复的, 如果不重复则返回true
	 * PS: 不同单元的房号可以有相同的编码
	 * 
	 * @param householdId
	 * @param householdCode
	 * @param cellId
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
	 * 更新房号信息。
	 * @param house 房号信息
	 */
	public void updateHouse(CellHouseholdInfo house);
	
	/**
	 * 判断当前单元是否和房号关联。
	 * @param cellId 单元编号
	 * @return
	 */
	public boolean existHouseByCellId(String cellId);
	
	/**
	 * 判断当前户型是否和房号关联。
	 * @param sizeId 户型编号
	 * @return
	 */
	public boolean existHouseBySizeId(String sizeId);
	
	/**
	 * 删除指定编号的房号。
	 * @param id 房号编号
	 * @return 操作结果
	 */
	public int removeById(String id);
	
	/**
	 * 查询完整房号code
	 * @param id
	 * @return
	 */
	public String getFullHouseNo(String id);
	
	/**
	 * 查询完整房号name
	 * @param id
	 * @return
	 */
	public String getFullHouseName(String id);
	
	public String getHouseIdByFullHouseNo(String fullHouseNo);
}
