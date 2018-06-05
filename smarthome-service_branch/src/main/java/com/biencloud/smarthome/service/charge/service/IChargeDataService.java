package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeStatitics;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeDataService 
 * 类描述： 收费数据管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:10:42
 */
public interface IChargeDataService extends IService<ChargeData, Long> {
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:10:53
	 * @param paramsOb:收费数据对象
	 * @return
	 */
	public List<ChargeData> queryChargeDataForList(ChargeData paramsOb);
	/**
	 * 
	 * 方法的描述: 查询收费数据列表(分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:11:13
	 * @param paramsOb：收费数据对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<ChargeData> queryChargeDataForPaging(ChargeData paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:11:58
	 * @param entity:收费数据对象
	 */
	public void updateChargeData(ChargeData entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:11
	 * @param entity:收费数据对象
	 */
	public void saveChargeData(ChargeData entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费数据对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:29
	 * @param entityId:收费数据对象主键
	 * @return
	 */
	public ChargeData getChargeData(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费数据对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:59
	 * @param entity:收费数据对象
	 */
	public void DelChargeData(ChargeData entity);
	/**
	 * 
	 * 方法的描述: 统计收费报表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:13:13
	 * @param ob：:收费数据对象
	 * @return
	 */
	public List<ChargeStatitics> statisticsCharge(ChargeData ob);
	/**
	 * 
	 * 方法的描述: 判断收费数据表中指定的楼宇ID是否有记录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-13 下午2:56:21
	 * @param buildingId
	 * @return
	 */
	public boolean isExistForBuilding(String buildingId);
	/**
	 * 
	 * 方法的描述:  判断收费数据表中指定的房间ID是否有记录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-13 下午2:59:47
	 * @param houseId
	 * @return
	 */
	public boolean isExistForHouseHold(String houseId);
}
