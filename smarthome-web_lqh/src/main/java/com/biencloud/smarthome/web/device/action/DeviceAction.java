package com.biencloud.smarthome.web.device.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.CryptoUtils;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.service.IDeviceSipService;
import com.biencloud.smarthome.web.device.vo.DeviceSipVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.service.IDeviceTypeService;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.log.service.ISystemLogService;
import com.biencloud.smarthome.web.log.vo.SystemLogVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 
 * 类名称：DeviceAction 
 * 类描述： 设备管理动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-1 下午3:34:19
 */
public class DeviceAction extends BaseAction<DeviceVO>{
		
	private static final long serialVersionUID = 3155302979010643718L;
	private IDeviceService deviceService;
	private IDeviceSipService deviceSipService;
	private ISystemLogService systemLogService;
	private IOwnerUserService ownerUserService;
	//设备实体
	private DeviceVO device ;
	//设备ID
	private String deviceId;
	//设备密码
	private String devicePwd;
	//设备新密码
	private String newPasswd;
	//成功标志
	private String successFlag;
	private SystemLogVO systemLog;
	//设备List
	private List<DeviceVO> devices;
	//房号实体
	private CellHouseholdInfoVo cellHouseholdInfo;
	//业主实体
	private OwnerUserVO ownerUser;
	//设备类型List
	private List<DeviceTypeVO> deviceTypes;
	private IDeviceTypeService deviceTypeService;
	private ISysParamService sysParamService;
	//文件服务器地址
	private String fileServerUrl;
	
	/**
	 * 
	 * 方法的描述: 查询设备列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-4 下午8:48:43
	 * @return
	 * @throws Exception
	 */
	public String queryDevices() throws Exception {
		List<DeviceVO> list = getDeviceService().queryDevices(device);
		setDevices(list);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 按条件查询小区设备列表，设备类型已做国际化
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-25 下午4:51:50
	 * @return
	 * @throws Exception
	 */
	public String queryDeviceList() throws Exception {
		if(device == null){
			device = new DeviceVO();
		}
		HousingDistrictInfoVo housingDistrictInfo = new HousingDistrictInfoVo();
		//小区ID赋值
		housingDistrictInfo.setId(super.getDistrictId());
		//设备赋值所属小区
		device.setHousingDistrictInfo(housingDistrictInfo);
		//查询所属小区的所有设备
		List<DeviceVO> list = getDeviceService().queryDevices(device);
		List<DeviceVO> deviceList = new ArrayList<DeviceVO>();
		//国际化设备类型
		if(list != null && list.size() != 0)
			for(DeviceVO vo : list){
				DeviceTypeVO deviceType = new DeviceTypeVO();
				if(vo!=null && vo.getDeviceType()!=null)
					deviceType.setDeviceName(this.getText(vo.getDeviceType().getDeviceName()));
				vo.setDeviceType(deviceType);
				deviceList.add(vo);
			}
		setDevices(list);
		return SUCCESS;
	}
	
	/**
	 * 方法的描述: 设备列表，分页查询
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午10:11:08
	 * @return
	 * @throws Exception
	 */
	public String deviceList() throws Exception{
		PagingVO<DeviceVO> page = getPage();
		if(page == null)
			page = new PagingVO<DeviceVO>();
		HousingDistrictInfoVo housingDistrictInfo = new HousingDistrictInfoVo();
		if(device == null){
			device = new DeviceVO();
		}
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())){	//如果是物管，则小区赋值为自己所在的小区
			housingDistrictInfo.setId(super.getDistrictId());
			device.setHousingDistrictInfo(housingDistrictInfo);
		}
		setDeviceRoomId(device);
		List<DeviceTypeVO> typeList= getDeviceTypeService().queryAllDeviceTypes();	//获取所有设备类型
		if(!CollectionUtils.isEmpty(typeList)){
			for(DeviceTypeVO type : typeList){
				type.setDeviceName(this.getText(type.getDeviceName()));	//国际化设备类型名称
			}
		}
		//如果是终端设备管理用户，则保留类型不是楼宇的类型(类型编号大于20的为终端设备)
		if(Constants.LOGIN_USER_TYPE_CLIENT_SYSTEM.equals(super.getUserType())){
			List<DeviceTypeVO> ls = new ArrayList<DeviceTypeVO>();
			for(DeviceTypeVO type : typeList){
				if(Integer.parseInt(type.getDeviceType())>=20){
					ls.add(type);
				}
			}
			typeList = ls;
		}
			
		setDeviceTypes(typeList);
		
