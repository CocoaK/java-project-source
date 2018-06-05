package com.biencloud.smarthome.web.push.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.service.IPushService;
import com.biencloud.smarthome.web.push.vo.PushFinishVO;
import com.biencloud.smarthome.web.push.vo.PushVO;

/**
 * 
 * 类名称：PushAction 类描述：推送Action类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午4:37:20
 */
@SuppressWarnings("serial")
public class PushAction extends BaseAction<PushVO> {
	private IPushService pushService;
	private PushVO pushVO;
	private PushFinishVO pushFinishVO;

	/**
	 * 
	 * 方法的描述:分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 下午4:39:25
	 * @return
	 */
	public String list() throws Exception {
		PagingVO<PushVO> page = getPage();
		if (page == null)
			page = new PagingVO<PushVO>();
		// System.out.println("-------pushVO:"+pushVO);
		//查询
		PagingVO<PushVO> pagingVO = pushService.queryPushForPaging(page.getPageNum(), page.getPageSize(), pushVO);

		setPage(pagingVO);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 根据设备编号查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-13 上午9:32:58
	 * @return
	 */
	public String queryByDeviceNo() throws Exception {
		this.list();
		return "list_deviceNo";
	}

	/**
	 * 
	 * 方法的描述: 查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-14 上午11:46:34
	 * @return
	 */
	public String query() throws Exception {
		PagingVO<PushVO> page = getPage();

		if (page == null)
			page = new PagingVO<PushVO>();
		page.setPageNum(1);
		//查询
		PagingVO<PushVO> pagingVO = pushService.query(page.getPageNum(), page.getPageSize(), pushVO);

		setPage(pagingVO);

		return "list";
	}

	/**
	 * 
	 * 方法的描述:重推
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-17 下午4:15:15
	 * @return
	 */
	public String repush() throws Exception {
		if (pushFinishVO != null && pushFinishVO.getId() != null) {
			pushService.repush(pushFinishVO.getId());
		}
		return list();
	}

	/**
	 * 
	 * 方法的描述: 查看详细
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-5 下午7:55:46
	 * @return
	 */
	public String viewDetail() throws Exception {
		pushVO = pushService.queryById(pushVO.getId());
		return "detail";
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public PushVO getPushVO() {
		return pushVO;
	}

	public void setPushVO(PushVO pushVO) {
		this.pushVO = pushVO;
	}

	public PushFinishVO getPushFinishVO() {
		return pushFinishVO;
	}

	public void setPushFinishVO(PushFinishVO pushFinishVO) {
		this.pushFinishVO = pushFinishVO;
	}

}
