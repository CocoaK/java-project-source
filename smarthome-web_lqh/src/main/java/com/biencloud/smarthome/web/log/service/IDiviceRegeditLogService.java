package com.biencloud.smarthome.web.log.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.vo.DiviceRegeditLogVO;

/**
 * 
 * 类名称：IDiviceRegeditLogVOService 
 * 类描述：设备注册日志管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IDiviceRegeditLogService{
	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：设备注册日志对象
	 * @return
	 */
	public List<DiviceRegeditLogVO> queryDiviceRegeditLogForList(DiviceRegeditLogVO paramsOb);
	/**
	 * 查询设备注册日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：设备注册日志对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<DiviceRegeditLogVO> queryDiviceRegeditLogForPaging(DiviceRegeditLogVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 获取单个设备注册日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：设备注册日志对象主键
	 * @return
	 */
	public DiviceRegeditLogVO getDiviceRegeditLog(String entityId);
}
