package com.biencloud.smarthome.web.mobile.action.leaveword;

import java.util.Date;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.ICallRecordService;
import com.biencloud.smarthome.web.device.vo.CallRecordVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 
 * 类名称：DeviceCallAction 类描述： 留言回放动作类
 * 
 * @author: ykou
 * @version: 0.1
 * @date: 2012-5-17 上午9:51:13
 */
public class LeaveWordsAction extends BaseAction<CallRecordVO> {

	private static final long serialVersionUID = -1068348270735808081L;
	private ICallRecordService callRecordService;
	private CallRecordVO callRecordVO;
	private Date callTime;
	private String successFlag;
	private String currUserType;
	private IOwnerUserService ownerUserService;
	private ISysParamService sysParamService;
	private String webDownloadUrl;
	private String appDownloadUrl;
	private String imageUrl;
	private String playUrl;

	/**
	 * 
	 * 方法的描述: 留言列表
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-17 下午2:27:23
	 * @return
	 * @throws Exception
	 */
	public String messageList() throws Exception {
		if (callRecordVO == null) {
			callRecordVO = new CallRecordVO();
		} else {
			setDateRange(callRecordVO.getBeginTime(), callRecordVO.getEndTime());
		}
		// callRecordVO.setCallType(Constants.CALL_TYPE_MESSAGE); //留言
		DeviceVO deviceVO = new DeviceVO();
		if (callRecordVO.getDevice() != null)
			deviceVO = callRecordVO.getDevice();
		// 如果是业主，将业主用户设置为条件，只查询业主的。
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType())) {
			CellHouseholdInfoVo cellHouseholdInfoVo = new CellHouseholdInfoVo();
			OwnerUserVO ownerUserVO = new OwnerUserVO();
			ownerUserVO = ownerUserService.getOwnerUserDetail(super.getUserId());
			cellHouseholdInfoVo.setOwner(ownerUserVO);
			deviceVO.setCellHouseholdInfo(cellHouseholdInfoVo);
			// 如果是物业管理员，将小区设置为条件，只查询该小区的。
		}
		if (Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())) {
			HousingDistrictInfoVo housingDistrictInfoVo = new HousingDistrictInfoVo();
			housingDistrictInfoVo.setId(super.getDistrictId());
			deviceVO.setHousingDistrictInfo(housingDistrictInfoVo);
		}
		callRecordVO.setDevice(deviceVO);
		webDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		PagingVO<CallRecordVO> page = getPage();
		if (page == null)
			page = new PagingVO<CallRecordVO>();
		// 查询并分页
		PagingVO<CallRecordVO> messageList = getCallRecordService().queryCallRecordForPaging(getCallRecordVO(), page.getPageNum(),
				page.getPageSize());
		setPage(messageList);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 查看详细
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-8 下午4:29:58
	 * @return
	 */
	public String viewDetail() {
		if (callRecordVO != null && callRecordVO.getId() != null) {
			callRecordVO = getCallRecordService().getCallRecordById(callRecordVO.getId());
			if(callRecordVO!=null)
			{
				callRecordVO.setTalkTime(callRecordVO.getTalkTime()/1000);
			}
			appDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		}
		return "detail";
	}

	/**
	 * 
	 * 方法的描述: 预览图片
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-8 下午5:40:42
	 * @return
	 */
	public String viewImage() {
		
		return "image";
	}

	

	public ICallRecordService getCallRecordService() {
		return callRecordService;
	}

	public void setCallRecordService(ICallRecordService callRecordService) {
		this.callRecordService = callRecordService;
	}

	public CallRecordVO getCallRecordVO() {
		return callRecordVO;
	}

	public void setCallRecordVO(CallRecordVO callRecordVO) {
		this.callRecordVO = callRecordVO;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getCurrUserType() {
		return currUserType;
	}

	public void setCurrUserType(String currUserType) {
		this.currUserType = currUserType;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public String getWebDownloadUrl() {
		return webDownloadUrl;
	}

	public void setWebDownloadUrl(String webDownloadUrl) {
		this.webDownloadUrl = webDownloadUrl;
	}

	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}

	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

}
