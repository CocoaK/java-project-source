package com.biencloud.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.model.NoticeData;
import com.biencloud.smarthome.service.info.service.IInfoSendService;


@Controller
@RequestMapping("/info")
public class NoticeController extends BaseController<InfoSend>{
		
	@Autowired
	private IInfoSendService infoSendService;
	
	@RequestMapping(value="/query", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<Paging<NoticeData>> query(String roomNo, Paging p) {
		Paging<NoticeData> paging = infoSendService.queryNoticeForPaging(roomNo, p.getPageNum(), p.getPageSize());
		return proccessResultEntity(ResultEntity.SUCCESS, ResultEntity.MESSAGE_SUCCESS, paging);
	}
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<List<NoticeData>> queryList(String roomNos) {
		List<NoticeData> list = infoSendService.queryNoticeForList(roomNos);
		return proccessResultEntity(ResultEntity.SUCCESS, ResultEntity.MESSAGE_SUCCESS, list);
	}
	
	@Override
	public IService getBaseService() {
		return infoSendService;
	}
	
}
