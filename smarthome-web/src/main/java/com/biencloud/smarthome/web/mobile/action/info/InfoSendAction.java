package com.biencloud.smarthome.web.mobile.action.info;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.service.IInfoSendService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：InfoSendAction 类描述： 信息发布管理类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:14:14
 */
public class InfoSendAction extends BaseAction<InfoSendVO> {

	private static final long serialVersionUID = 1L;

	private IInfoSendService infoSendService;

	private IInfoReceiverService infoReceiverService;

	public InfoSendVO infoSend;

	/**
	 * 
	 * 方法的描述: 信息发布列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:14:54
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<InfoSendVO> page = getPage();
		if (page == null)
			page = new PagingVO<InfoSendVO>();
		if (infoSend == null)
			infoSend = new InfoSendVO();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			infoSend.setType(InfoReceiverVO.INFO_TYPE_PERSON);
			infoSend.setAreaId(new Long(getDistrictId()));
			infoSend.setSendUserId(new Long(getUserId()));
		}
		PagingVO<InfoSendVO> pagingVO = infoSendService.queryInfoSendVOForPaging(infoSend, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 保存信息发布
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:15:03
	 * @return
	 */
	public String save() throws Exception {
		setInfoSend();
		boolean result = infoSendService.saveInfoSendVO(infoSend, null, getUserType(), null);
		this.getRequest().setAttribute("editResult", result);
		infoSend = null;
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 发布
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:53
	 * @return
	 */
	public String sendInfo() throws Exception {
		String id = getRequest().getParameter("requestId");
		infoSendService.updateStatus(id, InfoSendVO.STATUSYESSEND, null, true);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述:到信息发布页面 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-5 下午3:10:21
	 * @return
	 */
	public String gotoInput() {
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 设置信息发布属性
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:32
	 * @param comStr
	 */
	private void setInfoSend() {
		infoSend.setType(InfoReceiverVO.INFO_TYPE_PERSON);
		infoSend.setAreaId(new Long(getDistrictId()));
		infoSend.setSendMode(InfoSendVO.SENDMODE_GOING);
		infoSend.setSendUserId(new Long(getUserId()));
		infoSend.setSendUserName(getLoginVO().getUserName());
	}

	/**
	 * 
	 * 方法的描述: 查找单个信息发布
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:46
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		infoSend = infoSendService.getInfoSendVO(id);
		if (ActionUtils.judgShowDetail(getRequest()))
			return "detailPage";
		else
			return "detail";
	}

	public IInfoSendService getInfoSendService() {
		return infoSendService;
	}

	public void setInfoSendService(IInfoSendService infoSendService) {
		this.infoSendService = infoSendService;
	}

	public InfoSendVO getInfoSend() {
		return infoSend;
	}

	public void setInfoSend(InfoSendVO infoSend) {
		this.infoSend = infoSend;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

}
