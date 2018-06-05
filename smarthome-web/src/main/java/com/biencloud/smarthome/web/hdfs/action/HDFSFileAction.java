package com.biencloud.smarthome.web.hdfs.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfs.service.IHDFSFileService;
import com.biencloud.smarthome.web.hdfs.vo.LocalHdfsVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;

/**
 * 
 * 类名称：PushAction 类描述：推送Action类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午4:37:20
 */
@SuppressWarnings("serial")
public class HDFSFileAction extends BaseAction<LocalHdfsVO> {
	private IHDFSFileService hdfsFileService;
	private ISysParamService sysParamService;
	private LocalHdfsVO localHdfsVO;
	private String webFileDownloadUrl;
	private String appFileDownloadUrl;

	/**
	 * 
	 * 方法的描述: 分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-26 上午10:34:07
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		PagingVO<LocalHdfsVO> page = getPage();
		if (page == null)
			page = new PagingVO<LocalHdfsVO>();
        //获得分页数据
		PagingVO<LocalHdfsVO> pagingVO = hdfsFileService.queryHDFSFileForPaging(page.getPageNum(), page.getPageSize(), localHdfsVO);
        
		setPage(pagingVO);
		
        //获得文件下载地址
		this.webFileDownloadUrl = sysParamService.getWebDownloadAbsoluteUrl();
		this.appFileDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();

		return "list";
	}

	/**
	 * 
	 * 方法的描述: 查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-11 下午5:35:29
	 * @return
	 */
	public String query() throws Exception{
		PagingVO<LocalHdfsVO> page = getPage();
		if (page == null)
			page = new PagingVO<LocalHdfsVO>();
		//默认第1页
		page.setPageNum(1);
		PagingVO<LocalHdfsVO> pagingVO = hdfsFileService.query(page.getPageNum(), page.getPageSize(), localHdfsVO);
		setPage(pagingVO);
		//获得文件下载地址
		this.webFileDownloadUrl = sysParamService.getWebDownloadAbsoluteUrl();
		this.appFileDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 详细
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-9 上午11:41:36
	 * @return
	 */
	public String detail() throws Exception{
		if (localHdfsVO != null) {
			localHdfsVO = hdfsFileService.getLocalHdfsById(localHdfsVO.getId());
		}
		return "detail";
	}

	public IHDFSFileService getHdfsFileService() {
		return hdfsFileService;
	}

	public void setHdfsFileService(IHDFSFileService hdfsFileService) {
		this.hdfsFileService = hdfsFileService;
	}

	public LocalHdfsVO getLocalHdfsVO() {
		return localHdfsVO;
	}

	public void setLocalHdfsVO(LocalHdfsVO localHdfsVO) {
		this.localHdfsVO = localHdfsVO;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public String getWebFileDownloadUrl() {
		return webFileDownloadUrl;
	}

	public void setWebFileDownloadUrl(String webFileDownloadUrl) {
		this.webFileDownloadUrl = webFileDownloadUrl;
	}

	public String getAppFileDownloadUrl() {
		return appFileDownloadUrl;
	}

	public void setAppFileDownloadUrl(String appFileDownloadUrl) {
		this.appFileDownloadUrl = appFileDownloadUrl;
	}

}
