package com.biencloud.smarthome.service.log.service.impl;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.AppDataLog;
import com.biencloud.smarthome.service.log.service.IAppDataLogService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 类名称：AppDataLogServiceImpl 
 * 类描述： app请求数据日志服务接口实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-17 下午3:42:57
 */
public class AppDataLogServiceImpl extends BaseService<AppDataLog,Integer> implements IAppDataLogService{
	private ISysParamService sysParamService;
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	@Override
	public Paging<AppDataLog> queryAppDataLogForPaging(AppDataLog appDataLog, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql,appDataLog);
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM AppDataLog appDataLog");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "appDataLog");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(appDataLog)");
		queryString = queryString + " order by appDataLog.requestTime desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	
	// 创建属性变量名和属性值映射。
		private Map<String, Object> createParams(StringBuilder jpql,AppDataLog appDataLog) {
			Map<String, Object> params = new HashMap<String, Object>();
			if(appDataLog == null)
				return params;
			if(StringUtils.isNotBlank(appDataLog.getAction()))
				appendCondition(jpql, " appDataLog.action = :action", 
						"action", appDataLog.getAction(), params);
			if(StringUtils.isNotBlank(appDataLog.getDeviceName()))	
				appendCondition(jpql, " appDataLog.deviceName like :deviceName", 
						"deviceName", "%"+appDataLog.getDeviceName()+"%", params);
			if(StringUtils.isNotBlank(appDataLog.getDeviceNo()))
				appendCondition(jpql, " appDataLog.deviceNo like :deviceNo", 
						"deviceNo", "%"+appDataLog.getDeviceNo()+"%", params);
			if(StringUtils.isNotBlank(appDataLog.getIp()))	
				appendCondition(jpql, " appDataLog.ip like :ip", 
						"ip", "%"+appDataLog.getIp()+"%", params);
			if(StringUtils.isNotBlank(appDataLog.getMac()))	
				appendCondition(jpql, " appDataLog.mac like :mac", 
						"mac", "%"+appDataLog.getMac()+"%", params);
			if(StringUtils.isNotBlank(appDataLog.getResult()))	
				appendCondition(jpql, " appDataLog.result = :result", 
						"result", appDataLog.getResult(), params);
			if(StringUtils.isNotBlank(appDataLog.getPosition()))	
				appendCondition(jpql, " appDataLog.position like :position", 
						"position", "%"+appDataLog.getPosition()+"%", params);
			
			appendCondition(jpql, "appDataLog.requestTime >= :beginTime", "beginTime",
					appDataLog.getBeginTime(), params);
			
			appendCondition(jpql, "appDataLog.requestTime <= :endTime", "endTime",
					appDataLog.getEndTime(), params);
			return params;
		}
		//添加查询条件
		private void appendCondition(StringBuilder jpql, String condition, 
				String key, String value, Map<String, Object> params) {
			if (StringUtils.isNotBlank(value)) {
				if (StringUtils.contains(jpql, " WHERE ")) {
					jpql.append(" AND").append(condition);
				} else {
					jpql.append(" WHERE").append(condition);
				}			
				params.put(key, value);
			}
		}
		
		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public void deleteAppDataLogs(){
			GregorianCalendar gc = new GregorianCalendar();
			//删除interval天之前的
			String interval = sysParamService.queryParamValueByParamCode(Constants.APP_DATA_LOG_DELETE_DAY_COUNT);
			gc.add(GregorianCalendar.DATE, -Integer.parseInt(interval));
			String queryString = "DELETE FROM AppDataLog WHERE requestTime < ?1 ";
			super.removeByParams(queryString, gc.getTime());
		}
}
