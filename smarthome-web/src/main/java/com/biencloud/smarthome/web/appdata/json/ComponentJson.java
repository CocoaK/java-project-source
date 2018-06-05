package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.page.vo.ComponentVO;

/**
 * 
 * 类名称：PageJson 
 * 类描述： 终端页面组件Json
 * @author: ykou  
 * @version: 0.1
 */
@SuppressWarnings("serial")
public class ComponentJson extends Json{
	
	private List<ComponentVO> componentList;

	public List<ComponentVO> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<ComponentVO> componentList) {
		this.componentList = componentList;
	}

}
