package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeTypeService 
 * 类描述： 收费项目管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:23:22
 */
public interface IChargeTypeService extends IService<ChargeType, Long> {
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:23:32
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public List<ChargeType> queryChargeTypeForList(ChargeType paramsOb);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:23:44
	 * @param paramsOb：收费项目对象
	 * @param pageNum：页码	
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<ChargeType> queryChargeTypeForPaging(ChargeType paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费项目
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:24:52
	 * @param entity：收费项目对象
	 */
	public void updateChargeType(ChargeType entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费项目
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:03
	 * @param entity：收费项目对象
	 */
	public void saveChargeType(ChargeType entity);
	/**
	 * 
	 * 方法的描述:获取单个收费项目对象 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:14
	 * @param entityId：收费项目对象 主键
	 * @return
	 */
	public ChargeType getChargeType(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费项目对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:37
	 * @param entity：收费项目对象
	 */
	public void DelChargeType(ChargeType entity);
	
	/**
	 * 
	 * 方法的描述: 根据参数查询返回收费项目对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-2 上午10:33:47
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public ChargeType queryChargeTypeByParams(ChargeType paramsOb);
}
