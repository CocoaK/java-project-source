package com.biencloud.smarthome.service.rest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.rest.mapper.NoticeMapper;
import com.biencloud.smarthome.service.rest.model.Notice;
import com.biencloud.smarthome.service.rest.service.INoticeInfoService;


@Service
public class NoticeInfoService extends BaseResService<Notice> implements
		INoticeInfoService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private ICellHouseholdInfoService cellHouseholdInfoService;

	@Override
	public BaseMapper<Notice> getBaseMapper() {
		return noticeMapper;
	}

	@Override
	public ResultEntity<String> addForResultEntity(Notice notice){
		if(notice==null){
			return null;
		}
		if(notice.getRoomNo()!=null){
			String roomNo = notice.getRoomNo();
			String houseIdStr = cellHouseholdInfoService.getHouseIdByFullHouseNo(roomNo);
			if(houseIdStr==null){
				return new ResultEntity(ResultEntity.FAILD,"house not exist","");
			}
			Integer houseId = Integer.parseInt(houseIdStr);
			notice.setHouseId(houseId);
			notice.setCreateTime(new Date());
		}
		return super.addForResultEntity(notice);
	}
	
	@Override
	public ResultEntity<ResultList<List<Notice>>> getForResultListByEntity(
			Page<Notice> p, Notice notice) {
		String roomNo = notice.getRoomNo();
		String houseIdStr = cellHouseholdInfoService.getHouseIdByFullHouseNo(roomNo);
		if(houseIdStr==null){
			return new ResultEntity(ResultEntity.FAILD,"house not exist","");
		}
		Integer houseId = Integer.parseInt(houseIdStr);
		notice.setHouseId(houseId);
		noticeMapper.getForPage(p, notice);
		return proccessResultList(p.getTotal(), System.currentTimeMillis(),
				p.getResult());
	}
	
	@Override
	public ResultEntity<String> deleteNotices(String ids) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(ids==null || "".equals(ids)){
			return re;
		}
		String[] noticeIdArrays = ids.split(",");
		if(noticeIdArrays!=null && noticeIdArrays.length>0){
			for(String id : noticeIdArrays){
				Integer alarmId = Integer.parseInt(id);
				noticeMapper.delete(alarmId);
			}
		}
		re.setCode(ResultEntity.SUCCESS);
		return re;
	}
}  
