package com.biencloud.smarthome.web.page.service;

import java.util.List;

import com.biencloud.smarthome.web.appdata.json.ComponentJson;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.page.vo.ComponentVO;

public interface IComponentService {

	PagingVO<ComponentVO> queryComponentForPaging(ComponentVO component,int pageNum,int pageSize);
	
	void saveComponent(ComponentVO componentVO);
	
	void deleteComponent(String id);
	
	void updateComponent(ComponentVO componentVO);

	ComponentVO getComponentById(String id);

	ComponentJson queryComponentJson(ComponentVO componentVO);

	List<ComponentVO> queryComponents(ComponentVO componentVO);
}
