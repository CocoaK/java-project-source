package com.biencloud.smarthome.web.customercomplaint.action;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 客服投诉Action
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
@SuppressWarnings("serial")
public class ComplaintAction extends BaseAction<ComplaintVo> {
	private String complaintId;
	private Date endComplaintDate;
	private Date endProcessingDate;
	private ComplaintVo complaint;

	private String suggestion;
	/**
	 * 是否提交投诉
	 */
	private boolean submitComplaint;
	/**
	 * 是否排除未提交的投诉
	 */
	private boolean excludeUncommitted;

	private boolean successFlag;
	
	private IOwnerUserService ownerUserService;
	
	private IComplaintService complaintService;
	private ISystemGroupService systemGroupService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	/**
	 * 物业查询物业投诉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryPropertyComplaintList() throws Exception {
		PagingVO<ComplaintVo> page = getPage();
		if(page == null)
			page = new PagingVO<ComplaintVo>();
		if(complaint==null) complaint=new ComplaintVo();
		complaint.setDistrictId(getDistrictId());
		PagingVO<ComplaintVo> pagingVO = complaintService.queryPropertyComplaintForPaging(
				complaint, endComplaintDate, endProcessingDate, page.getPageNum(),
				page.getPageSize(), excludeUncommitted);

		setPage(pagingVO);
		initProcessingStatusText(pagingVO.getResults());
		return SUCCESS;
	}
	
	/**
	 * 系统管理员查询物业投诉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryPropertysComplaintList() throws Exception {
		PagingVO<ComplaintVo> page = getPage();
		if(page == null)
			page = new PagingVO<ComplaintVo>();
		PagingVO<ComplaintVo> pagingVO = complaintService.queryPropertyComplaintForPaging(
				complaint, endComplaintDate, endProcessingDate, page.getPageNum(),
				page.getPageSize(), true);

		setPage(pagingVO);
		initProcessingStatusText(pagingVO.getResults());
		return SUCCESS;
	}

	/**
	 * 物业查询业主投诉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryOwnerComplaintList() throws Exception {
		PagingVO<ComplaintVo> page = getPage();
		if(page == null)
			page = new PagingVO<ComplaintVo>();
		if(complaint==null) complaint=new ComplaintVo();
		complaint.setDistrictId(getDistrictId());
		PagingVO<ComplaintVo> pagingVO = complaintService.queryOwnerComplaintForPaging(
				complaint, endComplaintDate, endProcessingDate, page.getPageNum(),
				page.getPageSize(), true);

		setPage(pagingVO);
		initProcessingStatusText(pagingVO.getResults());
		return SUCCESS;
	}
	
	/**
	 * 业主查询业主投诉列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryOwnersComplaintList() throws Exception {
		PagingVO<ComplaintVo> page = getPage();
		if(page == null)
			page = new PagingVO<ComplaintVo>();
		if(complaint==null) complaint=new ComplaintVo();
		complaint.setComplaintLoginName(getLoginName());
		PagingVO<ComplaintVo> pagingVO = complaintService.queryOwnerComplaintForPaging(
				complaint, endComplaintDate, endProcessingDate, page.getPageNum(),
				page.getPageSize(), false);

		setPage(pagingVO);
		initProcessingStatusText(pagingVO.getResults());
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述:查询投诉记录明细 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-27 下午2:24:19
	 * @return
	 * @throws Exception
	 */
	public String viewComplaintDetail() throws Exception {
		complaint = complaintService.getComplaint(complaintId);
		return SUCCESS;
	}

	/**
	 * 回复投诉, 提交处理意见
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replySuggestion() throws Exception {
		// 处理人为登陆用户
		complaintService.replySuggestion(complaintId, getLoginName(), suggestion);
		viewComplaintDetail();
		return SUCCESS;
	}

	/**
	 * 新增投诉(转到新增投诉页面)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addComplaintInput() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 新增物业投诉
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addPropertyComplaint() throws Exception {
		initNewComplaint();
		complaint.setDistrictId(getDistrictId());
		complaintService.addPropertyComplaint(complaint);
		successFlag = true;
		return SUCCESS;
	}

	/**
	 * 新增业主投诉
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addOwnerComplaint() throws Exception {
		initNewComplaint();
		complaint.setLocation(complaintService.getFullLocation(ownerUserService.getOwnerUserDetail(getUserId()).getHouseId()));
		OwnerUserVO vo=ownerUserService.getOwnerUserDetail(getUserId());
		complaint.setHouseId(vo.getHouseId());
		complaint.setDistrictId(getDistrictId());
		complaintService.addOwnerComplaint(complaint);
		return SUCCESS;
	}

	/**
	 * 初始化投诉信息
	 */
	private void initNewComplaint() {
		changeProcessingStatus();
		complaint.setComplaintLoginName(getLoginName());
		HousingDistrictInfoVo vo=housingDistrictInfoService.getHousingDistrictInfo(getDistrictId());
		complaint.setLocation(systemGroupService.getCompletePosition(vo.getGroupId()));
	}

