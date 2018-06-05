package com.biencloud.smarthome.service.page.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.page.model.Component;

/**
 * 类名称：IComponentService 
 * 类描述： 电商页面服务接口。
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-15 下午1:57:02
 */
public interface IComponentService extends IService<Component,String>{

	/**
	 * 方法的描述: 根据ID查询Component
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 下午1:57:21
	 * @param id
	 * @return
	 */
	public Component getComponentById(String id);
	
	
	/**
	 * 修改组件
	 * @param component
	 */
	void updateComponent(Component component);
	
	/**
	 * 保存组件
	 * @param component
	 */
	void saveComponent(Component component);
	
	/**
	 * 删除组件
	 * @param id
	 */
	void deleteById(String id);

	List<Component> queryComponents(Component component);
	
	Paging<Component> queryComponentForPaging(Component component,int pageNum,int pageSize);
	
}
