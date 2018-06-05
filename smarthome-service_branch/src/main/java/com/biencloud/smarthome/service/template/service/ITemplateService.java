package com.biencloud.smarthome.service.template.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.template.model.Template;

public interface ITemplateService extends IService<Template, String>{
	public void createTemplate();
}
