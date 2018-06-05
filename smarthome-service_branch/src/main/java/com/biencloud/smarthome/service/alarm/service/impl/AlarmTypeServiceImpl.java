package com.biencloud.smarthome.service.alarm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.biencloud.smarthome.service.alarm.model.AlarmType;
import com.biencloud.smarthome.service.alarm.service.IAlarmTypeService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：AlarmServiceImpl 
 * 类描述： 报警类型服务接口实现类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:42:19
 */
public class AlarmTypeServiceImpl extends BaseService<AlarmType,String> implements IAlarmTypeService{

	@Override
	public List<AlarmType> queryAlarmType(AlarmType alarmType) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, alarmType);
		jpql.insert(0, "SELECT alarmType FROM AlarmType alarmType");
		return findByNamedParams(jpql.toString(), params);
	}
	
	// 创建属性变量名和属性值映射。
			private Map<String, Object> createQueryParams(StringBuilder jpql, 
					AlarmType alarmType) {
				Map<String, Object> params = new HashMap<String, Object>();
				if (alarmType == null)
					return params;

				if (StringUtils.isNotBlank(alarmType.getAlarmType()))
					appendCondition(jpql, "alarmType.alarmType = :alarmType", "alarmType", 
							alarmType.getAlarmType(), params);

				if (StringUtils.isNotBlank(alarmType.getAlarmName()))
					appendCondition(jpql, "alarmType.alarmName = :alarmName", "alarmName",
							alarmType.getAlarmName(), params);
				return params;
			}
}
