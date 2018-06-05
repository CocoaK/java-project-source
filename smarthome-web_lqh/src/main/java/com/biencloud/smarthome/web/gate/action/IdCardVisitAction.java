package com.biencloud.smarthome.web.gate.action;

import java.util.Date;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.service.IIdCardVisitService;
import com.biencloud.smarthome.web.gate.vo.IdCardVisitVO;

/**
 * 身份证刷卡管理动作类。
 * 
 * @author kouy
 * @since 1.0 2012-5-10
 */
@SuppressWarnings("serial")
public class IdCardVisitAction extends BaseAction<IdCardVisitVO> {

	private String visitId;

	private IdCardVisitVO idCardVisit;
	// 刷卡是否成功
	private boolean flushCardSuccess = false;

	private IIdCardVisitService idCardVisitService;

	/**
	 * 查看指定身份证刷卡记录的详细信息。
	 * 
	 * @return
	 * @throws Exception
	 *             当执行发生异常时
	 */
	public String viewDetail() throws Exception {

		setIdCardVisit(getIdCardVisitService().getIdCardVisitDetail(visitId));

		return SUCCESS;
	}

	/**
	 * 查询身份证刷卡记录列表。
	 * 
	 * @return
	 * @throws Exception
	 *             当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<IdCardVisitVO> page = getPage();
		if (page == null)
			page = new PagingVO<IdCardVisitVO>();

		if (idCardVisit == null)
			idCardVisit = new IdCardVisitVO();
		idCardVisit.setDistrictId(getDistrictId());
		setDateRange(idCardVisit.getBeginTime(), idCardVisit.getEndTime());

		page = getIdCardVisitService().queryIdCardVisitsForPaging(idCardVisit, page.getPageNum(), page.getPageSize());
		setPage(page);

		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 保存身份证信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-26 下午2:55:35
	 * @return
	 */
	public String saveCard() throws Exception{
		String districtId=this.getDistrictId();
		idCardVisit.setDistrictId(districtId);
		idCardVisit.setVisitTime(new Date());
		getIdCardVisitService().saveOrUpdateIdCardVisit(idCardVisit);
		flushCardSuccess = true;
		return "list";
	}
	/**
	 * 
	 * 方法的描述: 跳到刷卡页面
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-26 上午10:33:28
	 * @return
	 */
	public String toReadCard() {		
		return "to_read_card";
	}
	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public IdCardVisitVO getIdCardVisit() {
		return idCardVisit;
	}

	public void setIdCardVisit(IdCardVisitVO idCardVisit) {
		this.idCardVisit = idCardVisit;
	}

	public IIdCardVisitService getIdCardVisitService() {
		return idCardVisitService;
	}

	public void setIdCardVisitService(IIdCardVisitService idCardVisitService) {
		this.idCardVisitService = idCardVisitService;
	}

	public boolean isFlushCardSuccess() {
		return flushCardSuccess;
	}

	public void setFlushCardSuccess(boolean flushCardSuccess) {
		this.flushCardSuccess = flushCardSuccess;
	}

}
