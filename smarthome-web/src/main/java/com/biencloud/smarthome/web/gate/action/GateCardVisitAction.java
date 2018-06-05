package com.biencloud.smarthome.web.gate.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.service.IGateCardVisitService;
import com.biencloud.smarthome.web.gate.vo.GateCardVisitVO;

/**
 * 门卡刷卡管理动作类。
 * @author kouy
 * @since 1.0 2012-5-10
 */
@SuppressWarnings("serial")
public class GateCardVisitAction extends BaseAction<GateCardVisitVO> {

	private String visitId;
	
	private GateCardVisitVO gateCardVisit;
	
	private IGateCardVisitService gateCardVisitService;
	
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public GateCardVisitVO getGateCardVisit() {
		return gateCardVisit;
	}
	public void setGateCardVisit(GateCardVisitVO gateCardVisit) {
		this.gateCardVisit = gateCardVisit;
	}
	
	public IGateCardVisitService getGateCardVisitService() {
		return gateCardVisitService;
	}
	public void setGateCardVisitService(IGateCardVisitService gateCardVisitService) {
		this.gateCardVisitService = gateCardVisitService;
	}
	
	
	/**
	 * 查看指定门卡刷卡记录的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setGateCardVisit(getGateCardVisitService().getGateCardVisitDetail(visitId));
		return SUCCESS;
	}
	
	/**
	 * 查询门卡刷卡记录列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<GateCardVisitVO> page = getPage();
		if(page == null)
			page = new PagingVO<GateCardVisitVO>();
		
		if(gateCardVisit == null)
			gateCardVisit = new GateCardVisitVO();
		gateCardVisit.setDistrictId(getDistrictId());
		
		page = getGateCardVisitService().queryGateCardVisitsForPaging(
				gateCardVisit, page.getPageNum(), page.getPageSize());		
		setPage(page);
		
		return SUCCESS;
	}
}
