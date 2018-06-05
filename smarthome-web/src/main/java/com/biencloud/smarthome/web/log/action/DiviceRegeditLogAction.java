package com.biencloud.smarthome.web.log.action;

import java.util.List;
import org.springframework.util.CollectionUtils;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IDiviceRegeditLogService;
import com.biencloud.smarthome.web.log.vo.DiviceRegeditLogVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 
 * 类名称：DiviceRegeditLogAction 类描述： 设备注册日志管理类
 * 
 * @author: dehuaye
 * @version: 0.1
 * @date: 2012-5-7 上午10:55:57
 */
public class DiviceRegeditLogAction extends BaseAction<DiviceRegeditLogVO> {

	private static final long serialVersionUID = 1L;

	private IDiviceRegeditLogService diviceRegeditLogService;

	private DiviceRegeditLogVO diviceRegeditLog;
	
	private ISysParamService sysParamService;

	/**
	 * 
	 * 方法的描述: 设备注册日志列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:29
	 * @return
	 */
	public String queryList() throws Exception{
		try {
			PagingVO<DiviceRegeditLogVO> page = getPage();
			if (page == null)
				page = new PagingVO<DiviceRegeditLogVO>();
			PagingVO<DiviceRegeditLogVO> pagingVO = diviceRegeditLogService.queryDiviceRegeditLogForPaging(diviceRegeditLog, page.getPageNum(),page.getPageSize());
			List<DiviceRegeditLogVO> results = pagingVO.getResults();
			if(!CollectionUtils.isEmpty(results)){
				for(DiviceRegeditLogVO vo : results){
					vo.setDiveceType(this.getText(vo.getDiveceType()));
					vo.setEventAciton(this.getText(vo.getEventAciton()));
				}
			}
			setPage(pagingVO);
			 return "list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法的描述: 查找单个设备注册日志
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:00:57
	 * @return
	 */
	public String findById() throws Exception{
		try {
			String id=getRequest().getParameter("requestId");
			diviceRegeditLog=diviceRegeditLogService.getDiviceRegeditLog(id);
			diviceRegeditLog.setDiveceType(this.getText(diviceRegeditLog.getDiveceType()));
			diviceRegeditLog.setEventAciton(this.getText(diviceRegeditLog.getEventAciton()));
			setRequestAttribute("fileServerUrl", sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewdetail";
	}
	

	public IDiviceRegeditLogService getDiviceRegeditLogService() {
		return diviceRegeditLogService;
	}

	public void setDiviceRegeditLogService(IDiviceRegeditLogService diviceRegeditLogService) {
		this.diviceRegeditLogService = diviceRegeditLogService;
	}

	public DiviceRegeditLogVO getDiviceRegeditLog() {
		return diviceRegeditLog;
	}

	public void setDiviceRegeditLog(DiviceRegeditLogVO diviceRegeditLog) {
		this.diviceRegeditLog = diviceRegeditLog;
	}


	public ISysParamService getSysParamService() {
		return sysParamService;
	}


	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	

}
