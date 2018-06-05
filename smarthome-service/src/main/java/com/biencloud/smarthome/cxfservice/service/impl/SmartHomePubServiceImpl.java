package com.biencloud.smarthome.cxfservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.cxfservice.service.SmartHomePubService;
import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.ad.service.IAdLocationService;
import com.biencloud.smarthome.service.ad.service.IAdService;
import com.biencloud.smarthome.service.ad.service.IAdSysService;
import com.biencloud.smarthome.service.ad.service.IAdTargetService;
import com.biencloud.smarthome.service.ad.service.IAdTypeService;
import com.biencloud.smarthome.service.addressbook.model.AddressBook;
import com.biencloud.smarthome.service.addressbook.service.IAddressBookService;
import com.biencloud.smarthome.service.alarm.model.Alarm;
import com.biencloud.smarthome.service.alarm.model.AlarmType;
import com.biencloud.smarthome.service.alarm.service.IAlarmService;
import com.biencloud.smarthome.service.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.service.charge.model.ChargeCalMode;
import com.biencloud.smarthome.service.charge.model.ChargeCalUnit;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.charge.model.ChargeStatitics;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.charge.model.ChargeTypeResult;
import com.biencloud.smarthome.service.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.service.charge.service.IChargeCalUnitService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.charge.service.IChargeDetailService;
import com.biencloud.smarthome.service.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.service.charge.service.IChargeTypeResultService;
import com.biencloud.smarthome.service.charge.service.IChargeTypeService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;
import com.biencloud.smarthome.service.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.deivceno.service.IDeviceNoService;
import com.biencloud.smarthome.service.device.model.CallRecord;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.model.DevicePassword;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.device.service.ICallRecordService;
import com.biencloud.smarthome.service.device.service.IDeviceIpService;
import com.biencloud.smarthome.service.device.service.IDevicePasswordService;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.device.service.IDeviceTypeService;
import com.biencloud.smarthome.service.gate.model.GateCard;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;
import com.biencloud.smarthome.service.gate.model.IdCardVisit;
import com.biencloud.smarthome.service.gate.service.IGateCardService;
import com.biencloud.smarthome.service.gate.service.IGateCardVisitService;
import com.biencloud.smarthome.service.gate.service.IIdCardVisitService;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfs.service.IHDFSFileService;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.hdfstask.service.IHDFSTaskService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;
import com.biencloud.smarthome.service.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.housemgr.service.ICellSizeInfoService;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictRegionInfoService;
import com.biencloud.smarthome.service.housemgr.service.IPropertyCompanyInfoService;
import com.biencloud.smarthome.service.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.service.info.model.DistrictData;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.service.IInfoReceiverDeviceService;
import com.biencloud.smarthome.service.info.service.IInfoReceiverService;
import com.biencloud.smarthome.service.info.service.IInfoSendService;
import com.biencloud.smarthome.service.log.model.AppDataLog;
import com.biencloud.smarthome.service.log.model.ClientLog;
import com.biencloud.smarthome.service.log.model.DiviceRegeditLog;
import com.biencloud.smarthome.service.log.model.FileUploadLog;
import com.biencloud.smarthome.service.log.model.OperationLog;
import com.biencloud.smarthome.service.log.model.SystemLog;
import com.biencloud.smarthome.service.log.service.IAppDataLogService;
import com.biencloud.smarthome.service.log.service.IClientLogService;
import com.biencloud.smarthome.service.log.service.IDiviceRegeditLogService;
import com.biencloud.smarthome.service.log.service.IFileUploadLogService;
import com.biencloud.smarthome.service.log.service.IOperationLogService;
import com.biencloud.smarthome.service.log.service.ISystemLogService;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.menu.service.IMenuService;
import com.biencloud.smarthome.service.monitor.model.Scene;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
import com.biencloud.smarthome.service.monitor.service.IMonitorService;
import com.biencloud.smarthome.service.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.service.page.model.Component;
import com.biencloud.smarthome.service.page.model.PageLayout;
import com.biencloud.smarthome.service.page.model.PageContent;
import com.biencloud.smarthome.service.page.service.IComponentService;
import com.biencloud.smarthome.service.page.service.IPageContentService;
import com.biencloud.smarthome.service.page.service.IPageService;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.permissions.service.IPermissionsService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.model.PushFinish;
import com.biencloud.smarthome.service.push.service.IPushFinishService;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;
import com.biencloud.smarthome.service.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.service.rss.model.RssServer;
import com.biencloud.smarthome.service.rss.model.Weather;
import com.biencloud.smarthome.service.rss.service.IRssServerService;
import com.biencloud.smarthome.service.rss.service.IWeatherService;
import com.biencloud.smarthome.service.rss.vo.WeatherReportData;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;
import com.biencloud.smarthome.service.softwareupgrade.service.ISoftwareUpgradeService;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;
import com.biencloud.smarthome.service.sysgroup.service.ISystemGroupService;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;
import com.biencloud.smarthome.service.user.model.SaUser;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;
import com.biencloud.smarthome.service.user.service.IPaUserService;
import com.biencloud.smarthome.service.user.service.ISaUserService;

/**
 * 
 * 类名称：SmartHomePubServiceImpl 类描述：web service发布实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-2 下午5:20:16
 */
public class SmartHomePubServiceImpl implements SmartHomePubService {

	private ILoginService loginService;
	private IPushService pushService;
	private IPushFinishService pushFinishService;
	private IMenuService menuService;
	private IPermissionsService permissionsService;
	private ISysParamService sysParamService;
	private ISoftwareUpgradeService softwareUpgradeService;
	private IDeviceTypeService deviceTypeService;
	private ISystemLogService systemLogService;
	private IOperationLogService operationLogService;
	private IDeviceService deviceService;
	private IGateCardService gateCardService;
	private IGateCardVisitService gateCardVisitService;
	private IIdCardVisitService idCardVisitService;
	private ISaUserService saUserService;
	private IPaUserService paUserService;
	private IOwnerUserService ownerUserService;

	private IHousingDistrictInfoService housingDistrictInfoService;
	private IHousingDistrictRegionInfoService housingDistrictRegionInfoService;
	private IRegionBuildingInfoService regionBuildingInfoService;
	private IBuildingCellInfoService buildingCellInfoService;
	private ICellSizeInfoService cellSizeInfoService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IPropertyCompanyInfoService propertyCompanyInfoService;

	private ISystemGroupService systemGroupService;
	private IDeviceNoService deviceNoService;
	private IChargeCalUnitService chargeCalUnitService;
	private IChargeCalModeService chargeCalModeService;
	private IChargeDataService chargeDataService;
	private IChargeDetailService chargeDetailService;
	private IChargeMonetaryUnitService chargeMonetaryUnitService;
	private IChargeTypeResultService chargeTypeResultService;
	private IChargeTypeService chargeTypeService;
	private ICallRecordService callRecordService;
	private IDeviceIpService deviceIpService;
	private IInfoReceiverService infoReceiverService;
	private IInfoSendService infoSendService;
	private IAlarmService alarmService;
	private IAlarmTypeService alarmTypeService;
	private IAddressBookService addressBookService;

	private IComplaintService complaintService;

	private IAdService adService;
	private IAdTypeService adTypeService;
	private IAdSysService adSysService;
	private IAdLocationService adLocationService;
	private IAdTargetService adTargetService;

	private ISceneMonitorService sceneMonitorService;

	private IRssServerService rssServerService;
	private IWeatherService weatherService;
	private IMonitorService monitorService;
	private IHDFSTaskService hdfsTaskService;
	private IHDFSFileService hdfsFileService;

	private IRequestRepairService requestRepairService;
	private IClientLogService clientLogService;
	private IDevicePasswordService devicePasswordService;
	private IFileUploadLogService fileUploadLogService;
	private IDiviceRegeditLogService diviceRegeditLogService;
	private IInfoReceiverDeviceService infoReceiverDeviceService;
	private IAppDataLogService appDataLogService;
	private IComponentService componentService;
	private IPageService pageService;
	private IPageContentService pageContentService;
	
	
	public IAppDataLogService getAppDataLogService() {
		return appDataLogService;
	}
	public void setAppDataLogService(IAppDataLogService appDataLogService) {
		this.appDataLogService = appDataLogService;
	}
	public IInfoReceiverDeviceService getInfoReceiverDeviceService() {
		return infoReceiverDeviceService;
	}
	public void setInfoReceiverDeviceService(IInfoReceiverDeviceService infoReceiverDeviceService) {
		this.infoReceiverDeviceService = infoReceiverDeviceService;
	}
	public IDiviceRegeditLogService getDiviceRegeditLogService() {
		return diviceRegeditLogService;
	}
	public void setDiviceRegeditLogService(IDiviceRegeditLogService diviceRegeditLogService) {
		this.diviceRegeditLogService = diviceRegeditLogService;
	}
	public IFileUploadLogService getFileUploadLogService() {
		return fileUploadLogService;
	}
	public void setFileUploadLogService(IFileUploadLogService fileUploadLogService) {
		this.fileUploadLogService = fileUploadLogService;
	}
	public IRequestRepairService getRequestRepairService() {
		return requestRepairService;
	}
	public void setRequestRepairService(IRequestRepairService requestRepairService) {
		this.requestRepairService = requestRepairService;
	}
	public IMonitorService getMonitorService() {
		return monitorService;
	}

