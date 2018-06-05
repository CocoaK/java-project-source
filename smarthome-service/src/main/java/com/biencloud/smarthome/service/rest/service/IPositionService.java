package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.Position;

public interface IPositionService extends IBaseResService<Position>{
	
	List<Position> queryList(Position position);
	
	ResultEntity<String> delete(Position position);
	
	ResultEntity<String> addList(String pos);
	
}
