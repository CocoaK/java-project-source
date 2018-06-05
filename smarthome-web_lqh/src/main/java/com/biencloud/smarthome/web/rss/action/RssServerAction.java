package com.biencloud.smarthome.web.rss.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.rss.service.IRssServerService;
import com.biencloud.smarthome.web.rss.vo.RssServerVO;

/**
 * 
 * 类名称：RssServerAction 
 * 类描述：Rss服务动作类 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-4 下午7:07:49
 */
public class RssServerAction extends BaseAction<RssServerVO>{
	private static final long serialVersionUID = 6772055794952919475L;
	private IRssServerService rssServerService;
	private RssServerVO rssServerVO;
	private boolean successFlag;
	
	/**
	 * 
	 * 方法的描述: 查询分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:18:02
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception{
		PagingVO<RssServerVO> page = getPage();
		if(page == null)
			page = new PagingVO<RssServerVO>();
		//查询并分页
		PagingVO<RssServerVO> rssList = getRssServerService()
				.queryRssServerForPaging(rssServerVO, page.getPageNum(), page.getPageSize());
		setPage(rssList);
		return "query_list";
	}
	
	/**
	 * 
	 * 方法的描述: 查询明细
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:18:29
	 * @return
	 * @throws Exception
	 */
	public String queryDetail() throws Exception{
		RssServerVO rssServer = getRssServerService().queryRssServerById(rssServerVO.getRssId());
		setRssServerVO(rssServer);
		return "query_detail";
	}
	
	/**
	 * 
	 * 方法的描述: 修改页面
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午5:14:42
	 * @return
	 * @throws Exception
	 */
	public String updateDetail() throws Exception{
		RssServerVO rssServer = getRssServerService().queryRssServerById(rssServerVO.getRssId());
		setRssServerVO(rssServer);
		return "update_detail";
	}
	
	/**
	 * 
	 * 方法的描述:修改Rss服务 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:27:18
	 * @return
	 * @throws Exception
	 */
	public String rssUpdate() throws Exception{
		RssServerVO rssServer = getRssServerService().queryRssServerById(rssServerVO.getRssId());
		if(rssServerVO.getRssName()!=null)
			rssServer.setRssName(rssServerVO.getRssName());
		if(rssServerVO.getServerUrl()!=null)
			rssServer.setServerUrl(rssServerVO.getServerUrl());
		if(rssServerVO.getStatus()!=null)
			rssServer.setStatus(rssServerVO.getStatus());
		getRssServerService().update(rssServer);
		setSuccessFlag(true);
		//setRssServerVO(null);
		return "rss_update";
	}
	
	/**
	 * 
	 * 方法的描述:删除Rss服务信息 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午7:29:46
	 * @return
	 * @throws Exception
	 */
	public String rssRemove() throws Exception{
		RssServerVO rssServer = getRssServerService().queryRssServerById(rssServerVO.getRssId());
		getRssServerService().remove(rssServer);
		setSuccessFlag(true);
		queryList();
		return "rss_remove";
	}
	
	public IRssServerService getRssServerService() {
		return rssServerService;
	}
	public void setRssServerService(IRssServerService rssServerService) {
		this.rssServerService = rssServerService;
	}
	public RssServerVO getRssServerVO() {
		return rssServerVO;
	}
	public void setRssServerVO(RssServerVO rssServerVO) {
		this.rssServerVO = rssServerVO;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}


}
