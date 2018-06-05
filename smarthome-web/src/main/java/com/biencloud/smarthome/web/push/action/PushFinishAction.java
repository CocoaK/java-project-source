package com.biencloud.smarthome.web.push.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.service.IPushFinishService;
import com.biencloud.smarthome.web.push.vo.PushFinishVO;


/**
 * 
 * 类名称：PushFinishAction 类描述：推送完成Action类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午4:37:01
 */
public class PushFinishAction extends BaseAction<PushFinishVO> {
	private IPushFinishService pushFinishService;
	private PushFinishVO pushFinishVO;

	/**
	 * 
	 * 方法的描述: 分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 下午4:36:00
	 * @return
	 */
	public String list() throws Exception{
		PagingVO<PushFinishVO> page = getPage();
		if (page == null)
			page = new PagingVO<PushFinishVO>();
		
        //查询
		PagingVO<PushFinishVO> pagingVO = pushFinishService.queryPushFinishForPaging(page.getPageNum(), page.getPageSize(), pushFinishVO);

		setPage(pagingVO);
		return "list";
	}
	/**
	 * 
	 * 方法的描述: 根据设备编号分页
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-13 上午9:25:58
	 * @return
	 */
	public String listByDeviceNo() throws Exception{
		this.list();
		return "list_deviceNo";
	}
	/**
	 * 
	 * 方法的描述: 查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-14 上午11:46:58
	 * @return
	 */
	public String query() throws Exception{
		PagingVO<PushFinishVO> page = getPage();
		if (page == null)
			page = new PagingVO<PushFinishVO>();
		page.setPageNum(1);
		//查询
		PagingVO<PushFinishVO> pagingVO = pushFinishService.query(page.getPageNum(), page.getPageSize(), pushFinishVO);

		setPage(pagingVO);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 查看详细
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-5 下午7:57:29
	 * @return
	 */
	public String viewDetail() throws Exception{
		pushFinishVO = pushFinishService.findById(pushFinishVO.getId());
		return "detail";
	}

	public IPushFinishService getPushFinishService() {
		return pushFinishService;
	}

	public void setPushFinishService(IPushFinishService pushFinishService) {
		this.pushFinishService = pushFinishService;
	}

	public PushFinishVO getPushFinishVO() {
		return pushFinishVO;
	}

	public void setPushFinishVO(PushFinishVO pushFinishVO) {
		this.pushFinishVO = pushFinishVO;
	}

}
