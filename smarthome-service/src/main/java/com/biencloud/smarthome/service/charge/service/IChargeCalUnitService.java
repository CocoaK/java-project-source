package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeCalUnit;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeCalUnitService 
 * 类描述： 计算单位管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:07:55
 */
public interface IChargeCalUnitService extends IService<ChargeCalUnit, Long> {
	/**
	 * 
	 * 方法的描述: 查询计算单位列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:57:07
	 * @param paramsOb:收费项目对象
	 * @return
	 */
	public List<ChargeCalUnit> queryChargeCalUnitForList(ChargeCalUnit paramsOb);
	/**
	 * 
	 * 方法的描述: 查询计算单位列表(分页)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:02:17
	 * @param paramsOb:计算单位对象
	 * @param pageNum:页码
	 * @param pageSize:显示条数
	 * @return
	 */
	public Paging<ChargeCalUnit> queryChargeCalUnitForPaging(ChargeCalUnit paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:05
	 * @param entity:计算单位对象
	 */
	public void updateChargeCalUnit(ChargeCalUnit entity) ;
	/**
	 * 
	 * 方法的描述: 保存计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:41
	 * @param entity:计算单位对象
	 */
	public void saveChargeCalUnit(ChargeCalUnit entity);
	/**
	 * 
	 * 方法的描述: 获取单个计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:05:00
	 * @param entityId:计算单位主键
	 * @return
	 */
	public ChargeCalUnit getChargeCalUnit(String entityId);
	/**
	 * 
	 * 方法的描述: 删除计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:07:04
	 * @param entity:计算单位对象
	 */
	public void DelChargeCalUnit(ChargeCalUnit entity);
	/**
	 * 
	 * 方法的描述: 根据条件查询计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：计算单位对象
	 * @return
	 */
	public ChargeCalUnit queryChargeCalUnitByParams(ChargeCalUnit paramsOb);
}
