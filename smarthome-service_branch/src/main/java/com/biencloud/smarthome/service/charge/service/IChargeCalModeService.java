package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeCalMode;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeCalModeService 
 * 类描述： 收费计算方式管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:07:55
 */
public interface IChargeCalModeService extends IService<ChargeCalMode, Long> {
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:57:07
	 * @param paramsOb:收费项目对象
	 * @return
	 */
	public List<ChargeCalMode> queryChargeCalModeForList(ChargeCalMode paramsOb);
	/**
	 * 
	 * 方法的描述: 查询收费计算方式列表(分页)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:02:17
	 * @param paramsOb:收费计算方式对象
	 * @param pageNum:页码
	 * @param pageSize:显示条数
	 * @return
	 */
	public Paging<ChargeCalMode> queryChargeCalModeForPaging(ChargeCalMode paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:05
	 * @param entity:收费计算方式对象
	 */
	public void updateChargeCalMode(ChargeCalMode entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:41
	 * @param entity:收费计算方式对象
	 */
	public void saveChargeCalMode(ChargeCalMode entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费计算方式对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:05:00
	 * @param entityId:收费计算方式主键
	 * @return
	 */
	public ChargeCalMode getChargeCalMode(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费计算方式对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:07:04
	 * @param entity:收费计算方式对象
	 */
	public void DelChargeCalMode(ChargeCalMode entity);
	/**
	 * 
	 * 方法的描述: 根据条件查询收费计算方式对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费计算方式对象
	 * @return
	 */
	public ChargeCalMode queryChargeCalModeByParams(ChargeCalMode paramsOb);
}
