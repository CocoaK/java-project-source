package com.biencloud.smarthome.cxfservice.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.log.model.FileUploadLog;
/**
 * 
 * 类名称：SmartHomeFileService 
 * 类描述： 针对文件服务器发布的服务
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-4-26 下午7:51:06
 */

@WebService
public interface SmartHomeFileService {

	/**
	 * 
	 * 方法的描述: 查询任务
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:18:08
	 * @param uploadStatus 上
	 * @return
	 */
	@WebMethod
	public List<HdfsTask> findTask(@WebParam Integer taskStatus);
	/**
	 * 
	 * 方法的描述: 查询所有
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午10:13:29
	 * @return
	 */
	@WebMethod
	public List<HdfsTask> findAllTask();
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:13:32
	 * @param id
	 * @return
	 */
	@WebMethod
	public HdfsTask findById(@WebParam Long id);
	/**
	 * 
	 * 方法的描述: 删除任务
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:50:21
	 * @param id
	 * @return
	 */
	@WebMethod
	public boolean deleteTask(@WebParam Long id);
	
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:38
	 * @param hdfsTask
	 * @return
	 */
	@WebMethod
	public boolean saveOrUpdateHdfsTask(@WebParam HdfsTask hdfsTask);
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:05:08
	 * @param LocalHdfs
	 * @return
	 */
	@WebMethod
	public boolean saveOrUpdateLocalHdfs(@WebParam LocalHdfs localHdfs);
	/**
	 * 保存文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:文件上传日志对象
	 */
 	@WebMethod
	public void saveFileUploadLog(@WebParam FileUploadLog entity);
 	/**
 	 * 
 	 * 方法的描述: 查询参数值
 	 * @author: kouy  
 	 * @version: 0.1
 	 * @date: 2012-8-14 下午8:44:59
 	 * @param key
 	 * @return
 	 */
 	@WebMethod
 	public String queryParamValueByParamCode(@WebParam String paramCode);

}
