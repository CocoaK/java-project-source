package com.biencloud.smarthome.web.appdata.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.alarm.service.IAlarmService;
import com.biencloud.smarthome.web.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;
import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.AddressBookJson;
import com.biencloud.smarthome.web.appdata.json.AlarmJson;
import com.biencloud.smarthome.web.appdata.json.CallRecordJson;
import com.biencloud.smarthome.web.appdata.json.ChargeJson;
import com.biencloud.smarthome.web.appdata.json.ComplaintJson;
import com.biencloud.smarthome.web.appdata.json.ComponentJson;
import com.biencloud.smarthome.web.appdata.json.DeviceLocationJson;
import com.biencloud.smarthome.web.appdata.json.GatePermissionsJson;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.json.MonitorJson;
import com.biencloud.smarthome.web.appdata.json.PageComponentJson;
import com.biencloud.smarthome.web.appdata.json.PageJson;
import com.biencloud.smarthome.web.appdata.json.PasswordJson;
import com.biencloud.smarthome.web.appdata.json.RoomDeviceJson;
import com.biencloud.smarthome.web.appdata.json.RoomInfoJson;
import com.biencloud.smarthome.web.appdata.json.RoomJson;
import com.biencloud.smarthome.web.appdata.json.RoomSipJson;
import com.biencloud.smarthome.web.appdata.json.ServerTimeJson;
import com.biencloud.smarthome.web.appdata.service.IAddressBookService;
import com.biencloud.smarthome.web.appdata.service.IChargeService;
import com.biencloud.smarthome.web.appdata.service.ICompanyInfoService;
import com.biencloud.smarthome.web.appdata.service.IDevicePasswordService;
import com.biencloud.smarthome.web.appdata.service.IGatePermissionsService;
import com.biencloud.smarthome.web.appdata.service.IHouseInfoService;
import com.biencloud.smarthome.web.appdata.service.IMonitorDeviceService;
import com.biencloud.smarthome.web.appdata.service.IRoomService;
import com.biencloud.smarthome.web.appdata.vo.AddressBookVO;
import com.biencloud.smarthome.web.appdata.vo.CompanyInfoVO;
import com.biencloud.smarthome.web.appdata.vo.HouseInfoVO;
import com.biencloud.smarthome.web.appdata.vo.RoomVO;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeDetailService;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;
import com.biencloud.smarthome.web.device.service.ICallRecordService;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.CallRecordVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.gate.service.IGateCardVisitService;
import com.biencloud.smarthome.web.gate.service.IIdCardVisitService;
import com.biencloud.smarthome.web.gate.vo.GateCardVisitVO;
import com.biencloud.smarthome.web.gate.vo.IdCardVisitVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.web.page.service.IComponentService;
import com.biencloud.smarthome.web.page.service.IPageService;
import com.biencloud.smarthome.web.page.vo.ComponentVO;
import com.biencloud.smarthome.web.page.vo.PageVO;
import com.biencloud.smarthome.web.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;