	/**
	 * 改变投诉的处理状态
	 */
	private void changeProcessingStatus() {
		if (submitComplaint) { // 提交投诉, 处理状态为待处理
			complaint.setProcessingStatus(IComplaintService.PROCESSING_STATUS_PENDING);
		} else { // 处理状态为未提交
			complaint.setProcessingStatus(IComplaintService.PROCESSING_STATUS_UNCOMMITTED);
		}
	}

	/**
	 * 
	 * 方法的描述:更新投诉记录 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-27 下午2:24:38
	 * @return
	 * @throws Exception
	 */
	public String updateComplaint() throws Exception {
		complaintService.updateComplaint(complaint.getId(), complaint.getTitle(),
				complaint.getContent(), submitComplaint);
		successFlag = true;
		complaint=null;
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())){
			return queryPropertyComplaintList();
		}
		else{
			return queryOwnersComplaintList();
		}
	}

	/**
	 * 
	 * 方法的描述:删除投诉记录 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-27 下午2:24:47
	 * @return
	 * @throws Exception
	 */
	public String removeComplaint() throws Exception {
		complaintService.remove(complaintId);
		successFlag = true;
		return SUCCESS;
	}

	/**
	 * 初始化投诉处理状态的国际化字符
	 * 
	 * @param complaints
	 */
	private void initProcessingStatusText(List<ComplaintVo> complaints) {
		for (int i = 0, length = complaints.size(); i < length; i++) {
			ComplaintVo element = complaints.get(i);
			element.setProcessingStatusText(getProcessingStatusText(element.getProcessingStatus()));
		}
	}

	/**
	 * 获取投诉处理状态的国际化字符
	 * 
	 * @param processingStatus
	 * @return
	 */
	private String getProcessingStatusText(String processingStatus) {
		String i18nText = "";

		if (IComplaintService.PROCESSING_STATUS_PENDING.equals(processingStatus)) {
			i18nText = getText("customercomplaint.processingStatus.pending");
		} else if (IComplaintService.PROCESSING_STATUS_PROCESSED.equals(processingStatus)) {
			i18nText = getText("customercomplaint.processingStatus.processed");
		} else if (IComplaintService.PROCESSING_STATUS_UNCOMMITTED.equals(processingStatus)) {
			i18nText = getText("customercomplaint.processingStatus.uncommitted");
		}

		return i18nText;
	}

	public IComplaintService getComplaintService() {
		return complaintService;
	}
	public void setComplaintService(IComplaintService complaintService) {
		this.complaintService = complaintService;
	}
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public ComplaintVo getComplaint() {
		return complaint;
	}
	public void setComplaint(ComplaintVo complaint) {
		this.complaint = complaint;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Date getEndComplaintDate() {
		return endComplaintDate;
	}
	public void setEndComplaintDate(Date endComplaintDate) {
		this.endComplaintDate = endComplaintDate;
		if (endComplaintDate != null) {
			// 页面传过来的只包含日期, 时间默认为00:00:00
			// 因此不特殊处理的话默认条件为查询2012-5-30 00:00:00 ~ 2012-5-31 00:00:00
			// 这样的话2012-5-31 18:00:00的记录就不会包含在查询结果中
			// 因此这里默认将时间推后(24小时 - 1毫秒), 让时间变为23:59:59
			// 这样查询条件才是想要的2012-5-30 00:00:00 ~ 2012-5-31 23:59:59
			this.endComplaintDate.setTime(endComplaintDate.getTime() + (86400000 - 1));
		}
	}
	public Date getEndProcessingDate() {
		return endProcessingDate;
	}
	public void setEndProcessingDate(Date endProcessingDate) {
		this.endProcessingDate = endProcessingDate;
		if (endProcessingDate != null) {
			this.endProcessingDate.setTime(endProcessingDate.getTime() + (86400000 - 1));
		}
	}
	public boolean getSubmitComplaint() {
		return submitComplaint;
	}
	public void setSubmitComplaint(boolean submitComplaint) {
		this.submitComplaint = submitComplaint;
	}
	public boolean getExcludeUncommitted() {
		return excludeUncommitted;
	}
	public void setExcludeUncommitted(boolean excludeUncommitted) {
		this.excludeUncommitted = excludeUncommitted;
	}
	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}
	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}
	
}
