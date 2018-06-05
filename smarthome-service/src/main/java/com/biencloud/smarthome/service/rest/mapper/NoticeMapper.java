package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.Notice;
import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice>{
    int delete(Long id);

    int insert(Notice record);

    List<Notice> getForList();

    Notice getForOne(Long id);

    int updateOnActive(Notice record);

    int updateByIdWithBLOBs(Notice record);

    int update(Notice record);
}