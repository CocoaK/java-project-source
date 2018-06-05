package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.rest.mapper.AccessoriesTypeMapper;
import com.biencloud.smarthome.service.rest.model.AccessoriesType;
import com.biencloud.smarthome.service.rest.service.IAccessoriesTypeService;

@Service
public class AccessoriesTypeService extends BaseResService<AccessoriesType> implements
		IAccessoriesTypeService {

	@Autowired
	private AccessoriesTypeMapper accessoriesTypeMapper;

	@Override
	public BaseMapper<AccessoriesType> getBaseMapper() {
		return accessoriesTypeMapper;
	}

	@Override
	public ResultEntity<ResultList<List<AccessoriesType>>> getList(
			AccessoriesType accessoriesType) {
		List<AccessoriesType> list = accessoriesTypeMapper.getForList(accessoriesType);
		return super.proccessResultList(list!=null?list.size():0, System.currentTimeMillis(), list);
	}


	

}  
