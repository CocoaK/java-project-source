package com.biencloud.smarthome.web.charge.action;

import java.io.PrintWriter;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeMonetaryUnitAction 
 * 类描述：  收费货币管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:04:08
 */
public class ChargeMonetaryUnitAction extends BaseAction<ChargeMonetaryUnitVO> {

	private static final long serialVersionUID = 1L;

	private IChargeMonetaryUnitService chargeMonetaryUnitService;

	private ChargeMonetaryUnitVO chargeMonetaryUnit;

	/**
	 * 
	 * 方法的描述: 收费货币列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:18
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeMonetaryUnitVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeMonetaryUnitVO>();
		PagingVO<ChargeMonetaryUnitVO> pagingVO = chargeMonetaryUnitService.queryChargeMonetaryUnitVOForPaging(chargeMonetaryUnit, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 保存收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:26
	 * @return
	 */
	public String save() throws Exception {
		boolean result = chargeMonetaryUnitService.saveChargeMonetaryUnitVO(chargeMonetaryUnit);
		this.getRequest().setAttribute("editResult", result);
		chargeMonetaryUnit = null;
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述:验证名称是否存在 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:25:19
	 * @return
	 */
	public String getName() throws Exception {
		String validateResult = "true";
		ChargeMonetaryUnitVO result = chargeMonetaryUnitService.queryChargeMonetaryUnitByParams(chargeMonetaryUnit);
		if (result != null && result.getId() != null) {
			validateResult = "false";
		}
		this.getResponse().setContentType("text/html; charset=UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		out.write(validateResult);
		out.close();
		return null;
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
	 * 方法的描述: 更新收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:35
	 * @return
	 */
	public String update() throws Exception {
		boolean result = chargeMonetaryUnitService.updateChargeMonetaryUnitVO(chargeMonetaryUnit);
		this.getRequest().setAttribute("editResult", result);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:44
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeMonetaryUnit = chargeMonetaryUnitService.getChargeMonetaryUnitVO(id);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 删除收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:52
	 * @return
	 */
	public String delById() throws Exception {
		try {
			String id=getRequest().getParameter("requestId");
		    boolean result=chargeMonetaryUnitService.delChargeMonetaryUnitVO(id);
			this.getRequest().setAttribute("delResult", result);
		} catch (Exception e) {
			this.getRequest().setAttribute("delResult", false);
		}
		return "list";
	}

	public IChargeMonetaryUnitService getChargeMonetaryUnitService() {
		return chargeMonetaryUnitService;
	}

	public void setChargeMonetaryUnitService(IChargeMonetaryUnitService chargeMonetaryUnitService) {
		this.chargeMonetaryUnitService = chargeMonetaryUnitService;
	}

	public ChargeMonetaryUnitVO getChargeMonetaryUnit() {
		return chargeMonetaryUnit;
	}

	public void setChargeMonetaryUnit(ChargeMonetaryUnitVO chargeMonetaryUnit) {
		this.chargeMonetaryUnit = chargeMonetaryUnit;
	}


}
