package com.biencloud.smarthome.web.softwareupgrade.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseUploadAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.biencloud.smarthome.web.devicetype.service.IDeviceTypeService;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.softwareupgrade.service.ISoftwareUpgradeService;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeTargetVO;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeVO;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;

@SuppressWarnings("serial")
public class SoftwareUpgradeAction extends BaseUploadAction<SoftwareUpgradeVO> {
	
	private String softwareId;	
	
	private String groupIds;
	
	private String softwareFileName;
	private File softwareFile;
	private String softwareFileErrorMsg;
	
	private Date publishedTime;
	//定时发布标志
	private boolean timingFlag;
	//审核标志
	private boolean approveFlag;
	
	private Date upgradedTime;
	
	private boolean promptFlag;	
	private boolean errorFlag;
	
	private SoftwareUpgradeVO softwareUpgrade;
	private SoftwareUpgradeTargetVO suTarget;
	
	private List<DeviceTypeVO> deviceTypes;
	
	private ISoftwareUpgradeService softwareUpgradeService;
	private IDeviceTypeService deviceTypeService;
	private ISystemGroupService systemGroupService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	
	
	public String getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}
	
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	
	public String getSoftwareFileName() {
		return softwareFileName;
	}
	public void setSoftwareFileName(String softwareFileName) {
		this.softwareFileName = softwareFileName;
	}
	
	public File getSoftwareFile() {
		return softwareFile;
	}	
	public void setSoftwareFile(File softwareFile) {
		this.softwareFile = softwareFile;
	}
	
	public String getSoftwareFileErrorMsg() {
		return softwareFileErrorMsg;
	}
	public void setSoftwareFileErrorMsg(String softwareFileErrorMsg) {
		this.softwareFileErrorMsg = softwareFileErrorMsg;
	}
	
	public Date getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}
	
	public boolean isTimingFlag() {
		return timingFlag;
	}
	public void setTimingFlag(boolean timingFlag) {
		this.timingFlag = timingFlag;
	}
	
	public boolean isApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(boolean approveFlag) {
		this.approveFlag = approveFlag;
	}
	
	public Date getUpgradedTime() {
		return upgradedTime;
	}
	public void setUpgradedTime(Date upgradedTime) {
		this.upgradedTime = upgradedTime;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}
	
	public boolean isErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
	
	public SoftwareUpgradeVO getSoftwareUpgrade() {
		return softwareUpgrade;
	}
	public void setSoftwareUpgrade(SoftwareUpgradeVO softwareUpgrade) {
		this.softwareUpgrade = softwareUpgrade;
	}

	public SoftwareUpgradeTargetVO getSuTarget() {
		return suTarget;
	}
	public void setSuTarget(SoftwareUpgradeTargetVO suTarget) {
		this.suTarget = suTarget;
	}
	
	public List<DeviceTypeVO> getDeviceTypes() {
		return deviceTypes;
	}
	public void setDeviceTypes(List<DeviceTypeVO> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
	
	public ISoftwareUpgradeService getSoftwareUpgradeService() {
		return softwareUpgradeService;
	}
	public void setSoftwareUpgradeService(ISoftwareUpgradeService softwareUpgradeService) {
		this.softwareUpgradeService = softwareUpgradeService;
	}
	
	public IDeviceTypeService getDeviceTypeService() {
		return deviceTypeService;
	}
	public void setDeviceTypeService(IDeviceTypeService deviceTypeService) {
		this.deviceTypeService = deviceTypeService;
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
	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	
	
	/**
	 * 查看指定软件升级的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setSoftwareUpgrade(getSoftwareDetail());
		return SUCCESS;
	}
	
	/**
	 * 查询软件升级列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<SoftwareUpgradeVO> pagingVO = getPage();
		if(pagingVO == null)
			pagingVO = new PagingVO<SoftwareUpgradeVO>();
		
		String softwareName = null;
		String status = null;
		if(softwareUpgrade != null){
			softwareName = softwareUpgrade.getSoftwareName();
			status = softwareUpgrade.getStatus();
		}
		
		String dt = null;
		if(suTarget != null && suTarget.getDeviceType() != null)
			dt = suTarget.getDeviceType().getDeviceType();
		
		pagingVO = getSoftwareUpgradeService().querySoftwaresForPaging(softwareName, 
				dt, status, pagingVO.getPageNum(), pagingVO.getPageSize());
		
		logger.info("********************返回软件升级分页信息：{}", pagingVO);
		
		setPage(pagingVO);
		
		setDeviceTypes(queryAllDeviceTypes());
		
		setRequestAttribute("downloadContextPath", getSysParamService().getParamValue(
				Constants.EXTERNAL_FILE_SERVER_URL));
		
		return SUCCESS;
	}
	
	/**
	 * 新增软件升级信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		setDeviceTypes(queryAllDeviceTypes());
		setSystemGroups(null, true);
		setFileFormat();
		return SUCCESS;
	}
	
	
	/**
	 * 保存新增软件升级信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {		
		String fileName = FileUploadUtil.renameFileName(extractFileName(softwareFileName));
		UploadResult result = uploadFile(fileName, softwareFile);
		if(!result.isSuccess()){
			setErrorFlag(true);
			addFieldError("softwareFile", result.getErrorMsg());
			return addInput();
		}
			
		softwareUpgrade.setApplyedUser(getLoginName());
		softwareUpgrade.setApplyedTime(new Date());
		softwareUpgrade.setSize(getFileSize(softwareFile));
		softwareUpgrade.setSuTargets(buildSuTargets());
		softwareUpgrade.setSavePath(result.getDownloadRelativePath());
		getSoftwareUpgradeService().addSoftware(softwareUpgrade);
		logger.info("********************新增软件升级信息：{}", softwareUpgrade);
		softwareUpgrade = null;
		suTarget = null;
		setPromptFlag(true);
		return addInput();
	}
	
	/**
	 * 升级软件信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String upgradeInput() throws Exception {
		String currVersionName = null;
		if(softwareUpgrade != null)
			currVersionName = softwareUpgrade.getVersionName();
		
		SoftwareUpgradeVO su = getSoftwareDetail();
		
		if(errorFlag)
			su.setVersionName(currVersionName);
		else
			su.setVersionName(null);
		
		setSoftwareUpgrade(su);
		
		setFileFormat();
		
		return SUCCESS;
	}
	
	/**
	 * 保存升级软件信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String upgrade() throws Exception {
		softwareId = softwareUpgrade.getSoftwareId();
		
		UploadResult result = uploadFile(extractFileName(softwareFileName), softwareFile);
		if(!result.isSuccess()){
			setErrorFlag(true);
			addFieldError("softwareFile", result.getErrorMsg());
			return upgradeInput();
		}
		
		softwareUpgrade.setApplyedUser(getLoginName());
		softwareUpgrade.setApplyedTime(new Date());
		softwareUpgrade.setSize(getFileSize(softwareFile));
		softwareUpgrade.setSavePath(result.getDownloadRelativePath());
		getSoftwareUpgradeService().upgradeSoftware(softwareUpgrade);
		logger.info("********************升级软件信息：{}", softwareUpgrade);		
		softwareUpgrade = null;
		setPromptFlag(true);
		return upgradeInput();
	}
	
	/**
	 * 删除软件升级信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		getSoftwareUpgradeService().removeSoftware(softwareId);
		setPromptFlag(true);
		return queryList();
	}
		
	/**
	 * 审核软件升级信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String approve() throws Exception {
		getSoftwareUpgradeService().approveSoftware(softwareId, approveFlag, getLoginName());
		logger.info("********************审核编号为{}的软件升级信息", softwareId);
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 发布软件升级信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String publish() throws Exception {
		if(timingFlag){
			getSoftwareUpgradeService().publishTimingSoftware(
					softwareId, publishedTime, getLoginName());
		}else{
			getSoftwareUpgradeService().publishSoftware(softwareId, getLoginName());
		}
		logger.info("********************发布编号为{}的软件升级信息", softwareId);
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 通知统一升级软件。
	 * @return
	 * @throws Exception
	 */
	public String notifyUpgrade() throws Exception {
		getSoftwareUpgradeService().notifyUpgrade(
				softwareId, upgradedTime, getLoginName());
		logger.info("********************通知统一升级编号为{}的软件升级信息", softwareId);
		setPromptFlag(true);
		return queryList();
	}
	
	
	private List<SoftwareUpgradeTargetVO> buildSuTargets(){
		List<SoftwareUpgradeTargetVO> suTargets = new ArrayList<SoftwareUpgradeTargetVO>();
		if(StringUtils.isNotBlank(groupIds) 
				&& deviceTypes != null && !deviceTypes.isEmpty()){
			List<String> ids = new ArrayList<String>();
			for (String id : StringUtils.split(groupIds, Constants.SEPARATOR_GROUP_ID))
				ids.add(id);
			List<String> districtIds = housingDistrictInfoService.queryDistrictIds(ids);
			if(districtIds != null){
				for (String id : districtIds)
					addSuTargets(suTargets, id, deviceTypes);
			}
		}
		return suTargets;
	}
	
	//添加指定小区对应的广告投换目标
	private void addSuTargets(List<SoftwareUpgradeTargetVO> suTargets , 
			String districtId, List<DeviceTypeVO> dts){
		if(dts == null || dts.isEmpty())
			return;
		
		for (DeviceTypeVO dt : dts) {
			SoftwareUpgradeTargetVO sut = new SoftwareUpgradeTargetVO();
			sut.setDeviceType(dt);
			HousingDistrictInfoVo hdi = new HousingDistrictInfoVo();
			hdi.setId(districtId);
			sut.setHousingDistrictInfo(hdi);
			suTargets.add(sut);
		}		
	}
	
	private SoftwareUpgradeVO getSoftwareDetail() {
		SoftwareUpgradeVO su = getSoftwareUpgradeService().getSoftwareDetail(softwareId);
		if(su != null){
			List<SoftwareUpgradeTargetVO> suTargets = su.getSuTargets();
			if(suTargets != null){
				List<DeviceTypeVO> dts = new ArrayList<DeviceTypeVO>();
				Set<Long> districtIds = new HashSet<Long>();
				for (SoftwareUpgradeTargetVO sut : suTargets) {
					DeviceTypeVO dt = sut.getDeviceType();
					if(dt != null && !dts.contains(dt)){
						setTypeNameToI18N(dt);
						dts.add(dt);
					}
					HousingDistrictInfoVo hdi = sut.getHousingDistrictInfo();
					if(hdi != null)
						districtIds.add(Long.valueOf(hdi.getId()));
				}			
				setDeviceTypes(dts);
				setSystemGroups(districtIds, false);
			}
		}
		return su;
	}
	
	private List<DeviceTypeVO> queryAllDeviceTypes(){
		List<DeviceTypeVO> dts = getDeviceTypeService().queryAllDeviceTypes();
		for (DeviceTypeVO dt : dts) {
			setTypeNameToI18N(dt);
		}
		return dts;
	}
	
	private void setTypeNameToI18N(DeviceTypeVO dt){
		if(dt == null || StringUtils.isBlank(dt.getDeviceName()))
			return;
		dt.setDeviceName(getText(dt.getDeviceName()));
	}
	
	private void setSystemGroups(Set<Long> groupIds, boolean isEditable){
		Object[] objects = getSystemGroupService().querySystemGroupForCheck(groupIds, true, !isEditable);
		setRequestAttribute("treeList", objects[0]);
		setRequestAttribute("comStr", objects[1]);
		if(!isEditable)
			getRequest().setAttribute("isCheckBox","0");
	}
	
	private void setFileFormat() throws Exception {
		setRequestAttribute("fileExt", getSysParamService().getParamValue(
				Constants.PARAM_CODE_APP_SOFTWARE_FORMAT));
	}
}
