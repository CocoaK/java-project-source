package com.biencloud.smarthome.service.hdfstask.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
/**
 * 系统参数模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IHDFSTaskService extends IService<HdfsTask, Long>{
   
	/**
	 * 
	 * 方法的描述: 查询任务
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:18:08
	 * @param uploadStatus 上
	 * @return
	 */
	public List<HdfsTask> findTask(Integer taskStatus);
	/**
	 * 
	 * 方法的描述: 查询所有任务
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午10:15:32
	 * @return
	 */
	public List<HdfsTask> findTask();
	/**
	 * 
	 * 方法的描述: 分页
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:18
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<HdfsTask> list(int pageNum, int pageSize,String condition,String orderString);
	/**
	 * 
	 * 方法的描述: 分页
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:18
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Paging<HdfsTask> list(int pageNum, int pageSize,String condition,String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:38
	 * @param hdfsTask
	 * @return
	 */
	public boolean saveOrUpdate(HdfsTask hdfsTask);
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:15:58
	 * @param id
	 * @return
	 */
	public HdfsTask findById(Long id);
	/**
	 * 
	 * 方法的描述: 根据id进行删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:51:06
	 * @param id
	 * @return
	 */
	public boolean deleteById(Long id);
	
}
