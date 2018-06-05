package com.biencloud.smarthome.service.log.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.ClientLog;

/**
 * 
 * 类名称：IClientLogService 
 * 类描述： 终端日志业务接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-7-24 下午2:11:37
 */
public interface IClientLogService extends IService<ClientLog, Long>{
	/**
	 * 
	 * 方法的描述:分页查询 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午2:14:42
	 * @param pageNum
	 * @param pageSize
	 * @param condition
	 * @param orderString
	 * @param values
	 * @return
	 */
	public Paging<ClientLog> queryClientLogForPaging(
			 int pageNum, int pageSize,String condition,String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述: 保存日志
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午2:16:21
	 * @param clientLog
	 * @return
	 */
	public boolean saveClientLog(ClientLog clientLog);
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午2:17:37
	 * @param id
	 * @return
	 */
	public ClientLog queryClientLogById(Long id);
	/**
	 * 
	 * 方法的描述: 根据id进行删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午2:18:44
	 * @param id
	 * @return
	 */
	public boolean deleteClientLogById(Long id);
	
}
