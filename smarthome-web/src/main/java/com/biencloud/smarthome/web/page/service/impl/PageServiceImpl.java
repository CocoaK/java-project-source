package com.biencloud.smarthome.web.page.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.appdata.json.PageComponentJson;
import com.biencloud.smarthome.web.appdata.json.PageJson;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.page.service.IComponentService;
import com.biencloud.smarthome.web.page.service.IPageService;
import com.biencloud.smarthome.web.page.vo.ComponentVO;
import com.biencloud.smarthome.web.page.vo.PageVO;
import com.biencloud.smarthome.web.wsclient.stub.Page;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

public class PageServiceImpl extends BaseService<PageVO> implements IPageService{
	
	private IComponentService componentService;

	@Override
	public PagingVO<PageVO> queryPageForPaging(PageVO pageVO, int pageNum,
			int pageSize) {
		Page page = new Page();
		
		if(pageVO != null){
			copyProperties(pageVO,page);
		}
		Paging paging = getSmartHomeService().queryPageForPaging(page, pageNum, pageSize);
		PagingVO<PageVO> pagingVO = convertToPagingVO(paging);
		return pagingVO;
	}

	@Override
	public void savePage(PageVO pageVO) {
		Page page = new Page();
		if(pageVO!=null){
			copyProperties(pageVO,page);
			List<String> districts = pageVO.getDistricts();
			if(districts!=null){
				for(String district : districts){
					page.getDistricts().add(district);
				}
			}
		}
		getSmartHomeService().savePage(page);
	}

	@Override
	public void deletePage(String id) {
		Page page = new Page();
		page.setId(id);
		getSmartHomeService().deletePage(page);
		
	}

	@Override
	public void updatePage(PageVO pageVO) {
		Page page = new Page();
		if(pageVO!=null){
			copyProperties(pageVO,page);
		}
		getSmartHomeService().updatePage(page);
	}

	@Override
	public PageVO getPageById(String id) {
		Page p = getSmartHomeService().queryPageById(id);
		PageVO pageVO = new PageVO();
		if(p!=null){
			copyProperties(p,pageVO);
		}
		return pageVO;
	}

	@Override
	public List<PageVO> queryPage(PageVO page) {
		Page p = new Page();
		if(page!=null){
			copyProperties(page,p);
		}
		List<Page> pages = getSmartHomeService().queryPages(p);
		List<PageVO> pageVOs = new ArrayList<PageVO>();
		if(pages!=null && pages.size()>0){
			for(Page pg : pages){
				PageVO pVO = new PageVO();
				copyProperties(pg,pVO);
				pageVOs.add(pVO);
			}
		}
		return pageVOs;
	}

	@Override
	public PageJson queryPageJson(PageVO page) {
		PageJson pageJson = new PageJson();
		List<PageVO> list = queryPage(page);
		pageJson.setPageList(list);
		if(page!=null){
			pageJson.setUpdateId(page.getUpdateId());
		}
		if(list!=null && list.size()>0){
			if(pageJson.getUpdateId()==null){
				pageJson.setUpdateId(list.get(0).getUpdateId());
			}
		}
		return pageJson;
	}

	@Override
	public PageComponentJson queryPageComponentJson(PageVO pageVO) {
		PageComponentJson pageComponentJson = new PageComponentJson();
		if(pageVO==null){
			return pageComponentJson;
		}
		PageVO page = getPageById(pageVO.getId());
		ComponentVO componentVO = new ComponentVO();
		componentVO.setPageId(page.getId());
		List<ComponentVO> componentList = componentService.queryComponents(componentVO);
		pageComponentJson.setComponentList(componentList);
		pageComponentJson.setPage(page);
		return pageComponentJson;
	}
	
	@Override
	public List<PageComponentJson> queryAllPageComponentJson() {
		PageVO pageVO= new PageVO();
		List<PageComponentJson> pageComponentList = new ArrayList<PageComponentJson>();
		List<PageVO> pageList = queryPage(pageVO);
		if(pageList!=null && pageList.size()>0){
			for(PageVO page : pageList){
				PageComponentJson pageComponentJson = new PageComponentJson();
				ComponentVO componentVO = new ComponentVO();
				componentVO.setPageId(page.getId());
				List<ComponentVO> componentList = componentService.queryComponents(componentVO);
				pageComponentJson.setComponentList(componentList);
				pageComponentJson.setPage(page);
				pageComponentList.add(pageComponentJson);
			}
		}
		return pageComponentList;
	}

	public IComponentService getComponentService() {
		return componentService;
	}

	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

}
