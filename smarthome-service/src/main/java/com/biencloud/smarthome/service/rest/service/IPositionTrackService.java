package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.PositionTrack;

public interface IPositionTrackService extends IBaseResService<PositionTrack>{
	
	List<PositionTrack> queryList(PositionTrack track, String beginTime,String endTime);
	
	ResultEntity<String> delete(PositionTrack track);
	
	ResultEntity<String> addList(String pos);
	
}
