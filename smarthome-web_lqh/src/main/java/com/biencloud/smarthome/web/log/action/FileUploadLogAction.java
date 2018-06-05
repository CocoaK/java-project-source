package com.biencloud.smarthome.web.log.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IFileUploadLogService;
import com.biencloud.smarthome.web.log.vo.FileUploadLogVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 
 * 类名称：FileUploadLogAction 类描述： 文件上传日志管理类
 * 
 * @author: dehuaye
 * @version: 0.1
 * @date: 2012-5-7 上午10:55:57
 */
public class FileUploadLogAction extends BaseAction<FileUploadLogVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IFileUploadLogService fileUploadLogService;

	private FileUploadLogVO fileUploadLog;
	
	private ISysParamService sysParamService;

	/**
	 * 
	 * 方法的描述: 文件上传日志列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:01:29
	 * @return
	 */
	public String queryList() throws Exception{
		try {
			PagingVO<FileUploadLogVO> page = getPage();
			if (page == null)
				page = new PagingVO<FileUploadLogVO>();
			PagingVO<FileUploadLogVO> pagingVO = fileUploadLogService.queryFileUploadLogForPaging(fileUploadLog, page.getPageNum(),page.getPageSize());
			setPage(pagingVO);
			setRequestAttribute("fileServerUrl", sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL));
			 return "list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法的描述: 查找单个文件上传日志
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:00:57
	 * @return
	 */
	public String findById() throws Exception{
		try {
			String id=getRequest().getParameter("requestId");
			fileUploadLog=fileUploadLogService.getFileUploadLog(id);
			setRequestAttribute("fileServerUrl", sysParamService.getParamValue(Constants.EXTERNAL_FILE_SERVER_URL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewdetail";
	}
	

	public IFileUploadLogService getFileUploadLogService() {
		return fileUploadLogService;
	}

	public void setFileUploadLogService(IFileUploadLogService fileUploadLogService) {
		this.fileUploadLogService = fileUploadLogService;
	}

	public FileUploadLogVO getFileUploadLog() {
		return fileUploadLog;
	}

	public void setFileUploadLog(FileUploadLogVO fileUploadLog) {
		this.fileUploadLog = fileUploadLog;
	}


	public ISysParamService getSysParamService() {
		return sysParamService;
	}


	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	

}
