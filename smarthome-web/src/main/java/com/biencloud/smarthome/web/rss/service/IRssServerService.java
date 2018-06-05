package com.biencloud.smarthome.web.rss.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.vo.RssServerVO;

/**
 * 
 * 类名称：IRssServerService 
 * 类描述： Rss服务器信息管理web端服务
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-4 下午4:02:48
 */
public interface IRssServerService {
	/**
	 * 
	 * 方法的描述: 查询Rss服务信息并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午4:09:43
	 * @param rssServer
	 * @param pageNum
	 * @param pageSize
	 * @return PagingVO
	 */
	public PagingVO<RssServerVO> queryRssServerForPaging(RssServerVO rssServer, int pageNum,
			int pageSize);
	
	/**
	 * 
	 * 方法的描述: 查询Rss服务信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午4:10:13
	 * @param rssId
	 * @return RssServerVO
	 */
	public RssServerVO queryRssServerById(Long rssId);
	
	/**
	 * 
	 * 方法的描述:修改Rss服务信息 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午4:10:44
	 * @param rssServerVO
	 */
	public void update(RssServerVO rssServerVO);
	
	/**
	 * 
	 * 方法的描述:删除Rss服务信息 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午4:11:11
	 * @param rssServerVO
	 */
	public void remove(RssServerVO rssServerVO);
}
