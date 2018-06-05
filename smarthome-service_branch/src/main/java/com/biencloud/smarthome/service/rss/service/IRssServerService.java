package com.biencloud.smarthome.service.rss.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.rss.model.RssServer;

/**
 * 
 * 类名称：IRssServerService 
 * 类描述： Rss服务器管理服务
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-4 下午3:05:00
 */
public interface IRssServerService extends IService<RssServer,Long>{
	/**
	 * 
	 * 方法的描述: 查询RssServer，并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:09:56
	 * @param rssServer
	 * @return Paging
	 */
	public Paging<RssServer> queryRssServerForPaging(RssServer rssServer, int pageNum,
			int pageSize);
	
	/**
	 * 
	 * 方法的描述: 根据code查询Rss服务
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-6 下午6:47:49
	 * @param rssServerCode
	 * @return RssServer
	 */
	public RssServer queryRssServerByCode(String rssServerCode);
}
