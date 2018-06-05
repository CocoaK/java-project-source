package com.biencloud.smarthome.web.ad.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.ad.service.IAdLocationService;
import com.biencloud.smarthome.web.ad.service.IAdService;
import com.biencloud.smarthome.web.ad.service.IAdSysService;
import com.biencloud.smarthome.web.ad.service.IAdTypeService;
import com.biencloud.smarthome.web.ad.vo.AdLocationVO;
import com.biencloud.smarthome.web.ad.vo.AdSysVO;
import com.biencloud.smarthome.web.ad.vo.AdTargetVO;
import com.biencloud.smarthome.web.ad.vo.AdTypeVO;
import com.biencloud.smarthome.web.ad.vo.AdvertisementVO;
import com.biencloud.smarthome.web.base.action.BaseUploadAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.service.IDeviceTypeService;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;

/**
 * 广告投放管理动作类。
 * @author kouy
 * @since 1.0 2012-5-30
 */
@SuppressWarnings("serial")
public class AdAction extends BaseUploadAction<AdvertisementVO> {

	private static final String AD_DETAIL_PIC_FILE = "adDetailPicFile";
	private static final String AD_PIC_FILE = "adPicFile";
	
	//视图：广告投放到App系统
	private static final String APP = "app";
	//视图：广告投放到Web系统
	private static final String WEB = "web";
	
	private String adId;
	private Date publishedTime;
	//定时发布标志
	private boolean timingFlag;
	private String groupIds;
	
	private AdvertisementVO ad;
	
	private String adPicName;
	private File adPicFile;
	private String adPicErrorMsg;
	private String adDetailPicName;
	private File adDetailPicFile;
	private String adDetailPicErrorMsg;
	
	private List<AdTypeVO> adTypes;
	private List<AdSysVO> adSystems;
	private List<AdLocationVO> adLocations;
	private List<DeviceTypeVO> deviceTypes;
	
	private boolean promptFlag;
	private boolean errorFlag;
	
	private IAdService adService;
	private IAdTypeService adTypeService;
	private IAdSysService adSysService;
	private IAdLocationService adLocationService;
	private IDeviceService deviceService;
	private IDeviceTypeService deviceTypeService;
	private ISystemGroupService systemGroupService;

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
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

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public AdvertisementVO getAd() {
		return ad;
	}

	public void setAd(AdvertisementVO ad) {
		this.ad = ad;
	}
	
	public String getAdPicName() {
		return adPicName;
	}

	public void setAdPicName(String adPicName) {
		this.adPicName = adPicName;
	}

	public File getAdPicFile() {
		return adPicFile;
	}

	public void setAdPicFile(File adPicFile) {
		this.adPicFile = adPicFile;
	}
	
	public String getAdPicErrorMsg() {
		return adPicErrorMsg;
	}

	public void setAdPicErrorMsg(String adPicErrorMsg) {
		this.adPicErrorMsg = adPicErrorMsg;
	}

	public String getAdDetailPicName() {
		return adDetailPicName;
	}

	public void setAdDetailPicName(String adDetailPicName) {
		this.adDetailPicName = adDetailPicName;
	}

	public File getAdDetailPicFile() {
		return adDetailPicFile;
	}

	public void setAdDetailPicFile(File adDetailPicFile) {
		this.adDetailPicFile = adDetailPicFile;
	}

	public String getAdDetailPicErrorMsg() {
		return adDetailPicErrorMsg;
	}

	public void setAdDetailPicErrorMsg(String adDetailPicErrorMsg) {
		this.adDetailPicErrorMsg = adDetailPicErrorMsg;
	}

	public List<AdTypeVO> getAdTypes() {
		return adTypes;
	}

	public void setAdTypes(List<AdTypeVO> adTypes) {
		this.adTypes = adTypes;
	}
	
	public List<AdSysVO> getAdSystems() {
		return adSystems;
	}

	public void setAdSystems(List<AdSysVO> adSystems) {
		this.adSystems = adSystems;
	}

