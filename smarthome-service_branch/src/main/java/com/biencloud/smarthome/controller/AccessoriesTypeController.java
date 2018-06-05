package com.biencloud.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.rest.model.AccessoriesType;
import com.biencloud.smarthome.service.rest.service.IAccessoriesTypeService;

@Controller
@RequestMapping("/accessories/type")
public class AccessoriesTypeController extends BaseResController<AccessoriesType>{
		
	@Autowired
	private IAccessoriesTypeService accessoriesTypeService;
	
	@Override
	public IBaseResService<AccessoriesType> getBaseResService() {
		return accessoriesTypeService;
	}
	
	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<AccessoriesType>>> queryList(AccessoriesType accessoriesType) {
		return accessoriesTypeService.getList(accessoriesType);
	}


}
