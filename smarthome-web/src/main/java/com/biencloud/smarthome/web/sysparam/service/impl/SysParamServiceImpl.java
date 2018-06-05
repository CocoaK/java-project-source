package com.biencloud.smarthome.web.sysparam.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.sysparam.vo.SystemParamVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.SystemParam;

/**
 * 
 * 系统参数管理服务实现。
 * @author kouy
 * @since 1.0 2012-5-2
 * @see ISysParamService
 */
public class SysParamServiceImpl extends BaseService<SystemParamVO> implements
		ISysParamService {

	private static final String UPDATED_TIME = "updatedTime";
	
	@Override
	public PagingVO<SystemParamVO> querySystemParams(String paramName, int pageNum,
			int pageSize) throws Exception {
		Paging paging = getSmartHomeService().querySystemParamsForPaging(
				paramName, pageNum, pageSize);		
		PagingVO<SystemParamVO> pagingVO = convertToPagingVO(paging, UPDATED_TIME);		
		return pagingVO;
	}

	@Override
	public SystemParamVO getSystemParamDetail(String paramCode)
			throws Exception {
		SystemParamVO systemParamVO = new SystemParamVO();
		copyProperties(getSmartHomeService().getSystemParamDetail(paramCode), 
				systemParamVO, true, UPDATED_TIME);
		return systemParamVO;
	}

	@Override
	public void updateSystemParam(SystemParamVO systemParamVO) throws Exception {
		SystemParam systemParam = new SystemParam();		
		copyProperties(systemParamVO, systemParam, false, UPDATED_TIME);
		getSmartHomeService().updateSystemParam(systemParam);
	}

	@Override
	public String getFileServerUrl() {
		return getSmartHomeService().getFileServerUrl();
	}

	@Override
	public String getParamValue(String paramCode) throws Exception {
		SystemParamVO spv=this.getSystemParamDetail(paramCode);
		if(spv != null)
			return  spv.getParamValue();
		
		return null;
	}

	@Override
	public String getAppDownloadAbsoluteUrl() {
		// TODO Auto-generated method stub
		return getSmartHomeService().getAppDownloadAbsoluteUrl();
	}

	@Override
	public String getWebDownloadAbsoluteUrl() {
		// TODO Auto-generated method stub
		return getSmartHomeService().getWebDownloadAbsoluteUrl();
	}
}
