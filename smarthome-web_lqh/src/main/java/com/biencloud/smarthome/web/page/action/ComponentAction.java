package com.biencloud.smarthome.web.page.action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseUploadAction;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.page.service.IComponentService;
import com.biencloud.smarthome.web.page.service.IPageService;
import com.biencloud.smarthome.web.page.vo.ComponentVO;
import com.biencloud.smarthome.web.page.vo.PageVO;

/**
 * 
 * 类名称：DeviceCallAction 
 * 类描述：页面组件动作类
 * @author: ykou  
 * @version: 0.1
 */
public class ComponentAction extends BaseUploadAction<ComponentVO>{
		
	private static final long serialVersionUID = -1095744104756427075L;
	//页面组件service
	private IComponentService componentService;
	
	private IPageService pageService;
	
	private ComponentVO componentVO;
	//成功标志
	private String successFlag;
	
	private String id;
	
	private List<PageVO> pages;
	
	private String fileName;
	
	private List<ComponentVO> componentList;
	
	private File file;
	
	/**
	 * 
	 * 方法的描述: 页面列表
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception{
		PagingVO<ComponentVO> page = getPage();
		if(page == null)
			page = new PagingVO<ComponentVO>();
		PagingVO<ComponentVO> pageList = componentService.queryComponentForPaging(componentVO, page.getPageNum(), page.getPageSize());
		super.setPage(pageList);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 输入页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception{
		pages = pageService.queryPage(null);
		setPages(pages);
		//componentList = componentService.queryComponentForPaging(component, pageNum, pageSize);
		return SUCCESS;
	}
	
	public String actionAddInput() throws Exception{
		pages = pageService.queryPage(null);
		setPages(pages);
		//componentList = componentService.queryComponentForPaging(component, pageNum, pageSize);
		return SUCCESS;
	}
	
	public String linkAddInput() throws Exception{
		pages = pageService.queryPage(null);
		setPages(pages);
		//componentList = componentService.queryComponentForPaging(component, pageNum, pageSize);
		return SUCCESS;
	}
	
	public String textAddInput() throws Exception{
		pages = pageService.queryPage(null);
		setPages(pages);
		//componentList = componentService.queryComponentForPaging(component, pageNum, pageSize);
		return SUCCESS;
	}
	public String appAddInput() throws Exception{
		pages = pageService.queryPage(null);
		setPages(pages);
		//componentList = componentService.queryComponentForPaging(component, pageNum, pageSize);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 新增页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		//如果名称重复
//		PagingVO<ComponentVO> page = new PagingVO<ComponentVO>();
//		if(componentVO!=null){
//			PagingVO<ComponentVO> pageList =  componentService.queryComponentForPaging(componentVO, page.getPageNum(), page.getPageSize());
//			List<ComponentVO> list = pageList.getResults();
//			if(list!=null && list.size()>0){
//				successFlag = "repeat_name";
//				return SUCCESS;
//			}
//		}
		String imageUrl= null;
		String extName = "jpg";
		if(StringUtils.isNotBlank(fileName)){
			extName = FileUploadUtil.getExtName(FileUploadUtil.getFileName(fileName));
			String newfileName = FileUploadUtil.getPreFileName(FileUploadUtil.getFileName(fileName))+"-"+System.currentTimeMillis()+"."+extName;
			super.uploadFile(newfileName, file);	//上传文件
			imageUrl = newfileName;
		}
		if(componentVO!=null){
			componentVO.setImage(imageUrl);
		}
		componentService.saveComponent(componentVO);
		successFlag = "success";
		setSuccessFlag(successFlag);
		componentVO = null;
		return queryList();
	}
	
	/**
	 * 
	 * 方法的描述: 删除页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		componentService.deleteComponent(id);
		return queryList();
	}
	
	
	public String updateInput() throws Exception{
		componentVO = componentService.getComponentById(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 修改页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		ComponentVO cp = componentService.getComponentById(id);
		if(componentVO!=null){
			cp.setName(componentVO.getName());
			cp.setBelowOfId(componentVO.getBelowOfId());
			cp.setGroups(componentVO.getGroups());
			cp.setPageId(componentVO.getPageId());
			cp.setRightOfId(componentVO.getRightOfId());
			cp.setType(componentVO.getType());
			cp.setUrl(componentVO.getUrl());
		}
		
		componentService.updateComponent(cp);
		successFlag = "success";
		return SUCCESS;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public IComponentService getComponentService() {
		return componentService;
	}

	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

	public ComponentVO getComponentVO() {
		return componentVO;
	}

	public void setComponentVO(ComponentVO componentVO) {
		this.componentVO = componentVO;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<ComponentVO> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<ComponentVO> componentList) {
		this.componentList = componentList;
	}

	public List<PageVO> getPages() {
		return pages;
	}

	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

}
