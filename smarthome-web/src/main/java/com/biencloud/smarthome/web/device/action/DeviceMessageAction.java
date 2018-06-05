package com.biencloud.smarthome.web.device.action;

import java.util.Date;
import java.util.List;

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
 * 类名称：DeviceCallAction 
 * 类描述： 留言回放动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-17 上午9:51:13
 */
public class DeviceMessageAction extends BaseAction<CallRecordVO>{
		
	private static final long serialVersionUID = -1068348270735808081L;
	//通话留言记录接口
	private ICallRecordService callRecordService;
	//通话留言记录VO
	private CallRecordVO callRecordVO;
	//通话时间
	private Date callTime;
	//成功标志
	private String successFlag;
	//当前用户类型
	private String currUserType;
	//业主服务接口
	private IOwnerUserService ownerUserService; 
	//系统参数服务接口
	private ISysParamService sysParamService;
	//app下载地址
	private String appDownloadUrl;
	
	/**
	 * 
	 * 方法的描述: 留言列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-17 下午2:27:23
	 * @return
	 * @throws Exception
	 */
	public String messageList() throws Exception{
		if(callRecordVO == null){
			callRecordVO = new CallRecordVO();
		}else{
			setDateRange(callRecordVO.getBeginTime(), 
					callRecordVO.getEndTime());		
		}
		//callRecordVO.setCallType(Constants.CALL_TYPE_MESSAGE);		//留言
		DeviceVO deviceVO = new DeviceVO();
		if(callRecordVO.getDevice() != null)
			deviceVO = callRecordVO.getDevice();
		//如果是业主，将业主用户设置为条件，只查询业主的。
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType())){
			CellHouseholdInfoVo cellHouseholdInfoVo = new CellHouseholdInfoVo();
			OwnerUserVO ownerUserVO = new OwnerUserVO();
			ownerUserVO = ownerUserService.getOwnerUserDetail(super.getUserId());
			cellHouseholdInfoVo.setOwner(ownerUserVO);
			deviceVO.setCellHouseholdInfo(cellHouseholdInfoVo);
		//如果是物业管理员，将小区设置为条件，只查询该小区的。
		}
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())){
			HousingDistrictInfoVo housingDistrictInfoVo = new HousingDistrictInfoVo();
			housingDistrictInfoVo.setId(super.getDistrictId());
			deviceVO.setHousingDistrictInfo(housingDistrictInfoVo);
		}
		callRecordVO.setDevice(deviceVO);
		appDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		PagingVO<CallRecordVO> page = getPage();
		if(page == null)
			page = new PagingVO<CallRecordVO>();
		//查询并分页
		PagingVO<CallRecordVO> messageList = getCallRecordService()
				.queryCallRecordForPaging(getCallRecordVO(), page.getPageNum(), page.getPageSize());
		List<CallRecordVO> list = messageList.getResults();
		if(list!=null){	//将毫秒转换为秒
			for(CallRecordVO vo : list ){
				vo.setTalkTime(vo.getTalkTime()/1000);
			}
		}
		messageList.setResults(list);
		setPage(messageList);
		return "list";
	}
	
	/**
	 * 方法的描述: html页面播放
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:10:16
	 * @return
	 * @throws Exception
	 */
	public String htmlPlay() throws Exception{
		callRecordVO = getCallRecordService().getCallRecordById(callRecordVO.getId());
		return "play";
	}
	
	/**
	 * 方法的描述: html5页面播放
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:10:49
	 * @return
	 * @throws Exception
	 */
	public String html5Play() throws Exception{
		callRecordVO = getCallRecordService().getCallRecordById(callRecordVO.getId());
		return "play";
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

	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}

	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}
}
