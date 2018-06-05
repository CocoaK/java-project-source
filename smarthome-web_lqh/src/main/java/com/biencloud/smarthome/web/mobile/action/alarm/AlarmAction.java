package com.biencloud.smarthome.web.mobile.action.alarm;

import java.util.List;
import org.springframework.util.CollectionUtils;
import com.biencloud.smarthome.web.alarm.service.IAlarmService;
import com.biencloud.smarthome.web.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;
import com.biencloud.smarthome.web.alarm.vo.AlarmVO;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 类名称：AlarmAction 
 * 类描述： Web移动版报警管理管理类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-6 下午2:14:30
 */
@SuppressWarnings("serial")
public class AlarmAction extends BaseAction<AlarmVO> {

	private IAlarmService alarmService;
	
	private IAlarmTypeService alarmTypeService;

	private AlarmVO alarm;
	
	private List<AlarmTypeVO> alarmTypeList;

	/**
	 * 方法的描述: 报警管理列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-7 上午9:55:05
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception{
		PagingVO<AlarmVO> page = getPage();
		alarmTypeList=alarmTypeService.queryAlarmTypes(new AlarmTypeVO());
		if(!CollectionUtils.isEmpty(alarmTypeList)){
			for(AlarmTypeVO vo : alarmTypeList){
				if(vo!=null)
					vo.setAlarmName(this.getText(vo.getAlarmName()));	//报警类型名称国际化
			}
		}
		if (page == null)
			page = new PagingVO<AlarmVO>();
		PagingVO<AlarmVO> pagingVO = alarmService.queryAlarmVOForPaging(alarm, page.getPageNum(),page.getPageSize(),getUserType(),getUserId(),getDistrictId());
		List<AlarmVO> results = pagingVO.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for(AlarmVO vo : results){
				if(vo!=null){
					AlarmTypeVO alarmType=vo.getAlarmType();
					alarmType.setAlarmName(this.getText(alarmType.getAlarmName()));	//报警类型名称国际化
				}
			}
		}
		setPage(pagingVO);
		return "list";
	}
	
	/**
	 * 方法的描述: 查找单个报警管理
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-7 上午9:57:40
	 * @return
	 * @throws Exception
	 */
	public String findById() throws Exception{
		String id=getRequest().getParameter("requestId");
		alarm=alarmService.getAlarmVO(id);
		AlarmTypeVO alarmType=alarm.getAlarmType();
		if(alarmType!=null)
			alarmType.setAlarmName(this.getText(alarmType.getAlarmName()));
		return "editPage";
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

	public AlarmVO getAlarm() {
		return alarm;
	}

	public void setAlarm(AlarmVO alarm) {
		this.alarm = alarm;
	}

	public List<AlarmTypeVO> getAlarmTypeList() {
		return alarmTypeList;
	}

	public void setAlarmTypeList(List<AlarmTypeVO> alarmTypeList) {
		this.alarmTypeList = alarmTypeList;
	}
}
