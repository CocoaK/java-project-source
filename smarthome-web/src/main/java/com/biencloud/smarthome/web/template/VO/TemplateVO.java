package com.biencloud.smarthome.web.template.VO;

import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * @author Cocoa
 */
public class TemplateVO extends BaseVO{
	private static final long serialVersionUID = -682427163703331919L;
	private List<ModuleVO> template;	//模板
	private String sourceUrl;	//模板资源文件路径
	private String name;
	public List<ModuleVO> getTemplate() {
		return template;
	}
	public void setTemplate(List<ModuleVO> template) {
		this.template = template;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
