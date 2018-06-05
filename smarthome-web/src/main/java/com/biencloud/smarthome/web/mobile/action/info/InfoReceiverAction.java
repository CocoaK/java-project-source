package com.biencloud.smarthome.web.mobile.action.info;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：InfoReceiverAction 类描述： 信息接收管理类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:18:24
 */
public class InfoReceiverAction extends BaseAction<InfoReceiverVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IInfoReceiverService infoReceiverService;

	private InfoReceiverVO infoReceiver;

	private IOwnerUserService ownerUserService;

	/**
	 * 
	 * 方法的描述: 信息接收列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:32
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<InfoReceiverVO> page = getPage();
		if (page == null)
			page = new PagingVO<InfoReceiverVO>();
		if (infoReceiver == null)
			infoReceiver = new InfoReceiverVO();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			infoReceiver.setAreaId(new Long(getDistrictId()));
			infoReceiver.setHouseId(getHouseId(getUserId()));
		}
		PagingVO<InfoReceiverVO> pagingVO = infoReceiverService.queryInfoReceiverVOForPaging(infoReceiver, page.getPageNum(), page.getPageSize(), getUserId(), getUserType());
		setPage(pagingVO);
		return "list";
	}

	private long getHouseId(String userId) {
		return new Long(ownerUserService.getOwnerUserDetail(userId).getHouseId());
	}

	/**
	 * 
	 * 方法的描述: 查找单个信息接收
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:40
	 * @return
	 */
	public String findById() {
		String id = getRequest().getParameter("requestId");
		infoReceiver = infoReceiverService.getInfoReceiverVO(id, getUserType());
		return "detail";
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	public InfoReceiverVO getInfoReceiver() {
		return infoReceiver;
	}

	public void setInfoReceiver(InfoReceiverVO infoReceiver) {
		this.infoReceiver = infoReceiver;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

}
