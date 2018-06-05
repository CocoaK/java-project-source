package com.biencloud.smarthome.service.hdfs.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
/**
 * 系统参数模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IHDFSFileService extends IService<LocalHdfs, Long>{

	/**
	 * 
	 * 方法的描述:分页 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午10:58:47
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<LocalHdfs> list(int pageNum, int pageSize,String condition,String orderString);
	/**
	 * 
	 * 方法的描述:分页 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午10:58:47
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<LocalHdfs> list(int pageNum, int pageSize,String condition,String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:05:08
	 * @param LocalHdfs
	 * @return
	 */
	public boolean saveOrUpdate(LocalHdfs localHdfs);
	
}
