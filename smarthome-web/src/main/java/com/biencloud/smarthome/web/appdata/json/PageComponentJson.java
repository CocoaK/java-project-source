package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.page.vo.ComponentVO;
import com.biencloud.smarthome.web.page.vo.PageVO;

/**
 * 
 * 类名称：PageJson 
 * 类描述： 终端页面组件Json
 * @author: ykou  
 * @version: 0.1
 */
@SuppressWarnings("serial")
public class PageComponentJson extends Json{
	
	private PageVO page;
	private List<ComponentVO> componentList;
	public PageVO getPage() {
		return page;
	}
	public void setPage(PageVO page) {
		this.page = page;
	}
	public List<ComponentVO> getComponentList() {
		return componentList;
	}
	public void setComponentList(List<ComponentVO> componentList) {
		this.componentList = componentList;
	}
	
	

}
