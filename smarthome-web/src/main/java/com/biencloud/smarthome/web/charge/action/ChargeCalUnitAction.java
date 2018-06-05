package com.biencloud.smarthome.web.charge.action;

import java.io.PrintWriter;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeCalUnitService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 类名称：ChargeCalUnitAction 类描述： 收费计算单位管理类
 * 
 * @author: dehuaye
 * @version: 0.1
 * @date: 2012-5-7 上午10:55:57
 */
public class ChargeCalUnitAction extends BaseAction<ChargeCalUnitVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeCalUnitService chargeCalUnitService;

	private ChargeCalUnitVO chargeCalUnit;

	/**
	 * 
	 * 方法的描述: 收费计算单位列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:29
	 * @return
	 */
	public String queryList() throws Exception {
			PagingVO<ChargeCalUnitVO> page = getPage();
			if (page == null)
				page = new PagingVO<ChargeCalUnitVO>();
			PagingVO<ChargeCalUnitVO> pagingVO = chargeCalUnitService.queryChargeCalUnitVOForPaging(chargeCalUnit, page.getPageNum(),page.getPageSize());
			setPage(pagingVO);
			 return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 保存收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:20
	 * @return
	 */
	public String save() throws Exception {
		boolean result = chargeCalUnitService.saveChargeCalUnitVO(chargeCalUnit);
		this.getRequest().setAttribute("editResult", result);
		chargeCalUnit = null;
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
		ChargeCalUnitVO result = chargeCalUnitService.queryChargeCalUnitByParams(chargeCalUnit);
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
	 * 方法的描述: 更新收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:13
	 * @return
	 */
	public String update() throws Exception {
		boolean result = chargeCalUnitService.updateChargeCalUnitVO(chargeCalUnit);
		this.getRequest().setAttribute("editResult", result);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:00:57
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeCalUnit = chargeCalUnitService.getChargeCalUnitVO(id);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 删除收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:45
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = chargeCalUnitService.delChargeCalUnitVO(id);
		this.getRequest().setAttribute("delResult", result);
		return "list";
	}

	public IChargeCalUnitService getChargeCalUnitService() {
		return chargeCalUnitService;
	}

	public void setChargeCalUnitService(IChargeCalUnitService chargeCalUnitService) {
		this.chargeCalUnitService = chargeCalUnitService;
	}

	public ChargeCalUnitVO getChargeCalUnit() {
		return chargeCalUnit;
	}

	public void setChargeCalUnit(ChargeCalUnitVO chargeCalUnit) {
		this.chargeCalUnit = chargeCalUnit;
	}


}
