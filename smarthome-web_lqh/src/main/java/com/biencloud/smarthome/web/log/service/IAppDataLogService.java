package com.biencloud.smarthome.web.log.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.vo.AppDataLogVO;

/**
 * 类名称：IAppDataLogService 
 * 类描述： app请求数据日志服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-19 下午3:28:51
 */
public interface IAppDataLogService {
	/**
	 * 方法的描述: 保存或者更新APP请求日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-19 下午3:29:21
	 * @param appDataLogVO
	 */
	public AppDataLogVO saveOrUpdateAppDataLog(AppDataLogVO appDataLogVO);
	
	/**
	 * 方法的描述:分页查询
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-22 上午10:06:03
	 * @param appDataLogVO
	 * @param pageNum
	 * @param pageSize
	 * @return PagingVO
	 */
	public PagingVO<AppDataLogVO> queryAppDataLogForPaging(AppDataLogVO appDataLogVO, int pageNum, int pageSize);
	
	/**
	 * 方法的描述: 根据ID获取app日志
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-10-22 上午10:07:54
	 * @param id
	 * @return AppDataLogVO
	 */
	public AppDataLogVO getAppDataLogById(Integer id);

}
