package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.rest.model.AccessoriesType;

public interface IAccessoriesTypeService extends IBaseResService<AccessoriesType>{
	
	ResultEntity<ResultList<List<AccessoriesType>>> getList(AccessoriesType accessoriesType);
}
