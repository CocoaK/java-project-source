package com.biencloud.smarthome.service.log.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.SystemLog;
import com.biencloud.smarthome.service.log.service.ISystemLogService;

/**
 * 系统日志服务实现类。
 * @author Cocoa
 * @since 1.0 2012-5-3
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class SystemLogServiceImpl extends BaseService<SystemLog,String> implements ISystemLogService{

	@Override
	public Paging<SystemLog> querySystemLogForPaging(String logId,String operateUser,String operateTime,String menuCode, int pageNum, int pageSize) {
		// TODO Auto-generated method stub	
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(
				jpql,logId,operateUser, operateTime, menuCode);
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM SystemLog systemLog");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "systemLog");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(systemLog)");
		queryString = queryString + " order by systemLog.operateTime desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,String logId,
			String operateUser,String operateTime,String menuCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(logId))	
			appendCondition(jpql, " systemLog.logId = :logId", 
					"logId", logId, params);
		if(StringUtils.isNotBlank(operateUser))	
			appendCondition(jpql, " systemLog.operateUser like :operateUser", 
					"operateUser", "%"+operateUser+"%", params);
		if(StringUtils.isNotBlank(menuCode))	
			appendCondition(jpql, " systemLog.menuCode = :menuCode", 
					"menuCode", menuCode, params);
		if(StringUtils.isNotBlank(operateTime))	
			appendCondition(jpql, " systemLog.operateTime = :operateTime", 
					"operateTime", operateTime, params);
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
	public SystemLog getSystemLogById(String logId) {
		return get(logId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdate(SystemLog systemLog) {
		boolean isSuccess = false;
		if (systemLog != null) {
			if (systemLog.getLogId() == null) {
				super.save(systemLog);
				isSuccess = true;
			} else {
				super.update(systemLog);
				isSuccess = true;
			}
		}
		return isSuccess;
	}
}
