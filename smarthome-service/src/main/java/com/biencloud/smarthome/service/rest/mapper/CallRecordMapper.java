package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.CallRecord;
import java.util.List;

public interface CallRecordMapper extends BaseMapper<CallRecord>{
    int delete(Long id);

    int insert(CallRecord record);

    List<CallRecord> getForList();

    CallRecord getForOne(Long id);

    int updateOnActive(CallRecord record);

    int update(CallRecord record);
}