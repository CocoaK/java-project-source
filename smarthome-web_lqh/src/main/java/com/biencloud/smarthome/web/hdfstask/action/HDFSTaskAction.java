package com.biencloud.smarthome.web.hdfstask.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfstask.service.IHDFSTaskService;
import com.biencloud.smarthome.web.hdfstask.vo.HdfsTaskVO;
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
public class HDFSTaskAction extends BaseAction<HdfsTaskVO> {
	private IHDFSTaskService hdfsTaskService;
	private ISysParamService sysParamService;
	private HdfsTaskVO hdfsTaskVO;
	private String webFileDownloadUrl;
	private String appFileDownloadUrl;

	/**
	 * 
	 * 方法的描述:分页处理
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 下午4:39:25
	 * @return
	 */
	public String list() throws Exception {
		PagingVO<HdfsTaskVO> page = getPage();

		if (page == null)
			page = new PagingVO<HdfsTaskVO>();
        //查询
		PagingVO<HdfsTaskVO> pagingVO = hdfsTaskService.queryHDFSTaskForPaging(page.getPageNum(), page.getPageSize(), hdfsTaskVO);

		setPage(pagingVO);
		//下载地址
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
	 * @date: 2012-6-11 下午5:41:24
	 * @return
	 */
	public String query() throws Exception{
		PagingVO<HdfsTaskVO> page = getPage();
		if (page == null)
			page = new PagingVO<HdfsTaskVO>();
		page.setPageNum(1);
		//查询
		PagingVO<HdfsTaskVO> pagingVO = hdfsTaskService.query(page.getPageNum(), page.getPageSize(), hdfsTaskVO);
		setPage(pagingVO);
		//下载地址
		this.webFileDownloadUrl = sysParamService.getWebDownloadAbsoluteUrl();
		this.appFileDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 详细查看
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-8-9 下午3:12:25
	 * @return
	 */
	public String detail() throws Exception{
		if (hdfsTaskVO != null) {
			hdfsTaskVO = hdfsTaskService.getHdfsTaskById(hdfsTaskVO.getId());
		}
		return "detail";
	}

	public IHDFSTaskService getHdfsTaskService() {
		return hdfsTaskService;
	}

	public void setHdfsTaskService(IHDFSTaskService hdfsTaskService) {
		this.hdfsTaskService = hdfsTaskService;
	}

	public HdfsTaskVO getHdfsTaskVO() {
		return hdfsTaskVO;
	}

	public void setHdfsTaskVO(HdfsTaskVO hdfsTaskVO) {
		this.hdfsTaskVO = hdfsTaskVO;
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

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

}
