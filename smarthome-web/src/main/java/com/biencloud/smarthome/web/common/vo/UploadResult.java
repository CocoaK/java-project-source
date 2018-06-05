package com.biencloud.smarthome.web.common.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 文件上传返回的结果对象。
 * @author kouy
 * @since 1.0 2012-10-12
 */
@SuppressWarnings("serial")
public class UploadResult extends BaseVO {

	private boolean isSuccess;//是否成功
	
	/*
	 * 返回结果标志，参见：
	 * Constants.FILE_UPLOAD_SUCCESS
	 * Constants.FILE_UPLOAD_NAME_REPEATED
	 * Constants.FILE_UPLOAD_FAILURE
	 * Constants.FILE_UPLOAD_FORMAT_ERROR
	 */
	private String retFlag;
	
	private String errorMsg;//错误信息
	
	private String fileName;//文件名称
	private String fileFormat;//文件格式
	private String fileSize;//文件大小
	private String rootPath;//文件根路径
	private String fileRelativePath;//文件相对路径
	private String fileAbsolutePath;//文件绝对路径
	
	private String downloadRelativePath;//文件下载相对路径
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getRetFlag() {
		return retFlag;
	}
	public void setRetFlag(String retFlag) {
		this.retFlag = retFlag;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public String getFileRelativePath() {
		return fileRelativePath;
	}
	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}
	
	public String getFileAbsolutePath() {
		return fileAbsolutePath;
	}
	public void setFileAbsolutePath(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}
	
	public String getDownloadRelativePath() {
		return downloadRelativePath;
	}
	public void setDownloadRelativePath(String downloadRelativePath) {
		this.downloadRelativePath = downloadRelativePath;
	}
}
