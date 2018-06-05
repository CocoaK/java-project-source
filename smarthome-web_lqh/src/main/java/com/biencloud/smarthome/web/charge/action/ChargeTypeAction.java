package com.biencloud.smarthome.web.charge.action;

import java.io.PrintWriter;
import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.web.charge.service.IChargeCalUnitService;
import com.biencloud.smarthome.web.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.web.charge.service.IChargeTypeService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalModeVO;
import com.biencloud.smarthome.web.charge.vo.ChargeCalUnitVO;
import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeTypeAction 
 * 类描述： 收费货币管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:05:05
 */
public class ChargeTypeAction extends BaseAction<ChargeTypeVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeTypeService chargeTypeService;
	
	private IChargeCalModeService chargeCalModeService;
	
	private IChargeMonetaryUnitService chargeMonetaryUnitService;

	private ChargeTypeVO chargeType;
	
	private List<ChargeCalModeVO> chargeCalModeList;
	
	private List<ChargeCalUnitVO> chargeCalUnitList;
	
	private List<ChargeMonetaryUnitVO> chargeMonetaryUnitList;
	
	private IChargeCalUnitService chargeCalUnitService;
	
	/**
	 * 
	 * 方法的描述: 收费货币列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:05:14
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeTypeVO> pagingVO = getList();
		setPage(pagingVO);
		return "list";
	}
	
	private PagingVO<ChargeTypeVO> getList(){
		PagingVO<ChargeTypeVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeTypeVO>();
		if(chargeType==null) chargeType=new ChargeTypeVO();
		HousingDistrictInfoVo hdVo=new HousingDistrictInfoVo();
		hdVo.setId(getDistrictId());
		chargeType.setHousingDistrictInfo(hdVo);
		PagingVO<ChargeTypeVO> pagingVO = chargeTypeService.queryChargeTypeVOForPaging(chargeType, page.getPageNum(),page.getPageSize());
		return pagingVO;
	}
	
	/**
	 * 
	 * 方法的描述:  保存收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:05:33
	 * @return
	 */
	public String save() throws Exception {
		PaUserVO puVo = new PaUserVO();
		puVo.setUserId(getUserId());
		chargeType.setPaUser(puVo);
		HousingDistrictInfoVo hdVo = new HousingDistrictInfoVo();
		hdVo.setId(getDistrictId());
		chargeType.setHousingDistrictInfo(hdVo);
		boolean result = chargeTypeService.saveChargeTypeVO(chargeType);
		this.getRequest().setAttribute("editResult", result);
		chargeType = null;
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
		setRequestAttribute();
		PagingVO<ChargeTypeVO> pagingVO = getList();
		if(pagingVO!=null&&pagingVO.getResults()!=null&&!pagingVO.getResults().isEmpty()){
			long total=pagingVO.getTotalCount();
			ChargeTypeVO vo=pagingVO.getResults().get(0);
			setRequestAttribute("ChargeMonetaryUnit",vo.getChargeMonetaryUnit());
			setRequestAttribute("total",total);
		}
		
		return "editPage";
	}
	
	public String getName() throws Exception {
		String validateResult = "true";
		ChargeTypeVO result = chargeTypeService.queryChargeTypeByParams(chargeType, getDistrictId());
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
	 * 更新收费货币
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:05:43
	 * @return
	 */
	public String update() throws Exception {
		String chargeMonetaryUnitId = getRequest().getParameter("chargeMonetaryUnitId");
		ChargeMonetaryUnitVO vo = new ChargeMonetaryUnitVO();
		vo.setId(new Long(chargeMonetaryUnitId));
		chargeType.setChargeMonetaryUnit(vo);
		boolean result = chargeTypeService.updateChargeTypeVO(chargeType);
		this.getRequest().setAttribute("editResult", result);
		setRequestAttribute();
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:05:51
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeType = chargeTypeService.getChargeTypeVO(id);
		setRequestAttribute();
		return "editPage";
	}
	
	private void setRequestAttribute(){
		ChargeCalModeVO vo=new ChargeCalModeVO();
		vo.setDistrictId(new Long(getDistrictId()));
		chargeCalModeList=chargeCalModeService.queryChargeCalModeForList(vo);
		chargeMonetaryUnitList=chargeMonetaryUnitService.queryChargeMonetaryUnitForList(null);
		chargeCalUnitList=chargeCalUnitService.queryChargeCalUnitForList(null);
	}
	
	/**
	 * 
	 * 方法的描述:  删除收费货币
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:06:03
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = chargeTypeService.delChargeTypeVO(id);
		this.getRequest().setAttribute("editResult", result);
		return "list";
	}

	public IChargeTypeService getChargeTypeService() {
		return chargeTypeService;
	}

	public void setChargeTypeService(IChargeTypeService chargeTypeService) {
		this.chargeTypeService = chargeTypeService;
	}

	public ChargeTypeVO getChargeType() {
		return chargeType;
	}

	public void setChargeType(ChargeTypeVO chargeType) {
		this.chargeType = chargeType;
	}

	public IChargeCalModeService getChargeCalModeService() {
		return chargeCalModeService;
	}

	public void setChargeCalModeService(IChargeCalModeService chargeCalModeService) {
		this.chargeCalModeService = chargeCalModeService;
	}

	public IChargeMonetaryUnitService getChargeMonetaryUnitService() {
		return chargeMonetaryUnitService;
	}

	public void setChargeMonetaryUnitService(IChargeMonetaryUnitService chargeMonetaryUnitService) {
		this.chargeMonetaryUnitService = chargeMonetaryUnitService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ChargeCalModeVO> getChargeCalModeList() {
		return chargeCalModeList;
	}

	public void setChargeCalModeList(List<ChargeCalModeVO> chargeCalModeList) {
		this.chargeCalModeList = chargeCalModeList;
	}

	public List<ChargeMonetaryUnitVO> getChargeMonetaryUnitList() {
		return chargeMonetaryUnitList;
	}

	public void setChargeMonetaryUnitList(List<ChargeMonetaryUnitVO> chargeMonetaryUnitList) {
		this.chargeMonetaryUnitList = chargeMonetaryUnitList;
	}

	public IChargeCalUnitService getChargeCalUnitService() {
		return chargeCalUnitService;
	}

	public void setChargeCalUnitService(IChargeCalUnitService chargeCalUnitService) {
		this.chargeCalUnitService = chargeCalUnitService;
	}

	public List<ChargeCalUnitVO> getChargeCalUnitList() {
		return chargeCalUnitList;
	}

	public void setChargeCalUnitList(List<ChargeCalUnitVO> chargeCalUnitList) {
		this.chargeCalUnitList = chargeCalUnitList;
	}
	
	


}
