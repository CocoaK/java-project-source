package com.biencloud.smarthome.web.log.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IAppDataLogService;
import com.biencloud.smarthome.web.log.vo.AppDataLogVO;
import com.biencloud.smarthome.web.wsclient.stub.AppDataLog;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 类名称：AppDataLogServiceImpl 
 * 类描述： App请求数据服务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-19 下午3:53:01
 */
public class AppDataLogServiceImpl extends BaseService<AppDataLogVO> implements IAppDataLogService{
	private static final String REQUEST_TIME = "requestTime";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	@Override
	public AppDataLogVO saveOrUpdateAppDataLog(AppDataLogVO appDataLogVO) {
		AppDataLog appDataLog = getSmartHomeService().saveOrUpdateAppDataLog(voToAppDataLog(appDataLogVO));
		return appDataLogToVO(appDataLog);
	}

	@Override
	public PagingVO<AppDataLogVO> queryAppDataLogForPaging(AppDataLogVO appDataLogVO, int pageNum, int pageSize) {
		AppDataLog appDataLog = new AppDataLog();
		if(appDataLogVO != null){
			appDataLog = voToAppDataLog(appDataLogVO);
		}
		Paging paging = getSmartHomeService().queryAppDataLogForPaging(appDataLog, pageNum, pageSize);
		PagingVO<AppDataLogVO> pagingVO = convertToPagingVO(paging,REQUEST_TIME,BEGIN_TIME,END_TIME);
		return pagingVO;
	}

	@Override
	public AppDataLogVO getAppDataLogById(Integer id) {
		AppDataLog appDataLog = getSmartHomeService().getAppDataLogById(id);
		return appDataLogToVO(appDataLog);
	}
	
	private AppDataLog voToAppDataLog(AppDataLogVO appDataLogVO){
		AppDataLog appDataLog = new AppDataLog();
		if(appDataLogVO != null)
			copyProperties(appDataLogVO,appDataLog,false,REQUEST_TIME,BEGIN_TIME,END_TIME);
		return appDataLog;
	}
	
	private AppDataLogVO appDataLogToVO(AppDataLog appDataLog){
		AppDataLogVO appDataLogVO = new AppDataLogVO();
		if(appDataLog != null)
			copyProperties(appDataLog,appDataLogVO,true,REQUEST_TIME,BEGIN_TIME,END_TIME);
		return appDataLogVO;
	}
}
