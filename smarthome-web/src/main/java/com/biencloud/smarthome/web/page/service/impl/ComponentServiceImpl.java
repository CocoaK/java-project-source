package com.biencloud.smarthome.web.page.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.appdata.json.ComponentJson;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.page.service.IComponentService;
import com.biencloud.smarthome.web.page.vo.ComponentVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.wsclient.stub.Component;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

public class ComponentServiceImpl extends BaseService<ComponentVO> implements IComponentService{
	
	private ISysParamService sysParamService;

	@Override
	public PagingVO<ComponentVO> queryComponentForPaging(ComponentVO componentVO, int pageNum,
			int pageSize) {
		Component component = new Component();
		
		if(componentVO != null){
			copyProperties(componentVO,component);
		}
		Paging paging = getSmartHomeService().queryComponentForPaging(component, pageNum, pageSize);
		PagingVO<ComponentVO> pagingVO = convertToPagingVO(paging);
		return pagingVO;
	}

	@Override
	public void saveComponent(ComponentVO componentVO) {
		Component component = new Component();
		if(componentVO != null){
			copyProperties(componentVO,component);
		}
		getSmartHomeService().saveComponent(component);
	}

	@Override
	public void deleteComponent(String id) {
		getSmartHomeService().deleteComponent(id);
	}

	@Override
	public void updateComponent(ComponentVO componentVO) {
		Component component = new Component();
		if(componentVO != null){
			copyProperties(componentVO,component);
		}
		getSmartHomeService().updateComponent(component);
	}

	@Override
	public ComponentVO getComponentById(String id) {
		ComponentVO componentVO = new ComponentVO();
		Component component = getSmartHomeService().queryComponentById(id);
		if(component != null){
			copyProperties(component,componentVO);
		}
		return componentVO;
	}
	
	@Override
	public List<ComponentVO> queryComponents(ComponentVO componentVO) {
		Component component = new Component();
		if(componentVO != null){
			copyProperties(componentVO,component);
		}
		List<Component> list= getSmartHomeService().queryComponents(component);
		List<ComponentVO> componentList = new ArrayList<ComponentVO>();
		for(Component c : list){
			ComponentVO cVO = new ComponentVO();
			copyProperties(c,cVO);
			if(StringUtils.isNotBlank(cVO.getImage())){
				cVO.setImage(sysParamService.getWebDownloadAbsoluteUrl()+cVO.getImage());
			}
			componentList.add(cVO);
		}
		return componentList;
		
	}

	@Override
	public ComponentJson queryComponentJson(ComponentVO componentVO) {
		ComponentJson componentJson = new  ComponentJson();
		List<ComponentVO> list = queryComponents(componentVO);
		componentJson.setComponentList(list);
		return componentJson;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

}
