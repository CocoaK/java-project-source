package com.biencloud.smarthome.cxfservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.biencloud.smarthome.cxfservice.cxfmap.MapAdapter;
import com.biencloud.smarthome.service.addressbook.model.AddressBook;
import com.biencloud.smarthome.service.ad.model.AdLocation;
import com.biencloud.smarthome.service.ad.model.AdSys;
import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.model.AdType;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.alarm.model.Alarm;
import com.biencloud.smarthome.service.alarm.model.AlarmType;
import com.biencloud.smarthome.service.charge.model.ChargeCalMode;
import com.biencloud.smarthome.service.charge.model.ChargeCalUnit;
import com.biencloud.smarthome.service.charge.model.ChargeData;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;
import com.biencloud.smarthome.service.charge.model.ChargeMonetaryUnit;
import com.biencloud.smarthome.service.charge.model.ChargeStatitics;
import com.biencloud.smarthome.service.charge.model.ChargeType;
import com.biencloud.smarthome.service.charge.model.ChargeTypeResult;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;
import com.biencloud.smarthome.service.deivceno.model.DeviceNo;
import com.biencloud.smarthome.service.device.model.CallRecord;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.device.model.DevicePassword;
import com.biencloud.smarthome.service.device.model.DeviceType;
import com.biencloud.smarthome.service.gate.model.GateCard;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;
import com.biencloud.smarthome.service.gate.model.IdCardVisit;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.CellSizeInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictRegionInfo;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;
import com.biencloud.smarthome.service.housemgr.model.RegionBuildingInfo;
import com.biencloud.smarthome.service.housemgr.model.Room;
import com.biencloud.smarthome.service.info.model.DistrictData;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.log.model.AppDataLog;
import com.biencloud.smarthome.service.log.model.ClientLog;
import com.biencloud.smarthome.service.log.model.DiviceRegeditLog;
import com.biencloud.smarthome.service.log.model.FileUploadLog;
import com.biencloud.smarthome.service.log.model.OperationLog;
import com.biencloud.smarthome.service.log.model.SystemLog;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.menu.model.Menu;
import com.biencloud.smarthome.service.monitor.model.Scene;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
import com.biencloud.smarthome.service.page.model.Component;
import com.biencloud.smarthome.service.page.model.PageLayout;
import com.biencloud.smarthome.service.page.model.PageContent;
import com.biencloud.smarthome.service.permissions.model.Role;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.model.PushFinish;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;
import com.biencloud.smarthome.service.rss.model.RssServer;
import com.biencloud.smarthome.service.rss.model.Weather;
import com.biencloud.smarthome.service.rss.vo.WeatherReportData;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;
import com.biencloud.smarthome.service.user.model.SaUser;

/**
 * 
 * 类名称：SmartHomePubService 类描述： web service发布类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-2 下午5:03:02
 */
@WebService
public interface SmartHomePubService {

	/**
	 * 用户登录，如果登录成功则返回登录帐号信息且结果标志为成功，<br/>
	 * 否则结果标志为失败原因。
	 * 
	 * @since 1.0 2012-5-24
	 * @param login
	 *            登录对象
	 * @return
	 */
	@WebMethod
	public Login logon(@WebParam Login login);

	/**
	 * 通过登录名获取登录信息，如果找不到，则返回Null。
	 * 
	 * @param loginName
	 *            登录名
	 * @return
	 */
	@WebMethod
	public Login getLoginByLoginName(@WebParam String loginName);

	/**
	 * 重置密码。
	 * 
	 * @param loginName
	 *            登录名
	 */
	@WebMethod
	public void resetPassword(@WebParam String loginName);
	
	/**
	 * 修改密码。
	 * @param loginName 登录名
	 * @param password 新密码
	 */
	@WebMethod
	public void updatePassword(@WebParam String loginName, @WebParam String password);

	/**
	 * 更新登录账号状态。
	 * 
	 * @param loginId
	 *            登录编号
	 * @param status
	 *            登录账号状态
	 */
	@WebMethod
	public void updateStatus(@WebParam String loginId, @WebParam String status);
	
	/**
	 * 更新用户在线标志。
	 * @param loginName 登录名
	 * @param onlineFlag 在线标志
	 * @param ip IP地址
	 */
	@WebMethod
	public void updateOnlineFlag(@WebParam String loginName, @WebParam String onlineFlag, @WebParam String ip);
	
	/**
	 * 根据在线标志统计用户数。
	 * @param onlineFlag 在线标志，不为空作为查询条件
	 * @return
	 */
	@WebMethod
	public long countUsersByOnlineFlag(@WebParam String onlineFlag);