	public List<AdLocationVO> getAdLocations() {
		return adLocations;
	}

	public void setAdLocations(List<AdLocationVO> adLocations) {
		this.adLocations = adLocations;
	}
	
	public List<DeviceTypeVO> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(List<DeviceTypeVO> deviceTypes) {
		this.deviceTypes = deviceTypes;
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

	public IAdService getAdService() {
		return adService;
	}

	public void setAdService(IAdService adService) {
		this.adService = adService;
	}
	
	public IAdTypeService getAdTypeService() {
		return adTypeService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setAdTypeService(IAdTypeService adTypeService) {
		this.adTypeService = adTypeService;
	}

	public IAdSysService getAdSysService() {
		return adSysService;
	}

	public void setAdSysService(IAdSysService adSysService) {
		this.adSysService = adSysService;
	}

	public IAdLocationService getAdLocationService() {
		return adLocationService;
	}

	public void setAdLocationService(IAdLocationService adLocationService) {
		this.adLocationService = adLocationService;
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

	
	/**
	 * 查看指定广告的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setAd(getAdService().getAdDetail(adId));
		logger.info("********************返回广告详细信息：{}", ad);
		return SUCCESS;
	}
	
	/**
	 * 查询广告列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<AdvertisementVO> page = getPage();
		if(page == null)
			page = new PagingVO<AdvertisementVO>();
		
		page = getAdService().queryAdsForPaging(ad, 
				page.getPageNum(), page.getPageSize());
		
		logger.info("********************返回广告分页信息：{}", page);
		
		if(page.getResults() != null){
			for (AdvertisementVO at : page.getResults()) {
				setTypeNameToI18N(at.getAdType());
			}
		}
		
		setPage(page);
		
		setAdTypes(queryAllAdTypes());
		
		return SUCCESS;
	}
	
	/**
	 * 获取广告信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setAd(getAdService().getAdDetail(adId));
		logger.info("********************获取待更新的广告信息：{}", ad);
		setAdTypes(queryAllAdTypes());
		setAdLocations(queryAdLocations(ad.getAdLocation().getAdSys().getSysCode()));
		setPicExt();
		if(Constants.AD_APP_SYS.equals(ad.getAdLocation().getAdSys().getSysCode())){
			setDeviceTypes(queryAllDeviceTypes());
			setRequestAttribute("dts", extractDeviceTypes(ad.getAdTargets()));
			setSystemGroups(true, ad.getAdTargets());
			return APP;
		}
		return WEB;
	}
	
	/**
	 * 更新广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		logger.info("********************更新广告信息：{}", ad);
		adId = ad.getAdId();
		boolean toWeb = Constants.AD_WEB_SYS.equals(
				ad.getAdLocation().getAdSys().getSysCode());
		
		boolean success = true;
		if(adPicFile != null){
			String newFileName = FileUploadUtil.renameFileName(extractFileName(adPicName));
			UploadResult result1 = uploadFile(newFileName, adPicFile);
			if(result1.isSuccess()){
				ad.setAdPicUrl(result1.getDownloadRelativePath());
			}else{
				success = false;
				addFieldError(AD_PIC_FILE, result1.getErrorMsg());
			}
		}
		
		if(!toWeb){
			if(adDetailPicFile != null){
				String newFileName = FileUploadUtil.renameFileName(extractFileName(adDetailPicName));
				UploadResult result2 = uploadFile(newFileName, adDetailPicFile);
				if(result2.isSuccess()){
					ad.setAdDetailPicUrl(result2.getDownloadRelativePath());
				}else{
					success = false;
					addFieldError(AD_DETAIL_PIC_FILE, result2.getErrorMsg());
				}
			}
		}
		
		if(!success){
			setErrorFlag(true);
			return updateInput();
		}
		
		if(!toWeb)
			buildAdTargets();
		
		getAdService().updateAd(ad);
		setPromptFlag(true);
		return updateInput();
	}
	
	/**
	 * 新增广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 新增Web广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addWebInput() throws Exception {
		setAdTypes(queryAllAdTypes());
		setAdLocations(queryAdLocations(Constants.AD_WEB_SYS));
		setPicExt();
		return SUCCESS;
	}
	
	/**
	 * 新增App广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addAppInput() throws Exception {		
		setAdTypes(queryAllAdTypes());
		setAdLocations(queryAdLocations(Constants.AD_APP_SYS));
		setDeviceTypes(queryAllDeviceTypes());
		setSystemGroups(false, null);
		setPicExt();
		return SUCCESS;
	}
	
	/**
	 * 保存新增Web广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String saveWeb() throws Exception {
		String newFileName = FileUploadUtil.renameFileName(extractFileName(adPicName));
		UploadResult result = uploadFile(newFileName, adPicFile);
		if(!result.isSuccess()){
			setErrorFlag(true);
			addFieldError(AD_PIC_FILE, result.getErrorMsg());
			return addWebInput();
		}
		
		ad.setAdId(null);
		ad.setAdPicUrl(result.getDownloadRelativePath());
		setAdSys(Constants.AD_WEB_SYS);
		save();
		return addWebInput();
	}
	
	/**
	 * 保存新增App广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String saveApp() throws Exception {
		String newAdPicName = FileUploadUtil.renameFileName(extractFileName(adPicName));
		String newAdDetailPicName = FileUploadUtil.renameFileName(extractFileName(adDetailPicName));
		UploadResult result1 = uploadFile(newAdPicName, adPicFile);
		UploadResult result2 = uploadFile(newAdDetailPicName, adDetailPicFile);
		if(!result1.isSuccess() || !result2.isSuccess()){
			setErrorFlag(true);
			addFieldError(AD_PIC_FILE, result1.getErrorMsg());
			addFieldError(AD_DETAIL_PIC_FILE, result2.getErrorMsg());
			return addAppInput();
		}
				
		ad.setAdId(null);
		ad.setAdPicUrl(result1.getDownloadRelativePath());
		ad.setAdDetailPicUrl(result2.getDownloadRelativePath());
		setAdSys(Constants.AD_APP_SYS);
		buildAdTargets();		
		save();
		return addAppInput();
	}
	
	/**
	 * 发布广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String publish() throws Exception {
		if(timingFlag){
			getAdService().publishTimingAd(
					adId, publishedTime, getLoginName());
		}else{
			getAdService().publishAd(adId, getLoginName());
		}
		logger.info("********************发布编号为{}的广告信息", adId);
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 停止广告。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String stop() throws Exception {
		getAdService().stopAd(adId);
		logger.info("********************停止编号为{}的广告信息", adId);
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 恢复广告。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String resume() throws Exception {
		getAdService().resumeAd(adId);
		logger.info("********************恢复编号为{}的广告信息", adId);
		setPromptFlag(true);	
		return queryList();
	}
	
	/**
	 * 删除广告信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		getAdService().removeAd(adId);
		logger.info("********************删除编号为{}的广告信息", adId);
		setPromptFlag(true);
		return queryList();
	}
	
	
	//获取目标系统的广告投放位置
	private List<AdLocationVO> queryAdLocations(String sysCode){
		List<AdLocationVO> als = getAdLocationService().queryAdLocations(sysCode);
		for (AdLocationVO al : als) {
			al.setLocationName(getText("ad.location." + al.getLocationCode()));
		}
		return als;
	}
	
	//获取所有广告投放目标系统
//	private List<AdSysVO> queryAllAdSystems(){
//		List<AdSysVO> ass = getAdSysService().queryAllAdSystems();
//		for (AdSysVO as : ass) {
//			as.setSysName(getText("ad.syscode." + as.getSysCode()));
//		}
//		return ass;
//	}
	
	//获取所有广告类型
	private List<AdTypeVO> queryAllAdTypes(){
		List<AdTypeVO> adTypes = getAdTypeService().queryAllAdTypes();
		for (AdTypeVO at : adTypes) {
			setTypeNameToI18N(at);
		}
		return adTypes;
	}
	
	//构造广告投放目标
	private void buildAdTargets() {
		if(StringUtils.isNotBlank(groupIds) 
				&& deviceTypes != null && !deviceTypes.isEmpty()){
			List<String> ids = new ArrayList<String>();
			for (String id : StringUtils.split(groupIds, Constants.SEPARATOR_GROUP_ID)) {
				ids.add(id);
			}
			logger.info("********************查询小区对应的组织编号：{}", ids);
			List<String> dts = new ArrayList<String>();
			for (DeviceTypeVO dt : deviceTypes) {
				dts.add(dt.getDeviceType());
			}
			logger.info("********************选择设备类型：{}", dts);
			List<DeviceVO> devices = getDeviceService().queryDevices(ids, dts);
			logger.info("********************查询设备信息：{}", devices);
			List<AdTargetVO> adTargets = new ArrayList<AdTargetVO>();
			for (DeviceVO device : devices) {
				AdTargetVO adTarget = new AdTargetVO();
				adTarget.setAdId(ad.getAdId());
				adTarget.setDevice(device);
				adTargets.add(adTarget);
			}
			ad.setAdTargets(adTargets);
		}
	}
	
	//获取所有设备类型
	private List<DeviceTypeVO> queryAllDeviceTypes(){
		List<DeviceTypeVO> deviceTypes = getDeviceTypeService().queryAllDeviceTypes();
		for (DeviceTypeVO dt : deviceTypes) {
			dt.setDeviceName(getText(dt.getDeviceName()));
		}
		return deviceTypes;
	}
	
	private void setSystemGroups(boolean isUpdated, List<AdTargetVO> adTargets){
		Set<Long> districtIds = null;
		if(isUpdated){						
			districtIds = new HashSet<Long>();
			for (AdTargetVO at : adTargets) {
				if(at.getDevice() != null 
						&& at.getDevice().getHousingDistrictInfo() != null){
					String districtId = at.getDevice().getHousingDistrictInfo().getId();
					if(StringUtils.isNotBlank(districtId))
						districtIds.add(Long.valueOf(districtId));
				}
			}
		}
		logger.info("********************当前小区编号：{}", districtIds);
		Object[] objects = getSystemGroupService().querySystemGroupForCheck(districtIds, true, false);
		setRequestAttribute("treeList", objects[0]);
		setRequestAttribute("comStr", objects[1]);
	}
		
	private void setTypeNameToI18N(AdTypeVO at){
		if(at == null || StringUtils.isBlank(at.getTypeCode()))
			return;
		at.setTypeName(getText("ad.type." + at.getTypeCode()));
	}
	
	private void setAdSys(String sysCode){
		AdSysVO as = new AdSysVO();
		as.setSysCode(sysCode);
		ad.getAdLocation().setAdSys(as);
	}
	
	private Set<String> extractDeviceTypes(List<AdTargetVO> adTargets){
		Set<String> deviceTypes = new HashSet<String>();
		for (AdTargetVO at : adTargets) {
			if(at.getDevice() != null 
					&& at.getDevice().getDeviceType() != 
					null && StringUtils.isNotBlank(at.getDevice().getDeviceType().getDeviceType())){
				deviceTypes.add(at.getDevice().getDeviceType().getDeviceType());
			}
		}
		return deviceTypes;
	}
	
	private void save() throws Exception {
		ad.setApplyedUser(getLoginName());
		ad.setApplyedTime(new Date());
		logger.info("********************新增广告信息：{}", ad);
		getAdService().addAd(ad);
		ad = null;
		setPromptFlag(true);
	}
	
	private void setPicExt() throws Exception {
		setRequestAttribute("picExt", getSysParamService().getParamValue(
				Constants.PARAM_CODE_WEB_IMAGE_FORMAT));
	}
}
