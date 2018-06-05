package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntry;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntryJson;

public interface IRoomMobileEntryService extends IBaseResService<RoomMobileEntry>{
	
	ResultEntity<List<RoomMobileEntry>> queryList(RoomMobileEntry roomMobileEntry);
	
	ResultEntity<List<RoomMobileEntryJson>> addList(String entry);

	//ResultEntity<List<RoomMobileEntry>> getDistinctList(String jsonString);
	
}
