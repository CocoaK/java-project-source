package com.biencloud.smarthome.web.alarm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.web.alarm.vo.AlarmTypeVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.wsclient.stub.AlarmType;
/**
 * 报警类型管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 */
public class AlarmTypeServiceImpl extends BaseService<AlarmTypeVO> implements IAlarmTypeService{

	@Override
	public List<AlarmTypeVO> queryAlarmTypes(AlarmTypeVO alarmTypeVO) {
		AlarmType alarmType = new AlarmType();
		copyProperties(alarmTypeVO,alarmType);
		List<AlarmTypeVO> list = new ArrayList<AlarmTypeVO>();
		List<AlarmType> alarmTypes =  getSmartHomeService().queryAlarmType(alarmType);
		if(alarmTypes == null || alarmTypes.size() == 0)
			return list;
		for(AlarmType at : alarmTypes){
			AlarmTypeVO vo = new AlarmTypeVO();
			copyProperties(at,vo);
			list.add(vo);
		}
		return list;
	}

	@Override
	public AlarmTypeVO queryAlarmTypeById(String alarmType) {
		AlarmTypeVO alarmTypeVO = new AlarmTypeVO();
		copyProperties(getSmartHomeService().queryAlarmById(alarmType),alarmTypeVO);
		return alarmTypeVO;
	}

	@Override
	public void saveOrUpdateAlarmType(AlarmTypeVO alarmTypeVO) {
		AlarmType alarmType = new AlarmType();
		copyProperties(alarmTypeVO,alarmType);
		getSmartHomeService().saveOrUpdateAlarmType(alarmType);
	}

	@Override
	public void removeAlarmType(AlarmTypeVO alarmTypeVO) {
		AlarmType alarmType = new AlarmType();
		copyProperties(alarmTypeVO,alarmType);
		getSmartHomeService().removeAlarmType(alarmType);
	}

}
