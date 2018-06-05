package com.biencloud.smarthome.web.log.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IClientLogService;
import com.biencloud.smarthome.web.log.vo.ClientLogVO;

/**
 * 类名称：ClientLogAction 
 * 类描述： 终端日志动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午11:20:05
 */
public class ClientLogAction extends BaseAction<ClientLogVO> {

	private static final long serialVersionUID = 8978765403801771267L;
	private IClientLogService clientLogService;
	private ClientLogVO clientLog;

	/**
	 * 
	 * 方法的描述: 列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-24 下午4:31:37
	 * @return
	 */
	public String list() throws Exception{
		PagingVO<ClientLogVO> page = getPage();
		if (page == null)
			page = new PagingVO<ClientLogVO>();

		PagingVO<ClientLogVO> pagingVO = clientLogService.queryClientLogForPaging(page.getPageNum(), page.getPageSize(), clientLog);

		setPage(pagingVO);
		return "list";
	}
	/**
	 * 
	 * 方法的描述: 根据Mac地址查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-13 上午9:35:59
	 * @return
	 */
	public String queryByMac() throws Exception{
		this.list();
		return "list_mac";
	}
	/**
	 * 
	 * 方法的描述: 详细
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-24 下午5:31:25
	 * @return
	 */
	public String detail() throws Exception{
		if (clientLog != null) {
			clientLog = clientLogService.queryClientLogById(clientLog.getId());
		}
		return "detail";
	}

	public IClientLogService getClientLogService() {
		return clientLogService;
	}

	public void setClientLogService(IClientLogService clientLogService) {
		this.clientLogService = clientLogService;
	}

	public ClientLogVO getClientLog() {
		return clientLog;
	}

	public void setClientLog(ClientLogVO clientLog) {
		this.clientLog = clientLog;
	}

}