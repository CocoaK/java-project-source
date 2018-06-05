package com.biencloud.smarthome.web.info.action;

import java.io.PrintWriter;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：InfoReceiverAction 
 * 类描述： 信息接收管理类
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
		if (Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())) {
			infoReceiver.setAreaId(new Long(getDistrictId()));
		} else if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			infoReceiver.setAreaId(new Long(getDistrictId()));
			infoReceiver.setHouseId(getHouseId(getUserId()));
		}
		PagingVO<InfoReceiverVO> pagingVO = infoReceiverService.queryInfoReceiverVOForPaging(infoReceiver, page.getPageNum(), page.getPageSize(), getUserId(), getUserType());
		setPage(pagingVO);
		setRequestAttribute("LoginUserType", getUserType());
		return "list";
	}
	
	private long getHouseId(String userId){
		return new Long(ownerUserService.getOwnerUserDetail(userId).getHouseId());
	}
	
	public String getNoReadReceiverCount() throws Exception {
		Long result = infoReceiverService.getNoReadReceiverCount(getUserType(), getUserId(), getDistrictId());
		this.getResponse().setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		out = this.getResponse().getWriter();
		out.write(result.toString());
		out.close();
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法的描述: 查找单个信息接收
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:40
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		infoReceiver = infoReceiverService.getInfoReceiverVO(id, getUserType());
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述:  删除信息接收
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:48
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = infoReceiverService.delInfoReceiverVO(id);
		this.getRequest().setAttribute("editResult", result);
		return "list";
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
