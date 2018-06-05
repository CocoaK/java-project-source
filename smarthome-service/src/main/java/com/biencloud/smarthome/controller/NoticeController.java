package com.biencloud.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.model.Notice;
import com.biencloud.smarthome.service.rest.service.INoticeInfoService;


@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseResController<Notice>{
		
	@Autowired
	private INoticeInfoService noticeInfoService;

	@Override
	public ResultEntity<String> add(Notice notice) {
		return noticeInfoService.addForResultEntity(notice);
	}
	
	@Override
	public @ResponseBody ResultEntity<ResultList<List<Notice>>> list(Page<Notice> p,Notice notice) {
		return noticeInfoService.getForResultListByEntity(p, notice);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> del(String ids) {
		return noticeInfoService.deleteNotices(ids);
	}

@Override
public IBaseResService<Notice> getBaseResService() {
	return noticeInfoService;
}
	
}
