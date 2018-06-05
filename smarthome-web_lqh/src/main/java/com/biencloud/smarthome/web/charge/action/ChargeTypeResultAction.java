package com.biencloud.smarthome.web.charge.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeTypeResultService;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeTypeResultAction 
 * 类描述： 收费项目结果管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:06:13
 */
public class ChargeTypeResultAction extends BaseAction<ChargeTypeResultVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeTypeResultService chargeTypeResultService;

	private ChargeTypeResultVO chargeTypeResult;

	/**
	 * 
	 * 方法的描述: 收费项目结果列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:23
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeTypeResultVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeTypeResultVO>();
		PagingVO<ChargeTypeResultVO> pagingVO = chargeTypeResultService.queryChargeTypeResultVOForPaging(chargeTypeResult, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 保存收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:30
	 * @return
	 */
	public String save() throws Exception {
		boolean result = chargeTypeResultService.saveChargeTypeResultVO(chargeTypeResult);
		this.getRequest().setAttribute("editResult", result);
		return "list";
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
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述:  更新收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:37
	 * @return
	 */
	public String update() throws Exception {
		boolean result = chargeTypeResultService.updateChargeTypeResultVO(chargeTypeResult);
		this.getRequest().setAttribute("editResult", result);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:46
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeTypeResult = chargeTypeResultService.getChargeTypeResultVO(id);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述:  删除收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:53
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = chargeTypeResultService.delChargeTypeResultVO(id);
		this.getRequest().setAttribute("editResult", result);
		return "list";
	}

	public IChargeTypeResultService getChargeTypeResultService() {
		return chargeTypeResultService;
	}

	public void setChargeTypeResultService(IChargeTypeResultService chargeTypeResultService) {
		this.chargeTypeResultService = chargeTypeResultService;
	}

	public ChargeTypeResultVO getChargeTypeResult() {
		return chargeTypeResult;
	}

	public void setChargeTypeResult(ChargeTypeResultVO chargeTypeResult) {
		this.chargeTypeResult = chargeTypeResult;
	}


}
