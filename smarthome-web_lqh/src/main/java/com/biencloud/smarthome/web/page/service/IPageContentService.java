package com.biencloud.smarthome.web.page.service;

import java.util.List;
import com.biencloud.smarthome.web.page.vo.PageContentVO;

public interface IPageContentService {

	void savePageContent(PageContentVO pageContentVO);
	
	void deletePageContent(String updateId);
	
	List<PageContentVO> queryPageContent(PageContentVO pageContentVO);
	
	//PageContentJson queryPageJson(PageVO page);

}