	/**
	 * 查询菜单列表，分页。
	 * 
	 * @param menuName
	 *            菜单名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param hasParent
	 *            是否存在上级菜单
	 * @param status
	 *            菜单状态，如果为空不作为查询条件
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return 分页信息
	 */
	@WebMethod
	public Paging<Menu> queryMenusForPaging(@WebParam String menuName, @WebParam boolean hasParent, @WebParam String status,
			@WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取菜单详细信息。
	 * 
	 * @param menuCode
	 *            菜单代码
	 * @return
	 */
	@WebMethod
	public Menu getMenuDetail(@WebParam String menuCode);

	/**
	 * 更新菜单信息。
	 * 
	 * @param menu
	 *            菜单实体
	 */
	@WebMethod
	public void updateMenu(@WebParam Menu menu);

	/**
	 * 更新菜单状态。
	 * 
	 * @param menuCode
	 *            菜单代码
	 * @param status
	 *            菜单状态
	 * @param updatedUser
	 *            操作人员登录名
	 */
	@WebMethod
	public void updateMenuStatus(@WebParam String menuCode, @WebParam String status, @WebParam String updatedUser);

	/**
	 * 查询权限列表，分页。
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param loginName
	 *            登录名
	 * @param roleName
	 *            角色名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param status
	 *            角色状态，如果为空不作为查询条件
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<Role> queryRolesForPaging(@WebParam String roleCode, @WebParam String loginName, @WebParam String roleName,
			@WebParam String status, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 新增权限信息。
	 * 
	 * @param roleVO
	 *            角色值对象
	 */
	@WebMethod
	public void addRole(@WebParam Role role);

	/**
	 * 获取权限详细信息。
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return
	 */
	@WebMethod
	public Role getRoleDetail(@WebParam String roleCode);

	/**
	 * 更新权限信息。
	 * 
	 * @param role
	 *            角色实体对象
	 */
	@WebMethod
	public void updateRole(@WebParam Role role);

	/**
	 * 通过角色获取关联的所有菜单。
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return
	 */
	@WebMethod
	public List<Menu> queryMenusByRole(@WebParam String roleCode);

	/**
	 * 获取用户角色列表。
	 * 
	 * @param roleCode 角色代码
	 * @param loginName 登录名
	 * @param currUserType 当前用户类型
	 * @param targetUserType 目标用户类型
	 * @param filterDisabled 是否过滤被禁用的角色
	 * @return
	 */
	@WebMethod
	public List<Role> queryRoles(@WebParam String roleCode, @WebParam String loginName, @WebParam String currUserType,
			@WebParam String targetUserType, @WebParam boolean filterDisabled);

	/**
	 * 通过角色代码获取最多可操作的菜单。
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<Menu> queryMenus(String roleCode);
	
	/**
	 * 验证角色名称是否存在。
	 * @param roleCode 角色代码
	 * @param roleName 角色名称
	 * @return
	 */
	@WebMethod
	public boolean existRoleName(@WebParam String roleCode, 
			@WebParam String roleName);
	
	/**
	 * 查询系统参数列表，分页。
	 * 
	 * @param paramCode
	 *            参数名称，如果为空不作为查询条件，否则作为模糊查询条件。
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return 分页信息
	 */
	@WebMethod
	public Paging<SystemParam> querySystemParamsForPaging(@WebParam String paramName, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取系统参数详细信息。
	 * 
	 * @param paramCode
	 *            参数代码
	 * @return
	 */
	@WebMethod
	public SystemParam getSystemParamDetail(@WebParam String paramCode);

	/**
	 * 更新系统参数。
	 * 
	 * @param systemParam
	 *            系统参数实体对象
	 */
	@WebMethod
	public void updateSystemParam(@WebParam SystemParam systemParam);

	/**
	 * 查询软件升级列表，分页。
	 * 
	 * @param softwareName
	 *            软件名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param deviceType
	 *            设备类型，如果为空不作为查询条件
	 * @param status
	 *            状态，如果为空不作为查询条件
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return 分页信息
	 */
	@WebMethod
	public Paging<SoftwareUpgrade> querySoftwaresForPaging(@WebParam String softwareName, @WebParam String deviceType,
			@WebParam String status, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 升级软件。
	 * @param su 待升级的软件实体对象
	 */
	@WebMethod
	public void upgradeSoftware(@WebParam SoftwareUpgrade su);
	
	/**
	 * 通过软件代码获取最新的软件信息，获取不到返回Null。
	 * @param softwareCode 软件代码
	 * @return 最新的软件信息
	 */
	@WebMethod
	public SoftwareUpgrade getLatestSoftware(@WebParam String softwareCode);
	
	/**
	 * 审核软件。
	 * 
	 * @param softwareId
	 *            软件编号
	 * @param isApproved
	 *            是否审核通过
	 * @param approvedUser
	 *            审核人员登录名
	 */
	@WebMethod
	public void approveSoftware(@WebParam String softwareId, @WebParam boolean isApproved, @WebParam String approvedUser);

	/**
	 * 即时发布软件。
	 * 
	 * @param softwareId
	 *            软件编号
	 * @param publishedUser
	 *            发布人员登录名
	 */
	@WebMethod
	public void publishSoftware(@WebParam String softwareId, @WebParam String publishedUser);

	/**
	 * 定时发布软件。
	 * 
	 * @param softwareId
	 *            软件编号
	 * @param publishedTime
	 *            发布时间
	 * @param publishedUser
	 *            发布人员登录名
	 */
	@WebMethod
	public void publishTimingSoftware(@WebParam String softwareId, @WebParam Date publishedTime, @WebParam String publishedUser);

	/**
	 * 新增软件升级信息。
	 * 
	 * @param softwareUpgrade
	 *            软件升级实体对象
	 */
	@WebMethod
	public void addSoftware(@WebParam SoftwareUpgrade softwareUpgrade);

	/**
	 * 获取指定的软件升级详细信息。
	 * 
	 * @param softwareId
	 *            软件编号
	 * @return
	 */
	@WebMethod
	public SoftwareUpgrade getSoftwareDetail(@WebParam String softwareId);

	/**
	 * 删除指定的软件升级信息。
	 * 
	 * @param softwareId
	 *            软件编号
	 */
	@WebMethod
	public void removeSoftware(@WebParam String softwareId);

	/**
	 * 通知统一升级软件。
	 * @param softwareId 软件编号
	 * @param upgradedTime 升级时间
	 * @param upgradedUser 通知升级人员登录名
	 */
	@WebMethod
	public void notifyUpgradeSoftware(@WebParam String softwareId, 
			@WebParam Date upgradedTime, @WebParam String upgradedUser);
	
	/**
	 * 查询所有的设备类型。
	 * 
	 * @return
	 */
	@WebMethod
	public List<DeviceType> queryAllDeviceTypes();

	/**
	 * 查询日志列表，分页。参数如果为空，则不作为查询条件
	 * 
	 * @param operateTime
	 *            操作时间
	 * @param operateUser
	 *            操作用户名,如为空则不作为查询条件
	 * @param menuCode
	 *            菜单代码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<SystemLog> querySystemLogForPaging(@WebParam String logId, @WebParam String operateUser, @WebParam String operateTime,
			@WebParam String menuCode, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 查询日志列表，分页。参数如果为空，则不作为查询条件
	 * 
	 * @param operateTime
	 *            操作时间
	 * @param operateUser
	 *            操作用户名
	 * @param menuCode
	 *            菜单代码
	 * @param ip
	 *            用户登录IP
	 * @param details
	 *            操作内容
	 * @param operateCode
	 *            操作代码
	 * @param operateResult
	 *            操作结果
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<OperationLog> queryOperationLogForPaging(@WebParam String logId, @WebParam String operateUser,
			@WebParam String operateTime, @WebParam String menuCode, @WebParam String operateResult, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 保存或更新系统日志
	 * 
	 * @param systemLog
	 * @return
	 */
	@WebMethod
	public void saveOrUpdateSystemLog(@WebParam SystemLog systemLog);

	/**
	 * 保存或更新操作日志
	 * 
	 * @param operateLog
	 * @return
	 */
	@WebMethod
	public void saveOrUpdateOperationLog(@WebParam OperationLog operationLog);

	/**
	 * 根据条件查询设备列表。
	 * 
	 * @param device
	 *            设备对象，查询条件
	 * @return
	 */
	@WebMethod
	public List<Device> queryDevices(@WebParam Device device);

	/**
	 * 查询门卡刷卡记录列表，分页。
	 * 
	 * @param gateCardVisit
	 *            门卡刷卡对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<GateCardVisit> queryGateCardVisitsForPaging(@WebParam GateCardVisit gateCardVisit, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 获取门卡刷卡记录详细信息。
	 * 
	 * @param visitId
	 *            门卡刷卡编号
	 * @return
	 */
	@WebMethod
	public GateCardVisit getGateCardVisitDetail(@WebParam String visitId);

	/**
	 * 查询身份证刷卡记录列表，分页。
	 * 
	 * @param idCardVisit
	 *            访客身份证刷卡对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<IdCardVisit> queryIdCardVisitsForPaging(@WebParam IdCardVisit idCardVisit, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取身份证刷卡记录详细信息。
	 * 
	 * @param visitId
	 *            身份证刷卡编号
	 * @return
	 */
	@WebMethod
	public IdCardVisit getIdCardVisitDetail(@WebParam String visitId);

	/**
	 * 查询门卡列表，分页。
	 * 
	 * @param gateCard
	 *            门卡对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<GateCard> queryGateCardsForPaging(@WebParam GateCard gateCard, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取门卡详细信息。
	 * 
	 * @param visitId
	 *            门卡编号
	 * @return
	 */
	@WebMethod
	public GateCard getGateCardDetail(@WebParam String gateCardId);

	/**
	 * 新增门卡详细信息。
	 * 
	 * @param gateCard
	 *            门卡信息
	 */
	@WebMethod
	public void addGateCard(@WebParam GateCard gateCard);

	/**
	 * 更新门卡详细信息。
	 * 
	 * @param gateCard
	 *            门卡信息
	 */
	@WebMethod
	public void updateGateCard(@WebParam GateCard gateCard);

	/**
	 * 删除门卡详细信息。
	 * 
	 * @param gateCardId
	 *            门卡编号
	 */
	@WebMethod
	public void removeGateCard(@WebParam String gateCardId);

	/**
	 * 更新门卡状态。
	 * 
	 * @param gateCardId
	 *            门卡编号
	 * @param status
	 *            门卡状态
	 * @param updatedUser
	 *            操作人员登录名
	 */
	@WebMethod
	public void updateGateCardStatus(@WebParam String gateCardId, @WebParam String status, @WebParam String updatedUser);

	/**
	 * 判断门禁卡号是否存在。
	 * @param gateCard 门禁信息
	 * @return
	 */
	@WebMethod
	public boolean existCardNo(@WebParam GateCard gateCard);
	
	/**
	 * 通过设备代码获取门卡列表。
	 * @param deviceCode 设备代码
	 * @return
	 */
	@WebMethod
	public List<GateCard> queryGateCardByDeviceCode(@WebParam String deviceCode);
	
	/**
	 * 根据Id查询操作日志
	 * 
	 * @param String
	 *            logId
	 * @return SystemLog
	 */
	@WebMethod
	public SystemLog getSystemLogById(@WebParam String logId);

	/**
	 * 根据Id查询系统日志
	 * 
	 * @param String
	 *            logId
	 * @return OperationLog
	 */
	@WebMethod
	public OperationLog getOperationLogById(@WebParam String logId);

	/**
	 * 更新设备
	 * 
	 * @param Device
	 *            设备对象
	 * @return
	 */
	@WebMethod
	public int updateDevice(@WebParam Device device, @WebParam String updateType);

	/**
	 * 保存设备
	 * 
	 * @param Device
	 *            设备对象
	 * @return
	 */
	@WebMethod
	public int saveDevice(@WebParam Device device);

	/**
	 * 查询设备并分页
	 * 
	 * @param Device
	 *            device，int pageNum,int pageSize
	 * @return Paging
	 */
	@WebMethod
	public Paging<Device> queryDeviceForPaging(@WebParam Device device, @WebParam int pageNum, int pageSize);

	/**
	 * 根据Id查询设备
	 * 
	 * @param String
	 *            deviceId
	 * @return Device
	 */
	@WebMethod
	public Device queryDeviceById(@WebParam String deviceId);
	
	/**
	 * 根据Code查询设备
	 * 
	 * @param String
	 *            deviceCode
	 * @return Device
	 */
	@WebMethod
	public Device queryDeviceByCode(@WebParam String deviceCode);

	/**
	 * 查询设备IP并分页
	 * 
	 * @param DeviceIp
	 *            deviceIp，int pageNum,int pageSize
	 * @return Paging
	 */
	@WebMethod
	public Paging<DeviceIp> queryDeviceIpForPaging(@WebParam DeviceIp deviceIp, @WebParam int pageNum, int pageSize);

	/**
	 * 根据IP查询设备IP对象
	 * 
	 * @param Stirng
	 *            ipAddress
	 * @return Paging
	 */
	@WebMethod
	public DeviceIp queryDeviceIpByIp(String ipAddress,String districtId);

	/**
	 * 保存IP地址段例如：192.168.1.1-192.168.1.254
	 * 
	 * @param DeviceIp
	 *            deviceIp
	 * @return int 1表示保存成功，0表示保存失败
	 */
	@WebMethod
	public int saveSubnetIps(DeviceIp deviceIp);

	/**
	 * 根据ID查询设备留言
	 * 
	 * @param DeviceIp
	 *            deviceIp
	 * @return VideoMessage 留言录像实体对象
	 */
	@WebMethod
	public CallRecord getCallRecordById(String id);

	/**
	 * 根据设备ID查询设备留言
	 * 
	 * @param deviceId
	 * @return List 留言录像实体对象List
	 */
	@WebMethod
	public Paging<CallRecord> queryCallRecordForPaging(CallRecord callRecord, int pageNum, int pageSize);

	/**
	 * 保存留言录像
	 * 
	 * @param VideoMessage
	 * @return
	 */
	@WebMethod
	public void saveCallRecord(CallRecord callRecord);

	/**
	 * 获取小区信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public HousingDistrictInfo getHousingDistrictInfo(@WebParam String id);

	/**
	 * 根据组织编号查询小区ID
	 * 
	 * @param groupNo
	 * @return 小区ID, 如果查找不到则返回null
	 */
	@WebMethod
	public String getDistrictIdByGroupNo(@WebParam Long groupNo);
	
	/**
	 * 更新小区信息
	 * 
	 * @param district
	 * @return
	 */
	@WebMethod
	public void updateHousingDistrictInfo(@WebParam HousingDistrictInfo district);

	/**
	 * 新增小区信息(新增t_system_group中的小区时, 才会向小区表中新增记录)
	 * 
	 * @param district
	 * @return
	 */
	@WebMethod
	public void saveHousingDistrictInfo(@WebParam HousingDistrictInfo district);

	/**
	 * 变更小区归属的物业公司ID
	 * 
	 * @param districtId
	 * @param propertyCompanyId
	 */
	@WebMethod
	public void updateDistrictPropertyCompanyId(@WebParam String districtId, @WebParam int propertyCompanyId);

	/**
	 * 分页查询小区信息
	 * 
	 * @param condition
	 *            模版方式的查询条件
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<HousingDistrictInfo> queryHousingDistrictInfosForPaging(@WebParam HousingDistrictInfo condition, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 获取所有小区信息
	 * 
	 * @param condition
	 *            模版方式的查询条件
	 * @return
	 */
	@WebMethod
	public List<HousingDistrictInfo> getDistricts(@WebParam HousingDistrictInfo condition);
	
	/**
	 * 查询小区总数
	 * 
	 * @return
	 */
	@WebMethod
	public long getDistrictCount();

	/**
	 * 分页查询小区区域信息
	 * 
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<HousingDistrictRegionInfo> queryHousingDistrictRegionInfosForPaging(@WebParam HousingDistrictRegionInfo condition,
			@WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取所有区域信息
	 * 
	 * @param condition
	 * @return
	 */
	@WebMethod
	public List<HousingDistrictRegionInfo> getRegions(@WebParam HousingDistrictRegionInfo condition);

	/**
	 * 更新区域信息
	 * 
	 * @param region
	 */
	@WebMethod
	public void updateHousingDistrictRegionInfo(@WebParam HousingDistrictRegionInfo region);


	/**
	 * 删除指定编号的小区区域。
	 * @param id 区域编号
	 */
	@WebMethod
	public int removeRegionById(@WebParam String id);
	
	/**
	 * 新增小区区域
	 * 
	 * @param region
	 */
	@WebMethod
	public void saveHousingDistrictRegionInfo(@WebParam HousingDistrictRegionInfo region);

	/**
	 * 根据ID获取区域信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public HousingDistrictRegionInfo getHousingDistrictRegionInfo(@WebParam String id);
	
	/**
	 * 新增/更新区域信息
	 * 
	 * @param region
	 * @return
	 */
	@WebMethod
	public HousingDistrictRegionInfo saveOrUpdateRegion(@WebParam HousingDistrictRegionInfo region);

	/**
	 * 新增区域时, 判断区域编码是不是重复的, 如果不重复则返回true
	 * PS: 不同小区的区域可以有相同的编码
	 * 
	 * @param regionCode
	 * @param districtId
	 * @return
	 */
	@WebMethod
	public boolean newRegionCodeNotRepeat(@WebParam String regionCode, @WebParam String districtId);
	
	/**
	 * 更新区域时, 判断区域编码是不是重复的, 如果不重复则返回true
	 * PS: 不同小区的区域可以有相同的编码
	 * 
	 * @param regionId
	 * @param regionCode
	 * @param districtId
	 * @return
	 */
	@WebMethod
	public boolean updateRegionCodeNotRepeat(@WebParam String regionId, @WebParam String regionCode, @WebParam String districtId);

	/**
	 * 分页查询楼宇信息
	 * 
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<RegionBuildingInfo> queryRegionBuildingInfosForPaging(@WebParam RegionBuildingInfo condition, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 获取所有楼宇信息
	 * 
	 * @param condition
	 * @return
	 */
	@WebMethod
	public List<RegionBuildingInfo> getBuildings(@WebParam RegionBuildingInfo condition);

	/**
	 * 新增或更新楼宇信息
	 * 
	 * @param building
	 */
	@WebMethod
	public void saveOrUpdateRegionBuildingInfo(@WebParam RegionBuildingInfo building);

	/**
	 * 根据ID获取楼宇信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public RegionBuildingInfo getRegionBuildingInfo(@WebParam String id);

	/**
	 * 删除指定编号的小区楼宇。
	 * @param id 楼宇编号
	 */
	@WebMethod
	public int removeBuildingById(@WebParam String id);
	
	/**
	 * 新增楼宇时, 判断楼宇编码是不是重复的, 如果不重复则返回true
	 * PS: 不同区域的楼宇可以有相同的编码
	 * 
	 * @param buildingCode
	 * @param regionId
	 * @return
	 */
	public boolean newBuildingCodeNotRepeat(String buildingCode, String regionId);
	
	/**
	 * 更新楼宇时, 判断楼宇编码是不是重复的, 如果不重复则返回true
	 * PS: 不同区域的楼宇可以有相同的编码
	 * 
	 * @param buildingId
	 * @param buildingCode
	 * @param regionId
	 * @return
	 */
	public boolean updateBuildingCodeNotRepeat(String buildingId, String buildingCode, String regionId);
	
	/**
	 * 分页查询单元信息
	 * 
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<BuildingCellInfo> queryBuildingCellInfosForPaging(@WebParam BuildingCellInfo condition, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 根据ID获取单元信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public BuildingCellInfo getBuildingCellInfo(@WebParam String id);

	/**
	 * 删除指定编号的小区单元。
	 * @param id 单元编号
	 */
	@WebMethod
	public int removeCellById(@WebParam String id);
	
	/**
	 * 新增或更新单元信息
	 * 
	 * @param cell
	 */
	@WebMethod
	public void saveOrUpdateBuildingCellInfo(@WebParam BuildingCellInfo cell);
	
	/**
	 * 新增或更新单元信息
	 * 
	 * @param cell
	 */
	@WebMethod
	public BuildingCellInfo saveOrUpdateBuildingCell(@WebParam BuildingCellInfo cell);

	/**
	 * 新增单元时, 判断单元编码是不是重复的, 如果不重复则返回true
	 * PS: 不同楼宇的单元可以有相同的编码
	 * 
	 * @param cellCode
	 * @param buildingId
	 * @return
	 */
	@WebMethod
	public boolean newCellCodeNotRepeat(@WebParam String cellCode, @WebParam String buildingId);
	
	/**
	 * 更新单元时, 判断单元编码是不是重复的, 如果不重复则返回true
	 * PS: 不同楼宇的单元可以有相同的编码
	 * 
	 * @param cellId
	 * @param cellCode
	 * @param buildingId
	 * @return
	 */
	@WebMethod
	public boolean updateCellCodeNotRepeat(@WebParam String cellId, @WebParam String cellCode, @WebParam String buildingId);
	
	/**
	 * 新增/更新 户型
	 * 
	 * @param size
	 */
	@WebMethod
	public void saveOrUpdateCellSizeInfo(@WebParam CellSizeInfo size);

	/**
	 * 删除指定编号的户型。
	 * @param id 户型编号
	 */
	@WebMethod
	public int removeCellSizeById(@WebParam String id);
	
	/**
	 * 查找户型信息
	 * 
	 * @param condition
	 */
	@WebMethod
	public List<CellSizeInfo> findCellSizeInfo(@WebParam CellSizeInfo condition);

	/**
	 * 保存业主自定义的房间对应的户型信息。<br/>
	 * @param size 户型信息
	 * @param houseId 当前房间编号
	 * @return
	 */
	@WebMethod
	public String saveCustomSize(@WebParam CellSizeInfo size, @WebParam String houseId);
	
	/**
	 * 获取单个房号信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public CellHouseholdInfo getCellHouseholdInfo(@WebParam String id);

	/**
	 * 查找所有符合条件的房号信息
	 * 
	 * @param condition
	 * @return
	 */
	public List<CellHouseholdInfo> findHouseholds(@WebParam CellHouseholdInfo condition);

	/**
	 * 仅更新房号的一些信息
	 * 
	 * @param id
	 *            房号ID
	 * @param size
	 *            户型
	 * @param area
	 *            面积
	 * @param chargeTypes
	 *            收费类型
	 */
	@WebMethod
	public void updateCellHouseholdInfoSomeProperty(@WebParam String id, @WebParam String code, @WebParam CellSizeInfo size,
			@WebParam String area, @WebParam List<ChargeType> chargeTypes);

	/**
	 * 新增或更新房号信息
	 * 
	 * @param house
	 */
	@WebMethod
	public void saveOrUpdateCellHouseholdInfo(@WebParam CellHouseholdInfo house);
	
	/**
	 * 新增房号时, 判断房号编码是不是重复的, 如果不重复则返回true
	 * PS: 不同单元的房号可以有相同的编码
	 * 
	 * @param householdCode
	 * @param cellId
	 * @return
	 */
	@WebMethod
	public boolean newHouseCodeNotRepeat(@WebParam String householdCode, @WebParam String cellId);
	
	/**
	 * 更房号时, 判断房号编码是不是重复的, 如果不重复则返回true
	 * PS: 不同单元的房号可以有相同的编码
	 * 
	 * @param householdId
	 * @param householdCode
	 * @param cellId
	 * @return
	 */
	@WebMethod
	public boolean updateHouseCodeNotRepeat(@WebParam String householdId, @WebParam String householdCode, @WebParam String cellId);
	
	/**
	 * 更新房号的户型ID
	 * 
	 * @param houseId
	 * @param sizeId
	 */
	@WebMethod
	public void updateHouseSizeId(@WebParam String houseId, @WebParam String sizeId);

	/**
	 * 删除指定编号的小区房号。
	 * @param id 房号编号
	 */
	@WebMethod
	public int removeHouseById(@WebParam String id);
	
	/**
	 * 分页查询房号信息
	 * 
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<CellHouseholdInfo> queryCellHouseholdInfosForPaging(@WebParam CellHouseholdInfo condition, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 根据设备编号查询房号的房间信息
	 * 
	 * @param deviceNo
	 * @return
	 */
	@WebMethod
	public List<Room> queryRoomByDeviceNo(@WebParam String deviceNo);
	
	/**
	 * 分页查询物业公司信息
	 * 
	 * @param condition
	 * @return
	 */
	@WebMethod
	public Paging<PropertyCompanyInfo> queryPropertyCompanyInfosForPaging(@WebParam PropertyCompanyInfo condition, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 查询所有符合查询条件的物业公司
	 * 
	 * @param condition
	 * @return
	 */
	@WebMethod
	public List<PropertyCompanyInfo> findPropertyCompanyInfos(@WebParam PropertyCompanyInfo condition);
	
	/**
	 * 根据ID获取物业公司信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public PropertyCompanyInfo getPropertyCompanyInfo(@WebParam Integer id);

	/**
	 * 更新物业公司信息
	 * 
	 * @param propertyCompany
	 */
	@WebMethod
	public void updatePropertyCompanyInfo(@WebParam PropertyCompanyInfo propertyCompany);

	/**
	 * 新增物业公司信息
	 * 
	 * @param propertyCompany
	 * @return 新物业公司的ID
	 */
	@WebMethod
	public int savePropertyCompanyInfo(@WebParam PropertyCompanyInfo propertyCompany);
	
	/**
	 * 根据ID删除物业公司信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public void removePropertyCompanyInfo(@WebParam PropertyCompanyInfo propertyCompany);

	/**
	 * 查询系统管理用户列表，分页。
	 * 
	 * @param user
	 *            系统管理用户对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<SaUser> querySaUsersForPaging(@WebParam SaUser user, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取系统管理用户详细信息。
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	@WebMethod
	public SaUser getSaUserDetail(@WebParam String userId);

	/**
	 * 新增系统管理用户信息。
	 * 
	 * @param user
	 *            系统管理用户信息
	 */
	@WebMethod
	public void addSaUser(@WebParam SaUser user);

	/**
	 * 更新系统管理用户信息。
	 * 
	 * @param user
	 *            系统管理用户信息
	 */
	@WebMethod
	public void updateSaUser(@WebParam SaUser user);

	/**
	 * 查询物业管理用户列表，分页。
	 * 
	 * @param user
	 *            物业管理用户对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<PaUser> queryPaUsersForPaging(@WebParam PaUser user, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取物业管理用户详细信息。
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	@WebMethod
	public PaUser getPaUserDetail(@WebParam String userId);

	/**
	 * 新增物业管理用户信息。
	 * 
	 * @param user
	 *            物业管理用户信息
	 */
	@WebMethod
	public void addPaUser(@WebParam PaUser user);

	/**
	 * 更新物业管理用户信息。
	 * 
	 * @param user
	 *            物业管理用户信息
	 */
	@WebMethod
	public void updatePaUser(@WebParam PaUser user);

	/**
	 * 查询住户用户列表，分页。
	 * 
	 * @param user
	 *            住户用户对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<OwnerUser> queryOwnerUsersForPaging(@WebParam OwnerUser user, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取住户用户详细信息。
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	@WebMethod
	public OwnerUser getOwnerUserDetail(@WebParam String userId);

	/**
	 * 新增住户用户信息。
	 * 
	 * @param user
	 *            住户用户信息
	 */
	@WebMethod
	public void addOwnerUser(@WebParam OwnerUser user);

	/**
	 * 新增住户用户信息列表。
	 * 
	 * @param users
	 *            住户用户信息列表
	 */
	@WebMethod
	public void addOwnerUsers(@WebParam List<OwnerUser> users);

	/**
	 * 更新住户用户信息。
	 * 
	 * @param user
	 *            住户用户信息
	 */
	@WebMethod
	public void updateOwnerUser(@WebParam OwnerUser user);

	/**
	 * 统计住户用户数。
	 * @param user 住户用户对象
	 */
	@WebMethod
	public long countUsers(@WebParam OwnerUser user);
	
	/**
	 * 通过房间号获取住户信息，如果获取不到返回Null。
	 * @param houseId 房间号
	 * @return
	 */
	@WebMethod
	public OwnerUser getOwnerUserByHouseId(@WebParam String houseId);
	
	/**
	 * 
	 * 方法的描述: 通过ID查找
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:36:10
	 * @param id
	 * @return
	 */
	public Push findPushById(Long id);

	/**
	 * 
	 * 方法的描述: 根据ID删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:05
	 * @param id
	 * @return
	 */
	public boolean deletePushById(Long id);

	/**
	 * 
	 * 方法的描述: 通过实体类进行删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:58
	 * @param push
	 * @return
	 */
	public boolean deletePushByEntity(Push push);

	/**
	 * 
	 * 方法的描述: 分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 *            条件不包含
	 * @return
	 */
	public Paging<Push> queryPushForPaging(int pageNum, int pageSize, String condition, String orderString);

	/**
	 * 
	 * 方法的描述: 通过ID查找
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:36:10
	 * @param id
	 * @return
	 */
	public PushFinish findPushFinishById(Long id);

	/**
	 * 
	 * 方法的描述: 根据ID删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:05
	 * @param id
	 * @return
	 */
	public boolean deletePushFinishById(Long id);

	/**
	 * 
	 * 方法的描述: 通过实体类进行删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:58
	 * @param pushFinish
	 * @return
	 */
	public boolean deletePushFinishByEntity(PushFinish pushFinish);

	/**
	 * 
	 * 方法的描述: 分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 *            条件不包含
	 * @return
	 */
	public Paging<PushFinish> queryPushFinishForPaging(int pageNum, int pageSize, String condition, String orderString);

	/**
	 * 
	 * 方法的描述: 查询组织
	 * 
	 * @author: dehua ye
	 * @version: 0.1
	 * @date: 2012-5-7 下午9:23:00
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public List<SystemGroup> querySystemGroup(@WebParam int pageNo, @WebParam int pageSize, @WebParam SystemGroup ob);

	/**
	 * 
	 * 方法的描述:保存或更新组织
	 * 
	 * @author: dehua ye
	 * @version: 0.1
	 * @date: 2012-5-7 下午9:29:56
	 * @param systemGroup
	 */
	@WebMethod
	public boolean saveOrUpdateSystemGroup(@WebParam SystemGroup systemGroup);

	/**
	 * 
	 * 方法的描述: 删除组织
	 * 
	 * @author: dehua ye
	 * @version: 0.1
	 * @date: 2012-5-7 下午9:36:21
	 * @param id
	 */
	@WebMethod
	public boolean deleteSystemGroupById(@WebParam Long id);

	/**
	 * 查询设备编号列表。
	 * 
	 * @param device
	 *            设备编号对象
	 * @return
	 */
	@WebMethod
	public Paging<DeviceNo> queryDeviceNoForPaging(@WebParam DeviceNo paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 后台自动生成设备编号列表。
	 * 
	 * @return
	 */
	@WebMethod
	public String generatorDeviceNo();

	/**
	 * 更新设备编号的设备ID。
	 * 
	 * @return
	 */
	@WebMethod
	public boolean updateDeviceId(@WebParam Device device, @WebParam String deviceNo);

	/**
	 * 查询收费计算方式列表。
	 * 
	 * @param device
	 *            收费计算方式对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeCalMode> queryChargeCalModeForPaging(@WebParam ChargeCalMode paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 更新收费计算方式。
	 * 
	 * @param device
	 *            收费计算方式对象
	 * @return
	 */
	@WebMethod
	public void updateChargeCalMode(@WebParam ChargeCalMode entity);

	/**
	 * 保存收费计算方式。
	 * 
	 * @param device
	 *            收费计算方式对象
	 * @return
	 */
	@WebMethod
	public void saveChargeCalMode(@WebParam ChargeCalMode entity);

	/**
	 * 获取收费计算方式对象。
	 * 
	 * @param device
	 *            收费计算方式对象
	 * @return
	 */
	@WebMethod
	public ChargeCalMode getChargeCalMode(@WebParam String entityId);

	/**
	 * 查询收费数据列表。
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeData> queryChargeDataForPaging(@WebParam ChargeData paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 更新收费数据。
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public void updateChargeData(@WebParam ChargeData entity);

	/**
	 * 保存收费数据。
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public void saveChargeData(@WebParam ChargeData entity);

	/**
	 * 获取收费数据对象。
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public ChargeData getChargeData(@WebParam String entityId);

	/**
	 * 查询收费清单列表。
	 * 
	 * @param device
	 *            收费清单对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeDetail> queryChargeDetailForPaging(@WebParam ChargeDetail paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 更新收费清单。
	 * 
	 * @param device
	 *            收费清单对象
	 * @return
	 */
	@WebMethod
	public void updateChargeDetail(@WebParam ChargeDetail entity);

	/**
	 * 保存收费清单。
	 * 
	 * @param device
	 *            收费清单对象
	 * @return
	 */
	@WebMethod
	public void saveChargeDetail(@WebParam ChargeDetail entity);

	/**
	 * 获取收费清单对象。
	 * 
	 * @param device
	 *            收费清单对象
	 * @return
	 */
	@WebMethod
	public ChargeDetail getChargeDetail(@WebParam String entityId);

	/**
	 * 查询收费货币单位列表。
	 * 
	 * @param device
	 *            收费货币单位对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeMonetaryUnit> queryChargeMonetaryUnitForPaging(@WebParam ChargeMonetaryUnit paramsOb, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 更新收费货币单位。
	 * 
	 * @param device
	 *            收费货币单位对象
	 * @return
	 */
	@WebMethod
	public void updateChargeMonetaryUnit(@WebParam ChargeMonetaryUnit entity);

	/**
	 * 保存收费货币单位。
	 * 
	 * @param device
	 *            收费货币单位对象
	 * @return
	 */
	@WebMethod
	public void saveChargeMonetaryUnit(@WebParam ChargeMonetaryUnit entity);

	/**
	 * 获取收费货币单位对象。
	 * 
	 * @param device
	 *            收费货币单位对象
	 * @return
	 */
	@WebMethod
	public ChargeMonetaryUnit getChargeMonetaryUnit(@WebParam String entityId);

	/**
	 * 查询收费项目结果列表。
	 * 
	 * @param device
	 *            收费项目结果对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeTypeResult> queryChargeTypeResultForPaging(@WebParam ChargeTypeResult paramsOb, @WebParam int pageNum,
			@WebParam int pageSize);

	/**
	 * 更新收费项目结果。
	 * 
	 * @param device
	 *            收费项目结果对象
	 * @return
	 */
	@WebMethod
	public void updateChargeTypeResult(@WebParam ChargeTypeResult entity);

	/**
	 * 保存收费项目结果。
	 * 
	 * @param device
	 *            收费项目结果对象
	 * @return
	 */
	@WebMethod
	public void saveChargeTypeResult(@WebParam ChargeTypeResult entity);

	/**
	 * 获取收费项目结果对象。
	 * 
	 * @param device
	 *            收费项目结果对象
	 * @return
	 */
	@WebMethod
	public ChargeTypeResult getChargeTypeResult(@WebParam String entityId);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public Paging<ChargeType> queryChargeTypeForPaging(@WebParam ChargeType paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 更新收费项目。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public void updateChargeType(@WebParam ChargeType entity);

	/**
	 * 保存收费项目。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public void saveChargeType(@WebParam ChargeType entity);

	/**
	 * 获取收费项目对象。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public ChargeType getChargeType(@WebParam String entityId);

	/**
	 * 删除收费计算方式对象。
	 * 
	 * @param device
	 *            收费计算方式对象
	 * @return
	 */
	@WebMethod
	public void DelChargeCalMode(@WebParam ChargeCalMode entity);

	/**
	 * 删除收费数据对象。
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public void DelChargeData(@WebParam ChargeData entity);

	/**
	 * 删除收费清单对象。
	 * 
	 * @param device
	 *            收费清单对象
	 * @return
	 */
	@WebMethod
	public void DelChargeDetail(@WebParam ChargeDetail entity);

	/**
	 * 删除收费货币单位对象。
	 * 
	 * @param device
	 *            收费货币单位对象
	 * @return
	 */
	@WebMethod
	public void DelChargeMonetaryUnit(@WebParam ChargeMonetaryUnit entity);

	/**
	 * 删除收费项目结果对象。
	 * 
	 * @param device
	 *            收费项目结果对象
	 * @return
	 */
	@WebMethod
	public void DelChargeTypeResult(@WebParam ChargeTypeResult entity);

	/**
	 * 删除收费项目对象。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public void DelChargeType(@WebParam ChargeType entity);

	/**
	 * 
	 * 方法的描述: 插入推送内容
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-17 下午4:27:55
	 * @param push
	 * @return
	 */
	@WebMethod
	public boolean insertPush(@WebParam Push push);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeCalMode> queryChargeCalModeForList(@WebParam ChargeCalMode paramsOb);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeData> queryChargeDataForList(@WebParam ChargeData paramsOb);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeMonetaryUnit> queryChargeMonetaryUnitForList(@WebParam ChargeMonetaryUnit paramsOb);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeTypeResult> queryChargeTypeResultForList(@WebParam ChargeTypeResult paramsOb);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeType> queryChargeTypeForList(@WebParam ChargeType paramsOb);

	/**
	 * 查询收费项目列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<ChargeDetail> queryChargeDetailForList(@WebParam ChargeDetail paramsOb);

	/**
	 * 统计收费报表
	 * 
	 * @param device
	 *            收费数据对象
	 * @return
	 */
	@WebMethod
	public List<ChargeStatitics> statisticsCharge(@WebParam ChargeData ob);

	/**
	 * 
	 * 方法的描述: 保存通话记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-21 下午4:06:22
	 * @param callRecord
	 *            通话记录实体对象
	 */
	@WebMethod
	public void removeCallRecord(CallRecord callRecord);

	/**
	 * 
	 * 方法的描述: 查询通话记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-21 下午4:06:22
	 * @param callRecord
	 * @return List
	 */
	@WebMethod
	public List<CallRecord> queryCallRecords(CallRecord callRecord);

	/**
	 * 查询信息接收列表。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public List<InfoReceiver> queryInfoReceiverForList(@WebParam InfoReceiver paramsOb);

	/**
	 * 查询信息接收列表。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public Paging<InfoReceiver> queryInfoReceiverForPaging(@WebParam InfoReceiver paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 更新信息接收。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public void updateInfoReceiver(@WebParam InfoReceiver entity);

	/**
	 * 保存信息接收。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public void saveInfoReceiver(@WebParam InfoReceiver entity);

	/**
	 * 获取信息接收对象。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public InfoReceiver getInfoReceiver(@WebParam String entityId);

	/**
	 * 删除信息接收对象。
	 * 
	 * @param device
	 *            信息接收对象
	 * @return
	 */
	@WebMethod
	public void DelInfoReceiver(@WebParam InfoReceiver entity);

	/**
	 * 查询信息发布列表。
	 * 
	 * @param device
	 *            收费项目对象
	 * @return
	 */
	@WebMethod
	public List<InfoSend> queryInfoSendForList(@WebParam InfoSend paramsOb);

	/**
	 * 查询信息发布列表。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public Paging<InfoSend> queryInfoSendForPaging(@WebParam InfoSend paramsOb, int pageNum, int pageSize);

	/**
	 * 更新信息发布。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public void updateInfoSend(@WebParam InfoSend entity,String deviceTypeIds);

	/**
	 * 保存信息发布。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public void saveInfoSend(@WebParam InfoSend entity,String deviceTypeIds);

	/**
	 * 获取信息发布对象。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public InfoSend getInfoSend(@WebParam String entityId);

	/**
	 * 删除信息发布对象。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public void DelInfoSend(@WebParam InfoSend entity);

	/**
	 * 
	 * 方法的描述: 保存门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-22 下午3:16:46
	 * @param gateCardVisit
	 */
	@WebMethod
	public void saveGateCardVisit(GateCardVisit gateCardVisit);

	/**
	 * 
	 * 方法的描述: 保存门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-22 下午5:30:26
	 * @param gateCardVisit
	 */
	@WebMethod
	public void updateGateCardVisit(GateCardVisit gateCardVisit);

	/**
	 * 
	 * 方法的描述:删除门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-22 下午3:17:20
	 * @param gateCardVisit
	 */
	@WebMethod
	public void removeGateCardVisit(GateCardVisit gateCardVisit);

	/**
	 * 
	 * 方法的描述: 查询所有门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-22 下午3:18:37
	 * @param gateCardVisit
	 * @return List
	 */
	@WebMethod
	public List<GateCardVisit> queryAllGateCardVisits(GateCardVisit gateCardVisit);

	/**
	 * 
	 * 方法的描述: 查询身份证刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 下午4:29:31
	 * @param idCardVisit
	 * @return List
	 */
	@WebMethod
	public List<IdCardVisit> queryIdCardVisits(IdCardVisit idCardVisit);

	/**
	 * 
	 * 方法的描述: 保存或修改身份证刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:20:37
	 * @param IdCardVisit
	 */
	@WebMethod
	public void saveOrUpdateIdCardVisit(IdCardVisit IdCardVisit);

	/**
	 * 
	 * 方法的描述: 删除身份证刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:23:19
	 * @param IdCardVisit
	 */
	@WebMethod
	public void removeIdCardVisit(IdCardVisit IdCardVisit);

	/**
	 * 
	 * 方法的描述: 查询报警信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:20:33
	 * @param alarm
	 * @return List
	 */
	@WebMethod
	public List<Alarm> queryAlarms(Alarm alarm);

	/**
	 * 
	 * 方法的描述:查询报警信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:21:00
	 * @param alarmId
	 * @return Alarm
	 */
	@WebMethod
	public Alarm queryAlarmById(String alarmId);

	/**
	 * 
	 * 方法的描述: 保存或者修改报警信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:22:57
	 * @param alarm
	 */
	@WebMethod
	public void saveOrUpdateAlarm(Alarm alarm);

	/**
	 * 
	 * 方法的描述: 删除报警信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:23:22
	 * @param alarm
	 */
	@WebMethod
	public void removeAlarm(Alarm alarm);

	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:30:09
	 * @return List
	 */
	@WebMethod
	public List<AlarmType> queryAlarmType(AlarmType alarmType);

	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:30:28
	 * @param String
	 *            alarmType
	 * @return AlarmType
	 */
	@WebMethod
	public AlarmType queryAlarmTypeById(String alarmType);

	/**
	 * 
	 * 方法的描述: 保存或修改报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:31:18
	 * @param alarmType
	 */
	@WebMethod
	public void saveOrUpdateAlarmType(AlarmType alarmType);

	/**
	 * 
	 * 方法的描述: 删除报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:31:53
	 * @param AlarmType
	 *            alarmType
	 */
	@WebMethod
	public void removeAlarmType(AlarmType alarmType);

	/**
	 * 根据小区ID查询下面的区域、楼宇。
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@WebMethod
	public List<DistrictData> queryAreaData(@WebParam InfoSend ob);

	/**
	 * 
	 * 方法的描述:分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:21:01
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 * @param orderString
	 * @return
	 */
	@WebMethod
	public Paging<AddressBook> queryAddressBookForPaging(@WebParam int pageNum, @WebParam int pageSize, @WebParam String condition, @WebParam String orderString, @WebParam final Object... values);

	/**
	 * 
	 * 方法的描述:
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:22:28
	 * @param addressBook
	 * @return
	 */
	@WebMethod
	public boolean saveOrUpdateAdressBook(@WebParam AddressBook addressBook);

	/**
	 * 
	 * 方法的描述: 根据id删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:23:48
	 * @param id
	 * @return
	 */
	@WebMethod
	public boolean deleteAdressBookById(@WebParam String deviceNo,@WebParam Long id);

	/**
	 * 分页查询物业投诉信息
	 * 
	 * @param condition
	 * @param endComplaintDate
	 * @param endProcessingDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<Complaint> queryPropertyComplaintForPaging(@WebParam Complaint condition, @WebParam Date endComplaintDate,
			@WebParam Date endProcessingDate, @WebParam int pageNum, @WebParam int pageSize, @WebParam boolean excludeUncommitted);

	/**
	 * 分页查询业主投诉信息
	 * 
	 * @param condition
	 * @param endComplaintDate
	 * @param endProcessingDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<Complaint> queryOwnerComplaintForPaging(@WebParam Complaint condition, @WebParam Date endComplaintDate,
			@WebParam Date endProcessingDate, @WebParam int pageNum, @WebParam int pageSize, @WebParam boolean excludeUncommitted);

	/**
	 * 获取单条投诉信息
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public Complaint getComplaint(@WebParam String id);

	/**
	 * 删除投诉信息
	 * 
	 * @param id
	 */
	@WebMethod
	public void removeComplaint(@WebParam String id);
	
	/**
	 * 根据onlyValue来删除业主投诉信息(用于app的接口)
	 * 
	 * @param onlyValue
	 */
	@WebMethod
	public void removeOwnerComplaint(@WebParam String onlyValue);

	/**
	 * 回复投诉信息给出处理意见
	 * 
	 * @param id
	 * @param processingLoginName
	 * @param suggestion
	 */
	@WebMethod
	public void replySuggestion(@WebParam String id, @WebParam String processingLoginName, @WebParam String suggestion);

	/**
	 * 新增物业投诉信息
	 * 
	 * @param complaint
	 */
	@WebMethod
	public void addPropertyComplaint(@WebParam Complaint complaint);

	/**
	 * 新增业主投诉信息
	 * 
	 * @param complaint
	 */
	@WebMethod
	public void addOwnerComplaint(@WebParam Complaint complaint) throws Exception;

	/**
	 * 更新投诉信息
	 * 
	 * @param id
	 * @param title
	 * @param content
	 * @param submit
	 *            是否提交投诉, 否则只是保存投诉(投诉还处于未提交状态)
	 */
	@WebMethod
	public void updateComplaint(@WebParam String id, @WebParam String title, @WebParam String content, @WebParam boolean submit);

	/**
	 * 查询投诉总数
	 * 
	 * @param condition
	 * @param today 是查询当天还是全部
	 * @return
	 */
	@WebMethod
	public long queryComplaintCount(@WebParam Complaint condition, @WebParam boolean today);

	/**
	 * 查询最近的投诉信息
	 * 
	 * @param condition
	 * @param quantity
	 * @param today 是查询当天还是全部
	 * @return
	 */
	@WebMethod
	public List<Complaint> queryRecentComplaint(@WebParam Complaint condition,
			@WebParam int quantity, @WebParam boolean today);

	/**
	 * 查询广告列表，分页。
	 * 
	 * @param ad
	 *            广告对象
	 * @param pageNum
	 *            待查询的页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@WebMethod
	public Paging<Advertisement> queryAdsForPaging(@WebParam Advertisement ad, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * 获取广告详细信息。
	 * 
	 * @param adId
	 *            广告编号
	 * @return
	 */
	@WebMethod
	public Advertisement getAdDetail(@WebParam String adId);

	/**
	 * 即时发布广告。
	 * 
	 * @param adId
	 *            广告编号
	 * @param publishedUser
	 *            发布人员登录名
	 */
	@WebMethod
	public void publishAd(@WebParam String adId, @WebParam String publishedUser);

	/**
	 * 定时发布广告。
	 * 
	 * @param adId
	 *            广告编号
	 * @param publishedTime
	 *            发布时间
	 * @param publishedUser
	 *            发布人员登录名
	 */
	@WebMethod
	public void publishTimingAd(@WebParam String adId, @WebParam Date publishedTime, @WebParam String publishedUser);

	/**
	 * 新增广告。
	 */
	@WebMethod
	public void addAd(@WebParam Advertisement ad);

	/**
	 * 更新广告。
	 */
	@WebMethod
	public void updateAd(@WebParam Advertisement ad);

	/**
	 * 删除广告。
	 * 
	 * @param adId
	 *            广告编号
	 */
	@WebMethod
	public void removeAd(@WebParam String adId);

	/**
	 * 停止已投放的广告。
	 * 
	 * @param adId
	 *            广告编号
	 */
	@WebMethod
	public void stopAd(@WebParam String adId);

	/**
	 * 恢复已停止的广告。
	 * 
	 * @param adId
	 *            广告编号
	 */
	@WebMethod
	public void resumeAd(@WebParam String adId);

	/**
	 * 获取所有广告类型。
	 * 
	 * @return
	 */
	@WebMethod
	public List<AdType> queryAllAdTypes();

	/**
	 * 获取所有广告投放目标系统。
	 * 
	 * @return
	 */
	@WebMethod
	public List<AdSys> queryAllAdSystems();

	/**
	 * 通过广告目标系统代码获取广告投放目标位置。
	 * 
	 * @return
	 */
	@WebMethod
	public List<AdLocation> queryAdLocations(@WebParam String sysCode);

	/**
	 * 通过广告编号获取相关的投放目标。
	 * 
	 * @param adId
	 *            广告编号
	 * @return
	 */
	@WebMethod
	public List<AdTarget> queryAdTargets(@WebParam String adId);

	/**
	 * 
	 * 方法的描述: 根据设备编号获取场景
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:10:43
	 * @param deviceNo
	 * @return 场景集合
	 */
	@WebMethod
	public List<Scene> getSceneByDeviceNo(@WebParam String deviceNo);

	/**
	 * 
	 * 方法的描述: 根据场景模式获得场景
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:18:10
	 * @param deviceNo
	 *            设备编号
	 * @param mode
	 *            场景模式
	 * @return 场景
	 */
	@WebMethod
	public Scene getSceneByDeviceNoAndMode(@WebParam String deviceNo, @WebParam String mode);

	/**
	 * 
	 * 方法的描述: 发送场景监控命令
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午5:08:37
	 * @param deviceNo
	 *            设备编号
	 * @return
	 */
	@WebMethod
	public boolean sendSceneMonitorCommand(@WebParam String deviceNo);

	/**
	 * 
	 * 方法的描述: 根据设备编号获取场景设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:10:43
	 * @param deviceNo
	 * @return 场景集合
	 */
	@WebMethod
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, List<SceneDevice>> getSceneDeviceByDeviceNo(@WebParam String deviceNo);

	/**
	 * 
	 * 方法的描述: 根据设备编号获取场景设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:10:43
	 * @param deviceNo
	 * @return 场景集合
	 */
	public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo);

	/**
	 * @author: kouy 查询报警信息列表。
	 * @param paramsOb
	 *            报警信息对象
	 * @return
	 */
	@WebMethod
	public Paging<Alarm> queryAlarmForPaging(@WebParam Alarm paramsOb, @WebParam int pageNum, @WebParam int pageSize);

	/**
	 * @author: kouy 更新报警信息。
	 * @param paramsOb
	 *            报警信息对象
	 * @return
	 */
	@WebMethod
	public void updateAlarm(@WebParam Alarm entity);

	/**
	 * @author: kouy 保存报警信息。
	 * @param paramsOb
	 *            报警信息对象
	 * @return
	 */
	@WebMethod
	public void saveAlarm(@WebParam Alarm entity);

	/**
	 * @author: kouy 获取报警信息对象。
	 * @param paramsOb
	 *            报警信息对象
	 * @return
	 */
	@WebMethod
	public Alarm getAlarm(@WebParam String entityId);

	/**
	 * @author: kouy 删除报警信息对象。
	 * @param paramsOb
	 *            报警信息对象
	 * @return
	 */
	@WebMethod
	public void DelAlarm(@WebParam Alarm entity);

	/**
	 * 
	 * 方法的描述: 根据小区ID获取完整路径信息
	 * 
	 * @author: dehua ye
	 * @version: 0.1
	 * @date: 2012-5-7 下午9:23:00
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public String getCompletePosition(@WebParam String comId);

	/**
	 * 
	 * 方法的描述: 查询Rss服务并分页
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:34:59
	 * @param rssServer
	 * @param pageNum
	 * @param pageSize
	 * @return Paging
	 */
	@WebMethod
	public Paging<RssServer> queryRssServerForPaging(RssServer rssServer, int pageNum, int pageSize);

	/**
	 * 
	 * 方法的描述: 查询RssServer
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:36:46
	 * @param rssId
	 * @return
	 */
	@WebMethod
	public RssServer queryRssServerById(Long rssId);

	/**
	 * 
	 * 方法的描述:修改Rss服务信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:39:01
	 * @param rssServer
	 */
	@WebMethod
	public void updateRssServer(RssServer rssServer);

	/**
	 * 
	 * 方法的描述:删除Rss服务信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-4 下午3:39:48
	 * @param rssServer
	 */
	@WebMethod
	public void removeRssServer(RssServer rssServer);

	/**
	 * 远程开锁
	 * 
	 * @param targetDeviceCode
	 * @param personalDeviceCode
	 * @param personalDevicePwd
	 * @return
	 */
	@WebMethod
	public boolean remoteUnlock(@WebParam String targetDeviceCode, @WebParam String personalDeviceCode, @WebParam String personalDevicePwd, @WebParam String userType);

	/**
	 * 
	 * 方法的描述: 查询单元下的公共设备和业主自己的设备
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-5 下午2:44:44
	 * @param unitId
	 * @param roomId
	 * @return
	 */
	@WebMethod
	public List<Device> queryOwnerUnitDevice(String unitId, String roomId);

	/**
	 * 
	 * 方法的描述: 查询小区所有公共设备（非业主设备）
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-5 下午2:45:22
	 * @param distrcitId
	 * @return
	 */
	@WebMethod
	public List<Device> queryPropertyDevice(String distrcitId);

	/**
	 * 
	 * 方法的描述: 查询天气信息并分页
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-5 下午8:34:27
	 * @param weather
	 * @param pageNum
	 * @param pageSize
	 * @return paging
	 */
	@WebMethod
	public Paging<Weather> queryWeatherForPaging(Weather weather, int pageNum, int pageSize);

	/**
	 * 
	 * 方法的描述: 修改天气信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-5 下午8:36:33
	 * @param weather
	 */
	@WebMethod
	public void updateWeather(Weather weather);

	/**
	 * 
	 * 方法的描述: 查询天气信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-6 上午10:45:23
	 * @param id
	 * @return Weather
	 */
	@WebMethod
	public Weather queryWeatherById(Long id);

	/**
	 * 
	 * 方法的描述: 查询所有符合条件的设备
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-6 上午9:12:34
	 * @param districtIds
	 * @param deviceTypes
	 * @return list
	 */
	@WebMethod
	public List<Device> queryDevicesList(List<String> districtIds, List<String> deviceTypes);

	
	/**
	 * 
	 * 方法的描述: 发送场景设备控制命令
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-7 下午4:56:04
	 * @param deviceNo 目标设备编号
	 * @param list 监控设备列表
	 * @param sceneId 场景id
	 * @return true表示成功，false表示失败
	 */
	@WebMethod
	public boolean sendSceneDeviceMonitorCommand(@WebParam String deviceNo, @WebParam List<SceneDevice> list,@WebParam Long sceneId);
	/**
     * 
     * 方法的描述: 根据设备编号获得当前使用场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:51:08
     * @param deviceNo 设备编号,sceneKind
     * @return
     */
	@WebMethod
    public Scene getIsUsedSceneByDeviceNo(@WebParam String deviceNo,@WebParam String sceneKind);
    /**
     * 
     * 方法的描述: 根据房号编号获得当前使用场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:51:14
     * @param roomNo 房号编号
     * @return
     */
	@WebMethod
    public Scene getIsUsedSceneByRoomNo(@WebParam String roomNo);
	
	/**
     * 
     * 方法的描述: 根据房号编号获取场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param roomNo房号编号
     * @return 场景集合
     */
	@WebMethod
    public List<Scene> getSceneByRoomNo(@WebParam String roomNo);
	/**
	 * 
	 * 方法的描述: 根据房号查询设备中的某一个设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-7 上午10:26:44
	 * @param roomId
	 * @return
	 */
	@WebMethod
	public Device queryDeviceByRoomId(String roomId);
	/**
     * 
     * 方法的描述: 发送布防或撤防命令
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午9:10:19
     * @param deviceNo 设备编号
     * @param sceneId 场景id
     * @param actionWay 方式：0表示撤防，1表示布防
     * @return 成功为true,失败为false
     */
	@WebMethod
    public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo,Long sceneId,String actionWay,String sceneType);

	
	/**
	 * 
	 * 方法的描述:hadoop文件分页 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午10:58:47
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<LocalHdfs> queryLocalHdfsForPage(int pageNum, int pageSize,String condition,String orderString);
	/**
	 * 
	 * 方法的描述: hadoop文件同步任务分页
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:18
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<HdfsTask> queryHdfsTaskForPage(int pageNum, int pageSize,String condition,String orderString);

	
	/**
	 * 查询报修列表。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public List<RequestRepair> queryRequestRepairForList(@WebParam RequestRepair paramsOb);
	/**
	 * 查询报修列表。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public Paging<RequestRepair> queryRequestRepairForPaging(@WebParam RequestRepair paramsOb, @WebParam int pageNum, @WebParam int pageSize);
	/**
	 * 更新报修。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public void updateRequestRepair(@WebParam RequestRepair entity) ;
	/**
	 * 保存报修。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public void saveRequestRepair(@WebParam RequestRepair entity);
	/**
	 * 获取报修对象。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public RequestRepair getRequestRepair(@WebParam String entityId);
	/**
	 * 删除报修对象。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public void DelRequestRepair(@WebParam RequestRepair entity);
	/**
	 * 查询5条业主未读信息量。
	 * @param device 信息接收对象
	 * @return
	 */
	@WebMethod
	public List<InfoReceiver> queryInfoReceiverForIndex(@WebParam InfoReceiver paramsOb);
	/**
	 * 登录成功首页查询相关信息发布
	 * @param device 信息发布对象
	 * @return
	 */
	@WebMethod
	public List<InfoSend> queryInfoSendForIndex(@WebParam InfoSend paramsOb);
	/**
	 * 登录成功首页查询相关信息发布数量统计
	 * @param device 信息发布对象
	 * @return
	 */
	@WebMethod
	public Long getInfoCount(@WebParam InfoSend paramsOb);
	/**
	 * @author: kouy  
	 * 查询业主总报警数
	 * @param paramsOb 报警信息对象
	 * @return
	 */
	@WebMethod
	public Long getOwnerAlarmCount(@WebParam Alarm paramsOb);
	
	/**
	 * 查询报修数量。
	 * @param device 报修对象
	 * @return
	 */
	@WebMethod
	public Long getRequestRepairCount(@WebParam RequestRepair paramsOb) ;
	/**
	 * 缴费通知。
	 * @return
	 */
	@WebMethod
	public boolean publishChargeInfo(@WebParam Push pushOb,@WebParam String roomId);

	/**
	 * 
	 * 方法的描述: 分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 *            条件不包含
	 * @return
	 */
	@WebMethod
	public Paging<Push> queryPushForPagingByKeyValue(int pageNum, int pageSize, String condition, String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述:hadoop文件分页 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午10:58:47
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<LocalHdfs> queryLocalHdfsForPageByKeyValue(int pageNum, int pageSize,String condition,String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述: hadoop文件同步任务分页
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:18
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@WebMethod
	public Paging<HdfsTask> queryHdfsTaskForPageByKeyValue(int pageNum, int pageSize,String condition,String orderString,final Object... values);

	
	/**
	 * 
	 * 方法的描述: 查询设备数，如果参数为空则不作为查询条件
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-12 下午9:03:07
	 * @param districtId
	 * @param status
	 * @return Long
	 */
	@WebMethod
	public Long deviceCount(@WebParam String districtId,@WebParam String status);

	/**
	 * 
	 * 方法的描述: 查询留言记录数
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:49:08
	 * @param callRecord
	 * @return
	 */
	public Long callRecordCount(@WebParam CallRecord callRecord);
	
	/**
	 * 
	 * 方法的描述: 根据小区ID查询城市名称
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-14 上午10:26:08
	 * @param comId:小区ID
	 * @return
	 */
	@WebMethod
	public String getCityNameByDistrictId(@WebParam String comId);

	/**
	 * 
	 * 方法的描述: 分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 *            条件
	 * @return
	 */
	public Paging<PushFinish> queryPushFinishForPagingByKeyValue(int pageNum, int pageSize, String condition, String orderString,final Object...values);
	

	
	/**
	 * 
	 * 方法的描述: 查询操作日志并分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 下午2:19:12
	 * @param operationLog
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<OperationLog> queryOperationLogPaging(OperationLog operationLog, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述: 根据城市名称查询天气数据
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-14 上午11:29:10
	 * @param cityName
	 * @return WeatherReportData
	 */
	public WeatherReportData queryReportDataByCityName(String cityName);
	/**
	 * 
	 * 方法的描述: 获得文件服务器地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-15 上午10:50:14
	 * @return
	 */
	@WebMethod
	public String getFileServerUrl();
	
	/**
	 * 
	 * 获得从外网访问文件服务器地址 。
	 * @author kouy
	 * @since 1.0 2012-11-6
	 * @return
	 */
	public String getExternalFileServerUrl();
	
	/**
	 * 
	 * 方法的描述: 根据设备编号获得物业公司信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-19 下午2:41:58
	 * @param deviceCode 设备编号
	 * @return
	 */
	@WebMethod
	public PropertyCompanyInfo getPropertyCompanyInfoByDeviceCode(@WebParam String deviceCode);
	
	/**
	 * 
	 * 方法的描述: 查询未读消息数量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午2:32:26
	 * @param paramsOb
	 * @return
	 */
	@WebMethod
	public Long getNoReadReceiverCount(@WebParam InfoReceiver paramsOb);
	/**
	 * 
	 * 方法的描述: 查询户户通设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-20 下午2:24:32
	 * @param device
	 * @return list
	 */
	@WebMethod
	public List<Device> queryFamilyDevice(@WebParam Device device);
	/**
	 * 
	 * 方法的描述: 根据设备编号获得所有的通讯录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午3:17:27
	 * @param deviceNo
	 * @return
	 */
	@WebMethod
	public List<AddressBook> listAllAddressBookByDeviceNo(@WebParam String deviceNo);
	/**
	 * 
	 * 方法的描述: 根据设备编号查询所在位置的监控设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-26 下午4:22:11
	 * @param device
	 * @return
	 */
	@WebMethod
	public List<Device> queryMonitorDevice(@WebParam Device device);
	/**
	 * 
	 * 方法的描述: 根据参数查询返回收费项目对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-2 上午10:33:47
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	@WebMethod
	public ChargeType queryChargeTypeByParams(@WebParam ChargeType paramsOb);
	/**
	 * 
	 * 方法的描述: 根据条件查询收费计算方式对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费计算方式对象
	 * @return
	 */
	@WebMethod
	public ChargeCalMode queryChargeCalModeByParams(@WebParam ChargeCalMode paramsOb);
	/**
	 * 
	 * 方法的描述: 根据条件查询货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费货币单位对象
	 * @return
	 */
	@WebMethod
	public ChargeMonetaryUnit queryChargeMonetaryUnitByParams(@WebParam ChargeMonetaryUnit paramsOb);
	/**
     * 
     * 方法的描述: 批量保存或更新场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:34:22
     * @param sceneList
     * @return
     */
    public boolean saveOrUpdateScene(List<Scene> sceneList);
    /**
     * 
     * 方法的描述: 批量 保存或更新场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:38:16
     * @param sceneDeviceList 场景设备集合
     * @return
     */
    public boolean saveOrUpdateSceneDevice(List<SceneDevice> sceneDeviceList);
    /**
     * 
     * 方法的描述:  根据设备编号和场景id删除场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-7-5 下午5:47:46
     * @param deviceNo
     * @param sceneId
     * @return
     */
     public boolean deleteSceneByDeviceNoAndSceneId(String deviceNo,String sceneId);
     /**
      * 
      * 方法的描述: 根据设备编号和设备Id进行家具设备删除 
      * @author: kouy  
      * @version: 0.1
      * @date: 2012-7-5 下午5:59:06
      * @param deviceNo
      * @param deviceId
      * @return
      */
     public boolean deleteSceneDeviceByDeviceNoAndDeviceId(String deviceNo,String deviceId);

     /**
      * 判断单元是否包含房号
      * 
      * @param cellId
      * @return
      */
     public boolean hasHouse(String cellId);
     
     /**
      * 判断楼宇是否包含单元
      * 
      * @param buildingId
      * @return
      */
     public boolean hasCell(String buildingId);
     
     /**
      * 判断区域是否包含楼宇
      * 
      * @param regionId
      * @return
      */
     public boolean hasBuilding(String regionId);
     
     /**
      * 判断小区是否包含区域
      * 
      * @param districtId
      * @return
      */
     public boolean hasRegion(String districtId);
     
 	/**
 	 * 根据设备编号查询业主投诉信息
 	 * 
 	 * @param deviceCode
 	 * @return
 	 */
 	public List<Complaint> queryComplaintByDeviceCode(String deviceCode);
 	
 	/**
 	 * 
 	 * 方法的描述: 根据房间号判断是否有关联的设备
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-7-9 下午3:43:17
 	 * @param roomId
 	 * @return
 	 */
 	public boolean isHaveDevice(String roomId);

 	/**
 	 * 
 	 * 方法的描述: 分页查询
 	 * @author: kouy  
 	 * @version: 0.1
 	 * @date: 2012-7-24 下午2:41:26
 	 * @param pageNum
 	 * @param pageSize
 	 * @param condition
 	 * @param orderString
 	 * @param values
 	 * @return
 	 */
 	@WebMethod
 	public Paging<ClientLog> queryClientLogForPaging(
			 int pageNum, int pageSize,String condition,String orderString,final Object... values);
 	/**
 	 * 
 	 * 方法的描述: 根据id查询
 	 * @author: kouy  
 	 * @version: 0.1
 	 * @date: 2012-7-24 下午2:41:40
 	 * @param id
 	 * @return
 	 */
 	@WebMethod
 	public ClientLog queryClientLogById(Long id);

 	
 	/**
	 * 验证指定小区下的区域名称是否存在。
	 * 1）如果区域编号为空，则验证是否存在区域名称；<br/>
	 * 2）如果区域编号不为空，则验证区域名称对应的区域编号是否和当前区域编号相同；<br/>
	 * @param districtId 小区编号
	 * @param regionId 区域编号
	 * @param regionName 区域名称
	 * @return
	 */
 	@WebMethod
	public boolean existRegionName(@WebParam String districtId, 
			@WebParam String regionId, @WebParam String regionName);
	
	/**
	 * 验证指定区域下的楼宇名称是否存在。
	 * 1）如果楼宇编号为空，则验证是否存在楼宇名称；<br/>
	 * 2）如果楼宇编号不为空，则验证楼宇名称对应的楼宇编号是否和当前楼宇编号相同；<br/>
	 * @param regionId 区域编号
	 * @param buildingId 楼宇编号
	 * @param buildingName 楼宇名称
	 * @return
	 */
 	@WebMethod
	public boolean existBuildingName(@WebParam String regionId, 
			@WebParam String buildingId, @WebParam String buildingName);
	
	/**
	 * 验证指定楼宇下的单元名称是否存在。
	 * 1）如果单元编号为空，则验证是否存在单元名称；<br/>
	 * 2）如果单元编号不为空，则验证单元名称对应的单元编号是否和当前单元编号相同；<br/>
	 * @param buildingId 楼宇编号
	 * @param cellId 单元编号
	 * @param cellName 单元名称
	 * @return
	 */
 	@WebMethod
	public boolean existCellName(@WebParam String buildingId, 
			@WebParam String cellId, @WebParam String cellName);
	
	/**
	 * 验证指定单元下的房屋名称是否存在。
	 * 1）如果房屋编号为空，则验证是否存在房屋名称；<br/>
	 * 2）如果房屋编号不为空，则验证房屋名称对应的房屋编号是否和当前房屋编号相同；<br/>
	 * @param cellId 单元编号
	 * @param houseId 房屋编号
	 * @param houseName 房屋名称
	 * @return
	 */
 	@WebMethod
	public boolean existHouseName(@WebParam String cellId, 
			@WebParam String houseId, @WebParam String houseName);
 	
 	/**
	 * 通过组织编号列表查询对应的小区编号列表。
	 * @param groupIds 组织编号列表
	 * @return
	 */
 	@WebMethod
	public List<String> queryDistrictIds(@WebParam List<String> groupIds);
 	
 	/**
 	 * 方法的描述: 保存设备开锁密码
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-3 下午3:30:57
 	 * @param devicePassword
 	 */
 	@WebMethod
 	public void saveDevicePassword(DevicePassword devicePassword);
 	
 	/**
 	 * 方法的描述: 删除设备开锁密码
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-3 下午3:36:58
 	 * @param devicePassword
 	 */
 	@WebMethod
 	public void delDevicePassword(DevicePassword devicePassword);
 	
 	/**
 	 * 方法的描述: 查询设备开锁密码
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-3 下午3:36:58
 	 * @param deviceNo
 	 */
 	@WebMethod
 	public List<DevicePassword> queryDevicePassword(String deviceNo);
 	
 	/**
 	 * 方法的描述: 根据Id查询
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-3 下午3:41:24
 	 * @param id
 	 * @return
 	 */
 	@WebMethod
 	public DevicePassword queryDevicePasswordById(Long id);

 	/**
	 * 判断系统管理用户的身份证是否存在。
	 * 1）如果用户编号为空，则验证是否存在身份证；<br/>
	 * 2）如果用户编号不为空，则验证身份证对应的用户编号是否和当前用户编号相同；<br/>
	 * @param userId 用户编号
	 * @param idCard 身份证
	 * @return
	 */
 	@WebMethod
 	public boolean existIdCardForSaUser(@WebParam String userId, @WebParam String idCard);
 	
 	/**
	 * 判断物业管理用户的身份证是否存在。
	 * 1）如果用户编号为空，则验证是否存在身份证；<br/>
	 * 2）如果用户编号不为空，则验证身份证对应的用户编号是否和当前用户编号相同；<br/>
	 * @param userId 用户编号
	 * @param idCard 身份证
	 * @return
	 */
 	@WebMethod
 	public boolean existIdCardForPaUser(@WebParam String userId, @WebParam String idCard);
 	
 	/**
	 * 判断业主用户的身份证是否存在。
	 * 1）如果用户编号为空，则验证是否存在身份证；<br/>
	 * 2）如果用户编号不为空，则验证身份证对应的用户编号是否和当前用户编号相同；<br/>
	 * @param userId 用户编号
	 * @param idCard 身份证
	 * @return
	 */
 	@WebMethod
 	public boolean existIdCardForOwnerUser(@WebParam String userId, @WebParam String idCard);
 	/**
	 * 
	 * 方法的描述: 查询计算单位列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:57:07
	 * @param paramsOb:收费项目对象
	 * @return
	 */
 	@WebMethod
	public List<ChargeCalUnit> queryChargeCalUnitForList(@WebParam ChargeCalUnit paramsOb);
	/**
	 * 
	 * 方法的描述: 查询计算单位列表(分页)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:02:17
	 * @param paramsOb:计算单位对象
	 * @param pageNum:页码
	 * @param pageSize:显示条数
	 * @return
	 */
 	@WebMethod
	public Paging<ChargeCalUnit> queryChargeCalUnitForPaging(@WebParam ChargeCalUnit paramsOb,@WebParam  int pageNum,@WebParam  int pageSize);
	/**
	 * 
	 * 方法的描述: 更新计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:05
	 * @param entity:计算单位对象
	 */
 	@WebMethod
	public void updateChargeCalUnit(@WebParam ChargeCalUnit entity) ;
	/**
	 * 
	 * 方法的描述: 保存计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:41
	 * @param entity:计算单位对象
	 */
 	@WebMethod
	public void saveChargeCalUnit(@WebParam ChargeCalUnit entity);
	/**
	 * 
	 * 方法的描述: 获取单个计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:05:00
	 * @param entityId:计算单位主键
	 * @return
	 */
 	@WebMethod
	public ChargeCalUnit getChargeCalUnit(@WebParam String entityId);
	/**
	 * 
	 * 方法的描述: 删除计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:07:04
	 * @param entity:计算单位对象
	 */
 	@WebMethod
	public void DelChargeCalUnit(@WebParam ChargeCalUnit entity);
	/**
	 * 
	 * 方法的描述: 根据条件查询计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：计算单位对象
	 * @return
	 */
 	@WebMethod
	public ChargeCalUnit queryChargeCalUnitByParams(@WebParam ChargeCalUnit paramsOb);
 	/**
	 * 查询文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：文件上传日志对象
	 * @return
	 */
 	@WebMethod
	public List<FileUploadLog> queryFileUploadLogForList(@WebParam FileUploadLog paramsOb);
	/**
	 * 查询文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：文件上传日志对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
 	@WebMethod
	public Paging<FileUploadLog> queryFileUploadLogForPaging(@WebParam FileUploadLog paramsOb, @WebParam int pageNum,@WebParam int pageSize);
	/**
	 * 
	 * 方法的描述: 获取单个文件上传日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：文件上传日志对象主键
	 * @return
	 */
 	@WebMethod
	public FileUploadLog getFileUploadLog(@WebParam String entityId);
 	/**
 	 * 
 	 * 方法的描述:根据id查询云存储文件 
 	 * @author: kouy  
 	 * @version: 0.1
 	 * @date: 2012-8-9 上午11:49:01
 	 * @param id
 	 * @return
 	 */
 	@WebMethod
	public LocalHdfs queryLocalHdfsById(@WebParam Long id);
 	/**
 	 * 
 	 * 方法的描述: 根据id查询云存储任务 
 	 * @author: kouy  
 	 * @version: 0.1
 	 * @date: 2012-8-9 上午11:49:32
 	 * @param id
 	 * @return
 	 */
 	@WebMethod
	public HdfsTask queryHdfsTaskById(@WebParam Long id);
 	
 	/**
	 * 
	 * 方法的描述: 查询业主可以开锁的设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-10 下午3:50:58
	 * @param unitId
	 * @param districtId
	 * @return
	 */
 	@WebMethod
	public List<Device> queryOwnerUnlockDevice(@WebParam String unitId, @WebParam String districtId);
 	
	/**
	 * 方法的描述: 查询小区内的所有单元门口机和围墙机
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-11 上午11:37:55
	 * @param device
	 * @return
	 */
 	@WebMethod
	public List<Device> queryGateCardDevices(@WebParam Device device);
 	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：设备注册日志对象
	 * @return
	 */
 	@WebMethod
	public List<DiviceRegeditLog> queryDiviceRegeditLogForList(@WebParam DiviceRegeditLog paramsOb);
	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：设备注册日志对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
 	@WebMethod
	public Paging<DiviceRegeditLog> queryDiviceRegeditLogForPaging(@WebParam DiviceRegeditLog paramsOb,@WebParam  int pageNum,@WebParam  int pageSize);
	/**
	 * 保存设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:设备注册日志对象
	 */
 	@WebMethod
	public void saveDiviceRegeditLog(@WebParam DiviceRegeditLog entity);
	/**
	 * 
	 * 方法的描述: 获取单个设备注册日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：设备注册日志对象主键
	 * @return
	 */
 	@WebMethod
	public DiviceRegeditLog getDiviceRegeditLog(@WebParam String entityId);
 	
 	/**
 	 * 方法的描述: 保存并推送本设备的设备开锁密码到本设备所属单元的所有门口机
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-15 下午6:49:11
 	 * @param devicePassword
 	 */
 	@WebMethod
 	public void saveAndPushDevicePassword(@WebParam DevicePassword devicePassword);
 	
 	/**
	 * 
	 * 方法的描述: 根据房号信息和设备类型，查询设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-21 上午11:40:29
	 * @param deviceType
	 * @param districtNo
	 * @param regionNo
	 * @param buildingNo
	 * @param unitNo
	 * @param roomNo
	 * @return
	 */
 	@WebMethod
	public Device queryDeviceByRoomNo(@WebParam String districtNo,@WebParam String regionNo,@WebParam String buildingNo,@WebParam String unitNo,@WebParam String roomNo);
 	
 	/**
 	 * 方法的描述: 根据门卡卡号查询门卡
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-8-26 上午10:34:46
 	 * @param cardNo
 	 * @return
 	 */
 	@WebMethod
 	public GateCard queryGateCardByCardNo(@WebParam String districtId,@WebParam String cardNo);
 	
 	/**
 	 * 方法的描述: 查询设备开锁密码
 	 * @author: ykou  
 	 * @version: 0.1
 	 * @date: 2012-9-10 下午2:08:38
 	 * @param targetDeviceNo 目标设备设备编号
 	 * @return
 	 */
 	@WebMethod
 	public List<DevicePassword> queryPasswordByTargetDeviceNo(@WebParam String targetDeviceNo);
 	/**
	 * 查询信息接收设备列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：信息接收设备对象
	 * @return
	 */
 	@WebMethod
	public List<InfoReceiverDevice> queryInfoReceiverDeviceForList(@WebParam InfoReceiverDevice paramsOb);
	/**
	 * 查询信息接收设备列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：信息接收设备对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
 	@WebMethod
	public Paging<InfoReceiverDevice> queryInfoReceiverDeviceForPaging(@WebParam InfoReceiverDevice paramsOb,@WebParam  int pageNum, @WebParam int pageSize);
	/**
	 * 更新信息接收设备
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:信息接收设备对象
	 */
 	@WebMethod
	public void updateInfoReceiverDevice(@WebParam InfoReceiverDevice entity) ;
	/**
	 * 保存信息接收设备
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:信息接收设备对象
	 */
 	@WebMethod
	public void saveInfoReceiverDevice(@WebParam InfoReceiverDevice entity);
	/**
	 * 
	 * 方法的描述: 获取单个信息接收设备对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：信息接收设备对象主键
	 * @return
	 */
 	@WebMethod
	public InfoReceiverDevice getInfoReceiverDevice(@WebParam String entityId);
	/**
	 * 
	 * 方法的描述: 删除信息接收设备对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：信息接收设备对象
	 */
	public void DelInfoReceiverDevice(@WebParam InfoReceiverDevice entity);
	
	/**
	 * 方法的描述: 删除设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-20 上午11:09:31
	 * @param deviceId
	 */
	@WebMethod
	public boolean removeDeviceById(@WebParam String deviceId);
	
	/**
	 * 方法的描述: 设备是否在线
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-9 上午10:37:21
	 * @param deviceId
	 * @return
	 */
	@WebMethod
	public boolean isDeviceOnline(@WebParam String deviceId);
	
	/**
	 * 方法的描述: 查询场景模式
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-11 上午11:09:40
	 * @param deviceNo
	 * @param sceneKind
	 * @return
	 */
	@WebMethod
	public List<Scene> getScenes(@WebParam String deviceNo,@WebParam String sceneKind);
	
	/**
	 * 方法的描述: 保存或修改App请求日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-19 下午3:37:45
	 * @param appDataLog
	 */
	@WebMethod
	public AppDataLog saveOrUpdateAppDataLog(@WebParam AppDataLog appDataLog);
	
	/**
	 * 方法的描述: 分页查询App数据日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-22 上午10:34:19
	 * @param appDataLog
	 * @param pageNum
	 * @param pageSize
	 * @return Paging
	 */
	@WebMethod
	public Paging<AppDataLog> queryAppDataLogForPaging(@WebParam AppDataLog appDataLog, @WebParam int pageNum, @WebParam int pageSize);
	
	/**
	 * 方法的描述: 根据Id查询日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-19 下午3:37:45
	 * @param id
	 */
	@WebMethod
	public AppDataLog getAppDataLogById(@WebParam Integer id);
	
	/**
	 * 
	 * 方法的描述: 根据设备编号获取场景设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:10:43
	 * @param deviceNo,deviceNo
	 * @return 场景集合
	 */
	public List<SceneDevice> querySceneDeviceByDeviceNoAndSceneId(String deviceNo,Long sceneId);
	
	/**
	 * 
	 * 方法的描述: app下载绝对地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-8 下午5:06:43
	 * @return
	 */
	@WebMethod
	public String getAppDownloadAbsoluteUrl();
	
	/**
	 * 
	 * 方法的描述: web下载绝对地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-8 下午5:06:43
	 * @return
	 */
	@WebMethod
	public String getWebDownloadAbsoluteUrl();
	
	/**
     * 方法的描述: 发送获取场景的命令
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-11-16 下午3:07:18
     * @param deviceNo,sceneKind
     * @return
     */
	@WebMethod
    public boolean sendGetSceneCommand(@WebParam String deviceNo,@WebParam String sceneKind);
	
	/**
     * 方法的描述: 根据页面编号查询电商页面的组件列表
     * @author: ykou  
     * @param pageId
     * @return
     */
	@WebMethod
    public List<Component> queryComponents(@WebParam Component component);
	
	/**
     * 方法的描述: 保存组件
     * @author: ykou  
     * @param pageId
     * @return
     */
	@WebMethod
    public void saveComponent(@WebParam Component component);
	
	/**
     * 方法的描述: 删除组件
     * @author: ykou  
     * @param componentId
     * @return
     */
	@WebMethod
    public void deleteComponent(@WebParam String componentId);
	
	/**
     * 方法的描述: 修改组件
     * @author: ykou  
     * @param component
     * @return
     */
	@WebMethod
    public void updateComponent(@WebParam Component component);
	
	/**
     * 方法的描述: 保存页面
     * @author: ykou  
     * @param page
     * @return
     */
	@WebMethod
    public void savePage(@WebParam PageLayout page);
	
	/**
     * 方法的描述: 删除页面
     * @author: ykou  
     * @param page
     * @return
     */
	@WebMethod
    public void deletePage(@WebParam PageLayout page);
	
	/**
     * 方法的描述: 修改页面
     * @author: ykou  
     * @param page
     * @return
     */
	@WebMethod
    public void updatePage(@WebParam PageLayout page);
	
	/**
     * 方法的描述: 查询页面
     * @author: ykou  
     * @param page
     * @return
     */
	@WebMethod
    public List<PageLayout> queryPages(@WebParam PageLayout page);
	
	@WebMethod
    public Paging<PageLayout> queryPageForPaging(@WebParam PageLayout page, @WebParam int pageNum, @WebParam int pageSize);
	
	@WebMethod
    public Paging<Component> queryComponentForPaging(@WebParam Component component, @WebParam int pageNum, @WebParam int pageSize);

	@WebMethod
    public PageLayout queryPageById(@WebParam String entityId);
	
	@WebMethod
    public Component queryComponentById(@WebParam String entityId);
	
	@WebMethod
    public void savePageContent(@WebParam PageContent pageContent);
	
	@WebMethod
    public void deletePageContentById(@WebParam String updateId);
	
	@WebMethod
    public void deleteAllPageContent(List<PageContent> pageContents);
	
	@WebMethod
    public List<PageContent> queryPageContent(PageContent pageContents);
	
	@WebMethod
	public List<SceneDevice> querySceneDevices(SceneDevice sceneDevice);

	@WebMethod
	public SaUser getSaUser(@WebParam String userId, @WebParam String saUserType);
	
}
