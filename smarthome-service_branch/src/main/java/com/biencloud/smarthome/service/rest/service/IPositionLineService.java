package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.PositionLine;

public interface IPositionLineService extends IBaseResService<PositionLine>{
	
	List<PositionLine> queryList(PositionLine line);
	
	ResultEntity<String> deleteBySipUid(String sipUid);
	
	ResultEntity<String> addList(String pos);
	
}
