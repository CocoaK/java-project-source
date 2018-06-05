package com.biencloud.smarthome.web.base.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 支持上传文件到文件服务器的动作基类。
 * @author kouy
 * @since 1.0 2012-10-12
 */
@SuppressWarnings("serial")
public class BaseUploadAction<T> extends BaseAction<T> {

	private static final Map<String, String> UPLOAD_ERR_MSG_MAPPINGS;
	static{
		UPLOAD_ERR_MSG_MAPPINGS = new HashMap<String, String>();
		UPLOAD_ERR_MSG_MAPPINGS.put(Constants.FILE_UPLOAD_NAME_REPEATED, "error.file.name.repeat");
		UPLOAD_ERR_MSG_MAPPINGS.put(Constants.FILE_UPLOAD_FAILURE, "error.uploadfile.failure");
		UPLOAD_ERR_MSG_MAPPINGS.put(Constants.FILE_UPLOAD_FORMAT_ERROR, "error.uploadfile.format");
	}
	
	
	/**
	 * 上传文件并返回上传结果对象。
	 * @param fileName 待上传的文件名
	 * @param file 待上传的文件
	 * @return
	 * @throws Exception 上传发生异常时
	 */
	protected UploadResult uploadFile(String fileName, File file) throws Exception {
		UploadResult result = null;
		if(file == null){
			result = new UploadResult();
			result.setRetFlag(Constants.FILE_UPLOAD_FAILURE);
			result.setErrorMsg(getText(UPLOAD_ERR_MSG_MAPPINGS.get(result.getRetFlag())));
			return result;
		}
		
		String contextPath = getSysParamService().getFileServerUrl();
		result = FileUploadUtil.uploadByService(contextPath + Constants.UPLOAD_FILE_RELATIVE_PATH, 
				"uploadFile", file, fileName, getIp());
		
		if(Constants.FILE_UPLOAD_SUCCESS.equals(result.getRetFlag())){
			result.setDownloadRelativePath(getWebDownloadRelUrl() + fileName);
		}else{
			if(UPLOAD_ERR_MSG_MAPPINGS.containsKey(result.getRetFlag()))
				result.setErrorMsg(getText(UPLOAD_ERR_MSG_MAPPINGS.get(result.getRetFlag())));
		}
		
		logger.info("********************上传文件{}的结果：{}", new Object[]{fileName, result});
		
		return result;
	}
	
	/**
	 * 提取文件名。
	 */
	protected String extractFileName(String fileName){
		if(StringUtils.isEmpty(fileName))
			return fileName;
		int beginIndex = fileName.lastIndexOf("\\");
		if(beginIndex < 0)
			return fileName;
		return fileName.substring(beginIndex + 1);
	}
	
	/**
	 * 获取文件大小。
	 */
	protected String getFileSize(File file){
		if(file == null)
			return "";
		
		return String.valueOf(file.length());
	}
	
	/**
	 * 获取Web下载的相对路径。
	 */
	protected String getWebDownloadRelUrl() throws Exception {
		return getSysParamService().getParamValue(Constants.PARAM_CODE_WEB_DOWNLOAD_REL_URL);
	}
	
	/**
	 * 获取Web下载的绝对路径。
	 */
	protected String getWebDownloadAbsoluteUrl() throws Exception {
		return getSysParamService().getWebDownloadAbsoluteUrl();
	}
	
	private ISysParamService sysParamService;
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
}
