package com.biencloud.smarthome.web.log.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IAppDataLogService;
import com.biencloud.smarthome.web.log.vo.AppDataLogVO;
/**
 * 类名称：AppDataLogAction 
 * 类描述： App终端请求日志动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午11:18:52
 */
@SuppressWarnings("serial")
public class AppDataLogAction extends BaseAction<AppDataLogVO>{
	
	private AppDataLogVO appDataLog;
	private IAppDataLogService appDataLogService;

	public AppDataLogVO getAppDataLog() {
		return appDataLog;
	}

	public void setAppDataLog(AppDataLogVO appDataLog) {
		this.appDataLog = appDataLog;
	}

	public IAppDataLogService getAppDataLogService() {
		return appDataLogService;
	}

	public void setAppDataLogService(IAppDataLogService appDataLogService) {
		this.appDataLogService = appDataLogService;
	}

	/**
	 * 方法的描述: 分页查询
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-22 上午11:08:11
	 * @return
	 */
	public String queryList() throws Exception{
		PagingVO<AppDataLogVO> page = getPage();
		if(page == null)
			page = new PagingVO<AppDataLogVO>();
		PagingVO<AppDataLogVO> appDataLogList = appDataLogService.queryAppDataLogForPaging(appDataLog, page.getPageNum(), page.getPageSize());
		List<AppDataLogVO> results = appDataLogList.getResults();
		for(AppDataLogVO vo : results){
			//国际化动作名称
			if(StringUtils.isNotBlank(vo.getAction())){
				vo.setAction(this.getText("app."+vo.getAction()));
			}
		}
		appDataLogList.setResults(results);
		setPage(appDataLogList);
		return "query_list";
	}
	
	/**
	 * 方法的描述: 查询明细
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-22 上午11:08:29
	 * @return
	 */
	public String queryDetail() throws Exception{
		appDataLog = appDataLogService.getAppDataLogById(appDataLog.getId());
		return "query_detail";
	}
}
