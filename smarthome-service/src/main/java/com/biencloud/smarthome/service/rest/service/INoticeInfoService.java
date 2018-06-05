package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.model.Notice;

/**
 * 通知和信息服务
 * @author Cocoa
 *
 */
public interface INoticeInfoService extends IBaseResService<Notice>{

	/**
	 * 查询分页结果集
	 * @param p
	 * @param notice
	 * @return
	 */
	ResultEntity<ResultList<List<Notice>>> getForResultListByEntity(Page<Notice> p,Notice notice);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	ResultEntity<String> deleteNotices(String ids);
	
}
