package com.biencloud.smarthome.service.page.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.page.model.PageLayout;

/**
 * 类名称：IPageService 
 * 类描述： 电商页面服务接口。
 * @author: ykou  
 * @version: 0.1
 */
public interface IPageService extends IService<PageLayout,String>{
	
	/**
	 * 查询page列表
	 * @param id
	 * @return
	 */
	public List<PageLayout> queryAllPages(PageLayout page);
	
	/**
	 * 查询分页
	 * @param page
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<PageLayout> queryPageForPaging(PageLayout page,int pageNum,int pageSize);
	
}
