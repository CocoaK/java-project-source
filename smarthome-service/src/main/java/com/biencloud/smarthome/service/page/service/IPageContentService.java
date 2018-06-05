package com.biencloud.smarthome.service.page.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.page.model.PageContent;

/**
 * 类名称：IPageContentService 
 * 类描述： 电商页面服务接口。
 * @author: ykou  
 * @version: 0.1
 */
public interface IPageContentService extends IService<PageContent,String>{
	
	/**
	 * 查询pageContent列表
	 * @param id
	 * @return
	 */
	public List<PageContent> queryAllPageContents(PageContent pageContent);
	
	/**
	 * 查询分页
	 * @param pageContent
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<PageContent> queryPageForPaging(PageContent pageContent,int pageNum,int pageSize);
	
}
