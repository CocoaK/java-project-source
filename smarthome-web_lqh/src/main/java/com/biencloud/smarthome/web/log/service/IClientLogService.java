package com.biencloud.smarthome.web.log.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfstask.vo.HdfsTaskVO;
import com.biencloud.smarthome.web.log.vo.ClientLogVO;

/**
 * 
 * 类名称：IClientLogService 
 * 类描述：终端日志业务接口 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-24 下午4:12:10
 */
public interface IClientLogService{
	/**
	 * 
	 * 方法的描述: 分页查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午4:13:52
	 * @param pageNum
	 * @param pageSize
	 * @param clientLogVO
	 * @return
	 */
	public PagingVO<ClientLogVO> queryClientLogForPaging(int pageNum, int pageSize,ClientLogVO clientLogVO);
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午4:14:29
	 * @param id
	 * @return
	 */
	public ClientLogVO queryClientLogById(Long id);
}
