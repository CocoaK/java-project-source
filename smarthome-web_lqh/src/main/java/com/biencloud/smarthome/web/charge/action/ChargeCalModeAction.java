package com.biencloud.smarthome.web.charge.action;

import java.io.PrintWriter;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalModeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 类名称：ChargeCalModeAction 类描述： 收费计算方式管理类
 * 
 * @author: dehuaye
 * @version: 0.1
 * @date: 2012-5-7 上午10:55:57
 */
public class ChargeCalModeAction extends BaseAction<ChargeCalModeVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeCalModeService chargeCalModeService;

	private ChargeCalModeVO chargeCalMode;

	/**
	 * 
	 * 方法的描述: 收费计算方式列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:29
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeCalModeVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeCalModeVO>();
		if (chargeCalMode == null)
			chargeCalMode = new ChargeCalModeVO();
		chargeCalMode.setDistrictId(new Long(getDistrictId()));
		PagingVO<ChargeCalModeVO> pagingVO = chargeCalModeService.queryChargeCalModeVOForPaging(chargeCalMode, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 保存收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:20
	 * @return
	 */
	public String save() throws Exception {
		chargeCalMode.setDistrictId(new Long(getDistrictId()));
		boolean result = chargeCalModeService.saveChargeCalModeVO(chargeCalMode);
		this.getRequest().setAttribute("editResult", result);
		chargeCalMode = null;
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
		chargeCalMode.setDistrictId(new Long(getDistrictId()));
		ChargeCalModeVO result = chargeCalModeService.queryChargeCalModeByParams(chargeCalMode);
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
	 * 方法的描述: 更新收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:13
	 * @return
	 */
	public String update() throws Exception {
		boolean result = chargeCalModeService.updateChargeCalModeVO(chargeCalMode);
		this.getRequest().setAttribute("editResult", result);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:00:57
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeCalMode = chargeCalModeService.getChargeCalModeVO(id);
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 删除收费计算方式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:45
	 * @return
	 */
	public String delById() throws Exception {
		try {
			String id=getRequest().getParameter("requestId");
		    boolean result=chargeCalModeService.delChargeCalModeVO(id);
		    this.getRequest().setAttribute("delResult", result);
		} catch (Exception e) {
			this.getRequest().setAttribute("delResult", false);
		}
		return "list";
	}

	public IChargeCalModeService getChargeCalModeService() {
		return chargeCalModeService;
	}

	public void setChargeCalModeService(IChargeCalModeService chargeCalModeService) {
		this.chargeCalModeService = chargeCalModeService;
	}

	public ChargeCalModeVO getChargeCalMode() {
		return chargeCalMode;
	}

	public void setChargeCalMode(ChargeCalModeVO chargeCalMode) {
		this.chargeCalMode = chargeCalMode;
	}


}
