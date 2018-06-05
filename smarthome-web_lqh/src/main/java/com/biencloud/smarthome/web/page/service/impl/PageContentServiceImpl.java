package com.biencloud.smarthome.web.page.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.page.service.IPageContentService;
import com.biencloud.smarthome.web.page.vo.PageContentVO;
import com.biencloud.smarthome.web.wsclient.stub.PageContent;

public class PageContentServiceImpl extends BaseService<PageContentVO> implements IPageContentService{
	private static final String CREATED_TIME = "createdTime";

	@Override
	public void savePageContent(PageContentVO pageContentVO) {
		PageContent pageContent = new PageContent();
		if(pageContentVO!=null){
			copyProperties(pageContentVO,pageContent,false,CREATED_TIME);
		}
		getSmartHomeService().savePageContent(pageContent);
	}

	@Override
	public void deletePageContent(String updateId) {
		getSmartHomeService().deletePageContentById(updateId);		
	}

	@Override
	public List<PageContentVO> queryPageContent(PageContentVO pageContentVO) {
		PageContent pageContent = new PageContent();
		if(pageContentVO!=null){
			copyProperties(pageContentVO,pageContent,false,CREATED_TIME);
		}
		List<PageContent> list = getSmartHomeService().queryPageContent(pageContent);
		List<PageContentVO> contentVOs = new ArrayList<PageContentVO>();
		if(list!=null && list.size()>0){
			for(PageContent p : list){
				PageContentVO pVO = new PageContentVO();
				copyProperties(p,pageContentVO,true,CREATED_TIME);
				contentVOs.add(pVO);
			}
		}
		return contentVOs;
	}
	
	
}
