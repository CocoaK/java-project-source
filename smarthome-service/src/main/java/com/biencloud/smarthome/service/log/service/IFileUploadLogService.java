package com.biencloud.smarthome.service.log.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.FileUploadLog;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IFileUploadLogService 
 * 类描述：文件上传管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IFileUploadLogService extends IService<FileUploadLog, Long> {
	/**
	 * 查询文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：文件上传日志对象
	 * @return
	 */
	public List<FileUploadLog> queryFileUploadLogForList(FileUploadLog paramsOb);
	/**
	 * 查询文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：文件上传日志对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<FileUploadLog> queryFileUploadLogForPaging(FileUploadLog paramsOb, int pageNum, int pageSize);
	/**
	 * 更新文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:文件上传日志对象
	 */
	public void updateFileUploadLog(FileUploadLog entity) ;
	/**
	 * 保存文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:文件上传日志对象
	 */
	public void saveFileUploadLog(FileUploadLog entity);
	/**
	 * 
	 * 方法的描述: 获取单个文件上传日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：文件上传日志对象主键
	 * @return
	 */
	public FileUploadLog getFileUploadLog(String entityId);
	/**
	 * 
	 * 方法的描述: 删除文件上传日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：文件上传日志对象
	 */
	public void DelFileUploadLog(FileUploadLog entity);
}
