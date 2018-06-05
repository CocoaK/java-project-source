package com.biencloud.smarthome.service.log.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.FileUtil;
import com.biencloud.smarthome.service.common.utils.ServerPathUtil;
import com.biencloud.smarthome.service.log.model.OperationLog;
import com.biencloud.smarthome.service.log.service.IAppDataLogService;
import com.biencloud.smarthome.service.log.service.IOperationLogService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 操作日志服务实现类。
 * @author Cocoa
 * @since 1.0 2012-5-3
 */

public class OperationLogServiceImpl extends BaseService<OperationLog,String> implements IOperationLogService{
	private ISysParamService sysParamService;
	private IAppDataLogService appDataLogService;
	@Override
	public Paging<OperationLog> queryOperationLogForPaging(String logId,String operateUser,String operateTime,
							String menuCode,String operateResult, int pageNum, int pageSize) {
		// TODO Auto-generated method stub	
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(
				jpql,logId,operateUser,operateTime, menuCode,operateResult);
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM OperationLog operationLog");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "operationLog");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(operationLog)");
		queryString = queryString + " order by operationLog.operateTime desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,String logId,
			String operateUser,String operateTime,String menuCode,String operateResult) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(logId))
			appendCondition(jpql, " operationLog.logId = :logId", 
					"logId", logId.trim(), params);
		if(StringUtils.isNotBlank(operateUser))	
			appendCondition(jpql, " operationLog.operateUser = :operateUser", 
					"operateUser", operateUser.trim(), params);
		if(StringUtils.isNotBlank(menuCode))	
			appendCondition(jpql, " operationLog.menuCode = :menuCode", 
					"menuCode", menuCode.trim(), params);
		if(StringUtils.isNotBlank(operateTime))	
			appendCondition(jpql, " operationLog.operateTime = :operateTime", 
					"operateTime", operateTime, params);
		if(StringUtils.isNotBlank(operateResult))	
			appendCondition(jpql, " operationLog.operateResult = :operateResult", 
					"operateResult", operateResult.trim(), params);
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
	public OperationLog getOperationLog(String logId) {
		return get(logId);
	}
	@Override
	public boolean saveOrUpdate(OperationLog operationLog) {
		boolean isSuccess = false;
		if (operationLog != null) {
			if (operationLog.getLogId() == null) {
				super.save(operationLog);
				isSuccess = true;
			} else {
				super.update(operationLog);
				isSuccess = true;
			}
		}
		return isSuccess;
	}
	@Override
	public Paging<OperationLog> queryOperationLogForPaging(OperationLog operationLog, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql,operationLog);
		jpql.insert(0, "SELECT " + REPLACE_CHARS +" FROM OperationLog operationLog");
		String queryString = jpql.toString().replace(
				REPLACE_CHARS, "operationLog");
		String queryStringCount = jpql.toString().replace(
				REPLACE_CHARS, "COUNT(operationLog)");
		queryString = queryString + " order by operationLog.operateTime desc ";
		return queryByNamedParamsForPaging(pageNum, 
				pageSize, queryString, queryStringCount, params);
	}
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createParams(StringBuilder jpql,OperationLog operationLog) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(operationLog == null)
			return params;
		if(StringUtils.isNotBlank(operationLog.getLogId()))
			appendCondition(jpql, " operationLog.logId = :logId", 
					"logId", operationLog.getLogId().trim(), params);
		if(StringUtils.isNotBlank(operationLog.getOperateUser()))	
			appendCondition(jpql, " operationLog.operateUser like :operateUser", 
					"operateUser", "%"+operationLog.getOperateUser().trim()+"%", params);
		if(StringUtils.isNotBlank(operationLog.getMenuCode()))	
			appendCondition(jpql, " operationLog.menuCode = :menuCode", 
					"menuCode", operationLog.getMenuCode().trim(), params);
		if(StringUtils.isNotBlank(operationLog.getOperationCode()))	
			appendCondition(jpql, " operationLog.operationCode = :operationCode", 
					"operationCode", operationLog.getOperationCode().trim(), params);
		if(operationLog.getOperateResult()!= 0)	
		appendCondition(jpql, " operationLog.operateResult = :operateResult", 
					"operateResult", operationLog.getOperateResult(), params);
		
		appendCondition(jpql, "operationLog.operateTime >= :beginTime", "beginTime",
				operationLog.getBeginTime(), params);
		
		appendCondition(jpql, "operationLog.operateTime <= :endTime", "endTime",
				operationLog.getEndTime(), params);
		return params;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOperationLogs(){
		GregorianCalendar gc = new GregorianCalendar();
		String interval = sysParamService.queryParamValueByParamCode(Constants.OPERATE_LOG_DELETE_DAY_COUNT);
		gc.add(GregorianCalendar.DATE, -Integer.parseInt(interval));
		String queryString = "DELETE FROM OperationLog WHERE operateTime < ?1 ";
		super.removeByParams(queryString, gc.getTime());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeOldLogs(){
		String serverLogPath = ServerPathUtil.server_log_path;
		deleteOperationLogs();	//删除数据库中的操作日志
		appDataLogService.deleteAppDataLogs();//删除数据库中的app请求日志
		Properties props = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(ServerPathUtil.server_deployments_path+"log.properties");
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//读配置文件。应用的日志的路径是log.properties文件中的log.home
		String appLogPath = props.getProperty("log.home")+"/bak/";
		GregorianCalendar gc = new GregorianCalendar();
		String interval = sysParamService.queryParamValueByParamCode(Constants.SERVER_LOG_DELETE_DAY_COUNT);
		gc.add(GregorianCalendar.DATE, -Integer.parseInt(interval)); //时间为当前时间的前interval天
		File serverLogFile = new File(serverLogPath);
		File appLogFile = new File(appLogPath);
		if(appLogFile.isDirectory()){
			FileUtil.delAllFileByLastModifyTime(appLogPath, gc.getTime());	//删除最后修改时间为interval天前的日志
		}
		if(serverLogFile.isDirectory()){
			FileUtil.delAllFileByLastModifyTime(serverLogPath, gc.getTime());	//删除最后修改时间为interval个天前的日志
		}
	}
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	public IAppDataLogService getAppDataLogService() {
		return appDataLogService;
	}
	public void setAppDataLogService(IAppDataLogService appDataLogService) {
		this.appDataLogService = appDataLogService;
	}
	
}
