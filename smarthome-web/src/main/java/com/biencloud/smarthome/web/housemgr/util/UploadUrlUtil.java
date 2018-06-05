package com.biencloud.smarthome.web.housemgr.util;

import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 获取上传文件URL接口的工具类
 * 
 * @author jsun  
 * @since 1.0 2012-5-28
 */
public class UploadUrlUtil {
	//Web文件上传相对地址
	private static final String END_POINT = "/upload/fileAction_webUpload.action";

	private ISysParamService sysParamService;

	/**
	 * 获取上传文件服务的URL接口
	 * 
	 * @return 例如: http://127.0.0.1:8080/fileserver/upload/fileAction_webUpload.action
	 */
	public String getUploadUrl() {
		return sysParamService.getFileServerUrl() + END_POINT;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
}