	public void setMonitorService(IMonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public IRssServerService getRssServerService() {
		return rssServerService;
	}

	public void setRssServerService(IRssServerService rssServerService) {
		this.rssServerService = rssServerService;
	}
	

	public IChargeCalUnitService getChargeCalUnitService() {
		return chargeCalUnitService;
	}
	public void setChargeCalUnitService(IChargeCalUnitService chargeCalUnitService) {
		this.chargeCalUnitService = chargeCalUnitService;
	}
	public IComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(IComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	public IInfoSendService getInfoSendService() {
		return infoSendService;
	}

	public void setInfoSendService(IInfoSendService infoSendService) {
		this.infoSendService = infoSendService;
	}

	public IChargeCalModeService getChargeCalModeService() {
		return chargeCalModeService;
	}

	public void setChargeCalModeService(IChargeCalModeService chargeCalModeService) {
		this.chargeCalModeService = chargeCalModeService;
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	public IChargeDetailService getChargeDetailService() {
		return chargeDetailService;
	}

	public void setChargeDetailService(IChargeDetailService chargeDetailService) {
		this.chargeDetailService = chargeDetailService;
	}

	public IChargeMonetaryUnitService getChargeMonetaryUnitService() {
		return chargeMonetaryUnitService;
	}

	public void setChargeMonetaryUnitService(IChargeMonetaryUnitService chargeMonetaryUnitService) {
		this.chargeMonetaryUnitService = chargeMonetaryUnitService;
	}

	public IChargeTypeResultService getChargeTypeResultService() {
		return chargeTypeResultService;
	}

	public void setChargeTypeResultService(IChargeTypeResultService chargeTypeResultService) {
		this.chargeTypeResultService = chargeTypeResultService;
	}

	public IChargeTypeService getChargeTypeService() {
		return chargeTypeService;
	}

	public void setChargeTypeService(IChargeTypeService chargeTypeService) {
		this.chargeTypeService = chargeTypeService;
	}

	public IBuildingCellInfoService getBuildingCellInfoService() {
		return buildingCellInfoService;
	}

	public void setBuildingCellInfoService(IBuildingCellInfoService buildingCellInfoService) {
		this.buildingCellInfoService = buildingCellInfoService;
	}

	public IDeviceNoService getDeviceNoService() {
		return deviceNoService;
	}

	public void setDeviceNoService(IDeviceNoService deviceNoService) {
		this.deviceNoService = deviceNoService;
	}

	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}

	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}

	public IRegionBuildingInfoService getRegionBuildingInfoService() {
		return regionBuildingInfoService;
	}

	public void setRegionBuildingInfoService(IRegionBuildingInfoService regionBuildingInfoService) {
		this.regionBuildingInfoService = regionBuildingInfoService;
	}

	public IPropertyCompanyInfoService getPropertyCompanyInfoService() {
		return propertyCompanyInfoService;
	}

	public IHousingDistrictRegionInfoService getHousingDistrictRegionInfoService() {
		return housingDistrictRegionInfoService;
	}

	public void setHousingDistrictRegionInfoService(IHousingDistrictRegionInfoService housingDistrictRegionInfoService) {
		this.housingDistrictRegionInfoService = housingDistrictRegionInfoService;
	}

	public void setPropertyCompanyInfoService(IPropertyCompanyInfoService propertyCompanyInfoService) {
		this.propertyCompanyInfoService = propertyCompanyInfoService;
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
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

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IGateCardService getGateCardService() {
		return gateCardService;
	}

	public void setGateCardService(IGateCardService gateCardService) {
		this.gateCardService = gateCardService;
	}

	public IGateCardVisitService getGateCardVisitService() {
		return gateCardVisitService;
	}

	public void setGateCardVisitService(IGateCardVisitService gateCardVisitService) {
		this.gateCardVisitService = gateCardVisitService;
	}

	public IIdCardVisitService getIdCardVisitService() {
		return idCardVisitService;
	}

	public void setIdCardVisitService(IIdCardVisitService idCardVisitService) {
		this.idCardVisitService = idCardVisitService;
	}

	public ISaUserService getSaUserService() {
		return saUserService;
	}

	public void setSaUserService(ISaUserService saUserService) {
		this.saUserService = saUserService;
	}

	public IPaUserService getPaUserService() {
		return paUserService;
	}

	public void setPaUserService(IPaUserService paUserService) {
		this.paUserService = paUserService;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public IDeviceIpService getDeviceIpService() {
		return deviceIpService;
	}

	public void setDeviceIpService(IDeviceIpService deviceIpService) {
		this.deviceIpService = deviceIpService;
	}

	public ICallRecordService getCallRecordService() {
		return callRecordService;
	}

	public void setCallRecordService(ICallRecordService callRecordService) {
		this.callRecordService = callRecordService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public IAlarmTypeService getAlarmTypeService() {
		return alarmTypeService;
	}

	public void setAlarmTypeService(IAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
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

	public IAdTargetService getAdTargetService() {
		return adTargetService;
	}

	public void setAdTargetService(IAdTargetService adTargetService) {
		this.adTargetService = adTargetService;
	}

	public IWeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(IWeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public IDevicePasswordService getDevicePasswordService() {
		return devicePasswordService;
	}
	public void setDevicePasswordService(IDevicePasswordService devicePasswordService) {
		this.devicePasswordService = devicePasswordService;
	}
		
	
	public IPageService getPageService() {
		return pageService;
	}
	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}
	public IComponentService getComponentService() {
		return componentService;
	}
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	@Override
	public Paging<Menu> queryMenusForPaging(String menuName, boolean hasParent, String status, int pageNum, int pageSize) {
		return getMenuService().queryMenusForPaging(menuName, hasParent, status, pageNum, pageSize);
	}

	@Override
	public Menu getMenuDetail(String menuCode) {
		return getMenuService().get(menuCode);
	}

	@Override
	public void updateMenu(Menu menu) {
		getMenuService().update(menu);
	}

	@Override
	public void updateMenuStatus(String menuCode, String status, String updatedUser) {
		getMenuService().updateMenuStatus(menuCode, status, updatedUser);
	}

	@Override
	public Paging<Role> queryRolesForPaging(String roleCode, String loginName, String roleName, String status, int pageNum, int pageSize) {
		return getPermissionsService().queryRolesForPaging(roleCode, loginName, roleName, status, pageNum, pageSize);
	}

	@Override
	public void addRole(Role role) {
		getPermissionsService().save(role);
	}

	@Override
	public Role getRoleDetail(String roleCode) {
		return getPermissionsService().get(roleCode);
	}

	@Override
	public void updateRole(Role role) {
		getPermissionsService().update(role);
	}

	@Override
	public List<Menu> queryMenusByRole(String roleCode) {
		return getPermissionsService().queryMenusByRole(roleCode);
	}

	@Override
	public List<Role> queryRoles(String roleCode, String loginName, 
			String currUserType, String targetUserType, boolean filterDisabled) {
		return getPermissionsService().queryRoles(roleCode, loginName, 
				currUserType, targetUserType, filterDisabled);
	}

	@Override
	public Paging<SystemParam> querySystemParamsForPaging(String paramName, int pageNum, int pageSize) {
		return getSysParamService().querySystemParamsForPaging(paramName, pageNum, pageSize);
	}

	@Override
	public SystemParam getSystemParamDetail(String paramCode) {
		return getSysParamService().get(paramCode);
	}

	@Override
	public void updateSystemParam(SystemParam systemParam) {
		getSysParamService().updateSysParam(systemParam);
	}

	@Override
	public Paging<SoftwareUpgrade> querySoftwaresForPaging(String softwareName, String deviceType, String status, int pageNum, int pageSize) {
		return getSoftwareUpgradeService().querySoftwaresForPaging(softwareName, deviceType, status, pageNum, pageSize);
	}

	@Override
	public void upgradeSoftware(SoftwareUpgrade su) {
		getSoftwareUpgradeService().upgradeSoftware(su);
	}
	
	@Override
	public SoftwareUpgrade getLatestSoftware(String softwareCode) {
		return getSoftwareUpgradeService().getLatestSoftware(softwareCode);
	}
	
	@Override
	public void approveSoftware(String softwareId, boolean isApproved, String approvedUser) {
		getSoftwareUpgradeService().approveSoftware(softwareId, isApproved, approvedUser);
	}

	@Override
	public void publishSoftware(String softwareId, String publishedUser) {
		getSoftwareUpgradeService().publishSoftware(softwareId, publishedUser);
	}

	@Override
	public void publishTimingSoftware(String softwareId, Date publishedTime, String publishedUser) {
		getSoftwareUpgradeService().publishTimingSoftware(softwareId, publishedTime, publishedUser);
	}

	@Override
	public void addSoftware(SoftwareUpgrade softwareUpgrade) {
		getSoftwareUpgradeService().save(softwareUpgrade);
	}

	@Override
	public SoftwareUpgrade getSoftwareDetail(String softwareId) {
		return getSoftwareUpgradeService().get(softwareId);
	}

	@Override
	public void removeSoftware(String softwareId) {
		getSoftwareUpgradeService().removeByIds(softwareId);
	}

	@Override
	public void notifyUpgradeSoftware(String softwareId, Date upgradedTime,
			String upgradedUser) {
		getSoftwareUpgradeService().notifyUpgradeSoftware(
				softwareId, upgradedTime, upgradedUser);
	}
	
	@Override
	public List<DeviceType> queryAllDeviceTypes() {
		return getDeviceTypeService().queryAllDeviceTypes();
	}

	@Override
	public List<Device> queryDevices(Device device) {
		return getDeviceService().queryDevices(device);
	}

	@Override
	public Paging<GateCardVisit> queryGateCardVisitsForPaging(GateCardVisit gateCardVisit, int pageNum, int pageSize) {
		return getGateCardVisitService().queryGateCardVisitsForPaging(gateCardVisit, pageNum, pageSize);
	}

	@Override
	public GateCardVisit getGateCardVisitDetail(String visitId) {
		return getGateCardVisitService().get(visitId);
	}

	@Override
	public Paging<IdCardVisit> queryIdCardVisitsForPaging(IdCardVisit idCardVisit, int pageNum, int pageSize) {
		return getIdCardVisitService().queryIdCardVisitsForPaging(idCardVisit, pageNum, pageSize);
	}

	@Override
	public IdCardVisit getIdCardVisitDetail(String visitId) {
		return getIdCardVisitService().get(visitId);
	}

	@Override
	public Paging<GateCard> queryGateCardsForPaging(GateCard gateCard, int pageNum, int pageSize) {
		return getGateCardService().queryGateCardsForPaging(gateCard, pageNum, pageSize);
	}

	@Override
	public GateCard getGateCardDetail(String gateCardId) {
		return getGateCardService().get(gateCardId);
	}

	@Override
	public void addGateCard(GateCard gateCard) {
		getGateCardService().save(gateCard);
	}

	@Override
	public void updateGateCard(GateCard gateCard) {
		getGateCardService().update(gateCard);
	}

	@Override
	public void removeGateCard(String gateCardId) {
		getGateCardService().removeByIds(gateCardId);
	}

	@Override
	public void updateGateCardStatus(String gateCardId, String status, String updatedUser) {
		getGateCardService().updateGateCardStatus(gateCardId, status, updatedUser);
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public IPushFinishService getPushFinishService() {
		return pushFinishService;
	}

	public void setPushFinishService(IPushFinishService pushFinishService) {
		this.pushFinishService = pushFinishService;
	}

	@Override
	public HousingDistrictInfo getHousingDistrictInfo(String id) {
		return getHousingDistrictInfoService().get(id);
	}

	@Override
	public String getDistrictIdByGroupNo(Long groupNo) {
		return getHousingDistrictInfoService().getDistrictIdByGroupNo(groupNo);
	}
	
	@Override
	public void updateHousingDistrictInfo(HousingDistrictInfo district) {
		getHousingDistrictInfoService().update(district);
	}

	@Override
	public void updateDistrictPropertyCompanyId(String districtId, int propertyCompanyId) {
		housingDistrictInfoService.updateDistrictPropertyCompanyId(districtId, propertyCompanyId);
	}

	@Override
	public void saveHousingDistrictInfo(HousingDistrictInfo district) {
		getHousingDistrictInfoService().save(district);
	}

	@Override
	public Paging<HousingDistrictInfo> queryHousingDistrictInfosForPaging(HousingDistrictInfo condition, int pageNum, int pageSize) {
		return getHousingDistrictInfoService().queryHousingDistrictInfosForPaging(condition, pageNum, pageSize);
	}

	@Override
	public List<HousingDistrictInfo> getDistricts(HousingDistrictInfo condition) {
		return housingDistrictInfoService.getDistricts(condition);
	}

	@Override
	public Paging<HousingDistrictRegionInfo> queryHousingDistrictRegionInfosForPaging(HousingDistrictRegionInfo condition, int pageNum,
			int pageSize) {
		return housingDistrictRegionInfoService.queryHousingDistrictRegionInfosForPaging(condition, pageNum, pageSize);
	}
	@Override
	public long getDistrictCount() {
		return housingDistrictInfoService.getDistrictCount();
	}
	
	
	
	@Override
	public List<HousingDistrictRegionInfo> getRegions(HousingDistrictRegionInfo condition) {
		return housingDistrictRegionInfoService.getRegions(condition);
	}

	@Override
	public void updateHousingDistrictRegionInfo(HousingDistrictRegionInfo region) {
		housingDistrictRegionInfoService.update(region);
	}

	@Override
	public int removeRegionById(String id) {
		return housingDistrictRegionInfoService.removeById(id);
	}

	@Override
	public void saveHousingDistrictRegionInfo(HousingDistrictRegionInfo region) {
		housingDistrictRegionInfoService.save(region);
	}

	@Override
	public HousingDistrictRegionInfo getHousingDistrictRegionInfo(String id) {
		return housingDistrictRegionInfoService.get(id);
	}

	@Override
	public Paging<RegionBuildingInfo> queryRegionBuildingInfosForPaging(RegionBuildingInfo condition, int pageNum, int pageSize) {
		return regionBuildingInfoService.queryRegionBuildingInfosForPaging(condition, pageNum, pageSize);
	}

	@Override
	public List<RegionBuildingInfo> getBuildings(RegionBuildingInfo condition) {
		return regionBuildingInfoService.getBuildings(condition);
	}

	@Override
	public void saveOrUpdateRegionBuildingInfo(RegionBuildingInfo building) {
		regionBuildingInfoService.saveOrUpdateRegionBuilding(building);
	}

	@Override
	public RegionBuildingInfo getRegionBuildingInfo(String id) {
		return regionBuildingInfoService.get(id);
	}

	@Override
	public int removeBuildingById(String id) {
		return regionBuildingInfoService.removeById(id);
	}

	@Override
	public Paging<BuildingCellInfo> queryBuildingCellInfosForPaging(BuildingCellInfo condition, int pageNum, int pageSize) {
		return buildingCellInfoService.queryBuildingCellInfosForPaging(condition, pageNum, pageSize);
	}

	@Override
	public BuildingCellInfo getBuildingCellInfo(String id) {
		return buildingCellInfoService.get(id);
	}

	@Override
	public int removeCellById(String id) {
		return buildingCellInfoService.removeById(id);
	}

	@Override
	public void saveOrUpdateBuildingCellInfo(BuildingCellInfo cell) {
		buildingCellInfoService.save_update(cell);
	}
	
	@Override
	public BuildingCellInfo saveOrUpdateBuildingCell(BuildingCellInfo cell) {
		return buildingCellInfoService.save_update(cell);
	}

	@Override
	public void saveOrUpdateCellSizeInfo(CellSizeInfo size) {
		cellSizeInfoService.save_update(size);
	}

	@Override
	public int removeCellSizeById(String id) {
		return cellSizeInfoService.removeById(id);
	}
	@Override
	public List<CellSizeInfo> findCellSizeInfo(CellSizeInfo condition) {
		return cellSizeInfoService.findCellSizeInfo(condition);
	}

	@Override
	public String saveCustomSize(CellSizeInfo size, String houseId) {
		return cellSizeInfoService.saveCustomSize(size, houseId);
	}
	
	@Override
	public CellHouseholdInfo getCellHouseholdInfo(String id) {
		return cellHouseholdInfoService.get(id);
	}

	@Override
	public List<CellHouseholdInfo> findHouseholds(CellHouseholdInfo condition) {
		return cellHouseholdInfoService.findHouseholds(condition);
	}

	@Override
	public void updateCellHouseholdInfoSomeProperty(String id, String code, CellSizeInfo size, String area, List<ChargeType> chargeTypes) {
		cellHouseholdInfoService.updateSomeProperty(id, code, size, area, chargeTypes);
	}

	@Override
	public void saveOrUpdateCellHouseholdInfo(CellHouseholdInfo house) {
		cellHouseholdInfoService.save_update(house);
	}

	@Override
	public void updateHouseSizeId(String houseId, String sizeId) {
		cellHouseholdInfoService.updateHouseSizeId(houseId, sizeId);
	}

	@Override
	public int removeHouseById(String id) {
		return cellHouseholdInfoService.removeById(id);
	}

	@Override
	public Paging<CellHouseholdInfo> queryCellHouseholdInfosForPaging(CellHouseholdInfo condition, int pageNum, int pageSize) {
		return cellHouseholdInfoService.queryCellHouseholdInfosForPaging(condition, pageNum, pageSize);
	}

	@Override
	public Paging<PropertyCompanyInfo> queryPropertyCompanyInfosForPaging(PropertyCompanyInfo condition, int pageNum, int pageSize) {
		return propertyCompanyInfoService.queryPropertyCompanyInfosForPaging(condition, pageNum, pageSize);
	}

	@Override
	public List<PropertyCompanyInfo> findPropertyCompanyInfos(PropertyCompanyInfo condition) {
		return propertyCompanyInfoService.findPropertyCompanyInfos(condition);
	}

	@Override
	public PropertyCompanyInfo getPropertyCompanyInfo(Integer id) {
		return propertyCompanyInfoService.get(id);
	}

	@Override
	public void updatePropertyCompanyInfo(PropertyCompanyInfo propertyCompany) {
		propertyCompanyInfoService.update(propertyCompany);
	}

	@Override
	public int savePropertyCompanyInfo(PropertyCompanyInfo propertyCompany) {
		propertyCompanyInfoService.save(propertyCompany);
		return propertyCompany.getId();
	}

	@Override
	public Login logon(Login login) {
		return getLoginService().login(login);
	}

	@Override
	public Login getLoginByLoginName(String loginName) {
		return getLoginService().getLoginByLoginName(loginName);
	}

	@Override
	public void resetPassword(String loginName) {
		getLoginService().resetPassword(loginName);
	}

	@Override
	public void updatePassword(String loginName, String password) {
		getLoginService().updatePassword(loginName, password);
	}
	
	@Override
	public void updateStatus(String loginId, String status) {
		getLoginService().updateStatus(loginId, status);
	}

	@Override
	public void updateOnlineFlag(String loginName, String onlineFlag, String ip) {
		getLoginService().updateOnlineFlag(loginName, onlineFlag, ip);
	}
	
	@Override
	public long countUsersByOnlineFlag(String onlineFlag) {
		return getLoginService().countUsersByOnlineFlag(onlineFlag);
	}
	
	@Override
	public Paging<SaUser> querySaUsersForPaging(SaUser saUser, int pageNum,
			int pageSize) {

		return getSaUserService().querySaUsersForPaging(saUser, pageNum, pageSize);
	}

	@Override
	public SaUser getSaUserDetail(String userId) {
		return getSaUserService().get(userId);
	}
	
	@Override
	public SaUser getSaUser(String userId,String saUserType) {
		return getSaUserService().get(userId,saUserType);
	}

	@Override
	public void addSaUser(SaUser user) {
		getSaUserService().save(user);
	}

	@Override
	public void updateSaUser(SaUser user) {
		getSaUserService().update(user);
	}

	@Override
	public Paging<PaUser> queryPaUsersForPaging(PaUser user, int pageNum, int pageSize) {
		return getPaUserService().queryPaUsersForPaging(user, pageNum, pageSize);
	}

	@Override
	public PaUser getPaUserDetail(String userId) {
		return getPaUserService().get(userId);
	}

	@Override
	public void addPaUser(PaUser user) {
		getPaUserService().save(user);
	}

	@Override
	public void updatePaUser(PaUser user) {
		getPaUserService().update(user);
	}

	@Override
	public Paging<OwnerUser> queryOwnerUsersForPaging(OwnerUser user, int pageNum, int pageSize) {
		return getOwnerUserService().queryOwnerUsersForPaging(user, pageNum, pageSize);
	}

	@Override
	public OwnerUser getOwnerUserDetail(String userId) {
		return getOwnerUserService().get(userId);
	}

	@Override
	public void addOwnerUser(OwnerUser user) {
		getOwnerUserService().save(user);
	}

	@Override
	public void addOwnerUsers(List<OwnerUser> users) {
		getOwnerUserService().addUsers(users);
	}

	@Override
	public void updateOwnerUser(OwnerUser user) {
		getOwnerUserService().update(user);
	}

	@Override
	public long countUsers(OwnerUser user) {
		return getOwnerUserService().countUsers(user);
	}
	
	@Override
	public OwnerUser getOwnerUserByHouseId(String houseId) {
		return getOwnerUserService().getUserByHouseId(houseId);
	}
	
	@Override
	public Push findPushById(Long id) {
		return pushService.findById(id);
	}

	@Override
	public boolean deletePushById(Long id) {
		return pushService.deleteById(id);
	}

	@Override
	public boolean deletePushByEntity(Push push) {
		return pushService.deleteByEntity(push);
	}

	@Override
	public Paging<Push> queryPushForPaging(int pageNum, int pageSize, String condition, String orderString) {

		return pushService.queryPushForPaging(pageNum, pageSize, condition, orderString);
	}

	@Override
	public PushFinish findPushFinishById(Long id) {
		return pushFinishService.findById(id);
	}

	@Override
	public boolean deletePushFinishById(Long id) {
		return pushFinishService.deleteById(id);
	}

	@Override
	public boolean deletePushFinishByEntity(PushFinish pushFinish) {
		return pushFinishService.deleteByEntity(pushFinish);
	}

	@Override
	public Paging<PushFinish> queryPushFinishForPaging(int pageNum, int pageSize, String condition, String orderString) {
		return pushFinishService.queryPushFinishForPaging(pageNum, pageSize, condition, orderString);
	}

	@Override
	public List<SystemGroup> querySystemGroup(int pageNo, int pageSize, SystemGroup ob) {
		return getSystemGroupService().querySystemGroup(pageNo, pageSize, ob);
	}

	@Override
	public boolean saveOrUpdateSystemGroup(SystemGroup systemGroup) {
		return getSystemGroupService().saveOrUpdateSystemGroup(systemGroup);

	}

	@Override
	public boolean deleteSystemGroupById(Long id) {
		return getSystemGroupService().deleteSystemGroupById(id);
	}

	@Override
	public Paging<DeviceNo> queryDeviceNoForPaging(DeviceNo paramsOb, int pageNum, int pageSize) {
		return getDeviceNoService().queryDeviceForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public String generatorDeviceNo() {
		return getDeviceNoService().generatorDeviceNo();
	}

	@Override
	public boolean updateDeviceId(Device device, String deviceNo) {
		return getDeviceNoService().updateDeviceId(device, deviceNo);
	}

	@Override
	public Paging<ChargeCalMode> queryChargeCalModeForPaging(ChargeCalMode paramsOb, int pageNum, int pageSize) {
		return getChargeCalModeService().queryChargeCalModeForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeCalMode(ChargeCalMode entity) {
		getChargeCalModeService().updateChargeCalMode(entity);

	}

	@Override
	public void saveChargeCalMode(ChargeCalMode entity) {
		getChargeCalModeService().saveChargeCalMode(entity);
	}

	@Override
	public ChargeCalMode getChargeCalMode(String entityId) {
		return getChargeCalModeService().getChargeCalMode(entityId);
	}

	@Override
	public Paging<ChargeData> queryChargeDataForPaging(ChargeData paramsOb, int pageNum, int pageSize) {
		return getChargeDataService().queryChargeDataForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeData(ChargeData entity) {
		getChargeDataService().updateChargeData(entity);
	}

	@Override
	public void saveChargeData(ChargeData entity) {
		getChargeDataService().saveChargeData(entity);
	}

	@Override
	public ChargeData getChargeData(String entityId) {
		return getChargeDataService().getChargeData(entityId);
	}

	@Override
	public Paging<ChargeDetail> queryChargeDetailForPaging(ChargeDetail paramsOb, int pageNum, int pageSize) {
		return getChargeDetailService().queryChargeDetailForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeDetail(ChargeDetail entity) {
		getChargeDetailService().updateChargeDetail(entity);
	}

	@Override
	public void saveChargeDetail(ChargeDetail entity) {
		getChargeDetailService().saveChargeDetail(entity);
	}

	@Override
	public ChargeDetail getChargeDetail(String entityId) {
		return getChargeDetailService().getChargeDetail(entityId);
	}

	@Override
	public Paging<ChargeMonetaryUnit> queryChargeMonetaryUnitForPaging(ChargeMonetaryUnit paramsOb, int pageNum, int pageSize) {
		return getChargeMonetaryUnitService().queryChargeMonetaryUnitForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		getChargeMonetaryUnitService().updateChargeMonetaryUnit(entity);
	}

	@Override
	public void saveChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		getChargeMonetaryUnitService().saveChargeMonetaryUnit(entity);
	}

	@Override
	public ChargeMonetaryUnit getChargeMonetaryUnit(String entityId) {
		return getChargeMonetaryUnitService().getChargeMonetaryUnit(entityId);
	}

	@Override
	public Paging<ChargeTypeResult> queryChargeTypeResultForPaging(ChargeTypeResult paramsOb, int pageNum, int pageSize) {
		return getChargeTypeResultService().queryChargeTypeResultForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeTypeResult(ChargeTypeResult entity) {
		getChargeTypeResultService().updateChargeTypeResult(entity);
	}

	@Override
	public void saveChargeTypeResult(ChargeTypeResult entity) {
		getChargeTypeResultService().saveChargeTypeResult(entity);
	}

	@Override
	public ChargeTypeResult getChargeTypeResult(String entityId) {
		return getChargeTypeResultService().getChargeTypeResult(entityId);
	}

	@Override
	public Paging<ChargeType> queryChargeTypeForPaging(ChargeType paramsOb, int pageNum, int pageSize) {
		return getChargeTypeService().queryChargeTypeForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateChargeType(ChargeType entity) {
		getChargeTypeService().updateChargeType(entity);
	}

	@Override
	public void saveChargeType(ChargeType entity) {
		getChargeTypeService().saveChargeType(entity);
	}

	@Override
	public ChargeType getChargeType(String entityId) {
		return getChargeTypeService().getChargeType(entityId);
	}

	@Override
	public void DelChargeCalMode(ChargeCalMode entity) {
		getChargeCalModeService().DelChargeCalMode(entity);

	}

	@Override
	public void DelChargeData(ChargeData entity) {
		getChargeDataService().DelChargeData(entity);

	}

	@Override
	public void DelChargeDetail(ChargeDetail entity) {
		getChargeDetailService().DelChargeDetail(entity);

	}

	@Override
	public void DelChargeMonetaryUnit(ChargeMonetaryUnit entity) {
		getChargeMonetaryUnitService().DelChargeMonetaryUnit(entity);

	}

	@Override
	public void DelChargeTypeResult(ChargeTypeResult entity) {
		getChargeTypeResultService().DelChargeTypeResult(entity);

	}

	@Override
	public void DelChargeType(ChargeType entity) {
		getChargeTypeService().DelChargeType(entity);

	}

	@Override
	public boolean insertPush(Push push) {
		// TODO Auto-generated method stub
		return pushService.insertPush(push);
	}

	@Override
	public List<ChargeCalMode> queryChargeCalModeForList(ChargeCalMode paramsOb) {
		return getChargeCalModeService().queryChargeCalModeForList(paramsOb);
	}

	@Override
	public List<ChargeData> queryChargeDataForList(ChargeData paramsOb) {
		return getChargeDataService().queryChargeDataForList(paramsOb);
	}

	@Override
	public List<ChargeMonetaryUnit> queryChargeMonetaryUnitForList(ChargeMonetaryUnit paramsOb) {
		return getChargeMonetaryUnitService().queryChargeMonetaryUnitForList(paramsOb);
	}

	@Override
	public List<ChargeTypeResult> queryChargeTypeResultForList(ChargeTypeResult paramsOb) {
		return getChargeTypeResultService().queryChargeTypeResultForList(paramsOb);
	}

	@Override
	public List<ChargeType> queryChargeTypeForList(ChargeType paramsOb) {
		return getChargeTypeService().queryChargeTypeForList(paramsOb);
	}

	@Override
	public List<ChargeDetail> queryChargeDetailForList(ChargeDetail paramsOb) {
		return getChargeDetailService().queryChargeDetailForList(paramsOb);
	}

	@Override
	public SystemLog getSystemLogById(String logId) {
		return getSystemLogService().getSystemLogById(logId);
	}

	@Override
	public OperationLog getOperationLogById(String logId) {
		return getOperationLogService().getOperationLog(logId);
	}

	@Override
	public int updateDevice(Device device, String updateType) {
		return getDeviceService().updateDevice(device, updateType);
	}

	@Override
	public Paging<Device> queryDeviceForPaging(Device device, int pageNum, int pageSize) {
		return getDeviceService().queryDeviceForPaging(device, pageNum, pageSize);
	}

	@Override
	public Device queryDeviceById(String deviceId) {
		return getDeviceService().queryDeviceById(deviceId);
	}

	@Override
	public int saveDevice(Device device) {
		return getDeviceService().saveDevice(device);
	}

	@Override
	public Paging<DeviceIp> queryDeviceIpForPaging(DeviceIp deviceIp, int pageNum, int pageSize) {
		return getDeviceIpService().queryDeviceIpForPaging(deviceIp, pageNum, pageSize);
	}

	@Override
	public DeviceIp queryDeviceIpByIp(String ipAddress,String districtId) {
		return getDeviceIpService().queryDeviceIpByIp(ipAddress,districtId);
	}

	@Override
	public int saveSubnetIps(DeviceIp deviceIp) {
		return getDeviceIpService().saveSubnetIps(deviceIp);
	}

	@Override
	public CallRecord getCallRecordById(String id) {
		return getCallRecordService().getCallRecordById(id);
	}

	@Override
	public Paging<CallRecord> queryCallRecordForPaging(CallRecord callRecord, int pageNum, int pageSize) {
		return getCallRecordService().queryCallRecordForPaging(callRecord, pageNum, pageSize);
	}

	@Override
	public void saveCallRecord(CallRecord callRecord) {
		getCallRecordService().saveCallRecord(callRecord);

	}

	@Override
	public void saveOrUpdateSystemLog(SystemLog systemLog) {
		if (systemLog.getLogId() != null) {
			getSystemLogService().update(systemLog);
		} else {
			getSystemLogService().save(systemLog);
		}
	}

	@Override
	public void saveOrUpdateOperationLog(OperationLog operationLog) {
		if (operationLog.getLogId() != null) {
			getOperationLogService().update(operationLog);
		} else {
			getOperationLogService().save(operationLog);
		}
	}

	@Override
	public Paging<SystemLog> querySystemLogForPaging(String logId, String operateUser, String operateTime, String menuCode, int pageNum,
			int pageSize) {
		return getSystemLogService().querySystemLogForPaging(logId, operateUser, operateTime, menuCode, pageNum, pageSize);
	}

	@Override
	public Paging<OperationLog> queryOperationLogForPaging(String logId, String operateUser, String operateTime, String menuCode,
			String operateResult, int pageNum, int pageSize) {
		return getOperationLogService().queryOperationLogForPaging(logId, operateUser, operateTime, menuCode, operateResult, pageNum,
				pageSize);
	}

	@Override
	public List<ChargeStatitics> statisticsCharge(ChargeData ob) {
		return getChargeDataService().statisticsCharge(ob);
	}

	@Override
	public void removeCallRecord(CallRecord callRecord) {
		getCallRecordService().removeCallRecord(callRecord);

	}

	@Override
	public List<CallRecord> queryCallRecords(CallRecord callRecord) {
		return getCallRecordService().queryCallRecords(callRecord);
	}

	@Override
	public List<InfoReceiver> queryInfoReceiverForList(InfoReceiver paramsOb) {
		return getInfoReceiverService().queryInfoReceiverForList(paramsOb);
	}

	@Override
	public Paging<InfoReceiver> queryInfoReceiverForPaging(InfoReceiver paramsOb, int pageNum, int pageSize) {
		return getInfoReceiverService().queryInfoReceiverForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateInfoReceiver(InfoReceiver entity) {
		getInfoReceiverService().updateInfoReceiver(entity);
	}

	@Override
	public void saveInfoReceiver(InfoReceiver entity) {
		getInfoReceiverService().saveInfoReceiver(entity);
	}

	@Override
	public InfoReceiver getInfoReceiver(String entityId) {
		return getInfoReceiverService().getInfoReceiver(entityId);
	}

	@Override
	public void DelInfoReceiver(InfoReceiver entity) {
		getInfoReceiverService().DelInfoReceiver(entity);
	}

	@Override
	public List<InfoSend> queryInfoSendForList(InfoSend paramsOb) {
		return getInfoSendService().queryInfoSendForList(paramsOb);
	}

	@Override
	public Paging<InfoSend> queryInfoSendForPaging(InfoSend paramsOb, int pageNum, int pageSize) {
		return getInfoSendService().queryInfoSendForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateInfoSend(InfoSend entity,String deviceTypeIds) {
		getInfoSendService().updateInfoSend(entity,deviceTypeIds);
	}

	@Override
	public void saveInfoSend(InfoSend entity,String deviceTypeIds) {
		getInfoSendService().saveInfoSend(entity,deviceTypeIds);
	}

	@Override
	public InfoSend getInfoSend(String entityId) {
		return getInfoSendService().getInfoSend(entityId);
	}

	@Override
	public void DelInfoSend(InfoSend entity) {
		getInfoSendService().DelInfoSend(entity);
	}

	@Override
	public void saveGateCardVisit(GateCardVisit gateCardVisit) {
		getGateCardVisitService().save(gateCardVisit);

	}

	@Override
	public void removeGateCardVisit(GateCardVisit gateCardVisit) {
		getGateCardVisitService().remove(gateCardVisit);

	}

	@Override
	public List<GateCardVisit> queryAllGateCardVisits(GateCardVisit gateCardVisit) {
		return getGateCardVisitService().queryAllGateCardVisits(gateCardVisit);
	}

	@Override
	public void updateGateCardVisit(GateCardVisit gateCardVisit) {
		getGateCardVisitService().update(gateCardVisit);

	}

	@Override
	public List<IdCardVisit> queryIdCardVisits(IdCardVisit idCardVisit) {
		return getIdCardVisitService().queryIdCardVisits(idCardVisit);
	}

	@Override
	public void saveOrUpdateIdCardVisit(IdCardVisit IdCardVisit) {
		getIdCardVisitService().save_update(IdCardVisit);

	}

	@Override
	public void removeIdCardVisit(IdCardVisit IdCardVisit) {
		getIdCardVisitService().remove(IdCardVisit);

	}

	@Override
	public List<Alarm> queryAlarms(Alarm alarm) {
		return getAlarmService().queryAlarms(alarm);
	}

	@Override
	public Alarm queryAlarmById(String alarmId) {
		return getAlarmService().get(alarmId);
	}

	@Override
	public void saveOrUpdateAlarm(Alarm alarm) {
		getAlarmService().save_update(alarm);
	}

	@Override
	public void removeAlarm(Alarm alarm) {
		getAlarmService().remove(alarm);
	}

	@Override
	public List<AlarmType> queryAlarmType(AlarmType alarmType) {
		return getAlarmTypeService().queryAlarmType(alarmType);
	}

	@Override
	public AlarmType queryAlarmTypeById(String alarmType) {
		return getAlarmTypeService().get(alarmType);
	}

	@Override
	public void saveOrUpdateAlarmType(AlarmType alarmType) {
		getAlarmTypeService().save_update(alarmType);
	}

	@Override
	public void removeAlarmType(AlarmType alarmType) {
		getAlarmTypeService().remove(alarmType);
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public ICellSizeInfoService getCellSizeInfoService() {
		return cellSizeInfoService;
	}

	public void setCellSizeInfoService(ICellSizeInfoService cellSizeInfoService) {
		this.cellSizeInfoService = cellSizeInfoService;
	}

	@Override
	public List<DistrictData> queryAreaData(InfoSend ob) {
		return this.infoSendService.queryAreaData(ob);
	}

	@Override
	public Paging<AddressBook> queryAddressBookForPaging(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		// TODO Auto-generated method stub
		return addressBookService.queryAddressBookForPaging(pageNum, pageSize,condition, orderString, values);
	}

	@Override
	public boolean saveOrUpdateAdressBook(AddressBook addressBook) {
		// TODO Auto-generated method stub
		return addressBookService.saveOrUpdateAdressBook(addressBook);
	}

	@Override
	public boolean deleteAdressBookById(String deviceNo,Long id) {
		// TODO Auto-generated method stub
		return addressBookService.deleteByAppId(deviceNo,id);
	}

	public IAddressBookService getAddressBookService() {
		return addressBookService;
	}

	public void setAddressBookService(IAddressBookService addressBookService) {
		this.addressBookService = addressBookService;
	}

	@Override
	public Paging<Complaint> queryPropertyComplaintForPaging(Complaint condition, Date endComplaintDate, Date endProcessingDate,
			int pageNum, int pageSize, boolean excludeUncommitted) {
		return complaintService.queryPropertyComplaintForPaging(condition, endComplaintDate, endProcessingDate, pageNum, pageSize,
				excludeUncommitted);
	}

	@Override
	public Paging<Complaint> queryOwnerComplaintForPaging(Complaint condition, Date endComplaintDate, Date endProcessingDate, int pageNum,
			int pageSize, boolean excludeUncommitted) {
		return complaintService.queryOwnerComplaintForPaging(condition, endComplaintDate, endProcessingDate, pageNum, pageSize,
				excludeUncommitted);
	}

	@Override
	public Complaint getComplaint(String id) {
		return complaintService.get(id);
	}

	@Override
	public void removeComplaint(String id) {
		complaintService.removeByIds(id);
	}

	@Override
	public void removeOwnerComplaint(String onlyValue) {
		complaintService.removeOwnerComplaint(onlyValue);
	}
	
	@Override
	public void replySuggestion(String id, String processingLoginName, String suggestion) {
		complaintService.replySuggestion(id, processingLoginName, suggestion);
	}

	@Override
	public void addPropertyComplaint(Complaint complaint) {
		complaintService.addPropertyComplaint(complaint);
	}

	@Override
	public void addOwnerComplaint(Complaint complaint) throws Exception  {
		complaintService.addOwnerComplaint(complaint);
	}

	@Override
	public void updateComplaint(String id, String title, String content, boolean submit) {
		complaintService.updateComplaint(id, title, content, submit);
	}

	@Override	
	public long queryComplaintCount(Complaint condition, boolean today) {
		return complaintService.queryComplaintCount(condition, today);
	}
	@Override
	public List<Complaint> queryRecentComplaint(Complaint condition, int quantity, boolean today) {
		return complaintService.queryRecentComplaint(condition, quantity, today);
	}

	@Override
	public Paging<Advertisement> queryAdsForPaging(Advertisement ad,
			int pageNum, int pageSize) {

		return getAdService().queryAdsForPaging(ad, pageNum, pageSize);
	}

	@Override
	public Advertisement getAdDetail(String adId) {
		return getAdService().get(adId);
	}

	@Override
	public void publishAd(String adId, String publishedUser) {
		getAdService().publishAd(adId, publishedUser);
	}

	@Override
	public void publishTimingAd(String adId, Date publishedTime, String publishedUser) {
		getAdService().publishTimingAd(adId, publishedTime, publishedUser);
	}

	@Override
	public void addAd(Advertisement ad) {
		getAdService().save(ad);
	}

	@Override
	public void updateAd(Advertisement ad) {
		getAdService().update(ad);
	}

	@Override
	public void removeAd(String adId) {
		getAdService().removeByIds(adId);
	}

	@Override
	public void stopAd(String adId) {
		getAdService().stopAd(adId);
	}

	@Override
	public void resumeAd(String adId) {
		getAdService().resumeAd(adId);
	}

	@Override
	public List<AdType> queryAllAdTypes() {
		return getAdTypeService().queryAllAdTypes();
	}

	@Override
	public List<AdSys> queryAllAdSystems() {
		return getAdSysService().queryAllAdSystems();
	}

	@Override
	public List<AdLocation> queryAdLocations(String sysCode) {
		return getAdLocationService().queryAdLocations(sysCode);
	}

	@Override
	public List<AdTarget> queryAdTargets(String adId) {
		return getAdTargetService().queryAdTargets(adId);
	}

	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}

	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}

	@Override
	public List<Scene> getSceneByDeviceNo(String deviceNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getSceneByDeviceNo(deviceNo);
	}

	@Override
	public Scene getSceneByDeviceNoAndMode(String deviceNo, String mode) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getSceneByDeviceNo(deviceNo, mode);
	}

	@Override
	public boolean sendSceneMonitorCommand(String deviceNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.sendSceneMonitorCommand(deviceNo);
	}

	@Override
	public Map<String, List<SceneDevice>> getSceneDeviceByDeviceNo(String deviceNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getSceneDeviceByDeviceNo(deviceNo);
	}

	@Override
	public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.querySceneDeviceByDeviceNo(deviceNo);
	}

	@Override
	public boolean sendSceneDeviceMonitorCommand(String deviceNo, List<SceneDevice> list, Long sceneId) {
		// TODO Auto-generated method stub
		return sceneMonitorService.sendSceneMonitorCommand(deviceNo, list, sceneId);
	}

	@Override
	public Paging<Alarm> queryAlarmForPaging(Alarm paramsOb, int pageNum, int pageSize) {
		return getAlarmService().queryAlarmForPaging(paramsOb, pageNum, pageSize);
	}

	@Override
	public void updateAlarm(Alarm entity) {
		getAlarmService().updateAlarm(entity);
	}

	@Override
	public void saveAlarm(Alarm entity) {
		getAlarmService().saveAlarm(entity);
	}

	@Override
	public Alarm getAlarm(String entityId) {
		return getAlarmService().getAlarm(entityId);
	}

	@Override
	public void DelAlarm(Alarm entity) {
		getAlarmService().DelAlarm(entity);
	}

	@Override
	public String getCompletePosition(String comId) {
		return getSystemGroupService().getCompletePosition(comId);
	}

	@Override
	public Paging<RssServer> queryRssServerForPaging(RssServer rssServer, int pageNum, int pageSize) {
		return getRssServerService().queryRssServerForPaging(rssServer, pageNum, pageSize);
	}

	@Override
	public RssServer queryRssServerById(Long rssId) {
		return getRssServerService().get(rssId);
	}

	@Override
	public void updateRssServer(RssServer rssServer) {
		getRssServerService().update(rssServer);

	}

	@Override
	public void removeRssServer(RssServer rssServer) {
		getRssServerService().remove(rssServer);
	}

	@Override
	public boolean remoteUnlock(String targetDeviceCode, String personalDeviceCode, String personalDevicePwd,String userType) {
		return monitorService.remoteUnlock(targetDeviceCode, personalDeviceCode, personalDevicePwd, userType);
	}

	@Override
	public List<Device> queryOwnerUnitDevice(String unitId, String roomId) {
		return getDeviceService().queryOwnerUnitDevice(unitId, roomId);
	}

	@Override
	public List<Device> queryPropertyDevice(String distrcitId) {
		return getDeviceService().queryPropertyDevice(distrcitId);
	}

	@Override
	public Paging<Weather> queryWeatherForPaging(Weather weather, int pageNum, int pageSize) {
		return getWeatherService().queryWeatherForPaging(weather, pageNum, pageSize);
	}

	@Override
	public void updateWeather(Weather weather) {
		getWeatherService().update(weather);
	}

	@Override
	public List<Device> queryDevicesList(List<String> districtIds, List<String> deviceTypes) {
		return getDeviceService().queryDevices(districtIds, deviceTypes);
	}

	@Override
	public Weather queryWeatherById(Long id) {
		return getWeatherService().get(id);
	}

	@Override
	public Device queryDeviceByRoomId(String roomId) {
		return getDeviceService().queryDeviceByRoomId(roomId);
	}

	@Override
	public Scene getIsUsedSceneByDeviceNo(String deviceNo,String sceneKind) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getIsUsedSceneByDeviceNo(deviceNo,sceneKind);
	}

	@Override
	public Scene getIsUsedSceneByRoomNo(String roomNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getIsUsedSceneByRoomNo(roomNo);
	}

	@Override
	public List<Scene> getSceneByRoomNo(String roomNo) {
		// TODO Auto-generated method stub
		return sceneMonitorService.getSceneByRoomNo(roomNo);
	}

	@Override
	public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo, Long sceneId, String actionWay,String sceneType) {
		// TODO Auto-generated method stub
		return sceneMonitorService.sendProtectionAndRemovalMonitorCommand(deviceNo, sceneId, actionWay,sceneType);
	}

	@Override
	public List<RequestRepair> queryRequestRepairForList(RequestRepair paramsOb) {
		return getRequestRepairService().queryRequestRepairForList(paramsOb);
	}
	@Override
	public Paging<RequestRepair> queryRequestRepairForPaging(RequestRepair paramsOb, int pageNum, int pageSize) {
		return getRequestRepairService().queryRequestRepairForPaging(paramsOb, pageNum, pageSize);
	}
	@Override
	public void updateRequestRepair(RequestRepair entity) {
		getRequestRepairService().updateRequestRepair(entity);
	}
	@Override
	public void saveRequestRepair(RequestRepair entity) {
		getRequestRepairService().saveRequestRepair(entity);
	}
	@Override
	public RequestRepair getRequestRepair(String entityId) {
		return getRequestRepairService().getRequestRepair(entityId);
	}
	@Override
	public void DelRequestRepair(RequestRepair entity) {
		getRequestRepairService().DelRequestRepair(entity);
	}
	@Override
	public List<InfoReceiver> queryInfoReceiverForIndex(InfoReceiver paramsOb) {
		return getInfoReceiverService().queryInfoReceiverForIndex(paramsOb);
	}
	@Override
	public List<InfoSend> queryInfoSendForIndex(InfoSend paramsOb) {
		return getInfoSendService().queryInfoSendForIndex(paramsOb);
	}
	@Override
	public Long getInfoCount(InfoSend paramsOb) {
		return getInfoSendService().getInfoCount(paramsOb);
	}
	@Override
	public Long getOwnerAlarmCount(Alarm paramsOb) {
		return getAlarmService().getOwnerAlarmCount(paramsOb);
	}
	@Override
	public Long getRequestRepairCount(RequestRepair paramsOb) {
		return getRequestRepairService().getRequestRepairCount(paramsOb);
	}
	@Override
	public boolean publishChargeInfo(Push pushOb, String roomId) {
		return getChargeDetailService().publishChargeInfo(pushOb, roomId);
	}
	@Override
	public Long deviceCount(String districtId, String status) {
		return getDeviceService().deviceCount(districtId, status);
	}

	@Override
	public Long callRecordCount(CallRecord callRecord) {
		return getCallRecordService().callRecordCount(callRecord);
	}

	@Override
	public Paging<LocalHdfs> queryLocalHdfsForPage(int pageNum, int pageSize,String condition,String orderString) {
		// TODO Auto-generated method stub
		return hdfsFileService.list(pageNum, pageSize,condition,orderString);
	}

	@Override
	public Paging<HdfsTask> queryHdfsTaskForPage(int pageNum, int pageSize,String condition,String orderString) {
		// TODO Auto-generated method stub
		return hdfsTaskService.list(pageNum, pageSize,condition,orderString);
	}

	public IHDFSTaskService getHdfsTaskService() {
		return hdfsTaskService;
	}

	public void setHdfsTaskService(IHDFSTaskService hdfsTaskService) {
		this.hdfsTaskService = hdfsTaskService;
	}

	public IHDFSFileService getHdfsFileService() {
		return hdfsFileService;
	}

	public void setHdfsFileService(IHDFSFileService hdfsFileService) {
		this.hdfsFileService = hdfsFileService;
	}
	@Override
	public Paging<Push> queryPushForPagingByKeyValue(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		// TODO Auto-generated method stub
		return pushService.queryPushForPaging(pageNum, pageSize, condition, orderString, values);
	}
	@Override
	public Paging<LocalHdfs> queryLocalHdfsForPageByKeyValue(int pageNum, int pageSize, String condition, String orderString,
			Object... values) {
		// TODO Auto-generated method stub
		return hdfsFileService.list(pageNum, pageSize, condition, orderString, values);
	}
	@Override
	public Paging<HdfsTask> queryHdfsTaskForPageByKeyValue(int pageNum, int pageSize, String condition, String orderString,
			Object... values) {
		// TODO Auto-generated method stub
		return hdfsTaskService.list(pageNum, pageSize, condition, orderString, values);
	}
	@Override
	public String getCityNameByDistrictId(String comId) {
		return getSystemGroupService().getCityNameByDistrictId(comId);
	}

	@Override
	public Paging<PushFinish> queryPushFinishForPagingByKeyValue(int pageNum, int pageSize, String condition, String orderString,
			Object... values) {
		// TODO Auto-generated method stub
		return pushFinishService.queryPushFinishForPaging(pageNum, pageSize, condition, orderString, values);
	}

	@Override
	public Paging<OperationLog> queryOperationLogPaging(OperationLog operationLog, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return getOperationLogService().queryOperationLogForPaging(operationLog, pageNum, pageSize);
	}
	@Override
	public WeatherReportData queryReportDataByCityName(String cityName) {
		// TODO Auto-generated method stub
		return getWeatherService().queryReportDataByCityName(cityName);
	}
	@Override
	public String getFileServerUrl() {
		
		return sysParamService.getFileServerUrl();
	}
	
	@Override
	public String getExternalFileServerUrl() {		
		return sysParamService.getExternalFileServerUrl();
	}
	
	@Override
	public Device queryDeviceByCode(String deviceCode) {
		return getDeviceService().queryDeviceByCode(deviceCode);
	}
	@Override
	public PropertyCompanyInfo getPropertyCompanyInfoByDeviceCode(String deviceCode) {
		// TODO Auto-generated method stub
		return propertyCompanyInfoService.getPropertyCompanyInfoByDeviceCode(deviceCode);
	}
	public Long getNoReadReceiverCount(InfoReceiver paramsOb) {
		return getInfoReceiverService().getNoReadReceiverCount(paramsOb);
	}
	public List<Device> queryFamilyDevice(Device device) {
		return getDeviceService().queryFamilyDevice(device);
	}
	@Override
	public List<AddressBook> listAllAddressBookByDeviceNo(String deviceNo) {
		// TODO Auto-generated method stub
		return addressBookService.listAllByDeviceNo(deviceNo);
	}
	@Override
	public List<Device> queryMonitorDevice(Device device) {
		// TODO Auto-generated method stub
		return deviceService.queryMonitorDevice(device);
	}
	
	@Override
	public boolean existCardNo(GateCard gateCard) {
		return getGateCardService().existCardNo(gateCard);
	}
	
	@Override
	public List<GateCard> queryGateCardByDeviceCode(String deviceCode) {
		return getGateCardService().queryByDeviceCode(deviceCode);
	}
	@Override
	public ChargeType queryChargeTypeByParams(ChargeType paramsOb) {
		return getChargeTypeService().queryChargeTypeByParams(paramsOb);
	}
	@Override
	public ChargeCalMode queryChargeCalModeByParams(ChargeCalMode paramsOb) {
		return getChargeCalModeService().queryChargeCalModeByParams(paramsOb);
	}
	@Override
	public ChargeMonetaryUnit queryChargeMonetaryUnitByParams(ChargeMonetaryUnit paramsOb) {
		return getChargeMonetaryUnitService().queryChargeMonetaryUnitByParams(paramsOb);
	}
	
	@Override
	public boolean existRoleName(String roleCode, String roleName) {
		return getPermissionsService().existRoleName(roleCode, roleName);
	}
	
	@Override
	public List<Room> queryRoomByDeviceNo(String deviceNo) {
		return cellHouseholdInfoService.queryRoomByDeviceNo(deviceNo);
	}

	@Override
	public boolean saveOrUpdateScene(List<Scene> sceneList) {
		// TODO Auto-generated method stub
		return sceneMonitorService.saveOrUpdateScene(sceneList);
	}

	@Override
	public HousingDistrictRegionInfo saveOrUpdateRegion(HousingDistrictRegionInfo region) {
		return housingDistrictRegionInfoService.saveOrUpdateRegion(region);
	}
	@Override
	public boolean newRegionCodeNotRepeat(String regionCode, String districtId) {
		return housingDistrictRegionInfoService.newCodeNotRepeat(regionCode, districtId);
	}

	@Override
	public boolean saveOrUpdateSceneDevice(List<SceneDevice> sceneDeviceList) {
		// TODO Auto-generated method stub
		return sceneMonitorService.saveOrUpdateSceneDevice(sceneDeviceList);
	}

	@Override
	public boolean updateRegionCodeNotRepeat(String regionId, String regionCode, String districtId) {
		return housingDistrictRegionInfoService.updateCodeNotRepeat(regionId, regionCode, districtId);
	}
	
	@Override
	public List<Menu> queryMenus(String roleCode) {
		return getPermissionsService().queryMenus(roleCode);
	}
	@Override
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId) {
		return regionBuildingInfoService.newBuildingCodeNotRepeat(buildingCode, regionId);
	}
	@Override
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId) {
		return regionBuildingInfoService.updateBuildingCodeNotRepeat(buildingId, buildingCode, regionId);
	}
	@Override
	public boolean newCellCodeNotRepeat(String cellCode, String buildingId) {
		return buildingCellInfoService.newCodeNotRepeat(cellCode, buildingId);
	}
	@Override
	public boolean updateCellCodeNotRepeat(String cellId, String cellCode, String buildingId) {
		return buildingCellInfoService.updateCodeNotRepeat(cellId, cellCode, buildingId);
	}

	@Override
	public boolean deleteSceneByDeviceNoAndSceneId(String deviceNo, String sceneId) {
		// TODO Auto-generated method stub
		return sceneMonitorService.deleteSceneByDeviceNoAndSceneId(deviceNo, sceneId);
	}
	@Override
	public boolean deleteSceneDeviceByDeviceNoAndDeviceId(String deviceNo, String deviceId) {
		// TODO Auto-generated method stub
		return sceneMonitorService.deleteSceneDeviceByDeviceNoAndDeviceId(deviceNo, deviceId);
	}

	@Override
	public boolean newHouseCodeNotRepeat(String householdCode, String cellId) {
		return cellHouseholdInfoService.newCodeNotRepeat(householdCode, cellId);
	}
	@Override
	public boolean updateHouseCodeNotRepeat(String householdId, String householdCode, String cellId) {
		return cellHouseholdInfoService.updateCodeNotRepeat(householdId, householdCode, cellId);
	}

	@Override
	public boolean hasHouse(String cellId) {
		return buildingCellInfoService.hasHouse(cellId);
	}
	@Override
	public boolean hasCell(String buildingId) {
		return regionBuildingInfoService.hasCell(buildingId);
	}
	@Override
	public boolean hasBuilding(String regionId) {
		return housingDistrictRegionInfoService.hasBuilding(regionId);
	}
	@Override
	public boolean hasRegion(String districtId) {
		return housingDistrictInfoService.hasRegion(districtId);
	}
	@Override
	public List<Complaint> queryComplaintByDeviceCode(String deviceCode) {
		return complaintService.queryComplaintByDeviceCode(deviceCode);
	}
	@Override
	public boolean isHaveDevice(String roomId) {
		return getDeviceService().isHaveDevice(roomId);
	}

	public IClientLogService getClientLogService() {
		return clientLogService;
	}
	public void setClientLogService(IClientLogService clientLogService) {
		this.clientLogService = clientLogService;
	}
	@Override
	public Paging<ClientLog> queryClientLogForPaging(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		// TODO Auto-generated method stub
		return clientLogService.queryClientLogForPaging(pageNum, pageSize, condition, orderString, values);
	}
	@Override
	public ClientLog queryClientLogById(Long id) {
		// TODO Auto-generated method stub
		return clientLogService.queryClientLogById(id);
	}

    


	
	@Override
	public boolean existRegionName(String districtId, String regionId,
			String regionName) {
		return housingDistrictRegionInfoService.existRegionName(
				districtId, regionId, regionName);
	}
	
	@Override
	public boolean existBuildingName(String regionId, String buildingId,
			String buildingName) {
		return regionBuildingInfoService.existBuildingName(
				regionId, buildingId, buildingName);
	}
	
	@Override
	public boolean existCellName(String buildingId, String cellId,
			String cellName) {
		return buildingCellInfoService.existCellName(
				buildingId, cellId, cellName);
	}
	
	@Override
	public boolean existHouseName(String cellId, String houseId,
			String houseName) {
		return cellHouseholdInfoService.existHouseName(
				cellId, houseId, houseName);
	}
	
	@Override
	public List<String> queryDistrictIds(List<String> groupIds) {
		return housingDistrictInfoService.queryDistrictIds(groupIds);
	}

	@Override
	public void saveDevicePassword(DevicePassword devicePassword) {
		devicePasswordService.save_update(devicePassword);
		
	}
	@Override
	public void delDevicePassword(DevicePassword devicePassword) {
		devicePasswordService.remove(devicePassword);
		
	}
	@Override
	public DevicePassword queryDevicePasswordById(Long id) {
		return devicePasswordService.get(id);
	}

	@Override
	public boolean existIdCardForSaUser(String userId, String idCard) {
		return saUserService.existIdCard(userId, idCard);
	}
	
	@Override
	public boolean existIdCardForPaUser(String userId, String idCard) {
		return paUserService.existIdCard(userId, idCard);
	}
	
	@Override
	public boolean existIdCardForOwnerUser(String userId, String idCard) {
		return ownerUserService.existIdCard(userId, idCard);
	}

	@Override
	public List<DevicePassword> queryDevicePassword(String deviceNo) {
		return devicePasswordService.queryDevicePassword(deviceNo);
	}

	@Override
	public List<ChargeCalUnit> queryChargeCalUnitForList(ChargeCalUnit paramsOb) {
		return chargeCalUnitService.queryChargeCalUnitForList(paramsOb);
	}
	@Override
	public Paging<ChargeCalUnit> queryChargeCalUnitForPaging(ChargeCalUnit paramsOb, int pageNum, int pageSize) {
		return chargeCalUnitService.queryChargeCalUnitForPaging(paramsOb, pageNum, pageSize);
	}
	@Override
	public void updateChargeCalUnit(ChargeCalUnit entity) {
		chargeCalUnitService.updateChargeCalUnit(entity);
	}
	@Override
	public void saveChargeCalUnit(ChargeCalUnit entity) {
		chargeCalUnitService.saveChargeCalUnit(entity);
	}
	@Override
	public ChargeCalUnit getChargeCalUnit(String entityId) {
		return chargeCalUnitService.getChargeCalUnit(entityId);
	}
	@Override
	public void DelChargeCalUnit(ChargeCalUnit entity) {
		chargeCalUnitService.DelChargeCalUnit(entity);
	}
	@Override
	public ChargeCalUnit queryChargeCalUnitByParams(ChargeCalUnit paramsOb) {
		return chargeCalUnitService.queryChargeCalUnitByParams(paramsOb);
	}
	@Override
	public List<FileUploadLog> queryFileUploadLogForList(FileUploadLog paramsOb) {
		return fileUploadLogService.queryFileUploadLogForList(paramsOb);
	}
	@Override
	public Paging<FileUploadLog> queryFileUploadLogForPaging(FileUploadLog paramsOb, int pageNum, int pageSize) {
		return fileUploadLogService.queryFileUploadLogForPaging(paramsOb, pageNum, pageSize);
	}
	@Override
	public FileUploadLog getFileUploadLog(String entityId) {
		return fileUploadLogService.getFileUploadLog(entityId);
	}
	@Override
	public LocalHdfs queryLocalHdfsById(Long id) {
		
		return hdfsFileService.get(id);
	}
	@Override
	public HdfsTask queryHdfsTaskById(Long id) {
		// TODO Auto-generated method stub
		return hdfsTaskService.findById(id);
	}
	@Override
	public List<Device> queryOwnerUnlockDevice(String unitId, String districtId) {
		return deviceService.queryOwnerUnlockDevice(unitId, districtId);
	}
	@Override
	public List<Device> queryGateCardDevices(Device device) {
		return deviceService.queryGateCardDevices(device);
	}
	@Override
	public List<DiviceRegeditLog> queryDiviceRegeditLogForList(DiviceRegeditLog paramsOb) {
		return diviceRegeditLogService.queryDiviceRegeditLogForList(paramsOb);
	}
	@Override
	public Paging<DiviceRegeditLog> queryDiviceRegeditLogForPaging(DiviceRegeditLog paramsOb, int pageNum, int pageSize) {
		return diviceRegeditLogService.queryDiviceRegeditLogForPaging(paramsOb, pageNum, pageSize);
	}
	@Override
	public void saveDiviceRegeditLog(DiviceRegeditLog entity) {
		diviceRegeditLogService.saveDiviceRegeditLog(entity);
	}
	@Override
	public DiviceRegeditLog getDiviceRegeditLog(String entityId) {
		return diviceRegeditLogService.getDiviceRegeditLog(entityId);
	}
	@Override
	public void saveAndPushDevicePassword(DevicePassword devicePassword) {
		devicePasswordService.saveAndPushDevicePassword(devicePassword);
		
	}
	@Override
	public Device queryDeviceByRoomNo(String districtNo, String regionNo, String buildingNo, String unitNo, String roomNo) {
		return deviceService.queryDeviceByRoomNo(districtNo, regionNo, buildingNo, unitNo, roomNo);
	}
	@Override
	public GateCard queryGateCardByCardNo(String districtId,String cardNo) {
		return gateCardService.queryGateCardByCardNo(districtId,cardNo);
	}
	@Override
	public List<DevicePassword> queryPasswordByTargetDeviceNo(String targetDeviceNo) {
		return devicePasswordService.queryDevicePasswordByTargetDeviceNo(targetDeviceNo);
	}
	@Override
	public List<InfoReceiverDevice> queryInfoReceiverDeviceForList(InfoReceiverDevice paramsOb) {
		return infoReceiverDeviceService.queryInfoReceiverDeviceForList(paramsOb);
	}
	@Override
	public Paging<InfoReceiverDevice> queryInfoReceiverDeviceForPaging(InfoReceiverDevice paramsOb, int pageNum, int pageSize) {
		return infoReceiverDeviceService.queryInfoReceiverDeviceForPaging(paramsOb, pageNum, pageSize);
	}
	@Override
	public void updateInfoReceiverDevice(InfoReceiverDevice entity) {
		infoReceiverDeviceService.updateInfoReceiverDevice(entity);
	}
	@Override
	public void saveInfoReceiverDevice(InfoReceiverDevice entity) {
		infoReceiverDeviceService.saveInfoReceiverDevice(entity);
	}
	@Override
	public InfoReceiverDevice getInfoReceiverDevice(String entityId) {
		return infoReceiverDeviceService.getInfoReceiverDevice(entityId);
	}
	@Override
	public void DelInfoReceiverDevice(InfoReceiverDevice entity) {
		infoReceiverDeviceService.DelInfoReceiverDevice(entity);
	}
	@Override
	public boolean removeDeviceById(String deviceId) {
		return deviceService.deleteDeviceById(deviceId);
	}
	@Override
	public boolean isDeviceOnline(String deviceId) {
		return deviceService.isDeviceOnline(deviceId);
	}
	@Override
	public List<Scene> getScenes(String deviceNo, String sceneKind) {
		return sceneMonitorService.getScenes(deviceNo, sceneKind);
	}
	@Override
	public AppDataLog saveOrUpdateAppDataLog(AppDataLog appDataLog) {
		return appDataLogService.save_update(appDataLog);
	}
	@Override
	public Paging<AppDataLog> queryAppDataLogForPaging(AppDataLog appDataLog, int pageNum, int pageSize) {
		return appDataLogService.queryAppDataLogForPaging(appDataLog, pageNum, pageSize);
	}
	@Override
	public AppDataLog getAppDataLogById(Integer id) {
		return appDataLogService.get(id);
	}
	@Override
	public List<SceneDevice> querySceneDeviceByDeviceNoAndSceneId(String deviceNo, Long sceneId) {
		return sceneMonitorService.querySceneDeviceByDeviceNo(deviceNo, sceneId);
	}
	@Override
	public String getAppDownloadAbsoluteUrl() {
		// TODO Auto-generated method stub
		return sysParamService.getAppDownloadAbsoluteUrl();
	}
	@Override
	public String getWebDownloadAbsoluteUrl() {
		// TODO Auto-generated method stub
		return sysParamService.getWebDownloadAbsoluteUrl();
	}
	@Override
	public boolean sendGetSceneCommand(String deviceNo, String sceneKind) {
		return sceneMonitorService.sendGetSceneCommand(deviceNo, sceneKind);
	}
	@Override
	public List<Component> queryComponents(Component component) {
		return componentService.queryComponents(component);
	}
	@Override
	public void saveComponent(Component component) {
		componentService.save(component);
	}
	@Override
	public void deleteComponent(String componentId) {
		componentService.deleteById(componentId);
	}
	@Override
	public void updateComponent(Component component) {
		componentService.update(component);
	}
	@Override
	public void savePage(PageLayout page) {
		pageService.save(page);
	}
	@Override
	public void deletePage(PageLayout page) {
		// TODO Auto-generated method stub
		pageService.remove(page);
	}
	@Override
	public void updatePage(PageLayout page) {
		pageService.update(page);
	}
	@Override
	public List<PageLayout> queryPages(PageLayout page) {
		return pageService.queryAllPages(page);
	}
	@Override
	public Paging<PageLayout> queryPageForPaging(PageLayout page, int pageNum, int pageSize) {
		return pageService.queryPageForPaging(page, pageNum, pageSize);
	}
	@Override
	public Paging<Component> queryComponentForPaging(Component component,
			int pageNum, int pageSize) {
		return componentService.queryComponentForPaging(component, pageNum, pageSize);
	}
	@Override
	public PageLayout queryPageById(String entityId) {
		return pageService.get(entityId);
	}
	@Override
	public Component queryComponentById(String entityId) {
		return componentService.get(entityId);
	}
	@Override
	public void savePageContent(PageContent pageContent) {
		pageContentService.save(pageContent);
	}
	@Override
	public void deletePageContentById(String updateId) {
		pageContentService.removeByIds(updateId);
	}
	@Override
	public void deleteAllPageContent(List<PageContent> pageContents) {
		pageContentService.removeAll(pageContents);
	}
	@Override
	public List<PageContent> queryPageContent(PageContent pageContent) {
		return pageContentService.queryAllPageContents(pageContent);
	}
	public IPageContentService getPageContentService() {
		return pageContentService;
	}
	public void setPageContentService(IPageContentService pageContentService) {
		this.pageContentService = pageContentService;
	}
	@Override
	public List<SceneDevice> querySceneDevices(SceneDevice sceneDevice) {
		return sceneMonitorService.querySceneDevices(sceneDevice);
	}
	@Override
	public void removePropertyCompanyInfo(PropertyCompanyInfo propertyCompany) {
		propertyCompanyInfoService.remove(propertyCompany);
	}
	
}
