package com.biencloud.smarthome.web.housemgr.action;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.biencloud.smarthome.web.base.action.BaseUploadAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 房产管理模块基础Action
 * 
 * @author jsun
 * @since 1.0 2012-6-15
 */
@SuppressWarnings("serial")
public class HouseMgrBaseAction<T> extends BaseUploadAction<T> {
	private static final BigDecimal ZERO = BigDecimal.valueOf(0L);
	
	private ISysParamService sysParamService;
	//文件服务器URL
	private String fileServerUrl;

	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
		// 系统参数服务IoC进来就马上初始化文件服务器URL
		try {
			fileServerUrl = sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL);
		} catch (Exception e) {
			logger.error("**************获取系统参数{}的值发生异常**************", Constants.EXTERNAL_FILE_SERVER_URL);
		}
	}
	
	public String getFileServerUrl() {
		return fileServerUrl;
	}
	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}
	
	
	/**
	 * 设置图片的扩展名到请求范围里。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	protected void setPicExt() throws Exception {
		setRequestAttribute("picExt", getSysParamService().getParamValue(
				Constants.PARAM_CODE_WEB_IMAGE_FORMAT));
	}
	
	/**
	 * 判断值是否等于0。
	 * @return
	 */
	protected boolean equalZero(String value){
		if(StringUtils.isEmpty(value))
			return false;
		
		if(!NumberUtils.isNumber(value))
			return false;
		
		return (ZERO.compareTo(new BigDecimal(value)) == 0);
	}
}