/**
 * 
 * 类名称：AppDataAction 类描述： app数据处理Action
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-14 上午9:14:31
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class AppDataAction extends BaseAction {

	private IHouseInfoService houseInfoService;
	private ICompanyInfoService companyInfoService;
	private IDeviceService deviceService;
	private ICallRecordService callRecordService;
	private IGateCardVisitService gateCardVisitService;
	private IIdCardVisitService idCardVisitService;
	private IAlarmService alarmService;
	private IAlarmTypeService alarmTypeService;
	private IChargeDetailService chargeDetailService;
	private IAddressBookService addressBookService;
	private IGatePermissionsService gatePermissionsService;
	private IComplaintService complaintService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IRequestRepairService requestRepairService;
	private IOwnerUserService ownerUserService;
	private IRoomService roomService;
	private IMonitorDeviceService monitorDeviceService;
	private ISceneMonitorService sceneMonitorService;
	private IChargeService chargeService;
	private IDevicePasswordService devicePasswordService;
	private IPageService pageService;
	private IComponentService componentService;
	
	//设备实体VO
	private DeviceVO deviceVO;
	// 门禁数据对象
	private GatePermissionsJson gp;
	// 通话记录
	private CallRecordVO callRecordVO;
	// 通话记录Json
	private CallRecordJson callRecordJson;
	// 门卡刷卡记录
	private GateCardVisitVO gateCardVisitVO;
	// 身份证刷卡记录List
	private List<IdCardVisitVO> idCardVisitVOs;
	// 身份证刷卡记录
	private IdCardVisitVO idCardVisitVO;
	// 报警信息
	private AlarmVO alarm;
	// 报警类型
	private AlarmTypeVO alarmTypeVO;
	// 门卡刷卡记录List
	private List<GateCardVisitVO> gateCardVisitVOs;
	// 报警信息List
	private AlarmJson alarmJson;
	// 报警类型List
	private List<AlarmTypeVO> alarmTypes;
	// 收费信息
	private ChargeDetailVO chargeDetailVO;
	// 成功标识
	private int successFlag;
	// 地址薄
	private AddressBookVO addressBook;
	// json格式化对象
	private Json json;
	// 地址薄json格式化对象
	private AddressBookJson addressBookJson;
	// 投诉对象
	private ComplaintVo complaintVO;
	// 投诉json格式化对象
	private ComplaintJson complaintJson;
	/** 报修VO */
	private RequestRepairVO requestRepair;
	//户户通房号Json
	private RoomJson roomJson;
	// 房间信息数据
	private RoomInfoJson roomInfoJson;
	//监控设备json
	private MonitorJson monitorJson;
	/** 投诉唯一标识 */
	private String onlyValue;
	//服务器时间json
	private ServerTimeJson serverTime;
	//收费json
	private ChargeJson chargeJson;
	//设备编号
	private String deviceNo;
	//报警类型
	private String alarmType;
	//户户通房号信息
	private RoomVO roomVO;
	//设备开锁密码Json
	private PasswordJson passwordJson;
	//设备位置信息
	private DeviceLocationJson deviceLocationJson;
	// 物业信息
	private CompanyInfoVO companyInfoVO;
	// 房产信息
	private HouseInfoVO houseInfoVO;
	//终端页面
	private PageJson pageJson;
	private PageVO pageVO;
	private PageComponentJson pageComponentJson;
	private List<PageComponentJson> pageComponentJsonList;
	
	//终端页面组件
	private ComponentJson componentJson;
	private ComponentVO componentVO;
	
	//房间设备
	private RoomDeviceJson roomDeviceJson;
	
	//完整房号字符串
	private String roomNo;
	
	private RoomSipJson roomSipJson;
	
	/**
	 * 
	 * 方法的描述: 根据设备编号获取房间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-20 上午11:11:34
	 * @param deviceNo
	 *            :业主的设备编号
	 * @return
	 */
	private CellHouseholdInfoVo getHourseVO(String deviceNo) {
		DeviceVO deVo = alarmService.getDeviceVO(deviceNo);
		if(deVo==null){
			return null;
		}
		return deVo.getCellHouseholdInfo();
	}

	/**
	 * 
	 * 方法的描述: 保存报修
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:23:04
	 * @return
	 */
	public String saveRequestRepair() throws Exception{
		try {
			String deviceNo = getRequest().getParameter("deviceNo");
			// OwnerUserVO ouVo = getOwnerUserVO(deviceNo);
			CellHouseholdInfoVo houseVo = new CellHouseholdInfoVo();
			CellHouseholdInfoVo resultVo = getHourseVO(deviceNo);
			houseVo.setId(resultVo.getId());
			if(requestRepair!=null){
				requestRepair.setCellHouseholdInfo(houseVo);
				requestRepair.setOwnerUser(null);
				requestRepair.setStatus(RequestRepairVO.STATUS_NOPROCESS.toString());
				if(resultVo!=null && resultVo.getTHmBuildingCellInfo()!=null && 
						resultVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo()!=null &&
						resultVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo()!=null &&
						resultVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo()!=null){
					requestRepair.setDistrictId(new Long(resultVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo()
							.getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId()));
				}
				requestRepair.setRequestTime(new Date(System.currentTimeMillis()));
				boolean result = requestRepairService.saveRequestRepairVO(requestRepair);
				if (result)
					setSuccessFlag(1);
				else
					setSuccessFlag(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加报修出错：{}", new Object[] { e });
			setSuccessFlag(0);
		}
		return "save_request_repair";
	}

	/**
	 * 
	 * 方法的描述: 查询物业信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-14 上午9:16:17
	 * @return
	 */
	public String queryCompanyInfo() throws Exception{
		try {
			companyInfoVO = companyInfoService.queryCompanyInfo(companyInfoVO.getDeviceNo());
		} catch (Exception e) {
			companyInfoVO.setCode(AppDataConstant.FAILTRUE);
		}
		return "company_info";
	}

	/**
	 * 
	 * 方法的描述: 查询房间信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-3 下午2:45:21
	 * @return
	 */
	public String queryRoomData() throws Exception{
		roomInfoJson = roomService.queryRoomInfoByDeviceNo(roomInfoJson.getDeviceNo());
		return "room_info";
	}

	/**
	 * 
	 * 方法的描述: 查询房间设备信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-3 下午2:45:21
	 * @return
	 */
	public String queryRoomDeviceData() throws Exception{
		roomDeviceJson = roomService.queryRoomDevices(roomDeviceJson.getDeviceNo());
		return "room_device_data";
	}
	
	/**
	 * 
	 * 方法的描述: app发送场景数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-3 下午2:49:31
	 * @return
	 */
	public String sendSceneData() throws Exception{
		//System.out.println("sceneData---" + this.getJsonString());
		json = sceneMonitorService.saveOrUpdateSceneData(this.getJsonString());

		return "scene_data";
	}

	/**
	 * 
	 * 方法的描述: app发送家电设备控制数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-3 下午9:06:59
	 * @return
	 */
	public String sendHouseDeviceData() throws Exception{
		//System.out.println("sceneDeviceData---" + this.getJsonString());
		json = sceneMonitorService.saveOrUpdateRoomDeviceData(this.getJsonString());
		return "scene_device_data";
	}

	/**
	 * 
	 * 方法的描述: 修改设备别名
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-14 上午9:22:16
	 * @return
	 */
	public String updateDeviceName() throws Exception{
		deviceService.update(deviceVO, null);
		return "update_device_name";
	}

	/**
	 * 
	 * 方法的描述: 查询设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-14 上午9:23:43
	 * @return
	 */
	public String queryDevice() throws Exception{
		List<DeviceVO> list = deviceService.queryDevices(deviceVO);
		return "query_device";
	}

	/**
	 * 
	 * 方法的描述: app获取门禁数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-16 下午3:50:52
	 * @return
	 */
	public String queryGateData() throws Exception{
		if (gp == null)
			gp = new GatePermissionsJson();

		if (StringUtils.isEmpty(gp.getDeviceNo())) {
			gp.setCode(AppDataConstant.FAILTRUE);
			logger.error("************请求的设备编号为空************");
		} else {
			try {
				gp = getGatePermissionsService().queryAllGateDataByDeviceNo(gp.getDeviceNo());
				gp.setCode(AppDataConstant.SUCCESS);
			} catch (Exception e) {
				gp.setCode(AppDataConstant.FAILTRUE);
				logger.error("************设备编号为{}获取门禁数据发生异常：{}", new Object[] { gp.getDeviceNo(), e });
			}
		}
		return "gate_data";
	}

	/**
	 * 方法的描述: 获取设备所在位置
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-09 上午11:30:52
	 * @return
	 * @throws Exception
	 */
	public String getDeviceLocation() throws Exception{
		deviceLocationJson.setCode(AppDataConstant.FAILTRUE);//初始为失败
		if(deviceLocationJson == null || StringUtils.isBlank(
				deviceLocationJson.getDeviceNo())){
			logger.error("************请求的设备编号为空************");
		}else{
			try {
				DeviceVO device = getDeviceService().queryDeviceByCode(
						deviceLocationJson.getDeviceNo());
				if(device == null)
					logger.error("************设备编号为{}获取不到对应的设备************", deviceLocationJson.getDeviceNo());
				else
					buildDeviceLocation(device);				
			} catch (Exception e) {
				logger.error("************设备编号为{}获取设备所在位置发生异常：{}", new Object[] { deviceLocationJson.getDeviceNo(), e });
			}			
		}
		return "device_location";
	}

	/**
	 * 方法的描述: 组装设备位置信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-28 上午11:33:11
	 * @param device
	 */
	private void buildDeviceLocation(DeviceVO device) {
		if(device==null)
			return;
		deviceLocationJson.setDownloadPath(device.getHousingDistrictInfo().getFloorPlan());
		String deviceType = device.getDeviceType().getDeviceType();
		if(Constants.UNIT_DOOR_DEVICE.equals(deviceType)){
			deviceLocationJson.setCode(AppDataConstant.SUCCESS);
			deviceLocationJson.setCoordinate(
					convertCoordinate(device.getRegionBuildingInfo().getCoordinate()));
		}else if(Constants.FENCE_DEVICE.equals(deviceType)){
			deviceLocationJson.setCode(AppDataConstant.SUCCESS);
			deviceLocationJson.setCoordinate(
					convertCoordinate(device.getCoordinate()));
		}else{
			logger.error("************设备编号为{}不能获取所在位置（所属的设备类型不支持）************", deviceLocationJson.getDeviceNo());
		}
	}

	/**
	 * 方法的描述: 转换坐标的表示
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-28 上午11:36:40
	 * @param coordinate
	 * @return
	 */
	private String convertCoordinate(String coordinate){
		return StringUtils.replace(coordinate, ",", "|");
	}
	
	/**
	 * 
	 * 方法的描述: 查询通话记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:23:37
	 * @return
	 */
	public String queryCallRecord() throws Exception{
		List<CallRecordVO> callRecordVOs = getCallRecordService().queryCallRecords(callRecordVO);
		CallRecordJson crJson = new CallRecordJson();
		crJson.setCallRecordList(callRecordVOs);
		if (callRecordVO != null)
			crJson.setDeviceNo(callRecordVO.getDeviceCode());
		setCallRecordJson(crJson);
		return "query_call_record";
	}

	/**
	 * 
	 * 方法的描述:保存通话记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:23:58
	 * @return
	 */
	public String saveCallRecord() throws Exception{
		json = getCallRecordService().saveCallRecordByJson(getJsonString());
		return "save_call_record";
	}

	/**
	 * 方法的描述: 保存留言
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-9-13 下午3:46:33
	 * @return
	 */
	public String saveMessage() throws Exception{
		json = getCallRecordService().saveCallRecordMessageByJson(getJsonString());
		return "save_call_record";
	}

	/**
	 * 
	 * 方法的描述:删除通话记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:24:26
	 * @return
	 */
	public String removeCallRecord() throws Exception{
		Json js = new Json();
		try {
			getCallRecordService().removeCallRecord(callRecordVO);
			js.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			js.setCode(AppDataConstant.FAILTRUE);
		}
		js.setDeviceNo(callRecordVO.getDeviceCode());
		setJson(js);
		return "remove_call_record";
	}

	/**
	 * 
	 * 方法的描述:查询门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:24:52
	 * @return
	 */
	public String queryGateCardVisit() throws Exception{
		gateCardVisitVOs = getGateCardVisitService().queryGateCardVisits(gateCardVisitVO);
		return "query_gate_card_visit";
	}

	/**
	 * 
	 * 方法的描述:保存门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:25:08
	 * @return
	 */
	public String saveGateCardVisit() throws Exception{
		json = getGateCardVisitService().saveGateCardVisit(this.getJsonString());
		return "save_gate_card_visit";
	}

	/**
	 * 
	 * 方法的描述:删除门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:26:08
	 * @return
	 */
	public String removeGateCardVisit() throws Exception{
		getGateCardVisitService().removeGateCardVisit(gateCardVisitVO);
		setSuccessFlag(1);
		return "remove_gate_card_visit";
	}

	/**
	 * 
	 * 方法的描述: 查询身份证刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 下午4:56:08
	 * @return
	 */
	public String queryIdCardVisit() throws Exception{
		idCardVisitVOs = getIdCardVisitService().queryIdCardVisits(idCardVisitVO);
		return "query_id_card_visit";
	}

	/**
	 * 
	 * 方法的描述:保存或者修改门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 下午4:58:29
	 * @return
	 */
	public String saveOrUpdateIdCardVisit() throws Exception{
		getIdCardVisitService().saveOrUpdateIdCardVisit(idCardVisitVO);
		setSuccessFlag(1);
		return "save_id_card_visit";
	}

	/**
	 * 
	 * 方法的描述: 删除门卡刷卡记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 下午4:58:58
	 * @return
	 */
	public String removeIdCardVisit() throws Exception{
		getIdCardVisitService().removeIdCardVisit(idCardVisitVO);
		setSuccessFlag(1);
		return "remove_gate_card_visit";
	}

	/**
	 * 
	 * 方法的描述:查询报警记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:28:36
	 * @return
	 */
	public String queryAlarm() throws Exception{
		List<AlarmVO> alarms = getAlarmService().queryAlarms(alarm);
		AlarmJson aj = new AlarmJson();
		aj.setAlarmList(alarms);
		setAlarmJson(aj);
		return "query_alarm";
	}

	/**
	 * 
	 * 方法的描述:保存或修改报警记录
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-23 下午4:32:45
	 * @return
	 */
	public String saveOrUpdateAlarm() throws Exception{
		json = getAlarmService().saveOrUpdateAlarm(getJsonString());
		return "save_update_alarm";
	}

	/**
	 * 
	 * 方法的描述: 查询报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 上午11:42:31
	 * @return
	 */
	public String queryAlarmType() throws Exception{
		alarmTypes = getAlarmTypeService().queryAlarmTypes(alarmTypeVO);
		return "query_alarmtype";
	}

	/**
	 * 
	 * 方法的描述: 保存或者删除报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 上午11:42:46
	 * @return
	 */
	public String saveOrUpdateAlarmType() throws Exception{
		getAlarmTypeService().saveOrUpdateAlarmType(alarmTypeVO);
		setSuccessFlag(1);
		return "save_update_alarmtype";
	}

	/**
	 * 
	 * 方法的描述: 删除报警类型
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 上午11:43:13
	 * @return
	 */
	public String removeAlarmType() throws Exception{
		getAlarmTypeService().removeAlarmType(alarmTypeVO);
		setSuccessFlag(1);
		return "remove_alarmtype";
	}

	/**
	 * 
	 * 方法的描述: 修改设备密码
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-25 上午11:38:37
	 * @return
	 */
	public String updateDevicePasswd() throws Exception{
		Json js = new Json();
		if(deviceVO!=null){
			DeviceVO device = deviceService.queryDeviceByCode(deviceVO.getDeviceCode());
			device.setDevicePwd(deviceVO.getDevicePwd());
			js.setCode(AppDataConstant.SUCCESS);
			js.setDeviceNo(deviceVO.getDeviceCode());
			try {
				deviceService.update(device, null);
			} catch (Exception e) {
				js.setCode(AppDataConstant.FAILTRUE);
			}
		}
		setJson(js);
		return "update_device_passwd";
	}

	/**
	 * 
	 * 方法的描述: 保存地址薄
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午6:59:34
	 * @return
	 */
	public String saveAddressBook() throws Exception{
		try {
			json = addressBookService.saveOrUpdateAdressBook(this.getJsonString());
		} catch (Exception e) {
			e.printStackTrace();
			json.setCode(AppDataConstant.FAILTRUE);
		}
		return "save_address_book";
	}

	/**
	 * 
	 * 方法的描述: 查询地址薄
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午7:00:50
	 * @return
	 */
	public String queryAddressBook() throws Exception{
		try {
			addressBookJson = addressBookService.listAll(addressBook);
			addressBookJson.setDeviceNo(addressBook.getDeviceNo());

		} catch (Exception e) {
			addressBookJson.setCode(AppDataConstant.FAILTRUE);
		}
		return "list_address_book";
	}

	/**
	 * 
	 * 方法的描述: app取服务器时间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:16:51
	 * @return
	 */
	public String getServerCurrentTime() throws Exception{
		serverTime=new ServerTimeJson();
		if (this.getJsonString() != null) {
			JSONObject jb = JsonUtil.jsonStringToJsonObject(this.getJsonString());
			String deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
			if (deviceNo != null) {
				
				Calendar rightNow = Calendar.getInstance();
				
				serverTime.setServerTime(rightNow.getTimeInMillis());
				serverTime.setCode(AppDataConstant.SUCCESS);
				serverTime.setDeviceNo(deviceNo);
			}
		} else {
			serverTime = new ServerTimeJson();
			serverTime.setCode(AppDataConstant.FAILTRUE);
		}
		return "server_time";
	}

	/**
	 * 
	 * 方法的描述: 删除地址薄
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-30 下午7:02:06
	 * @return
	 */
	public String deleteAddressBook() throws Exception{
		if(addressBook!=null){
			try {
				json = addressBookService.deleteById(addressBook.getDeviceNo(), addressBook.getAppId());
				json.setDeviceNo(addressBook.getDeviceNo());
				json.setCode(AppDataConstant.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				json.setCode(AppDataConstant.FAILTRUE);
			}
		}
		return "delete_address_book";
	}

	/**
	 * 
	 * 方法的描述:查询收费清单
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-2 上午10:05:23
	 * @return
	 */
	public String queryChargeDetail() throws Exception{
		chargeJson = chargeService.queryList(deviceNo, chargeDetailVO);
		return "query_charge_detail";
	}

	/**
	 * 
	 * 方法的描述: 查询业主的投诉
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-31 下午5:30:44
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryOwnerComplaint() throws Exception{
		ComplaintJson cj = new ComplaintJson();
		PagingVO<ChargeDetailVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeDetailVO>();
		complaintVO = new ComplaintVo();
		complaintVO.setDeviceNo(getParameter("deviceNo"));
		PagingVO<ComplaintVo> pagingVO = getComplaintService().queryOwnerComplaintForPaging(complaintVO, null, null, page.getPageNum(), page.getPageSize(), true);
		cj.setComplaintList(pagingVO.getResults());
		setComplaintJson(cj);
		return "query_owner_complaint";
	}

	/**
	 * 
	 * 方法的描述: 查询物业的投诉
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-31 下午5:30:44
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryPropertyComplaint() throws Exception{
		ComplaintJson cj = new ComplaintJson();
		PagingVO<ChargeDetailVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeDetailVO>();
		PagingVO<ComplaintVo> pagingVO = getComplaintService().queryPropertyComplaintForPaging(complaintVO, null, null, page.getPageNum(), page.getPageSize(), true);
		cj.setComplaintList(pagingVO.getResults());
		setComplaintJson(cj);
		return "query_property_complaint";
	}

	/**
	 * 
	 * 方法的描述: 保存投诉
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-31 下午7:12:25
	 * @return
	 */
	public String saveComplaint() throws Exception{
		// System.out.println("complaint json:"+this.getJsonString());
		json = getComplaintService().saveAppComplaint(this.getJsonString());
		return "save_complaint";
	}

	/**
	 * 
	 * 方法的描述: 删除投诉
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-31 下午7:13:24
	 * @return
	 */
	public String removeCompaint() throws Exception{
		Json js = new Json();
		try {
			getComplaintService().removeOwnerComplaint(onlyValue);
			js.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			js.setCode(AppDataConstant.FAILTRUE);
		}
		setJson(js);
		return "remove_complaint";
	}

	/**
	 * 
	 * 方法的描述: 修改投诉，已经提交的投诉不能修改
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-5-31 下午7:14:26
	 * @return
	 */
	public String updateComplaint() throws Exception{
		boolean submit = false; // 是否提交
		if(complaintVO!=null){
			if (IComplaintService.PROCESSING_STATUS_PENDING.equals(complaintVO.getProcessingStatus()))
				submit = true;
			getComplaintService().updateComplaint(complaintVO.getId(), complaintVO.getTitle(), complaintVO.getContent(), submit);
		}
		return "update_complaint";
	}

	/**
	 * 
	 * 方法的描述: 查询户户通房号信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-21 上午9:28:32
	 * @return
	 */
	public String queryRooms() throws Exception{
		if (deviceVO != null)
			roomJson = roomService.queryRooms(deviceVO.getDeviceCode());
		return "query_room_device";
	}

	/**
	 * 
	 * 方法的描述: 查询监控设备
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-6-26 下午5:49:45
	 * @return
	 */
	public String queryMonitor() throws Exception{
		if (deviceVO != null)
			monitorJson = monitorDeviceService.queryMonitors(deviceVO.getDeviceCode());
		return "query_monitor_device";
	}

	/**
	 * 
	 * 方法的描述: 删除场景
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-5 下午6:18:57
	 * @return
	 */
	public String deleteSceneData() throws Exception{
		json = sceneMonitorService.deleteSceneData(this.getJsonString());
		return "delete_scene";
	}

	/**
	 * 
	 * 方法的描述: 删除场景设备
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-5 下午6:20:07
	 * @return
	 */
	public String deleteHouseDeviceData() throws Exception{
		json = sceneMonitorService.deleteSceneDeviceData(this.getJsonString());
		return "delete_scene_device";
	}

	/**
	 * 
	 * 方法的描述: 保存设备开锁密码
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-6 下午5:22:58
	 * @return
	 */
	public String saveDevicePassword() throws Exception{
		json = devicePasswordService.save(this.getJsonString());
		return "save_device_password";
	}

	/**
	 * 
	 * 方法的描述: 查询设备开锁密码
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-6 下午5:22:58
	 * @return
	 */
	public String queryDevicePassword() throws Exception{
		passwordJson = devicePasswordService.queryPasswordByTargetDeviceNo(deviceNo);
		return "query_device_password";
	}

	/**
	 * 方法的描述: 查询房间信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-22 下午4:25:22
	 * @return
	 */
	public String queryRoom() throws Exception{
		roomVO = roomService.queryRoom(this.getJsonString());
		return "query_room";
	}

	/**
	 * 方法的描述: 查询小区的所有管理机信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-22 下午5:07:25
	 * @return
	 */
	public String queryManageDevice() throws Exception{
		roomJson = roomService.queryManageDevice(deviceNo);
		return "query_manage_device";
	}
	
	/**
	 * 方法的描述: 查询小区的所有管理机信息
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-22 下午5:07:25
	 * @return
	 */
	public String queryManageDeviceByRoomNo() throws Exception{
		roomSipJson = roomService.queryManageDeviceByRoomNo(roomNo);
		return "query_manage_device_by_room";
	}
	
	/**
	 * 方法的描述: 查询所有页面
	 * @author: ykou
	 * @version: 0.1
	 * @return
	 */
	public String queryAllPages() throws Exception{
		pageJson = pageService.queryPageJson(pageVO);
		return "query_page";
	}
	
	/**
	 * 方法的描述: 查询页面组件
	 * @author: ykou
	 * @version: 0.1
	 * @return
	 */
	public String queryPageComponent() throws Exception{
		pageComponentJson = pageService.queryPageComponentJson(pageVO);
		return "query_page_component";
	}
	
	/**
	 * 方法的描述: 查询所有页面组件
	 * @author: ykou
	 * @version: 0.1
	 * @return
	 */
	public String queryAllPageComponent() throws Exception{
		pageComponentJsonList = pageService.queryAllPageComponentJson();
		return "query_all_page_component";
	}
	
	/**
	 * 方法的描述: 查询页面的组件
	 * @author: ykou
	 * @version: 0.1
	 * @return
	 */
	public String queryComponent() throws Exception{
		componentJson = componentService.queryComponentJson(componentVO);
		return "query_component";
	}

	public CompanyInfoVO getCompanyInfoVO() {
		return companyInfoVO;
	}

	public void setCompanyInfoVO(CompanyInfoVO companyInfoVO) {
		this.companyInfoVO = companyInfoVO;
	}

	public HouseInfoVO getHouseInfoVO() {
		return houseInfoVO;
	}

	public void setHouseInfoVO(HouseInfoVO houseInfoVO) {
		this.houseInfoVO = houseInfoVO;
	}

	public IHouseInfoService getHouseInfoService() {
		return houseInfoService;
	}

	public void setHouseInfoService(IHouseInfoService houseInfoService) {
		this.houseInfoService = houseInfoService;
	}

	public ICompanyInfoService getCompanyInfoService() {
		return companyInfoService;
	}

	public void setCompanyInfoService(ICompanyInfoService companyInfoService) {
		this.companyInfoService = companyInfoService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public DeviceVO getDeviceVO() {
		return deviceVO;
	}

	public void setDeviceVO(DeviceVO deviceVO) {
		this.deviceVO = deviceVO;
	}

	public GatePermissionsJson getGp() {
		return gp;
	}

	public void setGp(GatePermissionsJson gp) {
		this.gp = gp;
	}

	public IGatePermissionsService getGatePermissionsService() {
		return gatePermissionsService;
	}

	public void setGatePermissionsService(IGatePermissionsService gatePermissionsService) {
		this.gatePermissionsService = gatePermissionsService;
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

	public CallRecordJson getCallRecordJson() {
		return callRecordJson;
	}

	public void setCallRecordJson(CallRecordJson callRecordJson) {
		this.callRecordJson = callRecordJson;
	}

	public GateCardVisitVO getGateCardVisitVO() {
		return gateCardVisitVO;
	}

	public void setGateCardVisitVO(GateCardVisitVO gateCardVisitVO) {
		this.gateCardVisitVO = gateCardVisitVO;
	}

	public IGateCardVisitService getGateCardVisitService() {
		return gateCardVisitService;
	}

	public void setGateCardVisitService(IGateCardVisitService gateCardVisitService) {
		this.gateCardVisitService = gateCardVisitService;
	}

	public List<GateCardVisitVO> getGateCardVisitVOs() {
		return gateCardVisitVOs;
	}

	public void setGateCardVisitVOs(List<GateCardVisitVO> gateCardVisitVOs) {
		this.gateCardVisitVOs = gateCardVisitVOs;
	}

	public List<IdCardVisitVO> getIdCardVisitVOs() {
		return idCardVisitVOs;
	}

	public void setIdCardVisitVOs(List<IdCardVisitVO> idCardVisitVOs) {
		this.idCardVisitVOs = idCardVisitVOs;
	}

	public IdCardVisitVO getIdCardVisitVO() {
		return idCardVisitVO;
	}

	public void setIdCardVisitVO(IdCardVisitVO idCardVisitVO) {
		this.idCardVisitVO = idCardVisitVO;
	}

	public IIdCardVisitService getIdCardVisitService() {
		return idCardVisitService;
	}

	public void setIdCardVisitService(IIdCardVisitService idCardVisitService) {
		this.idCardVisitService = idCardVisitService;
	}

	public AlarmVO getAlarm() {
		return alarm;
	}

	public void setAlarm(AlarmVO alarm) {
		this.alarm = alarm;
	}

	public AlarmTypeVO getAlarmTypeVO() {
		return alarmTypeVO;
	}

	public void setAlarmTypeVO(AlarmTypeVO alarmTypeVO) {
		this.alarmTypeVO = alarmTypeVO;
	}

	public List<AlarmTypeVO> getAlarmTypes() {
		return alarmTypes;
	}

	public void setAlarmTypes(List<AlarmTypeVO> alarmTypes) {
		this.alarmTypes = alarmTypes;
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

	public ChargeDetailVO getChargeDetailVO() {
		return chargeDetailVO;
	}

	public void setChargeDetailVO(ChargeDetailVO chargeDetailVO) {
		this.chargeDetailVO = chargeDetailVO;
	}

	public IChargeDetailService getChargeDetailService() {
		return chargeDetailService;
	}

	public void setChargeDetailService(IChargeDetailService chargeDetailService) {
		this.chargeDetailService = chargeDetailService;
	}

	public AlarmJson getAlarmJson() {
		return alarmJson;
	}

	public void setAlarmJson(AlarmJson alarmJson) {
		this.alarmJson = alarmJson;
	}

	public int getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(int successFlag) {
		this.successFlag = successFlag;
	}

	public IAddressBookService getAddressBookService() {
		return addressBookService;
	}

	public void setAddressBookService(IAddressBookService addressBookService) {
		this.addressBookService = addressBookService;
	}

	public AddressBookVO getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBookVO addressBook) {
		this.addressBook = addressBook;
	}

	public Json getJson() {
		return json;
	}

	public void setJson(Json json) {
		this.json = json;
	}

	public AddressBookJson getAddressBookJson() {
		return addressBookJson;
	}

	public void setAddressBookJson(AddressBookJson addressBookJson) {
		this.addressBookJson = addressBookJson;
	}

	public ComplaintVo getComplaintVO() {
		return complaintVO;
	}

	public void setComplaintVO(ComplaintVo complaintVO) {
		this.complaintVO = complaintVO;
	}

	public ComplaintJson getComplaintJson() {
		return complaintJson;
	}

	public void setComplaintJson(ComplaintJson complaintJson) {
		this.complaintJson = complaintJson;
	}

	public IComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(IComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public IRequestRepairService getRequestRepairService() {
		return requestRepairService;
	}

	public void setRequestRepairService(IRequestRepairService requestRepairService) {
		this.requestRepairService = requestRepairService;
	}

	public RequestRepairVO getRequestRepair() {
		return requestRepair;
	}

	public void setRequestRepair(RequestRepairVO requestRepair) {
		this.requestRepair = requestRepair;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public RoomJson getRoomJson() {
		return roomJson;
	}

	public void setRoomJson(RoomJson roomJson) {
		this.roomJson = roomJson;
	}

	public IRoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	public IMonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(IMonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public MonitorJson getMonitorJson() {
		return monitorJson;
	}

	public void setMonitorJson(MonitorJson monitorJson) {
		this.monitorJson = monitorJson;
	}

	public String getOnlyValue() {
		return onlyValue;
	}

	public void setOnlyValue(String onlyValue) {
		this.onlyValue = onlyValue;
	}

	public RoomInfoJson getRoomInfoJson() {
		return roomInfoJson;
	}

	public void setRoomInfoJson(RoomInfoJson roomInfoJson) {
		this.roomInfoJson = roomInfoJson;
	}

	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}

	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}

	public void setServerTime(ServerTimeJson serverTime) {
		this.serverTime = serverTime;
	}

	public ServerTimeJson getServerTime() {
		return serverTime;
	}

	public ChargeJson getChargeJson() {
		return chargeJson;
	}

	public void setChargeJson(ChargeJson chargeJson) {
		this.chargeJson = chargeJson;
	}

	public IChargeService getChargeService() {
		return chargeService;
	}

	public void setChargeService(IChargeService chargeService) {
		this.chargeService = chargeService;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public IDevicePasswordService getDevicePasswordService() {
		return devicePasswordService;
	}

	public void setDevicePasswordService(IDevicePasswordService devicePasswordService) {
		this.devicePasswordService = devicePasswordService;
	}

	public RoomVO getRoomVO() {
		return roomVO;
	}

	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}

	public PasswordJson getPasswordJson() {
		return passwordJson;
	}

	public void setPasswordJson(PasswordJson passwordJson) {
		this.passwordJson = passwordJson;
	}

	public DeviceLocationJson getDeviceLocationJson() {
		return deviceLocationJson;
	}

	public void setDeviceLocationJson(DeviceLocationJson deviceLocationJson) {
		this.deviceLocationJson = deviceLocationJson;
	}

	public PageJson getPageJson() {
		return pageJson;
	}

	public void setPageJson(PageJson pageJson) {
		this.pageJson = pageJson;
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

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public ComponentJson getComponentJson() {
		return componentJson;
	}

	public void setComponentJson(ComponentJson componentJson) {
		this.componentJson = componentJson;
	}

	public ComponentVO getComponentVO() {
		return componentVO;
	}

	public void setComponentVO(ComponentVO componentVO) {
		this.componentVO = componentVO;
	}

	public PageComponentJson getPageComponentJson() {
		return pageComponentJson;
	}

	public void setPageComponentJson(PageComponentJson pageComponentJson) {
		this.pageComponentJson = pageComponentJson;
	}

	public List<PageComponentJson> getPageComponentJsonList() {
		return pageComponentJsonList;
	}

	public void setPageComponentJsonList(
			List<PageComponentJson> pageComponentJsonList) {
		this.pageComponentJsonList = pageComponentJsonList;
	}

	public RoomDeviceJson getRoomDeviceJson() {
		return roomDeviceJson;
	}

	public void setRoomDeviceJson(RoomDeviceJson roomDeviceJson) {
		this.roomDeviceJson = roomDeviceJson;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public RoomSipJson getRoomSipJson() {
		return roomSipJson;
	}

	public void setRoomSipJson(RoomSipJson roomSipJson) {
		this.roomSipJson = roomSipJson;
	}
	
}
