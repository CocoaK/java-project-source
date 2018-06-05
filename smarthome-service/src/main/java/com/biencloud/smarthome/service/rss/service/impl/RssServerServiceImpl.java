package com.biencloud.smarthome.service.rss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.rss.model.RssServer;
import com.biencloud.smarthome.service.rss.service.IRssServerService;

/**
 * 
 * 类名称：RssServerServiceImpl 
 * 类描述： Rss服务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-6 下午6:50:21
 */
public class RssServerServiceImpl extends BaseService<RssServer,Long> implements IRssServerService{
	private ILoginService loginService;
		
	public ILoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	/**
	 * 
	 * 方法的描述:查询Rss服务信息并分页
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:24:26
	 * @see com.biencloud.smarthome.service.rss.service.IRssServerService#queryRssServerForPaging(com.biencloud.smarthome.service.rss.model.RssServer)
	 * @param rssServer
	 * @return Paging
	 */
	@Override
	public Paging<RssServer> queryRssServerForPaging(RssServer rssServer,int pageNum,
			int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql,rssServer);
		jpql.insert(0, "SELECT "+ REPLACE_CHARS +" FROM RssServer rssServer");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "rssServer");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(rssServer)");
		queryString = queryString + " order by rssServer.createdTime desc ";
		Paging<RssServer> paging = queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
		for (int i = 0, length = paging.getResults().size(); i < length; i++) {
			paging.getResults().get(i).setUserLogin(loginService.getLoginByLoginName(paging.getResults().get(i).getCreatedUser()));
		}
		return paging;
	}

	/**
	 * 方法的描述: 创建属性变量名和属性值映射。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-29 上午10:42:04
	 * @param jpql
	 * @param rssServer
	 * @return
	 */
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			RssServer rssServer) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (rssServer == null)
			return params;
		if (StringUtils.isNotBlank(rssServer.getRssName()))
			appendCondition(jpql, " rssServer.rssName LIKE :rssName", "rssName", 
					"%" + rssServer.getRssName() + "%", params);
		if (StringUtils.isNotBlank(rssServer.getStatus()))
			appendCondition(jpql, " rssServer.status = :status", "status", 
					rssServer.getStatus(), params);
		return params;
	}
	@Override
	public RssServer queryRssServerByCode(String rssServerCode) {
		RssServer rssServer=null;
		if(rssServerCode!=null)
		{
			List<RssServer> list=super.find("from RssServer where rssServerCode='"+rssServerCode+"'");
			if(list!=null&&!list.isEmpty())
			{
				rssServer=list.get(0);
			}
		}
		return rssServer;
	}
		
		
}
