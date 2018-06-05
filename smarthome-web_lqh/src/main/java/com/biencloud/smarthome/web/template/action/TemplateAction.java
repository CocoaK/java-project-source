package com.biencloud.smarthome.web.template.action;

import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.CacheUtil;
import com.biencloud.smarthome.web.common.util.XmlUtil;
import com.biencloud.smarthome.web.template.VO.ModuleVO;
import com.biencloud.smarthome.web.template.VO.TemplateVO;

@SuppressWarnings("serial")
public class TemplateAction extends BaseAction<TemplateVO>{
	private TemplateVO template;

	public String addTemplate() throws Exception{
		logger.error("addTemplate:{}");
		List<ModuleVO> list = CacheUtil.getInstance().getList();
		if(list != null && list.size() > 0){
			template = new TemplateVO();
			template.setTemplate(list);
			template.setName("test");
			template.setSourceUrl("source 1");
			//XmlUtil.creatXml(template);
		}
		XmlUtil.crtXml();
		return "add";
	}
	public TemplateVO getTemplate() {
		return template;
	}

	public void setTemplate(TemplateVO template) {
		this.template = template;
	}
}

