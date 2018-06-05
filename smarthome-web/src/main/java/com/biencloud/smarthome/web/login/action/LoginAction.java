package com.biencloud.smarthome.web.login.action;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.gate.service.IGateCardService;
import com.biencloud.smarthome.web.gate.service.IIdCardVisitService;
import com.biencloud.smarthome.web.gate.vo.GateCardVO;
import com.biencloud.smarthome.web.gate.vo.IdCardVisitVO;
import com.biencloud.smarthome.web.hdfs.service.IHDFSFileService;
import com.biencloud.smarthome.web.hdfs.vo.LocalHdfsVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.service.IInfoSendService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;
import com.biencloud.smarthome.web.log.service.IOperationLogService;
import com.biencloud.smarthome.web.log.vo.OperationLogVO;
import com.biencloud.smarthome.web.login.service.ILoginService;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.login.vo.OwnerMainPageVO;
import com.biencloud.smarthome.web.login.vo.PaMainPageVO;
import com.biencloud.smarthome.web.login.vo.SaMainPageVO;
import com.biencloud.smarthome.web.menu.service.IMenuService;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.push.service.IPushService;
import com.biencloud.smarthome.web.push.vo.PushVO;
import com.biencloud.smarthome.web.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO;
import com.biencloud.smarthome.web.rss.service.IWeatherService;
import com.biencloud.smarthome.web.rss.vo.WeatherReportDataVO;
import com.biencloud.smarthome.web.softwareupgrade.service.ISoftwareUpgradeService;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.service.IPaUserService;
import com.biencloud.smarthome.web.user.service.ISaUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.user.vo.SaUserVO;
import com.biencloud.smarthome.web.ad.service.IAdService;
import com.biencloud.smarthome.web.ad.vo.AdLocationVO;
import com.biencloud.smarthome.web.ad.vo.AdSysVO;
import com.biencloud.smarthome.web.ad.vo.AdvertisementVO;
import com.biencloud.smarthome.web.alarm.service.IAlarmService;
import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeDataService;
import com.biencloud.smarthome.web.charge.service.IChargeDetailService;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.CryptoUtils;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;
import com.biencloud.smarthome.web.device.service.ICallRecordService;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.CallRecordVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 类名称：LoginAction 类描述： 登陆Action类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-2 上午10:55:57
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction<LoginVO> {

	// 选择的语言
	private String selectedLanguage;

	private ILoginService loginService;
	private IPermissionsService permissionsService;
	private IComplaintService complaintService;
	private LoginVO login;
	private IInfoSendService infoSendService;
	private IChargeDataService chargeDataService;
	private IAlarmService alarmService;
	private IRequestRepairService requestRepairService;
	private IAdService adService;
	private IDeviceService deviceService;
	private ICallRecordService callRecordService;
	private IOwnerUserService ownerUserService;
	private ISaUserService saUserService;
	private IPaUserService paUserService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	private IWeatherService weatherService;
	private IInfoReceiverService infoReceiverService;
	private IChargeDetailService chargeDetailService;
	private IOperationLogService operationLogService;
	private ISoftwareUpgradeService softwareUpgradeService;
	private IHDFSFileService hdfsFileService;
	private IPushService pushService;
	private ISystemGroupService systemGroupService;
	private IGateCardService gateCardService;
	private IIdCardVisitService idCardVisitService;
	// json对象
	private String verificationCode;
	private boolean validCodeOpenFlag;

	private String errorCode;
	
	private String loginId;
	private String currLoginName;
	private String currPass;
	private String newPass;
	private String confirmNewPass;
	private String status;
	private String currUserType;
	private SaMainPageVO saMainPageVO; //系统管理员页面数据对象
	private PaMainPageVO paMainPageVO;	//物业管理员页面数据对象
	private OwnerMainPageVO ownerMainPageVO;	//业主主页面数据对象
	private boolean promptFlag;
	private boolean existLoginName;//登录帐号是否存在
	private List<Object> itemTab; //主页面点击tab后显示的的
	private List<String> tabTops; //主页面显示的上方的tab的所有id
	private List<String> tabBottoms; //主页面显示的下方的tab的所有id
	private String tabId;
	private List<AdvertisementVO> ads;
	private String tabIdTop;
	private String tabIdBottom;
	private IMenuService menuService;
	private ISysParamService sysParamService;
	private String version;
	
	private boolean successFlag;
	
	private static final Map<String, String> MSG_MAPPINGS;
	static{
		MSG_MAPPINGS = new HashMap<String, String>();
		MSG_MAPPINGS.put(Constants.ACC_ERROR, "login.action.useraccount.no.exist");
		MSG_MAPPINGS.put(Constants.PASS_ERROR, "login.action.input.wrong.psw");
		MSG_MAPPINGS.put(Constants.ACC_DISABLED, "login.action.useraccount.forbid");
		MSG_MAPPINGS.put(Constants.ACC_LOCKED, "login.action.useraccount.locked");
		MSG_MAPPINGS.put(Constants.ACC_REMOVED, "login.action.useraccount.removed");
		MSG_MAPPINGS.put(Constants.SESSION_TIMEOUT, "login.action.session.timeout");
		MSG_MAPPINGS.put(Constants.LOGIN_SYNC, "login.action.sync");
	}
	
	/**
	 * 重置用户密码。
	 * @author kouy
	 * @since 1.0 2012-5-15
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String resetPass() throws Exception {
		getLoginService().resetPassword(currLoginName);
		setPromptFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 判断登录帐号是否存在。
	 * @return
	 * @throws Exception
	 */
	public String existLoginName() throws Exception {
		if(getLoginService().getLoginByLoginName(currLoginName) != null)
			setExistLoginName(true);
		return SUCCESS;
	}
	
	/**
	 * 修改用户密码。
	 * @author kouy
	 * @since 1.0 2012-6-19
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePassInput() throws Exception {		
		return SUCCESS;
	}
	
	/**
	 * 保存修改用户密码。
	 * @author kouy
	 * @since 1.0 2012-6-19
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePass() throws Exception {
		setPromptFlag(true);		
		if(!CryptoUtils.validateByMD5(getLoginVO().getPassword(), currPass)){
			addActionMessage(getText("login.action.error.currpwd"));
			return ERROR;
		}
		
		getLoginService().updatePassword(getLoginName(), newPass);
		//用新密码替换会话中的密码
		getLoginVO().setPassword(CryptoUtils.encodeByMD5(newPass));
		addActionMessage(getText("common.updatepass.success"));
		setSuccessFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 启用或禁用用户。
	 * @author kouy
	 * @since 1.0 2012-5-15
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String enableOrDisable() throws Exception {		
		getLoginService().updateStatus(loginId, status);
		setPromptFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 显示系统主页。
	 * @author kouy
	 * @since 1.0 2012-5-24
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String index()  throws Exception {
		List<MenuVO> menus = getPermissionsService().queryMenusByRole(
				getRoleCode());
		
		if(menus != null){
			//菜单排序
			Collections.sort(menus, new Comparator<MenuVO>() {
				@Override
				public int compare(MenuVO m1, MenuVO m2) {
					return (m1.getMenuOrder() - m2.getMenuOrder());
				}
			});
			
			for (MenuVO menuVO : menus)
				menuVO.setMenuName(getText(menuVO.getMenuCode()));
		}
				
		setSessionAttribute("permissions", menus);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 跳转到head.jsp页面
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-5 上午10:33:15
	 * @return
	 */
	public String forwardHeadJsp() throws Exception {
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 跳转到main.jsp页面
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-5 上午10:33:37
	 * @return
	 */
	public String forwardMainJsp() throws Exception {
		//获得广告信息,仅查询投放到web端的已发布的广告
		PagingVO<AdvertisementVO> page = new PagingVO<AdvertisementVO>();
		AdvertisementVO ad = new AdvertisementVO();
		Date currDate = new Date();
		ad.setAdBeginTime(currDate);
		ad.setAdEndTime(currDate);
		AdSysVO adSys = new AdSysVO();
		AdLocationVO adLocation = new AdLocationVO();
		adLocation.setLocationCode(Constants.AD_LOCATION_CODE_MAIN_RIGHT);
		adSys.setSysCode(Constants.AD_WEB_SYS);
		adLocation.setAdSys(adSys);
		ad.setAdLocation(adLocation);
		ad.setStatus(Constants.AD_RUNNING);
		page = getAdService().queryAdsForPaging(ad, page.getPageNum(), Constants.SELECT_COUNT_FOR_INDEX);
		List<AdvertisementVO> list = page.getResults();
		setAds(list);
		//03系统管理员
		if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(super.getUserType())){
			return "main_sa";
		}
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())){
			return "main_pa";
		}
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType())){
			return "main_owner";
		}

		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 语言选择
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-3 上午9:33:17
	 * @return
	 */
	public String changeLocale() throws Exception {
		Locale locale = Locale.CHINESE;
		if ("en".equals(selectedLanguage)) {
			locale = Locale.US;
		}
		
		ActionContext.getContext().setLocale(locale);
		setSessionAttribute("WW_TRANS_I18N_LOCALE", locale);
		setSessionAttribute("language", selectedLanguage);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 登陆
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-2 上午10:58:04
	 * @return
	 */
	public String login() throws Exception {		
		clearErrorsAndMessages();
		
		if(!getLoginService().checkValidationCode(login.getValidCode(), 
				(String)getSessionAttribute("randomCode"))){
			addActionError(getText("login.action.error.validcode"));
			setPromptFlag(true);
			return ERROR;
		}
		
		LoginVO retLogin = getLoginService().login(login);	
		String result = retLogin.getResult();
		if(!"00".equals(result)){
			addActionError(getText(MSG_MAPPINGS.get(result)));
			setPromptFlag(true);
			return ERROR;
		}
			
		retLogin.setIp(getIp());
		updateOnlineFlag(retLogin.getLoginName(), Constants.LOGIN_ONLINE, retLogin.getIp());
		setSessionAttribute(Constants.KEY_LOGIN_SESSION, retLogin);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 退出
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-2 上午10:58:34
	 * @return
	 */
	public String exit() throws Exception {	
		LoginVO login = getLoginVO();
		if(login != null && StringUtils.isNotBlank(login.getLoginName()))
			updateOnlineFlag(login.getLoginName(), Constants.LOGIN_OFFLINE, login.getIp());
		
		if(!CollectionUtils.isEmpty(getSession())){
			getSession().clear();
		}		
		
		login = null;
		if(StringUtils.isNotBlank(errorCode) 
				&& MSG_MAPPINGS.containsKey(errorCode)){
			addActionError(getText(MSG_MAPPINGS.get(errorCode)));
			setPromptFlag(true);
		}
		
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 获得验证码
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-5 上午10:21:30
	 * @return
	 */
	public String getValidationCode() throws Exception {
		verificationCode = loginService.getRandomCode();
		validCodeOpenFlag = getLoginService().isValidCodeOpenFlag();
		getSession().put("randomCode", verificationCode);
		return SUCCESS;
	}
	
	/**
	 * 获取是否验证校验码的标志。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String getValidCodeFlag() throws Exception {
		validCodeOpenFlag = getLoginService().isValidCodeOpenFlag();
		return SUCCESS;
	}
	
	/**
	 * 获取图形验证码。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String getValidCode() throws Exception {
		HttpServletResponse response = getResponse();
		
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		response.setContentType("image/jpeg");
		
		Properties props = new Properties();
		props.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		props.put("kaptcha.textproducer.char.string", "bcdefgkmnpstuwxyBCDEFGKMNPSTUWXY234678");
		props.put("kaptcha.textproducer.char.space", "4");
		props.put("kaptcha.textproducer.font.size", "25");
		props.put("kaptcha.image.width", "120");
		props.put("kaptcha.image.height", "36");		
		Config config = new Config(props);
		Producer producer = config.getProducerImpl();
		String validCode = producer.createText();		
		
		getSession().put("randomCode", validCode);
		
		BufferedImage bi = producer.createImage(validCode);
		ImageIO.write(bi, "jpg", response.getOutputStream());
		
		return null;
	}
	
	/**
	 * 
	 * 方法的描述: 获取主页面最初显示的tab页面的信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-8 下午2:59:39
	 * @return
	 */
	public String getMainTab() throws Exception{
		List<String> tabTopList = loginService.getTabs(super.getUserType(), super.getRoleCode(), "top");
		List<String> tabBottomList = loginService.getTabs(super.getUserType(), super.getRoleCode(), "bottom");
		setTabTops(tabTopList);
		setTabBottoms(tabBottomList);
		//getLeftPageValue();
		return SUCCESS;
	}
	/**
	 * 
	 * 方法的描述: 获得单个tab的信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-11 上午11:00:35
	 * @return
	 */
	public String getItemTabValue() throws Exception{
			//如果是系统管理员登录，显示系统管理员页面tab的信息
		if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(super.getUserType()))
			setItemTab(getSaMainPageTabList(tabId));
			//如果是物业登录，显示物业页面tab的信息
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType()))
			setItemTab(getPaMainPageTabList(tabId));
			//如果是业主登录，显示业主页面tab的信息
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType()))
			setItemTab(getOwnerMainPageTabList(tabId));
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 首页页面左边显示的统计数据模块
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:44:47
	 * @return
	 */
	public String getLeftPageValue() throws Exception{
			//系统管理员页面左边统计数据
		if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(super.getUserType())){
			SaMainPageVO saPage = new SaMainPageVO();
			ComplaintVo cv = new ComplaintVo();
			cv.setType(Constants.COMPLAINT_SYSTEM);
			SaUserVO saUser = saUserService.getSaUserDetail(super.getUserId());
			Long complaintCount = complaintService.queryComplaintCount(cv, false);
			Long districtCount = housingDistrictInfoService.getDistrictCount();
			Long liverCount = ownerUserService.countUsers(new OwnerUserVO());
			Long onlineDeviceCount = deviceService.deviceCount(null, Constants.DEVICE_ONLINE);
			Long onlineUserCount = loginService.countUsersByOnlineFlag(Constants.LOGIN_ONLINE);
			Long infoCount = infoSendService.getInfoCount(super.getUserType(), null, null);
			Long goodsCount = new Long(0);
			saPage.setComplaintCount(complaintCount);
			saPage.setDistrictCount(districtCount);
			saPage.setLiverCount(liverCount);
			saPage.setOnlineDeviceCount(onlineDeviceCount);
			saPage.setOnlineUserCount(onlineUserCount);
			saPage.setGoodsCount(goodsCount);
			saPage.setSaUserVO(saUser);
			saPage.setTodayInfoCount(infoCount);
			setSaMainPageVO(saPage);
			return "sa_page";
		}
			//物业管理员页面左边统计数据
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())){
			PaMainPageVO paPage = new PaMainPageVO();
			OwnerUserVO ownerUserVO = new OwnerUserVO();
			SaUserVO saUserVO = new SaUserVO();
			saUserVO.setRoleCode("P0002");
			ownerUserVO.setDistrictId(super.getDistrictId());
			SaUserVO saUser = new SaUserVO();
			saUser = saUserService.getSaUserDetail("1");
//			PagingVO<SaUserVO> paging = saUserService.querySaUsersForPaging(saUserVO, 1, 5); 
//			if(paging != null && paging.getResults() != null && paging.getResults().size() == 0){
//				saUser = paging.getResults().get(0);
//			}
			Long liverCount = ownerUserService.countUsers(ownerUserVO);
			ChargeStatiticsVO chargeStatiticsVO = chargeDetailService.statisticsChargeForIndex(super.getUserType(), null, super.getDistrictId());
			double chargeFinish = 0;
			double chargeTotal = 0;
			if(chargeStatiticsVO != null){
				DecimalFormat df=new DecimalFormat("#.00");//保留两位小数
				chargeFinish = Double.parseDouble(df.format(chargeStatiticsVO.getAcTotalMoney()));
				chargeTotal =  Double.parseDouble(df.format(chargeStatiticsVO.getFeeTotalMoney()));
			}
			Long deviceCount = deviceService.deviceCount(super.getDistrictId(), null);
			Long goodsCount = new Long(0);
			paPage.setLiverCount(liverCount);
			paPage.setChargeFinish(chargeFinish);
			paPage.setChargeTotal(chargeTotal);
			paPage.setDeviceCount(deviceCount);
			paPage.setGoodsCount(goodsCount);
			ComplaintVo complaint = new ComplaintVo();
			//complaint.setComplaintLoginName(super.getLoginName());
			complaint.setType(Constants.COMPLAINT_OWNER);
			complaint.setDistrictId(super.getDistrictId());
			paPage.setTodayCcomplaintCount(complaintService.queryComplaintCount(complaint, true));
			paPage.setSaUserVO(saUser);
			paPage.setWeatherReportData(getWeatherData());
			setPaMainPageVO(paPage);
			return "pa_page";
		}
			//业主页面左边统计数据
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType())){
			OwnerMainPageVO ownerPage = new OwnerMainPageVO();
			CallRecordVO callRecord = new CallRecordVO();
			DeviceVO deviceVO = new DeviceVO();
			CellHouseholdInfoVo cellHouseholdInfo = new CellHouseholdInfoVo();
			OwnerUserVO owner = new OwnerUserVO();
			//将userId传到里面
			owner.setUserId(super.getUserId());
			cellHouseholdInfo.setOwner(owner);
			deviceVO.setCellHouseholdInfo(cellHouseholdInfo);
			callRecord.setDevice(deviceVO);
			callRecord.setCallType(Constants.CALL_TYPE_MESSAGE);
			//赋值
			PaUserVO paUser = paUserService.getPaUserDetail("1");
			
			//paUser.setUserName("Cocoa001");
			//paUser.setOfficePhone("075512121212");
			Long infoCount = infoSendService.getInfoCount(super.getUserType(), super.getUserId(), super.getDistrictId());
			Long alarmCount = alarmService.getOwnerAlarmCount(super.getUserType(), super.getUserId(), super.getDistrictId());
			Long goodsCount = new Long(0);
			Long repairCount = requestRepairService.getRequestRepairCount(super.getUserType(), super.getUserId(), super.getDistrictId());
			Long visiorCount = callRecordService.recordCount(callRecord);
			ownerPage.setInfoCount(infoCount);
			ownerPage.setAlarmCount(alarmCount);
			ownerPage.setGoodsCount(goodsCount);
			ownerPage.setRepairCount(repairCount);
			ownerPage.setVisitorCount(visiorCount);
			ComplaintVo complaint = new ComplaintVo();
			complaint.setComplaintLoginName(super.getLoginName());
			ownerPage.setComplaintCount(complaintService.queryComplaintCount(complaint, false));
			ownerPage.setPaUser(paUser);
			ownerPage.setWeatherReportData(getWeatherData());
			setOwnerMainPageVO(ownerPage);
			return "owner_page";
		}
		return null;
	}
	
	/**
	 * 
	 * 方法的描述: 物业主页面tab显示信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:46:17
	 * @param tab
	 * @return
	 */
	public List<Object> getPaMainPageTabList(String tab) throws Exception{
		List<Object> list = new ArrayList<Object>();
		//未审核信息
		if("tab0".equals(tab)){
			List<InfoSendVO> infoSends = infoSendService.queryInfoSendForIndex(super.getUserType(), super.getUserId(), super.getDistrictId());
				if(infoSends!=null && infoSends.size()!=0)
					for(InfoSendVO info : infoSends){
						list.add(info);
					}
		}
		//最近投诉
		if("tab1".equals(tab)){
			ComplaintVo complaint = new ComplaintVo();
			complaint.setDistrictId(super.getDistrictId());
			complaint.setType(Constants.COMPLAINT_OWNER);
			complaint.setProcessingStatus(IComplaintService.PROCESSING_STATUS_PENDING);
			List<ComplaintVo> complaints = complaintService.queryRecentComplaint(complaint, Constants.SELECT_COUNT_FOR_INDEX, false);
			if(complaints!=null && complaints.size()!=0)
				for(ComplaintVo cp : complaints){
					list.add(cp);
				}
		}
		//最近报警
		if("tab2".equals(tab)){
			List<AlarmVO> alarms = alarmService.queryAlarmVOForIndex(super.getUserType(), super.getUserId(), super.getDistrictId());
			if(alarms != null && alarms.size()!=0)
				for(AlarmVO alarmVO : alarms){
					alarmVO.getAlarmType().setAlarmName(this.getText(alarmVO.getAlarmType().getAlarmName()));
					list.add(alarmVO);
				}
		}
		//最近访客
		if("tab3".equals(tab)){
			CallRecordVO callRecordVO = new CallRecordVO();
			DeviceVO deviceVO = new DeviceVO();
			HousingDistrictInfoVo housingDistrictInfo = new HousingDistrictInfoVo();
			housingDistrictInfo.setId(super.getDistrictId());
			deviceVO.setHousingDistrictInfo(housingDistrictInfo);
			callRecordVO.setDevice(deviceVO);
			callRecordVO.setCallType(Constants.CALL_TYPE_MESSAGE);
			PagingVO<CallRecordVO> paging = callRecordService.queryCallRecordForPaging(callRecordVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<CallRecordVO> callRecords = new ArrayList<CallRecordVO>();
			if(paging != null)
				callRecords = paging.getResults();
			if(callRecords != null && callRecords.size()!=0)
				for(CallRecordVO cr : callRecords){
					list.add(cr);
				}
		}
		//最近发卡/今日发卡
		if("tab4".equals(tab)){
			GateCardVO gateCardVO = new GateCardVO();
			gateCardVO.setDistrictId(super.getDistrictId());
			PagingVO<GateCardVO> paging = gateCardService.queryGateCardsForPaging(gateCardVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<GateCardVO> gateCards = new ArrayList<GateCardVO>();
			if(paging != null)
				gateCards = paging.getResults();
			if(gateCards != null && gateCards.size()!=0)
				for(GateCardVO card : gateCards){
					list.add(card);
				}
		}
		//最近入住用户
		if("tab5".equals(tab)){
			OwnerUserVO userVO = new OwnerUserVO();
			userVO.setDistrictId(super.getDistrictId());
			PagingVO<OwnerUserVO> paging = ownerUserService.queryOwnerUsersForPaging(userVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<OwnerUserVO> ownerUsers = new ArrayList<OwnerUserVO>();
			if(paging != null)
				ownerUsers = paging.getResults();
			if(ownerUsers != null && ownerUsers.size()!=0)
				for(OwnerUserVO ownerUser : ownerUsers){
					list.add(ownerUser);
				}
		}
		//在线设备
		if("tab6".equals(tab)){
			DeviceVO deviceVO = new DeviceVO();
			deviceVO.setDeviceStatus(Constants.DEVICE_ONLINE); //设备状态1：在线，0：离线
			PagingVO<DeviceVO> paging = deviceService.queryDeviceForPaging(deviceVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<DeviceVO> devices = new ArrayList<DeviceVO>();
			if(paging != null)
				devices = paging.getResults();
			if(devices != null && devices.size()!=0)
				for(DeviceVO device : devices){
					list.add(device);
				}
		}
		//最近报修
		if("tab7".equals(tab)){
			List<RequestRepairVO> repairs = requestRepairService.queryRequestRepairVOForIndex(super.getUserType(), super.getUserId(), super.getDistrictId());
			if(repairs != null && repairs.size()!=0)
				for(RequestRepairVO repairVO : repairs){
					list.add(repairVO);
				}
		}
		//身份证刷卡
		if("tab8".equals(tab)){
			IdCardVisitVO idCardVisitVO = new IdCardVisitVO();
			idCardVisitVO.setDistrictId(super.getDistrictId());
			PagingVO<IdCardVisitVO> paging = idCardVisitService.queryIdCardVisitsForPaging(idCardVisitVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<IdCardVisitVO> idCardVisits = new ArrayList<IdCardVisitVO>();
			if(paging != null){
				idCardVisits = paging.getResults();
			}
			if(idCardVisits != null && idCardVisits.size() != 0){
				for(IdCardVisitVO idCardVisit : idCardVisits){
					list.add(idCardVisit);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * 方法的描述: 系统管理员页面tab显示信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:47:26
	 * @param tab
	 * @return
	 */
	public List<Object> getSaMainPageTabList(String tab) throws Exception{
		List<Object> list = new ArrayList<Object>();
		//今日发布信息
		if("tab0".equals(tab)){
			List<InfoSendVO> infoSends = infoSendService.queryInfoSendForIndex(super.getUserType(),null,null);
			if(infoSends!=null && infoSends.size()!=0)
				for(InfoSendVO info : infoSends){
					list.add(info);
				}
		}
		//最近未处理投诉
		if("tab1".equals(tab)){
			ComplaintVo complaint = new ComplaintVo();
			//complaint.setComplaintLoginName(super.getLoginName());
			complaint.setType(Constants.COMPLAINT_SYSTEM);
			complaint.setProcessingStatus(IComplaintService.PROCESSING_STATUS_PENDING);
			List<ComplaintVo> complaints = complaintService.queryRecentComplaint(complaint, Constants.SELECT_COUNT_FOR_INDEX, false);
			if(complaints!=null && complaints.size()!=0)
				for(ComplaintVo cp : complaints){
					list.add(cp);
				}
		}
		//最近软件
		if("tab2".equals(tab)){
			PagingVO<SoftwareUpgradeVO> paging = softwareUpgradeService.querySoftwaresForPaging(null, null, null, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<SoftwareUpgradeVO> softwareUpgrades = new ArrayList<SoftwareUpgradeVO>();
			if(paging != null)
				softwareUpgrades =paging.getResults();
			if(softwareUpgrades !=null && softwareUpgrades.size() != 0)
				for(SoftwareUpgradeVO software : softwareUpgrades){
					list.add(software);
				}
		}
		//发布广告
		if("tab3".equals(tab)){
			AdvertisementVO adVO = new AdvertisementVO();
			AdSysVO adSys = new AdSysVO();
			AdLocationVO adLocation = new AdLocationVO();
			adSys.setSysCode(Constants.AD_WEB_SYS);
			adLocation.setAdSys(adSys);
			adVO.setAdLocation(adLocation);
			adVO.setStatus(Constants.AD_RUNNING);
			PagingVO<AdvertisementVO> paging = adService.queryAdsForPaging(adVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<AdvertisementVO> ads = new ArrayList<AdvertisementVO>();
			if(paging != null)
				ads = paging.getResults();
			if(ads !=null && ads.size() != 0)
				for(AdvertisementVO ad : ads){
					list.add(ad);
				}
		}
		//数据推送
		if("tab4".equals(tab)){
			PushVO pushVO = new PushVO();
			pushVO.setPushKind("all");
			PagingVO<PushVO> paging = pushService.query(1, Constants.SELECT_COUNT_FOR_INDEX, pushVO);
			List<PushVO> pushVOs = new ArrayList<PushVO>();
			if(paging != null)
				pushVOs = paging.getResults();
			if(pushVOs != null && pushVOs.size() != 0)
				for(PushVO push : pushVOs){
					list.add(push);
				}

		}
		//文件传输
		if("tab5".equals(tab)){
			LocalHdfsVO localHdfsVO = new LocalHdfsVO();
			localHdfsVO.setKind("all");
			localHdfsVO.setUploaderKind("all");
			localHdfsVO.setFileName("");
			PagingVO<LocalHdfsVO> paging = hdfsFileService.query(1, Constants.SELECT_COUNT_FOR_INDEX, localHdfsVO);
			List<LocalHdfsVO> localHdfsVOs = new ArrayList<LocalHdfsVO>();
			if(paging !=null)
				localHdfsVOs = paging.getResults();
			if(localHdfsVOs != null && localHdfsVOs.size() != 0)
				for(LocalHdfsVO localHdfs : localHdfsVOs){
					list.add(localHdfs);
				}
		}
		//发布产品
		if("tab6".equals(tab)){
			//暂时留空
		}
		//最新游戏
		if("tab7".equals(tab)){
			//暂时留空
		}
		
		return list;
	}
	
	/**
	 * 
	 * 方法的描述: 业主主页面tab信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:47:58
	 * @param tab
	 * @return
	 */
	public List<Object> getOwnerMainPageTabList(String tab) throws Exception{
		List<Object> list = new ArrayList<Object>();
		//未读信息
		if("tab0".equals(tab)){
			List<InfoReceiverVO> infoReceivers = infoReceiverService.queryInfoReceiverVOForIndex(super.getUserType(), super.getUserId(), null);
			if(infoReceivers!=null && infoReceivers.size()!=0)
				for(InfoReceiverVO info : infoReceivers){
					list.add(info);
				}
		}
		//最近投诉
		if("tab1".equals(tab)){
			ComplaintVo complaint = new ComplaintVo();
			complaint.setComplaintLoginName(super.getLoginName());
			List<ComplaintVo> complaints = complaintService.queryRecentComplaint(complaint, Constants.SELECT_COUNT_FOR_INDEX, false);
			if(complaints!=null && complaints.size()!=0)
				for(ComplaintVo cp : complaints){
					list.add(cp);
				}
		}
		//最近访客
		if("tab2".equals(tab)){
			CallRecordVO callRecordVO = new CallRecordVO();
			DeviceVO deviceVO = new DeviceVO();
			CellHouseholdInfoVo cellHouseholdInfo = new CellHouseholdInfoVo();
			OwnerUserVO owner = ownerUserService.getOwnerUserDetail(super.getUserId());
			if(owner!=null){
				cellHouseholdInfo.setOwner(owner);
				deviceVO.setCellHouseholdInfo(cellHouseholdInfo);
				callRecordVO.setDevice(deviceVO);
				callRecordVO.setCallType(Constants.CALL_TYPE_MESSAGE);
				PagingVO<CallRecordVO> paging = callRecordService.queryCallRecordForPaging(callRecordVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
				List<CallRecordVO> callRecords = new ArrayList<CallRecordVO>();
				if(paging != null)
					callRecords = paging.getResults();
				if(callRecords != null && callRecords.size()!=0){
					for(CallRecordVO cr : callRecords){
						list.add(cr);
					}
				}
			}
		}
		//最近报修
		if("tab3".equals(tab)){
			List<RequestRepairVO> repairs = requestRepairService.queryRequestRepairVOForIndex(super.getUserType(), super.getUserId(), super.getDistrictId());
			if(repairs != null && repairs.size()!=0)
				for(RequestRepairVO repairVO : repairs){
					list.add(repairVO);
				}
		}
		//最近缴费
		if("tab4".equals(tab)){
			List<ChargeDetailVO> chargeDetails = chargeDetailService.queryChargeDetailVOForIndex(super.getUserType(), super.getUserId(), super.getDistrictId());
			if(chargeDetails!=null && chargeDetails.size()!=0)
				for(ChargeDetailVO chargeDetail : chargeDetails){
					list.add(chargeDetail);
				}
		}
		//
		if("tab5".equals(tab)){
			//暂时留空
		}
		//最近发布信息
		if("tab6".equals(tab)){
			List<InfoSendVO> infoSends = infoSendService.queryInfoSendForIndex(super.getUserType(),super.getUserId(),super.getDistrictId());
			if(infoSends!=null && infoSends.size()!=0)
				for(InfoSendVO info : infoSends){
					list.add(info);
				}
		}
		//最近操作日志（登录）
		if("tab7".equals(tab)){
			OperationLogVO operationLogVO = new OperationLogVO();
			operationLogVO.setOperateUser(super.getLoginName());
			operationLogVO.setOperationCode("13");//登录
			PagingVO<OperationLogVO> paging = operationLogService.queryOperationLogPaging(operationLogVO, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<OperationLogVO> operationLogs = new ArrayList<OperationLogVO>();
			if(paging != null)
				operationLogs = paging.getResults();
			if(operationLogs!=null && operationLogs.size()!=0)
				for(OperationLogVO operationLog : operationLogs){
					operationLog.setMenuCode(this.getText(operationLog.getMenuCode()));
					list.add(operationLog);
				}
		}
		
		return list;
	}
	
	/**
	 * 
	 * 方法的描述: 获得当前小区所在城市的天气
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-14 下午2:08:03
	 * @return WeatherReportDataVO
	 */
	public WeatherReportDataVO getWeatherData() throws Exception{
		HousingDistrictInfoVo housingDistrictInfoVo = housingDistrictInfoService.getHousingDistrictInfo(super.getDistrictId());
		String cityName = "";
		if(housingDistrictInfoVo != null)
			cityName = systemGroupService.getCityNameByDistrictId(housingDistrictInfoVo.getGroupId());
		return weatherService.queryReportDataByCityName(cityName);
	}

	//更新用户的上线标志，失败不影响登录
	private void updateOnlineFlag(String loginName, String onlineFlag, String ip) {
		try {
			getLoginService().updateOnlineFlag(loginName, onlineFlag, ip);
		} catch (Exception e) {
			logger.error("********************更新登录名为{}的上线标志发生异常:{}",
					new Object[] { loginName, e });
		}
	}
	
	//获取版本号
	public String getVersionCode() throws Exception {
		try {
			version = sysParamService.getParamValue(Constants.VERSION_CODE);
		} catch (Exception e) {
			logger.error("********************获取版本号发生异常");
		}
		return SUCCESS;
	}
	
	public String getSelectedLanguage() {
		return selectedLanguage;
	}

	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}


	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCurrLoginName() {
		return currLoginName;
	}

	public void setCurrLoginName(String currLoginName) {
		this.currLoginName = currLoginName;
	}
	
	public String getCurrPass() {
		return currPass;
	}

	public void setCurrPass(String currPass) {
		this.currPass = currPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmNewPass() {
		return confirmNewPass;
	}

	public void setConfirmNewPass(String confirmNewPass) {
		this.confirmNewPass = confirmNewPass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCurrUserType() {
		return currUserType;
	}

	public void setCurrUserType(String currUserType) {
		this.currUserType = currUserType;
	}

	public boolean isPromptFlag() {
		return promptFlag;
	}

	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}
	
	public boolean isExistLoginName() {
		return existLoginName;
	}

	public void setExistLoginName(boolean existLoginName) {
		this.existLoginName = existLoginName;
	}

	public boolean isValidCodeOpenFlag() {
		return validCodeOpenFlag;
	}

	public void setValidCodeOpenFlag(boolean validCodeOpenFlag) {
		this.validCodeOpenFlag = validCodeOpenFlag;
	}

	public LoginVO getLogin() {
		return login;
	}

	public void setLogin(LoginVO login) {
		this.login = login;
	}

	public SaMainPageVO getSaMainPageVO() {
		return saMainPageVO;
	}

	public void setSaMainPageVO(SaMainPageVO saMainPageVO) {
		this.saMainPageVO = saMainPageVO;
	}

	public List<Object> getItemTab() {
		return itemTab;
	}

	public void setItemTab(List<Object> itemTab) {
		this.itemTab = itemTab;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public IComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(IComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	public PaMainPageVO getPaMainPageVO() {
		return paMainPageVO;
	}

	public void setPaMainPageVO(PaMainPageVO paMainPageVO) {
		this.paMainPageVO = paMainPageVO;
	}

	public OwnerMainPageVO getOwnerMainPageVO() {
		return ownerMainPageVO;
	}

	public void setOwnerMainPageVO(OwnerMainPageVO ownerMainPageVO) {
		this.ownerMainPageVO = ownerMainPageVO;
	}

	public IInfoSendService getInfoSendService() {
		return infoSendService;
	}

	public void setInfoSendService(IInfoSendService infoSendService) {
		this.infoSendService = infoSendService;
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	public IAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public IRequestRepairService getRequestRepairService() {
		return requestRepairService;
	}

	public void setRequestRepairService(IRequestRepairService requestRepairService) {
		this.requestRepairService = requestRepairService;
	}

	public IAdService getAdService() {
		return adService;
	}

	public void setAdService(IAdService adService) {
		this.adService = adService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public ICallRecordService getCallRecordService() {
		return callRecordService;
	}

	public void setCallRecordService(ICallRecordService callRecordService) {
		this.callRecordService = callRecordService;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
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

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	public IWeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(IWeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	public IChargeDetailService getChargeDetailService() {
		return chargeDetailService;
	}

	public void setChargeDetailService(IChargeDetailService chargeDetailService) {
		this.chargeDetailService = chargeDetailService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public ISoftwareUpgradeService getSoftwareUpgradeService() {
		return softwareUpgradeService;
	}

	public void setSoftwareUpgradeService(ISoftwareUpgradeService softwareUpgradeService) {
		this.softwareUpgradeService = softwareUpgradeService;
	}

	public IHDFSFileService getHdfsFileService() {
		return hdfsFileService;
	}

	public void setHdfsFileService(IHDFSFileService hdfsFileService) {
		this.hdfsFileService = hdfsFileService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}

	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}

	public IGateCardService getGateCardService() {
		return gateCardService;
	}

	public void setGateCardService(IGateCardService gateCardService) {
		this.gateCardService = gateCardService;
	}

	public List<AdvertisementVO> getAds() {
		return ads;
	}

	public void setAds(List<AdvertisementVO> ads) {
		this.ads = ads;
	}

	public IIdCardVisitService getIdCardVisitService() {
		return idCardVisitService;
	}

	public void setIdCardVisitService(IIdCardVisitService idCardVisitService) {
		this.idCardVisitService = idCardVisitService;
	}

	public String getTabIdTop() {
		return tabIdTop;
	}

	public void setTabIdTop(String tabIdTop) {
		this.tabIdTop = tabIdTop;
	}

	public String getTabIdBottom() {
		return tabIdBottom;
	}

	public void setTabIdBottom(String tabIdBottom) {
		this.tabIdBottom = tabIdBottom;
	}

	public List<String> getTabTops() {
		return tabTops;
	}

	public void setTabTops(List<String> tabTops) {
		this.tabTops = tabTops;
	}

	public List<String> getTabBottoms() {
		return tabBottoms;
	}

	public void setTabBottoms(List<String> tabBottoms) {
		this.tabBottoms = tabBottoms;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
