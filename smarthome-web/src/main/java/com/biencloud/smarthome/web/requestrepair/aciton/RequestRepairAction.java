package com.biencloud.smarthome.web.requestrepair.aciton;


import java.util.Date;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：RequestRepairAction 
 * 类描述： 报修管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:22:47
 */
public class RequestRepairAction extends BaseAction<RequestRepairVO> {

	private static final long serialVersionUID = 1L;
	
	private static final String LIST="list";
	
	private static final String EDIT_PAGE="editPage";

	private IRequestRepairService requestRepairService;

	private RequestRepairVO requestRepair;
	
	/**
	 * 
	 * 方法的描述: 报修列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:22:56
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<RequestRepairVO> page = getPage();
		if (page == null)
			page = new PagingVO<RequestRepairVO>();
		if (requestRepair == null)
			requestRepair = new RequestRepairVO();
		if (Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())) {
			requestRepair.setDistrictId(new Long(getDistrictId()));
			requestRepair.setIsexcuteNoSubmit(true);
		} else if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			OwnerUserVO ouVo = new OwnerUserVO();
			ouVo.setUserId(getUserId());
			requestRepair.setOwnerUser(ouVo);
		}
		PagingVO<RequestRepairVO> pagingVO = requestRepairService.queryRequestRepairVOForPaging(requestRepair, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return LIST;
	}
	
	/**
	 * 
	 * 方法的描述: 保存报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:04
	 * @return
	 */
	public String save() throws Exception {
		OwnerUserVO ouVo = new OwnerUserVO();
		ouVo.setUserId(getUserId());
		requestRepair.setOwnerUser(ouVo);
		requestRepair.setDistrictId(new Long(getDistrictId()));
		requestRepair.setRequestTime(new Date(System.currentTimeMillis()));
		boolean result = requestRepairService.saveRequestRepairVO(requestRepair);
		this.getRequest().setAttribute("editResult", result);
		requestRepair = null;
		return LIST;
	}
	
	/**
	 * 
	 * 方法的描述: 到更新页面
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:26
	 * @return
	 */
	public String goToUpdate() throws Exception {
		return EDIT_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述:更新报修 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:12
	 * @return
	 */
	public String update() throws Exception {
		boolean result = requestRepairService.updateRequestRepairVO(requestRepair);
		this.getRequest().setAttribute("editResult", result);
		return EDIT_PAGE;
	}
	
	
	
	/**
	 * 
	 * 方法的描述: 查找单个报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:20
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		requestRepair = requestRepairService.getRequestRepairVO(id);
		setRequestAttribute(ActionUtils.SHOWDETAIL, null);
		if (ActionUtils.judgShowDetail(getRequest())) {
			return "detailPage";
		} else
			return EDIT_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述: 处理报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:27
	 * @return
	 */
	public String updateStatus() throws Exception {
		String id = getRequest().getParameter("requestId");
		String status = getRequest().getParameter("status");
		RequestRepairVO requestRepair = requestRepairService.getRequestRepairVO(id);
		requestRepair.setStatus(status);
		if (RequestRepairVO.STATUS_YESPROCESS.toString().equals(status)) {
			requestRepair.setRepairTime(new Date(System.currentTimeMillis()));
			PaUserVO pa = new PaUserVO();
			pa.setUserId(getUserId());
			requestRepair.setPaUser(pa);
		} else {
			requestRepair.setRequestTime(new Date(System.currentTimeMillis()));
		}
		boolean result = requestRepairService.updateRequestRepairVO(requestRepair);
		this.getRequest().setAttribute("editResult", result);
		return LIST;
	}
	/**
	 * 
	 * 方法的描述:  删除报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:38
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = requestRepairService.delRequestRepairVO(id);
		this.getRequest().setAttribute("editResult", result);
		return LIST;
	}

	/**
	 * 
	 * 方法的描述:门口机报修 
	 * @return
	 */
	public String repairInfo() throws Exception {
		return "info";
	}
	
	public IRequestRepairService getRequestRepairService() {
		return requestRepairService;
	}

	public void setRequestRepairService(IRequestRepairService requestRepairService) {
		this.requestRepairService = requestRepairService;
	}

	public RequestRepairVO getRequestRepair() {
		return requestRepair;
	}

	public void setRequestRepair(RequestRepairVO requestRepair) {
		this.requestRepair = requestRepair;
	}

	
	

	

}