		//查询并分页
		PagingVO<DeviceVO> deviceList = getDeviceService()
				.queryDeviceForPaging(getDevice(), page.getPageNum(), page.getPageSize());
		List<DeviceVO> results = deviceList.getResults();
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		//对结果集的设备类型进行国际化
		if(!CollectionUtils.isEmpty(results)){
			for(DeviceVO vo : results){
				DeviceTypeVO deviceType = new DeviceTypeVO();
				//国际化设备类型名称
				deviceType.setDeviceName(this.getText(vo.getDeviceType().getDeviceName()));
				deviceType.setDeviceType(vo.getDeviceType().getDeviceType());
				vo.setDeviceType(deviceType);
				//塞sip账号
				DeviceSipVO deviceSipVO = new DeviceSipVO();
				deviceSipVO.setDeviceId(Long.parseLong(vo.getDeviceId()));
//				String sipid = "";
//				List<DeviceSipVO> ls = deviceSipService.queryList(deviceSipVO);
//				if(ls!=null && ls.size()>0){
//					sipid = ls.get(0).getSipid();
//				}
//				vo.setSipid(sipid);
				list.add(vo);
			}
			deviceList.setResults(list);
		}
		setPage(deviceList);
		return "list";
	}
	
	/**
	 * 方法的描述: 查看设备详细信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午10:16:55
	 * @return
	 * @throws Exception
	 */
	public String deviceDetail() throws Exception{
		DeviceVO device = getDeviceService().queryDeviceVOById(getDeviceId());	//根据ID查询设备对象
		setDevice(device);
		return "detail";
	}
	
	/**
	 * 方法的描述: 设备修改明细页面
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午10:17:36
	 * @return
	 * @throws Exception
	 */
	public String updateDetail() throws Exception{
		DeviceVO device = getDeviceService().queryDeviceVOById(getDeviceId());
		setDevice(device);
		return "detail";
	}
	
	//设备修改结果
	public String updateResult() throws Exception{
		DeviceVO deviceVO = getDeviceService().queryDeviceVOById(getDeviceId());
		if(device!=null && !"".equals(device.getDeviceName()) && device.getDeviceName()!=null){
			deviceVO.setDeviceName(device.getDeviceName());
		}
		if(device!=null && !"".equals(device.getDeviceAlias()) && device.getDeviceAlias()!=null){
			deviceVO.setDeviceAlias(device.getDeviceAlias());
		}
		deviceVO.setUpdatedTime(new Date());
		deviceVO.setUpdatedUser(this.getLoginName());
		String flag = "fail";
		int result = getDeviceService().update(deviceVO,Constants.DEVICE_NAME);
		if(result==Constants.DEVICE_UPDATE_FLAG_SUCCESS)	//修改成功！
			flag = "success";
		if(result==Constants.DEVICE_UPDATE_FLAG_NAMEREPEAT)
			flag = "samename";								//在同一房间有相同的设备名称，修改失败
		setSuccessFlag(flag);
		device = getDeviceService().queryDeviceVOById(getDeviceId());
		return "update_result";
	}
		
	//设备密码修改页面
	public String pwdChangeDetail() throws Exception{
		device = getDeviceService().queryDeviceVOById(getDeviceId());		//根据ID查询设备对象
		return "change_passwd";
	}
	//设备修改结果
	public String pwdChangeResult() throws Exception{
		DeviceVO deviceVO = getDeviceService().queryDeviceVOById(getDeviceId());
		String flag = "fail";
		String devicePasswd = "";
		String passwd = "";
		if(device!=null){
			devicePasswd = CryptoUtils.encodeByMD5(device.getDevicePwd());	//密码md5加密
		}
		if(deviceVO!= null){
			passwd = deviceVO.getDevicePwd();
			String newPasswd = CryptoUtils.encodeByMD5(getNewPasswd());	//新密码md5加密
			if(passwd.equals(devicePasswd)){		//如果输入原密码正确
				deviceVO.setDevicePwd(newPasswd);
				//更新密码
				int result = getDeviceService().update(deviceVO,Constants.DEVICE_PASSWORD);
				if(result==Constants.DEVICE_UPDATE_FLAG_SUCCESS){												//如果操作数据库成功
					flag = "success";
				}		
			}else{
				flag = "passwordWrong";	//原密码错误
			}
		}
		setDevice(deviceVO);
		setSuccessFlag(flag);
		return "result";
	}
	
	/**
	 * 
	 * 方法的描述: 如果为业主，则设置业主的房号
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-17 下午4:57:12
	 * @param deviceVO
	 */
	private void setDeviceRoomId(DeviceVO deviceVO){
		if(Constants.LOGIN_USER_TYPE_OWNER.equals(super.getUserType())){
			ownerUser = ownerUserService.getOwnerUserDetail(getUserId());
			CellHouseholdInfoVo cellHouseholdInfoVo = new CellHouseholdInfoVo();
			setCellHouseholdInfo(cellHouseholdInfoVo);
			if(ownerUser!=null && cellHouseholdInfo!=null){
				cellHouseholdInfo.setId(ownerUser.getHouseId());
			}
			if(device!=null)
				device.setCellHouseholdInfo(cellHouseholdInfo);
		}
	}
	
	/**
	 * 方法的描述: 查询门卡设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-13 上午10:20:18
	 * @return
	 * @throws Exception
	 */
	public String queryGateCardDevices() throws Exception {
		if(device == null){
			device = new DeviceVO();
		}
		HousingDistrictInfoVo housingDistrictInfo = new HousingDistrictInfoVo();
		housingDistrictInfo.setId(super.getDistrictId());
		device.setHousingDistrictInfo(housingDistrictInfo);
		List<DeviceVO> list = getDeviceService().queryGateCardDevices(device);
		if(list != null && list.size()>0){
			//国际化设备类型名称
			for (DeviceVO device : list) 
				if(device!=null){
					device.getDeviceType().setDeviceName(getText(device.getDeviceType().getDeviceName()));
					DeviceSipVO deviceSipVO = new DeviceSipVO();
					deviceSipVO.setDeviceId(Long.parseLong(device.getDeviceId()));
					String sipid = "";
					List<DeviceSipVO> ls = deviceSipService.queryList(deviceSipVO);
					if(ls!=null && ls.size()>0){
						sipid = ls.get(0).getSipid();
					}
					device.setSipid(sipid);
				}
		}
		setDevices(list);
		return SUCCESS;
	}
	
	/**
	 * 方法的描述: 数据追踪
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-14 上午9:56:33
	 * @return
	 * @throws Exception
	 */
	public String dataTrace() throws Exception{
		if(device == null){
			device = new DeviceVO();
		}
		return "data_trace_list";
	}
	
	/**
	 * 方法的描述: 删除设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-9 上午10:30:04
	 * @return
	 * @throws Exception
	 */
	public String removeDevice() throws Exception{
		String flag = "";
		DeviceVO vo = getDeviceService().queryDeviceVOById(deviceId);
		//判断设备是否存在,不存在则直接返回列表
		if(vo==null){
			return deviceList();
		}
		//判断设备是否在线，在线不让删除
		if(!getDeviceService().isDeviceOnline(deviceId)){
			if(getDeviceService().removeDeviceById(deviceId)){
				flag = "success";
			}
		}else{
			flag = "online";
		}
		setSuccessFlag(flag);
		return deviceList();
	}
	
	/**
	 * 方法的描述: 修改设备位置坐标
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-9 下午4:20:35
	 * @return
	 * @throws Exception
	 */
	public String updateCoordinateResult() throws Exception{
		DeviceVO deviceVO = getDeviceService().queryDeviceVOById(getDeviceId());
		if(device!=null && !"".equals(device.getCoordinate()) && device.getCoordinate()!=null){
			deviceVO.setCoordinate(device.getCoordinate());
		}
		String flag = "fail";
		//修改设备位置坐标并推送
		int result = getDeviceService().update(deviceVO,Constants.DEVICE_LOCATION_COORDINATE);
		if(result==1)											//保存成功！
			flag = "success";
		setSuccessFlag(flag);
		device = getDeviceService().queryDeviceVOById(getDeviceId());
		fileServerUrl = sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL);
		return "update_result";
	}

	/**
	 * 方法的描述: 修改坐标明细页面
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-9 下午5:28:46
	 * @return
	 * @throws Exception
	 */
	public String updateCoordinateDetail() throws Exception{
		fileServerUrl = sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL);
		DeviceVO device = getDeviceService().queryDeviceVOById(getDeviceId());
		setDevice(device);
		return "detail";
	}
		
	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public DeviceVO getDevice() {
		return device;
	}

	public void setDevice(DeviceVO device) {
		this.device = device;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getDevicePwd() {
		return devicePwd;
	}

	public void setDevicePwd(String devicePwd) {
		this.devicePwd = devicePwd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public SystemLogVO getSystemLog() {
		return systemLog;
	}

	public void setSystemLog(SystemLogVO systemLog) {
		this.systemLog = systemLog;
	}

	public List<DeviceVO> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceVO> devices) {
		this.devices = devices;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public CellHouseholdInfoVo getCellHouseholdInfo() {
		return cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfoVo cellHouseholdInfo) {
		this.cellHouseholdInfo = cellHouseholdInfo;
	}

	public OwnerUserVO getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(OwnerUserVO ownerUser) {
		this.ownerUser = ownerUser;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public List<DeviceTypeVO> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(List<DeviceTypeVO> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public IDeviceTypeService getDeviceTypeService() {
		return deviceTypeService;
	}

	public void setDeviceTypeService(IDeviceTypeService deviceTypeService) {
		this.deviceTypeService = deviceTypeService;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public String getFileServerUrl() {
		return fileServerUrl;
	}

	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}

	public IDeviceSipService getDeviceSipService() {
		return deviceSipService;
	}

	public void setDeviceSipService(IDeviceSipService deviceSipService) {
		this.deviceSipService = deviceSipService;
	}
}
