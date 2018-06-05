package com.biencloud.smarthome.web.alarm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.alarm.service.IAlarmService;
import com.biencloud.smarthome.web.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;
import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.wsclient.stub.Alarm;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：AlarmAction 
 * 类描述： 报警管理管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:51:06
 */
public class AlarmAction extends BaseAction<AlarmVO> {

	private static final long serialVersionUID = 1L;

	private IAlarmService alarmService;
	
	private IAlarmTypeService alarmTypeService;
	
	private ISysParamService sysParamService;

	private AlarmVO alarm;
	
	private List<AlarmTypeVO> alarmTypeList;
	
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IDeviceService deviceService;

	/**
	 * 
	 * 方法的描述: 报警管理列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:51:22
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<AlarmVO> page = getPage();
		if (page == null)
			page = new PagingVO<AlarmVO>();
		PagingVO<AlarmVO> pagingVO = alarmService.queryAlarmVOForPaging(alarm, page.getPageNum(), page.getPageSize(), getUserType(), getUserId(), getDistrictId());
		List<AlarmVO> results = pagingVO.getResults();
		if (!CollectionUtils.isEmpty(results)) {
			for (AlarmVO vo : results) {
				AlarmTypeVO alarmType = vo.getAlarmType();
				alarmType.setAlarmName(this.getText(alarmType.getAlarmName()));
			}
		}
		setPage(pagingVO);
		alarmTypeList = alarmTypeService.queryAlarmTypes(new AlarmTypeVO());
		if (!CollectionUtils.isEmpty(alarmTypeList)) {
			for (AlarmTypeVO vo : alarmTypeList) {
				vo.setAlarmName(this.getText(vo.getAlarmName()));
			}
		}
		return "list";
	}
	
	
	
	/**
	 * 
	 * 方法的描述: 查找单个报警管理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:51:29
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		alarm = alarmService.getAlarmVO(id);
		AlarmTypeVO alarmType = alarm.getAlarmType();
		alarmType.setAlarmName(this.getText(alarmType.getAlarmName()));
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 删除报警管理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:51:39
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		alarmService.delAlarm(id);
		this.getRequest().setAttribute("editResult", true);
		alarmTypeList = new ArrayList<AlarmTypeVO>();
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 获取地图信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:51:53
	 * @return
	 */
	public String getMapMsg() throws Exception {
		String id = getRequest().getParameter("requestId");
		if (ActionUtils.judgContainsValue(getRequest(), "map"))
			id = null;// 在地图处理后返回地图,此处设置的原因是因为updateStatus方法中getRequest().getParameter("requestId")的值不为空
		Object[] result = alarmService.queryAlarmStringForMap(id, getDistrictId());
		setRequestAttribute("houseTextList", result[0]);
		setRequestAttribute("useMsgTextList", result[1]);
		setRequestAttribute("districtMapPath", result[2]);
		setRequestAttribute("fileServerUrl", sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL));
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 返回报警信息li列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:52:05
	 * @return
	 */
	public String queryAlarmString() throws Exception {
		String path = getRequest().getContextPath();
		String result = queryAlarmString(getAlarm(), getDistrictId(), path);
		this.getResponse().setContentType("text/html; charset=UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		out.write(result);
		out.close();
		return null;
	}
	
	/**
	 * 
	 * 方法的描述: 根据条件参数查询，把结果拼装成li列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-26 上午11:09:16
	 * @param paramsOb
	 * @param groupNo
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String queryAlarmString(AlarmVO paramsOb, String groupNo, String path) throws Exception {
		StringBuilder sp = new StringBuilder();
		Alarm ob = new Alarm();
		/*
		 * if(paramsOb!=null){ ob=covertVotoOb(paramsOb); }
		 */
		HousingDistrictInfo hdOb = new HousingDistrictInfo();
		if (StringUtils.isNotBlank(groupNo))
			hdOb.setId(groupNo);
		//ob.setHousingDistrictInfo(hdOb);
		// ob.setStatus(AlarmVO.HANLDER_STATUS_NO.toString());
		ob.setCancelAndNoHanlder(true);
		List<Alarm> list = alarmService.queryAlarmsList(ob);
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				Alarm resultOb = (Alarm) list.get(index);
				DeviceVO deviceVo = deviceService.queryDeviceByCode(resultOb.getDeviceCode());
				// CellHouseholdInfoVo
				// dVo=cellHouseholdInfoService.get(resultOb.getHouseNo());
				if (deviceVo != null)
					sp.append("<li><span>" + this.getText(resultOb.getAlarmType().getAlarmName()) + "</span><a href='" + path + "/alarm/getMapMsg.action?requestId=" + resultOb.getAlarmId() + "' target='main'>" + alarmService.getHomeText(deviceVo) + "</a></li>");
			}
		}
		return sp.toString();
	}
	
	/**
	 * 
	 * 方法的描述: 更新报警状态
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:52:16
	 * @return
	 */
	public String updateStatus() throws Exception {
		String id = getRequest().getParameter("requestId");
		String status = getRequest().getParameter("status");
		alarmService.updateAlarmStatus(status, id, getUserId());
		if (ActionUtils.judgContainsValue(getRequest(), "map")) {
			setRequestAttribute("requsetUniqueFlag", getParameter("requsetUniqueFlag"));
			return getMapMsg();
		}
		this.getRequest().setAttribute("editResult", true);
		alarmTypeList = new ArrayList<AlarmTypeVO>();
		return "list";
	}

	public IAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public AlarmVO getAlarm() {
		return alarm;
	}

	public void setAlarm(AlarmVO alarm) {
		this.alarm = alarm;
	}



	public IAlarmTypeService getAlarmTypeService() {
		return alarmTypeService;
	}



	public void setAlarmTypeService(IAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
	}



	public List<AlarmTypeVO> getAlarmTypeList() {
		return alarmTypeList;
	}



	public void setAlarmTypeList(List<AlarmTypeVO> alarmTypeList) {
		this.alarmTypeList = alarmTypeList;
	}



	public ISysParamService getSysParamService() {
		return sysParamService;
	}



	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}



	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}



	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}



	public IDeviceService getDeviceService() {
		return deviceService;
	}



	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	

	
	

}
