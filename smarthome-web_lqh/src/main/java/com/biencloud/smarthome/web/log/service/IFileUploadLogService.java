package com.biencloud.smarthome.web.log.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.vo.FileUploadLogVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IFileUploadLogVOService 
 * 类描述：文件上传管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:56:55
 */
public interface IFileUploadLogService{
	/**
	 * 查询文件上传日志
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：文件上传日志对象
	 * @return
	 */
	public List<FileUploadLogVO> queryFileUploadLogForList(FileUploadLogVO paramsOb);
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
	public PagingVO<FileUploadLogVO> queryFileUploadLogForPaging(FileUploadLogVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 获取单个文件上传日志对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：文件上传日志对象主键
	 * @return
	 */
	public FileUploadLogVO getFileUploadLog(String entityId);
}
