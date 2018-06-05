package com.biencloud.smarthome.web.page.service;

import java.util.List;

import com.biencloud.smarthome.web.appdata.json.PageComponentJson;
import com.biencloud.smarthome.web.appdata.json.PageJson;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.page.vo.PageVO;

public interface IPageService {

	PagingVO<PageVO> queryPageForPaging(PageVO page,int pageNum,int pageSize);
	
	void savePage(PageVO page);
	
	void deletePage(String id);
	
	void updatePage(PageVO page);

	PageVO getPageById(String id);
	
	List<PageVO> queryPage(PageVO page);
	
	PageJson queryPageJson(PageVO page);

	PageComponentJson queryPageComponentJson(PageVO pageVO);

	List<PageComponentJson> queryAllPageComponentJson();
}
