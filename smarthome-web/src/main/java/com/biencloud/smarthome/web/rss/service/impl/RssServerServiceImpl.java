package com.biencloud.smarthome.web.rss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.rss.service.IRssServerService;
import com.biencloud.smarthome.web.rss.vo.RssServerVO;
import com.biencloud.smarthome.web.wsclient.stub.Login;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.RssServer;

/**
 * 
 * 类名称：RssServerServiceImpl 
 * 类描述： Rss服务信息实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-4 下午4:18:16
 */
public class RssServerServiceImpl extends BaseService<RssServerVO> implements IRssServerService{
	private static final String CREATED_TIME = "createdTime";
	private static final String USER_LOGIN = "userLogin";
	private static final String LAST_LOGIN_TIME = "lastLoginTime";
	
	@Override
	public PagingVO<RssServerVO> queryRssServerForPaging(RssServerVO rssServerVO, int pageNum, int pageSize) {
		RssServer rs = new RssServer();
		copyProperties(rssServerVO,rs,new String[]{USER_LOGIN},false,CREATED_TIME);
		Paging paging = getSmartHomeService().queryRssServerForPaging(rs, pageNum, pageSize);
		return toPagingVO(paging);
	}

	@Override
	public RssServerVO queryRssServerById(Long rssId) {
		RssServer rs = getSmartHomeService().queryRssServerById(rssId);
		LoginVO loginVO = new LoginVO();
		RssServerVO rsVO = new RssServerVO();
		copyProperties(rs.getUserLogin(),loginVO, true, CREATED_TIME, LAST_LOGIN_TIME);
		copyProperties(rs,rsVO,new String[]{USER_LOGIN},true,CREATED_TIME);
		rsVO.setUserLogin(loginVO);
		return rsVO;
	}

	@Override
	public void update(RssServerVO rssServerVO) {
		RssServer rs = new RssServer();
		Login login = new Login();
		copyProperties(rssServerVO.getUserLogin(),login, false, CREATED_TIME, LAST_LOGIN_TIME);
		copyProperties(rssServerVO,rs,new String[]{USER_LOGIN},false,CREATED_TIME);
		rs.setUserLogin(login);
		getSmartHomeService().updateRssServer(rs);
		
	}

	@Override
	public void remove(RssServerVO rssServerVO) {
		RssServer rs = new RssServer();
		Login login = new Login();
		copyProperties(rssServerVO.getUserLogin(),login, false, CREATED_TIME, LAST_LOGIN_TIME);
		copyProperties(rssServerVO,rs,new String[]{USER_LOGIN},false,CREATED_TIME);
		rs.setUserLogin(login);
		getSmartHomeService().removeRssServer(rs);
		
	}
	
	/**
	 * 
	 * 方法的描述: paging中的results中的对象有对象属性例如本方法中的RssServerVO中有LoginVO类型的属性，需要进行转换。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-5 下午1:20:08
	 * @param paging
	 * @param ignoreProperties	忽略的属性集
	 * @param xmlDateProperties	日期属性集
	 * @return
	 */
	public PagingVO<RssServerVO> toPagingVO(Paging paging){
		PagingVO<RssServerVO> pagingVO = new PagingVO<RssServerVO>();
		if(paging == null)
			return pagingVO;					
		copyProperties(paging, pagingVO);
		List<RssServerVO> voResults = new ArrayList<RssServerVO>();
		List<Object> results = paging.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				RssServerVO vo = new RssServerVO();
				RssServer rssServer = (RssServer) results.get(index);
				LoginVO loginVO = new LoginVO();
				copyProperties(rssServer.getUserLogin(),loginVO, true, CREATED_TIME, LAST_LOGIN_TIME);
				copyProperties(rssServer,vo,new String[]{USER_LOGIN},true,CREATED_TIME);
				vo.setUserLogin(loginVO);
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}
}
