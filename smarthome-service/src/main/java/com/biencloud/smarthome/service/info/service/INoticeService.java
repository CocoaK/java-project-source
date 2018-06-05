package com.biencloud.smarthome.service.info.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.NoticeData;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：INoticeService 
 * 类描述： 信息发布管理领域服务接口
 */
public interface INoticeService extends IService<NoticeData, Long> {

	/**
	 * 
	 * 方法的描述: 查询小区公告信息发布列表（分页）
	 */
	public Paging<NoticeData> queryNoticeForPaging(String roomNo, int pageNum, int pageSize);

}
