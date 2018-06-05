package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeMonetaryUnitService 
 * 类描述： 收费货币单位管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:16:40
 */
public interface IChargeMonetaryUnitService extends IService<ChargeMonetaryUnit, Long> {
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:16:53
	 * @param paramsOb：收费货币单位对象
	 * @return
	 */
	public List<ChargeMonetaryUnit> queryChargeMonetaryUnitForList(ChargeMonetaryUnit paramsOb);
	/**
	 * 
	 * 方法的描述: 查询收费货币单位列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:17:10
	 * @param paramsOb：收费货币单位对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<ChargeMonetaryUnit> queryChargeMonetaryUnitForPaging(ChargeMonetaryUnit paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费货币单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:01
	 * @param entity：收费货币单位对象
	 */
	public void updateChargeMonetaryUnit(ChargeMonetaryUnit entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费货币单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:19
	 * @param entity：收费货币单位对象
	 */
	public void saveChargeMonetaryUnit(ChargeMonetaryUnit entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:30
	 * @param entityId：收费货币单位对象主键
	 * @return
	 */
	public ChargeMonetaryUnit getChargeMonetaryUnit(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:54
	 * @param entity：收费货币单位对象
	 */
	public void DelChargeMonetaryUnit(ChargeMonetaryUnit entity);
	/**
	 * 
	 * 方法的描述: 根据条件查询货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费货币单位对象
	 * @return
	 */
	public ChargeMonetaryUnit queryChargeMonetaryUnitByParams(ChargeMonetaryUnit paramsOb);
}
