package com.biencloud.smarthome.service.sysparam.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.AppConstants;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 系统参数模块领域服务实现类。
 * 
 * @author kouy
 * @since 1.0 2012-4-14
 * @see BaseService
 * @see ISysParamService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SysParamServiceImpl extends BaseService<SystemParam, String> implements ISysParamService {
	private IPushDao pushDao;
	private IDeviceDao deviceDao;

	@Override
	public Paging<SystemParam> querySystemParamsForPaging(String paramName, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM SystemParam sp");
		Object[] values = null;
		if (StringUtils.isNotBlank(paramName)) {
			values = new Object[1];
			jpql.append(" WHERE sp.paramName LIKE ?1");
			values[0] = "%" + paramName + "%";
		}

		String queryString = jpql.toString().replace(REPLACE_CHARS, "sp");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(sp.paramCode)");
		return queryForPaging(pageNum, pageSize, queryString, queryStringCount, values);
	}

	@Override
	public SystemParam get(String entityId) {
		SystemParam sp = getDao().get(entityId);
		if (sp != null) {
			sp.setUpdatedUser(getLoginService().getUserNameByLoginName(sp.getUpdatedUser()));
		}
		return sp;
	}

	@Override
	public String getFileServerUrl() {
		return get(Constants.FILE_SERVER_URL).getParamValue();
	}

	@Override
	public String getExternalFileServerUrl() {
		return get(Constants.EXTERNAL_FILE_SERVER_URL).getParamValue();
	}
	
	@Override
	public String queryParamValueByParamCode(String paramCode) {
		String paramValue = null;
		if (paramCode != null) {
			List<SystemParam> list = super.find("from SystemParam where  paramCode=?", paramCode);
			if (list != null && !list.isEmpty()) {
				SystemParam sp = list.get(0);
				if (sp != null) {
					paramValue = sp.getParamValue();
				}

			}
		}
		return paramValue;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSysParam(SystemParam systemParam) {
		if (systemParam != null && systemParam.getParamCode() != null) {
			SystemParam _systemParam = super.get(systemParam.getParamCode());
			_systemParam.setParamDesc(systemParam.getParamDesc());
			_systemParam.setParamName(systemParam.getParamName());
			_systemParam.setParamValue(systemParam.getParamValue());
			_systemParam.setUpdatedTime(new Date());
			_systemParam.setUpdatedUser(systemParam.getUpdatedUser());
			// 判断该参数是否要被推送
			boolean isBepushed = this.isPushedParam(systemParam.getParamCode());
			if (isBepushed) {
				// 获得该参数的推送类型
				String pushKind = this.queryPushKind(systemParam.getParamCode());
				if (pushKind != null) {
					// 进行推送
					this.pushParamToClient(systemParam.getParamCode(), systemParam.getParamCode(), systemParam.getParamValue(), pushKind);
				}
			}
			super.update(_systemParam);

		}

	}

	/**
	 * 
	 * 方法的描述: 判断该参数是否被推送
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-1 上午11:49:19
	 * @param paramCode
	 *            参数代码
	 * @return 被推送为true,否则为false
	 */
	private boolean isPushedParam(String paramCode) {
		boolean isBepushed = false;
		if (AppConstants.DATA_SERVER_IP.equals(paramCode) || AppConstants.FILE_SERVER_IP.equals(paramCode)
				|| AppConstants.SOCKET_SERVER_IP.equals(paramCode)) {
			isBepushed = true;
		}
		return isBepushed;
	}

	/**
	 * 
	 * 方法的描述: 获得推送类型
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-1 上午11:54:27
	 * @param paramCode
	 * @return
	 */
	private String queryPushKind(String paramCode) {
		String pushKind = null;
		if (AppConstants.DATA_SERVER_IP.equals(paramCode) || AppConstants.FILE_SERVER_IP.equals(paramCode)
				|| AppConstants.SOCKET_SERVER_IP.equals(paramCode)) {
			pushKind = PushKindConstants.PUSH_SERVER_IP_CHANGE_KIND;
		}
		return pushKind;
	}

	/**
	 * 
	 * 方法的描述: 将参数推送给终端
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-1 上午11:43:05
	 * @param pushName
	 * @param paramCode
	 * @param paramValue
	 * @param pushKind
	 */
	private void pushParamToClient(String pushName, String paramCode, String paramValue, String pushKind) {
		String pushContent = paramCode + ":" + paramValue;
		Push p = new Push(pushName, pushKind, pushContent, null, null);
		List<Device> allDevicesList = deviceDao.findAll(null);
		pushDao.pushToClients(allDevicesList, p);

	}

	private ILoginService getLoginService() {
		return (ILoginService) getBean(Constants.BEAN_NAME_LOGIN_SERVICE);
	}
	@Override
	public String getAppDownloadAbsoluteUrl() {
		return getExternalFileServerUrl()+get(Constants.APP_DOWNLOAD_RELATIVE_URL).getParamValue();
	}

	@Override
	public String getWebDownloadAbsoluteUrl() {
		return getExternalFileServerUrl()+get(Constants.WEB_DOWNLOAD_RELATIVE_URL).getParamValue();
	}
	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	
}
