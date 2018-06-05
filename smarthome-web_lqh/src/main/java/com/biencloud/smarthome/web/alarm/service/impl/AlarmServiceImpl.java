package com.biencloud.smarthome.web.alarm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.alarm.service.IAlarmService;
import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;
import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.Alarm;
import com.biencloud.smarthome.web.wsclient.stub.AlarmType;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
/**
 * 报警管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 */
public class AlarmServiceImpl extends BaseService<AlarmVO> implements IAlarmService{
	private IOwnerUserService ownerUserService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private static final String CREATED_TIME = "createdTime";
	private static final String ALARMTYPE = "alarmType";
	private IDeviceService deviceService;
	public static final String[] IGNOREPRO_PERTIES_OB = {"alarmType","ownerUser","paUser","housingDistrictInfo"};
	public static final String[] IGNOREPRO_PERTIES_VO = {"alarmType","ownerUser","paUser","housingDistrictInfo","device"};
	private static final String[] CREATED_TIMES = {"createdTime","hanlderTime","alarmStartTime","alarmEndTime","handlerStartTime","handlerEndTime"};
	
	@Override 
	public PagingVO<AlarmVO> queryAlarmVOForPaging(AlarmVO paramsOb, int pageNum, int pageSize,String loginUserType,String userId,String groupNo) {
			Alarm ob=new Alarm(); 
			if(paramsOb!=null){
				ob=covertVotoOb(paramsOb);
			}
			if(Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)){//用户登录
				OwnerUser ouVo=new OwnerUser();
				ouVo.setUserId(userId);
				ob.setOwnerUser(ouVo);
			}else if(Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)){//物业管理员登录
				HousingDistrictInfo hdOb=new HousingDistrictInfo();
				hdOb.setId(groupNo); 
				ob.setHousingDistrictInfo(hdOb);
			}
			Paging paging=getSmartHomeService().queryAlarmForPaging(ob, pageNum, pageSize);
			PagingVO<AlarmVO> pagingVO = convertToVO(paging,IGNOREPRO_PERTIES_OB,CREATED_TIMES);
			List<Object> list = paging.getResults();
			if(list != null && list.size() > 0){
				List<AlarmVO> results = new ArrayList<AlarmVO>();
				for (int index = 0, size = list.size(); index < size; index++) {
					AlarmVO resultVo=covertObtoVo((Alarm)list.get(index));
//					CellHouseholdInfoVo dVo=cellHouseholdInfoService.get(resultVo.getHouseNo());
					DeviceVO deviceVo=deviceService.queryDeviceByCode(resultVo.getDeviceCode());
					resultVo.setHouseNo(getHomeText(deviceVo));
					results.add(resultVo);
				}
				pagingVO.setResults(results);
			}
			return pagingVO;
	}
	
	@Override 
	public List<AlarmVO> queryAlarmVOForIndex(String loginUserType,String userId,String districtId) {
			Alarm ob=new Alarm(); 
			if(Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)){//物业管理员登录
				HousingDistrictInfo hdOb=new HousingDistrictInfo();
				hdOb.setId(districtId); 
				ob.setHousingDistrictInfo(hdOb);
//				ob.setCreatedTime(this.convertToXMLGregorianCalendar(new Date()));
			}
			Paging paging=getSmartHomeService().queryAlarmForPaging(ob, 1, Constants.SELECT_COUNT_FOR_INDEX);
			List<Object> list = paging.getResults();
			List<AlarmVO> results = new ArrayList<AlarmVO>();
			if(list != null && list.size() > 0){
				for (int index = 0, size = list.size(); index < size; index++) {
					AlarmVO resultVo=covertObtoVo((Alarm)list.get(index));
					resultVo.setDevice(getDeviceVO(resultVo.getDeviceCode()));
					results.add(resultVo);
				}
			}
			return results;
	}
	
	
	@Override 
	public Object[] queryAlarmStringForMap(String alarmId,String groupNo) {
		Object[] result=new Object[3];
//			Locale local = Locale.US; 
			List<Map<String,String>> houseList=new ArrayList<Map<String,String>>();
			List<Map<String,String>> userMsgList=new ArrayList<Map<String,String>>();
			Set<String> XYCoordinatesMap=new HashSet<String>();
			String districtMapPath="";
//			String userName = ResourceBundle.getBundle("com.i18n.info_messages",local).getString("test.userName");
			if(StringUtils.isNotBlank(alarmId)){
				Alarm alarmOb=getSmartHomeService().getAlarm(alarmId);
				//CellHouseholdInfoVo dVo=cellHouseholdInfoService.get(alarmOb.getHouseNo());
				DeviceVO deviceVo=deviceService.queryDeviceByCode(alarmOb.getDeviceCode());
				if(deviceVo!=null){
					setListForMap(alarmOb,deviceVo,houseList,userMsgList,XYCoordinatesMap);
					HousingDistrictInfoVo districtVo=deviceVo.getHousingDistrictInfo();
					districtMapPath=districtVo.getFloorPlan()==null?"/images/map.png":districtVo.getFloorPlan();
				}
			}else{
				Alarm ob=new Alarm(); 
				HousingDistrictInfo hdOb=new HousingDistrictInfo();
				hdOb.setId(groupNo); 
				ob.setHousingDistrictInfo(hdOb);
//				ob.setStatus(AlarmVO.HANLDER_STATUS_NO.toString());
				ob.setCancelAndNoHanlder(true);
				List<Alarm> list=getSmartHomeService().queryAlarms(ob);
				if(list != null && list.size() > 0){
					for (int index = 0, size = list.size(); index < size; index++) {
						Alarm alarmOb=(Alarm)list.get(index);
						//CellHouseholdInfoVo dVo=cellHouseholdInfoService.get(alarmOb.getHouseNo());
						DeviceVO deviceVo=deviceService.queryDeviceByCode(alarmOb.getDeviceCode());
						if(deviceVo!=null){
							setListForMap(alarmOb,deviceVo,houseList,userMsgList,XYCoordinatesMap);
							HousingDistrictInfoVo districtVo=deviceVo.getHousingDistrictInfo();
							districtMapPath=districtVo.getFloorPlan()==null?"/images/map.png":districtVo.getFloorPlan();
						}
					}
				}
			}
			result[0]=houseList;
			result[1]=userMsgList;
			result[2]=districtMapPath;
			return result;
	}
	
	private void setListForMap(Alarm alarmOb,DeviceVO deviceVo,List<Map<String,String>> houseList,List<Map<String,String>> userMsgList,Set<String> XYCoordinatesMap){
		if(deviceVo!=null){
			Map<String,String> houseMap=new HashMap<String,String>();
			String xyCoordinates=deviceVo.getRegionBuildingInfo().getCoordinate();
			OwnerUserVO userVo=null;
			if(deviceVo.getCellHouseholdInfo()!=null) userVo=ownerUserService.getUserByHouseId(deviceVo.getCellHouseholdInfo().getId());
			String XCoordinates="";String YCoordinates="";
			if(StringUtils.isNotBlank(xyCoordinates)){//判断坐标是否为空
				String[] xys=xyCoordinates.split(",");
				XCoordinates=xys[0];
				YCoordinates=xys[1];
			}
			String XYCoordinates=XCoordinates+","+YCoordinates;
			if(XYCoordinatesMap.contains(XYCoordinates)){//坐标一样的在原来的map上的房号与惟一标识上累加
				for (int i = 0; i < houseList.size(); i++) {
					Map<String,String> houseMapEd=houseList.get(i);
					if(houseMapEd.get("XYCoordinates").equals(XYCoordinates)){
						String houseNo=getLocation(deviceVo);
						if(StringUtils.isNotBlank(houseMapEd.get("houseNo"))&&houseMapEd.get("houseNo").indexOf(getLocation(deviceVo))==-1){
							houseMapEd.put("houseNo", houseMapEd.get("houseNo")+","+getLocation(deviceVo));
							houseMapEd.put("uniqueFlag", houseMapEd.get("uniqueFlag")+","+alarmOb.getDeviceCode());
						}else {
							if(houseMapEd.get(houseNo)==null) houseMapEd.put(houseNo, 2+"");
							else houseMapEd.put(houseNo, (Integer.parseInt(houseMapEd.get(houseNo))+1)+"");
						}
					}
				}
			}else{//坐标不一样的新加一个Map
				XYCoordinatesMap.add(XYCoordinates);
				houseMap.put("XYCoordinates", XYCoordinates);
				houseMap.put("uniqueFlag", alarmOb.getDeviceCode());
				houseMap.put("houseNo",getLocation(deviceVo));
				houseMap.put("userName",userVo==null?"":userVo.getUserName());
				houseMap.put("userPhone",userVo==null?"":userVo.getMobilePhone());
				houseMap.put("XCoordinates",XCoordinates);
				houseMap.put("YCoordinates",YCoordinates);
				houseList.add(houseMap);
			}
			Map<String,String> usermsgMap=new HashMap<String,String>();//包装地图需要的所有信息
			usermsgMap.put("houseText",getHomeText(deviceVo));//房间显示的文本
			usermsgMap.put("uniqueFlag", alarmOb.getDeviceCode());//唯一标识，在点击地图上的每个房间号使用
			usermsgMap.put("workUnit",userVo==null||userVo.getWorkUnit()==null?"":userVo.getWorkUnit());//工作单位
			usermsgMap.put("companyPhone",userVo==null?"":userVo.getHomePhone());//公司电话
			usermsgMap.put("contact",userVo==null?"":userVo.getUserName());//联系人
			usermsgMap.put("userName",userVo==null?"":userVo.getUserName());//用户名
			usermsgMap.put("XCoordinates",XCoordinates);//横坐标
			usermsgMap.put("YCoordinates",YCoordinates);//Y坐标
			usermsgMap.put("AlarmId",alarmOb.getAlarmId());//报警ID
			usermsgMap.put("status", alarmOb.getStatus());//报警状态
			userMsgList.add(usermsgMap);
		}
	}
	@Override
	public String getHomeText(DeviceVO deviceVo){
		if(deviceVo==null) return "";
		else{
			StringBuilder sb=new StringBuilder();
			if(deviceVo.getHousingDistrictInfo()!=null) sb.append(deviceVo.getHousingDistrictInfo().getName());
			if(deviceVo.getHousingDistrictRegionInfo()!=null) sb.append("-"+deviceVo.getHousingDistrictRegionInfo().getName());
			if(deviceVo.getRegionBuildingInfo()!=null) sb.append("-"+deviceVo.getRegionBuildingInfo().getName());
			if(deviceVo.getBuildingCellInfo()!=null) sb.append("-"+deviceVo.getBuildingCellInfo().getName());
			if(deviceVo.getCellHouseholdInfo()!=null) sb.append("-"+deviceVo.getCellHouseholdInfo().getName());
			return sb.toString();
		}
		//return dVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getName()+"-"+dVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName()+"-"+dVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName()+"-"+dVo.getTHmBuildingCellInfo().getName()+"-"+dVo.getName();
	}
	@Override
	public List<Alarm> queryAlarmsList(Alarm ob){
		List<Alarm> list=getSmartHomeService().queryAlarms(ob);
		return list;
	}
	
	private String getLocation(DeviceVO deviceVo){
		if(deviceVo==null) return "";
		if(deviceVo.getCellHouseholdInfo()!=null) return deviceVo.getCellHouseholdInfo().getName();
		else return deviceVo.getPosition();
	}
	
	@Override
	public DeviceVO getDeviceVO(String deviceNo){
		DeviceVO vo=new DeviceVO();
		vo.setDeviceCode(deviceNo);
		List<DeviceVO> result=deviceService.queryDevices(vo);
		if(!result.isEmpty()) return result.get(0);
		return null;
	}
	@Override
	public AlarmVO getAlarmVO(String entityId) {
			Alarm ob=getSmartHomeService().getAlarm(entityId);
			AlarmVO resultVo=covertObtoVo(ob);
			resultVo.setDevice(getDeviceVO(resultVo.getDeviceCode()));
			return resultVo;
	}
	
	private void copyPropertiesObToVo(Alarm ob ,AlarmVO vo){
		this.copyProperties(ob,vo,IGNOREPRO_PERTIES_OB,true,CREATED_TIMES);
	}
	private void copyPropertiesVoToOb(AlarmVO vo ,Alarm ob){
		this.copyProperties(vo,ob,IGNOREPRO_PERTIES_VO,false,CREATED_TIMES);
	}

	private AlarmVO covertObtoVo(Alarm dnoOb){
		AlarmVO result=new AlarmVO();
		copyPropertiesObToVo(dnoOb, result);
		//转换对象
		AlarmTypeVO ayVo=new AlarmTypeVO();
		copyProperties(dnoOb.getAlarmType(),ayVo);
		result.setAlarmType(ayVo);
		PaUserVO puOb=new PaUserVO();
		if(dnoOb.getPaUser()!=null){
			if(dnoOb.getPaUser().getUserId()!=null) puOb.setUserId(dnoOb.getPaUser().getUserId());
			puOb.setUserName(dnoOb.getPaUser().getUserName());
		}
		result.setPaUser(puOb);
		OwnerUserVO onOb=new OwnerUserVO();
		OwnerUserVO userVo=ownerUserService.getUserByHouseId(dnoOb.getHouseNo());
		if(userVo==null){userVo=new OwnerUserVO();}
		copyProperties(userVo, onOb, new String[]{"birthDate","createdTime","updatedTime","login"});
		result.setOwnerUser(onOb);
		HousingDistrictInfoVo hdVo=new HousingDistrictInfoVo();
		if(dnoOb.getHousingDistrictInfo()!=null){
			if(dnoOb.getHousingDistrictInfo().getId()!=null) hdVo.setId(dnoOb.getHousingDistrictInfo().getId());
		}
		result.setHousingDistrictInfo(hdVo);
		return result; 
	}
	private Alarm covertVotoOb(AlarmVO entity){
		Alarm result=new Alarm();
		copyPropertiesVoToOb(entity, result);
		AlarmType ayVo=new AlarmType();
		copyProperties(entity.getAlarmType(),ayVo);
		result.setAlarmType(ayVo);
		PaUser puOb=new PaUser();
		if(entity.getPaUser()!=null){
			if(entity.getPaUser().getUserId()!=null) puOb.setUserId(entity.getPaUser().getUserId());
			puOb.setUserName(entity.getPaUser().getUserName());
		}
		result.setPaUser(puOb);
		HousingDistrictInfo hdOb=new HousingDistrictInfo();
		if(entity.getHousingDistrictInfo()!=null){
			if(entity.getHousingDistrictInfo().getId()!=null) hdOb.setId(entity.getHousingDistrictInfo().getId());
		}
		result.setHousingDistrictInfo(hdOb);
		OwnerUser onOb=new OwnerUser();
		if(entity.getOwnerUser()!=null){
			/*if(dnoOb.getOwnerUser().getUserId()!=null) onOb.setUserId(dnoOb.getOwnerUser().getUserId());
			onOb.setUserName(dnoOb.getOwnerUser().getUserName());*/
			copyProperties(entity.getOwnerUser(), onOb, new String[]{"birthDate","createdTime","updatedTime","login"});
		}
		result.setOwnerUser(onOb);
		return result;
	}
	
	/**
	 * 查询业主总报警数
	 * @return
	 */
	@Override
	public Long getOwnerAlarmCount(String loginUserType,String userId,String districtId){
			Alarm paramsOb=new Alarm();
			if(Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)){//业主登录
				OwnerUser ou=new OwnerUser();
				ou.setUserId(userId);
				paramsOb.setOwnerUser(ou);
			}
			return getSmartHomeService().getOwnerAlarmCount(paramsOb);
	}
	
	@Override
	public List<AlarmVO> queryAlarms(AlarmVO alarm) {
		Alarm al = new Alarm();
		copyProperties(alarm,al,false,CREATED_TIME);
		List<Alarm> alarms = getSmartHomeService().queryAlarms(al);
		List<AlarmVO> list = new ArrayList<AlarmVO>();
		if(alarms == null || alarms.size() == 0)
			return list;
		for (Alarm a : alarms) {
			AlarmVO avo = covertObtoVo(a);
			list.add(avo);
		}
		return list;
	}

	@Override
	public AlarmVO queryAlarmById(String id) {
		AlarmVO alarmVO = new AlarmVO();
		AlarmTypeVO alarmTypeVO = new AlarmTypeVO();
		copyProperties(getSmartHomeService().queryAlarmById(id),alarmVO,new String[]{ALARMTYPE},true,CREATED_TIME);
		copyProperties(getSmartHomeService().queryAlarmById(id).getAlarmType(),alarmTypeVO);
		alarmVO.setAlarmType(alarmTypeVO);
		return alarmVO;
	}

	@Override
	public void updateAlarmStatus(String status,String id,String userId) { 
		Alarm ob=getSmartHomeService().getAlarm(id);
		ob.setStatus(status);
		if(AlarmVO.HANLDER_STATUS_YES==Integer.parseInt(status)){
			ob.setHanlderTime(convertToXMLGregorianCalendar(new Date(System.currentTimeMillis())));
			PaUser pa=new PaUser();
			pa.setUserId(userId);
			ob.setPaUser(pa);
		}
		getSmartHomeService().updateAlarm(ob);
	}
	
	@Override
	public void delAlarm(String id) {
		Alarm ob=getSmartHomeService().getAlarm(id);
		getSmartHomeService().delAlarm(ob);
	}
	
	@Override
	public void saveOrUpdateAlarm(AlarmVO alarm) {
		Alarm al = new Alarm();
		AlarmType alarmType = new AlarmType();
		copyProperties(alarm.getAlarmType(),alarmType);
		copyProperties(alarm,al,new String[]{ALARMTYPE},false,CREATED_TIME);
		al.setAlarmType(alarmType);
		getSmartHomeService().saveOrUpdateAlarm(al);
	}

	@Override
	public void removeAlarm(AlarmVO alarm) {
		Alarm al = new Alarm();
		AlarmType alarmType = new AlarmType();
		copyProperties(alarm.getAlarmType(),alarmType);
		copyProperties(alarm,al,new String[]{ALARMTYPE},false,CREATED_TIME);
		al.setAlarmType(alarmType);
		getSmartHomeService().removeAlarm(al);
	}
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public Json saveOrUpdateAlarm(String jsonString) {
		Json js = new Json();
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(jsonString);
		String deviceCode = jsonObj.getString("deviceNo");
		String alarmType = jsonObj.getString("alarmType");
		String alarmContent = jsonObj.getString("alarmName");
		String alarmStatus = jsonObj.getString("alarmStatus");
		Long alarmTime = jsonObj.getLong("createTime");
		Alarm alarm=null;
		//如果状态为取消状态，先判断此条报警是否已存在
		List<Alarm> list=new ArrayList<Alarm>();
		if("0".equals(alarmStatus)){
			Alarm paramsOb=new Alarm();
			paramsOb.setDeviceCode(deviceCode);
			AlarmType at=new AlarmType();
			at.setAlarmType(alarmType);
			paramsOb.setAlarmType(at);
			paramsOb.setContent(alarmContent);
			paramsOb.setStatus("1");
			list = getSmartHomeService().queryAlarms(paramsOb);
		}
		if(!list.isEmpty()){
			alarm=list.get(0);
			alarm.setStatus(alarmStatus);
		}else{
			DeviceVO device = deviceService.queryDeviceByCode(deviceCode);
			if(device==null){
				device = new DeviceVO();
			}
			alarm = new Alarm();
			AlarmType type = new AlarmType();
			type.setAlarmType(alarmType);
			HousingDistrictInfo hdOb=new HousingDistrictInfo();
			hdOb.setId(device.getHousingDistrictInfo().getId()); 
			alarm.setAlarmType(type);
			alarm.setCreatedTime(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date(alarmTime)));
			alarm.setDeviceCode(deviceCode);
			alarm.setContent(alarmContent);
			alarm.setStatus(alarmStatus.trim());
			String houseNo=device.getPosition();//非室内机报警保存位置号
			if(device.getCellHouseholdInfo()!=null) houseNo=device.getCellHouseholdInfo().getId();
			alarm.setHouseNo(houseNo);
			alarm.setHousingDistrictInfo(hdOb);
		}
		try{
			getSmartHomeService().saveOrUpdateAlarm(alarm);
			js.setCode(AppDataConstant.SUCCESS);
		}catch(Exception e){
			js.setCode(AppDataConstant.FAILTRUE);
		}
  		js.setDeviceNo(deviceCode);
		return js;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
	
}
