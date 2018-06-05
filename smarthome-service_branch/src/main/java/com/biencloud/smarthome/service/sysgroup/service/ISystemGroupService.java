package com.biencloud.smarthome.service.sysgroup.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ISystemGroupService 
 * 类描述： 组织模块领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:34:47
 */

public interface ISystemGroupService extends IService<SystemGroup, Long>{

	/**
	 * 
	 * 方法的描述: 查询组织数据（不分页的）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:34:54
	 * @param pageNo 页码 ，已舍弃
	 * @param pageSize 每页记录数，已舍弃
	 * @param ob 组织对象
	 * @return
	 */
	public List<SystemGroup> querySystemGroup(int pageNo, int pageSize, SystemGroup sg);
	/**
	 * 
	 * 方法的描述: 根据小区对应的组织编号获取完整组织路径信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:35:46
	 * @param comId 小区对应的组织编号
	 * @return
	 */
	public String getCompletePosition(String comId);
	
	/**
	 * 保存或更新组织
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:01
	 * @param systemGroup 组织对象
	 * @return
	 */
	public boolean saveOrUpdateSystemGroup(SystemGroup systemGroup);
	/**
	 * 
	 * 方法的描述:  删除指定编号的组织
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:15
	 * @param id 组织编号
	 * @return
	 */
	public boolean deleteSystemGroupById(Long id);
	/**
	 * 
	 * 方法的描述: 通过组织编号获取组织信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:34
	 * @param id 组织编号
	 * @return
	 */
	public SystemGroup findByID(Long id);
	
	/**
	 * 
	 * 方法的描述: 根据城市名称模糊查询，结果返回小区对应的组织编号列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:50
	 * @param cityName 城市名称
	 * @return
	 */
	public List<String> getDistrictIdByCityName(String cityName);
	/**
	 * 
	 * 方法的描述: 根据小区对应的组织编号获取所在城市名称
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-14 上午10:26:08
	 * @param comId 小区对应的组织编号
	 * @return
	 */
	public String getCityNameByDistrictId(String comId);
}
